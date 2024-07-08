package alurachallenge.apirest.forohub.controller;

import alurachallenge.apirest.forohub.domain.curso.Curso;
import alurachallenge.apirest.forohub.domain.curso.CursoRepository;
import alurachallenge.apirest.forohub.domain.curso.DatosListaCurso;
import alurachallenge.apirest.forohub.domain.curso.DatosRegistroCurso;
import alurachallenge.apirest.forohub.domain.perfil.DatosListaPerfil;
import alurachallenge.apirest.forohub.domain.perfil.DatosPerfil;
import alurachallenge.apirest.forohub.domain.perfil.Perfil;
import alurachallenge.apirest.forohub.domain.perfil.PerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/curso")
public class CursoController {
    @Autowired
    private CursoRepository cursoRepository;
    @PostMapping
    public void newCurso(@RequestBody @Valid DatosRegistroCurso datosRegistroCurso){
        cursoRepository.save(new Curso((datosRegistroCurso)));
    }
    @GetMapping
    public Page<DatosListaCurso> listar(@PageableDefault(page = 0, size = 10) Pageable paginacion) {
        return cursoRepository.findAll(paginacion).map(DatosListaCurso::new);
    }
}
