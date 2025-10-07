package modelo;

public class ItensAluga extends Entidade {
    private Entidade item;   
    private int dias;  //Duração do aluguel     
    private double valor;

    public ItensAluga(int id, Entidade item, int dias, double valor) {
        super(id);
        this.item = item;
        this.dias = dias;
        this.valor = valor;
    }

    public Entidade getItem() {
        return item;
    }

    public void setItem(Entidade item) {
        this.item = item;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Item alugado: " + item.toString() + 
               " | Dias: " + dias + 
               " | Valor: R$ " + valor + "\n";
    }
}