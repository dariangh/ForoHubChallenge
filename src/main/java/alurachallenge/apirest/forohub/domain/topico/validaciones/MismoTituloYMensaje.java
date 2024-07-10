package alurachallenge.apirest.forohub.domain.topico.validaciones;

import alurachallenge.apirest.forohub.domain.topico.DatosRegistradoTopico;
import alurachallenge.apirest.forohub.domain.topico.TopicoRepository;
import alurachallenge.apirest.forohub.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MismoTituloYMensaje implements ValidadorDeTopicos{
    @Autowired
    private TopicoRepository topicoRepository;
    public void validar(DatosRegistradoTopico datosRegistradoTopico){
        var titulo=datosRegistradoTopico.titulo();
        var mensaje= datosRegistradoTopico.mensaje();
        var mismoTituloYMensaje=topicoRepository.mismoMensaje(mensaje,titulo);

        if(mismoTituloYMensaje){
            throw new ValidacionDeIntegridad("Ya existe un topíco con el mismo mensaje o titulo iguales");
        }

    }
}
