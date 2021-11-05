package challenge.brq.dataprovider.request;


import challenge.brq.dataprovider.entity.TabelaNutricionalEntity;
import challenge.brq.dataprovider.mapper.request.TabelaNutricionalRequestMapper;
import challenge.brq.usecase.model.request.TabelaNutricionalRequestDomain;
import challenge.brq.usecase.model.response.TabelaNutricionalResponseDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabelaNutricionalRequestMapperTest {

    @Test
    public void testeConverterTabelaNutricionalRequestSucesso(){
        TabelaNutricionalRequestDomain tabelaNutricionalRequestDomain = TabelaNutricionalRequestDomain.builder()
                .gorduraSaturada("2")
                .valorEnergetico("3")
                .acucar("4")
                .fibras("5")
                .proteinas("6")
                .sodio("7")
                .build();
        TabelaNutricionalEntity tabelaNutricional = TabelaNutricionalRequestMapper.converter(tabelaNutricionalRequestDomain);
        assertAll(() -> {
            assertEquals("2", tabelaNutricional.getGorduraSaturada());
            assertEquals("3", tabelaNutricional.getValorEnergetico());
            assertEquals("4", tabelaNutricional.getAcucar());
            assertEquals("5", tabelaNutricional.getFibras());
            assertEquals("6", tabelaNutricional.getProteinas());
            assertEquals("7", tabelaNutricional.getSodio());
        });
    }

    @Test
    public void testeConverterTabelaNutricionalResponseSucesso(){
        TabelaNutricionalResponseDomain tabelaNutricionalResponseDomain = TabelaNutricionalResponseDomain.builder()
                .gorduraSaturada("2")
                .valorEnergetico("3")
                .acucar("4")
                .fibras("5")
                .proteinas("6")
                .sodio("7")
                .build();
        TabelaNutricionalEntity tabelaNutricional = TabelaNutricionalRequestMapper.converterResponse(tabelaNutricionalResponseDomain);
        assertAll(() -> {
            assertEquals("2", tabelaNutricional.getGorduraSaturada());
            assertEquals("3", tabelaNutricional.getValorEnergetico());
            assertEquals("4", tabelaNutricional.getAcucar());
            assertEquals("5", tabelaNutricional.getFibras());
            assertEquals("6", tabelaNutricional.getProteinas());
            assertEquals("7", tabelaNutricional.getSodio());
        });
    }

}
