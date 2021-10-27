package challenge.brq.usecase.utils;

import challenge.brq.usecase.exception.produto.*;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Utils {

    public static void verificarSeCategoriaExisteParaAdicionar(Object id) {
        if (id == null) {
            throw new CategoriaNaoEncontradaParaAdicionarOuAtualizarProduto("Categoria não encontrada para fazer adição");
        }
    }
    public static void verificarSeCategoriaExisteParaAtualizarParcial(CategoriaResponseDomain categoriaResponseDomain) {
        if (categoriaResponseDomain == null) {
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (categoriaResponseDomain.getIdCategoria() == null) {
            throw new CategoriaNaoEncontradaParaAdicionarOuAtualizarProduto("Categoria não encontrada para fazer adição");
        }
    }

    public static void verificarSeQuantidadeMaiorIgualZero(Integer numero) {
        if (numero <= 0) {
            throw new QuantidadeMenorQueZeroException("Quantiade menor ou igual a 0.");
        }
    }

    public static void verificarSePorcentagemMaiorQueZeroAposModificacoes(ProdutoResponseDomain produtoResponseDomain){
        if(produtoResponseDomain.getPorcentagem() == null && produtoResponseDomain.getProdutoOfertado() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if(produtoResponseDomain.getPorcentagem() == null || produtoResponseDomain.getProdutoOfertado() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if(produtoResponseDomain.getPorcentagem() <= 0 && produtoResponseDomain.getProdutoOfertado() == true){
            throw new PorcentagemMaiorQueZeroException("O produto não pode ser ofertado se a porcentage for menor ou igual a 0 "
                    + "porcentagem atual: " + produtoResponseDomain.getPorcentagem());
        }
    }

    public static void verificarSeStatusDoProdutoEAtivoAposModificacoes(ProdutoResponseDomain produtoResponseDomain){
        if(produtoResponseDomain.getQuantidadeProduto() == null && produtoResponseDomain.getProdutoAtivo() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if(produtoResponseDomain.getQuantidadeProduto() == null || produtoResponseDomain.getProdutoAtivo() == null){
            produtoResponseDomain.builder().build();
            return;
        }
        if(produtoResponseDomain.getQuantidadeProduto() == 0 && produtoResponseDomain.getProdutoAtivo() == true){
            throw new QuantidadeZeroEProdutoAtivo("Produto não pode ser ativo se a quantidade for igual a 0");
        }
    }

    public static void verificarSeOfertadoAtivoEStatusAtivoAposModificacoes(ProdutoResponseDomain produtoResponseDomain) {
        if(produtoResponseDomain.getProdutoAtivo() == null && produtoResponseDomain.getProdutoOfertado() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if(produtoResponseDomain.getProdutoAtivo() == null || produtoResponseDomain.getProdutoOfertado() == null){
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (produtoResponseDomain.getProdutoAtivo() == false && produtoResponseDomain.getProdutoOfertado() == true) {
            throw new VerificarSeStatusInativoEOfertadoAtivoException("O produto não pode ser ofertado se o mesmo estiver inativo.");
        }
    }

}
