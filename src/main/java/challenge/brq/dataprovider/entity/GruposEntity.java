package challenge.brq.dataprovider.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_GRUPOS")
@Entity
public class GruposEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_GRUPO")
    private Integer id;
    @Column(name = "NOME_GRUPOS")
    private String nome;
    @ManyToMany
    @JoinTable(name = "TB_PERMISSOES_DE_GRUPOS",
    joinColumns = @JoinColumn(name = "cod_grupo"),
    inverseJoinColumns = @JoinColumn(name = "cod_permissao"))
    private List<PermissaoEntity> permissao;

}
