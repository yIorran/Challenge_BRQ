package challenge.brq.usecase;

import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.exception.CategoriaDuplicadaException;
import challenge.brq.usecase.exception.CategoriaEmUsoException;
import challenge.brq.usecase.exception.CategoriaNaoEncontradaException;
import challenge.brq.usecase.gateway.CategoriaGateway;
import challenge.brq.usecase.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoriaUseCase {

    private final CategoriaGateway categoriaGateway;

    private final ProdutoGateway produtoGateway;

    /**
     * Metodo responsavel por retornar todos os objetos persistidos no banco
     * @return CategoriaResponseDomain(Gateway)
     */
    public List<CategoriaResponseDomain> consultarCategorias(){
        return categoriaGateway.consultarCategoria();
    }

    /**
     * Metodo responsavel por consultar uma categoria pelo ID em nosso banco
     * Caso nao exista sera lancada uma excessao do tipo CategoriaNaoEncontradaException
     * que recebera o tratamento apropriado pela classe exception handler
     *
     * @param idCategoria
     *
     * @return CategoriaResponseDomain
     */
    public CategoriaResponseDomain consultarCategoriasPeloId(Integer idCategoria){
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloId(idCategoria);
        if(categoriaResponseDomain == null){
            throw new CategoriaNaoEncontradaException("Categoria não encontrada: " + idCategoria);
        }
        return categoriaGateway.consultarCategoriaPeloId(idCategoria);
    }

    /**
     * Metodo responsavel por excluir uma categoria do banco pelo ID informado
     *
     * @param idCategoria
     *
     * return void
     */
    public void excluiCategoriaPeloId(Integer idCategoria){
        consultarSeCategoriaTemProduto(consultarCategoriasPeloId(idCategoria));
        categoriaGateway.excluiCategoriaPeloId(idCategoria);
    }

    /**
     * Metodo responsavel por adicionar uma categoria no banco e validar se uma outra categoria com o mesmo
     * nome esta ja esta persistida
     *
     * @param categoriaRequestDomain
     *
     * @return CategoriaResponseDomain
     */
    public CategoriaResponseDomain adicionaCategoria(CategoriaRequestDomain categoriaRequestDomain){
        consultarCategoriaPeloNome(categoriaRequestDomain.getNomeCategoria());
        return categoriaGateway.adicionaCategoria(categoriaRequestDomain);
    }

    /**
     * Metodo responsavel por atualizar uma categoria do banco e validar se aquele ID informado no path existe, caso nao exista
     * sera lancada excessao
     * e se o nome informado para atualizacao ja existe no banco, caso exista sera lancada excessao
     * @param id
     * @param categoriaRequestDomain
     * @return CategoriaResponseDomain
     */
    public CategoriaResponseDomain atualizaCategoria(Integer id,CategoriaRequestDomain categoriaRequestDomain){
        CategoriaResponseDomain categoriaAtualPeloId = consultarCategoriasPeloId(id);
        CategoriaResponseDomain categoriaAtualPeloNome = consultarCategoriaPeloNome(categoriaRequestDomain.getNomeCategoria());
        categoriaAtualPeloId = CategoriaResponseDomain.builder()
                .idCategoria(categoriaAtualPeloId.getIdCategoria())
                .nomeCategoria(categoriaRequestDomain.getNomeCategoria())
                .build();
        return categoriaGateway.atualizaCategoria(categoriaAtualPeloId);
    }

    /**
     * metodo auxiliar responsavel por validar se o nome informado de uma categoria existe no banco, permitindo a
     * validacao para mudancas como atualizar e adicionar o banco
     *
     * @param nome
     *
     * @returnCategoriaResponseDomain
     */
    private CategoriaResponseDomain consultarCategoriaPeloNome(String nome){
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloNome(nome);
        if(categoriaResponseDomain != null){
            throw new CategoriaDuplicadaException("Categoria já existente: " + nome);
        }
        return categoriaGateway.consultarCategoriaPeloNome(nome);
    }

    /**
     * Método responsável por verificar se uma categoria está vinculada a um produto antes de sua exclusão
     *
     * @param categoriaResponseDomain
     *
     * @return produtoResponseDomain
     */
    private Object consultarSeCategoriaTemProduto(CategoriaResponseDomain categoriaResponseDomain){
        CategoriaResponseDomain categoriaSalva = categoriaGateway.consultarCategoriaPeloNome(categoriaResponseDomain.getNomeCategoria());
        List<ProdutoResponseDomain> produtoResponseDomain = produtoGateway.consultarProdutosPelaMarcaOuCategoria(categoriaSalva.getNomeCategoria());
        if(!produtoResponseDomain.isEmpty()){
            throw new CategoriaEmUsoException("Categoria em uso para um produto");
        }
        return produtoResponseDomain;
    }

}
