package alurachallenge.apirest.forohub.controller;

import alurachallenge.apirest.forohub.domain.perfil.*;
import alurachallenge.apirest.forohub.domain.topico.DatosRespuestaTopico;
import alurachallenge.apirest.forohub.domain.topico.Topico;
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
@RequestMapping("/perfil")
@SecurityRequirement(name = "bearer-key")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaPerfil> newPerfil(@RequestBody @Valid DatosPerfil datosPerfil, UriComponentsBuilder uriComponentsBuilder){
        Perfil perfil=perfilRepository.save(new Perfil((datosPerfil)));
        DatosRespuestaPerfil datosRespuestaPerfil=new DatosRespuestaPerfil(perfil.getNombre());
        URI url=uriComponentsBuilder.path("perfil/{id}").buildAndExpand(perfil.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaPerfil);
    }
    @GetMapping
    public Page<DatosListaPerfil> listar(@PageableDefault(page = 0, size = 10, sort = {"nombre"}) Pageable paginacion) {
        return perfilRepository.findAll(paginacion).map(DatosListaPerfil::new);
    }
    @PutMapping
    @Transactional
    public void actualizar(@RequestBody @Valid DatosActualizarPerfil datosActualizarPerfil){
        var perfil =perfilRepository.getReferenceById(datosActualizarPerfil.id());
        perfil.actualizarInformacion(datosActualizarPerfil);
    }
    @DeleteMapping("{id}")
    public ResponseEntity eliminarPerfil(@PathVariable Long id){
        perfilRepository.deleteById(id);

        System.out.println("desactivado topico");
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<DatosRespuestaPerfil> retornarDatosPerfil(@PathVariable Long id){
        Perfil perfil=perfilRepository.getReferenceById(id);
//        topico.desactivaTopico();
        var datosPerfil= new DatosRespuestaPerfil(perfil.getNombre());
        return ResponseEntity.ok(datosPerfil);
    }
}
