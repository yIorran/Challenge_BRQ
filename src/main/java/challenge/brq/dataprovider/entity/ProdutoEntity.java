package challenge.brq.dataprovider.entity;

import lombok.*;

import javax.persistence.*;

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

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @JoinColumn(name = "ID_CATEGORIA_PRODUTO")
    private CategoriaEntity categoria;

}
