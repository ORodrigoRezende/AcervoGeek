package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Janela extends JFrame {

  public Janela() {
    super("Minha janela");
    setSize(800, 600);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setLayout(new BorderLayout());

    JButton bt1 = new JButton("Clique aqui");
    add(bt1, BorderLayout.NORTH);

    JPanel painel = new JPanel(null);
    add(painel, BorderLayout.CENTER);

    JLabel lb1 = new JLabel("Nome:");
    // lb1.setBounds(10, 10, 50, 20); //se o layout for null
    JTextField tf1 = new JTextField("djshfs", 60);
    // tf1.setBounds(70, 10, 100, 20); //se o layout for null
    painel.add(lb1);
    painel.add(tf1);

    JLabel lb2 = new JLabel("Descrição:");
    JTextArea ta1 = new JTextArea(5, 30);
    ta1.setFont(new Font("Arial", Font.BOLD, 20));
    painel.add(lb2);
    painel.add(ta1);

    JCheckBox ck1 = new JCheckBox("Check?");
    painel.add(ck1);

    ButtonGroup bg1 = new ButtonGroup();
    JRadioButton rb1 = new JRadioButton("Ligado", false);
    JRadioButton rb2 = new JRadioButton("Desligado", true);
    bg1.add(rb1);
    bg1.add(rb2);
    JPanel radioPanel = new JPanel(new FlowLayout());
    radioPanel.setBorder(BorderFactory.createTitledBorder("Status"));
    radioPanel.add(rb1);
    radioPanel.add(rb2);
    painel.add(radioPanel);

    JComboBox<String> cb1 = new JComboBox<String>(new String[] { "OPCAO 1", "OPCAO 2" });
    painel.add(cb1);

    JButton bt2 = new JButton("Clique aqui");
    bt2.setPreferredSize(new Dimension(200, 100));
    painel.add(bt2);

  }

  public static void main(String[] args) {
    Janela j = new Janela();
    j.setVisible(true);
  }

}
