package it.tagetik.apps.third.project.api;

import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.third.project.dto.ProductByCategoryDto;
import it.tagetik.apps.third.project.service.ProductService;
import it.tagetik.apps.third.project.validator.ProductValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductApiController implements ProductApi{

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductValidator productValidator;

    @Override
    public ResponseEntity<ProductDto> addProduct(ProductDto product) {

        if(product == null){
            throw  new IllegalArgumentException("Product could not be null");
        }

        productValidator.validate(product);

        return ResponseEntity.ok(productService.addProduct(product));
    }

    @Override
    public ResponseEntity<ProductDto> updateProduct(ProductDto product) {

        if(product == null){
            throw  new IllegalArgumentException("Product could not be null");
        }

        productValidator.validate(product);


        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    @Override
    public ResponseEntity<ProductDto> getByProductId(Integer productId) {

        if(productId == null){
            throw  new IllegalArgumentException("Product Id could not be null");
        }

        return ResponseEntity.ok(productService.getByProductId(productId));
    }

    @Override
    public ResponseEntity<List<ProductDto>> getAllProductByCategory(String category) {

        if(category == null){
            throw  new IllegalArgumentException("Category could not be null");
        }

        return ResponseEntity.ok(productService.getAllProductByCategory(category));
    }

    @Override
    public ResponseEntity<List<ProductByCategoryDto>> getAllProductsGroupedByCategory() {
        return ResponseEntity.ok(productService.getAllProductsGroupedByCategory());
    }
}
