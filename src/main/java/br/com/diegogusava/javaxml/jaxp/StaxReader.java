package br.com.diegogusava.javaxml.jaxp;

import br.com.diegogusava.javaxml.jaxp.model.Product;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.events.XMLEvent;
import java.util.ArrayList;
import java.util.List;

public class StaxReader {

    public static void main(String[] args) throws Exception {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        final XMLEventReader events = factory.createXMLEventReader(SaxReader.class.getClassLoader().getResourceAsStream("sale.xml"));

        List<Product> products = new ArrayList<>();
        while (events.hasNext()) {
            final XMLEvent event = events.nextEvent();
            if (event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("product")) {

                Product product = createProduct(events);
                products.add(product);

            }
        }

        System.out.println(products);
    }

    private static Product createProduct(XMLEventReader events) throws Exception {
        Product product = new Product();
        while (events.hasNext()) {
            XMLEvent event = events.nextEvent();

            if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("product")) {
                break;
            }

            if (event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("name")) {
                event = events.nextEvent();
                product.setName(event.asCharacters().getData());
            }

            if (event.isStartElement() && event.asStartElement().getName().getLocalPart().equals("price")) {
                event = events.nextEvent();
                product.setPrice(Double.parseDouble(event.asCharacters().getData()));
            }

        }
        return product;
    }

}
