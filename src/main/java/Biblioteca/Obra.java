package Biblioteca;

import java.util.Date;

public class Obra {
    private static long contador = 0;
    private long id;
    private String titulo;
    private String autor;
    private int categoria;
    private boolean reservada;
    private boolean alugada;
    private long dataTiragem;
    private long dataFinal;

    //LEGENDA DAS CATEGORIAS
    //1 - AVENTURA
    //2 - COMÉDIA
    //3 - ROMANCE
    //4 - FICÇÃO
    //5 - MISTÉRIO
    //6 - INFANTIL

    protected Obra(String tituloObra, String autorObra, int categoriaObra, boolean reservadaObra, boolean alugadaObra, long dataTiragemObra, long dataFinalObra) {
        id = contador;
        contador++;

        titulo = tituloObra;
        autor = autorObra;
        categoria = categoriaObra;
        reservada = reservadaObra;
        alugada = alugadaObra;
        dataTiragem = dataTiragemObra;
        dataFinal = dataFinalObra;
    }

    protected long getId() {
        return id;
    }

    protected String getTitulo() {
        return titulo;
    }

    protected String getAutor() {
        return autor;
    }

    protected int getCategoria() {
        return categoria;
    }

    protected long getDataTiragem() {
        return dataTiragem;
    }

    protected long getDataFinal() {
        return dataFinal;
    }

    protected void setTitulo(String tituloObra) {
        titulo = tituloObra;
    }

    protected void setAutor(String autorObra) {
        autor = autorObra;
    }

    protected void setCategoria(int categoriaObra) {
        categoria = categoriaObra;
    }

    protected void setDataFinal(long data) { dataFinal = dataFinal + data; }

    protected void checaDisponibilidade() {
        Date dataAtual = new Date();

        long diferenca = dataFinal - dataAtual.getTime();

        if (diferenca <= 0) {
            devolverOuCancelar();
            System.out.println("Disponível");
            return;
        }

        long segundos = diferenca / 1000;

        long minutos = segundos / 60;

        long horas = minutos / 60;

        long dias = horas / 24;

        horas = horas % 24;

        minutos = minutos % 60;

        segundos = segundos % 60;

        System.out.println("Dias: " + dias + " | Horas: " + horas + " | Minutos: " + minutos + " | Segundos: " + segundos);
    }

    protected boolean isReservada() {
        if (reservada) {
            return true;
        }

        return false;
    }

    protected boolean isAlugada() {
        if (alugada) {
            return true;
        }

        return false;
    }

    protected void alugar() {
        alugada = true;

        Date data = new Date();

        dataTiragem = data.getTime();

        dataFinal = dataTiragem + 604800000;
    }

    protected void reservar() {
        reservada = true;

        Date data = new Date();

        dataTiragem = data.getTime();

        dataFinal = dataTiragem + 172800000;
    }

    protected void devolverOuCancelar() {
        alugada = false;
        reservada = false;
    }
}
