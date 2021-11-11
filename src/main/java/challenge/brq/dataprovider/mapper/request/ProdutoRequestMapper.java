package challenge.brq.dataprovider.mapper.request;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import org.springframework.data.domain.Page;

public class ProdutoRequestMapper {

    public static Page<ProdutoEntity> converterPaginaComTodosAtributosEntityDesativacao(Page<ProdutoEntity> produtoEntity) {
        return produtoEntity.map(ProdutoRequestMapper::converterProdutoEmtityComTodosAtributosDesativacao);
    }

    public static Page<ProdutoEntity> converterPaginaComTodosAtributosEntityAtivacao(Page<ProdutoEntity> produtoEntity) {
        return produtoEntity.map(ProdutoRequestMapper::converterProdutoEmtityComTodosAtributosAtivacao);
    }

    public static ProdutoEntity converterProdutoEmtityComTodosAtributosAtivacao(ProdutoEntity produtoEntity) {
        return ProdutoEntity.builder()
                .codigoProduto(produtoEntity.getCodigoProduto())
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricaoProduto(produtoEntity.getDescricaoProduto())
                .marcaProduto(produtoEntity.getMarcaProduto())
                .quantidadeProduto(produtoEntity.getQuantidadeProduto())
                .precoProduto(produtoEntity.getPrecoProduto())
                .produtoAtivo(true)
                .produtoOfertado(produtoEntity.getProdutoOfertado())
                .porcentagemoferta(produtoEntity.getPorcentagemoferta())
                .categoria(produtoEntity.getCategoria())
                .tabelaNutricionalEntity(produtoEntity.getTabelaNutricionalEntity())
                .build();
    }

    public static ProdutoEntity converterProdutoEmtityComTodosAtributosDesativacao(ProdutoEntity produtoEntity) {
        return ProdutoEntity.builder()
                .codigoProduto(produtoEntity.getCodigoProduto())
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricaoProduto(produtoEntity.getDescricaoProduto())
                .marcaProduto(produtoEntity.getMarcaProduto())
                .quantidadeProduto(produtoEntity.getQuantidadeProduto())
                .precoProduto(produtoEntity.getPrecoProduto())
                .produtoAtivo(false)
                .produtoOfertado(produtoEntity.getProdutoOfertado())
                .porcentagemoferta(produtoEntity.getPorcentagemoferta())
                .categoria(produtoEntity.getCategoria())
                .tabelaNutricionalEntity(produtoEntity.getTabelaNutricionalEntity())
                .build();
    }

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
                .tabelaNutricionalEntity(TabelaNutricionalRequestMapper.converter(produtoRequestDomain.getTabelaNutricionalRequestDomain()))
                .build();
    }

    public static ProdutoEntity converterTodosAtributos(ProdutoRequestDomain produtoRequestDomain) {
        return ProdutoEntity.builder()
                .nomeProduto(produtoRequestDomain.getNomeProduto())
                .descricaoProduto(produtoRequestDomain.getDescricaoProduto())
                .marcaProduto(produtoRequestDomain.getMarcaProduto())
                .quantidadeProduto(produtoRequestDomain.getQuantidadeProduto())
                .precoProduto(produtoRequestDomain.getPrecoProduto())
                .produtoAtivo(produtoRequestDomain.getProdutoAtivo())
                .produtoOfertado(produtoRequestDomain.getProdutoOfertado())
                .porcentagemoferta(produtoRequestDomain.getPorcentagem())
                .categoria(CategoriaRequestMapper.converterId(produtoRequestDomain.getCategoria()))
                .tabelaNutricionalEntity(TabelaNutricionalRequestMapper.converter(produtoRequestDomain.getTabelaNutricionalRequestDomain()))
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
                .tabelaNutricionalEntity(TabelaNutricionalRequestMapper.converterResponse(produtoResponseDomain.getTabelaNutricionalResponseDomain()))
                .build();
    }
}
