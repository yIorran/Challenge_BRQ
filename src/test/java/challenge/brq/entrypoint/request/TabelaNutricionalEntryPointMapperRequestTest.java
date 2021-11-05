package challenge.brq.entrypoint.request;

import challenge.brq.entrypoint.mapper.request.TabelaNutricionalEntryPointMapperRequest;
import challenge.brq.entrypoint.model.request.TabelaNutricionalModelRequest;
import challenge.brq.usecase.model.request.TabelaNutricionalRequestDomain;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TabelaNutricionalEntryPointMapperRequestTest {

    @Test
    public void testeConverterTabelaNutricionalRequestSucesso(){
        TabelaNutricionalRequestDomain tabelaNutricionalRequestDomain = TabelaNutricionalEntryPointMapperRequest.converter(TabelaNutricionalModelRequest.builder()
                .gorduraSaturada("2")
                .valorEnergetico("3")
                .acucar("4")
                .fibras("5")
                .proteinas("6")
                .sodio("7")
                .build());
        assertAll(() -> {
            assertEquals("2", tabelaNutricionalRequestDomain.getGorduraSaturada());
            assertEquals("3", tabelaNutricionalRequestDomain.getValorEnergetico());
            assertEquals("4", tabelaNutricionalRequestDomain.getAcucar());
            assertEquals("5", tabelaNutricionalRequestDomain.getFibras());
            assertEquals("6", tabelaNutricionalRequestDomain.getProteinas());
            assertEquals("7", tabelaNutricionalRequestDomain.getSodio());
        });
    }

}
