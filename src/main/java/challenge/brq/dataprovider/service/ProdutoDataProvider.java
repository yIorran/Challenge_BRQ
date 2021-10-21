package challenge.brq.dataprovider.service;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.dataprovider.mapper.request.ProdutoRequestMapper;
import challenge.brq.dataprovider.mapper.response.ProdutoResponseMapper;
import challenge.brq.dataprovider.repository.ProdutoRepository;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Component
public class ProdutoDataProvider implements ProdutoGateway {

    private ProdutoRepository produtoRepository;

    @Override
    public List<ProdutoResponseDomain> consultarProdutos() {
        List<ProdutoEntity> produtoEntity = produtoRepository.findAll();
        return ProdutoResponseMapper.converter(produtoEntity);
    }

    @Override
    public ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto) {
        Optional<ProdutoEntity> produtoEntity = produtoRepository.findById(idProduto);
        return ProdutoResponseMapper.converterProduto(produtoEntity.get());
    }

    @Transactional
    @Override
    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain) {
        ProdutoEntity produtoEntity = ProdutoRequestMapper.converter(produtoRequestDomain);
        ProdutoEntity produtoEntitySalvo = produtoRepository.save(produtoEntity);
        return ProdutoResponseMapper.converterProduto(produtoEntitySalvo);
    }

    @Transactional
    @Override
    public void excluirProdutosPeloId(Integer idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    @Override
    public List<ProdutoResponseDomain> consultarProdutosPelaMarca(String marcaOuCategoria) {
        List<ProdutoEntity> produtoEntityMarcaOuCategoria = produtoRepository.findByMarcaProdutoContaining(marcaOuCategoria);
        if (produtoEntityMarcaOuCategoria.isEmpty()) {
            consultarProdutosPelaCategoria(marcaOuCategoria);
        }
        return ProdutoResponseMapper.converter(produtoEntityMarcaOuCategoria);
    }

    @Override
    public List<ProdutoResponseDomain> consultarProdutosPelaCategoria(String categoriaOuMarca) {
        List<ProdutoEntity> produtoEntityMarcaOuCategoria = produtoRepository.categoria(categoriaOuMarca);
        if (produtoEntityMarcaOuCategoria.isEmpty()) {
            consultarProdutos();
        }
        return ProdutoResponseMapper.converter(produtoEntityMarcaOuCategoria);
    }

    @Transactional
    @Override
    public ProdutoResponseDomain atualizaProdutos(ProdutoResponseDomain produtoResponseDomain) {
        ProdutoEntity produtoEntity = ProdutoRequestMapper.converterParaAtualizacao(produtoResponseDomain);
        ProdutoEntity produtoEntitySalvo = produtoRepository.save(produtoEntity);
        return ProdutoResponseMapper.converterProdutoParaAtualizacao(produtoEntitySalvo);
    }

    @Override
    public ProdutoResponseDomain atualizarProdutosParcial(ProdutoResponseDomain produtoResponseDomain) {
        ProdutoEntity produtoEntity = ProdutoRequestMapper.converterParaAtualizacao(produtoResponseDomain);
        ProdutoEntity produtoEntitySalvo = produtoRepository.save(produtoEntity);
        return ProdutoResponseMapper.converterProdutoParaAtualizacao(produtoEntitySalvo);
    }

}
