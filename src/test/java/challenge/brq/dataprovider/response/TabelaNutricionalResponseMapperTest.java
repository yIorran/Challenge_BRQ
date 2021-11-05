package challenge.brq.dataprovider.response;


import challenge.brq.entrypoint.mapper.response.TabelaNutricionalEntryPointMapperResponse;
import challenge.brq.entrypoint.model.response.TabelaNutricionalModelResponse;
import challenge.brq.usecase.model.response.TabelaNutricionalResponseDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabelaNutricionalResponseMapperTest {

    @Test
    public void testeTabelaNutricionalEntryPointMapperResponse(){
        TabelaNutricionalResponseDomain tabelaNutricionalRequestDomain = TabelaNutricionalResponseDomain.builder()
                .gorduraSaturada("2")
                .valorEnergetico("3")
                .acucar("4")
                .fibras("5")
                .proteinas("6")
                .sodio("7")
                .build();
        TabelaNutricionalModelResponse tabelaNutricional = TabelaNutricionalEntryPointMapperResponse.converter(tabelaNutricionalRequestDomain);
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
