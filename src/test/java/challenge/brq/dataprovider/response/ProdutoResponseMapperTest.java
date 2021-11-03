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
import org.junit.jupiter.api.Test;

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
    public void testeConverterList(){
        List<ProdutoEntity> produto = getListProdutoEntity();
        List<ProdutoResponseDomain>produtoResponseDomain = ProdutoResponseMapper.converter(produto);
        assertAll(() -> {
        assertEquals(produto.get(0).getNomeProduto(), produtoResponseDomain.get(0).getNomeProduto());
        assertEquals(produto.get(0).getDescricaoProduto(), produtoResponseDomain.get(0).getDescricaoProduto());
        assertEquals(produto.get(0).getMarcaProduto(), produtoResponseDomain.get(0).getMarcaProduto());
        assertEquals(produto.get(0).getQuantidadeProduto(), produtoResponseDomain.get(0).getQuantidadeProduto());
        assertEquals(produto.get(0).getPrecoProduto(), produtoResponseDomain.get(0).getPrecoProduto());
        assertEquals(produto.get(0).getProdutoAtivo(), produtoResponseDomain.get(0).getProdutoAtivo());
        assertEquals(produto.get(0).getProdutoOfertado(), produtoResponseDomain.get(0).getProdutoOfertado());
        assertEquals(produto.get(0).getPorcentagemoferta(), produtoResponseDomain.get(0).getPorcentagem());
        assertEquals(produto.get(0).getTabelaNutricionalEntity().getTabelaNutricional(), produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getTabelaNutricional());
        assertEquals(produto.get(0).getTabelaNutricionalEntity().getGorduraSaturada(), produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getGorduraSaturada());
        assertEquals(produto.get(0).getTabelaNutricionalEntity().getValorEnergetico(), produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getValorEnergetico());
        assertEquals(produto.get(0).getTabelaNutricionalEntity().getAcucar(), produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getAcucar());
        assertEquals(produto.get(0).getTabelaNutricionalEntity().getFibras(), produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getFibras());
        assertEquals(produto.get(0).getTabelaNutricionalEntity().getProteinas(), produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getProteinas());
        assertEquals(produto.get(0).getTabelaNutricionalEntity().getSodio(), produtoResponseDomain.get(0).getTabelaNutricionalResponseDomain().getSodio());
        assertEquals(produto.get(0).getCategoria().getId(), produtoResponseDomain.get(0).getCategoria().getIdCategoria());
        });
    }



        public List<ProdutoResponseDomain> getListProdutoResponseDomain(){
            List<ProdutoResponseDomain> produtoResponseDomain = new ArrayList<>();
            produtoResponseDomain.add(ProdutoResponseDomain.builder()
                    .nomeProduto("Arroz")
                    .descricaoProduto("Arroz branco")
                    .marcaProduto("Arrozx")
                    .quantidadeProduto(1)
                    .precoProduto(2.0)
                    .produtoAtivo(true)
                    .produtoOfertado(false)
                    .porcentagem(0)
                    .categoria(CategoriaResponseDomain.builder()
                            .idCategoria(1)
                            .build())
                    .tabelaNutricionalResponseDomain(TabelaNutricionalResponseDomain.builder()
                            .tabelaNutricional("1")
                            .gorduraSaturada("2")
                            .valorEnergetico("3")
                            .acucar("4")
                            .fibras("5")
                            .proteinas("6")
                            .sodio("7")
                            .build())
                    .build());
            return produtoResponseDomain;
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

