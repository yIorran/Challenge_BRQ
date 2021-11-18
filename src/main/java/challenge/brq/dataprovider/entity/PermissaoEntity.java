package challenge.brq.dataprovider.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_PERMISSOES")
@Entity
public class PermissaoEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_PERMISSAO")
    private Integer id;
    @Column(name = "NOME_PERMISSAO")
    private String nome;
    @Column(name = "NOME_DESCRICAO")
    private String descricao;

}
