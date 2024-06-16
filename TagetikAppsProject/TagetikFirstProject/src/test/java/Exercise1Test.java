import it.tagetik.apps.first.project.Application;
import it.tagetik.apps.first.project.tree.CategoryAllDescendantsRetriever;
import it.tagetik.apps.model.entity.Category;
import it.tagetik.apps.third.project.tree.data.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ComponentScan(basePackages = { "it.tageik.apps.model" })
public class Exercise1Test {

    @Autowired
    private CategoryAllDescendantsRetriever categoryAllDescendantsRetriever;

    @Test
    public void testTreeWithOnlyRootThatMatchingCategory(){

        TreeNode categoriesTree = new TreeNode(Category.CAT1);

        List<Category> allDescendantsBy = categoryAllDescendantsRetriever
                .findAllDescendantsBy(Category.CAT1, categoriesTree);
        assertFalse(allDescendantsBy.isEmpty());
        assertSame(allDescendantsBy.iterator().next(),Category.CAT1 );

    }

    @Test
    public void testTreeWithOnlyRootThatNotMatchingCategory(){

        TreeNode categoriesTree = new TreeNode(Category.CAT1);

        List<Category> allDescendantsBy = categoryAllDescendantsRetriever
                .findAllDescendantsBy(Category.CAT2, categoriesTree);
        assertTrue(allDescendantsBy.isEmpty());
    }

    @Test
    public void testTreeWithFirstLevelThatMatchingCategoryInTheFirstLevel(){

        TreeNode categoriesTree = new TreeNode(Category.CAT1);
        categoriesTree.setChildren(Arrays.asList(new TreeNode(Category.CAT2),
                new TreeNode(Category.CAT3)));

        List<Category> allDescendantsBy = categoryAllDescendantsRetriever
                .findAllDescendantsBy(Category.CAT2, categoriesTree);

        assertFalse(allDescendantsBy.isEmpty());
        assertSame(allDescendantsBy.iterator().next(),Category.CAT2 );
        assertEquals(allDescendantsBy.size(),1);
    }

    @Test
    public void testTreeWithFirstLevelThatMatchingCategoryInTheRootLevelAndReturnWholeTree(){

        TreeNode categoriesTree = new TreeNode(Category.CAT1);
        categoriesTree.setChildren(Arrays.asList(new TreeNode(Category.CAT2),
                new TreeNode(Category.CAT3)));

        List<Category> allDescendantsBy = categoryAllDescendantsRetriever
                .findAllDescendantsBy(Category.CAT1, categoriesTree);

        assertFalse(allDescendantsBy.isEmpty());
        assertSame(allDescendantsBy.iterator().next(),Category.CAT1 );
        assertEquals(allDescendantsBy.size(),3);

        assertTrue(allDescendantsBy.containsAll(List.of(Category.CAT1,Category.CAT2,Category.CAT3)));

    }

    @Test
    public void testTreeWithNodeWithOnlyChildThatMatchingCategoryInTheRootLevelAndReturnWholeTree(){

        TreeNode categoriesTree = new TreeNode(Category.CAT1);
        TreeNode firstChild = new TreeNode(Category.CAT2);
        categoriesTree.setChildren(List.of(firstChild));
        firstChild.setChildren(List.of(new TreeNode(Category.CAT3)));

        List<Category> allDescendantsBy = categoryAllDescendantsRetriever
                .findAllDescendantsBy(Category.CAT1, categoriesTree);

        assertFalse(allDescendantsBy.isEmpty());
        assertSame(allDescendantsBy.iterator().next(),Category.CAT1 );
        assertEquals(allDescendantsBy.size(),3);

        assertTrue(allDescendantsBy.containsAll(List.of(Category.CAT1,Category.CAT2,Category.CAT3)));

    }

    @Test
    public void testTreeWithOneLevelButCategoryNotMatching(){

        TreeNode categoriesTree = new TreeNode(Category.CAT1);
        TreeNode firstChild = new TreeNode(Category.CAT2);
        categoriesTree.setChildren(List.of(firstChild));

        List<Category> allDescendantsBy = categoryAllDescendantsRetriever
                .findAllDescendantsBy(Category.CAT3, categoriesTree);

        assertTrue(allDescendantsBy.isEmpty());

    }

    @Test
    public void testTreeWithTwoLevelButMatchingCategoryIsOnFirstLevelAndReturnSubTree(){

        TreeNode categoriesTree = new TreeNode(Category.CAT1);
        TreeNode firstChild = new TreeNode(Category.CAT2);
        categoriesTree.setChildren(List.of(firstChild));
        firstChild.setChildren(List.of(new TreeNode(Category.CAT3)));

        List<Category> allDescendantsBy = categoryAllDescendantsRetriever
                .findAllDescendantsBy(Category.CAT2, categoriesTree);

        assertFalse(allDescendantsBy.isEmpty());
        assertSame(allDescendantsBy.iterator().next(),Category.CAT2 );
        assertEquals(allDescendantsBy.size(),2);

        assertTrue(allDescendantsBy.containsAll(List.of(Category.CAT2,Category.CAT3)));



    }

    @Test
    public void testTreeOfOneLevelButMatchingCategoryIsOnLeafNode(){

        TreeNode categoriesTree = new TreeNode(Category.CAT1);
        TreeNode firstChild = new TreeNode(Category.CAT2);
        categoriesTree.setChildren(List.of(firstChild));
        firstChild.setChildren(List.of(new TreeNode(Category.CAT3)));

        List<Category> allDescendantsBy = categoryAllDescendantsRetriever
                .findAllDescendantsBy(Category.CAT3, categoriesTree);

        assertFalse(allDescendantsBy.isEmpty());
        assertSame(allDescendantsBy.iterator().next(),Category.CAT3 );
        assertEquals(allDescendantsBy.size(),1);



    }


}
