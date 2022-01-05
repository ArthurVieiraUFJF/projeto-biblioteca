package Biblioteca;

import java.util.*;

public class Biblioteca {
    public static void main(String[] args) {
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

        if (checaUsuario(nome, senha)) {
            menu();
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

        if (checaUsuario(nome, senha)) {
            System.out.println("Essa conta já existe, você será direcionado para fazer o login.");
            login();
        } else {
            Usuario novoUsuario = new Usuario(nome, senha);

            // registra no arquivo usuarios.txt

            menu();
        }
    }

    private static void menu() {
        System.out.println("Menu da biblioteca:");
        System.out.println("[0] Ver obras");
        System.out.println("[1] Alugar obra");
        System.out.println("[2] Reservar obra");
        System.out.println("[3] Prolongar prazo");
        System.out.println("[4] Requisitar desbloqueio");

        Scanner scannerAcao = new Scanner(System.in);

        int acao = scannerAcao.nextInt();

        switch (acao) {
            case 0:
                // mostra obras
                break;
            case 1:
                // aluga obra
                break;
            case 2:
                // reserva obra
                break;
            case 3:
                // prolonga prazo
                break;
            case 4:
                // requisita desbloqueio
                break;
            default:
                System.out.println("Ação inválida, por favor insira um número válido!");
                menu();
                break;
        }
    }

    private static boolean checaUsuario(String nome, String senha) {
        // checa o arquivo usuarios.txt pelo nome e senha informados

        // caso ache -> return true;

        // caso não ache -> return false;
    }
}
