package visao;

import java.awt.*;
import javax.swing.*;
import persistencia.BancodeDados;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import com.formdev.flatlaf.FlatLightLaf;

public class JPrincipal extends JFrame {

    private JComboBox<String> cbCategorias;
    private JButton btAbrir;
    private BancodeDados bd;

    // coloque as opções que vão ser tratadas no switch (mesmo texto)
    private String[] categorias = { "Selecione...", "JOGOS", "CLIENTES" };

    public JPrincipal(BancodeDados bd) {
        super("Menu Principal");
        this.bd = bd;
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
            case "CLIENTES":
                JCliente jc = new JCliente(this.bd);
                jc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jc.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) { /* opcional: atualizar UI da JPrincipal */ }
                });
                jc.setVisible(true);
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

    private void abrirCliente() {
        JCliente jc = new JCliente(this.bd);
        jc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jc.setVisible(true);
    }
}
