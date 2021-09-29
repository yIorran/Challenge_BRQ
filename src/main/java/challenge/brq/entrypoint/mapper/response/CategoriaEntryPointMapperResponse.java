package challenge.brq.entrypoint.mapper.response;

import challenge.brq.entrypoint.mode.response.CategoriaModelResponse;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;

import java.util.ArrayList;
import java.util.List;

public class CategoriaEntryPointMapperResponse {

    public static List<CategoriaModelResponse> converter
            (List<CategoriaResponseDomain> categoriasResponseDomain){
        List<CategoriaModelResponse> categoriasModelResponse = new ArrayList<>();
        categoriasResponseDomain.forEach(categoriaResponseDomain -> {CategoriaModelResponse categoriaModelResponse = converterCategoria(categoriaResponseDomain);
        categoriasModelResponse.add(categoriaModelResponse);
        });
        return categoriasModelResponse;
    }

    public static CategoriaModelResponse converterCategoria(CategoriaResponseDomain categoriaResponseDomain){
        return CategoriaModelResponse.builder()
                .idCategoria(categoriaResponseDomain.getIdCategoria())
                .nomeCategoria(categoriaResponseDomain.getNomeCategoria())
                .build();
    }
}
