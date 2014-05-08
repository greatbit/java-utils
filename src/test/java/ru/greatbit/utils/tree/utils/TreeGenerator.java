package ru.greatbit.utils.tree.utils;

import ru.greatbit.utils.tree.nodes.NaryNode;

/**
 * Created by azee on 5/8/14.
 */
public class TreeGenerator {

    public static NaryNode<String, String> createNaryTree(){
        NaryNode<String, String> head = new NaryNode<String, String>("head", "head");

        NaryNode<String, String> child1 = new NaryNode<String, String>("1", "1");
        NaryNode<String, String> child2 = new NaryNode<String, String>("2", "2");
        NaryNode<String, String> child3 = new NaryNode<String, String>("3", "3");

        head.getChildren().add(child1);
        head.getChildren().add(child2);
        head.getChildren().add(child3);

        NaryNode<String, String> child1_1 = new NaryNode<String, String>("1_1", "1_1");
        NaryNode<String, String> child1_2 = new NaryNode<String, String>("1_2", "1_2");
        child1.getChildren().add(child1_1);
        child1.getChildren().add(child1_2);

        NaryNode<String, String> child1_1_1 = new NaryNode<String, String>("1_1_1", "1_1_1");
        NaryNode<String, String> child1_1_2 = new NaryNode<String, String>("1_1_2", "1_1_2");
        child1_1.getChildren().add(child1_1_1);
        child1_1.getChildren().add(child1_1_2);

        NaryNode<String, String> child1_2_1 = new NaryNode<String, String>("1_2_1", "1_2_1");
        child1_2.getChildren().add(child1_2_1);

        NaryNode<String, String> child2_1 = new NaryNode<String, String>("2_1", "2_1");
        child2.getChildren().add(child2_1);

        return head;
    }
}
