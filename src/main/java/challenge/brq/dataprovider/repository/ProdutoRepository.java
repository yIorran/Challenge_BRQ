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

    Page<ProdutoEntity> findByMarcaProdutoContaining(String marca, Pageable pageable);

    List<ProdutoEntity> findByNomeProduto(String nome);

    @Query("select p from ProdutoEntity p join p.categoria categoria where categoria.nomeCategoria=:nome")
    Page<ProdutoEntity> pesquisarPorNomeCategoria(@Param("nome") String nome, Pageable pageable);

    Page<ProdutoEntity> findByProdutoAtivo(Boolean status, Pageable pageable);

}
