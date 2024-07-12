package alurachallenge.apirest.forohub.domain.respuesta;

import alurachallenge.apirest.forohub.domain.curso.Curso;
import alurachallenge.apirest.forohub.domain.topico.DatosActualizarTopico;
import alurachallenge.apirest.forohub.domain.topico.DatosRegistradoTopico;
import alurachallenge.apirest.forohub.domain.topico.Topico;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
@Table(name="respuesta")
@Entity(name="Respuesta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @ManyToOne
    @JoinColumn(name="topico_id")
    @JsonManagedReference
    private Topico topico;

    private LocalDateTime fechaCreacion;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    @JsonManagedReference
    private Usuario usuario;

//    private String solucion;


    public Respuesta(DatosRegistradoRespuesta datosRegistradoRespuesta, Usuario usuario, Topico topico) {
        this.mensaje = datosRegistradoRespuesta.mensaje();
        this.fechaCreacion=datosRegistradoRespuesta.fechaCreacion();
//        this.solucion= datosRegistradoRespuesta.solucion();
        this.usuario=usuario;
        this.topico=topico;
    }
    public void actualizarInformacion(DatosActualizarRespuesta datosActualizarRespuesta) {

        if (datosActualizarRespuesta.mensaje() != null) {
            this.mensaje = datosActualizarRespuesta.mensaje();


        }
        if (datosActualizarRespuesta.fechaCreacion() != null) {
            this.fechaCreacion = datosActualizarRespuesta.fechaCreacion();

        }
        if (datosActualizarRespuesta.idUsuario() != null) {
            this.usuario = usuario;

        }
        if (datosActualizarRespuesta.idTopico() != null) {
            this.topico = topico;

        }
//        if (datosActualizarRespuesta.solucion() != null) {
//            this.solucion = datosActualizarRespuesta.solucion();
//
//
//        }
    }

}
