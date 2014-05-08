package ru.greatbit.utils.tree.nodes;

/**
 * Created by azee on 5/8/14.
 * AVL-tree node implementation
 */
public class AvlNode<K, V> extends BaseBinary<K, V>{

    private int height;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


}
