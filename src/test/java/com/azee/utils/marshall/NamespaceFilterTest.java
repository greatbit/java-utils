package com.azee.utils.marshall;

import com.azee.utils.beans.NamespaceBeanExample;
import com.azee.utils.marshall.utils.NamespaceFilter;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.sax.SAXSource;
import java.io.*;

import static junit.framework.Assert.assertNotNull;


/**
 * Created by azee on 4/10/14.
 */
public class NamespaceFilterTest {

    private String marshalledBean = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<namespaceBeanExample><value>1</value></namespaceBeanExample>";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getExampleBeanTest() throws Exception {
                NamespaceBeanExample beanExample;

        //Unmarshaller
        JAXBContext contextObj = JAXBContext.newInstance(NamespaceBeanExample.class);
        Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();

        //Adding namespaces
        BufferedReader rd = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(marshalledBean.getBytes("UTF-8"))));

        //Create an XMLReader to use with our filter
        XMLReader reader = XMLReaderFactory.createXMLReader();

        //Create the filter (to add namespace) and set the xmlReader as its parent.
        NamespaceFilter inFilter = new NamespaceFilter("beans.utils.azee.com", true);
        inFilter.setParent(reader);

        //Prepare the input
        InputSource is = new InputSource(rd);

        //Create a SAXSource specifying the filter
        SAXSource source = new SAXSource(inFilter, is);

        //Do unmarshalling
        beanExample = (NamespaceBeanExample)unmarshallerObj.unmarshal(source);

        rd.close();
        assertNotNull(beanExample);
    }

    @Test
    public void getExampleBeanTestNegative() throws Exception {
        exception.expect(javax.xml.bind.UnmarshalException.class);

        //Unmarshaller
        JAXBContext contextObj = JAXBContext.newInstance(NamespaceBeanExample.class);
        Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();

        //Do unmarshalling
        NamespaceBeanExample beanExample = (NamespaceBeanExample)unmarshallerObj.unmarshal(new InputStreamReader(new ByteArrayInputStream(marshalledBean.getBytes("UTF-8"))));
    }
}
