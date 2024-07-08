package alurachallenge.apirest.forohub.domain.perfil;

public record DatosListaPerfil(
         Long id,
         String nombre
) {
    public DatosListaPerfil(Perfil perfil) {
        this(perfil.getId(),perfil.getNombre());
    }
}
