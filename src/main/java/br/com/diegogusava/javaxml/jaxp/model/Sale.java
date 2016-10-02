package br.com.diegogusava.javaxml.jaxp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Sale {

    @XmlElementWrapper(name = "products")
    @XmlElement(name = "product")
    private List<Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return "Sale{" +
                "products=" + products +
                '}';
    }

    public void add(Product product) {
        this.products.add(product);
    }
}
