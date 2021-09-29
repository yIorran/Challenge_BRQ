package challenge.brq.entrypoint.mapper.request;

import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;

public class CategoriaEntryPointMapperRequest {

    public static CategoriaRequestDomain converter(String nomeCategoria){
        return CategoriaRequestDomain.builder().nomeCategoria(nomeCategoria).build();
    }

    public static CategoriaRequestDomain converterId(Integer idCategoria){
        return CategoriaRequestDomain.builder().idCategoria(idCategoria).build();
    }
}
