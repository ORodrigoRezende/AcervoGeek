
import java.util.Scanner;
import modelo.Cliente;
import modelo.Entidade;
import persistencia.BancodeDados;


public class Programa {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        BancodeDados bd =  new BancodeDados();
        int op1=0,op2=0;
        while(op1!=5){
            System.out.println("""
                    Acervo Geek
                
                Seja bem vindo ao Acervo Geek, escolha uma opção!
                
                1 - Cadastrar
                2 - Alterar
                3 - Remover
                4 - Alugar
                5 - Listar
                6 - Sair

                    """);
            op1 = scn.nextInt();
            switch (op1) {
                case 1:
                    System.out.println("""
                    Acervo Geek
                
                Escolha o que você quer cadastrar!
                
                1 - Cliente
                2 - Jogo
                3 - Livro
                4 - Filme
                5 - Voltar
                
                    """);
                op2 = scn.nextInt();
                    switch (op2) {
                        case 1:
                            Entidade d;
                            int id;
                            //do { 
                            System.out.println("Digite o id do cliente: ");
                            id = scn.nextInt();
                            /*if(getCliente(id)!=null){
                                System.out.println("Id do cliente já existe!, Tente novamente");
                                }else idT = false;
                            } while (idT); // Verificar se tem outro cliente com mesmo id; */
                            System.out.println("Digite o nome do cliente: ");
                            String nome = scn.next();
                            System.out.println("Digite o telefone do cliente: ");
                            String telefone = scn.next();
                            System.out.println("Digite o cpf do cliente: ");
                            String cpf = scn.next();
                            System.out.println("Digite o endereço do cliente: ");
                            String endereco = scn.next();
                            bd.getrCliente().inserir(new Cliente(id,nome,telefone,cpf,endereco));
                            break;
                        case 2:
                            // Inserção Jogo    
                        break;
                        case 3:
                            // Inserção Livro
                        break;
                        case 4:
                            // Inserção Filme
                        case 5:
                            //Sai da Inserção
                        break;
                    default:
                        System.out.println("Numero Invalido!");
                    break;
                }
                break;
                        
                case 2:
                    System.out.println("""
                    Acervo Geek
                
                Escolha o que você quer alterar!
                
                1 - Cliente
                2 - Jogo
                3 - Livro
                4 - Filme
                5 - Voltar
                
                    """);
                op2 = scn.nextInt();
                    switch (op2) {
                        case 1:/*
                            boolean idT=true;
                            Entidade c;
                            int id;
                            do { 
                                System.out.println("Digite o id do cliente que deseja alterar: ");
                                id = scn.nextInt();
                                scn.nextLine();
                                c = getEntidade(id);
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
                            if(!endereco.isEmpty())c.setEndereco(endereco);*/
                            break;
                        case 2:
                            // Inserção Jogo    
                        break;
                        case 3:
                            // Inserção Livro
                        break;
                        case 4:
                            // Inserção Filme
                        case 5:
                            //Sai da Inserção
                        break;
                        default:
                            break;
                    } break;
                case 3:
                        System.out.println("""
                        Acervo Geek
                
                        Escolha o que você quer remover!
                        
                        1 - Cliente
                        2 - Jogo
                        3 - Livro
                        4 - Filme
                        5 - Voltar
                        
                            """);
                        op2 = scn.nextInt();
                            switch (op2) {
                                case 1:
                                /* 
                                    boolean idT=true;
                                    Entidade c;
                                    int id;
                                    do { 
                                        System.out.println("Digite o id do cliente que deseja remover: ");
                                        id = scn.nextInt();
                                        scn.nextLine();
                                        c = getEntidade(id);
                                        if(c==null){
                                            System.out.println("Id do cliente não existe!, Tente novamente");
                                        }else idT = false;
                                    } while (idT); // Verifica se tem outro cliente com mesmo id;
                                    
                                    bd.getrCliente().excluir(id);*/
                                    break;
                                case 2:
                                    // Inserção Jogo    
                                break;
                                case 3:
                                    // Inserção Livro
                                break;
                            case 4:
                                // Inserção Filme
                            case 5:
                                //Sai da Inserção
                            break;
                            default:
                                break;
                    }
                    case 4:

                    // Venda Alguem faz;



                    break;
                    case 5:
                        //Listar
                    break;
                    case 6:
                    //Sair
                    break;
                    default:
                        System.out.println("Entrada Invalida");
                    break;
            }
        }
    }
}
