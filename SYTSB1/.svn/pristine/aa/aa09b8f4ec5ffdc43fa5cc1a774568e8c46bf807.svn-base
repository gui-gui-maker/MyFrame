package com.khnt.rtbox.template.parse.tag.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.relationships.Relationships;
import org.docx4j.wml.P.Hyperlink;

import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.template.parse.convert.RtCount;
import com.khnt.rtbox.template.parse.tag.BlankTag;
import com.khnt.rtbox.template.parse.tag.CustomTag;
import com.khnt.rtbox.template.parse.tag.DocxTag;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.HtmlUtils;

/**
 * @author ZQ
 * @version 2016年3月11日 下午2:25:39 标记WORD文档的实现类
 */
public class DocxTagImpl implements DocxTag {
	public boolean isBlank = false;

	/**
	 * 标记空白内容，设置输入框
	 * 
	 * @param rtPage
	 * 
	 * @param configList
	 */
	public void tag(MainDocumentPart documentPart, RtPage rtPage) throws Exception {
		log.debug(("DocxTag begin to mark ... "));
		// Document document =
		// DocumentHelper.parseText(XmlUtils.marshaltoString(documentPart.getContents()));
		List<Object> hyperlinks = Docx4jUtil.getAllElementFromObject(documentPart, Hyperlink.class);
		if (isBlank(documentPart, hyperlinks)) {
			log.debug(("1.need blank tag ... "));
			BlankTag blankTag = new BlankTag();
			// 注册顺序即为优先级
			blankTag.attach(new TdBlankRound());// 注册轮循空白TD 属于空白标记
			// blankTag.attach(new ColonBlankRound());// 注册轮循冒号 属于空白标记
			blankTag.attach(new UnitBlankRound());// 注册轮循单位库 属于空白标记
			// blankTag.attach(new DefaultsBlankRound());// 注册轮循默认值 属于空白标记

			// 开始标记
			blankTag.mark(documentPart, rtPage);
		}

		log.debug(("2.begin custom tag ... "));
		CustomTag roundTag = new CustomTag();
		RtCount count = new RtCount();
		boolean isFirst = isFirst(documentPart, hyperlinks);
		if (!isFirst) {
			log.debug(("count max id... "));
			count.getMax(documentPart, hyperlinks);// 获取最大ID (后期需要修改，改为替换最大ID)
			log.debug(("count max id : " + count.getCount()));
		}

		log.debug((" custom blank tag ... "));
		// roundTag.attach(new FirstTableCustomRound(count));//
		// 将TABLE中的空白标记，根据定义，转换为正式标记
		roundTag.attach(new FirstPCustomRound(count));
		// roundTag.attach(new InstrTextRound());// 超链接
	
		// checkbox转化 ZQ ADD 2017026
		roundTag.attach(new CheckboxCustomRound(count));

		if (!isFirst) {
			log.debug(("custom repeat ... "));
			roundTag.attach(new RepeatCustomRound(count));
		}

		// 等待解析P

		roundTag.mark(documentPart, rtPage);
	}

	/**
	 * 是否还未进行过任何标记
	 * 
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public boolean isBlank(MainDocumentPart documentPart, List<Object> hyperlinks) throws Exception {
		log.debug("is Blank ?");
		// WPS
		for (Object obj : hyperlinks) {
			Hyperlink hyperlink = (Hyperlink) obj;
			String val = Docx4jUtil.getElementContent(hyperlink);
			val = HtmlUtils.decode(val);
			Pattern pattern = Pattern.compile("\\$\\{(.*)\\}");
			Matcher matcher = pattern.matcher(val);
			if (matcher.find()) {
				log.debug("is Blank ? no ");
				return false;
			}
		}
		// WORD
		Relationships relationships = documentPart.getRelationshipsPart().getContents();
		if (relationships != null && relationships.getRelationship() != null) {
			for (Relationship obj : relationships.getRelationship()) {
				String linkText = obj.getTarget();
				linkText = HtmlUtils.decode(linkText);
				Pattern pattern = Pattern.compile("\\$\\{(.*)\\}");
				Matcher matcher = pattern.matcher(linkText);
				if (matcher.find()) {
					log.debug("is Blank ? no ");
					return false;
				}
			}
		}

		log.debug("is Blank ? yes! ");
		this.isBlank = true;
		return true;
	}

	/**
	 * 是否为第一次正式定制标记
	 * 
	 * @param document
	 * @return
	 * @throws Exception
	 */
	public boolean isFirst(MainDocumentPart documentPart, List<Object> hyperlinks) throws Exception {
		log.debug("is isFirst ?");
		for (Object obj : hyperlinks) {
			Hyperlink hyperlink = (Hyperlink) obj;
			String val = Docx4jUtil.getElementContent(hyperlink);
			val = HtmlUtils.decode(val);
			Pattern pattern = Pattern.compile("\\$\\{(.*\"id\":.*)\\}");
			Matcher matcher = pattern.matcher(val);
			if (matcher.find()) {
				log.debug("is isFirst ? no ");
				return false;
			}
		}

		// WORD
		Relationships relationships = documentPart.getRelationshipsPart().getContents();
		if (relationships != null && relationships.getRelationship() != null) {
			for (Relationship obj : relationships.getRelationship()) {
				String linkText = obj.getTarget();
				linkText = HtmlUtils.decode(linkText);
				Pattern pattern = Pattern.compile("\\$\\{(.*\"id\":.*)\\}");
				Matcher matcher = pattern.matcher(linkText);
				if (matcher.find()) {
					log.debug("is isFirst ? no ");
					return false;
				}
			}
		}
		log.debug("is isFirst ? yes! ");
		return true;
	}

	// 此方法即已被重载，不在使用
	@Override
	public void tag(MainDocumentPart documentPart) throws Exception {
		// TODO Auto-generated method stub

	}
}
