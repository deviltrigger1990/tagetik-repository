package it.tagetik.apps.third.project.validator;

import it.tagetik.apps.model.dto.ProductDto;
import it.tagetik.apps.third.project.exception.ProductValidationException;

/**
 * Interface that validate a request that contains a {@link ProductDto}
 */

@FunctionalInterface
public interface ProductValidator {

    /**
     * Validate a request that contains a {@link ProductDto}
     *
     * @param product that contains information about the request product
     * @throws ProductValidationException if the request that contains a {@link ProductDto}  violates some business rules
     */

    public void validate(ProductDto product) throws ProductValidationException;
}
