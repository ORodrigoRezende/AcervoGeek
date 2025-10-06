package persistencia;

public class BancodeDados {
    private Persistente rCliente;
    private Persistente rJogo;
    private Persistente rLivro;
    private Persistente rFilme;

    public BancodeDados() {
        rCliente = new Persistente();
        rFilme = new Persistente();
        rJogo = new Persistente();
        rLivro = new Persistente();
    }

    public Persistente getrCliente() {
        return rCliente;
    }

    public void setrCliente(Persistente rCliente) {
        this.rCliente = rCliente;
    }

    public Persistente getrJogo() {
        return rJogo;
    }

    public void setrJogo(Persistente rJogo) {
        this.rJogo = rJogo;
    }

    public Persistente getrLivro() {
        return rLivro;
    }

    public void setrLivro(Persistente rLivro) {
        this.rLivro = rLivro;
    }

    public Persistente getrFilme() {
        return rFilme;
    }

    public void setrFilme(Persistente rFilme) {
        this.rFilme = rFilme;
    }
}
