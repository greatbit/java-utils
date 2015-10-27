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
     * @return String - Empty string or string value if not null
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
     * @return result String - A list serialised in string
     * @param <T> - Objects class un list
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
     * @return result String -  A list serialised in string without spaces
     * @param <T> - Objects class un list
     */
    public static <T>String listAsStringNoSpaces(List<T> stringList){
        StringBuffer sb = new StringBuffer();
        for (T obj : stringList){
            sb.append(obj).append(",");
        }
        return removeTailing(sb.toString(), ",").trim();
    }

    /**
     * Get an MD5 sequence from a string
     * @param input - String to encode
     * @return - md5 String
     * @throws NoSuchAlgorithmException - NoSuchAlgorithmException
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
     * Add a string to list if it is not represented in list yet
     * @param toAdd - String - entry to add
     * @param strings - List - a list to update
     * @return - A list if unique strings
     */
    public static List<String> addUniqueString(String toAdd, List<String> strings){
        toAdd = emptyIfNull(toAdd);
        if (!strings.contains(toAdd)){
            strings.add(toAdd);
        }
        return strings;
    }

    /**
     * Find out if all of provided string parts are present in the source string
     * @param source - A String to search substrings in
     * @param toSearch - an array of possible substrings
     * @return - Boolean - if a list contains all of provided strings
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
     * @return - Boolean - if a list contains any of provided strings
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
     * @param source - Source to be cleared
     * @param toRemove - Item to remove
     * @return - Filtered value
     */
    public static String removeTailing(String source, String toRemove){
        if (toRemove == null){
            return source;
        }
        source = source.trim();
        int toRemoveLength = toRemove.length();
        int sourceRemoveLengthDiff = source.length() - toRemoveLength;
        if (source.length() >= toRemoveLength && toRemove.equals(source.substring(sourceRemoveLengthDiff))) {
            source = source.substring(0, sourceRemoveLengthDiff);
        }
        return source;
    }

    /**
     * Remove heading string (e.g. comma or slash)
     * @param source - Source to be cleared
     * @param toRemove - Item to remove
     * @return - Filtered value
     */
    public static String removeHeading(String source, String toRemove){
        if (toRemove == null){
            return source;
        }
        source = source.trim();
        int toRemoveLength = toRemove.length();
        if (source.length() >= toRemoveLength && toRemove.equals(source.substring(0, toRemoveLength))) {
            source = source.substring(toRemoveLength, source.length());
        }
        return source;
    }

    /**
     * Determine the longest common subsequence between the two strings
     * @param firstString - First string to look in
     * @param secondString - Second string to look for
     * @return - Longest Common Subsequence
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