package challenge.brq.usecase.exception.produto;

public class CategoriaInexistenteParaAdicionarProdutoException extends RuntimeException {

    public CategoriaInexistenteParaAdicionarProdutoException(String mensagem) {
        super(mensagem);
    }

}
