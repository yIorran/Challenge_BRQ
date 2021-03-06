package challenge.brq.usecase.utils;

import challenge.brq.usecase.exception.categoria.CategoriaDuplicadaException;
import challenge.brq.usecase.exception.categoria.CategoriaNaoEncontradaException;
import challenge.brq.usecase.exception.produto.*;
import challenge.brq.usecase.model.request.CategoriaRequestDomain;
import challenge.brq.usecase.model.request.ProdutoRequestDomain;
import challenge.brq.usecase.model.request.TabelaNutricionalRequestDomain;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;
import challenge.brq.usecase.model.response.ProdutoResponseDomain;
import challenge.brq.usecase.model.response.TabelaNutricionalResponseDomain;
import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class Utils {

    public static void verificarSeCategoriaFoiEncontrada(CategoriaResponseDomain categoriaResponseDomain, Integer idCategoria){
        if (categoriaResponseDomain == null) {
            throw new CategoriaNaoEncontradaException("Categoria não encontrada: " + idCategoria);
        }
    }

    public static void verificarSeCategoriaDuplicada(CategoriaResponseDomain categoriaResponseDomain, String nome){
        if (categoriaResponseDomain != null) {
            throw new CategoriaDuplicadaException("Categoria já existente: " + nome);
        }
    }

    public static void verificarSeIdExiste(ProdutoResponseDomain produtoResponseDomain){
        if (produtoResponseDomain == null) {
            throw new CategoriaDuplicadaException("ID não encontrado na nossa base");
        }
    }

    public static void verificarSePrecoMenorOuIgualAZero(Double preco) {
        if(preco <= 0) {
            throw new PrecoMenorOuIgualAZeroException("Preço não pode ser menor ou igual a zero");
        }
    }

    public static void verificarSeCategoriaExisteParaAdicionar(Object id) {
        if (id == null) {
            throw new CategoriaNaoEncontradaParaAdicionarOuAtualizarProduto("Categoria não encontrada para fazer adição");
        }
    }

    public static void verificarSeCategoriaExisteParaAtualizarParcial(CategoriaResponseDomain categoriaResponseDomain) {
        if (categoriaResponseDomain == null) {
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (categoriaResponseDomain.getIdCategoria() == null) {
            throw new CategoriaNaoEncontradaParaAdicionarOuAtualizarProduto("Categoria não encontrada para fazer adição");
        }
    }

    public static void verificarSeQuantidadeMaiorIgualZero(Integer numero) {
        if (numero <= 0) {
            throw new QuantidadeMenorQueZeroException("Quantiade menor ou igual a 0.");
        }
    }

    public static void verificarSePorcentagemMaiorQueZeroAposModificacoes(ProdutoResponseDomain produtoResponseDomain) {
        if (produtoResponseDomain.getPorcentagem() == null && produtoResponseDomain.getProdutoOfertado() == null) {
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (produtoResponseDomain.getPorcentagem() == null || produtoResponseDomain.getProdutoOfertado() == null) {
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (produtoResponseDomain.getPorcentagem() <= 0 && produtoResponseDomain.getProdutoOfertado() == true) {
            throw new PorcentagemMaiorQueZeroException("O produto não pode ser ofertado se a porcentage for menor ou igual a 0 "
                    + "porcentagem atual: " + produtoResponseDomain.getPorcentagem());
        }
    }

    public static void verificarSeStatusDoProdutoEAtivoAposModificacoes(ProdutoResponseDomain produtoResponseDomain) {
        if (produtoResponseDomain.getQuantidadeProduto() == null && produtoResponseDomain.getProdutoAtivo() == null) {
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (produtoResponseDomain.getQuantidadeProduto() == null || produtoResponseDomain.getProdutoAtivo() == null) {
            produtoResponseDomain.builder().build();
            return;
        }
        if (produtoResponseDomain.getQuantidadeProduto() <= 0 && produtoResponseDomain.getProdutoAtivo() == true) {
            throw new QuantidadeZeroEProdutoAtivo("Produto não pode ser ativo se a quantidade for igual a 0");
        }
    }

    public static void verificarSeOfertadoAtivoEStatusAtivoAposModificacoes(ProdutoResponseDomain produtoResponseDomain) {
        if (produtoResponseDomain.getProdutoAtivo() == null && produtoResponseDomain.getProdutoOfertado() == null) {
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (produtoResponseDomain.getProdutoAtivo() == null || produtoResponseDomain.getProdutoOfertado() == null) {
            ProdutoResponseDomain.builder().build();
            return;
        }
        if (produtoResponseDomain.getProdutoAtivo() == false && produtoResponseDomain.getProdutoOfertado() == true) {
            throw new VerificarSeStatusInativoEOfertadoAtivoException("O produto não pode ser ofertado se o mesmo estiver inativo.");
        }
    }

    public static ProdutoResponseDomain converterProdutoComValoresValidos(ProdutoRequestDomain produtoRequestDomain, ProdutoResponseDomain produtoAtual) {
        return ProdutoResponseDomain.builder()
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
                .tabelaNutricionalResponseDomain(produtoRequestDomain.getTabelaNutricionalRequestDomain() == null ? produtoAtual.getTabelaNutricionalResponseDomain() : converterTabela(produtoRequestDomain.getTabelaNutricionalRequestDomain(), produtoAtual.getTabelaNutricionalResponseDomain()))
                .build();
    }

    public static TabelaNutricionalResponseDomain converterTabela(TabelaNutricionalRequestDomain tabelaNutricionalRequestDomain, TabelaNutricionalResponseDomain tabelaNutricionalResponseDomain){
        if(Objects.isNull(tabelaNutricionalRequestDomain)){
            return TabelaNutricionalResponseDomain.builder()
                    .gorduraSaturada(tabelaNutricionalResponseDomain.getGorduraSaturada() == null ? tabelaNutricionalResponseDomain.getGorduraSaturada() : tabelaNutricionalRequestDomain.getGorduraSaturada())
                    .valorEnergetico(tabelaNutricionalResponseDomain.getValorEnergetico() == null ? tabelaNutricionalResponseDomain.getValorEnergetico() : tabelaNutricionalRequestDomain.getValorEnergetico())
                    .acucar(tabelaNutricionalResponseDomain.getAcucar() == null ? tabelaNutricionalResponseDomain.getAcucar() : tabelaNutricionalRequestDomain.getAcucar())
                    .fibras(tabelaNutricionalResponseDomain.getFibras() == null ? tabelaNutricionalResponseDomain.getFibras() : tabelaNutricionalRequestDomain.getFibras())
                    .proteinas(tabelaNutricionalResponseDomain.getProteinas() == null ? tabelaNutricionalResponseDomain.getProteinas() : tabelaNutricionalRequestDomain.getProteinas())
                    .sodio(tabelaNutricionalResponseDomain.getSodio() == null ? tabelaNutricionalResponseDomain.getSodio() : tabelaNutricionalRequestDomain.getSodio())
                    .build();
        }
        return TabelaNutricionalResponseDomain.builder()
                .gorduraSaturada(tabelaNutricionalRequestDomain.getGorduraSaturada() == null ? tabelaNutricionalResponseDomain.getGorduraSaturada() : tabelaNutricionalRequestDomain.getGorduraSaturada())
                .valorEnergetico(tabelaNutricionalRequestDomain.getValorEnergetico() == null ? tabelaNutricionalResponseDomain.getValorEnergetico() : tabelaNutricionalRequestDomain.getValorEnergetico())
                .acucar(tabelaNutricionalRequestDomain.getAcucar() == null ? tabelaNutricionalResponseDomain.getAcucar() : tabelaNutricionalRequestDomain.getAcucar())
                .fibras(tabelaNutricionalRequestDomain.getFibras() == null ? tabelaNutricionalResponseDomain.getFibras() : tabelaNutricionalRequestDomain.getFibras())
                .proteinas(tabelaNutricionalRequestDomain.getProteinas() == null ? tabelaNutricionalResponseDomain.getProteinas() : tabelaNutricionalRequestDomain.getProteinas())
                .sodio(tabelaNutricionalRequestDomain.getSodio() == null ? tabelaNutricionalResponseDomain.getSodio() : tabelaNutricionalRequestDomain.getSodio())
                .build();
    }

    public static CategoriaResponseDomain converter(CategoriaRequestDomain categoriaRequestDomain, CategoriaResponseDomain categoriaResponseDomain) {
        if (Objects.isNull(categoriaRequestDomain.getIdCategoria())) {
            return CategoriaResponseDomain.builder()
                    .idCategoria(categoriaResponseDomain.getIdCategoria())
                    .nomeCategoria(categoriaResponseDomain.getNomeCategoria())
                    .build();
        }
        return CategoriaResponseDomain.builder()
                .idCategoria(categoriaRequestDomain.getIdCategoria())
                .nomeCategoria(categoriaRequestDomain.getNomeCategoria())
                .build();
    }
}
