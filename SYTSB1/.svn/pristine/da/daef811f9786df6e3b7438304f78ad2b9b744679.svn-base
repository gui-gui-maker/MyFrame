package com.khnt.rtbox.template.parse.tag.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.wml.P.Hyperlink;

import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.template.components.RtHyperlink;
import com.khnt.rtbox.template.constant.RtPageType;
import com.khnt.rtbox.template.parse.convert.RtCount;
import com.khnt.rtbox.template.parse.tag.CustomRound;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.HtmlUtils;

/**
 * @author ZQ
 *         <p>
 *         用户对第一次指定过的标识符，进行修改后，订制唯一标识符及其他属性
 *         </p>
 */
public class RepeatCustomRound extends CustomRound {

	public RepeatCustomRound() {
	}

	public RepeatCustomRound(RtCount count) {
		this.count = count;
	}

	@Override
	public void mark(MainDocumentPart documentPart,RtPage rtPage) throws Exception {
		log.debug("RepeatCustomRound mark begin...");

		if (this.count == null) {
			throw new Exception("RepeatCustomRound : come on, give me a rtCounter...");
		}
		List<Object> rs = Docx4jUtil.getAllElementFromObject(documentPart, Hyperlink.class);
		for (Object obj : rs) {
			Hyperlink hyperlink = (Hyperlink) obj;
			Relationship relationship = documentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId());
			String val = relationship.getTarget();
			val = HtmlUtils.decode(val);
			String regex1 = "\\$\\{(.*)\\}";
			String regex2 = "\\$\\{(.*\"id\":.*)\\}";
			Pattern pattern1 = Pattern.compile(regex1);
			Matcher matcher1 = pattern1.matcher(val);
			Pattern pattern2 = Pattern.compile(regex2);
			Matcher matcher2 = pattern2.matcher(val);
			if (matcher1.find()) {
				String value = matcher1.group(0);
				if (!matcher2.find()) {
					String id = this.count.id(RtPageType.TABLE);
					value = value.replace("${", "${\"id\":\"" + id + "\",");
					this.count.add1();

					RtHyperlink.setText(hyperlink, "${id:" + id + "}");
					relationship.setTarget(value);
				}
				value = HtmlUtils.encode(value);
				relationship.setTarget(value);
			}
		}
	}

}
