
package webService.regularInspect;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the webService.regularInspect package. 
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

    private final static QName _JyDataJyResponse_QNAME = new QName("http://service.ws.cxf.zytx.com/", "jyDataJyResponse");
    private final static QName _JyDataQueryResponse_QNAME = new QName("http://service.ws.cxf.zytx.com/", "jyDataQueryResponse");
    private final static QName _JyDataJy_QNAME = new QName("http://service.ws.cxf.zytx.com/", "jyDataJy");
    private final static QName _JyDataQuery_QNAME = new QName("http://service.ws.cxf.zytx.com/", "jyDataQuery");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: webService.regularInspect
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JyDataJyResponse }
     * 
     */
    public JyDataJyResponse createJyDataJyResponse() {
        return new JyDataJyResponse();
    }

    /**
     * Create an instance of {@link JyDataQueryResponse }
     * 
     */
    public JyDataQueryResponse createJyDataQueryResponse() {
        return new JyDataQueryResponse();
    }

    /**
     * Create an instance of {@link JyDataQuery }
     * 
     */
    public JyDataQuery createJyDataQuery() {
        return new JyDataQuery();
    }

    /**
     * Create an instance of {@link JyDataJy }
     * 
     */
    public JyDataJy createJyDataJy() {
        return new JyDataJy();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JyDataJyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.cxf.zytx.com/", name = "jyDataJyResponse")
    public JAXBElement<JyDataJyResponse> createJyDataJyResponse(JyDataJyResponse value) {
        return new JAXBElement<JyDataJyResponse>(_JyDataJyResponse_QNAME, JyDataJyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JyDataQueryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.cxf.zytx.com/", name = "jyDataQueryResponse")
    public JAXBElement<JyDataQueryResponse> createJyDataQueryResponse(JyDataQueryResponse value) {
        return new JAXBElement<JyDataQueryResponse>(_JyDataQueryResponse_QNAME, JyDataQueryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JyDataJy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.cxf.zytx.com/", name = "jyDataJy")
    public JAXBElement<JyDataJy> createJyDataJy(JyDataJy value) {
        return new JAXBElement<JyDataJy>(_JyDataJy_QNAME, JyDataJy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JyDataQuery }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://service.ws.cxf.zytx.com/", name = "jyDataQuery")
    public JAXBElement<JyDataQuery> createJyDataQuery(JyDataQuery value) {
        return new JAXBElement<JyDataQuery>(_JyDataQuery_QNAME, JyDataQuery.class, null, value);
    }

}
