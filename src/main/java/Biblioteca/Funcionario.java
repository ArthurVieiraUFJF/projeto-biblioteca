package Biblioteca;

import java.util.List;

public class Funcionario extends Usuario {
    @Override
    public String getTipoUsuario() {
        return "funcionario";
    }

    protected Funcionario(String nomeUsuario, String senhaUsuario) {
        super(nomeUsuario, senhaUsuario);
    }

    protected void criaObra(String titulo, String autor, int categoria, List<Obra> obras ) {
        obras.add(new Obra(titulo, autor, categoria, false, false, 0, 0));

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
}
