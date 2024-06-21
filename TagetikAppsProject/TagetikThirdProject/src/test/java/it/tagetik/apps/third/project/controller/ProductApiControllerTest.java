package it.tagetik.apps.third.project.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.third.project.Application;
import it.tagetik.apps.third.project.dto.ProductByCategoryDto;
import it.tagetik.apps.third.project.exception.ApiError;
import it.tagetik.apps.third.project.repository.ProductRepository;
import it.tagetik.apps.third.project.service.ProductService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ComponentScan(basePackages = {"it.tageik.apps.model"})
public class ProductApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ObjectMapper objectMapper;

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
    public void testAddProduct() throws Exception {

        String jsonProductRequest = "{"
                + "\"description\": \"Sample Product CAT2\","
                + "\"categoryDescription\": \"CAT2\","
                + "\"quantity\": 10,"
                + "\"price\": 99.99"
                + "}";

        MvcResult productAddResponse = this.mockMvc.perform(post(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductRequest))
                .andExpect(status().isOk())
                .andReturn();

        String productResponse = productAddResponse.getResponse().getContentAsString();

        ProductDto responseProduct = objectMapper.readValue(productResponse,
                ProductDto.class);

        assertEquals("Sample Product CAT2", responseProduct.getDescription());
        assertEquals("CAT2", responseProduct.getCategoryDescription());
        assertEquals(10, responseProduct.getQuantity());
        assertEquals(99.99, responseProduct.getPrice(), 0.01);

        String creationDate = responseProduct.getCreationDate().toLocalDate().toString();
        String updateDate = responseProduct.getUpdatedAt().toLocalDate().toString();

        String dateFormatRegex = "\\d{4}-\\d{2}-\\d{2}";

        assertTrue(creationDate.matches(dateFormatRegex));
        assertTrue(updateDate.matches(dateFormatRegex));

    }

    @Test
    public void testAddProductButProductIsNull() throws Exception {

        String jsonProductRequest = "";

        MvcResult productAddResponse = this.mockMvc.perform(post(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductRequest))
                .andExpect(status().is5xxServerError())
                .andReturn();

        ApiError errorResponse = objectMapper.readValue(productAddResponse.getResponse().getContentAsString(),
                ApiError.class);

        assertEquals(errorResponse.getStatus(), 404);


    }

    @Test
    public void testAddProductButQuantityIsNegative() throws Exception {

        String jsonProductRequest = "{"
                + "\"description\": \"Sample Product CAT2\","
                + "\"categoryDescription\": \"CAT2\","
                + "\"quantity\": -10,"
                + "\"price\": 99.99"
                + "}";

        MvcResult productAddResponse = this.mockMvc.perform(post(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductRequest))
                .andExpect(status().is4xxClientError())
                .andReturn();

        ApiError errorResponse = objectMapper.readValue(productAddResponse.getResponse().getContentAsString(),
                ApiError.class);

        assertEquals(errorResponse.getStatus(), 400);
        assertEquals(errorResponse.getMessage(), "Quantity could not be a negative value");

    }

    @Test
    public void testAddProductButPriceIsNegative() throws Exception {

        String jsonProductRequest = "{"
                + "\"description\": \"Sample Product CAT2\","
                + "\"categoryDescription\": \"CAT2\","
                + "\"quantity\": 10,"
                + "\"price\": -99.99"
                + "}";

        MvcResult productAddResponse = this.mockMvc.perform(post(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductRequest))
                .andExpect(status().is4xxClientError())
                .andReturn();

        ApiError errorResponse = objectMapper.readValue(productAddResponse.getResponse().getContentAsString(),
                ApiError.class);

        assertEquals(errorResponse.getStatus(), 400);
        assertEquals(errorResponse.getMessage(), "Price could not be a negative value");

    }

    @Test
    public void testUpdateProduct() throws Exception {

        String jsonProductRequest = "{"
                + "\"description\": \"Sample Product CAT2\","
                + "\"categoryDescription\": \"CAT2\","
                + "\"quantity\": 10,"
                + "\"price\": 99.99"
                + "}";

        MvcResult productAddResponse = this.mockMvc.perform(post(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductRequest))
                .andExpect(status().isOk())
                .andReturn();

        String productResponse = productAddResponse.getResponse().getContentAsString();

        ProductDto responseProduct = objectMapper.readValue(productResponse,
                ProductDto.class);

        assertEquals("Sample Product CAT2", responseProduct.getDescription());
        assertEquals("CAT2", responseProduct.getCategoryDescription());
        assertEquals(10, responseProduct.getQuantity());
        assertEquals(99.99, responseProduct.getPrice(), 0.01);

        String creationDate = responseProduct.getCreationDate().toLocalDate().toString();
        String updateDate = responseProduct.getUpdatedAt().toLocalDate().toString();

        String dateFormatRegex = "\\d{4}-\\d{2}-\\d{2}";

        assertTrue(creationDate.matches(dateFormatRegex));
        assertTrue(updateDate.matches(dateFormatRegex));

    }

    @Test
    public void testUpdateProductButProductIsNull() throws Exception {

        String jsonProductRequest = "";

        MvcResult productUpdateResponse = this.mockMvc.perform(put(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductRequest))
                .andExpect(status().is5xxServerError())
                .andReturn();


        ApiError errorResponse = objectMapper.readValue(productUpdateResponse.getResponse().getContentAsString(),
                ApiError.class);

        assertEquals(errorResponse.getStatus(), 404);

    }

    @Test
    public void testUpdateProductButProductQuantityIsNegative() throws Exception {

        String jsonProductRequest = "{"
                + "\"description\": \"Sample Product CAT2\","
                + "\"categoryDescription\": \"CAT2\","
                + "\"quantity\": -10,"
                + "\"price\": 99.99"
                + "}";

        MvcResult productUpdateResponse = this.mockMvc.perform(put(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductRequest))
                .andExpect(status().is4xxClientError())
                .andReturn();


        ApiError errorResponse = objectMapper.readValue(productUpdateResponse.getResponse().getContentAsString(),
                ApiError.class);

        assertEquals(errorResponse.getStatus(), 400);
        assertEquals(errorResponse.getMessage(), "Quantity could not be a negative value");

    }

    @Test
    public void testUpdateProductButProductPriceIsNegative() throws Exception {

        String jsonProductRequest = "{"
                + "\"description\": \"Sample Product CAT2\","
                + "\"categoryDescription\": \"CAT2\","
                + "\"quantity\": 10,"
                + "\"price\": -99.99"
                + "}";

        MvcResult productUpdateResponse = this.mockMvc.perform(put(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonProductRequest))
                .andExpect(status().is4xxClientError())
                .andReturn();


        ApiError errorResponse = objectMapper.readValue(productUpdateResponse.getResponse().getContentAsString(),
                ApiError.class);

        assertEquals(errorResponse.getStatus(), 400);
        assertEquals(errorResponse.getMessage(), "Price could not be a negative value");

    }

    @Test
    public void testAllProduct() throws Exception {

        MvcResult allProductResponse = this.mockMvc.perform(get(
                        "/tagetik/tagetik-webapps/1.0.0/products"))
                .andExpect(status().isOk())
                .andReturn();

        String products = allProductResponse.getResponse().getContentAsString();

        List<ProductDto> responseProducts = objectMapper.readValue(products,
                new TypeReference<List<ProductDto>>() {
                });
        assertFalse(responseProducts.isEmpty());
        assertEquals(responseProducts.size(), 4);


    }

    @Test
    public void testGetProductById() throws Exception {

        MvcResult allProductResponse = this.mockMvc.perform(get(
                        "/tagetik/tagetik-webapps/1.0.0/products"))
                .andExpect(status().isOk())
                .andReturn();

        String products = allProductResponse.getResponse().getContentAsString();

        List<ProductDto> responseProducts = objectMapper.readValue(products,
                new TypeReference<List<ProductDto>>() {
                });

        assertFalse(responseProducts.isEmpty());

        Integer productId = responseProducts.get(0).getProductId();

        String productByIdAsUrl = UriComponentsBuilder.fromUriString("/tagetik/tagetik-webapps/1.0.0/products/")
                .path("{productId}")
                .buildAndExpand(productId)
                .toString();

        MvcResult productFindById = this.mockMvc.perform(get(
                        productByIdAsUrl))
                .andExpect(status().isOk())
                .andReturn();

        assertNotNull(productFindById);

    }

    @Test
    public void testGetProductByIdButProductNotFound() throws Exception {

        Integer productId = 999999;

        String productByIdAsUrl = UriComponentsBuilder.fromUriString("/tagetik/tagetik-webapps/1.0.0/products/")
                .path("{productId}")
                .buildAndExpand(productId)
                .toString();

        MvcResult mvcProductNotFoundResponse = this.mockMvc.perform(get(
                        productByIdAsUrl))
                .andExpect(status().is4xxClientError())
                .andReturn();

        ApiError errorResponse = objectMapper.readValue(mvcProductNotFoundResponse.getResponse().getContentAsString(),
                ApiError.class);

        assertEquals(errorResponse.getStatus(), 404);
        assertEquals(errorResponse.getMessage(), "Product with id 999999 not found");

    }

    @Test
    public void testGetProductByCategory() throws Exception {

        String categoryValue = "CAT1";

        String getProductForParticularCategoryAsUrl = UriComponentsBuilder.fromUriString("/tagetik/tagetik-webapps/1.0.0/products/")
                .path("/categories/{categoryValue}")
                .buildAndExpand(categoryValue)
                .toString();

        MvcResult productsFindByCategory = this.mockMvc.perform(get(
                        getProductForParticularCategoryAsUrl))
                .andExpect(status().isOk())
                .andReturn();

        List<ProductDto> productsForCategory = objectMapper.readValue(productsFindByCategory.getResponse().getContentAsString(),
                new TypeReference<List<ProductDto>>() {
                });

        assertFalse(productsForCategory.isEmpty());
        assertEquals(productsForCategory.size(), 2);
        assertTrue(
                productsForCategory.stream().allMatch(
                        product -> product.getCategoryDescription().equals("CAT1")));


    }

    @Test
    public void testGetProductByCategoryButProductsNotBelongToThatCategory() throws Exception {

        String categoryValue = "CAT2";

        String getProductForParticularCategoryAsUrl = UriComponentsBuilder.fromUriString("/tagetik/tagetik-webapps/1.0.0/products/")
                .path("/categories/{categoryValue}")
                .buildAndExpand(categoryValue)
                .toString();

        MvcResult productsFindByCategory = this.mockMvc.perform(get(
                        getProductForParticularCategoryAsUrl))
                .andExpect(status().isOk())
                .andReturn();

        List<ProductDto> productsForCategory = objectMapper.readValue(productsFindByCategory.getResponse().getContentAsString(),
                new TypeReference<List<ProductDto>>() {
                });

        assertTrue(productsForCategory.isEmpty());


    }

    @Test
    public void testUpdateProductWithANewOne() throws Exception {


        MvcResult allProductResponse = this.mockMvc.perform(get(
                        "/tagetik/tagetik-webapps/1.0.0/products"))
                .andExpect(status().isOk())
                .andReturn();

        List<ProductDto> products = objectMapper.readValue(allProductResponse.getResponse().getContentAsString(),
                new TypeReference<List<ProductDto>>() {
                });

        ProductDto productToUpdate = products.iterator().next();

        ProductDto newProduct = ProductDto
                .builder()
                .productId(productToUpdate.getProductId())
                .quantity(999)
                .categoryDescription("CAT3")
                .description("New Description")
                .price(999.0)
                .build();

        String productAsJson = objectMapper.writeValueAsString(newProduct);

        MvcResult productUpdateResponse = this.mockMvc.perform(put(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productAsJson))
                .andExpect(status().isOk())
                .andReturn();

        ProductDto responseProduct = objectMapper.readValue(productUpdateResponse.getResponse().getContentAsString(),
                ProductDto.class);

        assertEquals(newProduct.getQuantity(), 999);
        assertEquals(newProduct.getCategoryDescription(), "CAT3");
        assertEquals(newProduct.getDescription(), "New Description");
        assertEquals(newProduct.getPrice(), 999.0);


    }

    @Test
    public void testUpdateProductWithIdThatDoesNotExist() throws Exception {

        int productId = 999999;

        ProductDto newProduct = ProductDto
                .builder()
                .productId(productId)
                .quantity(999)
                .categoryDescription("CAT3")
                .description("New Description")
                .price(999.0)
                .build();

        String productAsJson = objectMapper.writeValueAsString(newProduct);

        MvcResult productUpdateResponse = this.mockMvc.perform(put(
                        "/tagetik/tagetik-webapps/1.0.0/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productAsJson))
                .andExpect(status().is4xxClientError())
                .andReturn();


        ApiError errorResponse = objectMapper.readValue(productUpdateResponse.getResponse().getContentAsString(),
                ApiError.class);

        assertEquals(errorResponse.getStatus(), 404);
        assertEquals(errorResponse.getMessage(), "Product with id 999999 not found");


    }

    @Test
    public void testGroupProductByAllCategory() throws Exception {

        MvcResult allProductResponse = this.mockMvc.perform(get(
                        "/tagetik/tagetik-webapps/1.0.0/products/categories"))
                .andExpect(status().isOk())
                .andReturn();

        String products = allProductResponse.getResponse().getContentAsString();

        List<ProductByCategoryDto> productsAggregateByCategory = objectMapper.readValue(products,
                new TypeReference<List<ProductByCategoryDto>>() {
                });

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
