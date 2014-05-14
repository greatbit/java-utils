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
     * @param toAdd - String - entry to add
     * @param strings - List<String> - a list to update
     * @return
     */
    public static List<String> addUniqueString(String toAdd, List<String> strings){
        toAdd = emptyIfNull(toAdd);
        if (!isStringInList(strings, toAdd)){
            strings.add(toAdd);
        }
        return strings;
    }

    /**
     * Find out if all of provided string parts are present in the source string
     * @param source - A String to search substrings in
     * @param toSearch - an array of possible substrings
     * @return
     */
    public static boolean containsAll(String source, String... toSearch){
        if (source == null || toSearch == null || toSearch.length == 0){
            return false;
        }
        for (String entry : toSearch){
            if (!source.contains(entry)){
                return false;
            }
        }
        return true;
    }

    /**
     * Find out if provided souce string contains any of string parts from the list
     * @param source - A String to search substrings in
     * @param toSearch - an array of possible substrings
     * @return
     */
    public static boolean containsAny(String source, String... toSearch){
        if (source == null || toSearch == null || toSearch.length == 0){
            return false;
        }
        for (String entry : toSearch){
            if (source.contains(entry)){
                return true;
            }
        }
        return false;
    }

    /**
     * Remove tailing string (e.g. comma or slash)
     * @param source
     * @param toRemove
     * @return
     */
    public static String removeTailing(String source, String toRemove){
        if (toRemove == null){
            return source;
        }
        int toRemoveLength = toRemove.length();
        if (toRemove.equals(source.substring(source.length() - toRemoveLength))) {
            source = source.substring(0, source.length() - toRemoveLength);
        }
        return source;
    }

    /**
     * Remove heading string (e.g. comma or slash)
     * @param source
     * @param toRemove
     * @return
     */
    public static String removeHeading(String source, String toRemove){
        if (toRemove == null){
            return source;
        }
        int toRemoveLength = toRemove.length();
        if (toRemove.equals(source.substring(0, toRemoveLength))) {
            source = source.substring(toRemoveLength, source.length());
        }
        return source;
    }
}