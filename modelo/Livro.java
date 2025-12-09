package modelo;

public class Livro extends Produto{
    private String genero;
    private String autor;

    public Livro(String nome, String genero, float valor, String autor, int id) {
        super(id,nome,valor);
        this.genero = genero;
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    // @Override
    // public String toString() {
    //     return super.toString() + "\n Nome: " + nome + "\n Genero: " + genero + "\n Valor=" + valor + "\n Autor: " + autor + "\n";
    // }
}
