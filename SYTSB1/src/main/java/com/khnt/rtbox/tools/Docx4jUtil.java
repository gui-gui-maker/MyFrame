package com.khnt.rtbox.tools;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.TextUtils;
import org.docx4j.wml.ContentAccessor;

/**
 * @author ZQ
 * @version 2017年3月9日 上午9:06:05 DOCX4j工具类
 */
public class Docx4jUtil {

	public static String getElementContent(Object obj) throws Exception {
		StringWriter stringWriter = new StringWriter();
		TextUtils.extractText(obj, stringWriter);
		return stringWriter.toString();
	}

	/**
	 * @Description:得到指定类型的元素
	 */
	public static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
		List<Object> result = new ArrayList<Object>();
		if (obj instanceof JAXBElement)
			obj = ((JAXBElement<?>) obj).getValue();
		if (obj.getClass().equals(toSearch)) {
			result.add(obj);
		} else if (obj instanceof ContentAccessor) {
			List<?> children = ((ContentAccessor) obj).getContent();
			for (Object child : children) {
				result.addAll(getAllElementFromObject(child, toSearch));
			}
		}
		return result;
	}

}
