package challenge.brq.dataprovider.mapper.response;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CategoriaResponseMapper {

    private CategoriaResponseMapper() {
    }

    public static List<CategoriaResponseDomain> converter(final List<CategoriaEntity> categoriaEntitie) {
        List<CategoriaResponseDomain> categoriaResponseDomains = new ArrayList<>();
        categoriaEntitie.forEach(categoria -> {
            CategoriaResponseDomain categoriaDomainModel = converterCategoria(categoria);
            categoriaResponseDomains.add(categoriaDomainModel);
        });
        return categoriaResponseDomains;
    }

    public static CategoriaResponseDomain converterCategoria(CategoriaEntity categoriaEntity) {
        if(Objects.isNull(categoriaEntity)){
            return CategoriaResponseDomain.builder().build();
        }
        return CategoriaResponseDomain.builder()
                .idCategoria(categoriaEntity.getId())
                .nomeCategoria(categoriaEntity.getNomeCategoria())
                .build();
    }
}
