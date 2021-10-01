package challenge.brq.dataprovider.mapper.request;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;

public class ProdutoRequestMapper {

    public static ProdutoEntity converter(ProdutoRequestDomain produtoRequestDomain){
        return ProdutoEntity.builder()
                .nomeProduto(produtoRequestDomain.getNomeProduto())
                .descricaoProduto(produtoRequestDomain.getDescricaoProduto())
                .marcaProduto(produtoRequestDomain.getMarcaProduto())
                .quantidadeProduto(produtoRequestDomain.getQuantidadeProduto())
                .precoProduto(produtoRequestDomain.getPrecoProduto())
                .produtoAtivo(true)
                .produtoOfertado(false)
                .porcentagemoferta(0)
                .build();
    }

}
