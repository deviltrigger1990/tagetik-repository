package it.tagetik.apps.model.decorator;

import it.tagetik.apps.model.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductDecorator {

    private final Product product;

    public double compute() {

        return product.getQuantity() * product.getPrice();
    }
}
