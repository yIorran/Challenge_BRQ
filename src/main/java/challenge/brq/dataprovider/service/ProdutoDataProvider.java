package challenge.brq.dataprovider.service;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.dataprovider.mapper.request.ProdutoRequestMapper;
import challenge.brq.dataprovider.mapper.response.ProdutoResponseMapper;
import challenge.brq.dataprovider.repository.ProdutoRepository;
import challenge.brq.usecase.gateway.ProdutoGateway;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@AllArgsConstructor
@Component
public class ProdutoDataProvider implements ProdutoGateway {

    private ProdutoRepository produtoRepository;

    @Override
    public Page<ProdutoResponseDomain> consultarProdutos(Pageable pageable) {
        Page<ProdutoEntity> produtoEntity = produtoRepository.findAll(pageable);
        return ProdutoResponseMapper.converterPaginaPadrao(produtoEntity);
    }

    @Override
    public ProdutoResponseDomain consultarProdutosPeloIdExpandirTabelaNutri(Integer idProduto, String expand) {
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(idProduto);
        if (produtoEntity.isEmpty()) {
            return null;
        }
        return ProdutoResponseMapper.converterProdutoComTodosAtributosExpand(produtoEntity.get(), expand);
    }

    @Override
    public ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto) {
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(idProduto);
        if (produtoEntity.isEmpty()) {
            return null;
        }
        return ProdutoResponseMapper.converterProdutoComBuscaPorIDSemExpand(produtoEntity.get());
    }

    @Transactional
    @Override
    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain) {
        if (produtoRequestDomain.getCategoria().getIdCategoria() == null) {
            return null;
        } else {
            ProdutoEntity produtoEntity = ProdutoRequestMapper.converter(produtoRequestDomain);
            ProdutoEntity produtoEntitySalvo = produtoRepository.save(produtoEntity);
            return ProdutoResponseMapper.converterProdutoComTodosAtributos(produtoEntitySalvo);
        }
    }

    @Transactional
    @Override
    public void excluirProdutosPeloId(Integer idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    @Override
    public Page<ProdutoResponseDomain> consultaDinamicaProdutos(ProdutoRequestDomain produtoRequestDomain) {
        ProdutoEntity produtoEntity = ProdutoRequestMapper.converterTodosAtributos(produtoRequestDomain);
        Page<ProdutoEntity> produtoEntitySalvo = produtoRepository.listarComFiltro(produtoEntity);
        return ProdutoResponseMapper.converterPaginaComTodosAtributos(produtoEntitySalvo);
    }

    @Override
    public Page<ProdutoResponseDomain> consultarProdutosPelaMarca(String marca, Pageable pageable) {
        Page<ProdutoEntity> produtoEntityMarca = produtoRepository.pesquisarPorMarcaProdutoParaExclusao(marca, pageable);
        if (produtoEntityMarca.isEmpty()) {
            consultarProdutosPelaCategoria(marca, pageable);
        }
        return ProdutoResponseMapper.converterPaginaComTodosAtributos(produtoEntityMarca);
    }

    @Override
    public Page<ProdutoResponseDomain> consultarProdutosPelaCategoria(String categoria, Pageable pageable) {
        Page<ProdutoEntity> produtoEntityCategoria = produtoRepository.pesquisarPorNomeCategoria(categoria, pageable);
        return ProdutoResponseMapper.converterPaginaComTodosAtributos(produtoEntityCategoria);
    }

    @Override
    public ProdutoResponseDomain atualizarProdutosParcial(ProdutoResponseDomain produtoResponseDomain) {
        ProdutoEntity produtoEntity = ProdutoRequestMapper.converterParaAtualizacao(produtoResponseDomain);
        ProdutoEntity produtoEntitySalvo = produtoRepository.save(produtoEntity);
        return ProdutoResponseMapper.converterProdutoParaAtualizacao(produtoEntitySalvo);
    }

    @Override
    public Page<ProdutoResponseDomain> consultarProdutosParaExclusaoDeCategorias(String marcaOuCategoria, Pageable pageable) {
        Page<ProdutoEntity> produtoEntityMarcaOuCategoria = produtoRepository.pesquisarPorMarcaProdutoParaExclusao(marcaOuCategoria, pageable);
        if (produtoEntityMarcaOuCategoria.isEmpty()) {
            produtoEntityMarcaOuCategoria = produtoRepository.pesquisarPorNomeCategoria(marcaOuCategoria, pageable);
        }
        return ProdutoResponseMapper.converterPaginaComTodosAtributos(produtoEntityMarcaOuCategoria);
    }

    @Override
    public Page<ProdutoResponseDomain> consultarProdutoPorStatus(Pageable pageable) {
        Page<ProdutoEntity> produtoEntity = produtoRepository.findByProdutoOfertadoTrue(pageable);
        return ProdutoResponseMapper.converterPaginaPadrao(produtoEntity);
    }

}
