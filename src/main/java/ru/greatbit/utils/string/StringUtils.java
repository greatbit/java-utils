package ru.greatbit.utils.string;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */
public class StringUtils {

    /**
     * Returns an empty string if null - helps to avoid NPE
     * @param input - String to verify
     * @return String
     */
    public static String emptyIfNull(String input){
        if (input == null) {
            return "";
        }
        else return input;
    }

    /**
     * Provide a string with a comma separated
     * @param stringList - List of strings
     * @return result String
     */
    public static String listAsString(List<String> stringList){
        String result = "";
        for (String oneString : stringList){
            result = result + oneString + ",";
        }

        //Removing last ','
        if (result.endsWith(","))
        {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * Get an MD5 sequence from a string
     * @param input - String to encode
     * @return - md5 String
     * @throws NoSuchAlgorithmException
     */
    public static String getMd5String(String input) throws NoSuchAlgorithmException {
        final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
        messageDigest.reset();
        messageDigest.update(input.getBytes(Charset.forName("UTF8")));
        final byte[] resultByte = messageDigest.digest();
        final String result = Hex.encodeHexString(resultByte);
        return result;
    }

    /**
     * Verifies if a list of strings contains a given one
     * @param stringList - List of strings
     * @param toSearch - String to search
     * @return - boolean - is string in list
     */
    public static boolean isStringInList(List<String> stringList, String toSearch){
        toSearch = emptyIfNull(toSearch);

        for (String entry : stringList){
            if (entry != null && toSearch.equals(entry))
                return true;
        }

        return false;
    }

    /**
     * Add a string to list if it is not represented in list yet
     * @param toAdd
     * @param strings
     * @return
     */
    public static List<String> addUniqueString(String toAdd, List<String> strings){
        toAdd = emptyIfNull(toAdd);
        if (!isStringInList(strings, toAdd)){
            strings.add(toAdd);
        }
        return strings;
    }
}