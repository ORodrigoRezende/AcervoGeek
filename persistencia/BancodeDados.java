package persistencia;

public class BancodeDados {
    private Persistente rCliente;
    private Persistente rJogo;
    private Persistente rLivro;
    private Persistente rFilme;
    private Persistente rAluga;

    public BancodeDados() {
        rCliente = new Persistente();
        rFilme = new Persistente();
        rJogo = new Persistente();
        rLivro = new Persistente();
        rAluga = new Persistente();
    }

    public Persistente getrCliente() {
        return rCliente;
    }

    public Persistente getrJogo() {
        return rJogo;
    }

    public Persistente getrLivro() {
        return rLivro;
    }

    public Persistente getrFilme() {
        return rFilme;
    }

    public Persistente getrAluga() {
        return rAluga;
    }

    
}
