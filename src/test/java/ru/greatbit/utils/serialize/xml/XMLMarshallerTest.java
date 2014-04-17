package ru.greatbit.utils.serialize.xml;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.greatbit.utils.beans.BeanWithNamespaceExample;
import ru.greatbit.utils.beans.BeanWithoutNamespaceExample;

import static org.hamcrest.core.Is.is;

/**
 * Created by azee on 4/10/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test-context.xml")
public class XMLMarshallerTest {

    @Autowired
    XMLMarshaller xmlMarshaller;

    private String marshalledBean = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<beanExample><value>3</value></beanExample>";

    private String marshalledBeanWithNs = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<beanExample xmlns=\"beans.utils.greatbit.ru\"><value>4</value></beanExample>";


    @Test
    public void testMarshalNoNamespaces() throws Exception {
        BeanWithoutNamespaceExample bean = new BeanWithoutNamespaceExample();
        bean.setValue(3);
        Assert.assertThat("Bean was not marshalled correctly",
                xmlMarshaller.marshal(bean),
                is(marshalledBean));
    }

    @Test
    public void testMarshalWithNamespaces() throws Exception {
        BeanWithNamespaceExample bean = new BeanWithNamespaceExample();
        bean.setValue(4);
        Assert.assertThat("Bean was not marshalled correctly",
                xmlMarshaller.marshal(bean),
                is(marshalledBeanWithNs));
    }



}
