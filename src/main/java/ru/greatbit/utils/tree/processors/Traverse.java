package ru.greatbit.utils.tree.processors;

import ru.greatbit.utils.tree.nodes.Node;
import ru.greatbit.utils.tree.processors.api.Visitor;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by azee on 5/8/14.
 */
public class Traverse {

    /**
     * Collect nodes in order using Breadth-first traversal
     * @param head - A head node of a tree
     * @return - returns a list of visited nodes
     * @param <K> - Tree node key
     * @param <V> - Tree node value
     */
    public static <K, V>List<Node<K, V>> bfsList(Node<K, V> head){
        final List<Node<K, V>> result = new LinkedList<Node<K, V>>();
        bfsVisit(head, new Visitor() {
            @Override
            public void visit(Node node) {
                result.add(node);
            }
        });
        return result;
    }

    /**
     * Process node using visitors pattern
     * @param head - A head node of a tree
     * @param visitor - Visitor object - a processor
     * @param <K> - Tree node key
     * @param <V> - Tree node value
     */
    public static <K, V> void bfsVisit(Node<K, V> head, Visitor visitor){
        if (head == null){
            return;
        }

        Queue<Node> queue = new LinkedList<Node>();
        queue.add(head);

        while (!queue.isEmpty()){
            if (!queue.isEmpty()) {
                head = queue.poll();
                visitor.visit(head);
            }

            for (Node child : head.getChildren()){
                queue.add(child);
            }
        }
    }

    /**
     * Collect nodes in order using Depth-first traversal
     * @param head - A head node of a tree
     * @return - returns a list of visited nodes
     * @param <K> - Tree node key
     * @param <V> - Tree node value
     */
    public static <K, V>List<Node<K, V>> dfsList(Node<K, V> head){
        List<Node<K, V>> result = new LinkedList<Node<K, V>>();
        dfsList(head, result);
        return result;
    }

    /**
     * Recursive method to collect objects in DFS traversal
     * @param head - A head node of a tree
     * @param result - returns a list of visited nodes
     * @param <K> - Tree node key
     * @param <V> - Tree node value
     */
    private static <K, V> void dfsList(Node<K, V> head, final List<Node<K, V>> result){
        dfsVisit(head, new Visitor() {
            @Override
            public void visit(Node node) {
                result.add(node);
            }
        });
    }

    /**
     * Recursive method to process objects in DFS traversal using visitors pattern
     * @param head - A head node of a tree
     * @param visitor - Visitor object - a processor
     * @param <K> - Tree node key
     * @param <V> - Tree node value
     */
    public static <K, V> void dfsVisit(Node<K, V> head, Visitor visitor){
        if (head == null){
            return;
        }
        visitor.visit(head);
        for (Node children : head.getChildren()){
            dfsVisit(children, visitor);
        }
    }

    /**
     * Find all leafs
     * @param head - A head node of a tree
     * @param <K> - Tree node key
     * @param <V> - Tree node value
     * @return - List of Tree Nodes - all leafs
     */
    public static <K, V> List<Node<K, V>> getLeafs(Node<K, V> head){
        List<Node<K, V>> result = new LinkedList<Node<K, V>>();
        getLeafs(head, result);
        return result;
    }


    /**
     * Recursive method to collect all leafs in DFS traversal
     * @param head - A head node of a tree
     * @param result - List of Tree Nodes - all leafs
     * @param <K> - Tree node key
     * @param <V> - Tree node value
     */
    public static <K, V> void getLeafs(Node<K, V> head, List<Node<K, V>> result){
        if (head == null){
            return;
        }
        if (head.getChildren().size() == 0){
            result.add(head);
        } else {
            for (Node node : head.getChildren()){
                getLeafs(node, result);
            }
        }
    }

    /**
     * Recursive method to count all leafs in DFS traversal
     * @param head - A head node of a tree
     * @param <K> - Tree node key
     * @param <V> - Tree node value
     * @return - ling value showing number of leafs
     */
    public static <K, V> long countLeafs(Node<K, V> head){
        long count = 0;
        if (head == null){
            return count;
        }
        if (head.getChildren().size() == 0){
            return 1;
        } else {
            for (Node node : head.getChildren()){
                count += countLeafs(node);
            }
            return count;
        }
    }

    /**
     * Recursive method to verify max height of the tree
     * @param head - A head node of a tree
     * @param <K> - Tree node key
     * @param <V> - Tree node value
     * @return  int - max tree height
     */
    public static <K, V> int countMaxHeight(Node<K, V> head){
        if (head == null){
            return 0;
        }

        List<Integer> heights = new LinkedList<Integer>();
        for (Node node : head.getChildren()){
            heights.add(countMaxHeight(node));
        }

        int max = 0;
        for (Integer childHeight : heights){
            max = Math.max(max, childHeight);
        }
        return 1 + max;
    }
}
