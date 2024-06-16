package it.tagetik.apps.second.project;


import it.tagetik.apps.model.decorator.ProductDecorator;
import it.tagetik.apps.model.decorator.ProductDecoratorFactory;
import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.model.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
 class CategoryTotalAmountCalculatorImpl implements CategoryTotalAmountCalculator {

    @Autowired
    private ProductDecoratorFactory productDecoratorFactory;

    public double amount(List<Product> products, Category category) {
        return products
                .stream()
                .filter(product -> product.getCategory().equals(category))
                .map(productDecoratorFactory::decorate)
                .map(ProductDecorator::compute)
                .reduce(0.0, Double::sum);


    }


}