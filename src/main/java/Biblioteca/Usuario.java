package Biblioteca;

public abstract class Usuario implements Permissao {
    private static long contador = 0;
    private long id;
    private String nome;
    private String senha;

    protected Usuario(String nomeUsuario, String senhaUsuario) {
        id = contador;
        contador++;

        nome = nomeUsuario;
        senha = senhaUsuario;
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
}
