package challenge.brq.entrypoint.mapper.request;

import challenge.brq.entrypoint.model.request.CategoriaModelRequest;
import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;

public class CategoriaEntryPointMapperRequest {

    public static CategoriaRequestDomain converterNome(CategoriaModelRequest categoriaModelRequest){
        return CategoriaRequestDomain.builder().nomeCategoria(categoriaModelRequest.getNome()).build();
    }

    public static CategoriaRequestDomain converterId(Integer idCategoria){
        return CategoriaRequestDomain.builder().idCategoria(idCategoria).build();
    }

    public static CategoriaRequestDomain converter(CategoriaModelRequest categoriaModelRequest){
        return CategoriaRequestDomain.builder()
                .nomeCategoria(categoriaModelRequest.getNome())
                .idCategoria(categoriaModelRequest.getId())
                .build();
    }

}
