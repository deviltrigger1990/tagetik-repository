package it.tagetik.apps.third.project.mapper;

import com.google.common.collect.Multimap;
import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.third.project.dto.ProductByCategoryDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProductByCategoryMapperImpl implements ProductByCategoryMapper {
    @Override
    public List<ProductByCategoryDto> map(Multimap<String, ProductDto> categoryVsProducts) {

        List<ProductByCategoryDto> productsByCategory = new ArrayList<>();

        for (Map.Entry<String, ProductDto> entry : categoryVsProducts.entries()) {
            String categoryAsKey = entry.getKey();
            List<ProductDto> products = categoryVsProducts.get(categoryAsKey).stream().toList();

            productsByCategory.add(ProductByCategoryDto.builder().
                    categoryDescription(categoryAsKey)
                    .products(products).build());
        }


        return productsByCategory;
    }
}
