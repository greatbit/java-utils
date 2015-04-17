package ru.greatbit.utils.tree.processors;

import org.junit.Test;
import ru.greatbit.utils.tree.nodes.NaryNode;
import ru.greatbit.utils.tree.nodes.Node;
import ru.greatbit.utils.tree.utils.ListTestUtils;
import ru.greatbit.utils.tree.utils.TreeGenerator;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static ru.greatbit.utils.tree.processors.Traverse.countLeafs;
import static ru.greatbit.utils.tree.processors.Traverse.countMaxHeight;

/**
 * Created by azee on 5/8/14.
 */
public class TraverseTest {

    @Test
    public void bfsListTest(){
        List<Node<String, String>> bfsList = Traverse.bfsList(TreeGenerator.createNaryTree());
        assertNotNull(bfsList);
        assertThat("Wrong number of nodes in the tree", bfsList.size(), is(10));
        assertThat("Wrong sequence of nodes", ListTestUtils.getKeysList(bfsList).toString(),
                is("[head, 1, 2, 3, 1_1, 1_2, 2_1, 1_1_1, 1_1_2, 1_2_1]"));
    }

    @Test
    public void dfsListTest(){
        List<Node<String, String>> dfsList = Traverse.dfsList(TreeGenerator.createNaryTree());
        assertNotNull(dfsList);
        assertThat("Wrong number of nodes in the tree", dfsList.size(), is(10));
        assertThat("Wrong sequence of nodes", ListTestUtils.getKeysList(dfsList).toString(),
                is("[head, 1, 1_1, 1_1_1, 1_1_2, 1_2, 1_2_1, 2, 2_1, 3]"));
    }

    @Test
    public void leafsListTest(){
        List<Node<String, String>> leafs = Traverse.getLeafs(TreeGenerator.createNaryTree());
        assertNotNull(leafs);
        assertThat("Wrong number of nodes in the tree", leafs.size(), is(5));
        assertThat("Wrong sequence of nodes", ListTestUtils.getKeysList(leafs).toString(),
                is("[1_1_1, 1_1_2, 1_2_1, 2_1, 3]"));
    }

    @Test
    public void countLeafsTest(){
        assertThat("Wrong number of leafs in the tree", countLeafs(TreeGenerator.createNaryTree()), is(5L));
    }

    @Test
    public void countMaxHeightTest(){
        assertThat("Wrong max height of the tree", countMaxHeight(TreeGenerator.createNaryTree()), is(4));
        assertThat("Wrong max height of an empty tree", countMaxHeight(new NaryNode<String, String>("head", "head")), is(1));
        assertThat("Wrong max height of the a tree", countMaxHeight(null), is(0));
    }
}
