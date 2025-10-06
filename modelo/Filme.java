package modelo;

public class Filme extends Entidade{
    private String nome;
    private String genero;
    private float valor;
    private String diretor;

    public Filme() {
        super();
        this.diretor="";
        this.genero="";
        this.nome="";
        this.valor=0f;
    }

     public Filme(String diretor, String genero, String nome, float valor, int id){
        super(id);
        this.diretor = diretor;
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
        return diretor;
    }

    public void setDiretor(String desenvolvedor) {
        this.diretor = desenvolvedor;
    }
}
