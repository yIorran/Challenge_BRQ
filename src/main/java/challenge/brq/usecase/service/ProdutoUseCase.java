package challenge.brq.usecase.service;

import challenge.brq.usecase.exception.categoria.CategoriaNaoExistenteParaAtualizacaoParcialException;
import challenge.brq.usecase.exception.produto.ProdutoPorIDNaoEncontrado;
import challenge.brq.usecase.gateway.CategoriaGateway;
import challenge.brq.usecase.gateway.ProdutoGateway;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.utils.Utils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    private final CategoriaGateway categoriaGateway;

    public List<ProdutoResponseDomain> consultarProdutosPeloStatus(Boolean status){
        return produtoGateway.consultarProdutoPorStatus(true);
    }

    public List<ProdutoResponseDomain> consultarProdutos() {
        return produtoGateway.consultarProdutos();
    }

    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain) {
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloId(produtoRequestDomain.getCategoria().getIdCategoria());
        Utils.verificarSeCategoriaExisteParaAdicionar(categoriaResponseDomain);
        Utils.verificarSeQuantidadeMaiorIgualZero(produtoRequestDomain.getQuantidadeProduto());
        return produtoGateway.adicionaProdutos(produtoRequestDomain);
    }

    public void excluiProdutoPeloId(Integer idCategoria) {
        consultarProdutosPeloId(idCategoria);
        produtoGateway.excluirProdutosPeloId(idCategoria);
    }

    public ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto) {
        if (produtoGateway.consultarProdutosPeloId(idProduto) == null) {
            throw new ProdutoPorIDNaoEncontrado("Id não encontrado em nossa base: " + idProduto);
        } else
            return produtoGateway.consultarProdutosPeloId(idProduto);
    }

    public ProdutoResponseDomain consultarCategoriaPeloIdParaAtualizarParcial(Integer idProduto) {
        if (idProduto == null) {
            return ProdutoResponseDomain.builder().build();
        }
        if (produtoGateway.consultarProdutosPeloId(idProduto) == null) {
            throw new CategoriaNaoExistenteParaAtualizacaoParcialException("Categoria informada inexistente para atualização");
        } else
            return produtoGateway.consultarProdutosPeloId(idProduto);
    }

    public List<ProdutoResponseDomain> consultarProdutosPelaMarcaOuCategoria(String marca, String categoria) {
        if (categoria != null) {
            return consultarProdutosPelaCategoria(categoria);
        } else if (marca != null) {
            return consultarProdutosPelaMarca(marca);
        }
        return consultarProdutos();
    }

    public List<ProdutoResponseDomain> consultarProdutosPelaMarca(String marca) {
        return produtoGateway.consultarProdutosPelaMarca(marca);
    }

    public List<ProdutoResponseDomain> consultarProdutosPelaCategoria(String categoria) {
        return produtoGateway.consultarProdutosPelaCategoria(categoria);
    }

    public ProdutoResponseDomain atualizarProdutosParcial(Integer id, ProdutoRequestDomain produtoRequestDomain) {
        consultarCategoriaPeloIdParaAtualizarParcial(produtoRequestDomain.getCategoria().getIdCategoria());
        ProdutoResponseDomain produtoAtual = consultarProdutosPeloId(id);
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloId(produtoRequestDomain.getCategoria().getIdCategoria());
        Utils.verificarSeCategoriaExisteParaAtualizarParcial(categoriaResponseDomain);
        produtoAtual = Utils.converterProduto(produtoRequestDomain, produtoAtual);
        Utils.verificarSeStatusDoProdutoEAtivoAposModificacoes(produtoAtual);
        Utils.verificarSePorcentagemMaiorQueZeroAposModificacoes(produtoAtual);
        Utils.verificarSeOfertadoAtivoEStatusAtivoAposModificacoes(produtoAtual);
        return produtoGateway.atualizarProdutosParcial(produtoAtual);
    }
}