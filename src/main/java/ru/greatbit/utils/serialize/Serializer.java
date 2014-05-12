package ru.greatbit.utils.serialize;

/**
 * Created by azee on 5/12/14.
 */
public class Serializer {

    /**
     * Unmarshal an oject if we don't now if it is a json or an xml
     * @param data
     * @param clazz
     * @param <T>
     * @return
     * @throws Exception
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
