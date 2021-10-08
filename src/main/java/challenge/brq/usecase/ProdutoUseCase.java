package challenge.brq.usecase;

import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.domain.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.gateway.ProdutoGateway;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    public List<ProdutoResponseDomain> consultarProdutos(){
        return produtoGateway.consultarProdutos();
    }

    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain){
        return produtoGateway.adicionaProdutos(produtoRequestDomain);
    }

    public void excluiProdutoPeloId(Integer idCategoria){
        produtoGateway.excluirProdutosPeloId(idCategoria);
    }

    public ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto){
        return produtoGateway.consultarProdutosPeloId(idProduto);
    }


    public List<ProdutoResponseDomain> consultarProdutosPelaMarcaOuCategoria(String marca, String categoria) {
       if (marca == null) {
           return produtoGateway.consultarProdutosPelaMarcaOuCategoria(categoria);
       }
        else
        return produtoGateway.consultarProdutosPelaMarcaOuCategoria(marca);
    }

    public ProdutoResponseDomain atualizarProdutos(Integer id, ProdutoRequestDomain produtoRequestDomain){
        ProdutoResponseDomain produtoAtual = consultarProdutosPeloId(id);;
        produtoAtual = ProdutoResponseDomain.builder()
                .codigoProduto(produtoAtual.getCodigoProduto())
                .nomeProduto(produtoRequestDomain.getNomeProduto())
                .descricaoProduto(produtoRequestDomain.getDescricaoProduto())
                .marcaProduto(produtoRequestDomain.getMarcaProduto())
                .quantidadeProduto(produtoRequestDomain.getQuantidadeProduto())
                .precoProduto(produtoRequestDomain.getPrecoProduto())
                .produtoAtivo(produtoRequestDomain.getProdutoAtivo())
                .produtoOfertado(produtoRequestDomain.getProdutoOfertado())
                .porcentagem(produtoRequestDomain.getPorcentagem())
                .categoria(produtoRequestDomain.getCategoria())
                .build();
        return produtoGateway.atualizaProdutos(produtoAtual);
    }

    public ProdutoResponseDomain atualizarProdutosParcial(Integer id, ProdutoRequestDomain produtoRequestDomain) {
        ProdutoResponseDomain produtoAtual = consultarProdutosPeloId(id);
        produtoAtual = ProdutoResponseDomain.builder()
                .codigoProduto(produtoAtual.getCodigoProduto())
                .nomeProduto(produtoRequestDomain.getNomeProduto() == null ? produtoAtual.getNomeProduto() : produtoRequestDomain.getNomeProduto())
                .descricaoProduto(produtoRequestDomain.getDescricaoProduto() == null ? produtoAtual.getDescricaoProduto() : produtoRequestDomain.getDescricaoProduto())
                .marcaProduto(produtoRequestDomain.getMarcaProduto() == null ? produtoAtual.getMarcaProduto() : produtoRequestDomain.getMarcaProduto())
                .quantidadeProduto(produtoRequestDomain.getQuantidadeProduto() == null ? produtoAtual.getQuantidadeProduto() : produtoRequestDomain.getQuantidadeProduto())
                .precoProduto(produtoRequestDomain.getPrecoProduto() == null ? produtoAtual.getPrecoProduto() : produtoRequestDomain.getPrecoProduto())
                .produtoAtivo(produtoRequestDomain.getProdutoAtivo() == null ? produtoAtual.getProdutoAtivo() : produtoRequestDomain.getProdutoAtivo())
                .produtoOfertado(produtoRequestDomain.getProdutoOfertado() == null ? produtoAtual.getProdutoOfertado() : produtoRequestDomain.getProdutoOfertado())
                .porcentagem(produtoRequestDomain.getPorcentagem() == null ? produtoAtual.getPorcentagem() : produtoRequestDomain.getPorcentagem())
                .categoria(produtoRequestDomain.getCategoria() == null ? produtoAtual.getCategoria() : produtoRequestDomain.getCategoria())
                .build();
        return produtoGateway.atualizaProdutos(produtoAtual);
    }



    }

