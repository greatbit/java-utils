package ru.greatbit.utils.tree.nodes;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 5/8/14.
 * Used for simply non-oriented N-ary trees
 */
public class NaryNode<K, V> extends Node<K, V>{

    private List<Node> children;

    public NaryNode(K key, V value, List<Node> children) {
        super(key, value);
        this.children = children;
    }

    public NaryNode(K key, V value) {
        super(key, value);
    }

    public NaryNode(List<Node> children) {
        this.children = children;
    }

    public NaryNode() {}

    @Override
    public List<Node> getChildren() {
        if (children == null){
            children = new LinkedList<Node>();
        }
        return children;
    }
}
