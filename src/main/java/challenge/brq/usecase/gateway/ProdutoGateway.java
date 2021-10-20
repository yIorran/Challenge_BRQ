package challenge.brq.usecase.gateway;

import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;

import java.util.List;

public interface ProdutoGateway {

    List<ProdutoResponseDomain> consultarProdutos();

    ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto);

    ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain);

    void excluirProdutosPeloId(Integer idProduto);

    List<ProdutoResponseDomain> consultarProdutosPelaMarcaOuCategoria(String marcaOuCategoria);

    ProdutoResponseDomain atualizaProdutos(ProdutoResponseDomain produtoResponseDomain);

    ProdutoResponseDomain atualizarProdutosParcial(ProdutoResponseDomain produtoResponseDomain);
}
