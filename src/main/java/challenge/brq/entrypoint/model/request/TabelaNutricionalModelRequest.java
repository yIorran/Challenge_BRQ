package challenge.brq.entrypoint.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TabelaNutricionalModelRequest {

    @NotBlank
    private String tabelaNutricional;
    @NotBlank
    private String valorEnergetico;
    @NotBlank
    private String gorduraSaturada;
    @NotBlank
    private String sodio;
    @NotBlank
    private String acucar;
    @NotBlank
    private String proteinas;
    @NotBlank
    private String fibras;

}
