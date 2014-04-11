package com.azee.utils.marshall.json;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.springframework.stereotype.Service;

/**
 * Created by azee on 4/11/14.
 */
@Service
public class JsonUnmarshaller {

    public <T>T unmarshal(String data, Class<T> objectClass) throws Exception{
        return unmarshal(data, "", objectClass);
    }

    public <T>T unmarshal(String data, String rootName, Class<T> objectClass) throws Exception{
        if (rootName != null && !"".equals(rootName)){
            data = "{\"" + rootName + "\": " + data + "}";
        }
        ObjectMapper mapper = new ObjectMapper();
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
        mapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
        return (T)mapper.readValue(data, objectClass);
    }
}
