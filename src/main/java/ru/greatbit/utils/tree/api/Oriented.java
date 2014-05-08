package ru.greatbit.utils.tree.api;

import ru.greatbit.utils.tree.nodes.Node;

import java.util.List;

/**
 * Created by azee on 5/8/14.
 * Used for oriented N-ary trees
 */
public interface Oriented {

    public List<Node> getLeftChildren();

    public List<Node> getRightChildren();
}
