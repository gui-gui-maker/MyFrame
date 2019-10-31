package com.khnt.rtbox.tools;

import java.io.File;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;

import com.khnt.utils.LogUtil;

public class ServerUtil {
	public static Integer getTomcatPortFromConfigXml(File serverXml) {
		Integer port;
		try {
			DocumentBuilderFactory domFactory = DocumentBuilderFactory.newInstance();
			domFactory.setNamespaceAware(true); // never forget this!
			DocumentBuilder builder = domFactory.newDocumentBuilder();
			Document doc = builder.parse(serverXml);
			XPathFactory factory = XPathFactory.newInstance();
			XPath xpath = factory.newXPath();
			XPathExpression expr = xpath
					.compile("/Server/Service[@name='Catalina']/Connector[count(@scheme)=0]/@port[1]");
			String result = (String) expr.evaluate(doc, XPathConstants.STRING);
			port = result != null && result.length() > 0 ? Integer.valueOf(result) : null;
		} catch (Exception e) {
			port = null;
			e.printStackTrace();
		}
		return port;
	}

	public static void main(String[] args) {
		Properties props = System.getProperties();
		String catalinaHome = props.get("catalina.home").toString();
		String port = getTomcatPortFromConfigXml(
				new File(catalinaHome + File.separator + "conf" + File.separator + "server.xml")) + "";
	}

}
