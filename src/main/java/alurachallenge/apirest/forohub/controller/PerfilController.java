package alurachallenge.apirest.forohub.controller;

import alurachallenge.apirest.forohub.domain.perfil.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/perfil")
public class PerfilController {
    @Autowired
    private PerfilRepository perfilRepository;
    @PostMapping
    public void newPerfil(@RequestBody @Valid DatosPerfil datosPerfil){
        perfilRepository.save(new Perfil((datosPerfil)));
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
}
