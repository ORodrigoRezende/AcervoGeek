package visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.List;
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
import modelo.Produto;
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;

public class JAluga extends JFrame implements ActionListener {

    private BancodeDados bd;
    private JTextField tfId, tfClienteId, tfData, tfValorTotal;
    private JButton btsv, btal, btrm, btcn;
    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JAluga(BancodeDados bd) {
        super("Cadastro de Aluguéis");
        this.bd = bd;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(1000, 600);
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

        // Cliente ID
        f.gridy = 2;
        form.add(new JLabel("ID Cliente:"), f);
        tfClienteId = new JTextField();
        f.gridy = 3;
        form.add(tfClienteId, f);

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

        carregarTabela();
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
            // Salvar e abrir janela de itens
            int id;
            try { id = Integer.parseInt(tfId.getText().trim()); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int clienteId;
            try { clienteId = Integer.parseInt(tfClienteId.getText().trim()); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID do cliente inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cliente cliente;
            try { cliente = bd.getrCliente().buscar(clienteId); }
            catch (IDNaoExistenteExeception ex) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (bd.getrAluga().idExiste(id)) {
                JOptionPane.showMessageDialog(this, "ID de aluguel já existe.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Aluga a = new Aluga(id, cliente);
            bd.getrAluga().inserir(a);
            carregarTabela();
            // abrir janela de itens para esse aluguel
            new JItensAluga(bd, a).setVisible(true);
        } else if (e.getSource() == btal) {
            // Alterar e abrir itens
            int id;
            try { id = Integer.parseInt(tfId.getText().trim()); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            int clienteId;
            try { clienteId = Integer.parseInt(tfClienteId.getText().trim()); }
            catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID do cliente inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Cliente cliente;
            try { cliente = bd.getrCliente().buscar(clienteId); }
            catch (IDNaoExistenteExeception ex) {
                JOptionPane.showMessageDialog(this, "Cliente não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Aluga a = new Aluga(id, cliente);
            boolean ok = bd.getrAluga().alterar(a);
            if (!ok) {
                JOptionPane.showMessageDialog(this, "Falha ao alterar — ID não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                return;
            }
            carregarTabela();
            // abre janela de itens
            try {
                Aluga atualizado = bd.getrAluga().buscar(id);
                new JItensAluga(bd, atualizado).setVisible(true);
            } catch (IDNaoExistenteExeception ex) {
                // não deve acontecer
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
            tfClienteId.setText("");
            tfData.setText(LocalDate.now().toString());
            tfValorTotal.setText("0.0");
            tabela.clearSelection();
        }
    }
}