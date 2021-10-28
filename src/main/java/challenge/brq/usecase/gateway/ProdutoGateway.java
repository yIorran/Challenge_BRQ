package challenge.brq.usecase.gateway;

import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProdutoGateway {

    Page<ProdutoResponseDomain> consultarProdutos(Pageable pageable);

    ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto);

    ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain);

    void excluirProdutosPeloId(Integer idProduto);

    Page<ProdutoResponseDomain> consultarProdutosPelaMarca(String marca, Pageable pageable);

    Page<ProdutoResponseDomain> consultarProdutosPelaCategoria(String categoria, Pageable pageable);

    ProdutoResponseDomain atualizarProdutosParcial(ProdutoResponseDomain produtoResponseDomain);

    Page<ProdutoResponseDomain> consultarProdutosParaExclusaoDeCategorias(String nomeOuCategoria, Pageable pageable);

    Page<ProdutoResponseDomain> consultarProdutoPorStatus(Boolean status, Pageable pageable);

}
