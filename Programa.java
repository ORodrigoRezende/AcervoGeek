
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import modelo.Aluga;
import modelo.Cliente;
import modelo.Entidade;
import modelo.Filme;
import modelo.ItensAluga;
import modelo.Jogo;
import modelo.Livro;
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
                            int id;
                            System.out.println("Digite o id do cliente: ");
                            id = scn.nextInt();
                            scn.nextLine();

                            System.out.println("Digite o nome do cliente: ");
                            String nome = scn.nextLine();

                            System.out.println("Digite o telefone do cliente: ");
                            String telefone = scn.nextLine();

                            System.out.println("Digite o cpf do cliente: ");
                            String cpf = scn.nextLine();

                            System.out.println("Digite o endereço do cliente: ");
                            String endereco = scn.nextLine();

                            bd.getrCliente().inserir(new Cliente(id,nome,telefone,cpf,endereco));
                            break;
                        case 2:
                            System.out.println("Digite o id do jogo: ");
                            int idJogo = scn.nextInt();
                            scn.nextLine();

                            System.out.println("Digite o nome do jogo: ");
                            String nomeJogo = scn.nextLine();

                            System.out.println("Digite o genero do jogo: ");
                            String generoJogo = scn.nextLine();

                            System.out.println("Digite o valor do jogo: ");
                            float valorJogo = scn.nextFloat();
                            scn.nextLine();

                            System.out.println("Digite o desenvolvedor do jogo: ");
                            String desenvolvedorJogo = scn.nextLine();

                            bd.getrJogo().inserir(new Jogo(desenvolvedorJogo, nomeJogo, generoJogo, valorJogo, idJogo));
    
                        break;
                        case 3:
                            System.out.println("Digite o id do livro: ");
                            int idLivro = scn.nextInt();
                            scn.nextLine();

                            System.out.println("Digite o nome do livro: ");
                            String nomeLivro = scn.nextLine();

                            System.out.println("Digite o genero do livro: ");
                            String generoLivro = scn.nextLine();

                            System.out.println("Digite o valor do livro: ");
                            float valorLivro = scn.nextFloat();
                            scn.nextLine();

                            System.out.println("Digite o autor do livro: ");
                            String autorLivro = scn.nextLine();

                            bd.getrLivro().inserir(new Livro(nomeLivro, generoLivro, valorLivro, autorLivro, idLivro));

                            break;
                        case 4:
                            System.out.println("Digite o id do filme: ");
                            int idFilme = scn.nextInt();
                            scn.nextLine();

                            System.out.println("Digite o nome do filme: ");
                            String nomeFilme = scn.nextLine();

                            System.out.println("Digite o genero do filme: ");
                            String generoFilme = scn.nextLine();

                            System.out.println("Digite o valor do filme: ");
                            float valorFilme = scn.nextFloat();
                            scn.nextLine();

                            System.out.println("Digite o diretor do filme: ");
                            String diretorFilme = scn.nextLine();

                            bd.getrFilme().inserir(new Filme(diretorFilme,nomeFilme, generoFilme, valorFilme, idFilme));

                        case 5:
                            //Sai da Inserção
                        break;
                    default:
                        System.out.println("Opção Invalida!");
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
                        case 1:
                            System.out.println("=== Clientes cadastrados ===");
                            System.out.println(bd.getrCliente().toString());
                            System.out.println("Digite o id do Cliente que deseja alterar: ");
                            int idC = scn.nextInt();
                            scn.nextLine();

                            Cliente c = (Cliente)bd.getrJogo().buscar(idC);

                            if (c == null){
                                System.out.println("Cliente nao encontrado!");
                                break;
                            }

                            System.out.println("Se nao deseja alterar so da Enter");

                            System.out.printf("Novo nome do Cliente: ", c.getNome_do_cliente());
                            String novoNomec = scn.nextLine();
                            if (!novoNomec.isEmpty()) { c.setNome_do_cliente(novoNomec); }

                            System.out.printf("Novo Telfone do Cliente: ", c.getTelefone_do_cliente());
                            String novoTelefone = scn.nextLine();
                            if (!novoTelefone.isEmpty()) { c.setTelefone_do_cliente(novoTelefone); }

                            System.out.printf("Novo CPF do Cliente: ", c.getCpf());
                            String novocpf = scn.nextLine();
                            if (!novocpf.isEmpty()) { c.setCpf(novocpf);}

                            System.out.printf("Novo Entedereço do Cliente: ", c.getEndereco());
                            String novoEndereco = scn.nextLine();
                            if (!novoEndereco.isEmpty()) { c.setEndereco(novoEndereco); }

                            bd.getrCliente().alterar(c);
                            System.out.println("Dados do Cliente alterados com sucesso!");
                            break;
                        case 2:
                            System.out.println("=== Jogos cadastrados ===");
                            System.out.println(bd.getrJogo().toString());
                            System.out.println("Digite o id do jogo que deseja alterar: ");
                            int idJogoA = scn.nextInt();
                            scn.nextLine();

                            Jogo j = (Jogo)bd.getrJogo().buscar(idJogoA);

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

                            System.out.printf("Novo desenvolvedor do jogo: ", j.getDesenvolvedor());
                            String novoAutorJ = scn.nextLine();
                            if (!novoAutorJ.isEmpty()) { j.setDesenvolvedor(novoAutorJ); }

                            bd.getrJogo().alterar(j);
                            System.out.println("Dados do jogo alterados com sucesso!");
    
                        break;
                        case 3:
                            System.out.println("=== Livros cadastrados ===");
                            System.out.println(bd.getrLivro().toString());
                            System.out.println("Digite o id do livro que deseja alterar: ");
                            int idLivroA = scn.nextInt();
                            scn.nextLine();

                            Livro l = (Livro)bd.getrLivro().buscar(idLivroA);

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
                            System.out.println("=== Filmes cadastrados ===");
                            System.out.println(bd.getrFilme().toString());
                            System.out.println("Digite o id do filme que deseja alterar: ");
                            int idFilmeA = scn.nextInt();
                            scn.nextLine();

                            Filme f = (Filme)bd.getrFilme().buscar(idFilmeA);

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

                            System.out.printf("Novo diretor do filme: ", f.getDiretor());
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
                                    System.out.println("=== Clientes cadastrados ===");
                                    System.out.println(bd.getrCliente().toString());
                                    System.out.println("Digite o id do Cliente que deseja remover: ");
                                    int idC = scn.nextInt();
                                    scn.nextLine();

                                    if (bd.getrJogo().excluir(idC)){
                                        System.out.println("Cliente removido com sucesso!");
                                    } else{
                                        System.out.println("Id do Cliente nao existe! Tente novamente");
                                    }    
                                break;
                                case 2:
                                    System.out.println("=== Jogos cadastrados ===");
                                    System.out.println(bd.getrJogo().toString());
                                    System.out.println("Digite o id do jogo que deseja remover: ");
                                    int idJogoR = scn.nextInt();
                                    scn.nextLine(); 

                                    if (bd.getrJogo().excluir(idJogoR)){
                                        System.out.println("Jogo removido com sucesso!");
                                    } else{
                                        System.out.println("Id do jogo nao existe! Tente novamente");
                                    }    
                                break;
                                case 3:
                                    System.out.println("=== Livros cadastrados ===");
                                    System.out.println(bd.getrLivro().toString());   
                                    System.out.println("Digite o id do livro que deseja remover: ");
                                    int idLivroR = scn.nextInt();
                                    scn.nextLine();

                                    if (bd.getrLivro().excluir(idLivroR)){
                                        System.out.println("Livro removido com sucesso!");
                                    } else{
                                        System.out.println("Id do livro nao existe! Tente novamente");
                                    }
                                    
                                    break;
                            case 4:
                                System.out.println("=== Filmes cadastrados ===");
                                System.out.println(bd.getrFilme().toString());
                                System.out.println("Digite o id do filme que deseja remover: ");
                                    int idFilmeR = scn.nextInt();
                                    scn.nextLine();

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
                            scn.nextLine();

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
                        scn.nextLine();
                        Cliente clienteAluga = null;

                        Entidade entidadeEncontrada = bd.getrCliente().buscar(idCliente);

                        if (entidadeEncontrada instanceof Cliente) {
                            clienteAluga = (Cliente) entidadeEncontrada;
                        }
                        if (clienteAluga == null) {
                            System.out.println("Cliente não encontrado!");
                            break;
                        }

                        LocalDate dataAluguel = LocalDate.now();
                        System.out.println("Digite a quantidade de dias para devolução: ");
                        int diasAluguel = scn.nextInt();
                        scn.nextLine();
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
                            scn.nextLine();

                            if (opItem == 4) break;

                            System.out.println("Digite o ID do item: ");
                            int idItem = scn.nextInt();
                            scn.nextLine();
                            Entidade item = null;

                            switch (op2) {
                                case 1:
                                    System.out.println(bd.getrJogo().toString());
                                    break;
                                case 2:
                                    System.out.println(bd.getrLivro().toString());
                                    break;
                                case 3:
                                    System.out.println(bd.getrFilme().toString());
                                    break;
                                default:
                                    System.out.println("Opcao Invalida");
                                    break;
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
                        scn.nextLine();
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
