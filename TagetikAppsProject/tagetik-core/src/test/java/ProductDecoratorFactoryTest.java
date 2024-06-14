import it.tagetik.apps.model.decorator.ProductDecorator;
import it.tagetik.apps.model.decorator.ProductDecoratorFactory;
import it.tagetik.apps.model.decorator.ProductDecoratorFactoryImpl;
import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.model.entity.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ProductDecoratorFactoryTest {

    @Test
    public void computePriceForZeroQuantity() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();

        Product product = Product.builder()
                .productId(1)
                .description("Product 1 Tagetik")
                .price(2.0)
                .quantity(0)
                .category(Category.CAT1)
                .build();


        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(),0);

    }

    @Test
    public void computePriceForZeroPrice() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();

        Product product = Product.builder()
                .productId(1)
                .description("Product 1 Tagetik")
                .price(2.0)
                .quantity(0)
                .category(Category.CAT1)
                .build();


        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(),0.0);

    }

    @Test
    public void computePriceForZeroPriceAndZeroQuantity() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();

        Product product = Product.builder()
                .productId(1)
                .description("Product 1 Tagetik")
                .price(0)
                .quantity(0)
                .category(Category.CAT1)
                .build();


        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(),0.0);

    }

    @Test
    public void computePriceForOneQuantity() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();

        Product product = Product.builder()
                .productId(1)
                .description("Product 1 Tagetik")
                .price(2.0)
                .quantity(1)
                .category(Category.CAT1)
                .build();


        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(),2.0);

    }

    @Test
    public void computePriceForMultipleQuantity() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();


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
    public void computePriceForNullProduct() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();

        Product p1 = null;

        assertNotNull(productDecoratorFactory);


        Throwable exception = assertThrows(IllegalArgumentException.class, () -> productDecoratorFactory.decorate(p1));
        assertEquals("Product must not be null", exception.getMessage());


    }

}
