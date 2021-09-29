package challenge.brq.usecase;

import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public List<ProdutoResponseDomain> consultarProdutos(ProdutoRequestDomain produtoRequestDomain){
        return produtoGateway.consultarProdutos(produtoRequestDomain);
    }

}
