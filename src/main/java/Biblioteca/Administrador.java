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

    }

    protected void removeObra(int id, List<Obra> obras ) {

        obras.remove(id);
        
    }

    protected void editaObra(String titulo, String autor,int id, int categoria,List<Obra> obras) {
        
        obras.set(id, new Obra(titulo, autor, categoria));

    }

    protected void cancelaPrazo() {

    }

    protected void bloqueiaUsuario() {

    }

    protected void desbloqueiaUsuario() {

    }
}
