package challenge.brq.entrypoint.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * Classe responsavel por conter os atributos de entryPoint
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModelRequestFiltro {


    private String nome;

    private String marca;

    private Double preco;

    private CategoriaModelRequestNome categoria;


}
