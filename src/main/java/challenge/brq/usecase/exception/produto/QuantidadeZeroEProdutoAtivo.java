package challenge.brq.usecase.exception.produto;

public class QuantidadeZeroEProdutoAtivo extends RuntimeException {

    public QuantidadeZeroEProdutoAtivo(String mensagem) {
        super(mensagem);
    }

}
