package ru.greatbit.utils.reflection;

import org.junit.Test;
import ru.greatbit.utils.beans.RecursiveBeanExample;
import ru.greatbit.utils.exceptions.NullObjectException;
import ru.greatbit.utils.refclection.FieldsFetcher;
import sun.swing.BakedArrayList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by azee on 17.08.14.
 */
public class FieldsFetcherTest {
    @Test
    public void valueFetcherTest() throws NullObjectException, InstantiationException, IllegalAccessException {
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

        assertThat((String) FieldsFetcher.findValue(parent, "childPrivate.str"), is("private"));
        assertThat((Integer) FieldsFetcher.findValue(parent, "childPrivate.count"), is(2));

        assertThat((String) FieldsFetcher.findValue(parent, Arrays.asList("childPublic.str".split("\\."))), is("public"));
        assertThat((Integer) FieldsFetcher.findValue(parent, Arrays.asList("childPublic.count".split("\\."))), is(3));

        assertThat((String) FieldsFetcher.findValue(parent, "childPublic.str"), is("public"));
        assertThat((Integer) FieldsFetcher.findValue(parent, "childPublic.count"), is(3));

        assertThat((String) FieldsFetcher.findValue(parent, Arrays.asList("childPublic.childPrivate.str".split("\\."))), is("grand"));
        assertThat((Integer) FieldsFetcher.findValue(parent, Arrays.asList("childPublic.childPrivate.count".split("\\."))), is(4));

        assertThat((String) FieldsFetcher.findValue(parent, "childPublic.childPrivate.str"), is("grand"));
        assertThat((Integer) FieldsFetcher.findValue(parent, "childPublic.childPrivate.count"), is(4));
    }

    @Test
    public void mergeListsByInterfaceTest() throws IllegalAccessException {
        class SuperClass{
            public String value;
        }
        class ChildOne extends SuperClass{}
        class ChildTwo extends SuperClass{}
        class Container{
            public List<SuperClass> publicList = new ArrayList<SuperClass>();
            private List<SuperClass> privateList = new ArrayList<SuperClass>();
            private List<SuperClass> emptyList = new ArrayList<SuperClass>();
            private List<SuperClass> nullList = null;

            public List<SuperClass> getPrivateList() {
                return privateList;
            }
        }

        SuperClass superClass = new SuperClass();
        superClass.value = "super";

        ChildOne childOne = new ChildOne();
        childOne.value = "childOne";

        ChildTwo childTwo = new ChildTwo();
        childTwo.value = "childTwo";

        ChildOne childPrivate = new ChildOne();
        childPrivate.value = "childPrivate";

        Container container = new Container();
        container.publicList.add(superClass);
        container.publicList.add(childOne);
        container.publicList.add(childTwo);

        container.getPrivateList().add(childPrivate);

        List<SuperClass> result = FieldsFetcher.mergeListsByInterface(container, SuperClass.class);
        assertNotNull(result);
        assertThat(result.size(), is(4));

        assertNotNull(result.get(0));
        assertNotNull(result.get(1));
        assertNotNull(result.get(2));
        assertNotNull(result.get(3));

        assertThat(result.get(0).value, is("super"));
        assertThat(result.get(1).value, is("childOne"));
        assertThat(result.get(2).value, is("childTwo"));
        assertThat(result.get(3).value, is("childPrivate"));
    }

    @Test
    public void getValuesByInterfaceTest() throws IllegalAccessException {
        class SuperClass{
            public String value;
        }
        class ChildOne extends SuperClass{}
        class ChildTwo extends SuperClass{}
        class Container{
            public SuperClass superClass;
            public SuperClass publicChildOne;
            public SuperClass publicChildTwo;
            private SuperClass privateChild;
            public SuperClass nullChild;

            public SuperClass getPrivateChild() {
                return privateChild;
            }

            public void setPrivateChild(SuperClass privateChild) {
                this.privateChild = privateChild;
            }
        }

        SuperClass superClass = new SuperClass();
        superClass.value = "super";

        ChildOne childOne = new ChildOne();
        childOne.value = "childOne";

        ChildTwo childTwo = new ChildTwo();
        childTwo.value = "childTwo";

        ChildOne childPrivate = new ChildOne();
        childPrivate.value = "childPrivate";

        Container container = new Container();
        container.superClass = superClass;
        container.publicChildOne = childOne;
        container.publicChildTwo = childTwo;
        container.setPrivateChild(childPrivate);

        List<SuperClass> result = FieldsFetcher.getValuesByInterface(container, SuperClass.class);
        assertNotNull(result);
        assertThat(result.size(), is(5));

        assertNotNull(result.get(0));
        assertNotNull(result.get(1));
        assertNotNull(result.get(2));
        assertNotNull(result.get(3));
        assertNull(result.get(4));

        assertThat(result.get(0).value, is("super"));
        assertThat(result.get(1).value, is("childOne"));
        assertThat(result.get(2).value, is("childTwo"));
        assertThat(result.get(3).value, is("childPrivate"));
    }
}

