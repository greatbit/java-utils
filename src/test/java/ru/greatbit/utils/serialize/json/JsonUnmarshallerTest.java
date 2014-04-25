package ru.greatbit.utils.serialize.json;

import ru.greatbit.utils.beans.BeanWithoutNamespaceExample;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.Is.is;

/**
 * Created by azee on 4/11/14.
 */
public class JsonUnmarshallerTest {
    String beanWithRoot = "{\"value\":1}";
    String beanWithoutRoot = "2";

    @Test
    public void testUnmarshal() throws Exception {
        BeanWithoutNamespaceExample bean = JsonUnmarshaller.unmarshal(beanWithRoot, BeanWithoutNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(1));

        bean = JsonUnmarshaller.unmarshal(beanWithoutRoot, "value", BeanWithoutNamespaceExample.class);
        assertNotNull(bean);
        Assert.assertThat("Wrong bean value", bean.getValue(), is(2));
    }
}
