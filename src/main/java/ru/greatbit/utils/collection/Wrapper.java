package ru.greatbit.utils.collection;

import java.util.function.Function;

/**
 * Created by azee on 31.05.16.
 */
public class Wrapper<T> {
    private T object;
    Function<T, Object> meaningValue;

    public Wrapper() {
    }

    public Wrapper(T object, Function<T, Object> meaningValue) {
        this.object = object;
        this.meaningValue = meaningValue;
    }

    public Wrapper(T object) {
        this.object = object;
    }

    public T getObject() {
        return object;
    }

    public void setObject(T object) {
        this.object = object;
    }

    public Function<T, Object> getMeaningValue() {
        return meaningValue;
    }

    public void setMeaningValue(Function<T, Object> meaningValue) {
        this.meaningValue = meaningValue;
    }

    public Wrapper<T> withObject(T object){
        this.object = object;
        return this;
    }

    public Wrapper<T> withEquals(Function<T, Object> meaningValue){
        this.meaningValue = meaningValue;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Wrapper && meaningValue != null){
            Object meaningValueObj = meaningValue.apply(this.getObject());
            obj = meaningValue.apply((T) ((Wrapper) obj).getObject());
            if (meaningValueObj == null && obj == null){
                return true;
            }
            if (meaningValueObj == null){
                return obj.equals(meaningValueObj);
            } else {
                return meaningValueObj.equals(obj);
            }
        }
        return equals(obj);
    }

    @Override
    public int hashCode() {
        Object meaningValueObj = meaningValue.apply(this.getObject());
        return meaningValueObj != null ? meaningValueObj.hashCode() : 0;
    }
}
