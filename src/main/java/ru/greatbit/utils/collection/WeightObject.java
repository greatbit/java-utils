package ru.greatbit.utils.collection;

/**
 * Created by azee on 04.03.16.
 */
public class WeightObject<T> implements Comparable<WeightObject>{
    private T object;
    private long weight;

    public WeightObject(T object, long weight) {
        this.object = object;
        this.weight = weight;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public long getWeight() {
        return weight;
    }

    public void setWeight(long weight) {
        this.weight = weight;
    }

    @Override
    public int compareTo(WeightObject o) {
        return getWeight() >= o.getWeight() ? 1 : -1;
    }
}
