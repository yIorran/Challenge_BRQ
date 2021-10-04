package challenge.brq.entrypoint.controller;

import challenge.brq.entrypoint.mapper.request.CategoriaEntryPointMapperRequest;
import challenge.brq.entrypoint.mapper.request.ProdutoEntryPointMapperRequest;
import challenge.brq.entrypoint.mapper.response.CategoriaEntryPointMapperResponse;
import challenge.brq.entrypoint.mapper.response.ProdutoEntryPointMapperResponse;
import challenge.brq.entrypoint.model.request.CategoriaModelRequest;
import challenge.brq.entrypoint.model.request.ProdutoModelRequest;
import challenge.brq.entrypoint.model.response.CategoriaModelResponse;
import challenge.brq.entrypoint.model.response.ProdutoModelResponse;
import challenge.brq.usecase.ProdutoUseCase;
import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {

    private ProdutoUseCase produtoUseCase;

    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> listarProdutosPelaMarca(@RequestParam(required = false) String marca){
        List<ProdutoResponseDomain> produtosModel = produtoUseCase.consultarProdutosPelaMarca(marca);
        if (produtosModel.isEmpty()) {
        produtosModel = produtoUseCase.consultarProdutos();
        }
        List<ProdutoModelResponse> dataModelResponse = ProdutoEntryPointMapperResponse.converter(produtosModel);
        return ResponseEntity.ok(dataModelResponse);
    }

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
}


