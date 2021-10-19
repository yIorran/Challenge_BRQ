package challenge.brq.entrypoint.mapper.request;

import challenge.brq.entrypoint.model.request.ProdutoModelRequest;
import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;

public class ProdutoEntryPointMapperRequest {

    public static ProdutoRequestDomain converter(ProdutoModelRequest produtoModelRequest){
        return ProdutoRequestDomain.builder()
                .codigoProduto(produtoModelRequest.getIdProduto())
                .nomeProduto(produtoModelRequest.getNome())
                .descricaoProduto(produtoModelRequest.getDescricao())
                .marcaProduto(produtoModelRequest.getMarca())
                .quantidadeProduto(produtoModelRequest.getQuantidade())
                .precoProduto(produtoModelRequest.getPreco())
                .produtoAtivo(true)
                .produtoOfertado(false)
                .porcentagem(0)
                .categoria(CategoriaEntryPointMapperRequest.converterId(produtoModelRequest.getCategoria().getId()))
                .build();
    }

    public static ProdutoRequestDomain converterMarca(String marca){
        return ProdutoRequestDomain.builder().
                marcaProduto(marca)
                .build();
    }
    public static ProdutoRequestDomain converterParaAtualizacaoParcial(ProdutoModelRequest produtoModelRequest){
        return ProdutoRequestDomain.builder()
                .codigoProduto(produtoModelRequest.getIdProduto())
                .nomeProduto(produtoModelRequest.getNome())
                .descricaoProduto(produtoModelRequest.getDescricao())
                .marcaProduto(produtoModelRequest.getMarca())
                .quantidadeProduto(produtoModelRequest.getQuantidade())
                .precoProduto(produtoModelRequest.getPreco())
                .produtoAtivo(produtoModelRequest.getAtivo())
                .produtoOfertado(produtoModelRequest.getOfertado())
                .porcentagem(produtoModelRequest.getPorcentagem())
                .categoria(CategoriaEntryPointMapperRequest.converterId(produtoModelRequest.getCategoria().getId()))
                .build();
    }

    public static ProdutoRequestDomain converterParaAtualizacao(ProdutoModelRequest produtoModelRequest){
        return ProdutoRequestDomain.builder()
                .codigoProduto(produtoModelRequest.getIdProduto())
                .nomeProduto(produtoModelRequest.getNome())
                .descricaoProduto(produtoModelRequest.getDescricao())
                .marcaProduto(produtoModelRequest.getMarca())
                .quantidadeProduto(produtoModelRequest.getQuantidade())
                .precoProduto(produtoModelRequest.getPreco())
                .produtoAtivo(produtoModelRequest.getAtivo())
                .produtoOfertado(produtoModelRequest.getOfertado())
                .porcentagem(produtoModelRequest.getPorcentagem())
                .build();
    }


}
