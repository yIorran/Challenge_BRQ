package challenge.brq.usecase.exception.produto;

public class CategoriaNaoEncontradaParaAdicionarOuAtualizarProduto extends RuntimeException {

    public CategoriaNaoEncontradaParaAdicionarOuAtualizarProduto(String mensagem) {
        super(mensagem);
    }

}
