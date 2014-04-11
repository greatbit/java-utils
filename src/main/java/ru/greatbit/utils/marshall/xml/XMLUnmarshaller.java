package ru.greatbit.utils.marshall.xml;

import ru.greatbit.utils.marshall.utils.NamespaceFilter;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.sax.SAXSource;
import java.io.*;
import java.lang.annotation.Annotation;

/**
 * Created by azee on 4/10/14.
 */
@Service
public class XMLUnmarshaller {

    /**
     * Unmarshall an object event if the string doesn't contain
     * namespaces on root element from String
     * @param data
     * @param clazz
     * @param <T>
     * @return
     * @throws JAXBException
     * @throws IOException
     * @throws SAXException
     */
    public <T>T unmarshall(String data, Class<T> clazz) throws JAXBException, IOException, SAXException {
        try {
            return unmarshall(new ByteArrayInputStream(data.getBytes("UTF-8")), clazz);
        }
        catch (javax.xml.bind.UnmarshalException e){
            return unmarshallNamespaceAware(new ByteArrayInputStream(data.getBytes("UTF-8")), clazz);
        }
    }

    /**
     * Unmarshall an object event if the string doesn't contain
     * namespaces on root element from InputStream
     * @param data
     * @param clazz
     * @param <T>
     * @return
     * @throws JAXBException
     * @throws IOException
     * @throws SAXException
     */
    public <T>T unmarshall(InputStream data, Class<T> clazz) throws JAXBException, IOException, SAXException {
        JAXBContext contextObj = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();
        InputStreamReader ir = new InputStreamReader(data);
        return (T)unmarshallerObj.unmarshal(ir);
    }

    /**
     * Unmarshall an object event if the string doesn't contain
     * namespaces on root element from InputStream
     * @param data
     * @param clazz
     * @param <T>
     * @return
     * @throws JAXBException
     * @throws IOException
     * @throws SAXException
     */
    public <T>T unmarshallNamespaceAware(InputStream data, Class<T> clazz) throws JAXBException, IOException, SAXException {
        T result;

        //Unmarshaller
        JAXBContext contextObj = JAXBContext.newInstance(clazz);
        Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();

        //Adding namespaces
        BufferedReader rd = new BufferedReader(new InputStreamReader(data));

        //Create an XMLReader to use with our filter
        XMLReader reader = XMLReaderFactory.createXMLReader();

        //Create the filter (to add namespace) and set the xmlReader as its parent.
        NamespaceFilter inFilter = new NamespaceFilter(getXMLRootNamespace(clazz), true);

        inFilter.setParent(reader);

        //Prepare the input
        InputSource is = new InputSource(rd);

        //Create a SAXSource specifying the filter
        SAXSource source = new SAXSource(inFilter, is);

        //Do unmarshalling
        result = (T)unmarshallerObj.unmarshal(source);

        rd.close();

        return result;
    }

    /**
     * Return the namespace value of @XmlRootElement
     * @param clazz
     * @return
     */
    public String getXMLRootNamespace(Class clazz) {
        String result = "";
        Annotation annotation = clazz.getAnnotation(XmlRootElement.class);
        //If XMLRoot annotation applied then use it's namespace
        if (annotation != null){
            try {
                result = annotation.annotationType().getMethod("namespace").invoke(annotation).toString();
            } catch (Exception e) {
                result = "";
            }
        }

        //If found default namespace value fromn XMLRootElement namespace{
        if ("##default".equals(result)){
            result = "";
        }

        //If no XMLRoot annotation applied then use package name
        if ("".equals(result) || "##default".equals(result)){
            String[] packageNames = clazz.getPackage().getName().split("\\.");
            for (int i = packageNames.length - 1; i >= 0; i--){
                result = result + "." + packageNames[i];
            }
            result = result.substring(1);
        }
        return result;
    }

}
