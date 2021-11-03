package challenge.brq.entrypoint.exception.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Builder
public class Problem {

    private Integer status;
    private String problema;
    private String mensagem;
    private List<CamposNaoPreenchidos> campos;

    @Getter
    @Builder
    public static class CamposNaoPreenchidos {

        private String campo;
        private String mensagem;

    }

}
