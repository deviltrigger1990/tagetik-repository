package it.tagetik.apps.first.project.tree;

import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.third.project.tree.data.TreeNode;

import java.util.List;

/**
 * Interface that Given a Category
 * as input, returns the List of all descendants Categories
 * (including Category passed as input).
 */

public interface CategoryAllDescendantsRetriever {


    /**
     * @param category the category of which find all descendants
     * @param root     the root node of the tree
     * @return the list of all descendants categories, including the category passed as input
     */
    public List<Category> findAllDescendantsBy(Category category, TreeNode root);


}
