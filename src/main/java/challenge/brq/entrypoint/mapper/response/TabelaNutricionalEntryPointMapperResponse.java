package challenge.brq.entrypoint.mapper.response;

import challenge.brq.entrypoint.model.response.TabelaNutricionalModelResponse;
import challenge.brq.usecase.model.response.TabelaNutricionalResponseDomain;

import java.util.Objects;

public class TabelaNutricionalEntryPointMapperResponse {

    public static TabelaNutricionalModelResponse converter(TabelaNutricionalResponseDomain tabelaNutricionalResponseDomain){
        if(Objects.isNull(tabelaNutricionalResponseDomain)){
            return TabelaNutricionalModelResponse.builder().build();
        }
        return TabelaNutricionalModelResponse.builder()
                .gorduraSaturada(tabelaNutricionalResponseDomain.getGorduraSaturada())
                .valorEnergetico(tabelaNutricionalResponseDomain.getValorEnergetico())
                .acucar(tabelaNutricionalResponseDomain.getAcucar())
                .fibras(tabelaNutricionalResponseDomain.getFibras())
                .proteinas(tabelaNutricionalResponseDomain.getProteinas())
                .sodio(tabelaNutricionalResponseDomain.getSodio())
                .build();
    }

}
