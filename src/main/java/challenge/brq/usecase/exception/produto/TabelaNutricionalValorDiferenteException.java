package challenge.brq.usecase.exception.produto;

public class TabelaNutricionalValorDiferenteException extends RuntimeException {
    public TabelaNutricionalValorDiferenteException(String mensagem) {
        super(mensagem);
    }
}
