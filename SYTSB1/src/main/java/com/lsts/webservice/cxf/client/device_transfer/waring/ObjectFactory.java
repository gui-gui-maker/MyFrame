
package com.lsts.webservice.cxf.client.device_transfer.waring;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the zjpt.ts.ws.warning package. 
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

    private final static QName _UpdateWarningDealResponse_QNAME = new QName("http://warning.ws.ts.zjpt/", "updateWarningDealResponse");
    private final static QName _UpdateWarningDeal_QNAME = new QName("http://warning.ws.ts.zjpt/", "updateWarningDeal");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: zjpt.ts.ws.warning
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdateWarningDeal }
     * 
     */
    public UpdateWarningDeal createUpdateWarningDeal() {
        return new UpdateWarningDeal();
    }

    /**
     * Create an instance of {@link UpdateWarningDealResponse }
     * 
     */
    public UpdateWarningDealResponse createUpdateWarningDealResponse() {
        return new UpdateWarningDealResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateWarningDealResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://warning.ws.ts.zjpt/", name = "updateWarningDealResponse")
    public JAXBElement<UpdateWarningDealResponse> createUpdateWarningDealResponse(UpdateWarningDealResponse value) {
        return new JAXBElement<UpdateWarningDealResponse>(_UpdateWarningDealResponse_QNAME, UpdateWarningDealResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateWarningDeal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://warning.ws.ts.zjpt/", name = "updateWarningDeal")
    public JAXBElement<UpdateWarningDeal> createUpdateWarningDeal(UpdateWarningDeal value) {
        return new JAXBElement<UpdateWarningDeal>(_UpdateWarningDeal_QNAME, UpdateWarningDeal.class, null, value);
    }

}
