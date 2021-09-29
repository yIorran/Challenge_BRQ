package challenge.brq.usecase.domain.model.response;

import lombok.Builder;
import lombok.Getter;


@Getter
@Builder
public class ProdutoResponseDomain {

    private Integer codigoProduto;
    private String nomeProduto;

}
