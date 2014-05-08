package ru.greatbit.utils.tree.utils;

import ru.greatbit.utils.tree.nodes.Node;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 5/8/14.
 */
public class ListTestUtils {

    public static <K, V>List<K> getKeysList(List<Node<K, V>> nodes){
        List<K> result = new LinkedList<K>();
        for (Node node : nodes){
            result.add((K)node.getKey());
        }
        return result;
    }

}
