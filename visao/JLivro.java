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
import modelo.Livro;
import modelo.Produto; // Import necessário para o Polimorfismo
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;

public class JLivro extends JFrame implements ActionListener {

    private BancodeDados bd; 
    private JTextField tfId, tfNome, tfGenero, tfValor, tfAutor; 
    private JButton btsv, btal, btrm, btcn; 
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JLivro(BancodeDados bd) {
        super("Cadastro de Livros");
        this.bd = bd;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Fecha apenas a janela
        setSize(1280, 720);
        setLocationRelativeTo(null); 
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); 
        gbc.insets = new Insets(8, 8, 8, 8);

        // --- FORMULÁRIO ---
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Dados do Livro"));
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
        form.add(new JLabel("Nome do Livro:"), f);
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

        // Autor
        f.gridy = 8;
        form.add(new JLabel("Autor:"), f);
        tfAutor = new JTextField();
        f.gridy = 9;
        form.add(tfAutor, f);

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

        // Adiciona formulário (centralizado verticalmente como JCliente)
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

        // --- TABELA ---
        modeloTabela = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };

        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Genero");
        modeloTabela.addColumn("Valor");
        modeloTabela.addColumn("Autor");

        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(22);
        tabela.setAutoCreateRowSorter(true);

        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
                int modelRow = tabela.convertRowIndexToModel(tabela.getSelectedRow());
                Object idVal = modeloTabela.getValueAt(modelRow, 0);

                if (idVal != null) {
                    int id = Integer.parseInt(idVal.toString());
                    try {
                        Produto p = bd.getrProduto().buscar(id);

                        if (p instanceof Livro) {
                            Livro livroEncontrado = (Livro) p;
                            
                            tfId.setText(String.valueOf(livroEncontrado.getId()));
                            tfId.setEditable(false);
                            tfNome.setText(livroEncontrado.getNome());
                            tfGenero.setText(livroEncontrado.getGenero());
                            tfValor.setText(String.valueOf(livroEncontrado.getValor()));
                            tfAutor.setText(livroEncontrado.getAutor());
                        }

                    } catch (persistencia.IDNaoExistenteExeception ex) {
                        System.err.println("Erro interno: ID na tabela não encontrado no banco.");
                    }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Livros Cadastrados"));

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 0.65; gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);

        CarregarTabelodoBanco();
    }

    // helper igual ao JCliente para centralizar o form verticalmente
    private JPanel createSpacer() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        return p;
    }

    private void CarregarTabelodoBanco() {
        modeloTabela.setRowCount(0);
        List<Livro> listaLivros = bd.getListarLivros(); 
        
        for (Livro l : listaLivros) {
            modeloTabela.addRow(new Object[] {
                l.getId(),
                l.getNome(),
                l.getGenero(),
                l.getValor(),
                l.getAutor()
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        // --- SALVAR ---
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
                
                Livro l = new Livro(tfNome.getText(), tfGenero.getText(), valor, tfAutor.getText(), id);
                
                bd.getrProduto().inserir(l); 
                
                CarregarTabelodoBanco();
                LimpaCampos();
                JOptionPane.showMessageDialog(this, "Livro salvo com sucesso!");

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Verifique se o ID é inteiro e o Valor é numérico.", "Erro de Formato", JOptionPane.ERROR_MESSAGE);
            }
        } 
        
        // --- ALTERAR ---
        else if (e.getSource() == btal) {
            try {
                int id = Integer.parseInt(tfId.getText().trim());
                float valor = Float.parseFloat(tfValor.getText().trim());

                Livro l = new Livro(tfNome.getText(), tfGenero.getText(), valor, tfAutor.getText(), id);
                
                boolean sucesso = bd.getrProduto().alterar(l);
    
                if (sucesso) {
                    CarregarTabelodoBanco();
                    LimpaCampos();
                    JOptionPane.showMessageDialog(this, "Livro alterado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(this, "Não foi possível alterar: ID não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Dados inválidos nos campos numéricos.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } 
        
        // --- APAGAR ---
        else if (e.getSource() == btrm) {
            try {
                int id = Integer.parseInt(tfId.getText().trim());
                
                boolean removeu = bd.getrProduto().excluir(id);
                
                if (removeu) {
                    CarregarTabelodoBanco();
                    LimpaCampos();
                    JOptionPane.showMessageDialog(this, "Livro excluído.");
                } else {
                    JOptionPane.showMessageDialog(this, "ID não encontrado para exclusão.", "Erro", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Selecione um livro ou digite um ID válido.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } 
        
        // --- LIMPAR ---
        else if (e.getSource() == btcn) {
            LimpaCampos();
            tabela.clearSelection();
        }
    }

    private void LimpaCampos() {
        tfId.setText("");
        tfId.setEditable(true);
        tfNome.setText("");
        tfGenero.setText("");
        tfValor.setText("");
        tfAutor.setText("");
    }

}