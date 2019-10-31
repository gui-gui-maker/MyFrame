
package webService.adddt;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webService.adddt package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _JyDataNewJyResponse_QNAME = new QName("http://service.ws.cxf.zytx.com/", "jyDataNewJyResponse");
    private final static QName _JyDataNewJy_QNAME = new QName("http://service.ws.cxf.zytx.com/", "jyDataNewJy");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webService.adddt
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JyDataNewJyResponse }
     * 
     */
    public JyDataNewJyResponse createJyDataNewJyResponse() {
        return new JyDataNewJyResponse();
    }

    /**
     * Create an instance of {@link JyDataNewJy }
     * 
     */
    public JyDataNewJy createJyDataNewJy() {
        return new JyDataNewJy();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JyDataNewJyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.cxf.zytx.com/", name = "jyDataNewJyResponse")
    public JAXBElement<JyDataNewJyResponse> createJyDataNewJyResponse(JyDataNewJyResponse value) {
        return new JAXBElement<JyDataNewJyResponse>(_JyDataNewJyResponse_QNAME, JyDataNewJyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JyDataNewJy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.cxf.zytx.com/", name = "jyDataNewJy")
    public JAXBElement<JyDataNewJy> createJyDataNewJy(JyDataNewJy value) {
        return new JAXBElement<JyDataNewJy>(_JyDataNewJy_QNAME, JyDataNewJy.class, null, value);
    }

}
