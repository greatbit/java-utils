package ru.greatbit.utils.collection;

import org.junit.Test;
import ru.greatbit.utils.beans.BeanWithNamespaceExample;
import ru.greatbit.utils.string.StringUtils;

import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

/**
 * Created by azee on 4/29/14.
 */
public class ListUtilsTest {

    @Test
    public void mergeStringListsTest(){
        List<String> first = new LinkedList<String>();
        List<String> second = new LinkedList<String>();

        first.add("1");
        first.add("2");
        first.add("3");

        second.add("2");
        second.add("3");
        second.add("4");

        List<String> result = ListUtils.mergeLists(first, second);
        assertNotNull(result);
        assertThat("Wrong number of items in result list", result.size(), is(4));
        assertTrue(StringUtils.isStringInList(result, "1"));
        assertTrue(StringUtils.isStringInList(result, "2"));
        assertTrue(StringUtils.isStringInList(result, "3"));
        assertTrue(StringUtils.isStringInList(result, "4"));
    }

    @Test
    public void mergeObjectListsTest(){
        List<BeanWithNamespaceExample> first = new LinkedList<BeanWithNamespaceExample>();
        List<BeanWithNamespaceExample> second = new LinkedList<BeanWithNamespaceExample>();

        BeanWithNamespaceExample commonBean = new BeanWithNamespaceExample(3);

        first.add(new BeanWithNamespaceExample(1));
        first.add(new BeanWithNamespaceExample(2));
        first.add(commonBean);

        second.add(commonBean);
        second.add(new BeanWithNamespaceExample(4));
        second.add(new BeanWithNamespaceExample(5));


        List<BeanWithNamespaceExample> result = ListUtils.mergeLists(first, second);
        assertNotNull(result);
        assertThat("Wrong number of items in result list", result.size(), is(5));

        List<String> values = new LinkedList<String>();
        for (BeanWithNamespaceExample bean : result){
            values.add(Integer.toString(bean.getValue()));
        }

        assertTrue(StringUtils.isStringInList(values, "1"));
        assertTrue(StringUtils.isStringInList(values, "2"));
        assertTrue(StringUtils.isStringInList(values, "3"));
        assertTrue(StringUtils.isStringInList(values, "4"));
        assertTrue(StringUtils.isStringInList(values, "5"));
    }
}

