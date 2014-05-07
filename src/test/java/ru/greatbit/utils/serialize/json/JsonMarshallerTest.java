package ru.greatbit.utils.serialize.json;

import ru.greatbit.utils.beans.BeanWithoutNamespaceExample;
import org.junit.Assert;
import org.junit.Test;
import ru.greatbit.utils.serialize.JsonSerializer;

import static org.hamcrest.core.Is.is;

/**
 * Created by azee on 4/11/14.
 */
public class JsonMarshallerTest {


    String beanWithRoot = "{\"beanExample\": {\"value\":1}}";
    String beanWithoutRoot = "{\"value\":1}";

    @Test
    public void testMarshal() throws Exception {
        BeanWithoutNamespaceExample bean = new BeanWithoutNamespaceExample();
        bean.setValue(1);

        Assert.assertThat("Incorrect marshalling",
                JsonSerializer.marshal(bean), is(beanWithoutRoot));

        Assert.assertThat("Incorrect marshalling",
                JsonSerializer.marshal(bean, "beanExample"), is(beanWithRoot));
    }
}
