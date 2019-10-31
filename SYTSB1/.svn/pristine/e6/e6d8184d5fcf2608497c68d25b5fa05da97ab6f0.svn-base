package com.khnt.rtbox.template.parse.tag.impl;

import java.math.BigInteger;
import java.util.List;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.P;
import org.docx4j.wml.P.Hyperlink;
import org.docx4j.wml.TcPrInner.HMerge;
import org.docx4j.wml.TcPrInner.VMerge;
import org.docx4j.wml.R;
import org.docx4j.wml.RPr;
import org.docx4j.wml.Tc;

import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.template.components.RtHyperlink;
import com.khnt.rtbox.template.constant.RtSign;
import com.khnt.rtbox.template.parse.tag.BlankRound;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年3月11日 下午1:43:20 空白TD轮循
 */
public class TdBlankRound extends BlankRound {

	@Override
	public void mark(MainDocumentPart documentPart, RtPage rtPage) throws Exception {
		log.debug("TdBlankRound mark begin...");
		List<Object> tcs = Docx4jUtil.getAllElementFromObject(documentPart, Tc.class);
		for (Object obj : tcs) {
			Tc tc = (Tc) obj;
			// ZQ ADD 20180907，合并的表格TD不加标记
			if (tc != null && tc.getTcPr() != null) {
				VMerge vMerge = tc.getTcPr().getVMerge();
				if (vMerge != null && !"restart".equals(vMerge.getVal())) {
					// 纵向合并
					continue;
				}
				// 猜测 未验证
				HMerge hMerge = tc.getTcPr().getHMerge();
				if (hMerge != null && !"restart".equals(hMerge.getVal())) {
					// 横向合并
					continue;
				}
			}
			if (StringUtil.isEmpty(Docx4jUtil.getElementContent(tc))) {
				// is w:vMerge?
				/*
				 * if (hasRuleOutTag(tc)) { continue; }
				 */
				// clear , for example 'enter'
				tc.getContent().clear();
				// create p
				P p = Context.getWmlObjectFactory().createP();
				tc.getContent().add(p);
				// create hyperlink
				Hyperlink link = RtHyperlink.create(documentPart, RtSign.BlANKTAG, RtSign.BlANKTAG);
				// p.getContent().add(link);

				HpsMeasure hpsMeasure = Context.getWmlObjectFactory().createHpsMeasure();

				hpsMeasure.setVal(new BigInteger("22"));

				RPr pr = Context.getWmlObjectFactory().createRPr();

				pr.setSz(hpsMeasure);
				pr.setSzCs(hpsMeasure);

				p.getContent().add(pr);
				p.getContent().add(link);
			}

		}
		log.debug("TdBlankRound mark successed...");
	}

	public boolean hasRuleOutTag(Tc tc) {
		if (tc.getTcPr() != null && tc.getTcPr().getVMerge() != null) {
			return true;
		}
		return false;
	}

}
