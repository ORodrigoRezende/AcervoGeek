
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import modelo.Aluga;
import modelo.Cliente;
import modelo.Entidade;
import modelo.ItensAluga;
import persistencia.BancodeDados;

public class Programa {
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        BancodeDados bd =  new BancodeDados();
        int op1=0,op2=0;
        while(op1!=6){
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
                            System.out.println("Digite o id do jogo: ");
                            int idJogo = scn.nextInt();

                            System.out.println("Digite o nome do jogo: ");
                            String nomeJogo = scn.next();

                            System.out.println("Digite o genero do jogo: ");
                            String generoJogo = scn.next();

                            System.out.println("Digite o valor do jogo: ");
                            float valorJogo = scn.nextFloat();

                            System.out.println("Digite o autor do jogo: ");
                            String desenvolvedorJogo = scn.next();

                            bd.getrJogo().inserir(new modelo.Jogo(desenvolvedorJogo, nomeJogo, generoJogo, valorJogo, idJogo));
    
                        break;
                        case 3:
                            System.out.println("Digite o id do livro: ");
                            int idLivro = scn.nextInt();

                            System.out.println("Digite o nome do livro: ");
                            String nomeLivro = scn.next();

                            System.out.println("Digite o genero do livro: ");
                            String generoLivro = scn.next();

                            System.out.println("Digite o valor do livro: ");
                            float valorLivro = scn.nextFloat();

                            System.out.println("Digite o autor do livro: ");
                            String autorLivro = scn.next();

                            bd.getrLivro().inserir(new modelo.Livro(nomeLivro, generoLivro, valorLivro, autorLivro, idLivro));

                            break;
                        case 4:
                            System.out.println("Digite o id do filme: ");
                            int idFilme = scn.nextInt();

                            System.out.println("Digite o nome do filme: ");
                            String nomeFilme = scn.next();

                            System.out.println("Digite o genero do filme: ");
                            String generoFilme = scn.next();

                            System.out.println("Digite o valor do filme: ");
                            float valorFilme = scn.nextFloat();

                            System.out.println("Digite o autor do filme: ");
                            String diretorFilme = scn.next();

                            bd.getrFilme().inserir(new modelo.Filme(diretorFilme,nomeFilme, generoFilme, valorFilme, idFilme));

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
                            System.out.println("Digite o id do jogo que deseja alterar: ");
                            int idJogoA = scn.nextInt();

                            modelo.Jogo j = (modelo.Jogo)bd.getrJogo().buscar(idJogoA);

                            if (j == null){
                                System.out.println("Jogo nao encontrado!");
                                break;
                            }

                            System.out.println("Se nao deseja alterar so da Enter");

                            System.out.printf("Novo nome do jogo: ", j.getNome());
                            String novoNomeJ = scn.nextLine();
                            if (!novoNomeJ.isEmpty()) { j.setNome(novoNomeJ); }

                            System.out.printf("Novo genero do jogo: ", j.getGenero());
                            String novoGeneroJ = scn.nextLine();
                            if (!novoGeneroJ.isEmpty()) { j.setGenero(novoGeneroJ); }

                            System.out.printf("Novo valor do jogo: ", j.getValor());
                            String novoValorJ = scn.nextLine();
                            if (!novoValorJ.isEmpty()) { j.setValor(Float.parseFloat(novoValorJ)); }

                            System.out.printf("Novo autor do jogo: ", j.getDesenvolvedor());
                            String novoAutorJ = scn.nextLine();
                            if (!novoAutorJ.isEmpty()) { j.setDesenvolvedor(novoAutorJ); }

                            bd.getrJogo().alterar(j);
                            System.out.println("Dados do jogo alterados com sucesso!");
    
                        break;
                        case 3:
                            System.out.println("Digite o id do livro que deseja alterar: ");
                            int idLivroA = scn.nextInt();

                            modelo.Livro l = (modelo.Livro)bd.getrLivro().buscar(idLivroA);

                            if (l == null){
                                System.out.println("Livro nao encontrado!");
                                break;
                            }

                            System.out.println("Se nao deseja alterar so da Enter");

                            System.out.printf("Novo nome do livro: ", l.getNome());
                            String novoNomeL = scn.nextLine();
                            if (!novoNomeL.isEmpty()) { l.setNome(novoNomeL); }

                            System.out.printf("Novo genero do livro: ", l.getGenero());
                            String novoGeneroL = scn.nextLine();
                            if (!novoGeneroL.isEmpty()) { l.setGenero(novoGeneroL); }

                            System.out.printf("Novo valor do livro: ", l.getValor());
                            String novoValorL = scn.nextLine();
                            if (!novoValorL.isEmpty()) { l.setValor(Float.parseFloat(novoValorL)); }

                            System.out.printf("Novo autor do livro: ", l.getAutor());
                            String novoAutorL = scn.nextLine();
                            if (!novoAutorL.isEmpty()) { l.setAutor(novoAutorL); }

                            bd.getrLivro().alterar(l);
                            System.out.println("Dados do livro alterados com sucesso!");

                            break;
                        case 4:
                            System.out.println("Digite o id do filme que deseja alterar: ");
                            int idFilmeA = scn.nextInt();

                            modelo.Filme f = (modelo.Filme)bd.getrFilme().buscar(idFilmeA);

                            if (f == null){
                                System.out.println("Filme nao encontrado!");
                                break;
                            }

                            System.out.println("Se nao deseja alterar so da Enter");

                            System.out.printf("Novo nome do filme: ", f.getNome());
                            String novoNomeF = scn.nextLine();
                            if (!novoNomeF.isEmpty()) { f.setNome(novoNomeF); }

                            System.out.printf("Novo genero do filme: ", f.getGenero());
                            String novoGeneroF = scn.nextLine();
                            if (!novoGeneroF.isEmpty()) { f.setGenero(novoGeneroF); }

                            System.out.printf("Novo valor do filme: ", f.getValor());
                            String novoValorF = scn.nextLine();
                            if (!novoValorF.isEmpty()) { f.setValor(Float.parseFloat(novoValorF)); }

                            System.out.printf("Novo autor do filme: ", f.getDiretor());
                            String novoDiretorF = scn.nextLine();
                            if (!novoDiretorF.isEmpty()) { f.setDiretor(novoDiretorF); }

                            bd.getrFilme().alterar(f);
                            System.out.println("Dados do filme alterados com sucesso!");

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
                                    System.out.println("Digite o id do jogo que deseja remover: ");
                                    int idJogoR = scn.nextInt();

                                    if (bd.getrJogo().excluir(idJogoR)){
                                        System.out.println("Jogo removido com sucesso!");
                                    } else{
                                        System.out.println("Id do jogo nao existe! Tente novamente");
                                    }    
                                break;
                                case 3:
                                    System.out.println("Digite o id do livro que deseja remover: ");
                                    int idLivroR = scn.nextInt();

                                    if (bd.getrLivro().excluir(idLivroR)){
                                        System.out.println("Livro removido com sucesso!");
                                    } else{
                                        System.out.println("Id do livro nao existe! Tente novamente");
                                    }
                                    
                                    break;
                            case 4:
                                System.out.println("Digite o id do filme que deseja remover: ");
                                    int idFilmeR = scn.nextInt();

                                    if (bd.getrFilme().excluir(idFilmeR)){
                                        System.out.println("Filme removido com sucesso!");
                                    } else{
                                        System.out.println("Id do filme nao existe! Tente novamente");
                                    }
                            case 5:
                                //Sai da Inserção
                            break;
                            default:
                                break;
                    }
                    case 4:
                        int idAluguel;
                        boolean idValido;
                        do {
                            idValido = true;
                            System.out.println("Digite o ID do aluguel: ");
                            idAluguel = scn.nextInt();

                            if (bd.getrAluga() != null) {
                                for (Aluga a : bd.getrAluga()) {
                                    if (a.getId() == idAluguel) {
                                        System.out.println("ID já existente! Escolha outro.");
                                        idValido = false;
                                        break;
                                    }
                                }
                            }
                        } while (!idValido);

                        System.out.println("Digite o ID do cliente que fará o aluguel: ");
                        int idCliente = scn.nextInt();
                        Cliente clienteAluga = null;

                        for (Entidade c : bd.getrCliente()) {
                            if (c.getId() == idCliente) {
                                clienteAluga = c;
                                break;
                            }
                        }

                        if (clienteAluga == null) {
                            System.out.println("Cliente não encontrado!");
                            break;
                        }

                        LocalDate dataAluguel = LocalDate.now();
                        System.out.println("Digite a quantidade de dias para devolução: ");
                        int diasAluguel = scn.nextInt();
                        LocalDate dataDevolucao = dataAluguel.plusDays(diasAluguel);

                        Aluga aluguel = new Aluga(idAluguel, clienteAluga, dataAluguel, dataDevolucao);

                    
                        int opItem = 0;
                        while (opItem != 4) {
                            System.out.println("""
                                Escolha o tipo de item para alugar:
                                1 - Jogo
                                2 - Livro
                                3 - Filme
                                4 - Finalizar seleção
                            """);
                            opItem = scn.nextInt();

                            if (opItem == 4) break;

                            System.out.println("Digite o ID do item: ");
                            int idItem = scn.nextInt();
                            Entidade item = null;

                            switch (opItem) {
                                case 1 -> {
                                    //Alguem faz o método de busca pra filme, livro e jogo
                                    System.out.println("Busca de Jogo não implementada ainda.");
                                }
                                case 2 -> {
                                    System.out.println("Busca de Livro não implementada ainda.");
                                }
                                case 3 -> {
                                    System.out.println("Busca de Filme não implementada ainda.");
                                }
                                default -> {
                                    System.out.println("Opção inválida!");
                                    continue;
                                }
                            }

                            if (item == null) {
                                System.out.println("Item não encontrado!");
                                continue;
                            }

                            System.out.println("Digite a quantidade de dias para este item: ");
                            int diasItem = scn.nextInt();

                            System.out.println("Digite o valor do aluguel deste item: ");
                            double valorItem = scn.nextDouble();

                            int idItemAluga = aluguel.getItens().size() + 1;
                            ItensAluga itemAlugado = new ItensAluga(idItemAluga, item, diasItem, valorItem);
                            aluguel.adicionarItem(itemAlugado);

                            System.out.println("Item adicionado ao aluguel!");
                        }

                        if (bd.getrAluga() == null) {
                            bd.setrAluga(new ArrayList<>());
                        }
                        bd.getrAluga().add(aluguel);

                        System.out.println("\nAluguel registrado com sucesso!");
                        System.out.println(aluguel);
                    break;
                    case 5:
                        System.out.println("""
                            Escolha o que quer listar:
                                1 - Clientes
                                2 - Jogos
                                3 - Livros
                                4 - Filmes
                                5 - Voltar
                        """);
                        op2 = scn.nextInt();
                        switch (op2) {
                            case 1:
                                System.out.println(bd.getrCliente().toString());
                                break;
                            case 2:
                                System.out.println(bd.getrJogo().toString());
                                break;
                            case 3:
                                System.out.println(bd.getrLivro().toString());
                                break;
                            case 4:
                                System.out.println(bd.getrFilme().toString());
                                break;
                            default:
                                break;
                        }
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
