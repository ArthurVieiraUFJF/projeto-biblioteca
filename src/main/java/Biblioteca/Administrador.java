package Biblioteca;

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

    protected void criaObra() {

    }

    protected void removeObra() {

    }

    protected void editaObra() {

    }

    protected void cancelaPrazo() {

    }

    protected void bloqueiaUsuario() {

    }

    protected void desbloqueiaUsuario() {

    }
}
