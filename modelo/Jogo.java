package modelo;

public class Jogo extends Produto{
    private String genero;
    private String desenvolvedor;

    public Jogo(String desenvolvedor, String genero, String nome, float valor, int id) {
        super(id,nome,valor);
        this.desenvolvedor = desenvolvedor;
        this.genero = genero;
    }
    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    // @Override
    // public String toString() {
    //     return super.toString() + "\n Nome:" + nome + "\n Genero: " + genero + "\n Valor: " + valor + "\n Desenvolvedor: " + desenvolvedor;
    // }
}
