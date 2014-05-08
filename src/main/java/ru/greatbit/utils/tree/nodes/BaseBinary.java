package ru.greatbit.utils.tree.nodes;

import ru.greatbit.utils.tree.api.Binary;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 5/8/14.
 * A base class for all binary trees
 */
public class BaseBinary<K, V> extends Node<K, V> implements Binary{
    private Node leftChild;
    private Node rightChild;

    @Override
    public Node getLeftChild() {
        return leftChild;
    }

    @Override
    public Node getRightChild() {
        return rightChild;
    }

    @Override
    public void setLeftChild(Node node) {
        leftChild = node;
    }

    @Override
    public void setRightChild(Node node) {
        rightChild = node;
    }


    @Override
    public List<Node> getChildren() {
        List<Node> children = new LinkedList<Node>();
        if (leftChild != null){
            children.add(leftChild);
        }

        if (rightChild != null){
            children.add(leftChild);
        }
        return children;
    }
}
