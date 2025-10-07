package modelo;

public class Jogo extends Entidade{
    private String nome;
    private String genero;
    private float valor;
    private String desenvolvedor;

    public Jogo() {
        super();
        this.desenvolvedor = "";
        this.genero = "";
        this.nome = "";
        this.valor = 0f;
    }

    public Jogo(String desenvolvedor, String genero, String nome, float valor, int id) {
        super(id);
        this.desenvolvedor = desenvolvedor;
        this.genero = genero;
        this.nome = nome;
        this.valor = valor;
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

    public String getDesenvolvedor() {
        return desenvolvedor;
    }

    public void setDesenvolvedor(String desenvolvedor) {
        this.desenvolvedor = desenvolvedor;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Nome:" + nome + "\n Genero: " + genero + "\n Valor: " + valor + "\n Desenvolvedor: " + desenvolvedor;
    }
}
