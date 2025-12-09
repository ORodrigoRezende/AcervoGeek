package modelo;

public class Filme extends Produto{
    private String genero;
    private String diretor;

     public Filme(int id, String diretor, String genero, String nome, float valor){
        super(id, nome,valor);
        this.diretor = diretor;
        this.genero = genero;
    }
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String desenvolvedor) {
        this.diretor = desenvolvedor;
    }

    // @Override
    // public String toString() {
    //     return super.toString()+ "\n Nome: " + nome + "\n Genero: " + genero + "\n Valor: " + valor + "\n Diretor: " + diretor + "\n";
    // }
}
