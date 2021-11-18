package challenge.brq.entrypoint.exception.api;

import lombok.Getter;

@Getter
public enum ProblemType {

    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos");

    private String title;


    ProblemType(String path, String title) {
        this.title = title;
    }

}
