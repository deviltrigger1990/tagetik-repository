package it.tagetik.apps.first.project.tree;

import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.third.project.tree.data.TreeNode;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
class CategoryAllDescendantsRetrieverImpl implements CategoryAllDescendantsRetriever {


    @Override
    public List<Category> findAllDescendantsBy(Category category, TreeNode root) {

        List<Category> categories = new ArrayList<>();
        visitTreeNode(root, category, categories);
        return categories;
    }

    private void visitTreeNode(TreeNode currentNode, Category category, List<Category> categories) {

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

        for (TreeNode categoryChildNode : currentNode.getChildren()) {
            visitTreeNode(categoryChildNode, category, categories);
        }
    }

}


