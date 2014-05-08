package ru.greatbit.utils.tree.api;

import ru.greatbit.utils.tree.nodes.Node;

/**
 * Created by azee on 5/8/14.
 * Used for Binary trees containing only 2 children - left and right
 */
public interface Binary {

    public Node getLeftChild();

    public Node getRightChild();

    public void setLeftChild(Node node);

    public void setRightChild(Node node);
}
