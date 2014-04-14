package ru.greatbit.utils.beans;

import ru.greatbit.utils.marshall.json.JsonUnmarshaller;
import ru.greatbit.utils.marshall.json.JsonMarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by azee on 4/11/14.
 */
@Service
public class CopyBeanUtils {

    @Autowired
    JsonMarshaller jsonMarshaller;

    @Autowired
    JsonUnmarshaller jsonUnmarshaller;

    /**
     * Get a copy of an object
     * @param source - a source object
     * @param <T> - class of the object
     * @return <T> - a copy of the object
     * @throws Exception
     */
    public <T>T getCopy(Object source) throws Exception {
        String sourceString = jsonMarshaller.marshal(source);
        Class clazz = source.getClass();
        return (T) jsonUnmarshaller.unmarshal(sourceString, clazz);
    }
}
