package Biblioteca;

import java.util.List;

public class Cliente extends Usuario {
    private long aluguel = -1;
    private long reserva = -1;
    private boolean bloqueado = false;

    @Override
    public String getTipoUsuario() {
        return "cliente";
    }

    protected Cliente(String nomeUsuario, String senhaUsuario) {
        super(nomeUsuario, senhaUsuario);
    }

    protected long getAluguel() {
        return aluguel;
    }

    protected long getReserva() {
        return reserva;
    }

    protected void setAluguel(long aluguelCliente) { aluguel = aluguelCliente; }

    protected void setReserva(long reservaCliente) { reserva = reservaCliente; }

    protected boolean isBloqueado() {
        return bloqueado;
    }

    protected void bloquear() {
        bloqueado = true;
    }

    protected void desbloquear() {
        bloqueado = false;
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
}
