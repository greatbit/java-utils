package ru.greatbit.utils.serialize.xml;

import ru.greatbit.utils.beans.BeanWithNamespaceExample;
import ru.greatbit.utils.beans.BeanWithoutNamespaceExample;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;

/**
 * Created by azee on 4/10/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test-context.xml")
public class XMLUnmarshallerTest {

    @Autowired
    XMLUnmarshaller xmlUnmarshaller;

    private String marshalledBean = "<beanExample><value>2</value></beanExample>";

    private String marshalledBeanWithNs = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<ns2:beanExample xmlns:ns2=\"beans.utils.greatbit.ru\"><ns2:value>10</ns2:value></ns2:beanExample>";


    @Test
    public void testUnmarshall() throws Exception {
        BeanWithoutNamespaceExample bean = xmlUnmarshaller.unmarshal(marshalledBean, BeanWithoutNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(2));
    }

    @Test
    public void testUnmarshallWNamespace() throws Exception {
        BeanWithNamespaceExample bean = xmlUnmarshaller.unmarshal(marshalledBeanWithNs, BeanWithNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(10));
    }

    @Test
    public void testUnmarshallWithoutNamespace() throws Exception {
        BeanWithNamespaceExample bean = xmlUnmarshaller.unmarshal(marshalledBean, BeanWithNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(2));
    }

    @Test
    public void testGetXMLRootNamespace() throws Exception {
        Assert.assertThat("Wrong namespace",
                xmlUnmarshaller.getXMLRootNamespace(BeanWithNamespaceExample.class),
                is("beans.utils.greatbit.ru"));
    }
    @Test
    public void testGetXMLRootNoNamespace() throws Exception {
        Assert.assertThat("Wrong namespace",
                xmlUnmarshaller.getXMLRootNamespace(BeanWithoutNamespaceExample.class),
                is("beans.utils.greatbit.ru"));
    }


}
