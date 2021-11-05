package challenge.brq.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TabelaNutricionalModelResponse {

    @JsonProperty(value = "valorEnergetico")
    private String valorEnergetico;
    @JsonProperty(value = "gorduraSaturada")
    private String gorduraSaturada;
    @JsonProperty(value = "sodio")
    private String sodio;
    @JsonProperty(value = "acucar")
    private String acucar;
    @JsonProperty(value = "proteinas")
    private String proteinas;
    @JsonProperty(value = "fibras")
    private String fibras;

}
