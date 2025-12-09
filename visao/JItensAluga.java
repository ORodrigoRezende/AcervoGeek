package visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Aluga;
import modelo.Filme;
import modelo.ItensAluga;
import modelo.Jogo;
import modelo.Livro;
import modelo.Produto;
import persistencia.BancodeDados;

public class JItensAluga extends JFrame implements java.awt.event.ActionListener {

    private BancodeDados bd;
    private Aluga aluguel;
    private JAluga parent;

    private JComboBox<String> cbTipo;            // Filme / Livro / Jogo
    private JComboBox<Produto> cbProdutos;       // produtos filtrados pelo tipo
    private JTextField tfDias;
    private JButton btAdd, btFechar, btRemover;

    private DefaultTableModel modeloTabela;
    private JTable tabela;

    public JItensAluga(BancodeDados bd, Aluga aluguel, JAluga parent) {
        super("Itens do Aluguel: " + aluguel.getId());
        this.bd = bd;
        this.aluguel = aluguel;
        this.parent = parent;
        setSize(700, 420);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Tipo selector
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Tipo:"), gbc);
        cbTipo = new JComboBox<>(new String[] {"Filme", "Livro", "Jogo"});
        cbTipo.addActionListener(e -> atualizarProdutosPorTipo());
        gbc.gridy = 1; add(cbTipo, gbc);

        // Produtos (filtrados)
        gbc.gridy = 2;
        add(new JLabel("Produto:"), gbc);
        cbProdutos = new JComboBox<>();
        cbProdutos.setRenderer(new DefaultListCellRenderer(){
            @Override
            public java.awt.Component getListCellRendererComponent(javax.swing.JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof Produto) {
                    Produto p = (Produto) value;
                    setText(p.getNome() + " (ID:" + p.getId() + ") - R$ " + p.getValor());
                }
                return this;
            }
        });
        gbc.gridy = 3; add(cbProdutos, gbc);

        // Dias
        gbc.gridy = 4;
        add(new JLabel("Dias:"), gbc);
        tfDias = new JTextField("1"); gbc.gridy = 5; add(tfDias, gbc);

        // Botões
        btAdd = new JButton("Adicionar Item"); btAdd.addActionListener(this);
        btRemover = new JButton("Remover Item"); btRemover.addActionListener(this);
        btFechar = new JButton("Fechar"); btFechar.addActionListener(this);

        gbc.gridy = 6; add(btAdd, gbc);
        gbc.gridy = 7; add(btRemover, gbc);
        gbc.gridy = 8; add(btFechar, gbc);

        // Tabela (padrão das outras janelas)
        modeloTabela = new DefaultTableModel(new Object[] { "ID Item", "Produto", "Tipo", "Dias", "Valor" }, 0) {
            @Override public boolean isCellEditable(int row, int column) { return false; }
        };
        tabela = new JTable(modeloTabela);
        tabela.setRowHeight(22);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBorder(BorderFactory.createTitledBorder("Itens do Aluguel"));

        gbc.gridx = 1; gbc.gridy = 0;
        gbc.gridheight = 9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1.0; gbc.weighty = 1.0;
        add(scroll, gbc);

        // inicializa produtos e tabela
        atualizarProdutosPorTipo();
        carregarTabela();
    }

    // popula cbProdutos segundo tipo selecionado
    private void atualizarProdutosPorTipo() {
        String tipo = (String) cbTipo.getSelectedItem();
        DefaultComboBoxModel<Produto> model = new DefaultComboBoxModel<>();
        if (bd == null || bd.getrProduto() == null) {
            cbProdutos.setModel(model);
            return;
        }
        for (Produto p : bd.getrProduto().getEntidades()) {
            if ("Filme".equals(tipo) && p instanceof Filme) model.addElement(p);
            else if ("Livro".equals(tipo) && p instanceof Livro) model.addElement(p);
            else if ("Jogo".equals(tipo) && p instanceof Jogo) model.addElement(p);
        }
        cbProdutos.setModel(model);
        if (model.getSize() > 0) cbProdutos.setSelectedIndex(0);
    }

    private void carregarTabela() {
        modeloTabela.setRowCount(0);
        int idx = 1;
        for (ItensAluga it : aluguel.getItensAluguel()) {
            Produto p = it.getItem();
            String tipo = tipoDoProduto(p);
            modeloTabela.addRow(new Object[] {
                it.getId(),
                p != null ? p.getNome() : "",
                tipo,
                it.getDias(),
                it.getValorTotal()
            });
            idx++;
        }
    }

    private String tipoDoProduto(Produto p) {
        if (p instanceof Filme) return "Filme";
        if (p instanceof Livro) return "Livro";
        if (p instanceof Jogo)  return "Jogo";
        return "Outro";
    }

    @Override
    public void actionPerformed(java.awt.event.ActionEvent e) {
        Object src = e.getSource();
        if (src == btAdd) {
            Produto p = (Produto) cbProdutos.getSelectedItem();
            if (p == null) { JOptionPane.showMessageDialog(this, "Selecione um produto válido."); return; }
            int dias;
            try { dias = Integer.parseInt(tfDias.getText().trim()); if (dias <= 0) throw new NumberFormatException(); }
            catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Dias inválido."); return; }

            int itemId = aluguel.getItensAluguel().size() + 1;
            aluguel.adicionarItem(p, dias, itemId);
            // recalcula e persiste
            aluguel.calcularValorTotal();
            bd.getrAluga().alterar(aluguel);
            carregarTabela();
            if (parent != null) parent.refreshAfterItens(aluguel);
        } else if (src == btRemover) {
            int sel = tabela.getSelectedRow();
            if (sel == -1) { JOptionPane.showMessageDialog(this, "Selecione um item na tabela para remover."); return; }
            int modelRow = tabela.convertRowIndexToModel(sel);
            int idItem = (int) modeloTabela.getValueAt(modelRow, 0);
            // encontra e remove por id
            ItensAluga alvo = null;
            for (ItensAluga it : new ArrayList<>(aluguel.getItensAluguel())) {
                if (it.getId() == idItem) { alvo = it; break; }
            }
            if (alvo != null) {
                aluguel.removerItem(alvo);
                aluguel.calcularValorTotal();
                bd.getrAluga().alterar(aluguel);
                carregarTabela();
                if (parent != null) parent.refreshAfterItens(aluguel);
            }
        } else if (src == btFechar) {
            dispose();
        }
    }
}