package challenge.brq.usecase.service;

import challenge.brq.usecase.exception.categoria.CategoriaNaoEncontradaException;
import challenge.brq.usecase.exception.categoria.CategoriaNaoExistenteParaAtualizacaoParcialException;
import challenge.brq.usecase.exception.produto.*;
import challenge.brq.usecase.gateway.CategoriaGateway;
import challenge.brq.usecase.gateway.ProdutoGateway;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class ProdutoUseCase {

    private final ProdutoGateway produtoGateway;

    private final CategoriaGateway categoriaGateway;

    public List<ProdutoResponseDomain> consultarProdutos() {
        return produtoGateway.consultarProdutos();
    }

    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain) {
        verificarSeCategoriaExisteParaAdicao(produtoRequestDomain);
        Object id = consultarProdutosPeloIdParaAtualizarParcial(produtoRequestDomain.getCategoria().getIdCategoria());
        verificarSeCategoriaExisteParaAdicionar(id);
        verificarSeQuantidadeMaiorIgualZero(produtoRequestDomain.getQuantidadeProduto());
        return produtoGateway.adicionaProdutos(produtoRequestDomain);
    }

    public void excluiProdutoPeloId(Integer idCategoria) {
        consultarProdutosPeloId(idCategoria);
        produtoGateway.excluirProdutosPeloId(idCategoria);
    }

    public ProdutoResponseDomain consultarProdutosPeloId(Integer idProduto) {
        if (produtoGateway.consultarProdutosPeloId(idProduto) == null) {
            throw new ProdutoPorIDNaoEncontrado("Id não encontrado em nossa base: " + idProduto);
        } else
            return produtoGateway.consultarProdutosPeloId(idProduto);
    }

    public ProdutoResponseDomain consultarProdutosPeloIdParaAtualizarParcial(Integer idProduto) {
        if (idProduto == null) {
            return ProdutoResponseDomain.builder().build();
        }
        if (produtoGateway.consultarProdutosPeloId(idProduto) == null) {
            throw new CategoriaNaoExistenteParaAtualizacaoParcialException("Categoria informada inexistente para atualização");
        } else
            return produtoGateway.consultarProdutosPeloId(idProduto);
    }

    public List<ProdutoResponseDomain> consultarProdutosPelaMarcaOuCategoria(String marca, String categoria) {
        if (categoria != null) {
            return consultarProdutosPelaCategoria(categoria);
        } else if (marca != null) {
            return consultarProdutosPelaMarca(marca);
        }
        return consultarProdutos();
    }

    public List<ProdutoResponseDomain> consultarProdutosPelaMarca(String marca) {
        return produtoGateway.consultarProdutosPelaMarca(marca);
    }

    public List<ProdutoResponseDomain> consultarProdutosPelaCategoria(String categoria) {
        return produtoGateway.consultarProdutosPelaCategoria(categoria);
    }


    public ProdutoResponseDomain atualizarProdutosParcial(Integer id, ProdutoRequestDomain produtoRequestDomain) {
        consultarProdutosPeloIdParaAtualizarParcial(produtoRequestDomain.getCategoria().getIdCategoria());
        ProdutoResponseDomain produtoAtual = consultarProdutosPeloId(id);
        verificarSeStatusDoProdutoEAtivo(produtoRequestDomain);
        verificarSePorcentagemMaiorQueZero(produtoRequestDomain);
        verificarSeOfertadoAtivoEStatusAtivo(produtoRequestDomain);
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
                .categoria(produtoRequestDomain.getCategoria() == null ? produtoAtual.getCategoria() : converter(produtoRequestDomain.getCategoria(), produtoAtual.getCategoria()))
                .build();
        return produtoGateway.atualizarProdutosParcial(produtoAtual);
    }

    // métodos auxiliares:

    public ProdutoResponseDomain verificarSePorcentagemMaiorQueZero(ProdutoRequestDomain produtoRequestDomain){
        if(produtoRequestDomain.getPorcentagem() == null && produtoRequestDomain.getProdutoOfertado() == null){
            return ProdutoResponseDomain.builder().build();
        }
        if(produtoRequestDomain.getPorcentagem() == null || produtoRequestDomain.getProdutoOfertado() == null){
            return ProdutoResponseDomain.builder().build();
        }
        if(produtoRequestDomain.getPorcentagem() <= 0 && produtoRequestDomain.getProdutoOfertado() == true){
            throw new PorcentagemMaiorQueZeroException("O produto não pode ser ofertado se a porcentage for menor ou igual a 0 "
            + "porcentagem atual: " + produtoRequestDomain.getPorcentagem());
        }
        return null;
    }

    public ProdutoResponseDomain verificarSeStatusDoProdutoEAtivo(ProdutoRequestDomain produtoRequestDomain){
        if(produtoRequestDomain.getQuantidadeProduto() == null && produtoRequestDomain.getProdutoAtivo() == null){
            return ProdutoResponseDomain.builder().build();
        }
        if(produtoRequestDomain.getQuantidadeProduto() == null || produtoRequestDomain.getProdutoAtivo() == null){
            return ProdutoResponseDomain.builder().build();
        }
        if(produtoRequestDomain.getQuantidadeProduto() == 0 && produtoRequestDomain.getProdutoAtivo() == true){
            throw new QuantidadeZeroEProdutoAtivo("Produto não pode ser ativo se a quantidade for igual a 0");
        }
        return null;
    }

    public void verificarSeCategoriaExisteParaAdicionar(Object id) {
        if (id == null) {
            throw new CategoriaNaoEncontradaException("Categoria não encontrada para fazer adição");
        }
    }

    public void verificarSeCategoriaExisteParaAdicao(ProdutoRequestDomain produtoRequestDomain) {
        if (produtoRequestDomain.getCategoria().getIdCategoria() == null) {
            throw new AdicionarProdutosIncompletoException("Categoria informada inexistente ou não informada.");
        }
    }

    public ProdutoResponseDomain verificarSeOfertadoAtivoEStatusAtivo(ProdutoRequestDomain produtoRequestDomain) {
        if(produtoRequestDomain.getProdutoAtivo() == null && produtoRequestDomain.getProdutoOfertado() == null){
            return ProdutoResponseDomain.builder().build();
        }
        if(produtoRequestDomain.getProdutoAtivo() == null || produtoRequestDomain.getProdutoOfertado() == null){
            return ProdutoResponseDomain.builder().build();
        }
        if (produtoRequestDomain.getProdutoAtivo() == false && produtoRequestDomain.getProdutoOfertado() == true) {
            throw new VerificarSeStatusInativoEOfertadoAtivoException("O produto não pode ser ofertado se o mesmo estiver inativo.");
        }
        return null;
    }

    public void verificarSeQuantidadeMaiorIgualZero(Integer numero) {
        if (numero <= 0) {
            throw new QuantidadeMenorQueZeroException("Quantiade menor ou igual a 0.");
        }
    }

    private CategoriaResponseDomain converter(CategoriaRequestDomain categoriaRequestDomain, CategoriaResponseDomain produtoRequestDomain) {
        if(Objects.isNull(categoriaRequestDomain.getIdCategoria())){
            return CategoriaResponseDomain.builder()
                    .idCategoria(produtoRequestDomain.getIdCategoria())
                    .nomeCategoria(produtoRequestDomain.getNomeCategoria())
                    .build();
        }
        return CategoriaResponseDomain.builder()
                .idCategoria(categoriaRequestDomain.getIdCategoria())
                .nomeCategoria(categoriaRequestDomain.getNomeCategoria())
                .build();
    }
}