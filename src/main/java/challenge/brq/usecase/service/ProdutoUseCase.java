package challenge.brq.usecase.service;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.dataprovider.mapper.response.ProdutoResponseMapper;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public ProdutoResponseDomain ativarProdutoUnico(Integer idProduto) {
        Utils.verificarSeIdExiste(produtoGateway.consultarProdutosPeloId(idProduto));
        return produtoGateway.ativarProdutoUnico(idProduto);
    }

    public ProdutoResponseDomain destivarProdutoUnico(Integer idProduto) {
        Utils.verificarSeIdExiste(produtoGateway.consultarProdutosPeloId(idProduto));
        return produtoGateway.desativarProdutoUnico(idProduto);
    }

    public Page<ProdutoResponseDomain> ativarProdutosEmMassa(List<Integer> produtoRequestDomain) {
        return produtoGateway.consultarProdutosParaAtivacaoEmMassa(produtoRequestDomain);
    }
    public Page<ProdutoResponseDomain> desativarProdutosEmMassa(List<Integer> produtoRequestDomain) {
        return produtoGateway.consultarProdutosParaDesativacaoEmMassa(produtoRequestDomain);
    }

    public void excluiProdutoPeloId(Integer idCategoria) {
        consultarProdutosPeloId(idCategoria);
        produtoGateway.excluirProdutosPeloId(idCategoria);
    }

    public ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto) {
        Utils.verificarSeIdExiste(produtoGateway.consultarProdutosPeloId(idProduto));
        return produtoGateway.consultarProdutosPeloId(idProduto);
    }

    public ProdutoResponseDomain consultarProdutosPeloIdExpandirTabelaNutri(Integer idProduto, String expand) {
        if(expand == null){
            return produtoGateway.consultarProdutosPeloIdExpandirTabelaNutri(idProduto, expand);
        }
        if(!Objects.equals(expand, "tabela_nutricional")){
            throw new TabelaNutricionalValorDiferenteException("Valor não reconhecido: " + expand + " valor disponível para filtro: tabela_nutricional");
        }
        if (produtoGateway.consultarProdutosPeloIdExpandirTabelaNutri(idProduto, expand) == null) {
            throw new ProdutoPorIDNaoEncontrado("Id não encontrado em nossa base: " + idProduto);
        } else
            return produtoGateway.consultarProdutosPeloIdExpandirTabelaNutri(idProduto, expand);
    }

    public CategoriaResponseDomain consultarCategoriaPeloIdParaAtualizarParcial(Integer idProduto) {
        if (idProduto == null) {
            return CategoriaResponseDomain.builder().build();
        }
        if (categoriaGateway.consultarCategoriaPeloId(idProduto) == null) {
            throw new CategoriaNaoExistenteParaAtualizacaoParcialException("Categoria informada inexistente para atualização");
        } else
            return categoriaGateway.consultarCategoriaPeloId(idProduto);
    }

    public Page<ProdutoResponseDomain> consultarProdutosDeFormaDinamica(ProdutoRequestDomain produtoRequestDomain, Pageable pageable) {
         if (Objects.isNull(produtoRequestDomain)) {
             return consultarProdutos(pageable);
        }
         else
        return produtoGateway.consultaDinamicaProdutos(produtoRequestDomain);
    }

    public ProdutoResponseDomain atualizarProdutosParcial(Integer id, ProdutoRequestDomain produtoRequestDomain) {
        ProdutoResponseDomain produtoAtual = consultarProdutosPeloId(id);
        consultarCategoriaPeloIdParaAtualizarParcial(produtoRequestDomain.getCategoria().getIdCategoria());
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