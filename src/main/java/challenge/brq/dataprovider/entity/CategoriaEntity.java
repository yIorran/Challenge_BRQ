package challenge.brq.dataprovider.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


/**
 * Classe de entidade categoria
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_CATEGORIA_PROD")
@Entity
public class CategoriaEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCategoria;

    @JsonIgnore
    @Column(name = "NOME_CATEGORIA_PRODUTO")
    private String nomeCategoria;

    @JsonIgnore
    @OneToMany(mappedBy = "categoria", targetEntity=ProdutoEntity.class)
    private List<ProdutoEntity> produtos;

}
