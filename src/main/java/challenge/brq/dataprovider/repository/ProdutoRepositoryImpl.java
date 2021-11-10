package challenge.brq.dataprovider.repository;

import challenge.brq.dataprovider.entity.ProdutoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class ProdutoRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public Page<ProdutoEntity> listarComFiltro(ProdutoEntity produtoEntity){
        //config padrão
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ProdutoEntity> criteria = builder.createQuery(ProdutoEntity.class);
        Root<ProdutoEntity> root = criteria.from(ProdutoEntity.class);

        var predicados = new ArrayList<Predicate>();

        //condições e predicados
        if(StringUtils.hasText(produtoEntity.getNomeProduto())){
            predicados.add(builder.like(root.get("nomeProduto"), "%" + produtoEntity.getNomeProduto() + "%"));
        }
        if(StringUtils.hasText(produtoEntity.getMarcaProduto())){
            predicados.add(builder.like(root.get("marcaProduto"), "%" + produtoEntity.getMarcaProduto() + "%"));
        }
        if(!Objects.isNull(produtoEntity.getPrecoProduto())){
            predicados.add(builder.lessThanOrEqualTo(root.get("precoProduto"), produtoEntity.getPrecoProduto()));
        }
        if(StringUtils.hasText(produtoEntity.getCategoria().getNomeCategoria())){
            predicados.add(builder.equal(root.get("categoria").get("nomeCategoria"), produtoEntity.getCategoria().getNomeCategoria()));
        }

        //lista contendo os predicados selecionados
        criteria.where(predicados.toArray(new Predicate[0]));

        //execução
        TypedQuery<ProdutoEntity> query = manager.createQuery(criteria);
        return new PageImpl<>(query.getResultList());
    }
}
