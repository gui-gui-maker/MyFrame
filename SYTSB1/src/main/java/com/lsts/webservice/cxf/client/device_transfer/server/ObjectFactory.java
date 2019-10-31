
package com.lsts.webservice.cxf.client.device_transfer.server;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the zjpt.ts.ws.device package. 
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

    private final static QName _UpdateParamInfo_QNAME = new QName("http://device.ws.ts.zjpt/", "updateParamInfo");
    private final static QName _UpdateParamInfoResponse_QNAME = new QName("http://device.ws.ts.zjpt/", "updateParamInfoResponse");
    private final static QName _GeDeviceInfoByRegCodeResponse_QNAME = new QName("http://device.ws.ts.zjpt/", "geDeviceInfoByRegCodeResponse");
    private final static QName _GeDeviceInfoByQrCode_QNAME = new QName("http://device.ws.ts.zjpt/", "geDeviceInfoByQrCode");
    private final static QName _UpdatDeviceInfoResponse_QNAME = new QName("http://device.ws.ts.zjpt/", "updatDeviceInfoResponse");
    private final static QName _GeDeviceInfoByQrCodeResponse_QNAME = new QName("http://device.ws.ts.zjpt/", "geDeviceInfoByQrCodeResponse");
    private final static QName _UpdatDeviceInfo_QNAME = new QName("http://device.ws.ts.zjpt/", "updatDeviceInfo");
    private final static QName _GeDeviceInfoByRegCode_QNAME = new QName("http://device.ws.ts.zjpt/", "geDeviceInfoByRegCode");
    private final static QName _InsertDeviceInfo_QNAME = new QName("http://device.ws.ts.zjpt/", "insertDeviceInfo");
    private final static QName _InsertDeviceInfoResponse_QNAME = new QName("http://device.ws.ts.zjpt/", "insertDeviceInfoResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: zjpt.ts.ws.device
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link UpdatDeviceInfoResponse }
     * 
     */
    public UpdatDeviceInfoResponse createUpdatDeviceInfoResponse() {
        return new UpdatDeviceInfoResponse();
    }

    /**
     * Create an instance of {@link GeDeviceInfoByQrCodeResponse }
     * 
     */
    public GeDeviceInfoByQrCodeResponse createGeDeviceInfoByQrCodeResponse() {
        return new GeDeviceInfoByQrCodeResponse();
    }

    /**
     * Create an instance of {@link UpdatDeviceInfo }
     * 
     */
    public UpdatDeviceInfo createUpdatDeviceInfo() {
        return new UpdatDeviceInfo();
    }

    /**
     * Create an instance of {@link InsertDeviceInfo }
     * 
     */
    public InsertDeviceInfo createInsertDeviceInfo() {
        return new InsertDeviceInfo();
    }

    /**
     * Create an instance of {@link GeDeviceInfoByRegCode }
     * 
     */
    public GeDeviceInfoByRegCode createGeDeviceInfoByRegCode() {
        return new GeDeviceInfoByRegCode();
    }

    /**
     * Create an instance of {@link InsertDeviceInfoResponse }
     * 
     */
    public InsertDeviceInfoResponse createInsertDeviceInfoResponse() {
        return new InsertDeviceInfoResponse();
    }

    /**
     * Create an instance of {@link UpdateParamInfoResponse }
     * 
     */
    public UpdateParamInfoResponse createUpdateParamInfoResponse() {
        return new UpdateParamInfoResponse();
    }

    /**
     * Create an instance of {@link UpdateParamInfo }
     * 
     */
    public UpdateParamInfo createUpdateParamInfo() {
        return new UpdateParamInfo();
    }

    /**
     * Create an instance of {@link GeDeviceInfoByRegCodeResponse }
     * 
     */
    public GeDeviceInfoByRegCodeResponse createGeDeviceInfoByRegCodeResponse() {
        return new GeDeviceInfoByRegCodeResponse();
    }

    /**
     * Create an instance of {@link GeDeviceInfoByQrCode }
     * 
     */
    public GeDeviceInfoByQrCode createGeDeviceInfoByQrCode() {
        return new GeDeviceInfoByQrCode();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateParamInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "updateParamInfo")
    public JAXBElement<UpdateParamInfo> createUpdateParamInfo(UpdateParamInfo value) {
        return new JAXBElement<UpdateParamInfo>(_UpdateParamInfo_QNAME, UpdateParamInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateParamInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "updateParamInfoResponse")
    public JAXBElement<UpdateParamInfoResponse> createUpdateParamInfoResponse(UpdateParamInfoResponse value) {
        return new JAXBElement<UpdateParamInfoResponse>(_UpdateParamInfoResponse_QNAME, UpdateParamInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeDeviceInfoByRegCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "geDeviceInfoByRegCodeResponse")
    public JAXBElement<GeDeviceInfoByRegCodeResponse> createGeDeviceInfoByRegCodeResponse(GeDeviceInfoByRegCodeResponse value) {
        return new JAXBElement<GeDeviceInfoByRegCodeResponse>(_GeDeviceInfoByRegCodeResponse_QNAME, GeDeviceInfoByRegCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeDeviceInfoByQrCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "geDeviceInfoByQrCode")
    public JAXBElement<GeDeviceInfoByQrCode> createGeDeviceInfoByQrCode(GeDeviceInfoByQrCode value) {
        return new JAXBElement<GeDeviceInfoByQrCode>(_GeDeviceInfoByQrCode_QNAME, GeDeviceInfoByQrCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatDeviceInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "updatDeviceInfoResponse")
    public JAXBElement<UpdatDeviceInfoResponse> createUpdatDeviceInfoResponse(UpdatDeviceInfoResponse value) {
        return new JAXBElement<UpdatDeviceInfoResponse>(_UpdatDeviceInfoResponse_QNAME, UpdatDeviceInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeDeviceInfoByQrCodeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "geDeviceInfoByQrCodeResponse")
    public JAXBElement<GeDeviceInfoByQrCodeResponse> createGeDeviceInfoByQrCodeResponse(GeDeviceInfoByQrCodeResponse value) {
        return new JAXBElement<GeDeviceInfoByQrCodeResponse>(_GeDeviceInfoByQrCodeResponse_QNAME, GeDeviceInfoByQrCodeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdatDeviceInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "updatDeviceInfo")
    public JAXBElement<UpdatDeviceInfo> createUpdatDeviceInfo(UpdatDeviceInfo value) {
        return new JAXBElement<UpdatDeviceInfo>(_UpdatDeviceInfo_QNAME, UpdatDeviceInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeDeviceInfoByRegCode }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "geDeviceInfoByRegCode")
    public JAXBElement<GeDeviceInfoByRegCode> createGeDeviceInfoByRegCode(GeDeviceInfoByRegCode value) {
        return new JAXBElement<GeDeviceInfoByRegCode>(_GeDeviceInfoByRegCode_QNAME, GeDeviceInfoByRegCode.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertDeviceInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "insertDeviceInfo")
    public JAXBElement<InsertDeviceInfo> createInsertDeviceInfo(InsertDeviceInfo value) {
        return new JAXBElement<InsertDeviceInfo>(_InsertDeviceInfo_QNAME, InsertDeviceInfo.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InsertDeviceInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://device.ws.ts.zjpt/", name = "insertDeviceInfoResponse")
    public JAXBElement<InsertDeviceInfoResponse> createInsertDeviceInfoResponse(InsertDeviceInfoResponse value) {
        return new JAXBElement<InsertDeviceInfoResponse>(_InsertDeviceInfoResponse_QNAME, InsertDeviceInfoResponse.class, null, value);
    }

}
