package challenge.brq.entrypoint.mapper.request;

import challenge.brq.entrypoint.model.request.TabelaNutricionalModelRequest;
import challenge.brq.usecase.model.request.TabelaNutricionalRequestDomain;

import java.util.Objects;

public class TabelaNutricionalEntryPointMapperRequest {

    public static TabelaNutricionalRequestDomain converter(TabelaNutricionalModelRequest tabelaNutricionalModelRequest){
        if(Objects.isNull(tabelaNutricionalModelRequest)){
            TabelaNutricionalRequestDomain.builder().build();
            return null;
        }
        return TabelaNutricionalRequestDomain.builder()
                .tabelaNutricional(tabelaNutricionalModelRequest.getTabelaNutricional())
                .gorduraSaturada(tabelaNutricionalModelRequest.getGorduraSaturada())
                .valorEnergetico(tabelaNutricionalModelRequest.getValorEnergetico())
                .acucar(tabelaNutricionalModelRequest.getAcucar())
                .fibras(tabelaNutricionalModelRequest.getFibras())
                .proteinas(tabelaNutricionalModelRequest.getProteinas())
                .sodio(tabelaNutricionalModelRequest.getSodio())
                .build();
    }

}
