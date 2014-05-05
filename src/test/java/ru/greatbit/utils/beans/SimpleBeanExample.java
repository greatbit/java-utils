package ru.greatbit.utils.beans;

import javax.xml.bind.annotation.*;

/**
 * Created by azee on 4/10/14.
 */

public class SimpleBeanExample {
    protected int value;

    public SimpleBeanExample() {}

    public SimpleBeanExample(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
