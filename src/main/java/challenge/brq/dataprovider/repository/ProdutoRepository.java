package challenge.brq.dataprovider.repository;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    @Query("select p from ProdutoEntity p where p.marcaProduto like %?1%")
    Page<ProdutoEntity> pesquisarPorMarcaProdutoParaExclusao(String marca, Pageable pageable);

    @Query("select p from ProdutoEntity p join p.categoria categoria where categoria.nomeCategoria=:nome")
    Page<ProdutoEntity> pesquisarPorNomeCategoriaParaExclusao(@Param("nome") String nome, Pageable pageable);

    Page<ProdutoEntity> findByProdutoOfertadoTrue(Pageable pageable);

    @Query("select p from ProdutoEntity p where p.precoProduto <= ?1")
    Page<ProdutoEntity> findByPrecoProduto(Double preco, Pageable pageable);

    @Query("select p from ProdutoEntity p where p.marcaProduto like %?1% and p.precoProduto <= ?2")
    Page<ProdutoEntity> pesquisarPorProdutoEPrecoMenorQueValor(String marca, Double preco, Pageable pageable);

    @Query("select p from ProdutoEntity p where p.nomeProduto like %?1% and p.precoProduto <= ?2")
    Page<ProdutoEntity> pesquisarPorNomeProdutoEPrecoMenorQueValor(String nome, Double preco, Pageable pageable);

    @Query("select b from CategoriaEntity a, ProdutoEntity b where a.nomeCategoria =:nome and b.precoProduto <=:preco")
    Page<ProdutoEntity> pesquisarPorNomeCategoriaEPrecoMenorQueValor(@Param("nome")String nome,@Param("preco")Double preco, Pageable pageable);

    @Query("select p from ProdutoEntity p join p.categoria categoria where categoria.nomeCategoria=:nome")
    Page<ProdutoEntity> pesquisarPorNomeCategoria(@Param("nome") String nome, Pageable pageable);



}
