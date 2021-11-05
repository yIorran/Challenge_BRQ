package challenge.brq.usecase.model.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TabelaNutricionalResponseDomain {

    private String valorEnergetico;
    private String gorduraSaturada;
    private String sodio;
    private String acucar;
    private String proteinas;
    private String fibras;

}
