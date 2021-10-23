package challenge.brq.usecase.exception.produto;

public class ProdutoPorIDNaoEncontrado extends RuntimeException{

    public ProdutoPorIDNaoEncontrado(String mensagem){
        super(mensagem);
    }
}
