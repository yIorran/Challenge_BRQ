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
    @NotBlank
    private String nome;
    @NotBlank
    private String descricao;
    @NotBlank
    private String marca;
    @NotNull
    private Integer quantidade;
    @NotNull
    @PositiveOrZero
    private Double preco;
    private Boolean ativo;
    private Boolean ofertado;
    private Integer porcentagem;
    @NotNull
    @Valid
    private CategoriaModelRequestID categoria;
    @Valid
    private TabelaNutricionalModelRequest tabelaNutricional;


}
