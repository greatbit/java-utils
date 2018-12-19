package ru.greatbit.utils.time;

import org.junit.Test;

import java.util.Date;
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
        assertEquals(1389312000000L - tz.getRawOffset(), TimeUtils.getStartOfTheDay(1389358493087L));
        assertEquals(1389312000000L, TimeUtils.getStartOfTheDay(1389358493087L));
        assertEquals(10800000L, TimeUtils.getStartOfTheDay(0));
        assertEquals(10800000L, TimeUtils.getStartOfTheDay(10800000L));
    }

    @Test
    public void getStartOfTheDayDateTest(){
        TimeZone.setDefault(TimeZone.getTimeZone("Etc/Greenwich"));
        TimeZone tz = TimeZone.getDefault();
        assertEquals(new Date(1389312000000L - tz.getRawOffset()), TimeUtils.getStartOfTheDay(new Date(1389358493087L)));
        assertEquals(new Date(1389312000000L), TimeUtils.getStartOfTheDay(new Date(1389358493087L)));
        assertEquals(new Date(0), TimeUtils.getStartOfTheDay(new Date(0)));
        assertEquals(new Date(0), TimeUtils.getStartOfTheDay(new Date(10800000L)));
    }
}
