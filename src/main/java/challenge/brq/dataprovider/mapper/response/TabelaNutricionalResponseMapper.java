package challenge.brq.dataprovider.mapper.response;

import challenge.brq.dataprovider.entity.TabelaNutricionalEntity;
import challenge.brq.usecase.model.response.TabelaNutricionalResponseDomain;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class TabelaNutricionalResponseMapper {

    public static TabelaNutricionalResponseDomain converterExpand(TabelaNutricionalEntity tabelaNutricionalEntity, String expand){
        if(Objects.isNull(tabelaNutricionalEntity) || StringUtils.isBlank(expand)){
            return TabelaNutricionalResponseDomain.builder().build();
        }
        return TabelaNutricionalResponseDomain.builder()
                .tabelaNutricional(tabelaNutricionalEntity.getTabelaNutricional())
                .gorduraSaturada(tabelaNutricionalEntity.getGorduraSaturada())
                .valorEnergetico(tabelaNutricionalEntity.getValorEnergetico())
                .acucar(tabelaNutricionalEntity.getAcucar())
                .fibras(tabelaNutricionalEntity.getFibras())
                .proteinas(tabelaNutricionalEntity.getProteinas())
                .sodio(tabelaNutricionalEntity.getSodio())
                .build();
    }
    public static TabelaNutricionalResponseDomain converter(TabelaNutricionalEntity tabelaNutricionalEntity){
        if(Objects.isNull(tabelaNutricionalEntity)){
            return TabelaNutricionalResponseDomain.builder().build();
        }
        return TabelaNutricionalResponseDomain.builder()
                .tabelaNutricional(tabelaNutricionalEntity.getTabelaNutricional())
                .gorduraSaturada(tabelaNutricionalEntity.getGorduraSaturada())
                .valorEnergetico(tabelaNutricionalEntity.getValorEnergetico())
                .acucar(tabelaNutricionalEntity.getAcucar())
                .fibras(tabelaNutricionalEntity.getFibras())
                .proteinas(tabelaNutricionalEntity.getProteinas())
                .sodio(tabelaNutricionalEntity.getSodio())
                .build();
    }

}
