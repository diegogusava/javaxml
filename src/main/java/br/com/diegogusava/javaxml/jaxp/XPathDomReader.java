package br.com.diegogusava.javaxml.jaxp;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * O XPath nos permite selecionar apenas uma parte do nosso documento, usando uma sintaxe bem parecida com a estrutura de pastas do nosso sistema operacional.
 * O que facilita a busca de dados do nosso documento.
 * <p>
 * Com a ajuda do XPath não precisamos adicionar diversos ifs no código, que lê o nosso arquivo XML. O que facilita muito o processo de filtrar
 * dados de um arquivo XML para a geração de diversos relatórios, por exemplo.
 * <p>
 *
 */
public class XPathDomReader {

    public static void main(String[] args) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setValidating(true);
        factory.setNamespaceAware(true);
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaLanguage", "http://www.w3.org/2001/XMLSchema");
        factory.setAttribute("http://java.sun.com/xml/jaxp/properties/schemaSource", XPathDomReader.class.getClassLoader().getResourceAsStream("sale.xsd"));
        final DocumentBuilder builder = factory.newDocumentBuilder();
        final Document document = builder.parse(XPathDomReader.class.getClassLoader().getResourceAsStream("sale.xml"));

        final XPath xPath = XPathFactory.newInstance().newXPath();

        //http://www.edankert.com/defaultnamespaces.html
        xPath.setNamespaceContext(new NamespaceContext() {
            @Override
            public String getNamespaceURI(String prefix) {
                if (prefix.equals("jx")) {
                    return "http://www.diegogusava.com/javaxml/schemas";
                }
                return null;
            }

            @Override
            public String getPrefix(String namespaceURI) {
                if (namespaceURI.equals("http://www.diegogusava.com/javaxml/schemas")) {
                    return "jx";
                }
                return null;
            }

            @Override
            public Iterator getPrefixes(String namespaceURI) {
                List<String> list = new ArrayList<>();
                if (namespaceURI.equals("http://www.diegogusava.com/javaxml/schemas")) {
                    list.add("jx");
                }
                return list.iterator();
            }
        });

        final XPathExpression expression = xPath.compile("/jx:sale/jx:products/jx:product");

        final NodeList products = (NodeList) expression.evaluate(document, XPathConstants.NODESET);
//        final NodeList products = document.getElementsByTagName("product");
        for (int i = 0; i < products.getLength(); i++) {
            final Element product = (Element) products.item(i);
            System.out.println(product.getElementsByTagNameNS("http://www.diegogusava.com/javaxml/schemas", "name")
                    .item(0).getTextContent());
        }
    }
}
