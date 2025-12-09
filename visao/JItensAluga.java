package visao;

import modelo.ItensAluga;
import modelo.Entidade;
import modelo.Filme;
import modelo.Livro;
import modelo.Jogo;
import persistencia.BancodeDados;
import persistencia.IDNaoExistenteExeception;
import javax.swing.*;
import java.awt.*;

public class JItensAluga extends JFrame {

    private JTextField tId, tIdItem, tDias, tValor;
    private JComboBox<String> comboTipo;
    private JTextArea tLista;

    private BancodeDados bd;

    public JItensAluga(BancodeDados bd) {
        this.bd = bd;

        setTitle("Gerenciar Itens de Aluguel");
        setSize(500, 450);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(6, 2));

        panel.add(new JLabel("ID do ItemAluga:"));
        tId = new JTextField();
        panel.add(tId);

        panel.add(new JLabel("Tipo do Item:"));
        comboTipo = new JComboBox<>(new String[]{"Filme", "Livro", "Jogo"});
        panel.add(comboTipo);

        panel.add(new JLabel("ID do Item:"));
        tIdItem = new JTextField();
        panel.add(tIdItem);

        panel.add(new JLabel("Dias:"));
        tDias = new JTextField();
        panel.add(tDias);

        panel.add(new JLabel("Valor R$:"));
        tValor = new JTextField();
        panel.add(tValor);

        JButton bCadastrar = new JButton("Cadastrar");
        bCadastrar.addActionListener(e -> cadastrar());
        panel.add(bCadastrar);

        JButton bExcluir = new JButton("Excluir");
        bExcluir.addActionListener(e -> excluir());
        panel.add(bExcluir);

        JButton bListar = new JButton("Listar");
        bListar.addActionListener(e -> listar());
        panel.add(bListar);

        add(panel, BorderLayout.NORTH);

        tLista = new JTextArea();
        add(new JScrollPane(tLista), BorderLayout.CENTER);

        setVisible(true);
    }

    private void cadastrar() {
        try {
            int id = Integer.parseInt(tId.getText());
            int idItem = Integer.parseInt(tIdItem.getText());
            int dias = Integer.parseInt(tDias.getText());
            double valor = Double.parseDouble(tValor.getText());

            Entidade item = null;
            String tipo = comboTipo.getSelectedItem().toString();

            switch (tipo) {
                case "Filme":
                    item = bd.getrFilme().buscar(idItem);
                    break;

                case "Livro":
                    item = bd.getrLivro().buscar(idItem);
                    break;

                case "Jogo":
                    item = bd.getrJogo().buscar(idItem);
                    break;
            }

            ItensAluga ia = new ItensAluga(id, item, dias, valor);

            bd.getrItensAluga().inserir(ia);

            JOptionPane.showMessageDialog(this, "Item cadastrado!");

        } catch (IDNaoExistenteExeception e) {
            JOptionPane.showMessageDialog(this, "ID do item não existe.");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
        }
    }

    private void excluir() {
        int id = Integer.parseInt(tId.getText());

        if (bd.getrAluga().excluir(id)) {
            JOptionPane.showMessageDialog(this, "Excluído!");
        } else {
            JOptionPane.showMessageDialog(this, "ID não encontrado.");
        }
    }

    private void listar() {
        tLista.setText(bd.getrAluga().toString());
    }
}