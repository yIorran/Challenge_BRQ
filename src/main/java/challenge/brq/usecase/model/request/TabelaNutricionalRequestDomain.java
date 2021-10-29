package challenge.brq.usecase.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
@Builder
public class TabelaNutricionalRequestDomain {

    private String tabelaNutricional;
    private String valorEnergetico;
    private String gorduraSaturada;
    private String sodio;
    private String acucar;
    private String proteinas;
    private String fibras;

}
