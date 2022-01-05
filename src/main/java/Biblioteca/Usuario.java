package Biblioteca;

public class Usuario {
    private static long contador = 0;
    private long id;
    private String nome;
    private String senha;
    private boolean possuiAluguel = false;
    private boolean possuiReserva = false;
    private boolean bloqueado = false;

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

    protected boolean possuiAluguel() {
        return possuiAluguel;
    }

    protected boolean possuiReserva() {
        return possuiReserva;
    }

    protected boolean isBloqueado() {
        return bloqueado;
    }

    protected void setNome(String nomeUsuario) {
        nome = nomeUsuario;
    }

    protected void setSenha(String senhaUsuario) {
        senha = senhaUsuario;
    }

    protected void setPossuiAluguel(boolean aluguel) {
        possuiAluguel = aluguel;
    }

    protected void setPossuiReserva(boolean reserva) {
        possuiReserva = reserva;
    }

    protected void reservarObra(long id) {

    }

    protected void alugarObra(long id) {

    }

    protected void prolongarPrazo(long id) {

    }

    protected void requisitarDesbloqueio() {

    }
}
