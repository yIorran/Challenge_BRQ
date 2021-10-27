package challenge.brq.entrypoint.mapper.request;

import challenge.brq.entrypoint.model.request.CategoriaModelRequestNome;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;

import java.util.Objects;

public class CategoriaEntryPointMapperRequest {

    public static CategoriaRequestDomain converterNome(CategoriaModelRequestNome categoriaModelRequestNome) {
        return CategoriaRequestDomain.builder().nomeCategoria(categoriaModelRequestNome.getNome()).build();
    }

    public static CategoriaRequestDomain converterId(CategoriaModelRequestNome categoriaModelRequestNome) {
        if (Objects.isNull(categoriaModelRequestNome)) {
            return CategoriaRequestDomain.builder().build();
        }
        return CategoriaRequestDomain.builder()
                .idCategoria(categoriaModelRequestNome.getId())
                .build();
    }

    public static CategoriaRequestDomain converter(CategoriaModelRequestNome categoriaModelRequestNome) {
        if (categoriaModelRequestNome == null) {
            return CategoriaRequestDomain.builder().build();
        }
        return CategoriaRequestDomain.builder()
                .nomeCategoria(categoriaModelRequestNome.getNome())
                .idCategoria(categoriaModelRequestNome.getId())
                .build();
    }

}
