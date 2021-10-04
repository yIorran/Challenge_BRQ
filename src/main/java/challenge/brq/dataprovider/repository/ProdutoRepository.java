package challenge.brq.dataprovider.repository;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import challenge.brq.usecase.domain.model.request.ProdutoRequestDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer> {

    List<ProdutoEntity> findByMarcaProdutoContaining(String marca);

}
