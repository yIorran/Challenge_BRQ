package challenge.brq.dataprovider.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_USUARIO")
@Entity
public class UsuarioEntity {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_USUARIO")
    private Integer id;
    @Column(name = "NOME_USUARIO")
    private String nome;
    @Column(name = "EMAIL_USUARIO")
    private String email;
    @Column(name = "SENHA_USUARIO")
    private String senha;
    @ManyToMany
    @JoinTable(name = "TB_GRUPOS_DE_USUARIOS",
    joinColumns = @JoinColumn(name = "cod_usuario"),
    inverseJoinColumns = @JoinColumn(name = "cod_grupo"))
    private List<GruposEntity> grupos;

}
