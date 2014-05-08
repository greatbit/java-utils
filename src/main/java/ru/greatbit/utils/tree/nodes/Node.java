package ru.greatbit.utils.tree.nodes;


import java.util.List;

/**
 * Created by azee on 5/8/14.
 * Base class for all nodes
 */
public abstract class Node<K, V>{

    private K key;
    private V value;

    public abstract List<Node> getChildren();

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public Node() {}

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }
}
