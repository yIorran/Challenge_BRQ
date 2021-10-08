package challenge.brq.dataprovider;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.dataprovider.mapper.request.ProdutoRequestMapper;
import challenge.brq.dataprovider.mapper.response.ProdutoResponseMapper;
import challenge.brq.dataprovider.repository.ProdutoRepository;
import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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

    @Override
    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain) {
        ProdutoEntity produtoEntity = ProdutoRequestMapper.converter(produtoRequestDomain);
        ProdutoEntity produtoEntitySalvo = produtoRepository.save(produtoEntity);
        return ProdutoResponseMapper.converterProduto(produtoEntitySalvo);
    }

    @Override
    public void excluirProdutosPeloId(Integer idProduto) {
        produtoRepository.deleteById(idProduto);
    }

    @Override
    public List<ProdutoResponseDomain> consultarProdutosPelaMarcaOuCategoria(String marcaOuCategoria) {
        List<ProdutoEntity> produtoEntityMarcaOuCategoria = produtoRepository.findByMarcaProdutoContaining(marcaOuCategoria);
        if(produtoEntityMarcaOuCategoria.isEmpty()){
            produtoEntityMarcaOuCategoria = produtoRepository.categoria(marcaOuCategoria);
        }
        return ProdutoResponseMapper.converter(produtoEntityMarcaOuCategoria);
    }

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
