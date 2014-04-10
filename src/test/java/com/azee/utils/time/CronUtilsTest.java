package com.azee.utils.time;

import com.azee.utils.time.CronUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.Assert.assertEquals;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:spring-test-context.xml")
public class CronUtilsTest {
    @Autowired
    CronUtils cronUtils;

    @Test
    public void convertToQuartzTest(){
        assertEquals("0 * * * * ?", cronUtils.convertToQuartz("* * * * *"));
        assertEquals("0 0 * * * ?", cronUtils.convertToQuartz("0 * * * *"));
        assertEquals("0 0 1 * * ?", cronUtils.convertToQuartz("0 1 * * *"));
        assertEquals("0 0 * ? * 2", cronUtils.convertToQuartz("0 * * * 1"));
        assertEquals("0 0 * * * ?", cronUtils.convertToQuartz("0 * * * *"));
        assertEquals("0 */1 * * * ?", cronUtils.convertToQuartz("*/1 * * * *"));
        assertEquals("0 0 10 ? * 2,3,4,5", cronUtils.convertToQuartz("0 10 * * 1-4"));
        assertEquals("0 0 10 ? * 3,4,5,6", cronUtils.convertToQuartz("0 10 * * 2-5"));
        assertEquals("0 0 10 ? * 2", cronUtils.convertToQuartz("0 10 * * 1"));
        assertEquals("0 0 10 ? * 3,4,5,6,7,1", cronUtils.convertToQuartz("0 10 * * 2-7"));
        assertEquals("0 0 10 ? * 1", cronUtils.convertToQuartz("0 10 * * 7"));
        assertEquals("0 0 10 ? * 1", cronUtils.convertToQuartz("0 10 * * 0"));
        assertEquals("0 0 10 ? * 2,3,4,5,6,7,1", cronUtils.convertToQuartz("0 10 * * 1,2,3,4,5,6,7"));
        assertEquals("0 0 10 ? * 1,2,3,4,5,6,7", cronUtils.convertToQuartz("0 10 * * 0,1,2,3,4,5,6"));
    }

}
