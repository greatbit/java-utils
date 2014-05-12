package ru.greatbit.utils.serialize;

import org.junit.Assert;
import org.junit.Test;
import ru.greatbit.utils.beans.BeanWithNamespaceExample;
import ru.greatbit.utils.beans.BeanWithoutNamespaceExample;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;

/**
 * Created by azee on 5/12/14.
 */
public class SerializerTest {
    private String marshalledBean = "<beanExample><value>2</value></beanExample>";

    private String marshalledBeanWithNs = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<ns2:beanExample xmlns:ns2=\"beans.utils.greatbit.ru\"><ns2:value>10</ns2:value></ns2:beanExample>";

    private String beanWithRoot = "{\"value\":1}";

    @Test
    public void testUnmarshal() throws Exception {
        BeanWithoutNamespaceExample bean = Serializer.unmarshal(beanWithRoot, BeanWithoutNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(1));
    }

    @Test
    public void testUnmarshall() throws Exception {
        BeanWithoutNamespaceExample bean = Serializer.unmarshal(marshalledBean, BeanWithoutNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(2));
    }

    @Test
    public void testUnmarshallWNamespace() throws Exception {
        BeanWithNamespaceExample bean = Serializer.unmarshal(marshalledBeanWithNs, BeanWithNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(10));
    }

    @Test
    public void testUnmarshallWithoutNamespace() throws Exception {
        BeanWithNamespaceExample bean = Serializer.unmarshal(marshalledBean, BeanWithNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(2));
    }
}
