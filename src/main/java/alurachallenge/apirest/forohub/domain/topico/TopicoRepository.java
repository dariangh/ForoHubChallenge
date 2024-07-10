package alurachallenge.apirest.forohub.domain.topico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TopicoRepository extends JpaRepository<Topico, Long> {
    @Query("""
            select count(t) > 0 from Topico t
            where t.mensaje = :mensaje OR
            t.titulo = :titulo
           """)
    Boolean mismoMensaje(String mensaje, String titulo);
}
