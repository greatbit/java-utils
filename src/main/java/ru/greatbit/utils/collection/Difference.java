package ru.greatbit.utils.collection;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by azee on 4/29/14.
 */
public class Difference<T> {

    private List<T> removed;
    private List<T> added;
    private List<T> equal;

    public List<T> getRemoved(){
        if (removed == null){
            removed = new LinkedList<T>();
        }
        return removed;
    }

    public List<T> getAdded(){
        if (added == null){
            added = new LinkedList<T>();
        }
        return added;
    }

    public List<T> getEqual(){
        if (equal == null){
            equal = new LinkedList<T>();
        }
        return equal;
    }

    public List<T> getMerged(){
        List<T> result = new LinkedList<T>();
        result.addAll(equal);
        result.addAll(removed);
        result.addAll(added);
        return result;
    }
}
