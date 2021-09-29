package challenge.brq.entrypoint.mapper.response;

import challenge.brq.entrypoint.mode.response.ProdutoModelResponse;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;

import java.util.ArrayList;
import java.util.List;

public class ProdutoEntryPointMapperResponse {

    public static List<ProdutoModelResponse> converter
            (List<ProdutoResponseDomain> produtosResponseDomain){
        List<ProdutoModelResponse> produtosModelResponse = new ArrayList<>();
        produtosResponseDomain.forEach(produtoResponseDomain -> {ProdutoModelResponse produtoModelResponse = converterProduto(produtoResponseDomain);});

        return produtosModelResponse;
    }

    private static ProdutoModelResponse converterProduto(ProdutoResponseDomain produtoResponseDomain){
        return ProdutoModelResponse.builder().codigoProduto(produtoResponseDomain.getCodigoProduto())
                .nomeProduto(produtoResponseDomain.getNomeProduto()).build();
    }
}
