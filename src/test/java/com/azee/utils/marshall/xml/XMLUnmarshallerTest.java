package com.azee.utils.marshall.xml;

import com.azee.utils.beans.BeanExample;
import com.azee.utils.beans.NamespaceBeanExample;
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

    private String marshalledBean = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<namespaceBeanExample><value>2</value></namespaceBeanExample>";

    private String marshalledBeanWithNs = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<ns2:namespaceBeanExample xmlns:ns2=\"beans.utils.azee.com\"><value>10</value></ns2:namespaceBeanExample>";


    @Test
    public void testUnmarshall() throws Exception {
        NamespaceBeanExample bean = xmlUnmarshaller.unmarshall(marshalledBean, NamespaceBeanExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(2));
    }

    @Test
    public void testUnmarshallWNamespace() throws Exception {
        BeanExample bean = xmlUnmarshaller.unmarshall(marshalledBeanWithNs, BeanExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(10));
    }

    @Test
    public void testGetXMLRootNamespace() throws Exception {
        Assert.assertThat("Wrong namespace",
                xmlUnmarshaller.getXMLRootNamespace(BeanExample.class),
                is("beans.utils.azee.com"));
    }
    @Test
    public void testGetXMLRootNoNamespace() throws Exception {
        Assert.assertThat("Wrong namespace",
                xmlUnmarshaller.getXMLRootNamespace(NamespaceBeanExample.class),
                is("beans.utils.azee.com"));
    }


}
