package Biblioteca;

public class Obra {
    private static long contador = 0;
    private long id;
    private String titulo;
    private String autor;
    private int categoria;
    private boolean reservada = false;
    private boolean alugada = false;
    private int horasIndisponivel = 0;

    protected Obra(String tituloObra, String autorObra, int categoriaObra) {
        id = contador;
        contador++;

        titulo = tituloObra;
        autor = autorObra;
        categoria = categoriaObra;
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

    protected int getHorasIndisponivel() {
        return horasIndisponivel;
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

    protected void setHorasIndisponivel(int horas) {
        horasIndisponivel = horas;
    }

    protected boolean checaDisponivel() {
        if (alugada || reservada) {
            return false;
        }

        return true;
    }

    protected void alugar() {
        alugada = true;
        horasIndisponivel = 168;
    }

    protected void reservar() {
        reservada = true;
        horasIndisponivel = 48;
    }

    protected void devolverOuCancelar() {
        alugada = false;
        reservada = false;
        horasIndisponivel = 0;
    }
}
