package challenge.brq.entrypoint.mapper.response;

import challenge.brq.entrypoint.model.response.ProdutoModelResponse;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;

import java.util.ArrayList;
import java.util.List;

public class ProdutoEntryPointMapperResponse {

    public static List<ProdutoModelResponse> converter
            (List<ProdutoResponseDomain> produtosResponseDomain){
        List<ProdutoModelResponse> produtosModelResponse = new ArrayList<>();
        produtosResponseDomain.forEach(produtoResponseDomain -> {ProdutoModelResponse produtoModelResponse = converterProduto(produtoResponseDomain);
        produtosModelResponse.add(produtoModelResponse);
        });
        return produtosModelResponse;
    }

    public static ProdutoModelResponse converterProduto(ProdutoResponseDomain produtoResponseDomain){
        return ProdutoModelResponse.builder().codigoProduto(produtoResponseDomain.getCodigoProduto())
                .nomeProduto(produtoResponseDomain.getNomeProduto())
                .descricaoProduto(produtoResponseDomain.getDescricaoProduto())
                .marcaProduto(produtoResponseDomain.getMarcaProduto())
                .quantidadeProduto(produtoResponseDomain.getQuantidadeProduto())
                .precoProduto(produtoResponseDomain.getPrecoProduto())
                .ativo(true)
                .ofertado(false)
                .porcentagem(0)
                .build();
    }

    public static ProdutoModelResponse converterParaAtualizacao(Integer id, ProdutoResponseDomain produtoResponseDomain){
        return ProdutoModelResponse.builder()
                .codigoProduto(id)
                .nomeProduto(produtoResponseDomain.getNomeProduto())
                .descricaoProduto(produtoResponseDomain.getDescricaoProduto())
                .marcaProduto(produtoResponseDomain.getMarcaProduto())
                .quantidadeProduto(produtoResponseDomain.getQuantidadeProduto())
                .precoProduto(produtoResponseDomain.getPrecoProduto())
                .ativo(produtoResponseDomain.getProdutoAtivo())
                .ofertado(produtoResponseDomain.getProdutoOfertado())
                .porcentagem(produtoResponseDomain.getPorcentagem())
                .build();
    }
}
