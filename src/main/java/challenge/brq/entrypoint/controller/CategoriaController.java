package challenge.brq.entrypoint.controller;

import challenge.brq.entrypoint.mapper.request.CategoriaEntryPointMapperRequest;
import challenge.brq.entrypoint.mapper.response.CategoriaEntryPointMapperResponse;
import challenge.brq.entrypoint.model.request.CategoriaModelRequestNome;
import challenge.brq.entrypoint.model.response.CategoriaModelResponse;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.service.CategoriaUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Classe controller operando com a rota "/categorias"
 * Classe responsável pelos métodos de:
 * > listar todas as categorias
 * > consultar por ID
 * > excluir por ID
 * > adicionar categoria
 * > atualizar categoria
 */

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaUseCase categoriaUseCase;

    /**
     * Metodo responsavel por listar todas as categorias, caso nao haja nenhuma sera retornado
     * HTTPstatus NO_CONTENT com um body vazio
     *
     * @return CategoriaModelResponse
     */
    @GetMapping
    public ResponseEntity<?> listarCategorias() {
        List<CategoriaResponseDomain> categoriasModel = categoriaUseCase.consultarCategorias();
        if (categoriasModel.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<CategoriaModelResponse> dataModelResponse = CategoriaEntryPointMapperResponse.converter(categoriasModel);
        return ResponseEntity.ok(dataModelResponse);
    }

    /**
     * Metodo responsavel por consultar uma categoria pelo Id informado na rota "categoria/{id}"
     * Caso nao exista a categoria pelo ID informado sera retornado um HTTPStatus NOT_FOUND
     * Caso exista, retornara a categoria referente àquele id com HTTPStatus OK
     *
     * @param idCategoria
     * @return CategoriaModelResponse
     */
    @GetMapping("{idCategoria}")
    public ResponseEntity<CategoriaModelResponse> listarCategorias(@PathVariable Integer idCategoria) {
        CategoriaResponseDomain categoriasModel = categoriaUseCase.consultarCategoriasPeloId(idCategoria);
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterCategoria(categoriasModel);
        return ResponseEntity.ok(categoriaModelResponse);
    }

    /**
     * Metodo responsavel por excluir uma categoria pelo Id informado na rota "categorias/{id}"
     * Caso nao exista a categoria pelo ID informado sera retornado um HTTPStatus NOT_FOUND
     * Caso exista, retornara a categoria referente àquele id com HTTPStatus OK
     *
     * @param idCategoria
     * @return void
     */
    @DeleteMapping("{idCategoria}")
    public ResponseEntity<Void> excluiCategoriaPeloId(@PathVariable Integer idCategoria) {
        categoriaUseCase.excluiCategoriaPeloId(idCategoria);
        return ResponseEntity.ok().build();
    }

    /**
     * Metodo responsavel por persistir uma nova categoria no banco, recebendo um CategoriaModelRequest
     * e retornando um CategoriaModelResponse.
     * <p>
     * Caso a categoria ja esteja persistida em nosso banco num ID diferente sera retornado
     * um HTTPStatus 422 UNPROCESSABLE_ENTITY
     * <p>
     * Caso a categoria nao esteja persistida sera retornado um HTTPStatus CREATED 204
     *
     * @param categoriaModelRequestNome
     * @return CategoriaModelResponse
     */
    @PostMapping
    public ResponseEntity<CategoriaModelResponse> adicionaCategoria(@RequestBody @Valid CategoriaModelRequestNome categoriaModelRequestNome) {
        CategoriaRequestDomain categoriaRequestDomain = CategoriaEntryPointMapperRequest.converterNome(categoriaModelRequestNome);
        CategoriaResponseDomain categoriaResponseDomain = categoriaUseCase.adicionaCategoria(categoriaRequestDomain);
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterCategoria(categoriaResponseDomain);
        return new ResponseEntity<>(categoriaModelResponse, HttpStatus.CREATED);
    }

    /**
     * Metodo responsavel por atualizar uma categoria persistida no banco
     * Caso o nome dessa categoria esteja presente em outro ID no banco, sera retornado um HTTPStatus 422 UNPROCESSABLE_ENTITY
     * sempre proibindo a duplicata de objetos no banco
     *
     * @param idCategoria
     * @param categoriaModelRequestNome
     * @return CategoriaModelResponse
     */
    @PutMapping("{idCategoria}")
    public ResponseEntity<CategoriaModelResponse> atualizaCategoria(@PathVariable Integer idCategoria, @RequestBody @Valid CategoriaModelRequestNome categoriaModelRequestNome) {
        CategoriaRequestDomain categoriaIdRequestDomain = CategoriaEntryPointMapperRequest.converterNome(categoriaModelRequestNome);
        CategoriaResponseDomain responseDomainId = categoriaUseCase.atualizaCategoria(idCategoria, categoriaIdRequestDomain);
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterParaAtualizacao(idCategoria, responseDomainId);
        return new ResponseEntity<>(categoriaModelResponse, HttpStatus.OK);
    }

}
