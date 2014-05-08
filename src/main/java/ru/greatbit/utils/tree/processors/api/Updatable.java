package ru.greatbit.utils.tree.processors.api;

import ru.greatbit.utils.tree.nodes.Node;

/**
 * Created by azee on 5/8/14.
 */
public interface Updatable {
    public <K, V> void addNode(Node<K, V> top, Node<K, V> toAdd);
    public <K, V> void removeNode(Node<K, V> top, Node<K, V> toRemove);
    public <K, V> void removeNode(Node<K, V> top, K key);
    public <K, V> void removeNodeByVal(Node<K, V> top, V value);
    public <K, V> Node merge(Node<K, V> destination, Node<K, V> source);

}
