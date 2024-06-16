package it.tagetik.apps.third.project.tree.data;

import it.tagetik.apps.model.entity.Category;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A node of the tree.
 */

@RequiredArgsConstructor
public class TreeNode {
    @Getter @lombok.NonNull
    Category category;
    @Getter @Setter List<TreeNode> children = new ArrayList<>();


}