package ru.greatbit.utils.time;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */
public class TimeUtils {

    private static final long BEGINNING_OF_TIMES = 10800000L;

    /**
     * Provides the time in milliseconds that corresponds the beginning of the provided day
     * @param time - long time of the day
     * @return long time - the beginning of the provided day
     */
    public static long getStartOfTheDay(long time){
        if (time <= BEGINNING_OF_TIMES){
            return BEGINNING_OF_TIMES;
        }
        return getStartOfTheDay(new Date(time)).getTime();
    }

    /**
     * Provides the Date that corresponds the beginning of the provided day
     * @param date - Date object - time of the day
     * @return date - the beginning of the provided day
     */
    public static Date getStartOfTheDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }


}
