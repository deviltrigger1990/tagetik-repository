package it.tagetik.apps.third.project.mapper;

import com.google.common.collect.Multimap;
import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.third.project.dto.ProductByCategoryDto;

import java.util.List;

/**
 * Interfate that convert a {#link Multimap<String,ProductDto>} to a list of
 * {@link ProductByCategoryMapper}
 */
@FunctionalInterface
public interface ProductByCategoryMapper {

    /**
     * Convert a {#link Multimap<String,ProductDto>} to a list of
     *  * {@link ProductByCategoryMapper}
     * @return List<ProductByCategoryDto>
     */

    public List<ProductByCategoryDto> map(Multimap<String, ProductDto> categoryVsProducts);
}
