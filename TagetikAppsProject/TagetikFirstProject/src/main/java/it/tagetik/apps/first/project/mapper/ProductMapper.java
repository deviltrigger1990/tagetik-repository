package it.tagetik.apps.first.project.mapper;

import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.model.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class ProductMapper {
    public abstract Product map(ProductDto src);


}
