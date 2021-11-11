package challenge.brq.entrypoint.mapper.response;

import challenge.brq.entrypoint.model.response.ProdutoModelResponse;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

public class ProdutoEntryPointMapperResponse {

    public static List<ProdutoModelResponse> converter
            (List<ProdutoResponseDomain> produtosResponseDomain) {
        List<ProdutoModelResponse> produtosModelResponse = new ArrayList<>();
        produtosResponseDomain.forEach(produtoResponseDomain -> {
            ProdutoModelResponse produtoModelResponse = converterProduto(produtoResponseDomain);
            produtosModelResponse.add(produtoModelResponse);
        });
        return produtosModelResponse;
    }

    public static Page<ProdutoModelResponse> converterPaginaPadrao(final Page<ProdutoResponseDomain> produtoResponseDomain) {
        return produtoResponseDomain.map(ProdutoEntryPointMapperResponse::converterProdutoPadrao);
    }

    public static ProdutoModelResponse converterProduto(ProdutoResponseDomain produtoResponseDomain) {
        return ProdutoModelResponse.builder().idProduto(produtoResponseDomain.getCodigoProduto())
                .nome(produtoResponseDomain.getNomeProduto())
                .descricao(produtoResponseDomain.getDescricaoProduto())
                .marca(produtoResponseDomain.getMarcaProduto())
                .quantidade(produtoResponseDomain.getQuantidadeProduto())
                .preco(produtoResponseDomain.getPrecoProduto())
                .ativo(produtoResponseDomain.getProdutoAtivo())
                .ofertado(produtoResponseDomain.getProdutoOfertado())
                .porcentagem(produtoResponseDomain.getPorcentagem())
                .categoria(CategoriaEntryPointMapperResponse.converterCategoria(produtoResponseDomain.getCategoria()))
                .tabelaNutricionalModelResponse(TabelaNutricionalEntryPointMapperResponse.converter(produtoResponseDomain.getTabelaNutricionalResponseDomain()))
                .build();
    }

    public static ProdutoModelResponse converterProdutoPadrao(ProdutoResponseDomain produtoResponseDomain) {
        return ProdutoModelResponse.builder().idProduto(produtoResponseDomain.getCodigoProduto())
                .nome(produtoResponseDomain.getNomeProduto())
                .descricao(produtoResponseDomain.getDescricaoProduto())
                .marca(produtoResponseDomain.getMarcaProduto())
                .quantidade(produtoResponseDomain.getQuantidadeProduto())
                .preco(produtoResponseDomain.getPrecoProduto())
                .ativo(produtoResponseDomain.getProdutoAtivo())
                .ofertado(produtoResponseDomain.getProdutoOfertado())
                .porcentagem(produtoResponseDomain.getPorcentagem())
                .build();
    }


    public static ProdutoModelResponse converterParaAtualizacao(Integer id, ProdutoResponseDomain produtoResponseDomain) {
        return ProdutoModelResponse.builder()
                .idProduto(id)
                .nome(produtoResponseDomain.getNomeProduto())
                .descricao(produtoResponseDomain.getDescricaoProduto())
                .marca(produtoResponseDomain.getMarcaProduto())
                .quantidade(produtoResponseDomain.getQuantidadeProduto())
                .preco(produtoResponseDomain.getPrecoProduto())
                .ativo(produtoResponseDomain.getProdutoAtivo())
                .ofertado(produtoResponseDomain.getProdutoOfertado())
                .porcentagem(produtoResponseDomain.getPorcentagem())
                .categoria(CategoriaEntryPointMapperResponse.converterCategoria(produtoResponseDomain.getCategoria()))
                .tabelaNutricionalModelResponse(TabelaNutricionalEntryPointMapperResponse.converter(produtoResponseDomain.getTabelaNutricionalResponseDomain()))
                .build();
    }

}
