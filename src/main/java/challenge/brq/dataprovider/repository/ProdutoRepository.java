package challenge.brq.dataprovider.repository;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByMarcaProdutoContaining(String marca);

    List<ProdutoEntity> findByNomeProduto(String nome);

    @Query("select p from ProdutoEntity p join p.categoria categoria where categoria.nomeCategoria=:nome")
    List<ProdutoEntity> pesquisarPorNomeCategoria(@Param("nome") String nome);

    ProdutoEntity findByProdutoAtivo(Boolean status);

}
