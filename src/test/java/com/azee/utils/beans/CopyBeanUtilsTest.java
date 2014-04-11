package com.azee.utils.beans;

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
public class CopyBeanUtilsTest {

    @Autowired
    CopyBeanUtils copyBeanUtils;

    @Test
    public void testGetCopy() throws Exception {
        BeanWithoutNamespaceExample bean = new BeanWithoutNamespaceExample();
        bean.setValue(1);

        BeanWithoutNamespaceExample copy = copyBeanUtils.getCopy(bean);
        assertNotNull(copy);
        Assert.assertThat("Wrong value of the copy", copy.getValue(), is(1));
        Assert.assertFalse(bean == copy);
    }
}
