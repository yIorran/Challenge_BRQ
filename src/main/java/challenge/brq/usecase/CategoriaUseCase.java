package challenge.brq.usecase;

import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.gateway.CategoriaGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    public List<CategoriaResponseDomain> consultarCategorias(){
        return categoriaGateway.consultarCategoria();
    }

    public CategoriaResponseDomain consultarCategoriasPeloId(Integer idCategoria){
        return categoriaGateway.consultarCategoriaPeloId(idCategoria);
    }

    public void excluiCategoriaPeloId(Integer idCategoria){
        categoriaGateway.excluiCategoriaPeloId(idCategoria);
    }

    public CategoriaResponseDomain adicionaCategoria(CategoriaRequestDomain categoriaRequestDomain){
        return categoriaGateway.adicionaCategoria(categoriaRequestDomain);
    }

    public CategoriaResponseDomain atualizaCategoria(Integer id,CategoriaRequestDomain categoriaRequestDomain){
        CategoriaResponseDomain categoriaAtual = consultarCategoriasPeloId(id);
        categoriaAtual.builder()
                .idCategoria(id)
                .nomeCategoria(categoriaRequestDomain.getNomeCategoria())
                .build();
        return categoriaGateway.atualizaCategoria(id,categoriaRequestDomain);
    }

}
