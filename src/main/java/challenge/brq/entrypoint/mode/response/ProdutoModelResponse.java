package challenge.brq.entrypoint.mode.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@ToString
public class ProdutoModelResponse {

    @JsonProperty(value = "codigo_produto")
    private Integer codigoProduto;
    @JsonProperty(value = "nome_produto")
    private String nomeProduto;

}
