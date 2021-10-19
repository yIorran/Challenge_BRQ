package challenge.brq.entrypoint.model.response;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@Builder
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProdutoModelResponse {

    @JsonProperty(value = "id")
    private Integer idProduto;
    @JsonProperty(value = "nome")
    private String nome;
    @JsonProperty(value = "descricao")
    private String descricao;
    @JsonProperty(value = "marca")
    private String marca;
    @JsonProperty(value = "quantidade")
    private Integer quantidade;
    @JsonProperty(value = "preco")
    private Double preco;
    @JsonProperty(value = "ativo")
    private Boolean ativo;
    @JsonProperty(value = "ofertado")
    private Boolean ofertado;
    @JsonProperty(value = "porcentagem")
    private Integer porcentagem;
    @JsonProperty(value = "categoria")
    private CategoriaEntity categoria;

}
