package com.azee.utils.string;

import com.azee.utils.string.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertTrue;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test-context.xml")
public class StringUtilsTest {
    @Autowired
    StringUtils stringUtils;

    @Test
    public void testEmptyIfNull() throws Exception {
        assertEquals("", stringUtils.emptyIfNull(null));
        assertEquals("", stringUtils.emptyIfNull(""));
        assertEquals("abc", stringUtils.emptyIfNull("abc"));
    }

    @Test
    public void testListAsString() throws Exception {
        List<String> strings = new LinkedList<String>();
        strings.add("Two");
        strings.add("beers");
        strings.add("or");
        strings.add("not");
        strings.add("two");
        strings.add("beers");

        assertEquals("Two,beers,or,not,two,beers", stringUtils.listAsString(strings));
    }

    @Test
    public void testGetMd5String() throws Exception {
        assertEquals("4896cb726e6127ac4e7bb382edae08ab", stringUtils.getMd5String("Super secret string"));
    }

    @Test
    public void testIsStringInList() throws Exception {
        List<String> strings = new LinkedList<String>();
        strings.add("Playing");
        strings.add("hide");
        strings.add("and");
        strings.add("seek");

        assertTrue(stringUtils.isStringInList(strings, "hide"));
        assertFalse(stringUtils.isStringInList(strings, "bride"));

    }
}
