package challenge.brq.entrypoint.model.response;

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


    @JsonProperty("id")
    private Integer idCategoria;
    @JsonProperty("nome")
    private String nomeCategoria;

}
