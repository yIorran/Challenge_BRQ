package challenge.brq.entrypoint.controller;

import challenge.brq.entrypoint.mapper.request.ProdutoEntryPointMapperRequest;
import challenge.brq.entrypoint.mapper.response.ProdutoEntryPointMapperResponse;
import challenge.brq.entrypoint.model.request.ProdutoModelRequest;
import challenge.brq.entrypoint.model.response.ProdutoModelResponse;
import challenge.brq.usecase.service.ProdutoUseCase;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * Método responsável por retornar todos os produtos ou apenas um (filtrado pela marca)
     *
     * @param ""
     * @param marca
     * @return Retorna os produtos que contém igualdade nas strings
     * Exemplo
     * Apple
     * marca= "app"
     * Então retornará itens da marca
     */
    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> listarProdutosPelaMarca(@RequestParam(required = false) String marca,
                                                                              @RequestParam(required = false) String categoria) {
        List<ProdutoResponseDomain> produtosModelRetornaMarcaOuCategoria = produtoUseCase.consultarProdutosPelaMarcaOuCategoria(marca, categoria);
        List<ProdutoResponseDomain> produtoModelRetornaTodos = produtoUseCase.consultarProdutos();
        List<ProdutoModelResponse> dataModelResponse = ProdutoEntryPointMapperResponse.converter(produtoModelRetornaTodos);
        if (!produtosModelRetornaMarcaOuCategoria.isEmpty()) {
            dataModelResponse = ProdutoEntryPointMapperResponse.converter(produtosModelRetornaMarcaOuCategoria);
            return ResponseEntity.ok(dataModelResponse);
        }
        return ResponseEntity.noContent().build();
    }

    /**
     * Método responsável por receber um Id, fazer a busca no banco
     *
     * @param idProduto
     * @return Retorna o item referente ao ID informado
     */
    @GetMapping("{idProduto}")
    public ResponseEntity<Object> consultarProdutosPeloID(@PathVariable Integer idProduto) {
        ProdutoResponseDomain produtosModel = produtoUseCase.consultarProdutosPeloId(idProduto);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterProduto(produtosModel);
        if (produtoModelResponse != null) {
            return ResponseEntity.ok(produtoModelResponse);
        }
        return ResponseEntity.notFound().build();
    }

    /**
     * Metodo responsavel por persistir um novo produto no banco, recebendo um ProdutoModelRequest
     * e retornando um ProdutoModelResponse.
     *
     * @param produtoModelRequest
     * @return produtoModelResponse
     */
    @PostMapping
    public ResponseEntity<ProdutoModelResponse> adicionaProduto(@RequestBody ProdutoModelRequest produtoModelRequest) {
        ProdutoRequestDomain produtoRequestDomain = ProdutoEntryPointMapperRequest.converter(produtoModelRequest);
        ProdutoResponseDomain produtoResponseDomain = produtoUseCase.adicionaProdutos(produtoRequestDomain);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterProduto(produtoResponseDomain);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.CREATED);
    }

    /**
     * Metodo responsavel por excluir um produto pelo Id informado na rota "produtos/{id}"
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
     * Metodo responsavel por atualizar um produto persistido no banco
     * Para atualizacao precisa informar no JSON
     * "nome":
     * "descricao":
     * "marca":
     * "quantidade":
     * "preco":
     * "ativo":
     * "ofertado":
     * "porcentagem":
     *
     * @param idProduto
     * @param produtoModelRequest
     * @return produtoModelResponse
     */
    @PutMapping("{idProduto}")
    public ResponseEntity<ProdutoModelResponse> atualizarProdutos(@PathVariable Integer idProduto,
                                                                  @RequestBody ProdutoModelRequest produtoModelRequest) {
        ProdutoRequestDomain produtoRequestDomain = ProdutoEntryPointMapperRequest.converterParaAtualizacao(produtoModelRequest);
        ProdutoResponseDomain produtoResponseDomain = produtoUseCase.atualizarProdutos(idProduto, produtoRequestDomain);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterParaAtualizacao(idProduto, produtoResponseDomain);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);
    }

    /**
     * Metodo responsavel por atualizar parcialmente um produto persistido no banco
     * Para a atualizacao parcial pode informar apenas os atributos desejados
     * <p>
     * PARA CATEGORIA:
     * <p>
     * "categoria":{
     * "id":2
     * }
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


