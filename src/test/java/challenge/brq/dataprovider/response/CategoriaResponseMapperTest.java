package challenge.brq.dataprovider.response;


import challenge.brq.entrypoint.mapper.response.CategoriaEntryPointMapperResponse;
import challenge.brq.entrypoint.model.response.CategoriaModelResponse;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriaResponseMapperTest {

    @Test
    public void testeConverterCategoriaRequestSucessoNome(){
        //cenario
        CategoriaResponseDomain categoriaResponseDomain = CategoriaResponseDomain.builder()
                .nomeCategoria("Alimento")
                .build();
        //condicao
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterCategoria(categoriaResponseDomain);
        //validacao
        assertEquals("Alimento", categoriaModelResponse.getNomeCategoria());
    }

    @Test
    public void testeConverterCategoriaParaAttRequestSucessoNome(){
        //cenario
        CategoriaResponseDomain categoriaResponseDomain = CategoriaResponseDomain.builder()
                .nomeCategoria("Alimento")
                .idCategoria(1)
                .build();
        //condicao
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterParaAtualizacao(1,categoriaResponseDomain);
        //validacao
        assertEquals("Alimento", categoriaModelResponse.getNomeCategoria());
        assertEquals(1, categoriaModelResponse.getIdCategoria());
    }

    @Test
    public void testeConverterList(){
        List<CategoriaResponseDomain> produto = getListCategoriaModelResponse();
        List<CategoriaModelResponse> categoriaResponseDomain = CategoriaEntryPointMapperResponse.converter(produto);
        assertAll(() -> {
            assertEquals("Alimento", categoriaResponseDomain.get(0).getNomeCategoria());
            assertEquals(1, categoriaResponseDomain.get(0).getIdCategoria());
        });
    }

    public List<CategoriaResponseDomain> getListCategoriaModelResponse(){
        List<CategoriaResponseDomain> categoriaModelResponses = new ArrayList<>();
        categoriaModelResponses.add(CategoriaResponseDomain.builder()
                .nomeCategoria("Alimento")
                .idCategoria(1)
                .build());
        return categoriaModelResponses;
    }

}
