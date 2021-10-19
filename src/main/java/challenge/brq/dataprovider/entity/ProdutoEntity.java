package challenge.brq.dataprovider.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

/**
 * Classe de entidade produto
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString
@Builder
@Table(name = "TB_PROD")
public class ProdutoEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_PROD")
    private Integer codigoProduto;

    @Column(name = "NOME_PROD")
    private String nomeProduto;

    @Column(name = "DESC_PROD")
    private String descricaoProduto;

    @Column(name = "QTD_PROD")
    private Integer quantidadeProduto;

    @Column(name = "PRECO_PROD")
    private Double precoProduto;

    @Column(name = "SITU_PROD")
    private Boolean produtoAtivo;

    @Column(name = "PROD_OFERTADO")
    private Boolean produtoOfertado;

    @Column(name = "PORCENT_OFERTA_PROD")
    private Integer porcentagemoferta;

    @Column(name = "MARCA_PRODUTO")
    private String marcaProduto;

    @ManyToOne(targetEntity=CategoriaEntity.class)
    @JoinColumn(nullable = false)
    private CategoriaEntity categoria;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProdutoEntity that = (ProdutoEntity) o;

        return Objects.equals(codigoProduto, that.codigoProduto);
    }

    @Override
    public int hashCode() {
        return 1685932735;
    }
}
