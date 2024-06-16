package it.tagetik.apps.model.decorator;

import it.tagetik.apps.model.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductDecoratorFactoryImpl implements ProductDecoratorFactory {

    @Override
    public ProductDecorator decorate(Product product) {

        if (product == null) {
            throw new IllegalArgumentException("Product must not be null");
        }
        return new ProductDecorator(product);
    }
}
