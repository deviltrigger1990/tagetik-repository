package it.tagetik.apps.third.project.tree;

import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.third.project.tree.data.TreeNode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class CategoryAllDescendantsRetrieverImpl implements CategoryAllDescendantsRetriever {

    @Getter
    @AllArgsConstructor
    static class CategoryWithOriginalMatching {

        Category categoryMatching;
        Category childCategory;

    }

    @Override
    public List<Category> findAllDescendantsBy(Category category, TreeNode root) {

        List<Category> categories = new ArrayList<>();
        visitCategories(root, category, categories);
        return categories;
    }

    private void visitCategories(TreeNode currentNode, Category category, List<Category> categories) {

        boolean currentNodeIsALeaf = currentNode.getChildren() == null
                || currentNode.getChildren().isEmpty();

        boolean rootCategoryAlreadyFound = !categories.isEmpty();
        boolean categoryIsMatching = currentNode.getCategory().equals(category);

        if (currentNodeIsALeaf && !categoryIsMatching && !rootCategoryAlreadyFound) {
            return;
        }

        if (currentNodeIsALeaf && categoryIsMatching) {
            categories.add(currentNode.getCategory());
            return;
        }

        if (rootCategoryAlreadyFound || categoryIsMatching) {
            categories.add(currentNode.getCategory());
        }


        if (!currentNodeIsALeaf && !rootCategoryAlreadyFound) {
            for (TreeNode categoryChildNode : currentNode.getChildren()) {
                visitCategories(categoryChildNode, category, categories);
            }
        }

    }
}
