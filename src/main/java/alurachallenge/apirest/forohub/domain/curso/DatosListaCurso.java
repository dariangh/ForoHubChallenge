package alurachallenge.apirest.forohub.domain.curso;

import alurachallenge.apirest.forohub.domain.categoria.Categoria;
import alurachallenge.apirest.forohub.domain.perfil.Perfil;

public record DatosListaCurso(
        String nombre,
        Categoria categoria
) {
    public DatosListaCurso(Curso curso) {
        this(
                curso.getNombre(),
                curso.getCategoria()

        );
    }
}
