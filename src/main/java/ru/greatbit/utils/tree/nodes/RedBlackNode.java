package ru.greatbit.utils.tree.nodes;

/**
 * Created by azee on 5/8/14.
 * Red-Black-tree node implementation
 */
public class RedBlackNode<K, V> extends BaseBinary<K, V>{

    private boolean color;

    public boolean getColor() {
        return color;
    }

    public void setColor(boolean color) {
        this.color = color;
    }
}
