package it.tagetik.apps.third.project.tree.data;

import it.tagetik.apps.model.entity.Category;

import java.util.List;

/**
 * A node of the tree.
 */

public class
TreeNode {
    Category category;
    List<TreeNode> children;
}