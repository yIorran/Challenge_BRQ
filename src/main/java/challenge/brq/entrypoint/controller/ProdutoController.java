package challenge.brq.entrypoint.controller;

import challenge.brq.entrypoint.mapper.request.ProdutoEntryPointMapperRequest;
import challenge.brq.entrypoint.mapper.response.ProdutoEntryPointMapperResponse;
import challenge.brq.entrypoint.model.request.ProdutoModelRequest;
import challenge.brq.entrypoint.model.response.ProdutoModelResponse;
import challenge.brq.usecase.ProdutoUseCase;
import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Classe responsável por ser o entrypoint da aplicação, contendo os métodos put, patch, delete, post e get.
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
     *
     * @return Retorna os produtos que contém igualdade nas strings
     * Exemplo
     * Apple
     * marca= "app"
     * Então retornará itens da marca
     */
    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> listarProdutosPelaMarca(@RequestParam(required = false) String marca){
        List<ProdutoResponseDomain> produtosModel = produtoUseCase.consultarProdutosPelaMarca(marca);
        if (produtosModel.isEmpty()) {
        produtosModel = produtoUseCase.consultarProdutos();
        }
        List<ProdutoModelResponse> dataModelResponse = ProdutoEntryPointMapperResponse.converter(produtosModel);
        return ResponseEntity.ok(dataModelResponse);
    }


    /**
     * Método responsável por receber um Id, fazer a busca no banco
     * @param idProduto
     * @return Retorna o item referente ao ID informado
     */
    @GetMapping("{idProduto}")
    public ResponseEntity<Object> listarProdutos(@PathVariable Integer idProduto){
        ProdutoResponseDomain produtosModel = produtoUseCase.consultarProdutosPeloId(idProduto);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterProduto(produtosModel);
        return ResponseEntity.ok(produtoModelResponse);
    }


    @PostMapping
    public ResponseEntity<ProdutoModelResponse> adicionaProduto(@RequestBody ProdutoModelRequest produtoModelRequest){
        ProdutoRequestDomain produtoRequestDomain = ProdutoEntryPointMapperRequest.converter(produtoModelRequest);
        ProdutoResponseDomain produtoResponseDomain = produtoUseCase.adicionaProdutos(produtoRequestDomain);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterProduto(produtoResponseDomain);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("{idProduto}")
    public ResponseEntity<Object> excluiCategoriaPeloIdExplicit(@PathVariable Integer idProduto){
        produtoUseCase.excluiProdutoPeloId(idProduto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("{idProduto}")
    public ResponseEntity<ProdutoModelResponse> atualizarProdutos(@PathVariable Integer idProduto,@RequestBody ProdutoModelRequest produtoModelRequest){
        ProdutoRequestDomain produtoRequestDomain = ProdutoEntryPointMapperRequest.converterParaAtualizacao(produtoModelRequest);
        ProdutoResponseDomain produtoResponseDomain = produtoUseCase.atualizarProdutos(idProduto,produtoRequestDomain);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterParaAtualizacao(idProduto,produtoResponseDomain);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);
    }

    @PatchMapping("{idProduto}")
    public ResponseEntity<ProdutoModelResponse> atualizarProdutosParcial(@PathVariable Integer idProduto,@RequestBody ProdutoModelRequest produtoModelRequest) {
    ProdutoRequestDomain produtoRequestDomain = ProdutoEntryPointMapperRequest.converterParaAtualizacao(produtoModelRequest);
        ProdutoResponseDomain produtoResponseDomain = produtoUseCase.atualizarProdutosParcial(idProduto,produtoRequestDomain);
        ProdutoModelResponse produtoModelResponse = ProdutoEntryPointMapperResponse.converterParaAtualizacao(idProduto,produtoResponseDomain);
        return new ResponseEntity<>(produtoModelResponse, HttpStatus.OK);
    }
}


