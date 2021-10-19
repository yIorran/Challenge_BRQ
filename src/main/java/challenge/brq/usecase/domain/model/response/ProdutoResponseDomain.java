package challenge.brq.usecase.domain.model.response;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ProdutoResponseDomain {

    private Integer codigoProduto;
    private String nomeProduto;
    private String descricaoProduto;
    private String marcaProduto;
    private Integer quantidadeProduto;
    private Double precoProduto;
    private Boolean produtoAtivo;
    private Boolean produtoOfertado;
    private Integer porcentagem;
    private CategoriaResponseDomain categoria;

}
