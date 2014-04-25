package ru.greatbit.utils.beans;

import ru.greatbit.utils.serialize.json.JsonUnmarshaller;
import ru.greatbit.utils.serialize.json.JsonMarshaller;

/**
 * Created by azee on 4/11/14.
 */
public class CopyBeanUtils {

    /**
     * Get a copy of an object
     * @param source - a source object
     * @param <T> - class of the object
     * @return <T> - a copy of the object
     * @throws Exception
     */
    public static <T>T getCopy(Object source) throws Exception {
        String sourceString = JsonMarshaller.marshal(source);
        Class clazz = source.getClass();
        return (T) JsonUnmarshaller.unmarshal(sourceString, clazz);
    }
}
