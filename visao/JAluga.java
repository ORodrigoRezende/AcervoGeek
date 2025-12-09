package visao;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.LocalDate;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import modelo.Aluga;
import modelo.Cliente;
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;

public class JAluga extends JFrame implements java.awt.event.ActionListener {

    private BancodeDados bd;
    private JTextField tfId, tfData, tfValorTotal;
    private JComboBox<Cliente> cbClientes;
    private JButton btsv, btal, btrm, btcn;
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JAluga(BancodeDados bd) {
        super("Cadastro de Aluguéis");
        this.bd = bd;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);

        JPanel form = new JPanel(new GridBagLayout());
        form.setBorder(BorderFactory.createTitledBorder("Dados do Aluguel"));
        GridBagConstraints f = new GridBagConstraints();
        f.insets = new Insets(6, 6, 6, 6);
        f.fill = GridBagConstraints.HORIZONTAL;
        f.weightx = 1.0;

        // ID Aluga
        f.gridx = 0; f.gridy = 0;
        form.add(new JLabel("ID Aluguel:"), f);
        tfId = new JTextField();
        f.gridy = 1;
        form.add(tfId, f);

        // Cliente (combo)
        f.gridy = 2;
        form.add(new JLabel("Cliente:"), f);
        cbClientes = new JComboBox<>();
        f.gridy = 3;
        form.add(cbClientes, f);

        // Data (read-only)
        f.gridy = 4;
        form.add(new JLabel("Data (início):"), f);
        tfData = new JTextField(LocalDate.now().toString());
        tfData.setEditable(false);
        f.gridy = 5;
        form.add(tfData, f);

        // Valor total (read-only)
        f.gridy = 6;
        form.add(new JLabel("Valor Total:"), f);
        tfValorTotal = new JTextField("0.0");
        tfValorTotal.setEditable(false);
        f.gridy = 7;
        form.add(tfValorTotal, f);

        // Buttons
        f.gridy = 8;
        f.fill = GridBagConstraints.NONE;
        f.anchor = GridBagConstraints.CENTER;
        JPanel btnRow = new JPanel();
        btsv = new JButton("Salvar"); btsv.addActionListener(this);
        btal = new JButton("Alterar"); btal.addActionListener(this);
        btrm = new JButton("Apagar"); btrm.addActionListener(this);
        btcn = new JButton("Limpar"); btcn.addActionListener(this);
        btnRow.add(btsv); btnRow.add(btal); btnRow.add(btrm); btnRow.add(btcn);
        form.add(btnRow, f);

        gbc.gridx = 0; gbc.gridy = 0;
        gbc.weightx = 0.35; gbc.fill = GridBagConstraints.BOTH;
        add(form, gbc);

        // Tabela de alugueis
        modeloTabela = new DefaultTableModel() {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        modeloTabela.addColumn("ID");
        modeloTabela.addColumn("Cliente");
        modeloTabela.addColumn("Data");
        modeloTabela.addColumn("Valor Total");

        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(22);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Aluguéis"));

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.weightx = 0.65; gbc.gridheight = 1;
        add(scroll, gbc);

        // listener para seleção na tabela
        tabela.getSelectionModel().addListSelectionListener((ListSelectionListener) e -> {
            if (!e.getValueIsAdjusting() && tabela.getSelectedRow() != -1) {
                int modelRow = tabela.convertRowIndexToModel(tabela.getSelectedRow());
                Object idVal = modeloTabela.getValueAt(modelRow, 0);
                if (idVal != null) {
                    int id = Integer.parseInt(idVal.toString());
                    try {
                        Aluga a = bd.getrAluga().buscar(id);
                        tfId.setText(String.valueOf(a.getId()));
                        tfId.setEditable(false);
                        // seleciona cliente no combo
                        if (a.getCliente() != null) cbClientes.setSelectedItem(a.getCliente());
                        tfData.setText(a.getDataAluguel().toString());
                        tfValorTotal.setText(String.valueOf(a.getValorTotal()));
                    } catch (IDNaoExistenteExeception ex) {
                        System.err.println("Aluguel não encontrado: " + id);
                    }
                }
            }
        });

        // popula combo e tabela
        carregarClientes();
        carregarTabela();
    }

    private void carregarClientes() {
        DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<>();
        if (bd != null && bd.getrCliente() != null) {
            for (Cliente c : bd.getrCliente().getEntidades()) {
                model.addElement(c);
            }
        }
        cbClientes.setModel(model);
        // mostra apenas o nome no combo
        cbClientes.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Cliente) {
                    setText(((Cliente) value).getNome_do_cliente());
                }
                return this;
            }
        });
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        if (bd == null || bd.getrAluga() == null) return;
        for (Aluga a : bd.getrAluga().getEntidades()) {
            String clienteNome = (a.getCliente() != null) ? a.getCliente().getNome_do_cliente() : "";
            modeloTabela.addRow(new Object[] { a.getId(), clienteNome, a.getDataAluguel(), a.getValorTotal() });
        }
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if (e.getSource() == btsv) {
            int id;
            try { id = Integer.parseInt(tfId.getText().trim()); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente cliente = (Cliente) cbClientes.getSelectedItem();
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (bd.getrAluga().idExiste(id)) {
                JOptionPane.showMessageDialog(this, "ID de aluguel já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Aluga a = new Aluga(id, cliente);
            bd.getrAluga().inserir(a);
            carregarTabela();
            // abrir janela de itens para esse aluguel (passa this para atualizar depois)
            new JItensAluga(bd, a, this).setVisible(true);

        } else if (e.getSource() == btal) {
            int id;
            try { id = Integer.parseInt(tfId.getText().trim()); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Cliente cliente = (Cliente) cbClientes.getSelectedItem();
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Selecione um cliente válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Aluga existente = bd.getrAluga().buscar(id);
                existente.setCliente(cliente);
                // persistir alteração
                bd.getrAluga().alterar(existente);
                carregarTabela();
                // abre janela de itens passando a aluga atualizada
                new JItensAluga(bd, existente, this).setVisible(true);
            } catch (IDNaoExistenteExeception ex) {
                JOptionPane.showMessageDialog(this, "Falha ao alterar — ID não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == btrm) {
            int sel = tabela.getSelectedRow();
            if (sel == -1) return;
            int modelIndex = tabela.convertRowIndexToModel(sel);
            Object idVal = modeloTabela.getValueAt(modelIndex, 0);
            if (idVal == null) return;
            int id = Integer.parseInt(idVal.toString());
            boolean ok = bd.getrAluga().excluir(id);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "Falha ao excluir — ID não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            carregarTabela();
        } else if (e.getSource() == btcn) {
            tfId.setText("");
            tfId.setEditable(true);
            cbClientes.setSelectedIndex(-1);
            tfData.setText(LocalDate.now().toString());
            tfValorTotal.setText("0.0");
            tabela.clearSelection();
        }
    }

    // método público para ser chamado por JItensAluga após alteração de itens
    public void refreshAfterItens(Aluga a) {
        carregarClientes();
        carregarTabela();
        tfValorTotal.setText(String.valueOf(a.getValorTotal()));
    }
}