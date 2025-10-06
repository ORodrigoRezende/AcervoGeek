package persistencia;

import java.util.ArrayList;
import modelo.Cliente;
import modelo.Jogo;


public class BancodeDados {
    private ArrayList<Cliente> rCliente;
    private ArrayList<Jogo> rJogo;
    private ArrayList<Livro> rLivro;
    private ArrayList<Filme> rFilme;
     
    public BancodeDados(){
        rCliente = new ArrayList<>();
        rJogo = new ArrayList<>();
        rLivro = new ArrayList<>();
        rFilme =  new ArrayList<>();
    }

    public ArrayList<Cliente> getrCliente() {
        return rCliente;
    }

    public void setrCliente(ArrayList<Cliente> rCliente) {
        this.rCliente = rCliente;
    }

    public ArrayList<Jogo> getrJogo() {
        return rJogo;
    }

    public void setrJogo(ArrayList<Jogo> rJogo) {
        this.rJogo = rJogo;
    }

    public ArrayList<Livro> getrLivro() {
        return rLivro;
    }

    public void setrLivro(ArrayList<Livro> rLivro) {
        this.rLivro = rLivro;
    }

    public ArrayList<Filme> getrFilme() {
        return rFilme;
    }

    public void setrFilme(ArrayList<Filme> rFilme) {
        this.rFilme = rFilme;
    }
    

}
