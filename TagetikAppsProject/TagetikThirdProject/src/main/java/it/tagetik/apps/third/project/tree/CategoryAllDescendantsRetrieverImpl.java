package it.tagetik.apps.third.project.tree;

import it.tagetik.apps.model.entity.Category;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
class CategoryAllDescendantsRetrieverImpl implements CategoryAllDescendantsRetriever {

    @Override
    public List<Category> findAllDescendantsBy(Category category, TreeNode root) {
        return List.of();
    }
}
