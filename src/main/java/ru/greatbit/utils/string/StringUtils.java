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
    public static <T>String listAsString(List<T> stringList){
        String result = stringList.toString();
        result = removeHeading(result, "[");
        result = removeTailing(result, "]");
        result.trim();
        return result;
    }

    /**
     * Provide a string with a comma separated
     * @param stringList - List of strings
     * @return result String
     */
    public static <T>String listAsStringNoSpaces(List<T> stringList){
        String result = "";
        for (T obj : stringList){
            result = result + obj + ",";
        }
        return removeTailing(result, ",").trim();
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
        source = source.trim();
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
        source = source.trim();
        int toRemoveLength = toRemove.length();
        if (toRemove.equals(source.substring(0, toRemoveLength))) {
            source = source.substring(toRemoveLength, source.length());
        }
        return source;
    }

    /**
     * Determine the longest common subsequence between the two strings
     * @param firstString
     * @param secondString
     * @return
     */
    public static String lcs(String firstString, String secondString) {
        int[][] lengths = new int[firstString.length() + 1][secondString.length() + 1];

        for (int i = 0; i < firstString.length(); i++)
            for (int j = 0; j < secondString.length(); j++)
                if (firstString.charAt(i) == secondString.charAt(j))
                    lengths[i+1][j+1] = lengths[i][j] + 1;
                else
                    lengths[i+1][j+1] =
                            Math.max(lengths[i+1][j], lengths[i][j+1]);

        StringBuffer sb = new StringBuffer();
        for (int x = firstString.length(), y = secondString.length(); x != 0 && y != 0; ) {
            if (lengths[x][y] == lengths[x-1][y])
                x--;
            else if (lengths[x][y] == lengths[x][y-1])
                y--;
            else {
                assert firstString.charAt(x-1) == secondString.charAt(y-1);
                sb.append(firstString.charAt(x-1));
                x--;
                y--;
            }
        }

        return sb.reverse().toString();
    }
}