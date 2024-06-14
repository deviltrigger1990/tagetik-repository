package it.tagetik.apps.model.decorator;

/**
 * Interface that compute the total amount of a product as quantity * price
 */

@FunctionalInterface
public interface ProductPriceQuantityCalculator {

    /**
     * Compute the total amount of a product as quantity * price
     * @param quantity of the product
     * @param price of the single product
     * @return total amount of a product
     */

    public double compute(Integer quantity, Double price);

}
