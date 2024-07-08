package alurachallenge.apirest.forohub.domain.usuario;

import alurachallenge.apirest.forohub.domain.perfil.Perfil;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public record DatosListaUsuario(
        Long id,
        String nombre,
        String correoElectronico,


        Perfil perfil
) {
    public DatosListaUsuario(Usuario usuario) {
        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico(),

                usuario.getPerfil() // Pasando el objeto completo Perfil
        );
    }



}
