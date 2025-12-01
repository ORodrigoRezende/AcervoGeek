package visao;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.formdev.flatlaf.FlatLightLaf;

public class JPrincipal extends JFrame {

    private JComboBox<String> cbCategorias;
    private JButton btAbrir;

    private String[] categorias = { "Selecione..", "JOGOS" };

    public JPrincipal() {
        super("AcervoGeek");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titulo = new JLabel("Selecione uma categoria:");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        // ComboBox
        cbCategorias = new JComboBox<>(categorias);
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        add(cbCategorias, gbc);

        // Botão Abrir
        btAbrir = new JButton("Abrir");
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(btAbrir, gbc);

        // Ação do botão
        btAbrir.addActionListener(e -> abrirCategoria());
    }

    private void abrirCategoria() {
        String cat = cbCategorias.getSelectedItem().toString();

        switch (cat) {
            case "JOGOS":
                new JJogo().setVisible(true);
                break;
                
            case "Selecione...":
            default:
                JOptionPane.showMessageDialog(this,
                        "Escolha uma categoria válida.",
                        "Atenção",
                        JOptionPane.WARNING_MESSAGE);
                break;
        }
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        new JPrincipal().setVisible(true);
    }
}
