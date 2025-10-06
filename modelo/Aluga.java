package modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Aluga extends Entidade {
    private Cliente cliente;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;
    private List<ItensAluga> itens;

    public Aluga(int id, Cliente cliente, LocalDate dataAluguel, LocalDate dataDevolucao) {
        super(id);
        this.cliente = cliente;
        this.dataAluguel = dataAluguel;
        this.dataDevolucao = dataDevolucao;
        this.itens = new ArrayList<>();
    }

    public void adicionarItem(ItensAluga item) {
        itens.add(item);
    }

    public void removerItem(ItensAluga item) {
        itens.remove(item);
    }

    public double calcularTotal() {
        double total = 0;
        for (ItensAluga item : itens) {
            total += item.getValor();
        }
        return total;
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

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public List<ItensAluga> getItens() {
        return itens;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aluguel [Cliente: ").append(cliente.toString())
          .append(" | Data aluguel: ").append(dataAluguel)
          .append(" | Data devolução: ").append(dataDevolucao)
          .append(" | Itens: \n");

        for (ItensAluga item : itens) {
            sb.append("   ").append(item.toString()).append("\n");
        }

        sb.append("Valor total: R$ ").append(calcularTotal());
        return sb.toString();
    }
}