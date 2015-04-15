package ru.greatbit.utils.tree.processors.api;

import ru.greatbit.utils.tree.nodes.Node;

/**
 * Created by azee on 15.04.15.
 */
public interface Visitor {
    public <K, V>void visit(Node<K, V> node);
}
