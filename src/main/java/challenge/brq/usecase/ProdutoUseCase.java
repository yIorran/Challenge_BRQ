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

    public List<ProdutoResponseDomain> consultarProdutos(){
        return produtoGateway.consultarProdutos();
    }

    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain){
        return produtoGateway.adicionaProdutos(produtoRequestDomain);
    }

    public void excluiProdutoPeloId(Integer idCategoria){
        produtoGateway.excluirProdutosPeloId(idCategoria);
    }

    public ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto){
        return produtoGateway.consultarProdutosPeloId(idProduto);
    }

}
