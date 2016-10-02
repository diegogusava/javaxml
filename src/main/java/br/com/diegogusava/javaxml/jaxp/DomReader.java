package br.com.diegogusava.javaxml.jaxp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

public class DomReader {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", DomReader.class.getClassLoader().getResourceAsStream("sale.xsd"));
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse(DomReader.class.getClassLoader().getResourceAsStream("sale.xml"));

        final NodeList products = document.getElementsByTagName("product");
        for (int i = 0; i < products.getLength(); i++) {
            final Element product = (Element) products.item(i);
            System.out.println(product.getElementsByTagName("name").item(0).getTextContent());
        }
    }
}
