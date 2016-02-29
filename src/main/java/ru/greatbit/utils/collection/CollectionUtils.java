package ru.greatbit.utils.collection;

import ru.greatbit.utils.serialize.JsonSerializer;
import ru.greatbit.utils.string.StringUtils;

import java.util.*;

/**
 * Created by azee on 4/29/14.
 */
public class CollectionUtils {

    /**
     * Merge lists
     * @param first - List
     * @param second - List
     * @param <T> - Object class
     * @return - List
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
     * Merge lists of object
     * Objects are compared by serialisation value
     * @param first - The first list of objects
     * @param second - The second list of objects
     * @return - Merged list of objects
     * @param <T> - Object class
     * @throws Exception - Serialization exceptions
     */
    public static <T> List<T> mergeListsByValue(List<T> first, List<T> second) throws Exception {
        Map<String, T> dataMap = listToMD5Map(first);

        for (T object : second){
            dataMap.put(StringUtils.getMd5String(JsonSerializer.marshal(object)), object);
        }

        List<T> resultObject = new LinkedList<T>();
        for (String key : dataMap.keySet()){
            resultObject.add(dataMap.get(key));
        }
        return resultObject;
    }



    /**
     * Get differences of lists
     * Objects should override hashCode and equals so they could be
     * compared in HashMap to find differences
     * @param first - List
     * @param second - List
     * @param <T> - Object class
     * @return Difference object
     */
    public static <T>Difference getDiff(List<T> first, List<T> second){
        return getDiff(listToMap(first), listToMap(second));
    }

    /**
     * Get differences of lists
     * Uses serialised objects md5 - it will work slower
     * but can process objects if hashCode and equals can't be overridden
     * @param first - first list
     * @param second - second list
     * @return - Difference object
     * @param <T> - Object class
     * @throws Exception - Serialization exception
     */
    public static <T>Difference getDiffAnyObject(List<T> first, List<T> second) throws Exception {
        return getDiff(listToMD5Map(first), listToMD5Map(second));
    }

    /**
     * Return a difference from 2 maps
     * @param firstMap - First map do diff
     * @param secondMap - Second map do diff
     * @param <K> - Key class
     * @param <V> - Value class
     * @return - Difference object
     */
    private static <K, V>Difference getDiff(Map<K, V> firstMap, Map<K, V> secondMap){
        Difference difference = new Difference();

        for (K object : secondMap.keySet()){
            V value = firstMap.get(object);
            if (value == null){
                difference.getAdded().add(secondMap.get(object));
            } else {
                difference.getEqual().add(value);
            }
        }

        for (K object : firstMap.keySet()){
            V value = secondMap.get(object);
            if (value == null){
                difference.getRemoved().add(firstMap.get(object));
            }
        }
        return difference;
    }

    /**
     * Load a list to a map
     * @param input - List
     * @param <V> - Object class
     * @return - Map
     */
    public static <V> Map<V, V> listToMap(List<V> input){
        Map<V, V> dataMap = new HashMap<V, V>();
        for (V object : input){
            dataMap.put(object, object);
        }
        return dataMap;
    }

    /**
     * Load a list to a map, use serialised objects md5 - it will work slower
     * but can process objects if hashCode and equals can't be overridden
     * @param input - List of objects
     * @return - Map of objects by md5 key
     * @param <T> - Object class
     * @throws Exception - Serialization exceptions
     */
    public static <T> Map<String, T> listToMD5Map(List<T> input) throws Exception {
        Map<String, T> dataMap = new HashMap<String, T>();
        for (T object : input){
            dataMap.put(StringUtils.getMd5String(JsonSerializer.marshal(object)), object);
        }
        return dataMap;
    }

    /**
     * Used to remove values from lists that don't support remove method
     * @param input - List
     * @param index - Index to remove
     * @param <T> - Object class
     * @return - Result list with removed item
     */
    public static <T> List<T> removeByIndex(List<T> input, int index) {
        List<T> result = new LinkedList<T>(input);
        result.remove(index);

        //Doing that to keep original input class
        if (input.getClass().getName().equals("java.util.Arrays$ArrayList")){
            input = (List<T>) Arrays.asList(result.toArray());
        } else {
            input.clear();
            input.addAll(result);
        }
        return input;
    }

    /**
     * Remove duplicate values of list from map
     * @param values - Map of values
     * @param <T> - Object class
     * @param <K> - Key
     * @return - Result Map without duplcates
     */
    public static <T, K> Map<K, List<T>> removeDuplicateValues(Map<K, List<T>> values) {
        if (values == null){
            return values;
        }
        Map<K, List<T>> newValues = new LinkedHashMap<K, List<T>>();
        for (K key : values.keySet()) {
            newValues.put(key, removeDuplicateValues(values.get(key)));
        }
        return newValues;
    }

    /**
     * Remove duplicate values of list from list
     * @param values - List of values to filter
     * @param <T> - Object class
     * @return - Filtered list
     */
    public static <T> List<T> removeDuplicateValues(List<T> values) {
        if (values == null){
            return values;
        }
        return new ArrayList(new HashSet<T>(values));
    }


    /**
     * Reorder a list of elements by another list. Trying to keep absolute order of initial list
     * but reorder regarding to provided relative order list.
     * E.g. initial was [1, 2, 3, 4, 5] - calling reorder with list [2, 5, 4] will generate list
     * [1, 2, 3, 5, 4]
     * @param elements - initial list
     * @param order - list describing relative order
     * @param <T> - Class of comparable object
     * @return - new reordered list
     */
     public static <T extends Comparable> List<T> reorder(List<T> elements, List<T> order){
        if (order.size() == 0){
            return elements;
        }
        if (elements.size() == 0){
            return order;
        }
        Set<T> merged = new LinkedHashSet<>();
        Set<T> elementsSet = new HashSet<>(elements);
        int i = 0;
        int j = 0;
        T currElement = elements.get(i);
        T currOrder = order.get(j);
        while(i < elements.size() || j < order.size()){
            if (j >= order.size()){
                merged.addAll(elements.subList(i, elements.size()));
                break;
            }
            currElement = i < elements.size() ? elements.get(i) : currElement;
            currOrder = j < order.size() ? order.get(j) : currOrder;
            if (currElement.compareTo(currOrder) < 0){
                merged.add(currElement);
                i++;
            }
            if (currOrder.compareTo(currElement) < 0 || i >= elements.size()){
                if (merged.contains(currOrder)){
                    merged.remove(currOrder);
                }
                if (elementsSet.contains(currOrder)){
                    merged.add(currOrder);
                }
                j++;
            }
            if (currElement.compareTo(currOrder) == 0){
                merged.add(currElement);
                i++;
                j++;
            }
        }
        return new ArrayList<>(merged);
    }

    /**
     * Compare objects values from map by provided keys
     * @param o1
     * @param o2
     * @param orderMap
     * @param <T>
     * @return
     */
    private static <T extends Comparable>int compare(T o1, T o2, Map<T, Long> orderMap) {
        Long order1 = orderMap.get(o1);
        Long order2 = orderMap.get(o2);
        if (order1 != null && order2 != null){
            return order1.compareTo(order2);
        }
        return o1.compareTo(o2);
    }

    /**
     * Swap two elements in list
     * @param elements - list of elements
     * @param i - index of first element
     * @param j - index of second element
     * @param <T> - elements class
     */
    public static <T extends Comparable> void swap(List<T> elements, int i, int j) {
        T buff = elements.get(j);
        elements.set(j, elements.get(i));
        elements.set(i, buff);
    }



}
