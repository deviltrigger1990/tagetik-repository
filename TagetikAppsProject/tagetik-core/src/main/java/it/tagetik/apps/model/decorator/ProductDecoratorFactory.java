package it.tagetik.apps.model.decorator;

import it.tagetik.apps.model.entity.Product;

/**
 * Interface that provide a {@link ProductDecorator}
 */
@FunctionalInterface
public interface ProductDecoratorFactory {

    public ProductDecorator decorate(final Product product);
}
