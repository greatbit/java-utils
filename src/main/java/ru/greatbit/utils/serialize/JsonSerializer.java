package ru.greatbit.utils.serialize;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

/**
 * Created by azee on 4/11/14.
 */
public class JsonSerializer {

    /**
     * Marshall an object without root
     * @param object - an object to marshall
     * @param <T> - object class
     * @return String - marshalled object
     * @throws Exception
     */
    public static <T> String marshal(T object) throws Exception{
        return marshal(object, "");
    }


    /**
     * Marshall an object
     * Object can be wrapped into root element if provided
     * @param object - an object to marshall
     * @param rootName - String root name to wrap
     * @param <T> - object class
     * @return - String - marshalled object
     * @throws Exception
     */
    public static <T> String marshal(T object, String rootName) throws Exception{
        String result;
        ObjectMapper mapper = new ObjectMapper();
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
        mapper.getDeserializationConfig().withAnnotationIntrospector(introspector);
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        result = mapper.writeValueAsString(object);
        if (rootName != null && !"".equals(rootName)){
            result = "{\"" + rootName + "\": " + result + "}";
        }
        return result;
    }

    /**
     * Unmarshall an object without root element
     * @param data - String - marshalled object to unmarshal
     * @param objectClass - class of the object
     * @param <T> - class of the object
     * @return <T> - unmarshalled object
     * @throws Exception
     */
    public static <T>T unmarshal(String data, Class<T> objectClass) throws Exception{
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
    public static <T>T unmarshal(String data, String rootName, Class<T> objectClass) throws Exception{
        if (rootName != null && !"".equals(rootName)){
            data = "{\"" + rootName + "\": " + data + "}";
        }
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
        mapper.getDeserializationConfig().withAnnotationIntrospector(introspector);
        return (T)mapper.readValue(data, objectClass);
    }
}
