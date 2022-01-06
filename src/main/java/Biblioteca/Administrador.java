package Biblioteca;

import java.util.*;

public class Administrador {
    private static long contador = 0;
    private long id;
    private String nome;
    private String senha;

    protected Administrador(String nomeAdministrador, String senhaAdministrador) {
        id = contador;
        contador++;

        nome = nomeAdministrador;
        senha = senhaAdministrador;
    }

    protected long getId() {
        return id;
    }

    protected String getNome() {
        return nome;
    }

    protected String getSenha() {
        return senha;
    }

    protected void setNome(String nomeUsuario) {
        nome = nomeUsuario;
    }

    protected void setSenha(String senhaUsuario) {
        senha = senhaUsuario;
    }

    protected void criaObra(String titulo, String autor, int categoria,List<Obra> obras ) {
        obras.add(new Obra(titulo, autor, categoria));

        System.out.println("Obra adicionada ao acervo!");
    }

    protected void removeObra(int id, List<Obra> obras ) {
        obras.remove(id);

        System.out.println("Obra removida do acervo!");
    }

    protected void editaObra(int id, String titulo, String autor, int categoria, List<Obra> obras) {
        for (Obra obra: obras) {
            if (obra.getId() == id) {
                obra.setTitulo(titulo);
                obra.setAutor(autor);
                obra.setCategoria(categoria);
            }
        }

        System.out.println("Obra atualizada!");
    }

    protected void cancelaPrazo() {

    }

    protected void bloqueiaUsuario(long id, List<Usuario> usuarios) {
        for (Usuario usuario: usuarios) {
            if (usuario.getId() == id && !usuario.isBloqueado()) {
                usuario.bloquear();
                System.out.println("Usuário bloqueado!");
                return;
            }
        }

        System.out.println("O usuário em questão já está bloqueado!");
    }

    protected void desbloqueiaUsuario(long id, List<Usuario> usuarios) {
        for (Usuario usuario: usuarios) {
            if (usuario.getId() == id && usuario.isBloqueado()) {
                usuario.desbloquear();
                System.out.println("Usuário desbloqueado!");
                return;
            }
        }

        System.out.println("O usuário em questão não está bloqueado!");
    }
}
