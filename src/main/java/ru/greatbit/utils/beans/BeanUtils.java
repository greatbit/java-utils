package ru.greatbit.utils.beans;

import ru.greatbit.utils.serialize.JsonSerializer;

/**
 * Created by azee on 15.03.16.
 */
public class BeanUtils {
    /**
     * Compare 2 beans by value using serialization to json
     * @param bean1 - first bean to compare
     * @param bean2 - second bean to compare
     * @return - true (equal) or false (not equal)
     * @throws Exception - Serialize exceptions
     */
    public static boolean equalByVal(Object bean1, Object bean2) throws Exception {
        //If both of them are null or the same object - no need to serialize
        if (bean1 == bean2){
            return true;
        }

        //Avoid NPE
        if (bean1 == null || bean2 == null){
            return false;
        }

        //Serialize and compare
        String bean1String = JsonSerializer.marshal(bean1);
        String bean2String = JsonSerializer.marshal(bean2);
        return bean1String.equals(bean2String);
    }

    /**
     * Get a copy of an object
     * @param source - a source object
     * @param <T> - class of the object
     * @return - a copy of the object
     * @throws Exception - Serialization Exceptions
     */
    public static <T>T getCopy(Object source) throws Exception {
        String sourceString = JsonSerializer.marshal(source);
        Class clazz = source.getClass();
        return (T) JsonSerializer.unmarshal(sourceString, clazz);
    }
}
