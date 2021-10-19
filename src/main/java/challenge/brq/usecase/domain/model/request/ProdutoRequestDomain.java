package challenge.brq.usecase.domain.model.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;


@ToString
@Builder
@Getter
public class ProdutoRequestDomain {

    private Integer codigoProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private String marcaProduto;
    private Integer quantidadeProduto;
    private Double precoProduto;
    private Boolean produtoAtivo;
    private Boolean produtoOfertado;
    private Integer porcentagem;
    private CategoriaRequestDomain categoria;


}
