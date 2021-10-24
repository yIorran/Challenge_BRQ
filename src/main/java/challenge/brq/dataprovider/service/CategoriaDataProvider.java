package challenge.brq.dataprovider.service;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.dataprovider.mapper.request.CategoriaRequestMapper;
import challenge.brq.dataprovider.mapper.response.CategoriaResponseMapper;
import challenge.brq.dataprovider.repository.CategoriaRepository;
import challenge.brq.usecase.gateway.CategoriaGateway;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoriaDataProvider implements CategoriaGateway {

    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaResponseDomain> consultarCategoria() {
        List<CategoriaEntity> categoriaEntity = categoriaRepository.findAll();
        return CategoriaResponseMapper.converter(categoriaEntity);
    }

    @Override
    public CategoriaResponseDomain consultarCategoriaPeloId(Integer idCategoria) {
        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(idCategoria);
        if (categoriaEntity.isPresent()) {
            return CategoriaResponseMapper.converterCategoria(categoriaEntity.get());
        } else
            return null;
    }

    @Transactional
    @Override
    public void excluiCategoriaPeloId(Integer idCategoria) {
        categoriaRepository.deleteById(idCategoria);
        categoriaRepository.flush();
    }

    @Transactional
    @Override
    public CategoriaResponseDomain adicionaCategoria(CategoriaRequestDomain categoriaRequestDomain) {
        CategoriaEntity categoriaEntity = CategoriaRequestMapper.converter(categoriaRequestDomain);
        CategoriaEntity categoriaEntitySalvo = categoriaRepository.save(categoriaEntity);
        return CategoriaResponseMapper.converterCategoria(categoriaEntitySalvo);
    }

    @Transactional
    @Override
    public CategoriaResponseDomain atualizaCategoria(CategoriaResponseDomain categoriaResponseDomain) {
        CategoriaEntity categoriaEntity = CategoriaRequestMapper.converterParaAtualizacao(categoriaResponseDomain);
        CategoriaEntity categoriaEntitieSalvo = categoriaRepository.save(categoriaEntity);
        return CategoriaResponseMapper.converterCategoria(categoriaEntitieSalvo);
    }

    @Override
    public CategoriaResponseDomain consultarCategoriaPeloNome(String nome) {
        CategoriaEntity categoriaEntity = categoriaRepository.findByNomeCategoriaIgnoreCaseLike(nome);
        if (categoriaEntity != null) {
            return CategoriaResponseMapper.converterCategoria(categoriaEntity);
        } else
            return null;
    }

}
