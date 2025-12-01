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
import modelo.Cliente;
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;

public class JCliente extends JFrame implements ActionListener {

    private BancodeDados bd; //Cria uma copia do banco de dados
    private JTextField tfId, tfNome, tfTel, tfCpf, tfEnd; //Campos de texto
    private JButton btsv, btal, btrm, btcn; //Botoes
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JCliente(BancodeDados bd) {
        super("Cadastro de Clientes");
        this.bd = bd;
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null); // Coloca o painel no meio da tela
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); //Cria um grid
        gbc.insets = new Insets(8, 8, 8, 8);

        // Coluna Esquerda para escrever os dados
        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Dados do Cliente"));
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
        form.add(new JLabel("Nome do cliente:"), f);
        tfNome = new JTextField();
        f.gridy = 3;
        form.add(tfNome, f);

        // Telefone
        f.gridy = 4;
        form.add(new JLabel("Telefone:"), f);
        tfTel = new JTextField();
        f.gridy = 5;
        form.add(tfTel, f);

        // CPF
        f.gridy = 6;
        form.add(new JLabel("CPF:"), f);
        tfCpf = new JTextField();
        f.gridy = 7;
        form.add(tfCpf, f);

        // Endereço
        f.gridy = 8;
        form.add(new JLabel("Endereço:"), f);
        tfEnd = new JTextField();
        f.gridy = 9;
        form.add(tfEnd, f);

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
        modeloTabela.addColumn("Telefone");
        modeloTabela.addColumn("CPF");
        modeloTabela.addColumn("Endereço");

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
                    Cliente c = bd.getrCliente().buscar(id);
                    tfId.setText(String.valueOf(c.getId()));
                    tfNome.setText(c.getNome_do_cliente());
                    tfTel.setText(c.getTelefone_do_cliente());
                    tfCpf.setText(c.getCpf());
                    tfEnd.setText(c.getEndereco());
                  } catch (IDNaoExistenteExeception ex) {
                    System.err.println("Cliente não encontrado: " + id);
                  }
                }
            }
        });

        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Clientes"));

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
        if (bd == null || bd.getrCliente() == null) return;
        for (Cliente c : bd.getrCliente().getEntidades()) { // Da erro no vsCode por causa do generico
            modeloTabela.addRow(new Object[] {
                c.getId(),
                c.getNome_do_cliente(),
                c.getTelefone_do_cliente(),
                c.getCpf(),
                c.getEndereco()
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

            if (bd.getrCliente().idExiste(id)) {
                JOptionPane.showMessageDialog(this, "ID já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente c = new Cliente(id, tfNome.getText(), tfTel.getText(), tfCpf.getText(), tfEnd.getText());
            bd.getrCliente().inserir(c);
            modeloTabela.addRow(new Object[] { id, c.getNome_do_cliente(), c.getTelefone_do_cliente(), c.getCpf(), c.getEndereco() });
            LimpaCampos();

        } else if (e.getSource() == btal) {
            String textoId = tfId.getText().trim();

            if (textoId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Para alterar, selecione um cliente ou digite o ID.", "Aviso", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int id;
            try { id = Integer.parseInt(tfId.getText().trim()); } 
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido. Digite um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean ok = false;
            Cliente c = new Cliente(id, tfNome.getText(), tfTel.getText(), tfCpf.getText(), tfEnd.getText());
            if (bd != null && bd.getrCliente() != null) {
                ok = bd.getrCliente().alterar(c);
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
            if (bd != null && bd.getrCliente() != null) {
                boolean ok = bd.getrCliente().excluir(id);
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
        tfTel.setText("");
        tfCpf.setText("");
        tfEnd.setText("");
    }

}