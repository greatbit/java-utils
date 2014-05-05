package ru.greatbit.utils.beans;

import org.junit.Test;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created by azee on 4/11/14.
 */
public class CompareBeanUtilsTest {

    @Test
    public void testCompareByValJaxb() throws Exception {
        BeanWithoutNamespaceExample bean1 = new BeanWithoutNamespaceExample(1);
        BeanWithoutNamespaceExample bean2 = new BeanWithoutNamespaceExample(1);
        BeanWithoutNamespaceExample bean3 = new BeanWithoutNamespaceExample(2);

        assertFalse(bean1 == bean2);
        assertTrue(CompareBeanUtils.equalByVal(bean1, bean2));
        assertTrue(CompareBeanUtils.equalByVal(bean1, bean1));
        assertTrue(CompareBeanUtils.equalByVal(null, null));
        assertFalse(CompareBeanUtils.equalByVal(bean1, null));
        assertFalse(CompareBeanUtils.equalByVal(null, bean2));
        assertFalse(CompareBeanUtils.equalByVal(bean1, bean3));
    }

    @Test
    public void testCompareByValSimple() throws Exception {
        SimpleBeanExample bean1 = new SimpleBeanExample(1);
        SimpleBeanExample bean2 = new SimpleBeanExample(1);
        SimpleBeanExample bean3 = new SimpleBeanExample(2);

        assertFalse(bean1 == bean2);
        assertTrue(CompareBeanUtils.equalByVal(bean1, bean2));
        assertTrue(CompareBeanUtils.equalByVal(bean1, bean1));
        assertTrue(CompareBeanUtils.equalByVal(null, null));
        assertFalse(CompareBeanUtils.equalByVal(bean1, null));
        assertFalse(CompareBeanUtils.equalByVal(null, bean2));
        assertFalse(CompareBeanUtils.equalByVal(bean1, bean3));
    }
}
