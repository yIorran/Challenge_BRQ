package challenge.brq.entrypoint.controller;

import challenge.brq.entrypoint.mapper.request.ProdutoEntryPointMapperRequest;
import challenge.brq.entrypoint.mapper.response.ProdutoEntryPointMapperResponse;
import challenge.brq.entrypoint.mode.response.ProdutoModelResponse;
import challenge.brq.usecase.ProdutoUseCase;
import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/produtos")
@AllArgsConstructor
public class ProdutoController {

    private ProdutoUseCase produtoUseCase;

    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> listarProdutos(
            @RequestParam(value = "codigo_produto", required = false) String nomeProduto){
        ProdutoRequestDomain tipo = ProdutoEntryPointMapperRequest.converter(nomeProduto);
        List<ProdutoResponseDomain> produtosModel = produtoUseCase.consultarProdutos(tipo);

        if(produtosModel.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        List<ProdutoModelResponse> dataModelResponse = ProdutoEntryPointMapperResponse.converter(produtosModel);

        return ResponseEntity.ok(dataModelResponse);
    }

}
