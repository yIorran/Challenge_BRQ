package challenge.brq.entrypoint.mapper.response;

import challenge.brq.entrypoint.model.response.CategoriaModelResponse;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;

import java.util.ArrayList;
import java.util.List;

public class CategoriaEntryPointMapperResponse {

    public static List<CategoriaModelResponse> converter
            (List<CategoriaResponseDomain> categoriasResponseDomain) {
        List<CategoriaModelResponse> categoriasModelResponse = new ArrayList<>();
        categoriasResponseDomain.forEach(categoriaResponseDomain -> {
            CategoriaModelResponse categoriaModelResponse = converterCategoria(categoriaResponseDomain);
            categoriasModelResponse.add(categoriaModelResponse);
        });
        return categoriasModelResponse;
    }

    public static CategoriaModelResponse converterCategoria(CategoriaResponseDomain categoriaResponseDomain) {
        return CategoriaModelResponse.builder()
                .idCategoria(categoriaResponseDomain.getIdCategoria())
                .nomeCategoria(categoriaResponseDomain.getNomeCategoria())
                .build();
    }

    public static CategoriaModelResponse converterParaAtualizacao(Integer id, CategoriaResponseDomain categoriaResponseDomain) {
        return CategoriaModelResponse.builder()
                .idCategoria(id)
                .nomeCategoria(categoriaResponseDomain.getNomeCategoria())
                .build();
    }
}
