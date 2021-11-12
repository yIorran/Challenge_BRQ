package challenge.brq.entrypoint.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * Classe responsavel por conter os atributos de entryPoint
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaModelRequestNome {


    private String nome;

}
