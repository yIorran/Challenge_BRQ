package challenge.brq.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProdutoModelResponse {

    @JsonProperty(value = "id")
    private Integer codigoProduto;
    @JsonProperty(value = "nome")
    private String nomeProduto;
    @JsonProperty(value = "descricao")
    private String descricaoProduto;
    @JsonProperty(value = "marca")
    private String marcaProduto;
    @JsonProperty(value = "quantidade")
    private Integer quantidadeProduto;
    @JsonProperty(value = "preco")
    private Double precoProduto;
    @JsonProperty(value = "ativo")
    private Boolean ativo;
    @JsonProperty(value = "ofertado")
    private Boolean ofertado;
    @JsonProperty(value = "porcentagem")
    private Integer porcentagem;

}
