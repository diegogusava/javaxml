package br.com.diegogusava.javaxml.jaxp;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class SaxReader {

    public static void main(String[] args) throws Exception {
        XMLReader xmlReader = XMLReaderFactory.createXMLReader();
        final SaleHandler handler = new SaleHandler();
        xmlReader.setContentHandler(handler);
        xmlReader.parse(new InputSource(SaxReader.class.getClassLoader().getResourceAsStream("sale.xml")));
        System.out.println(handler.getProducts());
    }

}
