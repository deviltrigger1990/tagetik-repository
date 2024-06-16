package it.tagetik.apps.third.project.dto;

import it.tagetik.apps.model.dto.ProductDto;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class ProductByCategoryDto {

    final String categoryDescription;
    final List<ProductDto> products;


}
