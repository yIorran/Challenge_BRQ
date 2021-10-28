package challenge.brq.entrypoint.mapper.request;

import challenge.brq.entrypoint.model.request.CategoriaModelRequestID;
import challenge.brq.entrypoint.model.request.CategoriaModelRequestNome;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;

import java.util.Objects;

public class CategoriaEntryPointMapperRequest {

    public static CategoriaRequestDomain converterNome(CategoriaModelRequestNome categoriaModelRequestNome) {
        return CategoriaRequestDomain.builder().nomeCategoria(categoriaModelRequestNome.getNome()).build();
    }

    public static CategoriaRequestDomain converterId(CategoriaModelRequestID categoriaModelRequestID) {
        if (Objects.isNull(categoriaModelRequestID)) {
            return CategoriaRequestDomain.builder().build();
        }
        return CategoriaRequestDomain.builder()
                .idCategoria(categoriaModelRequestID.getId())
                .build();
    }

    public static CategoriaRequestDomain converter(CategoriaModelRequestNome categoriaModelRequestNome) {
        if (categoriaModelRequestNome == null) {
            return CategoriaRequestDomain.builder().build();
        }
        return CategoriaRequestDomain.builder()
                .nomeCategoria(categoriaModelRequestNome.getNome())
                .build();
    }
}
