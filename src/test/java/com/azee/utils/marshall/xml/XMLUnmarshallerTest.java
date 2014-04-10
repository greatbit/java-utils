package com.azee.utils.marshall.xml;

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

    @Test
    public void testUnmarshall() throws Exception {
        NamespaceBeanExample bean = xmlUnmarshaller.unmarshall(marshalledBean, NamespaceBeanExample.class);
        assertNotNull(bean);
    }

    @Test
    public void testGetXMLRootNamespace() throws Exception {
        Assert.assertThat("Wonrg namespace",
                xmlUnmarshaller.getXMLRootNamespace(NamespaceBeanExample.class),
                is("beans.utils.azee.com"));
    }
}
