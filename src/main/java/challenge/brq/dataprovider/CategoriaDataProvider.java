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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
        return CategoriaResponseMapper.converterCategoria(categoriaEntity.get());
    }

    @Override
    public void excluiCategoriaPeloId(Integer idCategoria) {
        categoriaRepository.deleteById(idCategoria);
    }

    @Override
    public CategoriaResponseDomain adicionaCategoria(CategoriaRequestDomain categoriaRequestDomain) {
        CategoriaEntity categoriaEntity = CategoriaRequestMapper.converter(categoriaRequestDomain);
        CategoriaEntity categoriaEntitySalvo = categoriaRepository.save(categoriaEntity);
        return CategoriaResponseMapper.converterCategoria(categoriaEntitySalvo);
    }

    @Override
    public CategoriaResponseDomain atualizaCategoria(CategoriaResponseDomain categoriaResponseDomain) {
        CategoriaEntity categoriaEntity = CategoriaRequestMapper.converterParaAtualizacao(categoriaResponseDomain);
        CategoriaEntity categoriaEntitieSalvo = categoriaRepository.save(categoriaEntity);
        return CategoriaResponseMapper.converterCategoria(categoriaEntitieSalvo);
    }


    private Boolean validarCategoriaPeloNome(CategoriaRequestDomain categoriaRequestDomain){
        CategoriaEntity tipo = categoriaRepository.findByNomeCategoriaIgnoreCase(categoriaRequestDomain.getNomeCategoria());
        if(tipo == null){
            return false;
        }
        return tipo.getNomeCategoria().equalsIgnoreCase(categoriaRequestDomain.getNomeCategoria());
    }

}
