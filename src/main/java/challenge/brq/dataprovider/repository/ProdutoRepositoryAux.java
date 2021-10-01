package challenge.brq.dataprovider.repository;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepositoryAux extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByMarcaProduto(String marcaProduto);

}
