package challenge.brq.entrypoint.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoModelRequest {

    private String nome;
    private String descricao;
    private String marca;
    private Integer quantidade;
    private Double preco;
    private Integer porcentagem;

}