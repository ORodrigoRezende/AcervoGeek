package persistencia;

import modelo.Aluga;
import modelo.Cliente;
import modelo.Filme;
import modelo.Jogo;
import modelo.Livro;

public class BancodeDados {
    private Persistente<Cliente> rCliente;
    private Persistente<Jogo> rJogo;
    private Persistente<Livro> rLivro;
    private Persistente<Filme> rFilme;
    private Persistente<Aluga> rAluga;

    public BancodeDados() {
        rCliente = new Persistente<Cliente>();
        rFilme = new Persistente<Filme>();
        rJogo = new Persistente<Jogo>();
        rLivro = new Persistente<Livro>();
        rAluga = new Persistente<Aluga>();
    }

    public Persistente<Cliente> getrCliente() {
        return rCliente;
    }

    public Persistente<Jogo> getrJogo() {
        return rJogo;
    }

    public Persistente<Livro> getrLivro() {
        return rLivro;
    }

    public Persistente<Filme> getrFilme() {
        return rFilme;
    }

    public Persistente<Aluga> getrAluga() {
        return rAluga;
    }
}
