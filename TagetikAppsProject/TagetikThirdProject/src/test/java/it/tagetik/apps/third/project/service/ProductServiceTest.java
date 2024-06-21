package tagetik.firsttest;

import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.third.project.Application;
import it.tagetik.apps.third.project.dto.ProductByCategoryDto;
import it.tagetik.apps.third.project.exception.ProductNotFoundException;
import it.tagetik.apps.third.project.repository.ProductRepository;
import it.tagetik.apps.third.project.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = {"it.tageik.apps.model"})
public class ProductServiceTest {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        clearDb();
        populateDb();
    }

    @After
    public void tearDown() {
        clearDb();
    }

    private void populateDb() {
        ProductDto product1 = ProductDto.builder()
                .categoryDescription("CAT1")
                .description("PROD 1")
                .price(3.0)
                .quantity(1)
                .build();

        ProductDto product2 = ProductDto.builder()
                .categoryDescription("CAT3")
                .description("PROD 2")
                .price(3.0)
                .quantity(1)
                .build();

        ProductDto product3 = ProductDto.builder()
                .categoryDescription("CAT3")
                .description("PROD 3")
                .price(3.0)
                .quantity(1)
                .build();

        ProductDto product4 = ProductDto.builder()
                .categoryDescription("CAT1")
                .description("PROD 4")
                .price(3.0)
                .quantity(1)
                .build();

        productService.addProduct(product1);
        productService.addProduct(product2);
        productService.addProduct(product3);
        productService.addProduct(product4);


    }

    private void clearDb() {
        productRepository.deleteAll();
    }

    @Test
    public void testAddProduct() {

        List<ProductDto> products = productService.getAll();

        int productBeforeAdd = products.size();

        ProductDto product4 = ProductDto.builder()
                .categoryDescription("CAT3")
                .description("PROD 4")
                .price(3.0)
                .quantity(1)
                .build();

        productService.addProduct(product4);

        List<ProductDto> productsAfterAdd = productService.getAll();

        assertTrue(
                productsAfterAdd.stream().anyMatch(
                        product -> product.getDescription().equals("PROD 4")));
        assertEquals(productsAfterAdd.size(), productBeforeAdd + 1);


    }

    @Test
    public void testAllProducts() {
        List<ProductDto> products = productService.getAll();
        assertEquals(products.size(), 4);

    }

    @Test
    public void testGetProductById() {
        List<ProductDto> products = productService.getAll();

        ProductDto product = products.iterator().next();

        ProductDto productById = productService.getByProductId(product.getProductId());

        assertNotNull(productById);

    }

    @Test
    public void testGetProductByIdButProductNotFound() {

        Integer productId = 9999999;

        Throwable exception = assertThrows(ProductNotFoundException.class, () -> productService.getByProductId(productId));
        assertEquals(
                String.format("Product with id %s not found", productId), exception.getMessage());

    }

    @Test
    public void testGetProductByCategory() {

        List<ProductDto> productsByCat1 = productService.getAllProductByCategory("CAT1");
        assertEquals(productsByCat1.size(), 2);
        assertTrue(
                productsByCat1.stream().allMatch(
                        product -> product.getCategoryDescription().equals("CAT1")));

    }

    @Test
    public void testGetProductByCategoryButNotExistProductBelongToThatCategory() {
        List<ProductDto> productsByCat1 = productService.getAllProductByCategory("CAT2");
        assertTrue(productsByCat1.isEmpty());
    }

    @Test
    public void testUpdateProductWithANewOne() {
        List<ProductDto> products = productService.getAll();

        ProductDto productToUpdate = products.iterator().next();
        ProductDto newProduct = ProductDto
                .builder()
                .productId(productToUpdate.getProductId())
                .quantity(999)
                .categoryDescription("CAT3")
                .description("New Description")
                .price(999.0)
                .build();

        productService.updateProduct(newProduct);
        assertEquals(newProduct.getQuantity(), 999);
        assertEquals(newProduct.getCategoryDescription(), "CAT3");
        assertEquals(newProduct.getDescription(), "New Description");
        assertEquals(newProduct.getPrice(), 999.0);

    }

    @Test
    public void testUpdateProductWithIdThatDoesNotExist() {
        List<ProductDto> products = productService.getAll();

        Integer productId = 9999999;

        ProductDto productToUpdate = products.iterator().next();
        ProductDto newProduct = ProductDto
                .builder()
                .productId(productId)
                .quantity(999)
                .categoryDescription("CAT3")
                .description("New Description")
                .price(999.0)
                .build();

        Throwable exception = assertThrows(ProductNotFoundException.class, () -> productService.getByProductId(productId));
        assertEquals(
                String.format("Product with id %s not found", productId), exception.getMessage());


    }

    @Test
    public void testGroupProductByAllCategory() {

        List<ProductByCategoryDto> productsAggregateByCategory = productService.getAllProductsGroupedByCategory();

        List<ProductDto> productsByCat1 = productsAggregateByCategory.stream()
                .filter(productAggregate -> productAggregate.getCategoryDescription().equals("CAT1"))
                .flatMap(productAggr -> productAggr.getProducts().stream())
                .toList();

        assertEquals(productsByCat1.size(), 2);

        assertTrue(productsByCat1.stream().anyMatch(
                product -> product.getDescription().equals("PROD 1")));
        assertTrue(productsByCat1.stream().anyMatch(
                product -> product.getDescription().equals("PROD 4")));

        List<ProductDto> productsByCat3 = productsAggregateByCategory.stream()
                .filter(productAggregate -> productAggregate.getCategoryDescription().equals("CAT3"))
                .flatMap(productAggr -> productAggr.getProducts().stream())
                .toList();


        assertEquals(productsByCat3.size(), 2);
        assertTrue(productsByCat3.stream().anyMatch(
                product -> product.getDescription().equals("PROD 2")));
        assertTrue(productsByCat3.stream().anyMatch(
                product -> product.getDescription().equals("PROD 3")));

        List<ProductDto> productsByCat2 = productsAggregateByCategory.stream()
                .filter(productAggregate -> productAggregate.getCategoryDescription().equals("CAT2"))
                .flatMap(productAggr -> productAggr.getProducts().stream())
                .toList();

        assertEquals(productsByCat2.size(), 0);


    }


}
