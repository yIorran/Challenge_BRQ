package challenge.brq.dataprovider.response;


import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.dataprovider.entity.TabelaNutricionalEntity;
import challenge.brq.dataprovider.mapper.request.ProdutoRequestMapper;
import challenge.brq.dataprovider.mapper.response.ProdutoResponseMapper;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.request.TabelaNutricionalRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.model.response.TabelaNutricionalResponseDomain;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoResponseMapperTest {

    @Test
    public void testeConverterProdutoRequestSucesso() {
        //cenario
        ProdutoEntity produtoEntity = ProdutoEntity.builder()
                .nomeProduto("Arroz")
                .descricaoProduto("Arroz branco")
                .marcaProduto("Arrozx")
                .quantidadeProduto(1)
                .precoProduto(2.0)
                .produtoAtivo(true)
                .produtoOfertado(false)
                .porcentagemoferta(0)
                .categoria(CategoriaEntity.builder()
                        .id(1)
                        .build())
                .tabelaNutricionalEntity(TabelaNutricionalEntity.builder()
                        .tabelaNutricional("1")
                        .gorduraSaturada("2")
                        .valorEnergetico("3")
                        .acucar("4")
                        .fibras("5")
                        .proteinas("6")
                        .sodio("7")
                        .build())
                .build();
        //condicao
        ProdutoResponseDomain produto = ProdutoResponseMapper.converterProdutoComTodosAtributos(produtoEntity);
        //validacao
        assertAll(() -> {
            assertEquals("Arroz", produto.getNomeProduto());
            assertEquals("Arroz branco", produto.getDescricaoProduto());
            assertEquals("Arrozx", produto.getMarcaProduto());
            assertEquals(1, produto.getQuantidadeProduto());
            assertEquals(2.0, produto.getPrecoProduto());
            assertEquals(true, produto.getProdutoAtivo());
            assertEquals(false, produto.getProdutoOfertado());
            assertEquals(0, produto.getPorcentagem());
            assertEquals("1", produto.getTabelaNutricionalResponseDomain().getTabelaNutricional());
            assertEquals("2", produto.getTabelaNutricionalResponseDomain().getGorduraSaturada());
            assertEquals("3", produto.getTabelaNutricionalResponseDomain().getValorEnergetico());
            assertEquals("4", produto.getTabelaNutricionalResponseDomain().getAcucar());
            assertEquals("5", produto.getTabelaNutricionalResponseDomain().getFibras());
            assertEquals("6", produto.getTabelaNutricionalResponseDomain().getProteinas());
            assertEquals("7", produto.getTabelaNutricionalResponseDomain().getSodio());
            assertEquals(1, produto.getCategoria().getIdCategoria());
        });
    }

    @Test
    public void testeConverterPageTodosAtributos(){
        Page<ProdutoEntity> produtoEntity = new PageImpl<>(getListProdutoEntity());
        Page<ProdutoResponseDomain>produtoResponseDomain = ProdutoResponseMapper.converterPaginaComTodosAtributos(produtoEntity);
        assertAll(() -> {
            assertEquals("Arroz", produtoResponseDomain.getContent().get(0).getNomeProduto());
            assertEquals("Arroz branco", produtoResponseDomain.getContent().get(0).getDescricaoProduto());
            assertEquals("Arrozx", produtoResponseDomain.getContent().get(0).getMarcaProduto());
            assertEquals(1, produtoResponseDomain.getContent().get(0).getQuantidadeProduto());
            assertEquals(2.0, produtoResponseDomain.getContent().get(0).getPrecoProduto());
            assertEquals(true, produtoResponseDomain.getContent().get(0).getProdutoAtivo());
            assertEquals(false, produtoResponseDomain.getContent().get(0).getProdutoOfertado());
            assertEquals(0, produtoResponseDomain.getContent().get(0).getPorcentagem());
            assertEquals("1", produtoResponseDomain.getContent().get(0).getTabelaNutricionalResponseDomain().getTabelaNutricional());
            assertEquals("2", produtoResponseDomain.getContent().get(0).getTabelaNutricionalResponseDomain().getGorduraSaturada());
            assertEquals("3", produtoResponseDomain.getContent().get(0).getTabelaNutricionalResponseDomain().getValorEnergetico());
            assertEquals("4", produtoResponseDomain.getContent().get(0).getTabelaNutricionalResponseDomain().getAcucar());
            assertEquals("5", produtoResponseDomain.getContent().get(0).getTabelaNutricionalResponseDomain().getFibras());
            assertEquals("6", produtoResponseDomain.getContent().get(0).getTabelaNutricionalResponseDomain().getProteinas());
            assertEquals("7", produtoResponseDomain.getContent().get(0).getTabelaNutricionalResponseDomain().getSodio());
            assertEquals(1, produtoResponseDomain.getContent().get(0).getCategoria().getIdCategoria());
        });
    }

    @Test
    public void testeConverterPagePadrao(){
        Page<ProdutoEntity> produtoEntity = new PageImpl<>(getListProdutoEntity());
        Page<ProdutoResponseDomain>produtoResponseDomain = ProdutoResponseMapper.converterPaginaPadrao(produtoEntity);
        assertAll(() -> {
            assertEquals("Arroz", produtoResponseDomain.getContent().get(0).getNomeProduto());
            assertEquals("Arroz branco", produtoResponseDomain.getContent().get(0).getDescricaoProduto());
            assertEquals("Arrozx", produtoResponseDomain.getContent().get(0).getMarcaProduto());
            assertEquals(1, produtoResponseDomain.getContent().get(0).getQuantidadeProduto());
            assertEquals(2.0, produtoResponseDomain.getContent().get(0).getPrecoProduto());
            assertEquals(true, produtoResponseDomain.getContent().get(0).getProdutoAtivo());
            assertEquals(false, produtoResponseDomain.getContent().get(0).getProdutoOfertado());
            assertEquals(0, produtoResponseDomain.getContent().get(0).getPorcentagem());
        });
    }


    @Test
    public void testeConverterList(){
        List<ProdutoEntity> produto = getListProdutoEntity();
        List<ProdutoResponseDomain>produtoResponseDomain = ProdutoResponseMapper.converter(produto);
        assertAll(() -> {
        assertEquals("Arroz", produtoResponseDomain.get(0).getNomeProduto());
        assertEquals("Arroz branco", produtoResponseDomain.get(0).getDescricaoProduto());
        assertEquals("Arrozx", produtoResponseDomain.get(0).getMarcaProduto());
        assertEquals(1, produtoResponseDomain.get(0).getQuantidadeProduto());
        assertEquals(2.0, produtoResponseDomain.get(0).getPrecoProduto());
        assertEquals(true, produtoResponseDomain.get(0).getProdutoAtivo());
        assertEquals(false, produtoResponseDomain.get(0).getProdutoOfertado());
        assertEquals(0, produtoResponseDomain.get(0).getPorcentagem());
        assertEquals("1", produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getTabelaNutricional());
        assertEquals("2", produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getGorduraSaturada());
        assertEquals("3", produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getValorEnergetico());
        assertEquals("4", produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getAcucar());
        assertEquals("5", produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getFibras());
        assertEquals("6", produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getProteinas());
        assertEquals("7", produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getSodio());
        assertEquals(1, produtoResponseDomain.get(0).getCategoria().getIdCategoria());
        });
    }


    public List<ProdutoEntity> getListProdutoEntity(){
        List<ProdutoEntity> produtoEntity = new ArrayList<>();
        produtoEntity.add(ProdutoEntity.builder()
                .nomeProduto("Arroz")
                .descricaoProduto("Arroz branco")
                .marcaProduto("Arrozx")
                .quantidadeProduto(1)
                .precoProduto(2.0)
                .produtoAtivo(true)
                .produtoOfertado(false)
                .porcentagemoferta(0)
                .categoria(CategoriaEntity.builder()
                        .id(1)
                        .build())
                .tabelaNutricionalEntity(TabelaNutricionalEntity.builder()
                        .tabelaNutricional("1")
                        .gorduraSaturada("2")
                        .valorEnergetico("3")
                        .acucar("4")
                        .fibras("5")
                        .proteinas("6")
                        .sodio("7")
                        .build())
                .build());
        return produtoEntity;
    }

    }

