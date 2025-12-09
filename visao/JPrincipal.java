package visao;

import java.awt.*;
import javax.swing.*;
import persistencia.BancodeDados;

public class JPrincipal extends JFrame {

    private JComboBox<String> cbCategorias;
    private JButton btAbrir;
    private BancodeDados bd;
    private String[] categorias = { "Selecione..", "ALUGUEL", "CLIENTE", "FILMES", "JOGOS", "LIVROS" };

    public JPrincipal(BancodeDados bd) {
        super("AcervoGeek");

        this.bd = bd;

        // Tamanho e configurações
        setSize(1280, 720);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setLocationRelativeTo(null);

        // Estilo manual
        getContentPane().setBackground(new Color(240, 240, 240));

        var gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título
        JLabel titulo = new JLabel("Selecione uma categoria:");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titulo.setHorizontalAlignment(SwingConstants.CENTER);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titulo, gbc);

        // ComboBox
        cbCategorias = new JComboBox<>(categorias);
        cbCategorias.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbCategorias.setSelectedIndex(0); // garante que "Selecione.." seja exibido primeiro

        // Renderer para deixar "Selecione.." cinza
        cbCategorias.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if ("Selecione..".equals(value)) {
                    setForeground(Color.GRAY);
                } else {
                    setForeground(Color.BLACK);
                }
                return c;
            }
        });

        gbc.gridy = 1;
        add(cbCategorias, gbc);

        // Botão Abrir (CENTRALIZADO)
        btAbrir = new JButton("Abrir");
        btAbrir.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btAbrir.setBackground(new Color(100, 150, 255));
        btAbrir.setForeground(Color.WHITE);
        btAbrir.setFocusPainted(false);

        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        add(btAbrir, gbc);

        // Ação do botão
        btAbrir.addActionListener(e -> abrirCategoria());
    }

    private void abrirCategoria() {
        String cat = cbCategorias.getSelectedItem().toString();

        if ("Selecione..".equals(cat)) {
            JOptionPane.showMessageDialog(
                    this,
                    "Escolha uma categoria válida.",
                    "Atenção",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        switch (cat) {
             case "ALUGUEL":
                JAluga ja = new JAluga(this.bd);
                ja.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ja.setVisible(true);

                break;
            case "CLIENTE":
                JCliente jc = new JCliente(this.bd);
                jc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jc.setVisible(true);

                break;
              case "JOGOS":
                JJogo jj = new JJogo(this.bd);
                jj.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jj.setVisible(true);

                break;
              case "FILMES":
                JFilme jf = new JFilme(this.bd);
                jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jf.setVisible(true);
                break;
                
            case "LIVROS":
                JLivro jl = new JLivro(this.bd);
                jl.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jl.setVisible(true);

                break;
            // /*default:
            //     JOptionPane.showMessageDialog(
            //             this,
            //             "Escolha uma categoria válida.",
            //             "Atenção",
            //             JOptionPane.WARNING_MESSAGE
            //     );*/
        }
    }

}
