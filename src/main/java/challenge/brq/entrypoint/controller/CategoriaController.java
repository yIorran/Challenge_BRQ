package challenge.brq.entrypoint.controller;

import challenge.brq.entrypoint.mapper.response.CategoriaEntryPointMapperResponse;
import challenge.brq.entrypoint.mode.response.CategoriaModelResponse;
import challenge.brq.usecase.CategoriaUseCase;
import challenge.brq.usecase.domain.model.response.CategoriaResponseDomain;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
@AllArgsConstructor
public class CategoriaController {

    private CategoriaUseCase categoriaUseCase;

    @GetMapping
    public ResponseEntity<List<CategoriaModelResponse>> listarCategorias(){
        List<CategoriaResponseDomain> categoriasModel = categoriaUseCase.consultarCategorias();
        if(categoriasModel.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        List<CategoriaModelResponse> dataModelResponse = CategoriaEntryPointMapperResponse.converter(categoriasModel);
        return ResponseEntity.ok(dataModelResponse);
    }

    @GetMapping("{idCategoria}")
    public ResponseEntity<CategoriaModelResponse> listarCategorias(@PathVariable Integer idCategoria){
        CategoriaResponseDomain categoriasModel = categoriaUseCase.consultarCategoriasPeloId(idCategoria);
        CategoriaModelResponse dataModelResponse = CategoriaEntryPointMapperResponse.converterCategoria(categoriasModel);
        return ResponseEntity.ok(dataModelResponse);
    }



    @DeleteMapping("{idCategoria}")
    public ResponseEntity<Object> excluiCategoriaPeloIdExplicit(@PathVariable Integer idCategoria){
        excluiCategoriaPeloId(idCategoria);
        return ResponseEntity.noContent().build();
    }


    public void excluiCategoriaPeloId(Integer idCategoria) {
        categoriaUseCase.excluiCategoriaPeloId(idCategoria);
    }

}
