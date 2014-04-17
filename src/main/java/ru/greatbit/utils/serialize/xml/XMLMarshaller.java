package ru.greatbit.utils.serialize.xml;

import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;

/**
 * Created by azee on 4/17/14.
 */
@Service
public class XMLMarshaller {

    /**
     * Marhsal an object to string
     * @param instance
     * @return
     * @throws IOException
     * @throws JAXBException
     */
    public String marshal(Object instance) throws IOException, JAXBException {
        JAXBContext contextA = JAXBContext.newInstance(instance.getClass());
        Marshaller marshaller = contextA.createMarshaller();
        StringWriter writer = new StringWriter();
        marshaller.marshal(instance, writer);
        return writer.toString();
    }
}
