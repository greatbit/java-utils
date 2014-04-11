package com.azee.utils.marshall.json;

import com.azee.utils.beans.BeanWithoutNamespaceExample;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;

/**
 * Created by azee on 4/11/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test-context.xml")
public class JsonUnmarshallerTest {

    @Autowired
    JsonUnmarshaller jsonUnmarshaller;

    String beanWithRoot = "{\"value\":1}";
    String beanWithoutRoot = "2";

    @Test
    public void testUnmarshal() throws Exception {
        BeanWithoutNamespaceExample bean = jsonUnmarshaller.unmarshal(beanWithRoot, BeanWithoutNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(1));

        bean = jsonUnmarshaller.unmarshal(beanWithoutRoot, "value", BeanWithoutNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(2));
    }
}
