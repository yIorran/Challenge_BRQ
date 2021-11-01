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

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoResponseMapperTest {

    private final CategoriaEntity categoria = CategoriaEntity.builder()
            .id(1)
            .build();
    private final TabelaNutricionalEntity tabelaNutricionalEntity = TabelaNutricionalEntity.builder()
            .tabelaNutricional("1")
            .gorduraSaturada("2")
            .valorEnergetico("3")
            .acucar("4")
            .fibras("5")
            .proteinas("6")
            .sodio("7")
            .build();
    @Test
    public void testeConverterProdutoRequestSucesso(){
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
                .categoria(categoria)
                .tabelaNutricionalEntity(tabelaNutricionalEntity)
                .build();
        //condicao
        ProdutoResponseDomain produto = ProdutoResponseMapper.converterProdutoComTodosAtributos(produtoEntity);
        //validacao
        assertAll(() -> {
            assertEquals("Arroz", produto.getNomeProduto());
            assertEquals("Arroz branco" ,produto.getDescricaoProduto());
            assertEquals("Arrozx" ,produto.getMarcaProduto());
            assertEquals(1 ,produto.getQuantidadeProduto());
            assertEquals(2.0 ,produto.getPrecoProduto());
            assertEquals(true ,produto.getProdutoAtivo());
            assertEquals(false ,produto.getProdutoOfertado());
            assertEquals(0 ,produto.getPorcentagem());
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

    private final CategoriaResponseDomain categoriaResponseDomain = CategoriaResponseDomain.builder()
            .nomeCategoria("Alimento")
            .idCategoria(1)
            .build();
    private final TabelaNutricionalResponseDomain tabelaNutricionalResponseDomain = TabelaNutricionalResponseDomain.builder()
            .tabelaNutricional("1")
            .gorduraSaturada("2")
            .valorEnergetico("3")
            .acucar("4")
            .fibras("5")
            .proteinas("6")
            .sodio("7")
            .build();

    @Test
    public void testeConverterProdutoRequestAtualizacaoSucesso(){
        //cenario
        ProdutoResponseDomain produtoRequestDomain = ProdutoResponseDomain.builder()
                .nomeProduto("Arroz")
                .descricaoProduto("Arroz branco")
                .marcaProduto("Arrozx")
                .quantidadeProduto(1)
                .precoProduto(2.0)
                .produtoAtivo(true)
                .produtoOfertado(false)
                .porcentagem(0)
                .categoria(categoriaResponseDomain)
                .tabelaNutricionalResponseDomain(tabelaNutricionalResponseDomain)
                .build();
        //condicao
        ProdutoEntity produto = ProdutoRequestMapper.converterParaAtualizacao(produtoRequestDomain);
        //validacao
        assertAll(() -> {
            assertEquals("Arroz", produto.getNomeProduto());
            assertEquals("Arroz branco" ,produto.getDescricaoProduto());
            assertEquals("Arrozx" ,produto.getMarcaProduto());
            assertEquals(1 ,produto.getQuantidadeProduto());
            assertEquals(2.0 ,produto.getPrecoProduto());
            assertEquals(true ,produto.getProdutoAtivo());
            assertEquals(false ,produto.getProdutoOfertado());
            assertEquals(0 ,produto.getPorcentagemoferta());
            assertEquals("1", produto.getTabelaNutricionalEntity().getTabelaNutricional());
            assertEquals("2", produto.getTabelaNutricionalEntity().getGorduraSaturada());
            assertEquals("3", produto.getTabelaNutricionalEntity().getValorEnergetico());
            assertEquals("4", produto.getTabelaNutricionalEntity().getAcucar());
            assertEquals("5", produto.getTabelaNutricionalEntity().getFibras());
            assertEquals("6", produto.getTabelaNutricionalEntity().getProteinas());
            assertEquals("7", produto.getTabelaNutricionalEntity().getSodio());
            assertEquals(1, produto.getCategoria().getId());
        });
    }
}
