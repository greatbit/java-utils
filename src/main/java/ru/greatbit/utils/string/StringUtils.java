package ru.greatbit.utils.string;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: azee
 * Date: 1/10/14
 */
public class StringUtils {

    /**
     * Will be used for transliteration
     */
    private static final Map<String, String> letters = new HashMap<String, String>();
    static {
        letters.put("А", "A");
        letters.put("Б", "B");
        letters.put("В", "V");
        letters.put("Г", "G");
        letters.put("Д", "D");
        letters.put("Е", "E");
        letters.put("Ё", "E");
        letters.put("Ж", "ZH");
        letters.put("З", "Z");
        letters.put("И", "I");
        letters.put("Й", "I");
        letters.put("К", "K");
        letters.put("Л", "L");
        letters.put("М", "M");
        letters.put("Н", "N");
        letters.put("О", "O");
        letters.put("П", "P");
        letters.put("Р", "R");
        letters.put("С", "S");
        letters.put("Т", "T");
        letters.put("У", "U");
        letters.put("Ф", "F");
        letters.put("Х", "H");
        letters.put("Ц", "C");
        letters.put("Ч", "CH");
        letters.put("Ш", "SH");
        letters.put("Щ", "SH");
        letters.put("Ъ", "'");
        letters.put("Ы", "Y");
        letters.put("Ъ", "'");
        letters.put("Э", "E");
        letters.put("Ю", "U");
        letters.put("Я", "YA");
        letters.put("а", "a");
        letters.put("б", "b");
        letters.put("в", "v");
        letters.put("г", "g");
        letters.put("д", "d");
        letters.put("е", "e");
        letters.put("ё", "e");
        letters.put("ж", "zh");
        letters.put("з", "z");
        letters.put("и", "i");
        letters.put("й", "i");
        letters.put("к", "k");
        letters.put("л", "l");
        letters.put("м", "m");
        letters.put("н", "n");
        letters.put("о", "o");
        letters.put("п", "p");
        letters.put("р", "r");
        letters.put("с", "s");
        letters.put("т", "t");
        letters.put("у", "u");
        letters.put("ф", "f");
        letters.put("х", "h");
        letters.put("ц", "c");
        letters.put("ч", "ch");
        letters.put("ш", "sh");
        letters.put("щ", "sh");
        letters.put("ъ", "'");
        letters.put("ы", "y");
        letters.put("ъ", "'");
        letters.put("э", "e");
        letters.put("ю", "u");
        letters.put("я", "ya");
    }

    /**
     * Transliterate string from Russian chars to English
     * @param text - Russian text
     * @return - Transliterated text
     */
    public static String translit(String text) {
        StringBuilder sb = new StringBuilder(text.length());
        for (int i = 0; i < text.length(); i++) {
            String l = text.substring(i, i+1);
            if (letters.containsKey(l)) {
                sb.append(letters.get(l));
            }
            else {
                sb.append(l);
            }
        }
        return sb.toString();
    }

    /**
     * Returns an empty string if null - helps to avoid NPE
     * @param input - String to verify
     * @return String - Empty string or string value if not null
     */
    public static String emptyIfNull(String input){
        return input == null ? "" : input;
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
        stringList.forEach(string -> sb.append(string).append(","));
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