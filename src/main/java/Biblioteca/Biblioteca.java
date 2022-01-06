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

        bemVindo();
    }

    private static void bemVindo() {
        Administrador admin = new Administrador("admin", "admin");

        administradores.add(admin);

        System.out.println("Bem-vindo à biblioteca!");
        System.out.println("Acessar como USUÁRIO? [Digite 0] /OU/ Acessar como ADMINISTRADOR? [Digite 1] /OU/ SAIR [Digite 2]");

        Scanner scannerTipoUsuario = new Scanner(System.in);

        int tipoUsuario = scannerTipoUsuario.nextInt();

        switch (tipoUsuario) {
            case 0:
                System.out.println("Já é nosso usuário? [Digite 0] /OU/ É a sua primeira vez aqui? [Digite 1]");

                Scanner scannerLoginOuCadastro = new Scanner(System.in);

                int loginOuCadastro = scannerLoginOuCadastro.nextInt();

                if (loginOuCadastro == 0) {
                    login();
                } else {
                    cadastro();
                }
                break;
            case 1:
                menuAdmin(admin);
                break;
            case 2:
                System.exit(0);
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

        String nome = scannerNome.nextLine();

        System.out.print("\nSenha:");

        Scanner scannerSenha = new Scanner(System.in);

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
        if (usuario.isBloqueado()) {
            System.out.println("Você foi bloqueado no sistema por um administrador.");
            System.out.println("Requisite o desbloqueio para realizar suas ações.");
        }

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
                if (usuario.isBloqueado()) {
                    System.out.println("Você foi bloqueado no sistema por um administrador.");
                    System.out.println("Requisite o desbloqueio para realizar suas ações.");
                    break;
                }
                System.out.println("Aqui está nosso acervo:");
                System.out.println("ID | Título | Autor | Categoria");

                for (Obra obra: obras) {
                    System.out.println(obra.getId() + " | " + obra.getTitulo() + " | " + obra.getAutor() + " | " + checaCategoria(obra.getCategoria()) + " | " + checaEstadoObra(obra));
                }
                break;
            case 1:
                if (usuario.isBloqueado()) {
                    System.out.println("Você foi bloqueado no sistema por um administrador.");
                    System.out.println("Requisite o desbloqueio para realizar suas ações.");
                    break;
                }
                System.out.println("Qual obra gostaria de alugar?");
                System.out.print("ID: ");

                Scanner scannerIDAluguel = new Scanner(System.in);

                int idAluguel = scannerIDAluguel.nextInt();

                usuario.alugarObra(idAluguel, obras);
                break;
            case 2:
                if (usuario.isBloqueado()) {
                    System.out.println("Você foi bloqueado no sistema por um administrador.");
                    System.out.println("Requisite o desbloqueio para realizar suas ações.");
                    break;
                }
                System.out.println("Qual obra gostaria de reservar?");
                System.out.print("ID: ");

                Scanner scannerIDReserva = new Scanner(System.in);

                int idReserva = scannerIDReserva.nextInt();

                usuario.reservarObra(idReserva, obras);
                break;
            case 3:
                if (usuario.isBloqueado()) {
                    System.out.println("Você foi bloqueado no sistema por um administrador.");
                    System.out.println("Requisite o desbloqueio para realizar suas ações.");
                    break;
                }
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
                if (usuario.isBloqueado()) {
                    System.out.println("Você foi bloqueado no sistema por um administrador.");
                    System.out.println("Requisite o desbloqueio para realizar suas ações.");
                    break;
                }
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
                bemVindo();
                break;
            default:
                System.out.println("Ação inválida, por favor insira um número válido!");
                menu(usuario);
        }

        menu(usuario);
    }

    private static void menuAdmin(Administrador admin) {
        System.out.println("Menu do administrador:");
        System.out.println("[0] Ver obras");
        System.out.println("[1] Adicionar obra");
        System.out.println("[2] Remover obra");
        System.out.println("[3] Editar obra");
        System.out.println("[4] Bloquear usuário");
        System.out.println("[5] Desbloquear usuário");
        System.out.println("[6] Sair");

        Scanner scannerAcao = new Scanner(System.in);

        int acao = scannerAcao.nextInt();

        switch (acao) {
            case 0:
                System.out.println("ID | Título | Autor | Categoria");

                for (Obra obra: obras) {
                    System.out.println(obra.getId() + " | " + obra.getTitulo() + " | " + obra.getAutor() + " | " + checaCategoria(obra.getCategoria()) + " | " + checaEstadoObra(obra));
                }
                break;
            case 1:
                System.out.println("Para adicionar uma nova obra forneça os seguintes dados:");
                System.out.println("Título:");

                Scanner scannerTituloAdicao = new Scanner(System.in);

                String tituloAdicao = scannerTituloAdicao.nextLine();

                System.out.println("Autor:");

                Scanner scannerAutorAdicao = new Scanner(System.in);

                String autorAdicao = scannerAutorAdicao.nextLine();

                System.out.println("Categoria:");

                Scanner scannerCategoriaAdicao = new Scanner(System.in);

                int categoriaAdicao = scannerCategoriaAdicao.nextInt();

                admin.criaObra(tituloAdicao, autorAdicao, categoriaAdicao, obras);
                break;
            case 2:
                System.out.println("Para remover uma nova obra forneça os seguintes dados:");
                System.out.println("ID:");

                Scanner scannerIDRemocao = new Scanner(System.in);

                int idRemocao = scannerIDRemocao.nextInt();

                admin.removeObra(idRemocao, obras);
                break;
            case 3:
                System.out.println("Para editar uma nova obra forneça os seguintes dados:");
                System.out.println("ID:");

                Scanner scannerIDEdicao = new Scanner(System.in);

                int idEdicao = scannerIDEdicao.nextInt();

                System.out.println("Título:");

                Scanner scannerTituloEdicao = new Scanner(System.in);

                String tituloEdicao = scannerTituloEdicao.nextLine();

                System.out.println("Autor:");

                Scanner scannerAutorEdicao = new Scanner(System.in);

                String autorEdicao = scannerAutorEdicao.nextLine();

                System.out.println("Categoria:");

                Scanner scannerCategoriaEdicao = new Scanner(System.in);

                int categoriaEdicao = scannerCategoriaEdicao.nextInt();

                admin.editaObra(idEdicao, tituloEdicao, autorEdicao, categoriaEdicao, obras);
                break;
            case 4:
                System.out.println("Para bloquear um usuário forneça os seguintes dados:");
                System.out.println("ID:");

                Scanner scannerIDBloqueio = new Scanner(System.in);

                int idBloqueio = scannerIDBloqueio.nextInt();

                admin.bloqueiaUsuario(idBloqueio, usuarios);
                break;
            case 5:
                System.out.println("Para desbloquear um usuário forneça os seguintes dados:");
                System.out.println("ID:");

                Scanner scannerIDDesbloqueio = new Scanner(System.in);

                int idDesbloqueio = scannerIDDesbloqueio.nextInt();

                admin.desbloqueiaUsuario(idDesbloqueio, usuarios);
                break;
            case 6:
                bemVindo();
                break;
        }

        menuAdmin(admin);
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
