package alurachallenge.apirest.forohub.domain.curso;

import alurachallenge.apirest.forohub.domain.categoria.Categoria;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRegistroCurso(
        @NotBlank
        String nombre,
        @NotNull
        Categoria categoria

) {
}
