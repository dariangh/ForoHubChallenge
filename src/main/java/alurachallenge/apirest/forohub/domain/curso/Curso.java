package alurachallenge.apirest.forohub.domain.curso;
import alurachallenge.apirest.forohub.domain.categoria.Categoria;
import alurachallenge.apirest.forohub.domain.topico.Topico;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name="curso")
@Entity(name="Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    @OneToMany(mappedBy = "curso")
    @JsonBackReference
    private List<Topico> topico;

    public Curso(DatosRegistroCurso datosRegistroCurso) {
        this.nombre= datosRegistroCurso.nombre();
        this.categoria=datosRegistroCurso.categoria();
    }
}
