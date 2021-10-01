package challenge.brq.entrypoint.mapper.request;

import challenge.brq.entrypoint.model.request.ProdutoModelRequest;
import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;

public class ProdutoEntryPointMapperRequest {

    public static ProdutoRequestDomain converter(ProdutoModelRequest produtoModelRequest){
        return ProdutoRequestDomain.builder().nomeProduto(produtoModelRequest.getNome())
                .descricaoProduto(produtoModelRequest.getDescricao())
                .marcaProduto(produtoModelRequest.getMarca())
                .quantidadeProduto(produtoModelRequest.getQuantidade())
                .precoProduto(produtoModelRequest.getPreco())
                .produtoAtivo(true)
                .produtoOfertado(false)
                .porcentagem(0)
                .build();
    }

    public static ProdutoRequestDomain converterMarca(String marca){
        return ProdutoRequestDomain.builder().
                marcaProduto(marca)
                .build();
    }

}
