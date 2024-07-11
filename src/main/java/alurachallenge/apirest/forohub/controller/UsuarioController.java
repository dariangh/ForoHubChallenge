package alurachallenge.apirest.forohub.controller;

import alurachallenge.apirest.forohub.domain.perfil.DatosListaPerfil;
import alurachallenge.apirest.forohub.domain.perfil.DatosRegistradoPerfil;
import alurachallenge.apirest.forohub.domain.perfil.Perfil;
import alurachallenge.apirest.forohub.domain.perfil.PerfilRepository;
import alurachallenge.apirest.forohub.domain.topico.DatosRespuestaTopico;
import alurachallenge.apirest.forohub.domain.topico.Topico;
import alurachallenge.apirest.forohub.domain.usuario.*;
import alurachallenge.apirest.forohub.infra.errores.ValidacionDeIntegridad;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/usuario")
@SecurityRequirement(name = "bearer-key")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaUsuario> newUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistradoUsuario, UriComponentsBuilder uriComponentsBuilder){
        Perfil perfil = perfilRepository.findById(datosRegistradoUsuario.perfilId())
                .orElseThrow(() -> new ValidacionDeIntegridad("Perfil no encontrado"));
        Usuario usuario=usuarioRepository.save(new Usuario(datosRegistradoUsuario, perfil));
        DatosRespuestaUsuario datosRespuestaUsuario=new DatosRespuestaUsuario(usuario.getNombre(),usuario.getCorreoElectronico(),usuario.getPerfil());
        URI url=uriComponentsBuilder.path("usuario/{id}").buildAndExpand(usuario.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaUsuario);
    }
    @GetMapping
    public Page<DatosListaUsuario> listarUsuario(@PageableDefault(page = 0, size = 10) Pageable paginacion) {
        return usuarioRepository.findAll(paginacion).map(DatosListaUsuario::new);
    }
    @DeleteMapping("{id}")
    public ResponseEntity eliminarUsuario(@PathVariable Long id){
        usuarioRepository.deleteById(id);
//        Topico topico= topicoRepository.getReferenceById(id);
//        topico.desactivaTopico();
//        System.out.println("desactivado topico");
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<DatosRespuestaUsuario> retornarDatosUsuario(@PathVariable Long id){
        Usuario usuario=usuarioRepository.getReferenceById(id);
//        usuario.desactivaTopico();
        var datosUsuario= new DatosRespuestaUsuario(usuario.getNombre(),
                usuario.getCorreoElectronico(), usuario.getPerfil());
        return ResponseEntity.ok(datosUsuario);
    }
}


