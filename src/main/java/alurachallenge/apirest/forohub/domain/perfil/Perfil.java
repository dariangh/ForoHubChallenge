package alurachallenge.apirest.forohub.domain.perfil;

import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="perfil")
@Entity(name="Perfil")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Perfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @OneToMany(mappedBy = "perfil")
    @JsonBackReference
    private List<Usuario> usuario;

    public Perfil(DatosPerfil datosPerfil) {
        this.nombre= datosPerfil.nombre();
    }




    public void actualizarInformacion(DatosActualizarPerfil  datosActualizarPerfil) {
        if(datosActualizarPerfil.nombre()!=null){
            this.nombre= datosActualizarPerfil.nombre();
        }
    }
}
