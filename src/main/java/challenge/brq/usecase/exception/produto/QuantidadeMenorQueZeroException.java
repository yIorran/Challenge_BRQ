package challenge.brq.usecase.exception.produto;

public class QuantidadeMenorQueZeroException extends RuntimeException {

    public QuantidadeMenorQueZeroException(String mensagem) {
        super(mensagem);
    }

}
