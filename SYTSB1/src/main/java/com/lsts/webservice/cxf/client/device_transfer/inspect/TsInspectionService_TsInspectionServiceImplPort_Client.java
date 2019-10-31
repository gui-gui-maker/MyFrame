
package com.lsts.webservice.cxf.client.device_transfer.inspect;

/**
 * Please modify this class to meet your needs
 * This class is not complete
 */

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import com.lsts.webservice.cxf.client.device_transfer.inspect.impl.TsInspectionService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.4.3
 * 2017-03-24T16:49:07.150+08:00
 * Generated source version: 2.4.3
 * 
 */
public final class TsInspectionService_TsInspectionServiceImplPort_Client {

    private static final QName SERVICE_NAME = new QName("http://impl.inspect.ws.ts.zjpt/", "tsInspectionService");

    private TsInspectionService_TsInspectionServiceImplPort_Client() {
    }

    public static void main(String args[]) throws java.lang.Exception {
        URL wsdlURL = TsInspectionService.WSDL_LOCATION;
        if (args.length > 0 && args[0] != null && !"".equals(args[0])) { 
            File wsdlFile = new File(args[0]);
            try {
                if (wsdlFile.exists()) {
                    wsdlURL = wsdlFile.toURI().toURL();
                } else {
                    wsdlURL = new URL(args[0]);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
      
        TsInspectionService ss = new TsInspectionService(wsdlURL, SERVICE_NAME);
        com.lsts.webservice.cxf.client.device_transfer.inspect.TsInspectionService port = ss.getTsInspectionServiceImplPort();  
        
        String result = port.updateInspectionInfo("zdfdf342", "adfghgd43", "{\"UUID\":\"4c61222bccfb4de2aed83f5354ab001b\",\"PARAM\":{\"report_sn\":\"1234\",\"report_name\":\"456\",\"report_com_name\":\"测试测试\",\"inspect_type_name\":\"3\",\"report_com_address\":\"川大的\",\"inspect_org\":\"四川省特种设备检验研究院\",\"inspect_date\":\"2016-1-1\",\"inspect_next_date\":\"2017-1-1\",\"inspect_conclusion\":\"合格\"}}");
        System.out.println(result);
//        {
//        System.out.println("Invoking updateInspectionInfo...");
//        java.lang.String _updateInspectionInfo_account = "";
//        java.lang.String _updateInspectionInfo_password = "";
//        java.lang.String _updateInspectionInfo_content = "";
//        java.lang.String _updateInspectionInfo__return = port.updateInspectionInfo(_updateInspectionInfo_account, _updateInspectionInfo_password, _updateInspectionInfo_content);
//        System.out.println("updateInspectionInfo.result=" + _updateInspectionInfo__return);
//
//
//        }
//
//        System.exit(0);
    }

}
