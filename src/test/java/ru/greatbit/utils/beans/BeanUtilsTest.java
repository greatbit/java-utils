package ru.greatbit.utils.beans;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;
import static org.hamcrest.core.Is.is;

/**
 * Created by azee on 15.03.16.
 */
public class BeanUtilsTest {
    @Test
    public void testGetCopy() throws Exception {
        BeanWithoutNamespaceExample bean = new BeanWithoutNamespaceExample();
        bean.setValue(1);

        BeanWithoutNamespaceExample copy = BeanUtils.getCopy(bean);
        assertNotNull(copy);
        Assert.assertThat("Wrong value of the copy", copy.getValue(), is(1));
        Assert.assertFalse(bean == copy);
    }

    @Test
    public void testCompareByValJaxb() throws Exception {
        BeanWithoutNamespaceExample bean1 = new BeanWithoutNamespaceExample(1);
        BeanWithoutNamespaceExample bean2 = new BeanWithoutNamespaceExample(1);
        BeanWithoutNamespaceExample bean3 = new BeanWithoutNamespaceExample(2);

        assertFalse(bean1 == bean2);
        assertTrue(BeanUtils.equalByVal(bean1, bean2));
        assertTrue(BeanUtils.equalByVal(bean1, bean1));
        assertTrue(BeanUtils.equalByVal(null, null));
        assertFalse(BeanUtils.equalByVal(bean1, null));
        assertFalse(BeanUtils.equalByVal(null, bean2));
        assertFalse(BeanUtils.equalByVal(bean1, bean3));
    }

    @Test
    public void testCompareByValSimple() throws Exception {
        SimpleBeanExample bean1 = new SimpleBeanExample(1);
        SimpleBeanExample bean2 = new SimpleBeanExample(1);
        SimpleBeanExample bean3 = new SimpleBeanExample(2);

        assertFalse(bean1 == bean2);
        assertTrue(BeanUtils.equalByVal(bean1, bean2));
        assertTrue(BeanUtils.equalByVal(bean1, bean1));
        assertTrue(BeanUtils.equalByVal(null, null));
        assertFalse(BeanUtils.equalByVal(bean1, null));
        assertFalse(BeanUtils.equalByVal(null, bean2));
        assertFalse(BeanUtils.equalByVal(bean1, bean3));
    }
}
