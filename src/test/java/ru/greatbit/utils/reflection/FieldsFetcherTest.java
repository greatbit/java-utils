package ru.greatbit.utils.reflection;

import org.junit.Test;
import ru.greatbit.utils.beans.RecursiveBeanExample;
import ru.greatbit.utils.exceptions.NullObjectException;
import ru.greatbit.utils.refclection.FieldsFetcher;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by azee on 17.08.14.
 */
public class FieldsFetcherTest {
    @Test
    public void ValueFetcherTest() throws NullObjectException, InstantiationException, IllegalAccessException {
        RecursiveBeanExample parent = new RecursiveBeanExample();
        RecursiveBeanExample privateChild = new RecursiveBeanExample();
        RecursiveBeanExample publicChild = new RecursiveBeanExample();
        RecursiveBeanExample grandChild = new RecursiveBeanExample();

        parent.setStr("parent");
        parent.setCount(1);

        privateChild.setStr("private");
        privateChild.setCount(2);

        publicChild.setStr("public");
        publicChild.setCount(3);

        grandChild.setStr("grand");
        grandChild.setCount(4);

        parent.setChildPrivate(privateChild);
        parent.setChildPublic(publicChild);
        publicChild.setChildPrivate(grandChild);

        assertThat((String) FieldsFetcher.getObjectFromField(parent, parent.getClass(), "str"), is("parent"));
        assertThat((Integer) FieldsFetcher.getObjectFromField(parent, parent.getClass(), "count"), is(1));

        assertThat((String) FieldsFetcher.findValue(parent, Arrays.asList("childPrivate.str".split("\\."))), is("private"));
        assertThat((Integer) FieldsFetcher.findValue(parent, Arrays.asList("childPrivate.count".split("\\."))), is(2));

        assertThat((String) FieldsFetcher.findValue(parent, Arrays.asList("childPublic.str".split("\\."))), is("public"));
        assertThat((Integer) FieldsFetcher.findValue(parent, Arrays.asList("childPublic.count".split("\\."))), is(3));

        assertThat((String) FieldsFetcher.findValue(parent, Arrays.asList("childPublic.childPrivate.str".split("\\."))), is("grand"));
        assertThat((Integer) FieldsFetcher.findValue(parent, Arrays.asList("childPublic.childPrivate.count".split("\\."))), is(4));
    }
}
