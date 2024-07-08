package alurachallenge.apirest.forohub.controller;

import alurachallenge.apirest.forohub.domain.perfil.DatosListaPerfil;
import alurachallenge.apirest.forohub.domain.perfil.DatosRegistradoPerfil;
import alurachallenge.apirest.forohub.domain.perfil.Perfil;
import alurachallenge.apirest.forohub.domain.perfil.PerfilRepository;
import alurachallenge.apirest.forohub.domain.usuario.DatosListaUsuario;
import alurachallenge.apirest.forohub.domain.usuario.DatosRegistroUsuario;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import alurachallenge.apirest.forohub.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private PerfilRepository perfilRepository;

    @PostMapping
    public void newUsuario(@RequestBody @Valid DatosRegistroUsuario datosRegistradoUsuario){
        Perfil perfil = perfilRepository.findById(datosRegistradoUsuario.perfilId())
                .orElseThrow(() -> new IllegalArgumentException("Perfil no encontrado"));
        usuarioRepository.save(new Usuario(datosRegistradoUsuario, perfil));
    }
    @GetMapping
    public Page<DatosListaUsuario> listarUsuario(@PageableDefault(page = 0, size = 10) Pageable paginacion) {
        return usuarioRepository.findAll(paginacion).map(DatosListaUsuario::new);
    }

}
