package ru.greatbit.utils.serialize.xml;

import org.junit.Assert;
import org.junit.Test;
import ru.greatbit.utils.beans.BeanWithNamespaceExample;
import ru.greatbit.utils.beans.BeanWithoutNamespaceExample;
import ru.greatbit.utils.serialize.XmlSerializer;

import static org.hamcrest.core.Is.is;

/**
 * Created by azee on 4/10/14.
 */

public class XMLMarshallerTest {

    private String marshalledBean = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<beanExample><value>3</value></beanExample>";

    private String marshalledBeanWithNs = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<beanExample xmlns=\"beans.utils.greatbit.ru\"><value>4</value></beanExample>";


    @Test
    public void testMarshalNoNamespaces() throws Exception {
        BeanWithoutNamespaceExample bean = new BeanWithoutNamespaceExample();
        bean.setValue(3);
        Assert.assertThat("Bean was not marshalled correctly",
                XmlSerializer.marshal(bean),
                is(marshalledBean));
    }

    @Test
    public void testMarshalWithNamespaces() throws Exception {
        BeanWithNamespaceExample bean = new BeanWithNamespaceExample();
        bean.setValue(4);
        Assert.assertThat("Bean was not marshalled correctly",
                XmlSerializer.marshal(bean),
                is(marshalledBeanWithNs));
    }



}
