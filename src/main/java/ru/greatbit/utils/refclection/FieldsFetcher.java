package ru.greatbit.utils.refclection;

import ru.greatbit.utils.collection.ListUtils;
import ru.greatbit.utils.exceptions.NullObjectException;

import java.lang.reflect.Field;
import java.util.List;

/**
 * Created by azee on 17.08.14.
 */
public class FieldsFetcher {

    /**
     * Returns a value fetched from the provided object by point delimited path
     * @param object
     * @param path
     * @return
     * @throws NullObjectException
     */
    public static Object findValue(Object object, List<String> path) throws NullObjectException, IllegalAccessException, InstantiationException {
        if (object == null){
            throw new NullObjectException();
        }
        return findValue(object, object.getClass(), path);
    }

    /**
     * Returns a value fetched from the provided object of provided class by point delimited path
     * @param object
     * @param clazz
     * @param path
     * @return
     * @throws NullObjectException
     */
    public static Object findValue(Object object, Class clazz, List<String> path) throws NullObjectException, InstantiationException, IllegalAccessException {
        if (object == null){
            throw new NullObjectException();
        }
        if (path.size() != 0){
            Object nextObject = getObjectFromField(object, clazz, path.get(0).trim());
            try {
                path.remove(0);
            } catch (UnsupportedOperationException e){
                path = ListUtils.removeByIndex(path, 0);
            }

            if (nextObject != null){
                return findValue(nextObject, clazz, path);
            }
        }
        return object;
    }

    /**
     * Fetch an object from the parent object by field name
     * @param obj
     * @param clazz
     * @param fieldName
     * @return
     */
    public static Object getObjectFromField(Object obj, Class clazz, String fieldName){
        Object result;
        Field field;
        try {
            field = clazz.getDeclaredField(fieldName);
        } catch (NoSuchFieldException e) {
            return null;
        }

        boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            result = field.get(obj);
            field.setAccessible(accessible);
            return result;

        } catch (IllegalAccessException e) {
            return null;
        }
    }
}
