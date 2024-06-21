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
        //visitTreeNode(root, category, categories);
        TreeNode subTree = visitTreeNodeV2(root, category);
        findCategoryInSubTree(subTree,categories);
        //return subTree;
        return categories;

    }

    private void findCategoryInSubTree(TreeNode node,List<Category> categories) {

        if (node == null) {
            return;
        }

        categories.add(node.getCategory());

        for (TreeNode child : node.getChildren()) {
            findCategoryInSubTree(child,categories);
        }


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

    private TreeNode visitTreeNodeV2(TreeNode currentNode, Category category) {


        if (currentNode == null) {
            return null;
        }

        boolean categoryIsMatching = currentNode.getCategory().equals(category);

        if (categoryIsMatching) {
            return currentNode;
        }

        for (TreeNode child : currentNode.getChildren()) {
            TreeNode result = visitTreeNodeV2(child, category);
            if (result != null) {
                return result;
            }
        }

        return null;

    }

}


