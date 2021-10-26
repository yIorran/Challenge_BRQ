package challenge.brq.usecase.service;

import challenge.brq.usecase.utils.Utils;
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

    public List<ProdutoResponseDomain> consultarProdutosPeloStatus(Boolean status){
        return produtoGateway.consultarProdutoPorStatus(true);
    }

    public List<ProdutoResponseDomain> consultarProdutos() {
        return produtoGateway.consultarProdutos();
    }

    public ProdutoResponseDomain adicionaProdutos(ProdutoRequestDomain produtoRequestDomain) {
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloId(produtoRequestDomain.getCategoria().getIdCategoria());
        Utils.verificarSeCategoriaExisteParaAdicionar(categoriaResponseDomain);
        Utils.verificarSeQuantidadeMaiorIgualZero(produtoRequestDomain.getQuantidadeProduto());
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

    public ProdutoResponseDomain consultarCategoriaPeloIdParaAtualizarParcial(Integer idProduto) {
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

    /*
    TODO
    Verificar motivo de não lançar excessão mandando campos separadamente
     */
    public ProdutoResponseDomain atualizarProdutosParcial(Integer id, ProdutoRequestDomain produtoRequestDomain) {
        consultarCategoriaPeloIdParaAtualizarParcial(produtoRequestDomain.getCategoria().getIdCategoria());
        ProdutoResponseDomain produtoAtual = consultarProdutosPeloId(id);
        CategoriaResponseDomain categoriaResponseDomain = categoriaGateway.consultarCategoriaPeloId(produtoRequestDomain.getCategoria().getIdCategoria());
        Utils.verificarSeCategoriaExisteParaAtualizarParcial(categoriaResponseDomain);
        Utils.verificarSeStatusDoProdutoEAtivo(produtoRequestDomain);
        Utils.verificarSePorcentagemMaiorQueZero(produtoRequestDomain);
        Utils.verificarSeOfertadoAtivoEStatusAtivo(produtoRequestDomain);
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