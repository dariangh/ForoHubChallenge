package alurachallenge.apirest.forohub.controller;

import alurachallenge.apirest.forohub.domain.usuario.DatosAutenticacionUsuario;
import alurachallenge.apirest.forohub.domain.usuario.Usuario;
import alurachallenge.apirest.forohub.infra.security.DatosJWTToken;
import alurachallenge.apirest.forohub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacionController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity autenticacionUsuario(@RequestBody @Valid DatosAutenticacionUsuario datosAutenticacionUsuario){
        Authentication authToken= new UsernamePasswordAuthenticationToken(datosAutenticacionUsuario.correoElectronico(),
                datosAutenticacionUsuario.contrasena());
        var UsuarioAutenticado =authenticationManager.authenticate(authToken);
        var JWToken = tokenService.generarToken((Usuario) UsuarioAutenticado.getPrincipal());
        return  ResponseEntity.ok((new DatosJWTToken(JWToken)));
    }
}

