package challenge.brq.usecase.model.response;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoriaResponseDomain {

    private String nomeCategoria;
    private Integer idCategoria;

}
