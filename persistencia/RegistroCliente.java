package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Cliente;

//Vai ser excluido 
public class RegistroCliente {
    private ArrayList<Cliente> rCliente;

    public RegistroCliente() {
        rCliente = new ArrayList<>();
    }

    public void addCliente(){
        Scanner scn = new Scanner(System.in); //Coloquei so para teste pode ter so um
        boolean idT=true;
        int id;
        do { 
            System.out.println("Digite o id do cliente: ");
            id = scn.nextInt();
            if(getCliente(id)!=null){
                System.out.println("Id do cliente já existe!, Tente novamente");
            }else idT = false;
        } while (idT); // Verificar se tem outro cliente com mesmo id;
        System.out.println("Digite o nome do cliente: ");
        String nome = scn.next();
        System.out.println("Digite o telefone do cliente: ");
        String telefone = scn.next();
        System.out.println("Digite o cpf do cliente: ");
        String cpf = scn.next();
        System.out.println("Digite o endereço do cliente: ");
        String endereco = scn.next();
        rCliente.add(new Cliente(id,nome,telefone,cpf,endereco));

        //scn.close(); //teste
    }

    public void alteraCliente(){
        Scanner scn = new Scanner(System.in);
        boolean idT=true;
        Cliente c;
        int id;
        do { 
            System.out.println("Digite o id do cliente que deseja alterar: ");
            id = scn.nextInt();
            scn.nextLine();
            c = getCliente(id);
            if(c==null){
                System.out.println("Id do cliente não existe!, Tente novamente");
            }else idT = false;
        } while (idT); // Verifica se tem outro cliente com mesmo id;

        System.out.println("Se não deseja alterar so da Enter ;)\n");

        System.out.printf("Digite o nome do cliente: %s\n",c.getNome_do_cliente());
        String nome = scn.nextLine();
        if(!nome.isEmpty()) c.setNome_do_cliente(nome);

        System.out.printf("Digite o telefone do cliente: %s\n",c.getTelefone_do_cliente());
        String telefone = scn.nextLine();
        if(!telefone.isEmpty()) c.setTelefone_do_cliente(telefone);

        System.out.printf("Digite o cpf do cliente: %s\n",c.getCpf());
        String cpf = scn.nextLine();
        if(!cpf.isEmpty())c.setCpf(cpf);

        System.out.printf("Digite o endereço do cliente: %s\n",c.getEndereco());
        String endereco = scn.nextLine();
        if(!endereco.isEmpty())c.setEndereco(endereco);
    }

    public void listaClientes(){
        for(Cliente c: rCliente){
            System.out.printf("%s\n",c.toString());
        }
    }

    public Cliente getCliente(int id){
        for(Cliente cliente: rCliente){
            if(cliente.getId_cliente() == id) return cliente;
        }
        return null;
    }

    public void removeCliente(int id){
        if(getCliente(id)!=null)rCliente.remove(getCliente(id));
        else System.out.println("Cliente Não Encontrado!");
    }

}
