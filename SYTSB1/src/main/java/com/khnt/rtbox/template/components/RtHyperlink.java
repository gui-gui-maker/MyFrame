package com.khnt.rtbox.template.components;

import javax.xml.bind.JAXBElement;

import org.docx4j.XmlUtils;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.relationships.Namespaces;
import org.docx4j.relationships.Relationship;
import org.docx4j.relationships.Relationships;
import org.docx4j.wml.P.Hyperlink;
import org.docx4j.wml.R;
import org.docx4j.wml.Text;

import com.khnt.rtbox.tools.HtmlUtils;
import com.khnt.rtbox.tools.Utils;

/**
 * @author ZQ
 * @version 2017年3月9日 下午12:54:51 创建超链接
 */
public class RtHyperlink {

	public static Hyperlink create(MainDocumentPart mainPart, String url, String value) throws Exception {
		org.docx4j.relationships.ObjectFactory factory = new org.docx4j.relationships.ObjectFactory();
		org.docx4j.relationships.Relationship rel = factory.createRelationship();
		rel.setType(Namespaces.HYPERLINK);
		rel.setTarget(url);
		rel.setTargetMode("External");
		mainPart.getRelationshipsPart().addRelationship(rel);
		StringBuffer sb = new StringBuffer();
		sb.append("<w:hyperlink r:id=\"");
		sb.append(rel.getId());
		sb.append("\" xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" ");
		sb.append("xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\" >");
//		sb.append("<w:r><w:rPr><w:rStyle w:val=\"Hyperlink\" /> <w:sz w:val=\"22\"/><w:szCs w:val=\"22\"/></w:rPr>");
		sb.append("<w:r><w:rPr> <w:rStyle w:val=\"a5\"/><w:rFonts w:asciiTheme=\"minorEastAsia\" w:hAnsiTheme=\"minorEastAsia\"/> <w:sz w:val=\"22\"/><w:szCs w:val=\"22\"/></w:rPr>");
		sb.append("<w:t>");
		sb.append(value);
		sb.append("</w:t></w:r></w:hyperlink>");
		return (Hyperlink) XmlUtils.unmarshalString(sb.toString());
	}

	public static void setText(Hyperlink Hyperlink, String value) throws Exception {
		R r = (R) Hyperlink.getContent().get(0);
		Text text = (Text) ((JAXBElement<?>) r.getContent().get(0)).getValue();
		text.setValue(value);
	}

	/**
	 * 清楚用于JSP页面传递的超链接
	 * 
	 * @param mainPart
	 * @throws Exception
	 */
	public static void clearRtLink(MainDocumentPart mainPart) throws Exception {
		Relationships relationships = mainPart.getRelationshipsPart().getRelationships();
		for (Relationship relationship : relationships.getRelationship()) {
			String targetMode = relationship.getTargetMode();
			if ("External".equalsIgnoreCase(targetMode)) {
				String target = relationship.getTarget();
				target = HtmlUtils.decode(target);
				String regex = "(\\$\\{.*\\})";
				if (Utils.transMatch(target, regex)) {
					mainPart.getRelationshipsPart().removeRelationship(relationship);
				}
			}
		}
	}
}
