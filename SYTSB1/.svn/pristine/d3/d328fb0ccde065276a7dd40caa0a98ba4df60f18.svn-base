package com.khnt.rtbox.template.parse.convert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.relationships.Relationship;
import org.docx4j.relationships.Relationships;
import org.docx4j.wml.P.Hyperlink;

import com.khnt.rtbox.template.constant.RtPageType;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.HtmlUtils;

/**
 * @author ZQ
 * @version 2016年3月15日 下午3:58:41 计数器
 */
public class RtCount {

	private int count = 0;
	private int bit = DEFAULT_BIT;
	public static int DEFAULT_BIT = 5;

	public RtCount() {
		this.clear();
	}

	/**
	 * 计数器清零
	 */
	public void clear() {
		this.count = 0;
	}

	/**
	 * 自加1
	 */
	public void add1() {
		this.count++;
	}

	/**
	 * 遇大交换
	 * 
	 * @param count
	 */
	public void exchange(int count) {
		if (this.count < count) {
			this.count = count;
		}
	}

	/**
	 * 默认TYPE+5位随机ID
	 * 
	 * @param type
	 * @return
	 * @throws Exception
	 */
	public String id(String type) throws Exception {
		return id(type, DEFAULT_BIT);
	}

	/**
	 * 根据段落或者TABLE，和位数返回ID
	 * 
	 * @param type
	 * @param bit
	 * @return
	 * @throws Exception
	 */
	public String id(String type, int bit) throws Exception {
		this.setBit(bit);
		int differ = bit - String.valueOf(this.count).length();
		if (differ > 0) {
			StringBuilder val = new StringBuilder();
			val.append(type.toUpperCase());
			for (int i = 0; i < differ; i++) {
				val.append("0");
			}
			val.append(this.count);
			return val.toString();
		} else if (differ == 0) {
			return type + this.count;
		} else {
			throw new Exception("自增长ID已经溢出啦....");
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getBit() {
		return bit;
	}

	public void setBit(int bit) {
		this.bit = bit;
	}

	public void getMax(MainDocumentPart documentPart, List<Object> hyperlinks) throws Exception {
		String regex = "\"id\":\"" + RtPageType.TABLE + "\\d{" + this.bit + "}\"";
		Pattern pattern = Pattern.compile(regex);
		// WPS
		for (Object obj : hyperlinks) {
			Hyperlink hyperlink = (Hyperlink) obj;
			String val = Docx4jUtil.getElementContent(hyperlink);
			val = HtmlUtils.decode(val);
			Matcher matcher = pattern.matcher(val);
			while (matcher.find()) {
				swapMax(matcher.group());
			}
		}
		// WORD
		Relationships relationships = documentPart.getRelationshipsPart().getContents();
		if (relationships != null && relationships.getRelationship() != null) {
			for (Relationship obj : relationships.getRelationship()) {
				String linkText = obj.getTarget();
				linkText = HtmlUtils.decode(linkText);
				Matcher matcher = pattern.matcher(linkText);
				if (matcher.find()) {
					swapMax(matcher.group());
				}
			}
		}

		this.count++;
	}

	private void swapMax(String idStr) {
		idStr = idStr.replaceAll("\"", "").trim();
		String _idCount = idStr.split(RtPageType.TABLE)[1];
		try {
			int idCount = Integer.parseInt(_idCount);
			if (idCount > this.count) {
				this.count = idCount;
			}
		} catch (NumberFormatException e) {
		}
	}
}
