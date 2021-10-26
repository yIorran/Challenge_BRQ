package challenge.brq.usecase.utils;

import challenge.brq.usecase.exception.produto.*;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public static void verificarSePorcentagemMaiorQueZero(ProdutoRequestDomain produtoRequestDomain){
        if(produtoRequestDomain.getPorcentagem() == null && produtoRequestDomain.getProdutoOfertado() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if(produtoRequestDomain.getPorcentagem() == null || produtoRequestDomain.getProdutoOfertado() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if(produtoRequestDomain.getPorcentagem() <= 0 && produtoRequestDomain.getProdutoOfertado() == true){
            throw new PorcentagemMaiorQueZeroException("O produto não pode ser ofertado se a porcentage for menor ou igual a 0 "
                    + "porcentagem atual: " + produtoRequestDomain.getPorcentagem());
        }
    }

    public static void verificarSeStatusDoProdutoEAtivo(ProdutoRequestDomain produtoRequestDomain){
        if(produtoRequestDomain.getQuantidadeProduto() == null && produtoRequestDomain.getProdutoAtivo() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if(produtoRequestDomain.getQuantidadeProduto() == null || produtoRequestDomain.getProdutoAtivo() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if(produtoRequestDomain.getQuantidadeProduto() == 0 && produtoRequestDomain.getProdutoAtivo() == true){
            throw new QuantidadeZeroEProdutoAtivo("Produto não pode ser ativo se a quantidade for igual a 0");
        }
    }

    public static void verificarSeCategoriaExisteParaAdicionar(Object id) {
        if (id == null) {
            throw new CategoriaNaoEncontradaParaAdicionarOuAtualizarProduto("Categoria não encontrada para fazer adição");
        }
    }
    public static void verificarSeCategoriaExisteParaAtualizarParcial(Object id) {
        if (id == null) {
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (id == null) {
            throw new CategoriaNaoEncontradaParaAdicionarOuAtualizarProduto("Categoria não encontrada para fazer adição");
        }
    }

    public static void verificarSeOfertadoAtivoEStatusAtivo(ProdutoRequestDomain produtoRequestDomain) {
        if(produtoRequestDomain.getProdutoAtivo() == null && produtoRequestDomain.getProdutoOfertado() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if(produtoRequestDomain.getProdutoAtivo() == null || produtoRequestDomain.getProdutoOfertado() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (produtoRequestDomain.getProdutoAtivo() == false && produtoRequestDomain.getProdutoOfertado() == true) {
            throw new VerificarSeStatusInativoEOfertadoAtivoException("O produto não pode ser ofertado se o mesmo estiver inativo.");
        }
    }

    public static void verificarSeQuantidadeMaiorIgualZero(Integer numero) {
        if (numero <= 0) {
            throw new QuantidadeMenorQueZeroException("Quantiade menor ou igual a 0.");
        }
    }

}
