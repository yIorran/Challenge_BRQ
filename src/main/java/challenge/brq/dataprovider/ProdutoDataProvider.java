package challenge.brq.dataprovider;

import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.gateway.ProdutoGateway;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProdutoDataProvider implements ProdutoGateway {

    @Override
    public List<ProdutoResponseDomain> consultarProdutos(ProdutoRequestDomain produtoRequestDomain) {
        return null;
    }

}
