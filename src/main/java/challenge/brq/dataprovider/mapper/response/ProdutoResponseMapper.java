package challenge.brq.dataprovider.mapper.response;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;

import java.util.ArrayList;
import java.util.List;

public class ProdutoResponseMapper {

    private ProdutoResponseMapper(){}

    public static List<ProdutoResponseDomain> converter(final List<ProdutoEntity> produtoEntity){
        List<ProdutoResponseDomain> produtosResponseDomain = new ArrayList<>();

        produtoEntity.forEach(produto -> {ProdutoResponseDomain produtoDomainModel = converterProduto(produto);
        produtosResponseDomain.add(produtoDomainModel);
        });
        return produtosResponseDomain;
    }

    private static ProdutoResponseDomain converterProduto(ProdutoEntity produtoEntity){
        return ProdutoResponseDomain.builder()
                .codigoProduto(produtoEntity.getCodigoProduto())
                .nomeProduto(produtoEntity.getNomeProduto()).build();
    }

}
