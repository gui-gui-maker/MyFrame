package com.khnt.rtbox.template.parse.tag.impl;

import java.util.ArrayList;
import java.util.List;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.P;
import org.docx4j.wml.P.Hyperlink;
import org.docx4j.wml.Tc;

import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.template.components.RtHyperlink;
import com.khnt.rtbox.template.constant.RtSign;
import com.khnt.rtbox.template.parse.tag.BlankRound;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.HtmlUtils;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年3月11日 下午1:53:37 单位库轮循
 */
public class UnitBlankRound extends BlankRound {

	@Override
	public void mark(MainDocumentPart documentPart,RtPage rtPage) throws Exception {

		log.debug("UnitBlankRound mark begin...");
		if (checkRuleNull()) {
			log.debug("blank rule,exit mark...");
			return;
		}
		List<Object> tcs = Docx4jUtil.getAllElementFromObject(documentPart, Tc.class);
		for (Object obj : tcs) {
			Tc tc = (Tc) obj;
			String val = Docx4jUtil.getElementContent(tc);
			if (StringUtil.isNotEmpty(val) && hasRuleInTag(val)) {
				// clear , for example 'enter'
				tc.getContent().clear();
				// create p
				P p = Context.getWmlObjectFactory().createP();
				tc.getContent().add(p);
				// create hyperlink
				Hyperlink link = RtHyperlink.create(documentPart, RtSign.BlANKTAG, RtSign.BlANKTAG + " " + HtmlUtils.trimHtmlTxt(val));
				p.getContent().add(link);
			}

		}

		log.debug("UnitBlankRound mark successed...");
	}

	public boolean hasRuleInTag(String val) {
		if (ruleIns == null) {
			initRuleIns();
		}
		// Element wtc = element.getParent();
		val = HtmlUtils.trimHtmlTxt(val);
		for (String str : ruleIns) {
			if (val.equals(str)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void initRuleIns() {
		String utis = "mm,A,S,g/l,N,min,MPa,t/h,℃,t/h(MW),t/h（MW）,h";
		ruleIns = new ArrayList<String>();
		String[] us = utis.split(",");
		for (String str : us) {
			ruleIns.add(str);
		}
	}

}
