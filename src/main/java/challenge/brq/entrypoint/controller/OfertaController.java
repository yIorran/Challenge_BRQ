package challenge.brq.entrypoint.controller;

import challenge.brq.entrypoint.mapper.response.ProdutoEntryPointMapperResponse;
import challenge.brq.entrypoint.model.response.ProdutoModelResponse;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.service.ProdutoUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ofertas")
@AllArgsConstructor
public class OfertaController {

    private ProdutoUseCase produtoUseCase;

    @GetMapping
    public ResponseEntity<List<ProdutoModelResponse>> listarProdutos(@RequestParam(required = false) Boolean status) {
        List<ProdutoResponseDomain> produtosModelRetornaStatus = produtoUseCase.consultarProdutosPeloStatus(status);
        if (produtosModelRetornaStatus.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<ProdutoModelResponse> dataModelResponse = ProdutoEntryPointMapperResponse.converter(produtosModelRetornaStatus);
        return ResponseEntity.ok(dataModelResponse);
    }

}
