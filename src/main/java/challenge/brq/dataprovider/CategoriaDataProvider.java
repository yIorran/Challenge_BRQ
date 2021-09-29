package challenge.brq.dataprovider;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.dataprovider.mapper.response.CategoriaResponseMapper;
import challenge.brq.dataprovider.repository.CategoriaRepository;
import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.gateway.CategoriaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@AllArgsConstructor
public class CategoriaDataProvider implements CategoriaGateway {

    private CategoriaRepository categoriaRepository;

    @Override
    public List<CategoriaResponseDomain> consultarCategoriaPeloNome() {
        List<CategoriaEntity> categoriaEntity = categoriaRepository.findAll();
        return CategoriaResponseMapper.converter(categoriaEntity);
    }

    @Override
    public CategoriaResponseDomain consultarCategoriaPeloId(Integer idCategoria) {
        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(idCategoria);
        return CategoriaResponseMapper.converterCategoria(categoriaEntity.get());
    }

    @Override
    public void excluiCategoriaPeloId(Integer idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }

    private Boolean validarCategoriaPeloNome(CategoriaRequestDomain categoriaRequestDomain){
        CategoriaEntity tipo = categoriaRepository.findByNomeCategoriaIgnoreCase(categoriaRequestDomain.getNomeCategoria());
        if(tipo == null){
            return false;
        }
        return tipo.getNomeCategoria().equalsIgnoreCase(categoriaRequestDomain.getNomeCategoria());
    }

    private Boolean validarCategoriaPeloId(CategoriaRequestDomain categoriaRequestDomain){
        List <CategoriaEntity> tipo = categoriaRepository.findByIdCategoria(categoriaRequestDomain.getIdCategoria());
        if(tipo == null){
            return false;
        }
        return true;
    }
}
