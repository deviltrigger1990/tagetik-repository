package it.tagetik.apps.second.project;


import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.model.entity.Product;

import java.util.List;

/**
 * Interface that compute the sum of total amount for all products belonging to the input category.That was the second
 * excercise provide by Tagetik
 */

public interface Excercise2Runner {

    /**
     * @param products the list of products *
     * @param category the category on which to filter
     * @return The sum of total amount for all products belonging to the input category
     */
    public double amount(List<Product> products, Category category);
}
