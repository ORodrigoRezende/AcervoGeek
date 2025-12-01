package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JogoForm extends JFrame implements ActionListener {

    private JTextField tfnome, tfgenero, tfvalor, tfdesenv;
    private JButton btsalvar, btalterar, btremover, btcancelar;
    private JTable tabela;
    private DefaultTableModel modeloTabela;

    public JogoForm() {

        super("Cadastro de Jogos");
        setSize(700, 500);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        var gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // linha 0 – Nome
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);

        tfnome = new JTextField();
        gbc.gridx = 1; gbc.gridwidth = 3;
        add(tfnome, gbc);

        // linha 1 – Genero
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 1;
        add(new JLabel("Gênero:"), gbc);

        tfgenero = new JTextField();
        gbc.gridx = 1; gbc.gridwidth = 3;
        add(tfgenero, gbc);

        // linha 2 – Valor
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1;
        add(new JLabel("Valor:"), gbc);

        tfvalor = new JTextField();
        gbc.gridx = 1;
        add(tfvalor, gbc);

        // linha 2 – Desenvolvedor
        gbc.gridx = 2;
        add(new JLabel("Desenvolvedor:"), gbc);

        tfdesenv = new JTextField();
        gbc.gridx = 3;
        add(tfdesenv, gbc);

        // linha 3 – Botões
        btsalvar = new JButton("Salvar");
        btsalvar.addActionListener(this);
        gbc.gridx = 0; gbc.gridy = 3;
        add(btsalvar, gbc);

        btalterar = new JButton("Alterar");
        btalterar.addActionListener(this);
        gbc.gridx = 1;
        add(btalterar, gbc);

        btremover = new JButton("Remover");
        btremover.addActionListener(this);
        gbc.gridx = 2;
        add(btremover, gbc);

        btcancelar = new JButton("Cancelar");
        btcancelar.addActionListener(this);
        gbc.gridx = 3;
        add(btcancelar, gbc);

        // linha 4 – Tabela
        modeloTabela = new DefaultTableModel();
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Gênero");
        modeloTabela.addColumn("Valor");
        modeloTabela.addColumn("Desenvolvedor");

        tabela = new JTable(modeloTabela);

        JScrollPane scroll = new JScrollPane(tabela);
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 4;
        gbc.weighty = 1; gbc.fill = GridBagConstraints.BOTH;
        add(scroll, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btsalvar) {

            modeloTabela.addRow(new Object[]{
                tfnome.getText(),
                tfgenero.getText(),
                tfvalor.getText(),
                tfdesenv.getText()
            });

        } else if (e.getSource() == btalterar) {

            int linha = tabela.getSelectedRow();
            if (linha >= 0) {
                modeloTabela.setValueAt(tfnome.getText(), linha, 0);
                modeloTabela.setValueAt(tfgenero.getText(), linha, 1);
                modeloTabela.setValueAt(tfvalor.getText(), linha, 2);
                modeloTabela.setValueAt(tfdesenv.getText(), linha, 3);
            }

        } else if (e.getSource() == btremover) {

            int linha = tabela.getSelectedRow();
            if (linha >= 0)
                modeloTabela.removeRow(linha);

        } else if (e.getSource() == btcancelar) {

            tfnome.setText("");
            tfgenero.setText("");
            tfvalor.setText("");
            tfdesenv.setText("");
            tabela.clearSelection();
        }
    }

    public static void main(String[] args) {
        new JogoForm().setVisible(true);
    }
}
