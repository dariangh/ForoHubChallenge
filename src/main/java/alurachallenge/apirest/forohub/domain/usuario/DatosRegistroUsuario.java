package alurachallenge.apirest.forohub.domain.usuario;

import alurachallenge.apirest.forohub.domain.perfil.DatosPerfil;
import alurachallenge.apirest.forohub.domain.perfil.Perfil;
import alurachallenge.apirest.forohub.domain.respuesta.Respuesta;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DatosRegistroUsuario(
        @NotBlank
         String nombre,
         @NotBlank
         @Email
         String correoElectronico,
        @NotBlank
         String contrasena,
        @NotNull
        @Valid
         Long perfilId

) {
}
