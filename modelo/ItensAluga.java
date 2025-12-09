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
        recalcularValor();
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
        if (this.dataDevolucao == null) {
            this.dataDevolucao = LocalDate.now().plusDays(dias);
        } else {
            this.dataDevolucao = this.dataDevolucao.minusDays(this.dias).plusDays(dias);
        }
        recalcularValor();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    private void recalcularValor() {
        if (item != null) {
            this.valorTotal = item.getValor() * this.dias;
        } else {
            this.valorTotal = 0;
        }
    }

    // @Override
    // public String toString() {
    //     return "\nItem alugado: " + item.toString() + 
    //            "\n | Dias: " + dias + 
    //            " | Valor: R$ " + valor + " |\n";
    // }
}