
package webService.dqjy;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for jyDataQuery complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="jyDataQuery">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="xmlStr" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jyDataQuery", propOrder = {
    "xmlStr"
})
public class JyDataQuery {

    protected String xmlStr;

    /**
     * Gets the value of the xmlStr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXmlStr() {
        return xmlStr;
    }

    /**
     * Sets the value of the xmlStr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXmlStr(String value) {
        this.xmlStr = value;
    }

}
