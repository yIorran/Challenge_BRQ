package challenge.brq.entrypoint.controller;

import challenge.brq.entrypoint.mapper.request.ProdutoEntryPointMapperRequest;
import challenge.brq.entrypoint.mapper.response.ProdutoEntryPointMapperResponse;
import challenge.brq.entrypoint.model.request.ProdutoModelRequest;
import challenge.brq.entrypoint.model.response.ProdutoModelResponse;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.service.ProdutoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


/**
 * Classe controller operando com a rota "/produtos"
 * Classe responsável pelos métodos de:
 * > listar todos os produtos
 * > consultar por ID
 * > consultar por marca
 * > consultar por categoria
 * > excluir por ID
 * > adicionar produto
 * > atualizar produto
 * > atualizar produto parcialmente
 */
@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {

    private ProdutoUseCase produtoUseCase;


    /**
     * Método responsável por retornar todos os produtos, produtos filtrados pela categoria
     * e produtos filtrados pela marca
     *
     * @param marca     {String marca}
     * @param categoria {String categoria}
     */
    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> listarProdutos(@RequestParam(required = false) String marca,
                                                                     @RequestParam(required = false) String categoria) {
        List<ProdutoResponseDomain> produtosModelRetornaMarcaOuCategoria = produtoUseCase.consultarProdutosPelaMarcaOuCategoria(marca, categoria);
        if (produtosModelRetornaMarcaOuCategoria.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ProdutoModelResponse> dataModelResponse = ProdutoEntryPointMapperResponse.converter(produtosModelRetornaMarcaOuCategoria);
        return ResponseEntity.ok(dataModelResponse);

    }

    /**
     * Método responsável por receber um Id, fazer a busca no banco do ID informado na rota
     *
     * @param idProduto
     * @return Retorna o item referente ao ID informado
     */
    @GetMapping("{idProduto}")
    public ResponseEntity<Object> consultarProdutosPeloID(@PathVariable Integer idProduto) {
        ProdutoResponseDomain produtosModel = produtoUseCase.consultarProdutosPeloId(idProduto);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterProduto(produtosModel);
        if (produtoModelResponse == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(produtoModelResponse);
    }

    /**
     * Metodo responsavel por persistir um novo produto no banco, recebendo um Produto informado via JSON
     *
     * @param produtoModelRequest
     * @return produtoModelResponse
     */
    @PostMapping
    public ResponseEntity<ProdutoModelResponse> adicionaProduto(@RequestBody @Valid ProdutoModelRequest produtoModelRequest) {
        ProdutoRequestDomain produtoRequestDomain = ProdutoEntryPointMapperRequest.converter(produtoModelRequest);
        ProdutoResponseDomain produtoResponseDomain = produtoUseCase.adicionaProdutos(produtoRequestDomain);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterProduto(produtoResponseDomain);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.CREATED);
    }

    /**
     * Metodo responsavel por excluir um produto pelo Id informado na rota
     *
     * @param idProduto
     * @return void
     */
    @DeleteMapping("{idProduto}")
    public ResponseEntity<Object> excluiCategoriaPeloIdExplicit(@PathVariable Integer idProduto) {
        produtoUseCase.excluiProdutoPeloId(idProduto);
        return ResponseEntity.ok().build();
    }

    /**
     * Metodo responsavel por atualizar parcialmente um produto persistido no banco
     * Para a atualizacao parcial pode informar apenas os atributos desejados
     *
     * @param idProduto
     * @param produtoModelRequest
     * @return produtoModelResponse
     */
    @PatchMapping("{idProduto}")
    public ResponseEntity<ProdutoModelResponse> atualizarProdutosParcial(@PathVariable Integer idProduto,
                                                                         @RequestBody ProdutoModelRequest produtoModelRequest) {
        ProdutoRequestDomain produtoRequestDomain = ProdutoEntryPointMapperRequest.converterParaAtualizacaoParcial(produtoModelRequest);
        ProdutoResponseDomain produtoResponseDomain = produtoUseCase.atualizarProdutosParcial(idProduto, produtoRequestDomain);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterParaAtualizacao(idProduto, produtoResponseDomain);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);
    }
}


