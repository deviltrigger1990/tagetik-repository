import it.tagetik.apps.model.decorator.ProductDecorator;
import it.tagetik.apps.model.decorator.ProductDecoratorFactory;
import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.model.entity.Product;
import it.tagetik.apps.second.project.Application;
import it.tagetik.apps.second.project.CategoryTotalAmountCalculator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = { "it.tageik.apps.model" })
public class Excercise2Test {

    @Autowired
    private ProductDecoratorFactory productDecoratorFactory;

    @Autowired
    private CategoryTotalAmountCalculator excercise2Runner;

    @Test
    public void computePriceForOneQuantity() {

        Product product = Product.builder()
                        .productId(1)
                        .description("Product 1 Tagetik")
                        .price(2.0)
                        .quantity(1)
                        .category(Category.CAT1)
                        .build();

        assertNotNull(productDecoratorFactory);

        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(),2.0);

    }

    @Test
    public void computePriceForMultipleQuantity() {

        Product product = Product.builder()
                .productId(1)
                .description("Product 1 Tagetik")
                .price(2.0)
                .quantity(3)
                .category(Category.CAT1)
                .build();

        assertNotNull(productDecoratorFactory);

        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(),6.0);

    }

    @Test
    public void computePriceForOneProductMatchCategory() {

        Product product1 = Product.builder()
                .productId(1)
                .description("Product 1 Tagetik")
                .price(2.0)
                .quantity(3)
                .category(Category.CAT1)
                .build();

        Product product2 = Product.builder()
                .productId(1)
                .description("Product 2 Tagetik")
                .price(2.0)
                .quantity(3)
                .category(Category.CAT2)
                .build();

        assertEquals(excercise2Runner.amount(Arrays.asList(product1,product2),Category.CAT1),6.0);
        assertNotEquals(excercise2Runner.amount(Arrays.asList(product1,product2),Category.CAT1),3.0);

    }

    @Test
    public void computePriceForOneProductMatchMultipleCategory() {

        Product product1 = Product.builder()
                .productId(1)
                .description("Product 1 Tagetik")
                .price(2.0)
                .quantity(3)
                .category(Category.CAT1)
                .build();

        Product product2 = Product.builder()
                .productId(2)
                .description("Product 2 Tagetik")
                .price(2.0)
                .quantity(3)
                .category(Category.CAT2)
                .build();

        Product product3 = Product.builder()
                .productId(3)
                .description("Product 3 Tagetik")
                .price(2.0)
                .quantity(3)
                .category(Category.CAT1)
                .build();

        assertEquals(excercise2Runner.amount(Arrays.asList(product1,product2,product3),Category.CAT1),12.0);

    }

    @Test
    public void computePriceNoMatching() {

        Product product1 = Product.builder()
                .productId(1)
                .description("Product 1 Tagetik")
                .price(2.0)
                .quantity(3)
                .category(Category.CAT1)
                .build();

        Product product2 = Product.builder()
                .productId(2)
                .description("Product 2 Tagetik")
                .price(2.0)
                .quantity(3)
                .category(Category.CAT2)
                .build();

        Product product3 = Product.builder()
                .productId(3)
                .description("Product 3 Tagetik")
                .price(2.0)
                .quantity(3)
                .category(Category.CAT1)
                .build();

        assertEquals(excercise2Runner.amount(Arrays.asList(product1,product2,product3),Category.CAT3),0);

    }




}
