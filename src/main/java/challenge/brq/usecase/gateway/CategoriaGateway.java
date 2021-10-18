package challenge.brq.usecase.gateway;

import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;

import java.util.List;

public interface CategoriaGateway {

    List<CategoriaResponseDomain> consultarCategoria();

    CategoriaResponseDomain consultarCategoriaPeloId(Integer idCategoria);

    void excluiCategoriaPeloId(Integer idCategoria);

    CategoriaResponseDomain adicionaCategoria(CategoriaRequestDomain categoriaRequestDomain);

    CategoriaResponseDomain atualizaCategoria(CategoriaResponseDomain categoriaResponseDomain);

    CategoriaResponseDomain consultarCategoriaPeloNome(String nome);
}
