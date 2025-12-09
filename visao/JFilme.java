package visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List; // Import necessário
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import modelo.Filme;
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;

public class JFilme extends JFrame implements ActionListener {

    private BancodeDados bd; 
    private JTextField tfId, tfNome, tfGenero, tfValor, tfDiretor; 
    private JButton btsv, btal, btrm, btcn; 
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JFilme(BancodeDados bd) {
        super("Cadastro de Filmes");
        this.bd = bd;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Use DISPOSE para não fechar o programa todo, só a janela
        setSize(1280, 720);
        setLocationRelativeTo(null); 
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(8, 8, 8, 8);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Dados do Filme"));
        GridBagConstraints f = new GridBagConstraints();
        f.insets = new Insets(6, 6, 6, 6);
        f.fill = GridBagConstraints.HORIZONTAL;
        f.weightx = 1.0;

        // ID
        f.gridx = 0; f.gridy = 0;
        form.add(new JLabel("ID:"), f);
        tfId = new JTextField();
        f.gridy = 1;
        form.add(tfId, f);

        // Nome
        f.gridy = 2;
        form.add(new JLabel("Nome do Filme:"), f);
        tfNome = new JTextField();
        f.gridy = 3;
        form.add(tfNome, f);

        // Genero
        f.gridy = 4;
        form.add(new JLabel("Genero:"), f);
        tfGenero = new JTextField();
        f.gridy = 5;
        form.add(tfGenero, f);

        // Valor
        f.gridy = 6;
        form.add(new JLabel("Valor:"), f);
        tfValor = new JTextField();
        f.gridy = 7;
        form.add(tfValor, f);

        // Diretor
        f.gridy = 8;
        form.add(new JLabel("Diretor:"), f);
        tfDiretor = new JTextField();
        f.gridy = 9;
        form.add(tfDiretor, f);

        // Botões
        f.gridy = 10;
        f.fill = GridBagConstraints.NONE;
        f.anchor = GridBagConstraints.CENTER;
        JPanel btnRow = new JPanel();
        btsv = new JButton("Salvar"); btsv.addActionListener(this);
        btal = new JButton("Alterar"); btal.addActionListener(this);
        btrm = new JButton("Apagar"); btrm.addActionListener(this);
        btcn = new JButton("Limpar"); btcn.addActionListener(this);
        btnRow.add(btsv); btnRow.add(btal); btnRow.add(btrm); btnRow.add(btcn);
        form.add(btnRow, f);

        // centralizar verticalmente (mesma técnica do JCliente)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        add(createSpacer(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 1;
        gbc.weightx = 0.35;
        add(form, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 1.0;
        add(createSpacer(), gbc);

        // Tabela à direita (mantém o restante do código que cria/insere a tabela)
        // cria modelo e tabela (se ainda não existir)
        modeloTabela = new DefaultTableModel(new Object[] { "ID", "Nome", "Genero", "Valor", "Diretor" }, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(22);
        tabela.setAutoCreateRowSorter(true);

        // o 'scroll' é o JScrollPane que torna a tabela rolável
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Filmes Cadastrados"));

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 0.65; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);

        CarregarTabelodoBanco();
    }

    private JPanel createSpacer() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        return p;
    }

    private void CarregarTabelodoBanco() {
        modeloTabela.setRowCount(0);
        
        List<Filme> listaFilmes = bd.getListarFilmes(); 
        
        for (Filme f : listaFilmes) {
            modeloTabela.addRow(new Object[] {
                f.getId(),
                f.getNome(),
                f.getGenero(),
                f.getValor(),
                f.getDiretor()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // --- BOTÃO SALVAR ---
        if (e.getSource() == btsv) {
            try {
                int id = Integer.parseInt(tfId.getText().trim());
                
                try {
                    bd.getrProduto().buscar(id);
                    JOptionPane.showMessageDialog(this, "ID já existe! Escolha outro.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return; 
                } catch (IDNaoExistenteExeception ex) {
                }

                float valor = Float.parseFloat(tfValor.getText().trim());
                
                Filme f = new Filme(id, tfDiretor.getText(), tfGenero.getText(), tfNome.getText(), valor);
                
                bd.getrProduto().inserir(f);
                
                CarregarTabelodoBanco();
                LimpaCampos();
                JOptionPane.showMessageDialog(this, "Filme salvo com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifique se o ID é inteiro e o Valor é numérico.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            }
        } 
        
        else if (e.getSource() == btal) {
            try {
                int id = Integer.parseInt(tfId.getText().trim());
                float valor = Float.parseFloat(tfValor.getText().trim());

                Filme f = new Filme(id, tfDiretor.getText(), tfGenero.getText(), tfNome.getText(), valor);
                boolean sucesso = bd.getrProduto().alterar(f);
    
                if (sucesso) {
                    CarregarTabelodoBanco();
                    LimpaCampos();
                    JOptionPane.showMessageDialog(this, "Filme alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível alterar: ID não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
    }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Dados inválidos nos campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } 
        
        else if (e.getSource() == btrm) {
            try {
                int id = Integer.parseInt(tfId.getText().trim());
                boolean removeu = bd.getrProduto().excluir(id);
                
                if (removeu) {
                    CarregarTabelodoBanco();
                    LimpaCampos();
                    JOptionPane.showMessageDialog(this, "Filme excluído.");
                } else {
                    JOptionPane.showMessageDialog(this, "ID não encontrado para exclusão.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Selecione um filme ou digite um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } 
        
        else if (e.getSource() == btcn) {
            LimpaCampos();
            tabela.clearSelection();
        }
    }

    private void LimpaCampos() {
        tfId.setText("");
        tfId.setEditable(true); // Libera o ID para novo cadastro
        tfNome.setText("");
        tfGenero.setText("");
        tfValor.setText("");
        tfDiretor.setText("");
    }
}