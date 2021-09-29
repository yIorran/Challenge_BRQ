package challenge.brq.usecase.gateway;

import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;

import java.util.List;

public interface CategoriaGateway {

    List<CategoriaResponseDomain> consultarCategoriaPeloNome();

    CategoriaResponseDomain consultarCategoriaPeloId(Integer idCategoria);

    void excluiCategoriaPeloId(Integer idCategoria);
}
