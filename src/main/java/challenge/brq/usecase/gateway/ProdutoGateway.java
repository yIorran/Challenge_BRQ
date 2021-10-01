package challenge.brq.usecase.gateway;

import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;

import java.util.List;

public interface ProdutoGateway {

    List<ProdutoResponseDomain> consultarProdutos();

    ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto);

    ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain);

    void excluirProdutosPeloId(Integer idProduto);
}
