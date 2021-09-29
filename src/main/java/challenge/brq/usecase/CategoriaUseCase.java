package challenge.brq.usecase;

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
        return categoriaGateway.consultarCategoriaPeloNome();
    }

    public CategoriaResponseDomain consultarCategoriasPeloId(Integer idCategoria){
        return categoriaGateway.consultarCategoriaPeloId(idCategoria);
    }

    public void excluiCategoriaPeloId(Integer idCategoria){
        categoriaGateway.excluiCategoriaPeloId(idCategoria);
    }

}
