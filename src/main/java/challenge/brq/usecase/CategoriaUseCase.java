package challenge.brq.usecase;

import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.exception.CategoriaDuplicadaException;
import challenge.brq.usecase.exception.CategoriaNaoEncontradaException;
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
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloId(idCategoria);
        if(categoriaResponseDomain == null){
            throw new CategoriaNaoEncontradaException("Categoria não encontrada: " + idCategoria);
        }
        return categoriaGateway.consultarCategoriaPeloId(idCategoria);
    }

    public void excluiCategoriaPeloId(Integer idCategoria){
            consultarCategoriasPeloId(idCategoria);
            categoriaGateway.excluiCategoriaPeloId(idCategoria);
    }

    public CategoriaResponseDomain adicionaCategoria(CategoriaRequestDomain categoriaRequestDomain){
        consultarCategoriaPeloNome(categoriaRequestDomain.getNomeCategoria());
        return categoriaGateway.adicionaCategoria(categoriaRequestDomain);
    }

    public CategoriaResponseDomain atualizaCategoria(Integer id,CategoriaRequestDomain categoriaRequestDomain){
        CategoriaResponseDomain categoriaAtualPeloId = consultarCategoriasPeloId(id);
        CategoriaResponseDomain categoriaAtualPeloNome = consultarCategoriaPeloNome(categoriaRequestDomain.getNomeCategoria());
        categoriaAtualPeloId = CategoriaResponseDomain.builder()
                .idCategoria(categoriaAtualPeloId.getIdCategoria())
                .nomeCategoria(categoriaRequestDomain.getNomeCategoria())
                .build();
        return categoriaGateway.atualizaCategoria(categoriaAtualPeloId);
    }

    public CategoriaResponseDomain consultarCategoriaPeloNome(String nome){
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloNome(nome);
        if(categoriaResponseDomain != null){
            throw new CategoriaDuplicadaException("Categoria já existente: " + nome);
        }
        return categoriaGateway.consultarCategoriaPeloNome(nome);
    }
}
