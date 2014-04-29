package ru.greatbit.utils.collection;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by azee on 4/29/14.
 */
public class ListUtils {

    /**
     * Merge lists
     * @param first - List<Object>
     * @param second - List<Object>
     * @return - List<Object>
     */
    public static <T> List<T> mergeLists(List<T> first, List<T> second){
        Map<T, T> dataMap = listToMap(first);

        for (T object : second){
            dataMap.put(object, object);
        }

        List<T> resultObject = new LinkedList<T>();
        for (T object : dataMap.keySet()){
            resultObject.add(object);
        }
        return resultObject;
    }

    /**
     * Get differences of lists
     * @param first - List<T>
     * @param second - List<T>
     * @param <T> - Object class
     * @return Difference object
     */
    public static <T>Difference getDiff(List<T> first, List<T> second){
        Map<T, T> firstMap = listToMap(first);
        Map<T, T> secondMap = listToMap(second);

        Difference difference = new Difference();

        for (T object : secondMap.keySet()){
            if (firstMap.get(object) == null){
                difference.getAdded().add(object);
            } else {
                difference.getEqual().add(object);
            }
        }

        for (T object : firstMap.keySet()){
            if (secondMap.get(object) == null){
                difference.getRemoved().add(object);
            }
        }

        return difference;
    }

    private static <T>Map<T, T> listToMap(List<T> input){
        Map<T, T> dataMap = new HashMap<T, T>();
        for (T object : input){
            dataMap.put(object, object);
        }
        return dataMap;
    }

}
