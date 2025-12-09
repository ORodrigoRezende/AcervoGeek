import persistencia.BancodeDados;
import visao.JPrincipal;

public class Programa {
    public static void main(String[] args) {
        BancodeDados bd = new BancodeDados();
        javax.swing.SwingUtilities.invokeLater(() -> new JPrincipal(bd).setVisible(true));
    }
}