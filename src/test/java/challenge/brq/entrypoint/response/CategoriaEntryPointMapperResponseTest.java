package challenge.brq.entrypoint.response;

import challenge.brq.entrypoint.mapper.response.CategoriaEntryPointMapperResponse;
import challenge.brq.entrypoint.model.response.CategoriaModelResponse;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CategoriaEntryPointMapperResponseTest {

    @Test
    public void converter(){
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterCategoria(CategoriaResponseDomain.builder()
                .idCategoria(1)
                .nomeCategoria("Alimento").build());
        assertEquals(1, categoriaModelResponse.getIdCategoria());
        assertEquals("Alimento", categoriaModelResponse.getNomeCategoria());
    }

    @Test
    public void converterAtualizacao(){
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterParaAtualizacao(1,CategoriaResponseDomain.builder()
                .idCategoria(1)
                .nomeCategoria("Alimento").build());
        assertEquals(1, categoriaModelResponse.getIdCategoria());
        assertEquals("Alimento", categoriaModelResponse.getNomeCategoria());
    }

    @Test
    public void testeConverterList(){
        List<CategoriaResponseDomain> produto = getListaCategoriaModelResponse();
        List<CategoriaModelResponse> categoriaResponseDomain = CategoriaEntryPointMapperResponse.converter(produto);
        assertAll(() -> {
            assertEquals("Alimento", categoriaResponseDomain.get(0).getNomeCategoria());
            assertEquals(1, categoriaResponseDomain.get(0).getIdCategoria());
        });
    }

    public List<CategoriaResponseDomain> getListaCategoriaModelResponse() {
        List<CategoriaResponseDomain> categoriaResponseDomain = new ArrayList<>();
        categoriaResponseDomain.add(CategoriaResponseDomain.builder().idCategoria(1).nomeCategoria("Alimento").build());
        return categoriaResponseDomain;
    }
}
