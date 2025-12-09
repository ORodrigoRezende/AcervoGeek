package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluga extends Entidade {

    private Cliente cliente;
    private LocalDate dataAluguel;
    private List<ItensAluga> itensAluguel;
    private double valorTotal;

    public Aluga(int id, Cliente cliente){
        super(id);
        this.cliente = cliente;
        this.dataAluguel = LocalDate.now();
        this.itensAluguel = new ArrayList<>();
        this.valorTotal = 0;
    }

    public void adicionarItem(Produto produto,int dias,int id){
        ItensAluga item = new ItensAluga(id,produto,dias,dataAluguel);
        this.itensAluguel.add(item);
        calcularValorTotal();
    }

    public void removerItem(ItensAluga item){
        this.itensAluguel.remove(item);
        calcularValorTotal();
    }

    public void calcularValorTotal(){
        double total = 0;
        for(ItensAluga item : itensAluguel){
            total += item.getValorTotal();
        }
        this.valorTotal = total;
    }

    public double getValorTotal() {
        return valorTotal;
    }
    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
    public Cliente getCliente() {
        return cliente;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public LocalDate getDataAluguel() {
        return dataAluguel;
    }
    public void setDataAluguel(LocalDate dataAluguel) {
        this.dataAluguel = dataAluguel;
    }
    public List<ItensAluga> getItensAluguel() {
        return itensAluguel;
    }
    
    // retorna o próximo id válido para um item (maior id atual + 1)
    public int gerarProximoItemId() {
        int max = 0;
        if (itensAluguel != null) {
            for (ItensAluga it : itensAluguel) {
                if (it != null && it.getId() > max) max = it.getId();
            }
        }
        return max + 1;
    }

}
