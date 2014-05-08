package ru.greatbit.utils.tree.processors.api;

import ru.greatbit.utils.tree.nodes.Node;

/**
 * Created by azee on 5/8/14.
 */
public interface Searchable {
    public <K, V> Node<K, V> find(K key);
    public <K, V> Node<K, V> findByVal(V value);
}
