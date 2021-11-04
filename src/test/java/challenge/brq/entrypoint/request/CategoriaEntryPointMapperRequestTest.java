package challenge.brq.entrypoint.request;

import challenge.brq.entrypoint.mapper.request.CategoriaEntryPointMapperRequest;
import challenge.brq.entrypoint.model.request.CategoriaModelRequestID;
import challenge.brq.entrypoint.model.request.CategoriaModelRequestNome;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CategoriaEntryPointMapperRequestTest {

    @Test
    public void converterNome(){
        CategoriaRequestDomain categoriaRequestDomain = CategoriaEntryPointMapperRequest.converterNome(CategoriaModelRequestNome.builder().nome("Alimento").build());
        assertEquals("Alimento", categoriaRequestDomain.getNomeCategoria());
    }
    @Test
    public void converter(){
        CategoriaRequestDomain categoriaRequestDomain = CategoriaEntryPointMapperRequest.converterId(CategoriaModelRequestID.builder().id(1).build());
        assertEquals(1, categoriaRequestDomain.getIdCategoria());
    }
    @Test
    public void converterNomeCategoriaNotNull(){
        CategoriaRequestDomain categoriaRequestDomain = CategoriaEntryPointMapperRequest.converter(CategoriaModelRequestNome.builder().nome(null).build());
        assertNull(categoriaRequestDomain.getNomeCategoria());
    }
}
