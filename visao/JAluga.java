package visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import modelo.Aluga;
import modelo.Cliente;
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;

public class JAluga extends JFrame implements ActionListener {

    private BancodeDados bd; //Cria uma copia do banco de dados
    private DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private JTextField tfId, tfIdCliente, tfDataAluguel, tfDataDevolucao; //Campos de texto
    private JButton btsv, btal, btrm, btcn; //Botoes
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JAluga(BancodeDados bd) {
        super("Gerenciar Aluguéis");
        this.bd = bd;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null); // Coloca o painel no meio da tela
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); //Cria um grid
        gbc.insets = new Insets(8, 8, 8, 8);

        // Coluna Esquerda para escrever os dados
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Dados dos Aluguéis"));
        GridBagConstraints f = new GridBagConstraints();
        f.insets = new Insets(6, 6, 6, 6);
        f.fill = GridBagConstraints.HORIZONTAL;
        f.weightx = 1.0;

        // ID ALuguel
        f.gridx = 0;
        f.gridy = 0;
        f.gridwidth = 1;
        form.add(new JLabel("ID do Aluguel:"), f);
        tfId = new JTextField();
        f.gridy = 1;
        form.add(tfId, f);

        // ID Cliente
        f.gridy = 2;
        form.add(new JLabel("ID do Cliente:"), f);
        tfIdCliente = new JTextField();
        f.gridy = 3;
        form.add(tfIdCliente, f);

        //Data Aluguel
        f.gridy = 4;
        form.add(new JLabel("Data do Aluguel (dd/MM/yyyy):"), f);
        tfDataAluguel = new JTextField();
        f.gridy = 5;
        form.add(tfDataAluguel, f);

        // Data Devolucao
        f.gridy = 6;
        form.add(new JLabel("Data de Devolução (dd/MM/yyyy):"), f);
        tfDataDevolucao = new JTextField();
        f.gridy = 7;
        form.add(tfDataDevolucao, f);

        // Botoes
        f.gridy = 10;
        f.fill = GridBagConstraints.NONE;
        f.anchor = GridBagConstraints.CENTER;
        f.gridwidth = 1;
        JPanel btnRow = new JPanel();
        btsv = new JButton("Cadastrar"); 
        btsv.addActionListener(this);
        btal = new JButton("Alterar"); 
        btal.addActionListener(this);
        btrm = new JButton("Apagar"); 
        btrm.addActionListener(this);
        btcn = new JButton("Listar"); 
        btcn.addActionListener(this);
        btnRow.add(btsv); 
        btnRow.add(btal); 
        btnRow.add(btrm); 
        btnRow.add(btcn);
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
                return false;
            }
        };

        modeloTabela.addColumn("ID Aluguel");
        modeloTabela.addColumn("ID Cliente");
        modeloTabela.addColumn("Data Aluguel");
        modeloTabela.addColumn("Data Devolução");

        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(22);
        tabela.setAutoCreateRowSorter(true);
        tabela.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
                int modelRow = tabela.convertRowIndexToModel(tabela.getSelectedRow());
                Object idVal = modeloTabela.getValueAt(modelRow, 0);

                tfId.setEditable(false);

                if (idVal != null) {
                    int id = Integer.parseInt(idVal.toString());
                    try {
                        Aluga a = bd.getrAluga().buscar(id);
                        tfId.setText(String.valueOf(a.getId()));
                        tfIdCliente.setText(String.valueOf(a.getCliente().getId()));

                        // <-- MODIFICADO (formato brasileiro)
                        tfDataAluguel.setText(a.getDataAluguel().format(formato));
                        tfDataDevolucao.setText(a.getDataDevolucao().format(formato));

                    } catch (IDNaoExistenteExeception ex) {
                        System.err.println("Aluguel não encontrado: " + id);
                    }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Aluguéis"));

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
        if (bd == null || bd.getrAluga() == null) return;
        for (Aluga a : bd.getrAluga().getEntidades()) {
            modeloTabela.addRow(new Object[] {
                a.getId(),
                a.getCliente().getId(),

                a.getDataAluguel().format(formato),
                a.getDataDevolucao().format(formato)
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
            try { 
                id = Integer.parseInt(tfId.getText().trim()); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int idCliente;
            try { 
                idCliente = Integer.parseInt(tfIdCliente.getText().trim()); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID do cliente inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (bd.getrAluga().idExiste(id)) {
                JOptionPane.showMessageDialog(this, "ID já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente c;
            try {
                c = bd.getrCliente().buscar(idCliente);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            LocalDate da, dd;
            try {
                da = LocalDate.parse(tfDataAluguel.getText().trim(), formato);
                dd = LocalDate.parse(tfDataDevolucao.getText().trim(), formato);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Data inválida. Use dd/MM/yyyy.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Aluga a = new Aluga(id, c, da, dd);
            bd.getrAluga().inserir(a);
            CarregarTabelodoBanco();
            LimpaCampos();

        } else if (e.getSource() == btal) {

            int sel = tabela.getSelectedRow();
            if (sel == -1) return;

            int id;
            try { 
                id = Integer.parseInt(tfId.getText().trim()); 
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Aluga a = bd.getrAluga().buscar(id);

                a.setDataDevolucao(LocalDate.parse(tfDataDevolucao.getText().trim(), formato));

                bd.getrAluga().alterar(a);
                CarregarTabelodoBanco();
                tabela.clearSelection();
                LimpaCampos();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btrm) {

            int sel = tabela.getSelectedRow();
            if (sel == -1) return;

            int modelIndex = tabela.convertRowIndexToModel(sel);
            Object idVal = modeloTabela.getValueAt(modelIndex, 0);
            if (idVal == null) return;

            int id = Integer.parseInt(idVal.toString());

            if (bd != null && bd.getrAluga() != null) {
                boolean ok = bd.getrAluga().excluir(id);
                if (!ok) {  
                    JOptionPane.showMessageDialog(this, "Falha ao excluir.", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            modeloTabela.removeRow(modelIndex);
            LimpaCampos();

        } else if (e.getSource() == btcn) {

            LimpaCampos();
            tabela.clearSelection();
            CarregarTabelodoBanco();
        }
    }

    private void LimpaCampos() {
        tfId.setText("");
        tfId.setEditable(true);
        tfIdCliente.setText("");
        tfDataAluguel.setText("");
        tfDataDevolucao.setText("");
    }
}