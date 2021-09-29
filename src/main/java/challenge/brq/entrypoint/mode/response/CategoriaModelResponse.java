package challenge.brq.entrypoint.mode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class CategoriaModelResponse {

    @JsonProperty(value = "nome_categoria")
    private String nomeCategoria;
    @JsonProperty(value = "id_categoria")
    private Integer idCategoria;

}
