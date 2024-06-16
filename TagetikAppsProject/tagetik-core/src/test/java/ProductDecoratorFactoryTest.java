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

        Product product = new Product();
        product.setProductId(2);
        product.setDescription("Product 1 Tagetik");
        product.setPrice(2.0);
        product.setQuantity(0);
        product.setCategory(Category.CAT1);

        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(), 0);

    }

    @Test
    public void computePriceForZeroPrice() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();

        Product product = new Product();
        product.setProductId(2);
        product.setDescription("Product 1 Tagetik");
        product.setPrice(0);
        product.setQuantity(2);
        product.setCategory(Category.CAT1);

        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(), 0.0);

    }

    @Test
    public void computePriceForZeroPriceAndZeroQuantity() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();

        Product product = new Product();
        product.setProductId(2);
        product.setDescription("Product 1 Tagetik");
        product.setPrice(0);
        product.setQuantity(0);
        product.setCategory(Category.CAT1);


        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(), 0.0);

    }

    @Test
    public void computePriceForOneQuantity() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();

        Product product = new Product();
        product.setProductId(2);
        product.setDescription("Product 1 Tagetik");
        product.setPrice(2.0);
        product.setQuantity(1);
        product.setCategory(Category.CAT1);


        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(), 2.0);

    }

    @Test
    public void computePriceForMultipleQuantity() {

        ProductDecoratorFactory productDecoratorFactory = new ProductDecoratorFactoryImpl();
        Product product = new Product();
        product.setProductId(2);
        product.setDescription("Product 1 Tagetik");
        product.setPrice(2.0);
        product.setQuantity(3);
        product.setCategory(Category.CAT1);

        assertNotNull(productDecoratorFactory);

        ProductDecorator pd = productDecoratorFactory.decorate(product);
        assertEquals(pd.compute(), 6.0);

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
