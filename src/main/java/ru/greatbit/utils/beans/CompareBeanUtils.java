package ru.greatbit.utils.beans;

import ru.greatbit.utils.serialize.JsonSerializer;

/**
 * Created by azee on 5/5/14.
 */
public class CompareBeanUtils {

    /**
     * Compare 2 beans by value using serialization to json
     * @param bean1
     * @param bean2
     * @return
     * @throws Exception
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
}
