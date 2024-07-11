package alurachallenge.apirest.forohub.controller;

import alurachallenge.apirest.forohub.domain.curso.*;
import alurachallenge.apirest.forohub.domain.perfil.*;
import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/curso")
@SecurityRequirement(name = "bearer-key")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;
    @PostMapping
    public ResponseEntity<DatosRespuestaCurso> newCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso, UriComponentsBuilder uriComponentsBuilder){
        Curso curso=cursoRepository.save(new Curso((datosRegistroCurso)));
        DatosRespuestaCurso datosRespuestaCurso= new DatosRespuestaCurso(curso.getNombre(),curso.getCategoria());
        URI url=uriComponentsBuilder.path("medicos/{id}").buildAndExpand(curso.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaCurso);
    }
    @GetMapping
    public Page<DatosListaCurso> listar(@PageableDefault(page = 0, size = 10) Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosListaCurso::new);
    }
    @DeleteMapping("{id}")
    public ResponseEntity eliminarCurso(@PathVariable Long id){
        cursoRepository.deleteById(id);

        System.out.println("desactivado topico");
        return ResponseEntity.noContent().build();
    }
    @GetMapping("{id}")
    public ResponseEntity<DatosRespuestaCurso> retornarDatosCurso(@PathVariable Long id){
        Curso curso=cursoRepository.getReferenceById(id);
//        topico.desactivaTopico();
        var datosCurso= new DatosRespuestaCurso(curso.getNombre(), curso.getCategoria());
        return ResponseEntity.ok(datosCurso);
    }
}
