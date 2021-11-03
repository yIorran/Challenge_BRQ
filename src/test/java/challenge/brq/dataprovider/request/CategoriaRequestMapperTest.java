package challenge.brq.dataprovider.request;


import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.dataprovider.mapper.request.CategoriaRequestMapper;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CategoriaRequestMapperTest {

    @Test
    public void testeConverterCategoriaRequestSucessoNome(){
        //cenario
        CategoriaRequestDomain categoriaRequestDomain = CategoriaRequestDomain.builder()
                .nomeCategoria("Alimento")
                .build();
        //condicao
        CategoriaEntity categoria = CategoriaRequestMapper.converter(categoriaRequestDomain);
        //validacao
        assertEquals("Alimento", categoria.getNomeCategoria());
    }

    /**
     * Teste para checar se o Id da categoria é nulo
     */
    @Test
    public void testeConverterCategoriaRequestSucessoIdNulo(){
        //cenario
        CategoriaRequestDomain categoriaRequestDomain = CategoriaRequestDomain.builder()
                .idCategoria(null)
                .build();
        //condicao
        CategoriaEntity categoria = CategoriaRequestMapper.converterId(categoriaRequestDomain);
        //validacao
        assertNull(categoria.getId());
    }

    /**
     * Teste para checar se o ID da categoria é válido e diferente de nulo
     */
    @Test
    public void testeConverterCategoriaRequestSucessoId(){
        //cenario
        CategoriaRequestDomain categoriaRequestDomain = CategoriaRequestDomain.builder()
                .idCategoria(1)
                .build();
        //condicao
        CategoriaEntity categoria = CategoriaRequestMapper.converterId(categoriaRequestDomain);
        //validacao
        assertEquals(1, categoria.getId());
    }

    /**
     * Teste para checar se o objeto entity da categoria é válido
     */
    @Test
    public void testeConverterCategoriaResponseSucesso(){
        //cenario
        CategoriaResponseDomain categoriaResponseDomain = CategoriaResponseDomain.builder()
                .nomeCategoria("Alimento")
                .idCategoria(1)
                .build();
        //condicao
        CategoriaEntity categoria = CategoriaRequestMapper.converterParaAtualizacao(categoriaResponseDomain);
        //validacao
        assertAll(() -> {
            assertEquals(1, categoria.getId());
            assertEquals("Alimento",categoria.getNomeCategoria());
        });
    }

    /**
     * Teste para checar se o Id da categoria é válido e não nulo
     */
    @Test
    public void testeConverterIdCategoriaResponseSucesso(){
        //cenario
        CategoriaResponseDomain categoriaResponseDomain = CategoriaResponseDomain.builder()
                .idCategoria(1)
                .build();
        //condicao
        CategoriaEntity categoria = CategoriaRequestMapper.converterParaAtualizacao(categoriaResponseDomain);
        //validacao
        assertEquals(1, categoria.getId());
    }
}
