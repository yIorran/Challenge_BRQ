package challenge.brq.usecase.exception.produto;

public class PrecoMenorOuIgualAZeroException extends RuntimeException {
    public PrecoMenorOuIgualAZeroException(String mensagem) {
        super(mensagem);
    }
}
