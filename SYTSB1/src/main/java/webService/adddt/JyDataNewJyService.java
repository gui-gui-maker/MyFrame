package webService.adddt;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.3
 * 2017-09-28T11:16:46.368+08:00
 * Generated source version: 2.4.3
 * 
 */
@WebService(targetNamespace = "http://service.ws.cxf.zytx.com/", name = "JyDataNewJyService")
@XmlSeeAlso({ObjectFactory.class})
public interface JyDataNewJyService {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "jyDataNewJy", targetNamespace = "http://service.ws.cxf.zytx.com/", className = "webService.adddt.JyDataNewJy")
    @WebMethod
    @ResponseWrapper(localName = "jyDataNewJyResponse", targetNamespace = "http://service.ws.cxf.zytx.com/", className = "webService.adddt.JyDataNewJyResponse")
    public java.lang.String jyDataNewJy(
        @WebParam(name = "xmlStr", targetNamespace = "")
        java.lang.String xmlStr
    );
}
