package it.tagetik.apps.model.mapper;

import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.model.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    @Mapping(target = "category",
            expression = "java(it.tagetik.apps.model.entity.Category.valueOf(src.getCategoryDescription()))")

    public abstract Product map(ProductDto src);

    @Mapping(target = "categoryDescription",
            expression = "java(mapCategory(src.getCategory()))")
    public abstract ProductDto map(Product src);


    protected String mapCategory(Category category) {
        return category.name();
    }


}
