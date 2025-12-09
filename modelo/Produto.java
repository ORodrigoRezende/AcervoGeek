package modelo;

public abstract class Produto extends Entidade{
    private String nome;
    private float valor;
    
    public Produto(int id, String nome,float valor){
        super(id);
        this.nome = nome;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public float getValor() {
        return valor;
    }
    public void setValor(float valor) {
        this.valor = valor;
    }
}
