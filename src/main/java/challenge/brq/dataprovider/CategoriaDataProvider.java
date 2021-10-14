package challenge.brq.dataprovider;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.dataprovider.mapper.request.CategoriaRequestMapper;
import challenge.brq.dataprovider.mapper.response.CategoriaResponseMapper;
import challenge.brq.dataprovider.repository.CategoriaRepository;
import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.gateway.CategoriaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

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
        if(categoriaEntity.isPresent()) {
            return CategoriaResponseMapper.converterCategoria(categoriaEntity.get());
        }
        else
            return null;
    }

    @Override
    public void excluiCategoriaPeloId(Integer idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }

    @Override
    public CategoriaResponseDomain adicionaCategoria(CategoriaRequestDomain categoriaRequestDomain) {
        Optional<CategoriaEntity> checarNomeEntity = Optional.ofNullable(categoriaRepository.findByNomeCategoriaIgnoreCaseLike(categoriaRequestDomain.getNomeCategoria()));
        if(checarNomeEntity.isEmpty()){
            CategoriaEntity categoriaEntity = CategoriaRequestMapper.converter(categoriaRequestDomain);
            CategoriaEntity categoriaEntitySalvo = categoriaRepository.save(categoriaEntity);
            return CategoriaResponseMapper.converterCategoria(categoriaEntitySalvo);
        }
        else {
            return null;
        }
    }

    @Override
    public CategoriaResponseDomain atualizaCategoria(CategoriaResponseDomain categoriaResponseDomain) {
        CategoriaEntity categoriaEntity = CategoriaRequestMapper.converterParaAtualizacao(categoriaResponseDomain);
        CategoriaEntity categoriaEntitieSalvo = categoriaRepository.save(categoriaEntity);
        return CategoriaResponseMapper.converterCategoria(categoriaEntitieSalvo);
    }

}
