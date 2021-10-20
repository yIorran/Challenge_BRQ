package challenge.brq.usecase.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoriaRequestDomain {

    private String nomeCategoria;
    private Integer idCategoria;

}
