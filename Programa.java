
public class Programa {
    public static void main(String[] args) {
        
    }
}


/* Teste Cliente ok
        RegistroCliente rCliente = new RegistroCliente();

        try (Scanner scn = new Scanner(System.in)) {
            int x = -1;
            String entrada;

            do {
                System.out.println("\n--- MENU PRINCIPAL ---");
                System.out.println("1 - Inserir Cliente");
                System.out.println("2 - Alterar Cliente");
                System.out.println("3 - Remover Cliente (ID)");
                System.out.println("4 - Visualizar Clientes");
                System.out.println("0 - Sair");
                System.out.print("Digite uma opção: ");

                entrada = scn.nextLine();

                try {
                    x = Integer.parseInt(entrada);
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Digite um número inteiro.");
                    x = -1; // Mantém o loop ativo
                }

                switch (x) {
                    case 1:
                        rCliente.addCliente(); 
                        break;
                    case 2:
                        rCliente.alteraCliente();
                        break;
                    case 3:
                        System.out.print("Digite o ID do cliente a ser removido: ");
                        try {
                            int y = Integer.parseInt(scn.nextLine());
                            rCliente.removeCliente(y);
                        } catch (NumberFormatException e) {
                            System.out.println("ID inválido. Digite um número inteiro para o ID.");
                        }
                        break;
                    case 4:
                        rCliente.listaClientes();
                        break;
                    case 0:
                        System.out.println("Saindo do programa. Até logo!");
                        break;
                    default:
                        System.out.println("Opção não reconhecida. Tente novamente.");
                        break;
                }
            } while (x != 0);
        }*/