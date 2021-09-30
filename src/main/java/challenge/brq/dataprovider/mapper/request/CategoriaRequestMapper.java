package challenge.brq.dataprovider.mapper.request;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;

public class CategoriaRequestMapper {

    public static CategoriaEntity converter(CategoriaRequestDomain categoriaRequestDomain) {
        return CategoriaEntity.builder()
                .nomeCategoria(categoriaRequestDomain.getNomeCategoria())
                .build();
    }

    public static CategoriaEntity converterParaAtualizacao(Integer id, CategoriaRequestDomain categoriaRequestDomain) {
        return CategoriaEntity.builder()
                .nomeCategoria(categoriaRequestDomain.getNomeCategoria())
                .idCategoria(id)
                .build();
    }
}
