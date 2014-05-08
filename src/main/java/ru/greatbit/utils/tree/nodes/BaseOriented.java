package ru.greatbit.utils.tree.nodes;

import ru.greatbit.utils.tree.api.Oriented;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 5/8/14.
 * A base class for all N-ary oriented trees
 */
public class BaseOriented<K, V> extends Node<K, V> implements Oriented {
    private List<Node> leftChildren;
    private List<Node> rightChildren;

    @Override
    public List<Node> getLeftChildren() {
        if (leftChildren == null){
            leftChildren = new LinkedList<Node>();
        }
        return leftChildren;
    }

    @Override
    public List<Node> getRightChildren() {
        if (rightChildren == null){
            rightChildren = new LinkedList<Node>();
        }
        return rightChildren;
    }

    @Override
    public List<Node> getChildren() {
        List<Node> result = new LinkedList<Node>();
        if (getLeftChildren().size() > 0){
            result.addAll(getLeftChildren());
        }
        if (getRightChildren().size() > 0){
            result.addAll(getRightChildren());
        }
        return result;
    }
}
