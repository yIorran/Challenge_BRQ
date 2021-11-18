package challenge.brq.dataprovider.mapper.response;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ProdutoResponseMapper {

    private ProdutoResponseMapper() {
    }

    public static List<ProdutoResponseDomain> converter(final List<ProdutoEntity> produtoEntity) {
        List<ProdutoResponseDomain> produtosResponseDomain = new ArrayList<>();

        produtoEntity.forEach(produto -> {
            ProdutoResponseDomain produtoDomainModel = converterProdutoComTodosAtributos(produto);
            produtosResponseDomain.add(produtoDomainModel);
        });
        return produtosResponseDomain;
    }

    public static Page<ProdutoResponseDomain> converterPaginaComTodosAtributos(Page<ProdutoEntity> produtoEntity) {
        return produtoEntity.map(ProdutoResponseMapper::converterProdutoComTodosAtributos);
    }

    public static Page<ProdutoResponseDomain> converterPaginaPadrao(Page<ProdutoEntity> produtoEntity) {
        return produtoEntity.map(ProdutoResponseMapper::converterProdutoPadrao);
    }


    public static ProdutoResponseDomain converterProdutoComTodosAtributos(ProdutoEntity produtoEntity) {
        if(Objects.isNull(produtoEntity)){
            return ProdutoResponseDomain.builder().build();
        }
        return ProdutoResponseDomain.builder()
                .codigoProduto(produtoEntity.getCodigoProduto())
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricaoProduto(produtoEntity.getDescricaoProduto())
                .marcaProduto(produtoEntity.getMarcaProduto())
                .quantidadeProduto(produtoEntity.getQuantidadeProduto())
                .precoProduto(produtoEntity.getPrecoProduto())
                .produtoAtivo(produtoEntity.getProdutoAtivo())
                .produtoOfertado(produtoEntity.getProdutoOfertado())
                .porcentagem(produtoEntity.getPorcentagemoferta())
                .categoria(CategoriaResponseMapper.converterCategoria(produtoEntity.getCategoria()))
                .tabelaNutricionalResponseDomain(TabelaNutricionalResponseMapper.converter(produtoEntity.getTabelaNutricionalEntity()))
                .build();
    }

    public static ProdutoResponseDomain converterProdutoComTodosAtributosExpand(ProdutoEntity produtoEntity, String expand) {
        if(StringUtils.isEmpty(expand)){
            return ProdutoResponseDomain.builder()
                    .codigoProduto(produtoEntity.getCodigoProduto())
                    .nomeProduto(produtoEntity.getNomeProduto())
                    .descricaoProduto(produtoEntity.getDescricaoProduto())
                    .marcaProduto(produtoEntity.getMarcaProduto())
                    .quantidadeProduto(produtoEntity.getQuantidadeProduto())
                    .precoProduto(produtoEntity.getPrecoProduto())
                    .produtoAtivo(produtoEntity.getProdutoAtivo())
                    .produtoOfertado(produtoEntity.getProdutoOfertado())
                    .porcentagem(produtoEntity.getPorcentagemoferta())
                    .categoria(CategoriaResponseMapper.converterCategoria(produtoEntity.getCategoria()))
                    .build();
        }
        else
        return ProdutoResponseDomain.builder()
                .codigoProduto(produtoEntity.getCodigoProduto())
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricaoProduto(produtoEntity.getDescricaoProduto())
                .marcaProduto(produtoEntity.getMarcaProduto())
                .quantidadeProduto(produtoEntity.getQuantidadeProduto())
                .precoProduto(produtoEntity.getPrecoProduto())
                .produtoAtivo(produtoEntity.getProdutoAtivo())
                .produtoOfertado(produtoEntity.getProdutoOfertado())
                .porcentagem(produtoEntity.getPorcentagemoferta())
                .categoria(CategoriaResponseMapper.converterCategoria(produtoEntity.getCategoria()))
                .tabelaNutricionalResponseDomain(TabelaNutricionalResponseMapper.converterExpand(produtoEntity.getTabelaNutricionalEntity(), expand))
                .build();
    }


    public static ProdutoResponseDomain converterProdutoPadrao(ProdutoEntity produtoEntity) {
        return ProdutoResponseDomain.builder()
                .codigoProduto(produtoEntity.getCodigoProduto())
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricaoProduto(produtoEntity.getDescricaoProduto())
                .marcaProduto(produtoEntity.getMarcaProduto())
                .quantidadeProduto(produtoEntity.getQuantidadeProduto())
                .precoProduto(produtoEntity.getPrecoProduto())
                .produtoAtivo(produtoEntity.getProdutoAtivo())
                .produtoOfertado(produtoEntity.getProdutoOfertado())
                .porcentagem(produtoEntity.getPorcentagemoferta())
                .build();
    }


    public static ProdutoResponseDomain converterProdutoParaAtualizacao(ProdutoEntity produtoEntity) {
        return ProdutoResponseDomain.builder()
                .codigoProduto(produtoEntity.getCodigoProduto())
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricaoProduto(produtoEntity.getDescricaoProduto())
                .marcaProduto(produtoEntity.getMarcaProduto())
                .quantidadeProduto(produtoEntity.getQuantidadeProduto())
                .precoProduto(produtoEntity.getPrecoProduto())
                .produtoAtivo(produtoEntity.getProdutoAtivo())
                .produtoOfertado(produtoEntity.getProdutoOfertado())
                .porcentagem(produtoEntity.getPorcentagemoferta())
                .categoria(CategoriaResponseMapper.converterCategoria(produtoEntity.getCategoria()))
                .tabelaNutricionalResponseDomain(TabelaNutricionalResponseMapper.converter(produtoEntity.getTabelaNutricionalEntity()))
                .build();
    }

    public static ProdutoResponseDomain converterProdutoComTodosAtributosAtivacaoFalse(ProdutoEntity produtoEntity) {
        return ProdutoResponseDomain.builder()
                .codigoProduto(produtoEntity.getCodigoProduto())
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricaoProduto(produtoEntity.getDescricaoProduto())
                .marcaProduto(produtoEntity.getMarcaProduto())
                .quantidadeProduto(produtoEntity.getQuantidadeProduto())
                .precoProduto(produtoEntity.getPrecoProduto())
                .produtoAtivo(false)
                .produtoOfertado(produtoEntity.getProdutoOfertado())
                .porcentagem(produtoEntity.getPorcentagemoferta())
                .categoria(CategoriaResponseMapper.converterCategoria(produtoEntity.getCategoria()))
                .tabelaNutricionalResponseDomain(TabelaNutricionalResponseMapper.converter(produtoEntity.getTabelaNutricionalEntity()))
                .build();
    }

    public static ProdutoResponseDomain converterProdutoComTodosAtributosAtivacaoTrue(ProdutoEntity produtoEntity) {
        return ProdutoResponseDomain.builder()
                .codigoProduto(produtoEntity.getCodigoProduto())
                .nomeProduto(produtoEntity.getNomeProduto())
                .descricaoProduto(produtoEntity.getDescricaoProduto())
                .marcaProduto(produtoEntity.getMarcaProduto())
                .quantidadeProduto(produtoEntity.getQuantidadeProduto())
                .precoProduto(produtoEntity.getPrecoProduto())
                .produtoAtivo(true)
                .produtoOfertado(produtoEntity.getProdutoOfertado())
                .porcentagem(produtoEntity.getPorcentagemoferta())
                .categoria(CategoriaResponseMapper.converterCategoria(produtoEntity.getCategoria()))
                .tabelaNutricionalResponseDomain(TabelaNutricionalResponseMapper.converter(produtoEntity.getTabelaNutricionalEntity()))
                .build();
    }

    public static Page<ProdutoResponseDomain> converterPaginaComTodosAtributosAtivacao(Page<ProdutoEntity> produtoEntity) {
        return produtoEntity.map(ProdutoResponseMapper::converterProdutoComTodosAtributosAtivacaoTrue);
    }

    public static Page<ProdutoResponseDomain> converterPaginaComTodosAtributosDesativacao(Page<ProdutoEntity> produtoEntity) {
        return produtoEntity.map(ProdutoResponseMapper::converterProdutoComTodosAtributosAtivacaoFalse);
    }
}