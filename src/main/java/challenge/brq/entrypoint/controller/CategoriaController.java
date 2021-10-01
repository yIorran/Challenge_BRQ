package challenge.brq.entrypoint.controller;

import challenge.brq.entrypoint.mapper.request.CategoriaEntryPointMapperRequest;
import challenge.brq.entrypoint.mapper.response.CategoriaEntryPointMapperResponse;
import challenge.brq.entrypoint.model.request.CategoriaModelRequest;
import challenge.brq.entrypoint.model.response.CategoriaModelResponse;
import challenge.brq.usecase.CategoriaUseCase;
import challenge.brq.usecase.domain.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaUseCase categoriaUseCase;

    @GetMapping
    public ResponseEntity<Object> listarCategorias(){
        List<CategoriaResponseDomain> categoriasModel = categoriaUseCase.consultarCategorias();
        if(categoriasModel.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<CategoriaModelResponse> dataModelResponse = CategoriaEntryPointMapperResponse.converter(categoriasModel);
        return ResponseEntity.ok(dataModelResponse);
    }

    @GetMapping("{idCategoria}")
    public ResponseEntity<Object> listarCategorias(@PathVariable Integer idCategoria){
        CategoriaResponseDomain categoriasModel = categoriaUseCase.consultarCategoriasPeloId(idCategoria);
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterCategoria(categoriasModel);
        return ResponseEntity.ok(categoriaModelResponse);
    }

    @DeleteMapping("{idCategoria}")
    public ResponseEntity<Object> excluiCategoriaPeloIdExplicit(@PathVariable Integer idCategoria){
        categoriaUseCase.excluiCategoriaPeloId(idCategoria);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<CategoriaModelResponse> adicionaCategoria(@RequestBody CategoriaModelRequest categoriaModelRequest){
        CategoriaRequestDomain categoriaRequestDomain = CategoriaEntryPointMapperRequest.converter(categoriaModelRequest);
         CategoriaResponseDomain categoriaResponseDomain = categoriaUseCase.adicionaCategoria(categoriaRequestDomain);
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterCategoria(categoriaResponseDomain);
        return new ResponseEntity<>(categoriaModelResponse, HttpStatus.CREATED);
    }

    @PutMapping("{idCategoria}")
    public ResponseEntity<CategoriaModelResponse> atualizaCategoria(@PathVariable Integer idCategoria,@RequestBody CategoriaModelRequest categoriaModelRequest){
        CategoriaRequestDomain categoriaIdRequestDomain = CategoriaEntryPointMapperRequest.converter(categoriaModelRequest);
        CategoriaResponseDomain responseDomainId = categoriaUseCase.atualizaCategoria(idCategoria,categoriaIdRequestDomain);
        CategoriaModelResponse categoriaModelResponse = CategoriaEntryPointMapperResponse.converterParaAtualizacao(idCategoria,responseDomainId);
        return new ResponseEntity<>(categoriaModelResponse, HttpStatus.OK);
    }
}
