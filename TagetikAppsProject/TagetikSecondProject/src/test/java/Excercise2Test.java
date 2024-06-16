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
    private CategoryTotalAmountCalculator categoryTotalAmountCalculator;

    @Test
    public void computePriceForOneQuantity() {

        Product product = new Product();
        product.setProductId(1);
        product.setDescription("Product 1 Tagetik");
        product.setPrice(2.0);
        product.setQuantity(1);
        product.setCategory(Category.CAT1);

        assertNotNull(productDecoratorFactory);

        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(),2.0);

    }

    @Test
    public void computePriceForMultipleQuantity() {

        Product product = new Product();
        product.setProductId(1);
        product.setDescription("Product 1 Tagetik");
        product.setPrice(2.0);
        product.setQuantity(3);
        product.setCategory(Category.CAT1);

        assertNotNull(productDecoratorFactory);

        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(),6.0);

    }

    @Test
    public void computePriceForOneProductMatchCategory() {

        Product product1 = new Product();
        product1.setProductId(1);
        product1.setDescription("Product 1 Tagetik");
        product1.setPrice(2.0);
        product1.setQuantity(3);
        product1.setCategory(Category.CAT1);

        Product product2 = new Product();
        product2.setProductId(1);
        product2.setDescription("Product 1 Tagetik");
        product2.setPrice(2.0);
        product2.setQuantity(3);
        product2.setCategory(Category.CAT2);

        assertEquals(categoryTotalAmountCalculator.amount(Arrays.asList(product1,product2),Category.CAT1),6.0);
        assertNotEquals(categoryTotalAmountCalculator.amount(Arrays.asList(product1,product2),Category.CAT1),3.0);

    }

    @Test
    public void computePriceForOneProductMatchMultipleCategory() {

        Product product1 = new Product();
        product1.setProductId(1);
        product1.setDescription("Product 1 Tagetik");
        product1.setPrice(2.0);
        product1.setQuantity(3);
        product1.setCategory(Category.CAT1);

        Product product2 = new Product();
        product2.setProductId(2);
        product2.setDescription("Product 2 Tagetik");
        product2.setPrice(2.0);
        product2.setQuantity(3);
        product2.setCategory(Category.CAT2);

        Product product3 = new Product();
        product3.setProductId(2);
        product3.setDescription("Product 2 Tagetik");
        product3.setPrice(2.0);
        product3.setQuantity(3);
        product3.setCategory(Category.CAT1);

        assertEquals(categoryTotalAmountCalculator.amount(Arrays.asList(product1,product2,product3),Category.CAT1),12.0);

    }

    @Test
    public void computePriceNoMatching() {

        Product product1 = new Product();
        product1.setProductId(1);
        product1.setDescription("Product 1 Tagetik");
        product1.setPrice(2.0);
        product1.setQuantity(3);
        product1.setCategory(Category.CAT1);

        Product product2 = new Product();
        product2.setProductId(2);
        product2.setDescription("Product 2 Tagetik");
        product2.setPrice(2.0);
        product2.setQuantity(3);
        product2.setCategory(Category.CAT2);

        Product product3 = new Product();
        product3.setProductId(2);
        product3.setDescription("Product 2 Tagetik");
        product3.setPrice(2.0);
        product3.setQuantity(3);
        product3.setCategory(Category.CAT2);



        assertEquals(categoryTotalAmountCalculator.amount(Arrays.asList(product1,product2,product3),Category.CAT3),0);

    }




}
