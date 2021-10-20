package challenge.brq.dataprovider.mapper.request;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;

import java.util.Objects;

/**
 * Classe de conversores
 */
public class CategoriaRequestMapper {

    public static CategoriaEntity converter(CategoriaRequestDomain categoriaRequestDomain) {
        return CategoriaEntity.builder()
                .nomeCategoria(categoriaRequestDomain.getNomeCategoria())
                .build();
    }

    public static CategoriaEntity converterId(CategoriaRequestDomain categoriaRequestDomain) {
        if (Objects.isNull(categoriaRequestDomain)) {
            return CategoriaEntity.builder().build();
        }
        return CategoriaEntity.builder()
                .id(categoriaRequestDomain.getIdCategoria())
                .build();
    }

    public static CategoriaEntity converterParaAtualizacao(CategoriaResponseDomain categoriaResponseDomain) {
        return CategoriaEntity.builder()
                .nomeCategoria(categoriaResponseDomain.getNomeCategoria())
                .id(categoriaResponseDomain.getIdCategoria())
                .build();
    }

    public static CategoriaEntity converterIdResponse(CategoriaResponseDomain categoriaResponseDomain) {
        if (Objects.isNull(categoriaResponseDomain)) {
            return null;
        }
        return CategoriaEntity.builder()
                .id(categoriaResponseDomain.getIdCategoria())
                .build();
    }


}
