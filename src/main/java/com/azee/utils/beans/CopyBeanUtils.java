package com.azee.utils.beans;

import com.azee.utils.marshall.json.JsonMarshaller;
import com.azee.utils.marshall.json.JsonUnmarshaller;
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
     * @param source
     * @param <T>
     * @return
     * @throws Exception
     */
    public <T>T getCopy(Object source) throws Exception {
        String sourceString = jsonMarshaller.marshal(source);
        Class clazz = source.getClass();
        return (T) jsonUnmarshaller.unmarshal(sourceString, clazz);
    }
}
