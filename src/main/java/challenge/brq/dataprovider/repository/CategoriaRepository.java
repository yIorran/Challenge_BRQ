package challenge.brq.dataprovider.repository;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {


    CategoriaEntity findByNomeCategoriaIgnoreCaseLike(String categoriaEntity);

}
