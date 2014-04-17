package ru.greatbit.utils.serialize.json;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.springframework.stereotype.Service;

/**
 * Created by azee on 4/11/14.
 */
@Service
public class JsonUnmarshaller {

    /**
     * Unmarshall an object without root element
     * @param data - String - marshalled object to unmarshal
     * @param objectClass - class of the object
     * @param <T> - class of the object
     * @return <T> - unmarshalled object
     * @throws Exception
     */
    public <T>T unmarshal(String data, Class<T> objectClass) throws Exception{
        return unmarshal(data, "", objectClass);
    }

    /**
     * Unmarshall object with root element
     * @param data - String - marshalled object to unmarshal
     * @param rootName - String - additional root name to add
     * @param objectClass - class of the object
     * @param <T> - class of the object
     * @return <T> - unmarshalled object
     * @throws Exception
     */
    public <T>T unmarshal(String data, String rootName, Class<T> objectClass) throws Exception{
        if (rootName != null && !"".equals(rootName)){
            data = "{\"" + rootName + "\": " + data + "}";
        }
        ObjectMapper mapper = new ObjectMapper();
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
        mapper.getDeserializationConfig().withAnnotationIntrospector(introspector);
        return (T)mapper.readValue(data, objectClass);
    }
}
