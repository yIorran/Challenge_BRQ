package challenge.brq.dataprovider.response;


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
                .tabelaNutricional("1")
                .gorduraSaturada("2")
                .valorEnergetico("3")
                .acucar("4")
                .fibras("5")
                .proteinas("6")
                .sodio("7")
                .build();
        TabelaNutricionalEntity tabelaNutricional = TabelaNutricionalRequestMapper.converter(tabelaNutricionalRequestDomain);
        assertAll(() -> {
            assertEquals("1", tabelaNutricional.getTabelaNutricional());
            assertEquals("2", tabelaNutricional.getGorduraSaturada());
            assertEquals("3", tabelaNutricional.getValorEnergetico());
            assertEquals("4", tabelaNutricional.getAcucar());
            assertEquals("5", tabelaNutricional.getFibras());
            assertEquals("6", tabelaNutricional.getProteinas());
            assertEquals("7", tabelaNutricional.getSodio());
        });
    }

    @Test
    public void testeConverterTabelaNutricionalResponsetSucesso(){
        TabelaNutricionalResponseDomain tabelaNutricionalResponseDomain = TabelaNutricionalResponseDomain.builder()
                .tabelaNutricional("1")
                .gorduraSaturada("2")
                .valorEnergetico("3")
                .acucar("4")
                .fibras("5")
                .proteinas("6")
                .sodio("7")
                .build();
        TabelaNutricionalEntity tabelaNutricional = TabelaNutricionalRequestMapper.converterResponse(tabelaNutricionalResponseDomain);
        assertAll(() -> {
            assertEquals("1", tabelaNutricional.getTabelaNutricional());
            assertEquals("2", tabelaNutricional.getGorduraSaturada());
            assertEquals("3", tabelaNutricional.getValorEnergetico());
            assertEquals("4", tabelaNutricional.getAcucar());
            assertEquals("5", tabelaNutricional.getFibras());
            assertEquals("6", tabelaNutricional.getProteinas());
            assertEquals("7", tabelaNutricional.getSodio());
        });
    }

}
