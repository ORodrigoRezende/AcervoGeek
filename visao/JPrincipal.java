package visao;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class JPrincipal extends JFrame implements ActionListener {

  private JTextField tfnm, tfvl;
  private JComboBox<String> cbct;
  private JButton btsv, btal, btrm, btcn;
  private DefaultTableModel modeloTabela;
  private JTable tabela;

  private String[] categorias = new String[] {"JOGOS" };
  

  public JPrincipal() {

    super("Formulário de produtos");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new GridBagLayout());
    var gbc = new GridBagConstraints();
    gbc.fill = GridBagConstraints.HORIZONTAL;

    // linha 0
    gbc.gridx = 0;
    gbc.gridy = 0;
    add(new JLabel("Nome:"), gbc);

    tfnm = new JTextField();
    gbc.gridx = 1;
    gbc.gridwidth = 7;
    add(tfnm, gbc);

    // linha 1
    gbc.gridx = 0;
    gbc.gridy = 1;
    gbc.gridwidth = 1;
    add(new JLabel("Valor:"), gbc);

    tfvl = new JTextField();
    gbc.gridx = 1;
    gbc.gridwidth = 3;
    add(tfvl, gbc);

    gbc.gridx = 4;
    add(new JLabel("Categoria:"), gbc);

    cbct = new JComboBox<String>(categorias);
    cbct.setSelectedIndex(0);

    // ➜ Ao selecionar uma categoria, abre janela correspondente
    cbct.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String cat = cbct.getSelectedItem().toString();
        if (cat.equals("JOGOS")) {
          new JJogo().setVisible(true);
        }
      }
    });

    gbc.gridx = 5;
    gbc.gridwidth = 3;
    add(cbct, gbc);

    // linha 2 – botões
    btsv = new JButton("Salvar");
    btsv.addActionListener(this);
    gbc.gridx = 0;
    gbc.gridy = 2;
    gbc.gridwidth = 2;
    add(btsv, gbc);

    btal = new JButton("Alterar");
    btal.addActionListener(this);
    gbc.gridx = 2;
    add(btal, gbc);

    btrm = new JButton("Apagar");
    btrm.addActionListener(this);
    gbc.gridx = 4;
    add(btrm, gbc);

    btcn = new JButton("Cancelar");
    btcn.addActionListener(this);
    gbc.gridx = 6;
    add(btcn, gbc);

    // linha 3 – tabela
    modeloTabela = new DefaultTableModel();
    modeloTabela.addColumn("Nome");
    modeloTabela.addColumn("Valor");
    modeloTabela.addColumn("Categoria");

    tabela = new JTable(modeloTabela);

    JScrollPane scroll = new JScrollPane(tabela);
    gbc.gridx = 0;
    gbc.gridy = 3;
    gbc.gridwidth = 8;
    gbc.weighty = 1;
    gbc.fill = GridBagConstraints.BOTH;
    gbc.insets = new Insets(5, 0, 5, 0);
    add(scroll, gbc);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (e.getSource() == btsv) {
      modeloTabela.addRow(new Object[] {
          tfnm.getText(),
          tfvl.getText(),
          cbct.getSelectedItem()
      });

    } else if (e.getSource() == btal) {

      int index = tabela.getSelectedRow();
      if (index >= 0) {
        modeloTabela.setValueAt(tfnm.getText(), index, 0);
        modeloTabela.setValueAt(tfvl.getText(), index, 1);
        modeloTabela.setValueAt(cbct.getSelectedItem(), index, 2);
      }

    } else if (e.getSource() == btrm) {

      int index = tabela.getSelectedRow();
      if (index >= 0)
        modeloTabela.removeRow(index);

    } else if (e.getSource() == btcn) {

      tfnm.setText("");
      tfvl.setText("");
      cbct.setSelectedIndex(0);
      tabela.clearSelection();
    }
  }

  public static void main(String[] args) {
    new JPrincipal().setVisible(true);
  }
}
