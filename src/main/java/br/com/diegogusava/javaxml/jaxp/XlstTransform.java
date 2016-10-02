package br.com.diegogusava.javaxml.jaxp;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.InputStream;

/**
 * Arquivos XSLT são arquivos que nos ajudam a transformar arquivos XML em outros formatos, como o HTML, facilitando essa conversão. Porém não é muito utilizado atualmente no mercado,
 * pois geralmente arquivos XSLT crescem muito rápido, ficando difíceis de manter.
 *
 * Arquivos XSLT podem ser úteis quando queremos pegar todos os dados da nossa venda e
 * mostrar no navegador para o usuário, por exemplo.
 */
public class XlstTransform {

    public static void main(String[] args) throws Exception {

        final InputStream xml = XlstTransform.class.getClassLoader().getResourceAsStream("sale.xml");
        final InputStream xsl = XlstTransform.class.getClassLoader().getResourceAsStream("sale.xsl");

        Transformer transformer = TransformerFactory.newInstance()
                .newTransformer(new StreamSource(xsl));

        transformer.transform(new StreamSource(xml), new StreamResult("src/main/resources/sale.html"));

    }


}
