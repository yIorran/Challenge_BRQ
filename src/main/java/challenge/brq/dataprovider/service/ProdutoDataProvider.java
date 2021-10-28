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
        return ProdutoResponseMapper.converterPagina(produtoEntity);
    }

    @Override
    public ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto) {
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(idProduto);
        if (produtoEntity.isEmpty()) {
            return null;
        }
        return ProdutoResponseMapper.converterProduto(produtoEntity.get());
    }

    @Transactional
    @Override
    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain) {
        if (produtoRequestDomain.getCategoria().getIdCategoria() == null) {
            return null;
        } else {
            ProdutoEntity produtoEntity = ProdutoRequestMapper.converter(produtoRequestDomain);
            ProdutoEntity produtoEntitySalvo = produtoRepository.save(produtoEntity);
            return ProdutoResponseMapper.converterProduto(produtoEntitySalvo);
        }
    }

    @Transactional
    @Override
    public void excluirProdutosPeloId(Integer idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    @Override
    public Page<ProdutoResponseDomain> consultarProdutosPelaMarca(String marcaOuCategoria, Pageable pageable) {
        Page<ProdutoEntity> produtoEntityMarcaOuCategoria = produtoRepository.findByMarcaProdutoContaining(marcaOuCategoria, pageable);
        if (produtoEntityMarcaOuCategoria.isEmpty()) {
            consultarProdutosPelaCategoria(marcaOuCategoria, pageable);
        }
        return ProdutoResponseMapper.converterPagina(produtoEntityMarcaOuCategoria);
    }

    @Override
    public Page<ProdutoResponseDomain> consultarProdutosPelaCategoria(String categoriaOuMarca, Pageable pageable) {
        Page<ProdutoEntity> produtoEntityMarcaOuCategoria = produtoRepository.pesquisarPorNomeCategoria(categoriaOuMarca, pageable);
        if (produtoEntityMarcaOuCategoria.isEmpty()) {
            consultarProdutos(pageable);
        }
        return ProdutoResponseMapper.converterPagina(produtoEntityMarcaOuCategoria);
    }

    @Override
    public ProdutoResponseDomain atualizarProdutosParcial(ProdutoResponseDomain produtoResponseDomain) {
        ProdutoEntity produtoEntity = ProdutoRequestMapper.converterParaAtualizacao(produtoResponseDomain);
        ProdutoEntity produtoEntitySalvo = produtoRepository.save(produtoEntity);
        return ProdutoResponseMapper.converterProdutoParaAtualizacao(produtoEntitySalvo);
    }

    @Override
    public Page<ProdutoResponseDomain> consultarProdutosParaExclusaoDeCategorias(String marcaOuCategoria, Pageable pageable) {
        Page<ProdutoEntity> produtoEntityMarcaOuCategoria = produtoRepository.findByMarcaProdutoContaining(marcaOuCategoria, pageable);
        if (produtoEntityMarcaOuCategoria.isEmpty()) {
            produtoEntityMarcaOuCategoria = produtoRepository.pesquisarPorNomeCategoria(marcaOuCategoria, pageable);
        }
        return ProdutoResponseMapper.converterPagina(produtoEntityMarcaOuCategoria);
    }

    @Override
    public Page<ProdutoResponseDomain> consultarProdutoPorStatus(Boolean status, Pageable pageable) {
        Page<ProdutoEntity> produtoEntity = produtoRepository.findByProdutoAtivo(status, pageable);
        return ProdutoResponseMapper.converterPagina(produtoEntity);
    }

}
