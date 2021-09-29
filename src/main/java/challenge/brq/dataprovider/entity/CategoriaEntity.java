package challenge.brq.dataprovider.entity;

import lombok.*;

import javax.persistence.*;

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

}
