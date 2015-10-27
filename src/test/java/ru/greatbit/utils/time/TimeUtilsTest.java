package ru.greatbit.utils.time;

import org.junit.Test;
import java.util.TimeZone;

import static org.junit.Assert.assertEquals;


/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */

public class TimeUtilsTest {

    @Test
    public void getStartOfTheDayTest(){
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/Greenwich"));
        TimeZone tz = TimeZone.getDefault();
        System.out.println(tz.getRawOffset());
        assertEquals(1389312000000L - tz.getRawOffset(), TimeUtils.getStartOfTheDay(1389358493087L));
        assertEquals(1389312000000L, TimeUtils.getStartOfTheDay(1389358493087L));
        assertEquals(10800000L, TimeUtils.getStartOfTheDay(0));
        assertEquals(10800000L, TimeUtils.getStartOfTheDay(10800000L));
    }
}
