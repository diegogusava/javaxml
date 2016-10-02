package br.com.diegogusava.javaxml.jaxp;

import br.com.diegogusava.javaxml.jaxp.model.Product;
import br.com.diegogusava.javaxml.jaxp.model.Sale;
import com.sun.xml.internal.bind.marshaller.NamespacePrefixMapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.io.StringWriter;

public class JaxbReader {

    public static void main(String[] args) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Sale.class);
        transformXmlToObject(context);
        transformObjectToXml(context);
    }

    private static void transformObjectToXml(JAXBContext context) throws JAXBException {

        Sale sale = new Sale();
        sale.add(new Product("Java Book", 29.90));
        sale.add(new Product("Scala Book", 29.90));
        sale.add(new Product("Php Book", 29.90));

        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE );
        marshaller.setProperty(Marshaller.JAXB_ENCODING, "ISO-8859-1");
        StringWriter sw = new StringWriter();
        marshaller.marshal(sale, sw);
        System.out.println(sw.toString());
    }

    private static void transformXmlToObject(JAXBContext context) throws JAXBException {
        final Unmarshaller unmarshaller = context.createUnmarshaller();
        final InputStream inputStream = JaxbReader.class.getClassLoader()
                .getResourceAsStream("sale.xml");
        Sale sale = (Sale) unmarshaller.unmarshal(inputStream);
        System.out.println(sale);
    }

}
