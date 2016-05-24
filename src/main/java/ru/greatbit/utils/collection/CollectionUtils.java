package ru.greatbit.utils.collection;

import ru.greatbit.utils.serialize.JsonSerializer;
import ru.greatbit.utils.string.StringUtils;

import java.util.*;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

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
        second.forEach(item -> dataMap.put(item, item));
        return dataMap.values().stream().collect(toList());
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
        return dataMap.values().stream().collect(toList());
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

        secondMap.entrySet().forEach(entry ->{
            V value = firstMap.get(entry.getKey());
            if (value == null){
                difference.getAdded().add(entry.getValue());
            } else {
                difference.getEqual().add(value);
            }
        });

        firstMap.entrySet().forEach(entry -> {
            V value = secondMap.get(entry.getKey());
            if (value == null){
                difference.getRemoved().add(entry.getValue());
            }
        });
        return difference;
    }

    /**
     * Load a list to a map
     * @param input - List
     * @param <V> - Object class
     * @return - Map
     */
    public static <V> Map<V, V> listToMap(List<V> input){
        Map<V, V> dataMap = new LinkedHashMap<V, V>();
        input.forEach(value -> dataMap.put(value, value));
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
        Map<String, T> dataMap = new LinkedHashMap<String, T>();
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
        return new ArrayList(new LinkedHashSet<T>(values));
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

        Map<T, Integer> elementsIndexMap = new HashMap<>();
        for(int i = 0; i < elements.size(); i++){
            elementsIndexMap.put(elements.get(i), i);
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

            Integer currElementIndex = elementsIndexMap.get(currElement);
            Integer currOrderIndexInElements = elementsIndexMap.get(currOrder);
            currOrderIndexInElements = currOrderIndexInElements == null ? elements.size() : currOrderIndexInElements;

            if (currElementIndex.compareTo(currOrderIndexInElements) < 0){
                merged.add(currElement);
                i++;
            }
            if (currOrderIndexInElements.compareTo(currElementIndex) < 0 || i >= elements.size()){
                if (merged.contains(currOrder)){
                    merged.remove(currOrder);
                }
                if (elementsSet.contains(currOrder)){
                    merged.add(currOrder);
                }
                j++;
            }
            if (currElementIndex.compareTo(currOrderIndexInElements) == 0){
                merged.add(currElement);
                i++;
                j++;
            }
        }
        return new ArrayList<>(merged);
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
