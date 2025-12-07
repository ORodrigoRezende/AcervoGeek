package persistencia;

import modelo.Aluga;
import modelo.Cliente;
import modelo.Filme;
import modelo.ItensAluga;
import modelo.Jogo;
import modelo.Livro;

public class BancodeDados {

    private Persistente<Cliente> rCliente;
    private Persistente<Jogo> rJogo;
    private Persistente<Livro> rLivro;
    private Persistente<Filme> rFilme;
    private Persistente<Aluga> rAluga;
    private Persistente<ItensAluga> rItensAluga; 

    public BancodeDados() {
        rCliente = new Persistente<>();
        rFilme = new Persistente<>();
        rJogo = new Persistente<>();
        rLivro = new Persistente<>();
        rAluga = new Persistente<>();
        rItensAluga = new Persistente<>();      
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

    public Persistente<ItensAluga> getrItensAluga() {  
        return rItensAluga;
    }
}
