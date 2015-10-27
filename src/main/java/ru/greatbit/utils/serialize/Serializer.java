package ru.greatbit.utils.serialize;

/**
 * Created by azee on 5/12/14.
 */
public class Serializer {

    /**
     * Unmarshal an oject if we don't now if it is a json or an xml
     * @param data - String with serialised object
     * @param clazz - A class of object to be deserialised
     * @param <T> - Class of object
     * @return - Unmarshaled object
     * @throws Exception - Serialisation exceptions
     */
    public static <T>T unmarshal(String data, Class<T> clazz) throws Exception {
        try {
            return XmlSerializer.unmarshal(data, clazz);
        }
        catch (javax.xml.bind.UnmarshalException e){
            return JsonSerializer.unmarshal(data, clazz);
        }
    }
}
