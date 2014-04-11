package com.azee.utils.marshall.json;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;
import org.springframework.stereotype.Service;

/**
 * Created by azee on 4/11/14.
 */
@Service
public class JsomMarshaller {
    public <T> String marshal(T object, String rootName) throws Exception{
        String result;
        ObjectMapper mapper = new ObjectMapper();
        AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
        mapper.getSerializationConfig().setAnnotationIntrospector(introspector);
        result = mapper.writeValueAsString(object);
        if (rootName != null && !"".equals(rootName)){
            result = "{\"" + rootName + "\": " + result + "}";
        }
        return result;
    }
}
