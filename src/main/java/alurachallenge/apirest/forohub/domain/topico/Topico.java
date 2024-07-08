package alurachallenge.apirest.forohub.domain.topico;

import alurachallenge.apirest.forohub.domain.curso.Curso;
import alurachallenge.apirest.forohub.domain.respuesta.Respuesta;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Table(name="topico")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensaje;
    private Date fechaCreacion;
    private boolean status;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    @JsonBackReference
    private Usuario usuario;//usuario tabla
    @ManyToOne
    @JoinColumn(name="curso_id")
    @JsonBackReference
    private Curso curso;//curso tabla
    @OneToMany(mappedBy = "topico")
    @JsonManagedReference
    private List<Respuesta> respuesta;

    public Topico(DatosRegistradoTopico datosRegistradoTopico, Usuario usuario, Curso curso) {
        this.titulo= datosRegistradoTopico.titulo();
        this.mensaje = datosRegistradoTopico.mensaje();
        this.fechaCreacion=datosRegistradoTopico.fechaCreacion();
        this.usuario=usuario;
        this.curso=curso;
        this.status= Boolean.valueOf(datosRegistradoTopico.status());


    }

    public void actualizarInformacion(DatosActualizarTopico datosActualizarTopico) {
        this.titulo= datosActualizarTopico.titulo();
        this.mensaje = datosActualizarTopico.mensaje();
        this.fechaCreacion=datosActualizarTopico.fechaCreacion();
        this.usuario=usuario;
        this.curso=curso;
        this.status= datosActualizarTopico.status();
    }

    public void desactivaTopico() {
        this.status=false;
    }
}
