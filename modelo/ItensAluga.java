package modelo;
import java.time.LocalDate;

public class ItensAluga extends Entidade {
    private Produto item;
    private LocalDate dataDevolucao;   
    private int dias;     
    private double valorTotal;

    public ItensAluga(int id, Produto item, int dias,LocalDate dataInicio) {
        super(id);
        this.item = item;
        this.dias = dias;
        this.dataDevolucao = dataInicio.plusDays(dias);
        this.valorTotal = item.getValor() * dias;
    }

    public Produto getItem() {
        return item;
    }

    public void setItem(Produto item) {
        this.item = item;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getDias() {
        return dias;
    }

    public void setDias(int dias) {
        this.dias = dias;
    }

    // @Override
    // public String toString() {
    //     return "\nItem alugado: " + item.toString() + 
    //            "\n | Dias: " + dias + 
    //            " | Valor: R$ " + valor + " |\n";
    // }
}