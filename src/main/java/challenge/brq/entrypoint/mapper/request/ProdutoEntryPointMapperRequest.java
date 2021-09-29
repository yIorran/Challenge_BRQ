package challenge.brq.entrypoint.mapper.request;

import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;

public class ProdutoEntryPointMapperRequest {

    public static ProdutoRequestDomain converter(String nomeProduto){
        return ProdutoRequestDomain.builder().nomeProduto(nomeProduto).build();
    }

}
