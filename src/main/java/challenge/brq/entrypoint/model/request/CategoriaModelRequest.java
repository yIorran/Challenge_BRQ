package challenge.brq.entrypoint.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Classe responsavel por conter os atributos de entryPoint
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaModelRequest {

    private Integer id;
    private String nome;

}
