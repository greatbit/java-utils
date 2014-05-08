package ru.greatbit.utils.tree.processors;

import ru.greatbit.utils.tree.nodes.Node;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by azee on 5/8/14.
 */
public class Traverse {

    /**
     * Collect nodes in order using Breadth-first traversal
     * @param head
     * @return
     */
    public static <K, V>List<Node> bfsList(Node<K, V> head){
        List<Node> result = new LinkedList<Node>();
        if (head == null){
            return result;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(head);

        while (!queue.isEmpty()){
            if (!queue.isEmpty()) {
                head = queue.poll();
                result.add(head);
            }

            for (Node child : head.getChildren()){
                queue.add(child);
            }
        }

        return result;
    }

    /**
     * Collect nodes in order using Depth-first traversal
     * @param head
     * @return
     */
    public static <K, V>List<Node> dfsList(Node<K, V> head){
        List<Node> result = new LinkedList<Node>();
        dfsList(head, result);
        return result;
    }

    /**
     * Recursive method to collect objects in DFS traversal
     * @param head
     * @param result
     */
    private static <K, V> void dfsList(Node<K, V> head, List<Node> result){
        if (head == null){
            return;
        }
        result.add(head);
        for (Node children : head.getChildren()){
            dfsList(children, result);
        }
    }

    /**
     * Find all leafs
     * @param head
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> List<Node> getlLeafs(Node<K, V> head){
        List<Node> result = new LinkedList<Node>();
        getlLeafs(head, result);
        return result;
    }


    /**
     * Recursive method to collect all leafs in DFS traversal
     * @param head
     * @param result
     * @param <K>
     * @param <V>
     */
    private static <K, V> void getlLeafs(Node<K, V> head, List<Node> result){
        if (head == null){
            return;
        }
        if (head.getChildren().size() == 0){
            result.add(head);
        } else {
            for (Node node : head.getChildren()){
                getlLeafs(node, result);
            }
        }
    }
}
