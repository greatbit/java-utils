package com.azee.utils.beans;

import javax.xml.bind.annotation.*;

/**
 * Created by azee on 4/10/14.
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "NamespaceBeanExample", propOrder = {
        "value"
})
@XmlRootElement(name = "namespaceBeanExample", namespace = "beans.utils.azee.com")
public class BeanExample {
    @XmlElement(required = true)
    protected int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
