package challenge.brq.entrypoint.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

/**
 * Classe responsavel por conter os atributos de entryPoint
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModelRequest {

    private Integer idProduto;
    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String nome;
    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String descricao;
    @NotBlank(message = "Valor não pode ser nulo ou vazio")
    private String marca;
    @NotNull(message = "Valor não pode ser nulo ou vazio")
    private Integer quantidade;
    @NotNull(message = "Valor não pode ser nulo ou vazio")
    @PositiveOrZero
    private Double preco;
    private Boolean ativo;
    private Boolean ofertado;
    private Integer porcentagem;
    @NotNull(message = "Valor não pode ser nulo ou vazio")
    @Valid
    private CategoriaModelRequestID categoria;
    @Valid
    private TabelaNutricionalModelRequest tabelaNutricional;


}
