package ru.greatbit.utils.time;

import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */
@Service
public class CronUtils {
    private final String DEFAULT_CRON = "";
    private final String DEFAULT_SIMPLE_CRON = "";

    /**
     * Converts a UNIX-style cron value into the Quartz
     * @param input
     * @return
     */
    public String convertToQuartz(String input){
        String result = "";
        String[] entries = input.split(" ");

        //Incorrect cron, return default
        if (entries.length < 5) {
            return DEFAULT_CRON;
        }

        //Can't be ? and * for both - Day-OfThe-Week and Day-OfThe-Month
        if ("*".equals(entries[4])){
            entries[4] = "?";
        }
        if (!"*".equals(entries[4]) && !"?".equals(entries[4]) && "*".equals(entries[2])){
            entries[2] = "?";
        }

        //In quartz Day of The week starts from 1 and it is sunday
        if (!"*".equals(entries[4]) && !"?".equals(entries[4])){
            try {
                entries[4] = adjustDayOfWeek(entries[4]);
            } catch (NumberFormatException e){
                return DEFAULT_CRON;
            }
        }

        //Rebuild a new string
        for (String entry : Arrays.asList(entries)){
            result = result + entry + " ";
        }

        //Add seconds
        result = "0 " + result;
        return result.trim();
    }


    /**
     * Format a day-of-week item
     * @param entry
     * @return
     * @throws NumberFormatException
     */
    private String adjustDayOfWeek(String entry) throws NumberFormatException{
        String splitter = "";
        String result = "";

        //Convert 1-5 to 1,2,3,4,5
        if (entry.contains("-")){
            entry = formCommaSeparatedEntry(entry);
        }

        //Process all days from cron to quartz
        if (entry.contains(",")){
            splitter = ",";
        } else{
            return result + convertCronDayToQuartz(Integer.parseInt(entry));
        }

        for(String number : entry.split(splitter)){
            result = result + convertCronDayToQuartz(Integer.parseInt(number)) + ",";
        }

        //Remove last comma
        result = result.substring(0, result.length() - 1);
        return result;
    }

    /**
     * Convert 1-5 to 1,2,3,4,5
     * @param entry
     * @return
     */
    private String formCommaSeparatedEntry(String entry) throws NumberFormatException{
        String result = "";
        String[] values = entry.split("-");
        if (values.length != 2){
            throw new NumberFormatException();
        }

        int low = Integer.parseInt(values[0].trim());
        int high = Integer.parseInt(values[1].trim());

        for(int i = low; i <= high; i++){
            result = result + i + ",";
        }

        //Remove last comma
        result = result.substring(0, result.length() - 1);

        return result;
    }

    /**
     * Converts cron days to Quartz
     * @param value
     * @return
     */
    private int convertCronDayToQuartz(int value){
        if (value == 7 || value == 0){
            return 1;
        } else {
            return value + 1;
        }
    }
}
