package challenge.brq.entrypoint.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

/**
 * Classe responsavel por conter os atributos de endPoint, esses atributos são mostrados no JSON
 */
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
    private CategoriaModelResponse categoria;
    @JsonProperty(value = "tabelaNutricional")
    private TabelaNutricionalModelResponse tabelaNutricionalModelResponse;

}
