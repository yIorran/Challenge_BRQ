package challenge.brq.entrypoint.request;

import challenge.brq.entrypoint.mapper.request.ProdutoEntryPointMapperRequest;
import challenge.brq.entrypoint.model.request.CategoriaModelRequestID;
import challenge.brq.entrypoint.model.request.ProdutoModelRequest;
import challenge.brq.entrypoint.model.request.TabelaNutricionalModelRequest;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdutoEntryPointMapperRequestTest {

    @Test
    public void converterProdutoParaResposta(){
        ProdutoRequestDomain produtoRequestDomain = ProdutoEntryPointMapperRequest.converter(ProdutoModelRequest.builder()
                .idProduto(1)
                .nome("Arroz")
                .descricao("Arroz branco")
                .marca("Arrozx")
                .quantidade(1)
                .preco(2.0)
                .ativo(true)
                .ofertado(false)
                .porcentagem(0)
                .categoria(CategoriaModelRequestID.builder().id(1).build())
                .tabelaNutricional(TabelaNutricionalModelRequest.builder()
                        .gorduraSaturada("2")
                        .valorEnergetico("3")
                        .acucar("4")
                        .fibras("5")
                        .proteinas("6")
                        .sodio("7")
                        .build())
                .build());
        assertAll(() -> {
            assertEquals("Arroz", produtoRequestDomain.getNomeProduto());
            assertEquals("Arroz branco" ,produtoRequestDomain.getDescricaoProduto());
            assertEquals("Arrozx" ,produtoRequestDomain.getMarcaProduto());
            assertEquals(1 ,produtoRequestDomain.getQuantidadeProduto());
            assertEquals(2.0 ,produtoRequestDomain.getPrecoProduto());
            assertEquals(true ,produtoRequestDomain.getProdutoAtivo());
            assertEquals(false ,produtoRequestDomain.getProdutoOfertado());
            assertEquals(0 ,produtoRequestDomain.getPorcentagem());
            assertEquals("2", produtoRequestDomain.getTabelaNutricionalRequestDomain().getGorduraSaturada());
            assertEquals("3", produtoRequestDomain.getTabelaNutricionalRequestDomain().getValorEnergetico());
            assertEquals("4", produtoRequestDomain.getTabelaNutricionalRequestDomain().getAcucar());
            assertEquals("5", produtoRequestDomain.getTabelaNutricionalRequestDomain().getFibras());
            assertEquals("6", produtoRequestDomain.getTabelaNutricionalRequestDomain().getProteinas());
            assertEquals("7", produtoRequestDomain.getTabelaNutricionalRequestDomain().getSodio());
            assertEquals(1, produtoRequestDomain.getCategoria().getIdCategoria());
        });
    }

    @Test
    public void converterProdutoAtualizaParcialParaResposta(){
        ProdutoRequestDomain produtoRequestDomain = ProdutoEntryPointMapperRequest.converterParaAtualizacaoParcial(ProdutoModelRequest.builder()
                .idProduto(1)
                .nome("Arroz")
                .descricao("Arroz branco")
                .marca("Arrozx")
                .quantidade(1)
                .preco(2.0)
                .ativo(true)
                .ofertado(false)
                .porcentagem(0)
                .categoria(CategoriaModelRequestID.builder().id(1).build())
                .tabelaNutricional(TabelaNutricionalModelRequest.builder()
                        .gorduraSaturada("2")
                        .valorEnergetico("3")
                        .acucar("4")
                        .fibras("5")
                        .proteinas("6")
                        .sodio("7")
                        .build())
                .build());
        assertAll(() -> {
            assertEquals("Arroz", produtoRequestDomain.getNomeProduto());
            assertEquals("Arroz branco" ,produtoRequestDomain.getDescricaoProduto());
            assertEquals("Arrozx" ,produtoRequestDomain.getMarcaProduto());
            assertEquals(1 ,produtoRequestDomain.getQuantidadeProduto());
            assertEquals(2.0 ,produtoRequestDomain.getPrecoProduto());
            assertEquals(true ,produtoRequestDomain.getProdutoAtivo());
            assertEquals(false ,produtoRequestDomain.getProdutoOfertado());
            assertEquals(0 ,produtoRequestDomain.getPorcentagem());
            assertEquals("2", produtoRequestDomain.getTabelaNutricionalRequestDomain().getGorduraSaturada());
            assertEquals("3", produtoRequestDomain.getTabelaNutricionalRequestDomain().getValorEnergetico());
            assertEquals("4", produtoRequestDomain.getTabelaNutricionalRequestDomain().getAcucar());
            assertEquals("5", produtoRequestDomain.getTabelaNutricionalRequestDomain().getFibras());
            assertEquals("6", produtoRequestDomain.getTabelaNutricionalRequestDomain().getProteinas());
            assertEquals("7", produtoRequestDomain.getTabelaNutricionalRequestDomain().getSodio());
            assertEquals(1, produtoRequestDomain.getCategoria().getIdCategoria());
        });
    }

}
