package challenge.brq.usecase.gateway;

import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoGateway {

    Page<ProdutoResponseDomain> consultarProdutos(Pageable pageable);

    ProdutoResponseDomain consultarProdutosPeloIdExpandirTabelaNutri(Integer idProduto, String exapnd);

    ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto);

    ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain);

    void excluirProdutosPeloId(Integer idProduto);

    Page<ProdutoResponseDomain> consultarProdutosPeloNome(String nome, Double preco, Pageable pageable);

    Page<ProdutoResponseDomain> consultarProdutosPelaMarca(String marca, Double preco,  Pageable pageable);

    Page<ProdutoResponseDomain> consultarProdutosPelaCategoria(String categoria, Double preco, Pageable pageable);

    Page<ProdutoResponseDomain> consultarProdutosPeloPreco(Double preco, Pageable pageable);

    ProdutoResponseDomain atualizarProdutosParcial(ProdutoResponseDomain produtoResponseDomain);

    Page<ProdutoResponseDomain> consultarProdutosParaExclusaoDeCategorias(String nomeOuCategoria, Pageable pageable);

    Page<ProdutoResponseDomain> consultarProdutoPorStatus(Pageable pageable);

}
