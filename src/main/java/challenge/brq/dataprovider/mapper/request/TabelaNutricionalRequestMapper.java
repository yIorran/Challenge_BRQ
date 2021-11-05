package challenge.brq.dataprovider.mapper.request;

import challenge.brq.dataprovider.entity.TabelaNutricionalEntity;
import challenge.brq.usecase.model.request.TabelaNutricionalRequestDomain;
import challenge.brq.usecase.model.response.TabelaNutricionalResponseDomain;

import java.util.Objects;

public class TabelaNutricionalRequestMapper {

    public static TabelaNutricionalEntity converter(TabelaNutricionalRequestDomain tabelaNutricionalRequestDomain){
        if(Objects.isNull(tabelaNutricionalRequestDomain)){
            return TabelaNutricionalEntity.builder().build();
        }
        return TabelaNutricionalEntity.builder()
                .gorduraSaturada(tabelaNutricionalRequestDomain.getGorduraSaturada())
                .valorEnergetico(tabelaNutricionalRequestDomain.getValorEnergetico())
                .acucar(tabelaNutricionalRequestDomain.getAcucar())
                .fibras(tabelaNutricionalRequestDomain.getFibras())
                .proteinas(tabelaNutricionalRequestDomain.getProteinas())
                .sodio(tabelaNutricionalRequestDomain.getSodio())
                .build();
    }

    public static TabelaNutricionalEntity converterResponse(TabelaNutricionalResponseDomain tabelaNutricionalResponseDomain){
        if(Objects.isNull(tabelaNutricionalResponseDomain)){
            return TabelaNutricionalEntity.builder().build();
        }
        return TabelaNutricionalEntity.builder()
                .gorduraSaturada(tabelaNutricionalResponseDomain.getGorduraSaturada())
                .valorEnergetico(tabelaNutricionalResponseDomain.getValorEnergetico())
                .acucar(tabelaNutricionalResponseDomain.getAcucar())
                .fibras(tabelaNutricionalResponseDomain.getFibras())
                .proteinas(tabelaNutricionalResponseDomain.getProteinas())
                .sodio(tabelaNutricionalResponseDomain.getSodio())
                .build();
    }

}
