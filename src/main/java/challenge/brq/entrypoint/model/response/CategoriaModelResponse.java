package challenge.brq.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * Classe responsavel por conter os atributos de endPoint, esses atributos são mostrados no JSON
 */
@Getter
@Builder
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CategoriaModelResponse {


    @JsonProperty("id")
    private Integer idCategoria;
    @JsonProperty("nome")
    private String nomeCategoria;

}
