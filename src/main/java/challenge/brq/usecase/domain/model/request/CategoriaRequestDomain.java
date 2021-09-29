package challenge.brq.usecase.domain.model.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoriaRequestDomain {

    private String nomeCategoria;
    private Integer idCategoria;

}
