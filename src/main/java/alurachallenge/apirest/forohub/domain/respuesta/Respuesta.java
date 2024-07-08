package alurachallenge.apirest.forohub.domain.respuesta;

import alurachallenge.apirest.forohub.domain.topico.Topico;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    @JsonBackReference
    private Topico topico;
    private Date fechaCreacion;
    @ManyToOne
    @JoinColumn(name="usuario_id")
    @JsonBackReference
    private Usuario usuario;

    private String solucion;

}
