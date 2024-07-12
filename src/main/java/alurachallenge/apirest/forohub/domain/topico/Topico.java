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

import java.time.LocalDateTime;
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

    private LocalDateTime fechaCreacion;
    private boolean status;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    @JsonManagedReference
    private Usuario usuario;//usuario tabla
    @ManyToOne
    @JoinColumn(name="curso_id")
    @JsonManagedReference
    private Curso curso;//curso tabla
    @OneToMany(mappedBy = "topico")
    @JsonBackReference
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
        if(datosActualizarTopico.titulo()!=null){
            this.titulo= datosActualizarTopico.titulo();

        }
        if(datosActualizarTopico.mensaje()!=null){
            this.mensaje = datosActualizarTopico.mensaje();


        }
        if(datosActualizarTopico.fechaCreacion()!=null){
            this.fechaCreacion=datosActualizarTopico.fechaCreacion();

        }
        if(datosActualizarTopico.idUsuario()!=null){
            this.usuario=usuario;

        }
        if(datosActualizarTopico.idCurso()!=null){
            this.curso=curso;

        }
        if(!datosActualizarTopico.status()){
            this.status= datosActualizarTopico.status();

        }




    }

    public void desactivaTopico() {
        this.status=false;
    }
}
