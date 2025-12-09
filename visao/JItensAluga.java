package visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import modelo.Aluga;
import modelo.ItensAluga;
import modelo.Produto;
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;

public class JItensAluga extends JFrame implements ActionListener {

    private BancodeDados bd;
    private Aluga aluguel;
    private JTextField tfProdutoId, tfDias;
    private JButton btAdd, btFechar;
    private DefaultListModel<String> listModel;
    private JList<String> listItens;

    public JItensAluga(BancodeDados bd, Aluga aluguel) {
        super("Itens do Aluguel: " + aluguel.getId());
        this.bd = bd;
        this.aluguel = aluguel;
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints(); gbc.insets = new Insets(6,6,6,6);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx=0; gbc.gridy=0;
        add(new JLabel("ID Produto:"), gbc);
        tfProdutoId = new JTextField(); gbc.gridy=1; add(tfProdutoId, gbc);

        gbc.gridy=2;
        add(new JLabel("Dias:"), gbc);
        tfDias = new JTextField("1"); gbc.gridy=3; add(tfDias, gbc);

        btAdd = new JButton("Adicionar Item"); btAdd.addActionListener(this);
        btFechar = new JButton("Fechar"); btFechar.addActionListener(this);

        gbc.gridy=4; add(btAdd, gbc);
        gbc.gridy=5; add(btFechar, gbc);

        listModel = new DefaultListModel<>();
        listItens = new JList<>(listModel);
        JScrollPane scroll = new JScrollPane(listItens);
        gbc.gridx=1; gbc.gridy=0; gbc.gridheight=6; gbc.fill = GridBagConstraints.BOTH; gbc.weightx = 1.0; gbc.weighty = 1.0;
        add(scroll, gbc);

        carregarLista();
    }

    private void carregarLista() {
        listModel.clear();
        int i=1;
        for (ItensAluga it : aluguel.getItensAluguel()) {
            listModel.addElement(i++ + " - " + it.getItem().getNome() + " | dias: " + it.getDias() + " | valor: " + it.getItem().getValor()*it.getDias());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btAdd) {
            int prodId;
            int dias;
            try { prodId = Integer.parseInt(tfProdutoId.getText().trim()); }
            catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "ID do produto inválido."); return; }
            try { dias = Integer.parseInt(tfDias.getText().trim()); }
            catch (NumberFormatException ex) { JOptionPane.showMessageDialog(this, "Dias inválido."); return; }

            try {
                Produto p = bd.getrProduto().buscar(prodId);
                // gera id simples para item como tamanho+1
                int itemId = aluguel.getItensAluguel().size() + 1;
                aluguel.adicionarItem(p, dias, itemId);
                // atualizar persistência do aluguel
                bd.getrAluga().alterar(aluguel);
                carregarLista();
                JOptionPane.showMessageDialog(this, "Item adicionado.");
            } catch (IDNaoExistenteExeception ex) {
                JOptionPane.showMessageDialog(this, "Produto não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btFechar) {
            // atualiza valor total exibido no Aluga (se necessário)
            dispose();
        }
    }
}