package ru.greatbit.utils.beans;

import javax.xml.bind.annotation.*;

/**
 * Created by azee on 4/10/14.
 */

@XmlAccessorType(XmlAccessType.FIELD)

@XmlRootElement(name = "beanExample")
public class BeanWithoutNamespaceExample {
    @XmlElement(required = true)
    protected int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
