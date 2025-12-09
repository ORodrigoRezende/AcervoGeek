// package visao;

// import java.awt.*;
// import java.time.LocalDate;
// import javax.swing.*;
// import modelo.Aluga;
// import modelo.Cliente;
// import persistencia.BancodeDados;

// public class JAluga extends JFrame {

//     private JTextField tIdAluga, tIdCliente, tDataAluguel, tDataDevolucao;
//     private JTextArea tLista;

//     private BancodeDados bd;

//     public JAluga(BancodeDados bd) {
//         this.bd = bd;
//         setTitle("Gerenciar Aluguéis");
//         setSize(500, 450);
//         setLayout(new BorderLayout());
//         setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

//         JPanel panel = new JPanel(new GridLayout(6, 2));

//         panel.add(new JLabel("ID do Aluguel:"));
//         tIdAluga = new JTextField();
//         panel.add(tIdAluga);

//         panel.add(new JLabel("ID do Cliente:"));
//         tIdCliente = new JTextField();
//         panel.add(tIdCliente);

//         panel.add(new JLabel("Data Aluguel (AAAA-MM-DD):"));
//         tDataAluguel = new JTextField();
//         panel.add(tDataAluguel);

//         panel.add(new JLabel("Data Devolução (AAAA-MM-DD):"));
//         tDataDevolucao = new JTextField();
//         panel.add(tDataDevolucao);

//         JButton bCadastrar = new JButton("Cadastrar");
//         bCadastrar.addActionListener(e -> cadastrar());
//         panel.add(bCadastrar);

//         JButton bAlterar = new JButton("Alterar");
//         bAlterar.addActionListener(e -> alterar());
//         panel.add(bAlterar);

//         JButton bExcluir = new JButton("Excluir");
//         bExcluir.addActionListener(e -> excluir());
//         panel.add(bExcluir);

//         JButton bListar = new JButton("Listar");
//         bListar.addActionListener(e -> listar());
//         panel.add(bListar);

//         add(panel, BorderLayout.NORTH);

//         tLista = new JTextArea();
//         add(new JScrollPane(tLista), BorderLayout.CENTER);

//         setVisible(true);
//     }

//     private void cadastrar() {
//         try {
//             int idA = Integer.parseInt(tIdAluga.getText());
//             int idC = Integer.parseInt(tIdCliente.getText());

//             Cliente c = bd.getrCliente().buscar(idC);

//             LocalDate da = LocalDate.parse(tDataAluguel.getText());
//             LocalDate dd = LocalDate.parse(tDataDevolucao.getText());

//             Aluga novo = new Aluga(idA, c, da, dd);
//             bd.getrAluga().inserir(novo);

//             JOptionPane.showMessageDialog(this, "Aluguel cadastrado!");

//         } catch (Exception e) {
//             JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
//         }
//     }

//     private void alterar() {
//         try {
//             int id = Integer.parseInt(tIdAluga.getText());
//             Aluga a = bd.getrAluga().buscar(id);

//             a.setDataDevolucao(LocalDate.parse(tDataDevolucao.getText()));
//             bd.getrAluga().alterar(a);

//             JOptionPane.showMessageDialog(this, "Atualizado!");

//         } catch (Exception e) {
//             JOptionPane.showMessageDialog(this, "Erro: " + e.getMessage());
//         }
//     }

//     private void excluir() {
//         int id = Integer.parseInt(tIdAluga.getText());

//         if (bd.getrAluga().excluir(id)) {
//             JOptionPane.showMessageDialog(this, "Excluído!");
//         } else {
//             JOptionPane.showMessageDialog(this, "ID não encontrado.");
//         }
//     }

//     private void listar() {
//         tLista.setText(bd.getrAluga().toString());
//     }
// }
