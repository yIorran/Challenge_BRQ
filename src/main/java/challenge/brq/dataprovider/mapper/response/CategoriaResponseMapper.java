package challenge.brq.dataprovider.mapper.response;

import challenge.brq.dataprovider.entity.CategoriaEntity;
import challenge.brq.usecase.model.response.CategoriaResponseDomain;

import java.util.ArrayList;
import java.util.List;

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
        return CategoriaResponseDomain.builder()
                .idCategoria(categoriaEntity.getId())
                .nomeCategoria(categoriaEntity.getNomeCategoria())
                .build();
    }

    public static CategoriaResponseDomain converterCategoriaParaAtualizacao(Integer id, CategoriaEntity categoriaEntity) {
        return CategoriaResponseDomain.builder()
                .idCategoria(id)
                .nomeCategoria(categoriaEntity.getNomeCategoria())
                .build();
    }

}
