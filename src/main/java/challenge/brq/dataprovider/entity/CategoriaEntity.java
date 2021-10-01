package challenge.brq.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @Column(name = "NOME_CATEGORIA_PRODUTO")
    private String nomeCategoria;

    @OneToMany(mappedBy = "categoriaProduto")
    private List<ProdutoEntity> produtos;

}
