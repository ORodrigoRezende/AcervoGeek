package modelo;

import java.time.LocalDate;
import java.util.Scanner;
import persistencia.BancodeDados;
import persistencia.Persistente;

public class Aluga extends Entidade {

    private Cliente cliente;
    private LocalDate dataAluguel;
    private LocalDate dataDevolucao;

    public Aluga() {}

    public Aluga(int id, Cliente cliente, LocalDate da, LocalDate dd) {
        super(id);
        this.cliente = cliente;
        this.dataAluguel = da;
        this.dataDevolucao = dd;
    }


    public void cadastrar(BancodeDados bd, Scanner sc) {

        Persistente<Aluga> rAluga = bd.getrAluga();
        Persistente<Cliente> rCliente = bd.getrCliente();

        System.out.print("ID do aluguel: ");
        int idAluguel = sc.nextInt();

        System.out.print("ID do cliente: ");
        int idCliente = sc.nextInt();

        Cliente c = rCliente.buscar(idCliente);
        if (c == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        System.out.print("Data de aluguel (AAAA-MM-DD): ");
        LocalDate da = LocalDate.parse(sc.next());

        System.out.print("Data de devolução (AAAA-MM-DD): ");
        LocalDate dd = LocalDate.parse(sc.next());

        Aluga novo = new Aluga(idAluguel, c, da, dd);

        rAluga.inserir(novo);
        System.out.println("Aluguel cadastrado!");
    }

    public void alterar(BancodeDados bd, Scanner sc) {

        Persistente<Aluga> rAluga = bd.getrAluga();

        System.out.print("ID do aluguel: ");
        int id = sc.nextInt();

        Aluga a = rAluga.buscar(id);

        if (a == null) {
            System.out.println("Aluguel não encontrado!");
            return;
        }

        System.out.print("Nova data de devolução (AAAA-MM-DD): ");
        LocalDate nova = LocalDate.parse(sc.next());

        a.setDataDevolucao(nova);
        rAluga.alterar(a);

        System.out.println("Aluguel atualizado!");
    }


    public void excluir(BancodeDados bd, Scanner sc) {

        Persistente<Aluga> rAluga = bd.getrAluga();

        System.out.print("ID do aluguel: ");
        int id = sc.nextInt();

        if (rAluga.excluir(id)) {
            System.out.println("Aluguel excluído!");
        } else {
            System.out.println("ID não encontrado!");
        }
    }

    public void listar(BancodeDados bd) {
        System.out.println("\n===== LISTA DE ALUGUÉIS =====");
        System.out.println(bd.getrAluga().toString());
    }

    public Cliente getCliente() { return cliente; }

    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public LocalDate getDataAluguel() { return dataAluguel; }

    public void setDataAluguel(LocalDate dataAluguel) { this.dataAluguel = dataAluguel; }

    public LocalDate getDataDevolucao() { return dataDevolucao; }

    public void setDataDevolucao(LocalDate dataDevolucao) { this.dataDevolucao = dataDevolucao; }

}
