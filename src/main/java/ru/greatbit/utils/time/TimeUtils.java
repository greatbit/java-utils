package ru.greatbit.utils.time;

import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */
@Service
public class TimeUtils {

    private final long BEGINNING_OF_TIMES = 10800000L;

    /**
     * Provides the time in milliseconds that corresponds the beginning of the provided day
     * @param time - ling time of the day
     * @return long time - the beginning of the provided day
     */
    public long getStartOfTheDay(long time){
        if (time <= BEGINNING_OF_TIMES){
            return BEGINNING_OF_TIMES;
        }

        Date date = new Date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }


}
