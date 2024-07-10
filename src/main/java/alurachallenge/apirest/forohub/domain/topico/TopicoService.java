package alurachallenge.apirest.forohub.domain.topico;

import alurachallenge.apirest.forohub.domain.curso.Curso;
import alurachallenge.apirest.forohub.domain.curso.CursoRepository;
import alurachallenge.apirest.forohub.domain.topico.validaciones.ValidadorDeTopicos;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import alurachallenge.apirest.forohub.domain.usuario.UsuarioRepository;
import alurachallenge.apirest.forohub.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TopicoService {
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    List<ValidadorDeTopicos> validadorDeTopicosList;

    public DatosRespuestaTopico publicarTopico(DatosRegistradoTopico datosRegistradoTopico){
        var existUsuario = usuarioRepository.findById(datosRegistradoTopico.idUsuario())
                    .orElseThrow(() -> new ValidacionDeIntegridad("Usuario no encontrado"));
            var existCurso = cursoRepository.findById(datosRegistradoTopico.idCurso())
                    .orElseThrow(() -> new ValidacionDeIntegridad("curso no encontrado"));


        validadorDeTopicosList.forEach(v->v.validar(datosRegistradoTopico));
        var curso=cursoRepository.findById(datosRegistradoTopico.idCurso()).get();
        var usuario = usuarioRepository.findById(datosRegistradoTopico.idUsuario()).get();


        var topico = new Topico(null,datosRegistradoTopico.titulo(),
                datosRegistradoTopico.mensaje(),
                datosRegistradoTopico.fechaCreacion(),
                datosRegistradoTopico.status(), usuario, curso,null);
        topicoRepository.save(topico);
        return new DatosRespuestaTopico(topico);
    }
}
