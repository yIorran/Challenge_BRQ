package challenge.brq.dataprovider.mapper.request;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;

public class ProdutoRequestMapper {

    public static ProdutoEntity converter(ProdutoRequestDomain produtoRequestDomain) {
        return ProdutoEntity.builder()
                .nomeProduto(produtoRequestDomain.getNomeProduto())
                .descricaoProduto(produtoRequestDomain.getDescricaoProduto())
                .marcaProduto(produtoRequestDomain.getMarcaProduto())
                .quantidadeProduto(produtoRequestDomain.getQuantidadeProduto())
                .precoProduto(produtoRequestDomain.getPrecoProduto())
                .produtoAtivo(true)
                .produtoOfertado(false)
                .porcentagemoferta(0)
                .categoria(CategoriaRequestMapper.converterId(produtoRequestDomain.getCategoria()))
                .build();
    }

    public static ProdutoEntity converterParaAtualizacao(ProdutoResponseDomain produtoResponseDomain) {
        return ProdutoEntity.builder()
                .nomeProduto(produtoResponseDomain.getNomeProduto())
                .codigoProduto(produtoResponseDomain.getCodigoProduto())
                .descricaoProduto(produtoResponseDomain.getDescricaoProduto())
                .marcaProduto(produtoResponseDomain.getMarcaProduto())
                .quantidadeProduto(produtoResponseDomain.getQuantidadeProduto())
                .precoProduto(produtoResponseDomain.getPrecoProduto())
                .produtoAtivo(produtoResponseDomain.getProdutoAtivo())
                .produtoOfertado(produtoResponseDomain.getProdutoOfertado())
                .porcentagemoferta(produtoResponseDomain.getPorcentagem())
                .categoria(CategoriaRequestMapper.converterIdResponse(produtoResponseDomain.getCategoria()))
                .build();
    }
}
