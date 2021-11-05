package challenge.brq.dataprovider.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class TabelaNutricionalEntity {

    @Column(name = "tb_valorEnergetico")
    private String valorEnergetico;
    @Column(name = "tb_gorduraSaturada")
    private String gorduraSaturada;
    @Column(name = "tb_sodio")
    private String sodio;
    @Column(name = "tb_acucar")
    private String acucar;
    @Column(name = "tb_proteinas")
    private String proteinas;
    @Column(name = "tb_fibras")
    private String fibras;
}

