package Biblioteca;

import java.util.*;

public class Usuario {
    private static long contador = 0;
    private long id;
    private String nome;
    private String senha;
    private long aluguel = -1;
    private long reserva = -1;
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

    protected long getAluguel() {
        return aluguel;
    }

    protected long getReserva() {
        return reserva;
    }

    protected boolean possuiAluguel() {
        if(aluguel >= 0) {
            return true;
        }

        return false;
    }

    protected boolean possuiReserva() {
        if(reserva >= 0) {
            return true;
        }

        return false;
    }

    protected void bloquear() {
        bloqueado = true;
    }

    protected void desbloquear() {
        bloqueado = false;
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

    protected void alugarObra(long idObra, List<Obra> obras) {
        boolean aluguelFeito = false;
        long idAluguel = 0;

        for (Obra obra: obras) {
            if (obra.getId() == idObra && !obra.isAlugada() && !obra.isReservada()) {
                obra.alugar();
                aluguelFeito = true;
                idAluguel = obra.getId();
            }
        }

        if (aluguelFeito) {
            aluguel = idAluguel;
            System.out.println("Obra alugada!");
        } else {
            System.out.println("ID inválido ou obra já alugada/reservada!");
        }
    }

    protected void reservarObra(long idObra, List<Obra> obras) {
        boolean reservaFeita = false;
        long idReserva = 0;

        for (Obra obra: obras) {
            if (obra.getId() == idObra && !obra.isAlugada() && !obra.isReservada()) {
                obra.reservar();
                reservaFeita = true;
                idReserva = obra.getId();
            }
        }

        if (reservaFeita) {
            reserva = idReserva;
            System.out.println("Obra reservada!");
        } else {
            System.out.println("ID inválido ou obra já alugada/reservada!");
        }
    }

    protected void prolongarPrazo(long idObra, long prorrogacao, List<Obra> obras) {
        for (Obra obra: obras) {
            if (obra.getId() == idObra) {
                obra.setDataFinal(prorrogacao);
            }
        }
    }

    protected void requisitarDesbloqueio() {

    }
}
