package challenge.brq.dataprovider.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;


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

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "NOME_CATEGORIA_PRODUTO")
    private String nomeCategoria;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CategoriaEntity that = (CategoriaEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 455218708;
    }
}
