package ru.greatbit.utils.time;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.TimeZone;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test-context.xml")
public class TimeUtilsTest {
    @Autowired
    TimeUtils timeUtils;

    @Test
    public void getStartOfTheDayTest(){
        TimeZone tz = TimeZone.getDefault();
        assertEquals(1389312000000L - tz.getRawOffset(), timeUtils.getStartOfTheDay(1389358493087L));
        assertEquals(10800000L, timeUtils.getStartOfTheDay(0));
        assertEquals(10800000L, timeUtils.getStartOfTheDay(10800000L));
    }
}
