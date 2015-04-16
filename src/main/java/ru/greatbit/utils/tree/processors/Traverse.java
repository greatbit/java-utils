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
     * @param head
     * @return
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
     * @param head
     * @return
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
     * @param head
     * @return
     */
    public static <K, V>List<Node<K, V>> dfsList(Node<K, V> head){
        List<Node<K, V>> result = new LinkedList<Node<K, V>>();
        dfsList(head, result);
        return result;
    }

    /**
     * Recursive method to collect objects in DFS traversal
     * @param head
     * @param result
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
     * @param head
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
     * @param head
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V> List<Node<K, V>> getLeafs(Node<K, V> head){
        List<Node<K, V>> result = new LinkedList<Node<K, V>>();
        getLeafs(head, result);
        return result;
    }


    /**
     * Recursive method to collect all leafs in DFS traversal
     * @param head
     * @param result
     * @param <K>
     * @param <V>
     */
    private static <K, V> void getLeafs(Node<K, V> head, List<Node<K, V>> result){
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
}
