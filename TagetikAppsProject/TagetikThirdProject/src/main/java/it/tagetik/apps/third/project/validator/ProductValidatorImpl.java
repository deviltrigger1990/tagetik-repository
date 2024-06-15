package it.tagetik.apps.third.project.validator;

import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.third.project.exception.ProductValidationException;
import org.springframework.stereotype.Component;

@Component
 class ProductValidatorImpl implements ProductValidator{
    @Override
    public void validate(ProductDto product) throws ProductValidationException {

        int quantity = product.getQuantity();
        double price = product.getPrice();

        if(quantity < 0){
            throw new ProductValidationException("Quantity could not be a negative value");
        }

        if(price < 0){
            throw new ProductValidationException("Price could not be a negative value");
        }




    }
}
