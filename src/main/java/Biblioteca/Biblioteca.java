package Biblioteca;

import java.util.*;

public class Biblioteca {
    private static List<Obra> obras = new ArrayList<>();
    private static List<Administrador> administradores = new ArrayList<>();
    private static List<Usuario> usuarios = new ArrayList<>();

    public static void main(String[] args) {
        obras.add(new Obra("As aventuras de TinTim", "Hergé", 1));
        obras.add(new Obra("A divina comédia", "Dante Alighieri", 2));
        obras.add(new Obra("O morro dos ventos uivantes", "Emily Brontë", 3));
        obras.add(new Obra("Sombra e ossos", "Leigh Bardugo", 4));
        obras.add(new Obra("O segredo da sala rosa", "Glaucia Lewicki", 5));
        obras.add(new Obra("O pequeno príncipe", "Antoine de Saint-Exupéry", 6));

        Usuario usuario = new Usuario("usuario", "senha");

        usuarios.add(usuario);

        System.out.println("Bem-vindo à biblioteca!");
        System.out.println("Já é nosso usuário? [Digite 0] /OU/ É a sua primeira vez aqui? [Digite 1]");

        Scanner scannerLoginOuCadastro = new Scanner(System.in);

        int loginOuCadastro = scannerLoginOuCadastro.nextInt();

        if (loginOuCadastro == 0) {
            login();
        } else {
            cadastro();
        }
    }

    private static void login() {
        System.out.println("Digite abaixo o nome e a senha correspondetes ao seu usuário.\n");

        System.out.print("\nNome:");
        Scanner scannerNome = new Scanner(System.in);

        System.out.print("\nSenha:");
        Scanner scannerSenha = new Scanner(System.in);

        String nome = scannerNome.nextLine();
        String senha = scannerSenha.nextLine();

        if (checaUsuario(0, nome, senha)) {
            Usuario novoUsuario = null;

            for (Usuario usuario : usuarios) {
                if (usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
                    novoUsuario = usuario;
                }
            }

            menu(novoUsuario);
        } else {
            System.out.println("Algo deu errado!");
            System.out.println("Confira os dados fornecidos e caso continue apresentando erros crie sua conta novamente.");
            login();
        }
    }

    private static void cadastro() {
        System.out.println("Digite abaixo o nome e a senha que usará para acessar.\n");

        System.out.print("\nNome:");
        Scanner scannerNome = new Scanner(System.in);

        System.out.print("\nSenha:");
        Scanner scannerSenha = new Scanner(System.in);

        String nome = scannerNome.nextLine();
        String senha = scannerSenha.nextLine();

        if (checaUsuario(1, nome, senha)) {
            System.out.println("Essa conta já existe, você será direcionado para fazer o login.");
            login();
        } else {
            Usuario novoUsuario = new Usuario(nome, senha);
            usuarios.add(novoUsuario);
            menu(novoUsuario);
        }
    }

    private static void menu(Usuario usuario) {
        System.out.println("Menu da biblioteca:");
        System.out.println("[0] Ver obras");
        System.out.println("[1] Alugar obra");
        System.out.println("[2] Reservar obra");
        System.out.println("[3] Prolongar prazo");
        System.out.println("[4] Requisitar desbloqueio");
        System.out.println("[5] Checar disponibilidade");
        System.out.println("[6] Sair");

        Scanner scannerAcao = new Scanner(System.in);

        int acao = scannerAcao.nextInt();

        switch (acao) {
            case 0:
                System.out.println("Aqui está nosso acervo:");
                System.out.println("ID | Título | Autor | Categoria");

                for (Obra obra: obras) {
                    System.out.println(obra.getId() + " | " + obra.getTitulo() + " | " + obra.getAutor() + " | " + checaCategoria(obra.getCategoria()) + " | " + checaEstadoObra(obra));
                }
                break;
            case 1:
                System.out.println("Qual obra gostaria de alugar?");
                System.out.print("ID: ");

                Scanner scannerIDAluguel = new Scanner(System.in);

                int idAluguel = scannerIDAluguel.nextInt();

                usuario.alugarObra(idAluguel, obras);
                break;
            case 2:
                System.out.println("Qual obra gostaria de reservar?");
                System.out.print("ID: ");

                Scanner scannerIDReserva = new Scanner(System.in);

                int idReserva = scannerIDReserva.nextInt();

                usuario.reservarObra(idReserva, obras);
                break;
            case 3:
                if (usuario.possuiAluguel()) {
                    usuario.prolongarPrazo(usuario.getAluguel(), 604800000, obras);
                    System.out.println("Aluguel prorrogado!");
                    break;
                }
                if (usuario.possuiReserva()) {
                    usuario.prolongarPrazo(usuario.getReserva(), 172800000, obras);
                    System.out.println("Reserva prorrogada!");
                    break;
                }
                System.out.println("Usuário não tem aluguel/reserva na biblioteca.");
                break;
            case 4:
                // requisita desbloqueio
                break;
            case 5:
                System.out.println("Qual obra gostaria de checar?");
                System.out.print("ID: ");

                Scanner scannerIDChecagem = new Scanner(System.in);

                int idChecagem = scannerIDChecagem.nextInt();

                for (Obra obra: obras) {
                    if (obra.getId() == idChecagem) {
                        System.out.println("Tempo de aluguel restante:");
                        obra.checaDisponibilidade();
                    }
                }
                break;
            case 6:
                return;
            default:
                System.out.println("Ação inválida, por favor insira um número válido!");
                menu(usuario);
        }

        menu(usuario);
    }

    private static String checaEstadoObra(Obra obra) {
        if (obra.isReservada()) {
            return "Reservada";
        }

        if (obra.isAlugada()) {
            return "Alugada";
        }

        return "Disponível";
    }

    private static String checaCategoria(Integer index) {
        switch (index) {
            case 1:
                return "Aventura";
            case 2:
                return "Comédia";
            case 3:
                return "Romance";
            case 4:
                return "Ficção";
            case 5:
                return "Mistério";
            case 6:
                return "Infantil";
            default:
                return "Categoria inválida!";
        }
    }

    private static boolean checaUsuario(Integer option, String nome, String senha) {
        if (option == 1) {

            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getNome() + " - " + nome);

                if (usuario.getNome().equals(nome)) {
                    return true;
                }

            }
            return false;
        } else {

            for (Usuario usuario : usuarios) {
                System.out.println(usuario.getNome() + "," + usuario.getSenha() + " - " + nome + "," + senha);

                if (usuario.getNome().equals(nome) && usuario.getSenha().equals(senha)) {
                    return true;
                }

            }
            return false;
        }
    }
}
