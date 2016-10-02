package br.com.diegogusava.javaxml.jaxp;

import br.com.diegogusava.javaxml.jaxp.model.Product;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class SaleHandler extends DefaultHandler {

    private List<Product> products = new ArrayList<>();
    private Product product;
    private StringBuilder sb;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equals("product")) {
            product = new Product();

        }
        sb = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals("product")) {
            products.add(product);
        } else if (qName.equals("name")) {
            product.setName(sb.toString());
        } else if (qName.equals("price")) {
            product.setPrice(Double.parseDouble(sb.toString()));
        }

    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        sb.append(ch, start, length);
    }

    public List<Product> getProducts() {
        return products;
    }
}
