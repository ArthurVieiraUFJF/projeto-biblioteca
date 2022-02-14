package Biblioteca;

import java.util.*;

public class Administrador extends Usuario {
    @Override
    public String getTipoUsuario() {
        return "administrador";
    }

    protected Administrador(String nomeUsuario, String senhaUsuario) {
        super(nomeUsuario, senhaUsuario);
    }

    protected void bloqueiaUsuario(long id, List<Cliente> clientes) {
        for (Cliente cliente: clientes) {
            if (cliente.getId() == id && !cliente.isBloqueado()) {
                cliente.bloquear();
                System.out.println("Usuário bloqueado!");
                return;
            }
        }

        System.out.println("O usuário em questão já está bloqueado!");
    }

    protected void desbloqueiaUsuario(long id, List<Cliente> clientes) {
        for (Cliente cliente: clientes) {
            if (cliente.getId() == id && cliente.isBloqueado()) {
                cliente.desbloquear();
                System.out.println("Usuário desbloqueado!");
                return;
            }
        }

        System.out.println("O usuário em questão não está bloqueado!");
    }
}
