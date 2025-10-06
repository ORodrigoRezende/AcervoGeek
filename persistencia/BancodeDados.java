package persistencia;

import java.util.ArrayList;
import modelo.Cliente;

public class BancodeDados {
    private ArrayList<Cliente> rCliente;

    public ArrayList<Cliente> getrCliente() {
        return rCliente;
    }

    public void setrCliente(ArrayList<Cliente> rCliente) {
        this.rCliente = rCliente;
    }
    

}
