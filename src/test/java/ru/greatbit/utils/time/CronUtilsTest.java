package ru.greatbit.utils.time;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */
public class CronUtilsTest {

    @Test
    public void convertToQuartzTest(){
        assertEquals("0 * * * * ?", CronUtils.convertToQuartz("* * * * *"));
        assertEquals("0 0 * * * ?", CronUtils.convertToQuartz("0 * * * *"));
        assertEquals("0 0 1 * * ?", CronUtils.convertToQuartz("0 1 * * *"));
        assertEquals("0 0 * ? * 2", CronUtils.convertToQuartz("0 * * * 1"));
        assertEquals("0 0 * * * ?", CronUtils.convertToQuartz("0 * * * *"));
        assertEquals("0 */1 * * * ?", CronUtils.convertToQuartz("*/1 * * * *"));
        assertEquals("0 0 10 ? * 2,3,4,5", CronUtils.convertToQuartz("0 10 * * 1-4"));
        assertEquals("0 0 10 ? * 3,4,5,6", CronUtils.convertToQuartz("0 10 * * 2-5"));
        assertEquals("0 0 10 ? * 2", CronUtils.convertToQuartz("0 10 * * 1"));
        assertEquals("0 0 10 ? * 3,4,5,6,7,1", CronUtils.convertToQuartz("0 10 * * 2-7"));
        assertEquals("0 0 10 ? * 1", CronUtils.convertToQuartz("0 10 * * 7"));
        assertEquals("0 0 10 ? * 1", CronUtils.convertToQuartz("0 10 * * 0"));
        assertEquals("0 0 10 ? * 2,3,4,5,6,7,1", CronUtils.convertToQuartz("0 10 * * 1,2,3,4,5,6,7"));
        assertEquals("0 0 10 ? * 1,2,3,4,5,6,7", CronUtils.convertToQuartz("0 10 * * 0,1,2,3,4,5,6"));
    }

}
