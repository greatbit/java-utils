package ru.greatbit.utils.serialize.xml;

import ru.greatbit.utils.beans.BeanWithNamespaceExample;
import ru.greatbit.utils.serialize.utils.NamespaceFilter;
import org.junit.Assert;
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
import static org.hamcrest.core.Is.is;


/**
 * Created by azee on 4/10/14.
 */
public class NamespaceFilterTest {

    private String marshalledBean = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>" +
            "<beanExample><value>1</value></beanExample>";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void getExampleBeanTest() throws Exception {
        BeanWithNamespaceExample beanExample;

        //Unmarshaller
        JAXBContext contextObj = JAXBContext.newInstance(BeanWithNamespaceExample.class);
        Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();

        //Adding namespaces
        BufferedReader rd = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(marshalledBean.getBytes("UTF-8"))));

        //Create an XMLReader to use with our filter
        XMLReader reader = XMLReaderFactory.createXMLReader();

        //Create the filter (to add namespace) and set the xmlReader as its parent.
        NamespaceFilter inFilter = new NamespaceFilter("beans.utils.greatbit.ru", true);
        inFilter.setParent(reader);

        //Prepare the input
        InputSource is = new InputSource(rd);

        //Create a SAXSource specifying the filter
        SAXSource source = new SAXSource(inFilter, is);

        //Do unmarshalling
        beanExample = (BeanWithNamespaceExample)unmarshallerObj.unmarshal(source);

        rd.close();
        assertNotNull(beanExample);
        Assert.assertThat("Wrong bean value", beanExample.getValue(), is(1));
    }


    @Test
    public void getExampleBeanTestNegative() throws Exception {
        exception.expect(javax.xml.bind.UnmarshalException.class);

        //Unmarshaller
        JAXBContext contextObj = JAXBContext.newInstance(BeanWithNamespaceExample.class);
        Unmarshaller unmarshallerObj = contextObj.createUnmarshaller();

        //Do unmarshalling
        BeanWithNamespaceExample beanExample = (BeanWithNamespaceExample)unmarshallerObj.unmarshal(new InputStreamReader(new ByteArrayInputStream(marshalledBean.getBytes("UTF-8"))));
    }
}
