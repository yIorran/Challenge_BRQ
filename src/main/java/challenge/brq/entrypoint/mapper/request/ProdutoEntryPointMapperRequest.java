package challenge.brq.entrypoint.mapper.request;

import challenge.brq.entrypoint.model.request.ProdutoModelRequest;
import challenge.brq.entrypoint.model.request.ProdutoModelRequestFiltro;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;

import java.util.Objects;

public class ProdutoEntryPointMapperRequest {

    public static ProdutoRequestDomain converter(ProdutoModelRequest produtoModelRequest) {
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
                .categoria(CategoriaEntryPointMapperRequest.converterId(produtoModelRequest.getCategoria()))
                .tabelaNutricionalRequestDomain(TabelaNutricionalEntryPointMapperRequest.converter(produtoModelRequest.getTabelaNutricional()))
                .build();
    }


    public static ProdutoRequestDomain converterPesquisaFiltro(ProdutoModelRequestFiltro produtoModelRequestFiltro) {
        if(Objects.isNull(produtoModelRequestFiltro)){
            return ProdutoRequestDomain.builder().build();
        }
        return ProdutoRequestDomain.builder()
                .nomeProduto(produtoModelRequestFiltro.getNome())
                .marcaProduto(produtoModelRequestFiltro.getMarca())
                .precoProduto(produtoModelRequestFiltro.getPreco())
                .categoria(CategoriaEntryPointMapperRequest.converterNome(produtoModelRequestFiltro.getCategoria()))
                .build();
    }


    public static ProdutoRequestDomain converterParaAtualizacaoParcial(ProdutoModelRequest produtoModelRequest) {
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
                .categoria(CategoriaEntryPointMapperRequest.converterId(produtoModelRequest.getCategoria()))
                .tabelaNutricionalRequestDomain(TabelaNutricionalEntryPointMapperRequest.converter(produtoModelRequest.getTabelaNutricional()))
                .build();
    }
}
