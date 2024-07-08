package alurachallenge.apirest.forohub.domain.perfil;

import alurachallenge.apirest.forohub.domain.categoria.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosPerfil(

        @NotBlank
        String nombre

) {
}
