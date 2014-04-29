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
        Map<T, T> dataMap = new HashMap<T, T>();
        for (T object : first){
            dataMap.put(object, object);
        }

        for (T object : second){
            dataMap.put(object, null);
        }

        List<T> resultObject = new LinkedList<T>();
        for (T object : dataMap.keySet()){
            resultObject.add(object);
        }
        return resultObject;
    }
}
