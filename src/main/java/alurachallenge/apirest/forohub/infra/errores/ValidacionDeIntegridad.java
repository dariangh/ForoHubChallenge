package alurachallenge.apirest.forohub.infra.errores;

public class ValidacionDeIntegridad extends RuntimeException{
    public ValidacionDeIntegridad(String s) {
        super(s);
    }
}
