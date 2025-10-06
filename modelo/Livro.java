package modelo;

public class Livro extends Entidade{
    private String nome;
    private String genero;
    private float valor;
    private String autor;

    public Livro() {
        super();
        this.nome = "";
        this.genero = "";
        this.valor = 0f;
        this.autor = "";
    }

    public Livro(String nome, String genero, float valor, String autor, int id) {
        super(id);
        this.nome = nome;
        this.genero = genero;
        this.valor = valor;
        this.autor = autor;
    }

        public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
}
