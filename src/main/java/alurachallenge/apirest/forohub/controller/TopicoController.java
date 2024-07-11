package alurachallenge.apirest.forohub.controller;

import alurachallenge.apirest.forohub.domain.curso.Curso;
import alurachallenge.apirest.forohub.domain.curso.CursoRepository;
import alurachallenge.apirest.forohub.domain.perfil.DatosActualizarPerfil;
import alurachallenge.apirest.forohub.domain.perfil.Perfil;
import alurachallenge.apirest.forohub.domain.perfil.PerfilRepository;
import alurachallenge.apirest.forohub.domain.topico.*;
import alurachallenge.apirest.forohub.domain.usuario.DatosListaUsuario;
import alurachallenge.apirest.forohub.domain.usuario.DatosRegistroUsuario;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import alurachallenge.apirest.forohub.domain.usuario.UsuarioRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topico")
@SecurityRequirement(name = "bearer-key")
public class TopicoController{
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private TopicoService topicoService;


        @PostMapping
        public ResponseEntity newTopico(@RequestBody @Valid DatosRegistradoTopico datosRegistradoTopico, UriComponentsBuilder uriComponentsBuilder){

            var response=topicoService.publicarTopico(datosRegistradoTopico);
            return ResponseEntity.ok(response);
//            Usuario usuario = usuarioRepository.findById(datosRegistradoTopico.idUsuario())
//                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
//            Curso curso = cursoRepository.findById(datosRegistradoTopico.idCurso())
//                    .orElseThrow(() -> new IllegalArgumentException("curso no encontrado"));
//           Topico topico= topicoRepository.save(new Topico(datosRegistradoTopico, usuario, curso));
//            DatosRespuestaTopico datosRspuestaTopico =
//                    new DatosRespuestaTopico((topico.getId()),
//                    topico.getTitulo(),topico.getMensaje(), topico.getFechaCreacion(),topico.isStatus(),topico.getUsuario(),
//                    topico.getCurso());
//            URI url=uriComponentsBuilder.path("topicos/{id}").buildAndExpand(topico.getId()).toUri();
//            return  ResponseEntity.created(url).body(datosRspuestaTopico);
        }
    @GetMapping
    public ResponseEntity<Page<DatosListarTopico>> listarTopico(@PageableDefault(page = 0, size = 10) Pageable paginacion) {
        return ResponseEntity.ok(topicoRepository.findAll(paginacion).map(DatosListarTopico::new));
    }
    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        Topico topico =topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.actualizarInformacion(datosActualizarTopico);
        return ResponseEntity.ok(new DatosRespuestaTopico((topico.getId()),
                topico.getTitulo(),topico.getMensaje(), topico.getFechaCreacion(),topico.isStatus(),topico.getUsuario(),
                topico.getCurso()));

    }
    @DeleteMapping("{id}")
    public ResponseEntity eliminarTopico(@PathVariable Long id){
            Topico topico= topicoRepository.getReferenceById(id);
            topico.desactivaTopico();
        System.out.println("desactivado topico");
            return ResponseEntity.noContent().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<DatosRespuestaTopico> retornarDatosTopico(@PathVariable Long id){
            Topico topico=topicoRepository.getReferenceById(id);
            topico.desactivaTopico();
            var datosTopico= new DatosRespuestaTopico((topico.getId()),
                    topico.getTitulo(),topico.getMensaje(), topico.getFechaCreacion(),topico.isStatus(),topico.getUsuario(),
                    topico.getCurso());
            return ResponseEntity.ok(datosTopico);
    }
}

