package alurachallenge.apirest.forohub.controller;

import alurachallenge.apirest.forohub.domain.respuesta.*;
import alurachallenge.apirest.forohub.domain.topico.*;
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
@RequestMapping("/respuesta")
@SecurityRequirement(name = "bearer-key")
public class RespuestaController {
    @Autowired
    TopicoRepository topicoRepository;
    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    RespuestaRepository respuestaRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaDeRespuesta> newRespuesta(@RequestBody @Valid DatosRegistradoRespuesta datosRegistradoRespuesta, UriComponentsBuilder uriComponentsBuilder){

//        var response=topicoService.publicarTopico(datosRegistradoTopico);
//        return ResponseEntity.ok(response);
            Usuario usuario = usuarioRepository.findById(datosRegistradoRespuesta.idUsuario())
                    .orElseThrow(() -> new IllegalArgumentException("Usuario no encontrado"));
        Topico topico = topicoRepository.findById(datosRegistradoRespuesta.idTopico())
                    .orElseThrow(() -> new IllegalArgumentException("Topico no encontrado"));
           Respuesta respuesta= respuestaRepository.save(new Respuesta(datosRegistradoRespuesta, usuario, topico));
            DatosRespuestaDeRespuesta datosRespuestaDeRespuesta =
                    new DatosRespuestaDeRespuesta(respuesta.getId(),
                            respuesta.getMensaje(), respuesta.getTopico(),respuesta.getFechaCreacion(),respuesta.getUsuario()                   );
            URI url=uriComponentsBuilder.path("respuesta/{id}").buildAndExpand(topico.getId()).toUri();
            return  ResponseEntity.created(url).body(datosRespuestaDeRespuesta);
    }
    @GetMapping
    public ResponseEntity<Page<DatosListarRespuesta>> listarRespuesta(@PageableDefault(page = 0, size = 10) Pageable paginacion){
        return ResponseEntity.ok(respuestaRepository.findAll(paginacion).map(DatosListarRespuesta::new));
    }
    @PutMapping
    @Transactional
    public ResponseEntity actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datosActualizarRespuesta){
        Respuesta respuesta =respuestaRepository.getReferenceById(datosActualizarRespuesta.id());
        respuesta.actualizarInformacion(datosActualizarRespuesta);
        return ResponseEntity.ok(new DatosRespuestaDeRespuesta(datosActualizarRespuesta.id(),
                respuesta.getMensaje(), respuesta.getTopico(),respuesta.getFechaCreacion(),respuesta.getUsuario()));

    }
    @DeleteMapping("{id}")
    public ResponseEntity eliminarRespuesta(@PathVariable Long id){
        Respuesta respuesta= respuestaRepository.getReferenceById(id);
//        respuesta.desactivaTopico();
        System.out.println("desactivado topico");
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<DatosRespuestaDeRespuesta> retornarDatosRespuesta(@PathVariable Long id){
        Respuesta respuesta=respuestaRepository.getReferenceById(id);
//        respuesta.desactivaTopico();
        var datosRespuesta= new DatosRespuestaDeRespuesta(
                respuesta.getId(),
                respuesta.getMensaje(), respuesta.getTopico(),
                respuesta.getFechaCreacion(),respuesta.getUsuario());
        return ResponseEntity.ok(datosRespuesta);
    }
}
