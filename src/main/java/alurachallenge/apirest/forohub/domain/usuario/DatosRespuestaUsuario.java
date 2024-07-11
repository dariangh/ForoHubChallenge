package alurachallenge.apirest.forohub.domain.usuario;

import alurachallenge.apirest.forohub.domain.perfil.Perfil;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DatosRespuestaUsuario(
                                    String nombre,

                                    String correoElectronico,

                                    Perfil perfil) {
}
