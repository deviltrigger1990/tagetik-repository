package it.tagetik.apps.third.project.api;

import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.third.project.dto.ProductByCategoryDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/tagetik/tagetik-webapps/1.0.0/products")
public interface ProductApi {

    @PostMapping
    public ResponseEntity<ProductDto> addProduct( @RequestBody ProductDto product);

    @PutMapping
    public ResponseEntity<ProductDto> updateProduct(@RequestBody ProductDto product);

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAll();

    @GetMapping("{productId}")
    public ResponseEntity<ProductDto> getByProductId(@PathVariable(name = "productId") Integer productId) ;

    @GetMapping("/categories/{category}")
    public ResponseEntity<List<ProductDto>> getAllProductByCategory(@PathVariable(name = "category") String category);

    @GetMapping("/categories")
    public ResponseEntity<List<ProductByCategoryDto>> getAllProductsGroupedByCategory();



}
