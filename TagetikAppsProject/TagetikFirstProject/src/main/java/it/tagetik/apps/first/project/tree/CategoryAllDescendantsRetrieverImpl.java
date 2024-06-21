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
        TreeNode subTree = visitTreeNode(root, category);
        findCategoryInSubTree(subTree, categories);
        return categories;

    }

    private void findCategoryInSubTree(TreeNode node, List<Category> categories) {

        if (node == null) {
            return;
        }

        categories.add(node.getCategory());

        for (TreeNode child : node.getChildren()) {
            findCategoryInSubTree(child, categories);
        }


    }

    private TreeNode visitTreeNode(TreeNode currentNode, Category category) {


        if (currentNode == null) {
            return null;
        }

        boolean categoryIsMatching = currentNode.getCategory().equals(category);

        if (categoryIsMatching) {
            return currentNode;
        }

        for (TreeNode child : currentNode.getChildren()) {
            TreeNode result = visitTreeNode(child, category);
            if (result != null) {
                return result;
            }
        }

        return null;

    }

}


