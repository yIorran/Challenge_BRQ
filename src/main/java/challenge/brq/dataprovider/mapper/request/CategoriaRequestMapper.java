package challenge.brq.dataprovider.mapper.request;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;

public class CategoriaRequestMapper {

    public static CategoriaEntity converter(CategoriaRequestDomain categoriaRequestDomain) {
        return CategoriaEntity.builder()
                .nomeCategoria(categoriaRequestDomain.getNomeCategoria())
                .build();
    }
    public static CategoriaEntity converterParaAtualizacao(CategoriaResponseDomain categoriaResponseDomain) {
        return CategoriaEntity.builder()
                .nomeCategoria(categoriaResponseDomain.getNomeCategoria())
                .idCategoria(categoriaResponseDomain.getIdCategoria())
                .build();
    }
}
