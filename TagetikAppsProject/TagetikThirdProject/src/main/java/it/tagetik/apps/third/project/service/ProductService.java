package it.tagetik.apps.third.project.service;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.model.entity.Product;
import it.tagetik.apps.model.mapper.ProductMapper;
import it.tagetik.apps.third.project.dto.ProductByCategoryDto;
import it.tagetik.apps.third.project.exception.ProductNotFoundException;
import it.tagetik.apps.third.project.mapper.ProductByCategoryMapper;
import it.tagetik.apps.third.project.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductByCategoryMapper productByCategoryMapper;

    public ProductDto addProduct(ProductDto product) {
        Product productEntity = productMapper.map(product);
        productRepository.save(productEntity);
        return product;
    }

    public ProductDto updateProduct(ProductDto product) {

        Product productUpdate = productRepository
                .findById((long) product.getProductId())
                .map(productFound -> {
                    productFound.setCategory(Category.valueOf(product.getCategoryDescription()));
                    productFound.setPrice(product.getPrice());
                    productFound.setDescription(product.getDescription());
                    productFound.setQuantity(product.getQuantity());
                    return productFound;
                }).orElseThrow(() -> new ProductNotFoundException((
                        String.format("Product with id %s not found", product.getProductId()))));
        productRepository.save(productUpdate);
        return product;
    }

    public List<ProductDto> getAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::map)
                .collect(toList());
    }

    public ProductDto getByProductId(Integer productId) {
        return productRepository.findById(Long.valueOf(productId))
                .map(productMapper::map)
                .orElseThrow(() -> new ProductNotFoundException((
                        String.format("Product with id %s not found", productId))));
    }

    public List<ProductDto> getAllProductByCategory(@PathVariable(name = "category") String category) {
        return productRepository.findAll()
                .stream()
                .filter(product -> product.getCategory() == Category.valueOf(category))
                .map(productMapper::map)
                .collect(toList());
    }


    public List<ProductByCategoryDto> getAllProductsGroupedByCategory() {
        Multimap<String, ProductDto> categoryVsProducts = ArrayListMultimap.create();
        List<ProductDto> products = productRepository.findAll()
                .stream()
                .map(productMapper::map)
                .toList();

        for (ProductDto product : products) {
            categoryVsProducts.put(product.getCategoryDescription(), product);
        }

        return productByCategoryMapper.map(categoryVsProducts);

    }

}
