package Biblioteca;

import java.util.*;
import java.io.*;

public class Biblioteca {
    private static List<Obra> obras = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Funcionario> funcionarios = new ArrayList<>();
    private static List<Administrador> administradores = new ArrayList<>();
    private static List<Cliente> requisicoes = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        obras.add(new Obra("As aventuras de TinTim", "Hergé", 1));
        obras.add(new Obra("A divina comédia", "Dante Alighieri", 2));
        obras.add(new Obra("O morro dos ventos uivantes", "Emily Brontë", 3));
        obras.add(new Obra("Sombra e ossos", "Leigh Bardugo", 4));
        obras.add(new Obra("O segredo da sala rosa", "Glaucia Lewicki", 5));
        obras.add(new Obra("O pequeno príncipe", "Antoine de Saint-Exupéry", 6));

        bemVindo();
    }

    private static void bemVindo() throws Exception {
        Administrador admin = new Administrador("admin", "admin");
        Funcionario funcionario = new Funcionario("frederico", "biblioteca");

        administradores.add(admin);
        funcionarios.add(funcionario);

        System.out.println("Bem-vindo à biblioteca!");
        System.out.println("Acessar como: \nUSUÁRIO? [Digite 0] \nFUNCIONÁRIO? [DIGITE 1] \nADMINISTRADOR? [Digite 2] \nSAIR [Digite 4]");

        Scanner scannerTipoUsuario = new Scanner(System.in);

        int tipoUsuario = scannerTipoUsuario.nextInt();

        switch (tipoUsuario) {
            case 0:
                System.out.println("Já é nosso usuário? [Digite 0] \nÉ a sua primeira vez aqui? [Digite 1]");

                Scanner scannerLoginOuCadastro = new Scanner(System.in);

                int loginOuCadastro = scannerLoginOuCadastro.nextInt();

                if (loginOuCadastro == 0) {
                    login(0);
                } else {
                    cadastro();
                }
                break;
            case 1:
                login(1);
                break;
            case 2:
                login(2);
                break;
            case 3:
                System.exit(0);
        }
    }

    private static void login(int opcao) throws Exception {
        System.out.println("Digite abaixo o nome e a senha correspondentes ao seu usuário.\n");

        System.out.print("\nNome:");
        Scanner scannerNome = new Scanner(System.in);

        System.out.print("\nSenha:");
        Scanner scannerSenha = new Scanner(System.in);

        String nome = scannerNome.nextLine();
        String senha = scannerSenha.nextLine();

        switch (opcao) {
            case 0:
                if ((checaCliente(0, nome, senha))) {
                    Cliente novoCliente = null;

                    for (Cliente cliente : clientes) {
                        if (cliente.getNome().equals(nome) && cliente.getSenha().equals(senha)) {
                            novoCliente = cliente;
                        }
                    }

                    menuCliente(novoCliente);
                } else {
                    throw new Exception("Algo deu errado! \n Confira os dados fornecidos e caso continue apresentando erros crie sua conta novamente.");
                }
                break;
            case 1:
                if (checaFuncionario(nome, senha)) {
                    Funcionario novoFuncionario = null;

                    for (Funcionario funcionario: funcionarios) {
                        if (funcionario.getNome().equals(nome) && funcionario.getSenha().equals(senha)) {
                            novoFuncionario = funcionario;
                        }
                    }

                    menuFuncionario(novoFuncionario);
                } else {
                    throw new Exception("Algo deu errado! \n Confira os dados fornecidos.");
                }
                break;
            case 2:
                if (checaAdmin(nome, senha)) {
                    Administrador novoAdmin = null;

                    for (Administrador admin: administradores) {
                        if (admin.getNome().equals(nome) && admin.getSenha().equals(senha)) {
                            novoAdmin = admin;
                        }
                    }

                    menuAdmin(novoAdmin);
                } else {
                    throw new Exception("Algo deu errado! \n Confira os dados fornecidos e caso continue apresentando erros crie sua conta novamente.");
                }
                break;
        }
    }

    private static void cadastro() throws Exception {
        System.out.println("Digite abaixo o nome e a senha que usará para acessar.\n");

        System.out.print("\nNome:");

        Scanner scannerNome = new Scanner(System.in);

        String nome = scannerNome.nextLine();

        System.out.print("\nSenha:");

        Scanner scannerSenha = new Scanner(System.in);

        String senha = scannerSenha.nextLine();

        if (checaCliente(1, nome, senha)) {
            throw new Exception("Essa conta já existe, você será direcionado para fazer o login.");
        } else {
            Cliente novoCliente = new Cliente(nome, senha);
            clientes.add(novoCliente);
            menuCliente(novoCliente);
        }
    }

    private static void menuCliente(Cliente cliente) throws Exception {
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
                if (cliente.isBloqueado()) {
                    throw new Exception("Você foi bloqueado no sistema por um administrador. \nRequisite o desbloqueio para realizar suas ações.");
                }
                System.out.println("Aqui está nosso acervo:");
                System.out.println("ID | Título | Autor | Categoria");

                for (Obra obra: obras) {
                    System.out.println(obra.getId() + " | " + obra.getTitulo() + " | " + obra.getAutor() + " | " + checaCategoria(obra.getCategoria()) + " | " + checaEstadoObra(obra));
                }
                break;
            case 1:
                if (cliente.isBloqueado()) {
                    throw new Exception("Você foi bloqueado no sistema por um administrador. \nRequisite o desbloqueio para realizar suas ações.");
                }
                System.out.println("Qual obra gostaria de alugar?");
                System.out.print("ID: ");

                Scanner scannerIDAluguel = new Scanner(System.in);

                int idAluguel = scannerIDAluguel.nextInt();

                cliente.alugarObra(idAluguel, obras);
                break;
            case 2:
                if (cliente.isBloqueado()) {
                    throw new Exception("Você foi bloqueado no sistema por um administrador. \nRequisite o desbloqueio para realizar suas ações.");
                }
                System.out.println("Qual obra gostaria de reservar?");
                System.out.print("ID: ");

                Scanner scannerIDReserva = new Scanner(System.in);

                int idReserva = scannerIDReserva.nextInt();

                cliente.reservarObra(idReserva, obras);
                break;
            case 3:
                if (cliente.isBloqueado()) {
                    throw new Exception("Você foi bloqueado no sistema por um administrador. \nRequisite o desbloqueio para realizar suas ações.");
                }
                if (cliente.getAluguel() >= 0) {
                    cliente.prolongarPrazo(cliente.getAluguel(), 604800000, obras);
                    System.out.println("Aluguel prorrogado!");
                    break;
                }
                if (cliente.getReserva() >= 0) {
                    cliente.prolongarPrazo(cliente.getReserva(), 172800000, obras);
                    System.out.println("Reserva prorrogada!");
                    break;
                }
                System.out.println("Usuário não tem aluguel/reserva na biblioteca.");
                break;
            case 4:
                requisicoes.add(cliente);
                break;
            case 5:
                if (cliente.isBloqueado()) {
                    throw new Exception("Você foi bloqueado no sistema por um administrador. \nRequisite o desbloqueio para realizar suas ações.");
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
                menuCliente(cliente);
        }

        menuCliente(cliente);
    }

    private static void menuFuncionario(Funcionario funcionario) throws Exception {
        System.out.println("Menu do funcionário:");
        System.out.println("[0] Ver obras");
        System.out.println("[1] Adicionar obra");
        System.out.println("[2] Editar obra");
        System.out.println("[3] Remover obra");
        System.out.println("[4] Sair");

        Scanner scannerAcao = new Scanner(System.in);

        int acao = scannerAcao.nextInt();

        switch (acao) {
            case 0:
                if (!funcionario.getTipoUsuario().equals("funcionario")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
                System.out.println("ID | Título | Autor | Categoria");

                for (Obra obra: obras) {
                    System.out.println(obra.getId() + " | " + obra.getTitulo() + " | " + obra.getAutor() + " | " + checaCategoria(obra.getCategoria()) + " | " + checaEstadoObra(obra));
                }
                break;
            case 1:
                if (!funcionario.getTipoUsuario().equals("funcionario")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
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

                funcionario.criaObra(tituloAdicao, autorAdicao, categoriaAdicao, obras);
                break;
            case 2:
                if (!funcionario.getTipoUsuario().equals("funcionario")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
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

                funcionario.editaObra(idEdicao, tituloEdicao, autorEdicao, categoriaEdicao, obras);
            case 3:
                if (!funcionario.getTipoUsuario().equals("funcionario")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
                System.out.println("Para remover uma nova obra forneça os seguintes dados:");
                System.out.println("ID:");

                Scanner scannerIDRemocao = new Scanner(System.in);

                int idRemocao = scannerIDRemocao.nextInt();

                funcionario.removeObra(idRemocao, obras);
                break;
            case 4:
                bemVindo();
                break;
        }

        menuFuncionario(funcionario);
    }

    private static void menuAdmin(Administrador admin) throws Exception {
        System.out.println("Menu do administrador:");
        System.out.println("[0] Ver obras");
        System.out.println("[1] Cadastrar funcionário");
        System.out.println("[2] Editar funcionário");
        System.out.println("[3] Remover funcionário");
        System.out.println("[4] Bloquear usuário");
        System.out.println("[5] Desbloquear usuário");
        System.out.println("[6] Sair");

        Scanner scannerAcao = new Scanner(System.in);

        int acao = scannerAcao.nextInt();

        switch (acao) {
            case 0:
                if (!admin.getTipoUsuario().equals("admin")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
                System.out.println("ID | Título | Autor | Categoria");

                for (Obra obra: obras) {
                    System.out.println(obra.getId() + " | " + obra.getTitulo() + " | " + obra.getAutor() + " | " + checaCategoria(obra.getCategoria()) + " | " + checaEstadoObra(obra));
                }
                break;
            case 1:
                if (!admin.getTipoUsuario().equals("admin")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
                System.out.println("Para adicionar um novo funcionário forneça os seguintes dados:");
                System.out.println("Nome:");

                Scanner scannerNomeAdicao = new Scanner(System.in);

                String nomeAdicao = scannerNomeAdicao.nextLine();

                System.out.println("Senha:");

                Scanner scannerSenhaAdicao = new Scanner(System.in);

                String senhaAdicao = scannerSenhaAdicao.nextLine();

                funcionarios.add(new Funcionario(nomeAdicao, senhaAdicao));
                break;
            case 2:
                if (!admin.getTipoUsuario().equals("admin")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
                System.out.println("Para editar um funcionário forneça os seguintes dados:");
                System.out.println("ID:");

                Scanner scannerIDEdicao = new Scanner(System.in);

                int idEdicao = scannerIDEdicao.nextInt();

                System.out.println("Nome:");

                Scanner scannerNomeEdicao = new Scanner(System.in);

                String nomeEdicao = scannerNomeEdicao.nextLine();

                System.out.println("Senha:");

                Scanner scannerSenhaEdicao = new Scanner(System.in);

                String senhaEdicao = scannerSenhaEdicao.nextLine();

                for (Funcionario funcionario : funcionarios) {
                    if (funcionario.getId() == idEdicao) {
                        funcionario.setNome(nomeEdicao);
                        funcionario.setSenha(senhaEdicao);
                    }
                }
                break;
            case 3:
                if (!admin.getTipoUsuario().equals("admin")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
                System.out.println("Para remover um funcionário forneça os seguintes dados:");
                System.out.println("ID:");

                Scanner scannerIDRemocao = new Scanner(System.in);

                int idRemocao = scannerIDRemocao.nextInt();

                funcionarios.remove(idRemocao);
                break;
            case 4:
                if (!admin.getTipoUsuario().equals("admin")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
                System.out.println("Para bloquear um usuário forneça os seguintes dados:");
                System.out.println("ID:");

                Scanner scannerIDBloqueio = new Scanner(System.in);

                int idBloqueio = scannerIDBloqueio.nextInt();

                admin.bloqueiaUsuario(idBloqueio, clientes);
                break;
            case 5:
                if (!admin.getTipoUsuario().equals("admin")) {
                    throw new Exception("Você não tem autorização para realizar essa ação.");
                }
                System.out.println("Para desbloquear um usuário forneça os seguintes dados:");
                System.out.println("ID:");

                Scanner scannerIDDesbloqueio = new Scanner(System.in);

                int idDesbloqueio = scannerIDDesbloqueio.nextInt();

                admin.desbloqueiaUsuario(idDesbloqueio, clientes);

                for (Cliente requisicao: requisicoes) {
                    if (requisicao.getId() == idDesbloqueio) {
                        requisicoes.remove(requisicao.getId());
                    }
                }
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

    private static boolean checaCliente(Integer opcao, String nome, String senha) {
        if (opcao == 1) {
            for (Cliente cliente: clientes) {
                System.out.println(cliente.getNome() + " - " + nome);

                if (cliente.getNome().equals(nome)) {
                    return true;
                }

            }
            return false;
        } else {
            for (Cliente cliente: clientes) {
                System.out.println(cliente.getNome() + "," + cliente.getSenha() + " - " + nome + "," + senha);

                if (cliente.getNome().equals(nome) && cliente.getSenha().equals(senha)) {
                    return true;
                }

            }
            return false;
        }
    }

    private static boolean checaFuncionario(String nome, String senha) {
        for (Funcionario funcionario: funcionarios) {
            if (funcionario.getNome().equals(nome) && funcionario.getSenha().equals(senha)) {
                return true;
            }
        }

        return false;
    }

    private static boolean checaAdmin(String nome, String senha) {
        for (Administrador admin: administradores) {
            if (admin.getNome().equals(nome) && admin.getSenha().equals(senha)) {
                return true;
            }
        }

        return false;
    }

    private static void leArquivo(String referencia) throws IOException {
        BufferedReader bufferLeitura = new BufferedReader(new FileReader(referencia));

        String linha = "";

        System.out.println("começou a leitura");

        while (true) {
            if (linha != null) {
                System.out.println(linha);
            } else {
                break;
            }

            linha = bufferLeitura.readLine();
        }

        bufferLeitura.close();

        System.out.println("terminou a leitura");
    }

    private static void escreveArquivo(String referencia, String conteudo) throws IOException {
        BufferedWriter bufferEscrita = new BufferedWriter(new FileWriter(referencia));

        bufferEscrita.append(conteudo + "\n");

        bufferEscrita.close();
    }
}
