package challenge.brq.entrypoint.mapper.request;

import challenge.brq.entrypoint.model.request.CategoriaModelRequest;
import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;

import java.util.Objects;

public class CategoriaEntryPointMapperRequest {

    public static CategoriaRequestDomain converterNome(CategoriaModelRequest categoriaModelRequest){
        return CategoriaRequestDomain.builder().nomeCategoria(categoriaModelRequest.getNome()).build();
    }

    public static CategoriaRequestDomain converterId(CategoriaModelRequest categoriaModelRequest){
        if (Objects.isNull(categoriaModelRequest)) {
            return null;
        }
        return CategoriaRequestDomain.builder()
                .idCategoria(categoriaModelRequest.getId())
                .build();
    }

    public static CategoriaRequestDomain converter(CategoriaModelRequest categoriaModelRequest){
        return CategoriaRequestDomain.builder()
                .nomeCategoria(categoriaModelRequest.getNome())
                .idCategoria(categoriaModelRequest.getId())
                .build();
    }

}
