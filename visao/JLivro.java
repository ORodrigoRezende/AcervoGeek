package visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;

public class JLivro extends JFrame implements ActionListener {

    private BancodeDados bd; //Cria uma copia do banco de dados
    private JTextField tfId, tfNome, tfGenero, tfValor, tfAutor; //Campos de texto
    private JButton btsv, btal, btrm, btcn; //Botoes
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JLivro(BancodeDados bd) {
        super("Cadastro de Livros");
        this.bd = bd;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null); // Coloca o painel no meio da tela
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); //Cria um grid
        gbc.insets = new Insets(8, 8, 8, 8);

        // Coluna Esquerda para escrever os dados
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Dados do Livro"));
        GridBagConstraints f = new GridBagConstraints();
        f.insets = new Insets(6, 6, 6, 6);
        f.fill = GridBagConstraints.HORIZONTAL;
        f.weightx = 1.0;

        // ID
        f.gridx = 0;
        f.gridy = 0;
        f.gridwidth = 1;
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

        //Genero
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

        // Botoes
        f.gridy = 10;
        f.fill = GridBagConstraints.NONE;
        f.anchor = GridBagConstraints.CENTER;
        f.gridwidth = 1;
        JPanel btnRow = new JPanel();
        btsv = new JButton("Salvar"); btsv.addActionListener(this);
        btal = new JButton("Alterar"); btal.addActionListener(this);
        btrm = new JButton("Apagar"); btrm.addActionListener(this);
        btcn = new JButton("Limpar"); btcn.addActionListener(this);
        btnRow.add(btsv); btnRow.add(btal); btnRow.add(btrm); btnRow.add(btcn);
        form.add(btnRow, f);

        // Criação de 3 linhas para centralizar o form
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

        // Coluna a direita para a tabela
        modeloTabela = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Impede a edição direta na tabela
            }
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

                tfId.setEditable(false);

                if (idVal != null) {  //Verificação do if vai mudar 
                  int id = Integer.parseInt(idVal.toString());
                  try {
                    Livro c = bd.getrLivro().buscar(id);
                    tfId.setText(String.valueOf(c.getId()));
                    tfNome.setText(c.getNome());
                    tfGenero.setText(c.getGenero());
                    tfValor.setText(String.valueOf(c.getValor()));
                    tfAutor.setText(c.getAutor());
                  } catch (IDNaoExistenteExeception ex) {
                    System.err.println("Livro não encontrado: " + id);
                  }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Livros"));

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 0.65;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);

        CarregarTabelodoBanco();
    }

    private void CarregarTabelodoBanco() {
        modeloTabela.setRowCount(0);
        if (bd == null || bd.getrLivro() == null) return;
        for (Livro c : bd.getrLivro().getEntidades()) { // Da erro no vsCode por causa do generico
            modeloTabela.addRow(new Object[] {
                c.getId(),
                c.getNome(),
                c.getGenero(),
                c.getValor(),
                c.getAutor()
            });
        }
    }

    private JPanel createSpacer() {
        JPanel p = new JPanel();
        p.setOpaque(false);
        return p;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btsv) {
            
            int id; 
            try { id = Integer.parseInt(tfId.getText().trim()); } //Revisar isso que foi gerado, tem que colocar a nossa exceção a ser criada
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (bd.getrLivro().idExiste(id)) {
                JOptionPane.showMessageDialog(this, "ID já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            float valor;
            try {
            valor = Float.parseFloat(tfValor.getText().trim());}
             catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite um números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
             }

            Livro f = new Livro(tfNome.getText(), tfGenero.getText(),valor,tfAutor.getText(),id);
            bd.getrLivro().inserir(f);
            modeloTabela.addRow(new Object[] { id, f.getNome(), f.getGenero(), f.getValor(), f.getAutor() });
            LimpaCampos();

        } else if (e.getSource() == btal) {
             int sel = tabela.getSelectedRow();
            if (sel == -1) return;
            int modelIndex = tabela.convertRowIndexToModel(sel);

            int id;
            try { id = Integer.parseInt(tfId.getText().trim()); } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            float valor;
            try {
            valor = Float.parseFloat(tfValor.getText().trim());}
             catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Valor inválido. Digite um números.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
             }

            boolean ok = false;
            Livro f = new Livro(tfNome.getText(), tfGenero.getText(),valor,tfAutor.getText(),id);
            if (bd != null && bd.getrLivro() != null) {
                ok = bd.getrLivro().alterar(f);
                if (!ok) {  
                    JOptionPane.showMessageDialog(this, "Falha ao alterar — ID não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            if(ok){ // Se foi alterado atualiza a tabela e limpa os campos
                CarregarTabelodoBanco();
                tabela.clearSelection();
                LimpaCampos();
            }

        } else if (e.getSource() == btrm) {
            int sel = tabela.getSelectedRow();
            if (sel == -1) return;
            int modelIndex = tabela.convertRowIndexToModel(sel);
            Object idVal = modeloTabela.getValueAt(modelIndex, 0);
            if (idVal == null) return;
            int id = Integer.parseInt(idVal.toString());
            if (bd != null && bd.getrLivro() != null) {
                boolean ok = bd.getrLivro().excluir(id);
                if (!ok) {  
                    JOptionPane.showMessageDialog(this, "Falha ao excluir — ID não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
            modeloTabela.removeRow(modelIndex);
            LimpaCampos();

        } else if (e.getSource() == btcn) {
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
