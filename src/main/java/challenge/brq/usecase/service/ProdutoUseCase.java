package challenge.brq.usecase.service;

import challenge.brq.usecase.exception.categoria.CategoriaNaoExistenteParaAtualizacaoParcialException;
import challenge.brq.usecase.exception.produto.ProdutoPorIDNaoEncontrado;
import challenge.brq.usecase.exception.produto.TabelaNutricionalValorDiferenteException;
import challenge.brq.usecase.gateway.CategoriaGateway;
import challenge.brq.usecase.gateway.ProdutoGateway;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.utils.Utils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    private final CategoriaGateway categoriaGateway;

    public Page<ProdutoResponseDomain> consultarProdutosPeloStatus(Pageable pageable) {
        return produtoGateway.consultarProdutoPorStatus(pageable);
    }

    public Page<ProdutoResponseDomain> consultarProdutos(Pageable pageable) {
        return produtoGateway.consultarProdutos(pageable);
    }

    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain) {
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloId(produtoRequestDomain.getCategoria().getIdCategoria());
        Utils.verificarSeCategoriaExisteParaAdicionar(categoriaResponseDomain);
        Utils.verificarSeQuantidadeMaiorIgualZero(produtoRequestDomain.getQuantidadeProduto());
        Utils.verificarSePrecoMenorOuIgualAZero(produtoRequestDomain.getPrecoProduto());
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

    public ProdutoResponseDomain consultarProdutosPeloIdExpandirTabelaNutri(Integer idProduto, String expand) {
        if(!Objects.equals(expand, "tabela_nutricional")){
            throw new TabelaNutricionalValorDiferenteException("Valor não reconhecido: " + expand);
        }
        if (produtoGateway.consultarProdutosPeloIdExpandirTabelaNutri(idProduto, expand) == null) {
            throw new ProdutoPorIDNaoEncontrado("Id não encontrado em nossa base: " + idProduto);
        } else
            return produtoGateway.consultarProdutosPeloIdExpandirTabelaNutri(idProduto, expand);
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

    public Page<ProdutoResponseDomain> consultarProdutosPelaMarcaOuCategoria(String marca, String categoria, Pageable pageable) {
        if (StringUtils.isNotBlank(categoria)) {
            return produtoGateway.consultarProdutosPelaCategoria(categoria, pageable);
        } else if (StringUtils.isNotBlank(marca)) {
            return produtoGateway.consultarProdutosPelaMarca(marca, pageable);
        }
        return consultarProdutos(pageable);
    }

    public ProdutoResponseDomain atualizarProdutosParcial(Integer id, ProdutoRequestDomain produtoRequestDomain) {
        consultarCategoriaPeloIdParaAtualizarParcial(produtoRequestDomain.getCategoria().getIdCategoria());
        ProdutoResponseDomain produtoAtual = consultarProdutosPeloId(id);
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloId(produtoRequestDomain.getCategoria().getIdCategoria());
        Utils.verificarSeCategoriaExisteParaAtualizarParcial(categoriaResponseDomain);
        produtoAtual = Utils.converterProdutoComValoresValidos(produtoRequestDomain, produtoAtual);
        Utils.verificarSeStatusDoProdutoEAtivoAposModificacoes(produtoAtual);
        Utils.verificarSePorcentagemMaiorQueZeroAposModificacoes(produtoAtual);
        Utils.verificarSeOfertadoAtivoEStatusAtivoAposModificacoes(produtoAtual);
        Utils.verificarSePrecoMenorOuIgualAZero(produtoAtual.getPrecoProduto());
        return produtoGateway.atualizarProdutosParcial(produtoAtual);
    }
}