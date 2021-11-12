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

    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String valorEnergetico;
    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String gorduraSaturada;
    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String sodio;
    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String acucar;
    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String proteinas;
    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String fibras;

}
