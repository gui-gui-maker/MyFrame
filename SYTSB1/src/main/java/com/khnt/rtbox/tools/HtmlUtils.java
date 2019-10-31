package com.khnt.rtbox.tools;

/**
 * @author ZQ
 * @version 2016年3月9日 上午10:12:30
 * HTML工具测试类
 */
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.khnt.rtbox.template.constant.RtSign;

public class HtmlUtils {

	public static final int WORD_HTML = 8;
	public static final int WORD_TXT = 7;
	public static final int EXCEL_HTML = 44;

	public static void main(String[] args) throws Exception {

		String test = "<table class=\"a6 a1 \" id=\"docx4j_tbl_0\""
				+ " style=\"border-bottom-style: none; border-bottom-width: 1px; border-bottom-color: #000000; border-left-style: none; border-left-width: 1px; border-left-color: #000000; border-right-style: none; border-right-width: 1px; border-right-color: #000000; border-top-style: none; border-top-width: 1px; border-top-color: #000000; position: relative; margin-left: 0in; table-layout: fixed; vertical-align: top; border-collapse: collapse; width: 6.57in;\">"
				+ "<colgroup>" + "              <col style=\"width: 73.32%;\" />"
				+ "              <col style=\"width: 26.68%;\" />" + "            </colgroup>"
				+ "            <tbody><tr style=\"width: 26.68%;\"><td"
				+ "                  style=\"border-bottom-style: none; border-bottom-width: 1px; border-bottom-color: #000000; border-left-style: none; border-left-width: 1px; border-left-color: #000000; border-right-style: none; border-right-width: 1px; border-right-color: #000000; border-top-style: none; border-top-width: 1px; border-top-color: #000000; padding-bottom: 0mm; padding-left: 1.91mm; padding-right: 1.91mm; padding-top: 0mm;\">"
				+ "                  <p class=\"a-a6-BR DocDefaults \" style=\"text-align: right;\">"
				+ "                    <span class=\"a0 \""
				+ "                      style=\"font-weight: bold; font-size: 14.0pt;\"><span"
				+ "                      class=\"\" style=\"font-family: 'SimSun';\">报告编号：</span></span>"
				+ "                  </p></td><td"
				+ "                  style=\"border-bottom-style: none; border-bottom-width: 1px; border-bottom-color: #000000; border-left-style: none; border-left-width: 1px; border-left-color: #000000; border-right-style: none; border-right-width: 1px; border-right-color: #000000; border-top-style: none; border-top-width: 1px; border-top-color: #000000; padding-bottom: 0mm; padding-left: 1.91mm; padding-right: 1.91mm; padding-top: 0mm;\">"
				+ "                  <p class=\"a-a6-BR DocDefaults \">"
				+ "                    <input id=\"base__report_sn\" name=\"base__report_sn\" type=\"text\""
				+ "                      ltype=\"text\" value=\"${param.report_sn }\" ligerui=\"{width:157}\""
				+ "                      readonly=\"readonly\" /></p></td></tr>"
		+ "            <tr style=\"width: 26.68%;\"><td"
		+ "                  style=\"border-bottom-style: none; border-bottom-width: 1px; border-bottom-color: #000000; border-left-style: none; border-left-width: 1px; border-left-color: #000000; border-right-style: none; border-right-width: 1px; border-right-color: #000000; border-top-style: none; border-top-width: 1px; border-top-color: #000000; padding-bottom: 0mm; padding-left: 1.91mm; padding-right: 1.91mm; padding-top: 0mm;\">"
		+ "                  <p class=\"a-a6-BR DocDefaults \" style=\"text-align: right;\">"
		+ "                    <span class=\"a0 \""
		+ "                      style=\"font-weight: bold; font-size: 14.0pt;\"><span"
		+ "                      class=\"\" style=\"font-family: 'SimSun';\">报告编号：</span></span>"
		+ "                  </p></td><td"
		+ "                  style=\"border-bottom-style: none; border-bottom-width: 1px; border-bottom-color: #000000; border-left-style: none; border-left-width: 1px; border-left-color: #000000; border-right-style: none; border-right-width: 1px; border-right-color: #000000; border-top-style: none; border-top-width: 1px; border-top-color: #000000; padding-bottom: 0mm; padding-left: 1.91mm; padding-right: 1.91mm; padding-top: 0mm;\">"
		+ "                  <p class=\"a-a6-BR DocDefaults \">"
		+ "                    <input id=\"base__report_sn\" name=\"base__report_sn\" type=\"text\""
		+ "                      ltype=\"text\" value=\"${param.report_sn }\" ligerui=\"{width:157}\""
		+ "                      readonly=\"readonly\" /></p></td></tr></tbody></table>";

		// StringBuilder sb = new StringBuilder();
		// cleanSelfStyleSubHtmlTag("td", test, sb);
		// System.out.println(sb.toString());
		System.out.println(cleanSpecifyTagStyle(test, "table,td,p,span".split(",")));
		// System.out.println(cleanSpecifyTagStyle(test, "table".split(",")));
	}

	public static String cleanSpecifyTagStyle(String html, String[] tagNames) {
		for (String tagName : tagNames) {
			html = cleanSpecifyTagStyle(html, tagName);
		}
		return html;
	}

	public static String cleanSpecifyTagStyle(String html, String tagName) {
		/*String regex = "<" + tagName + "(.*?)(class=.*?\".*?\")(.*?)>";
		String regex2 = "<" + tagName + "(.*?)(style=.*?\".*?\")(.*?)>";*/
		String regex = "<" + tagName + "([^>]*?)(class=.*?\".*?\")(.*?)>";
		String regex2 = "<" + tagName + "([^>]*?)(style=.*?\".*?\")(.*?)>";
		return html.replaceAll(regex, "<" + tagName + "$1 $3>").replaceAll(regex2, "<" + tagName + "$1 $3>");
		/*
		 * Pattern pattern = Pattern.compile(regex); Matcher matcher =
		 * pattern.matcher(html); if (matcher.find()) {
		 * System.out.println(matcher.group());
		 * 
		 * } else { System.out.println("no find"); } return html;
		 */
	}

	/**
	 * 将自身样式及所有子标签去掉
	 * 
	 * @param tagName
	 * @param html
	 * @param sb
	 */
	public static void cleanSelfStyleSubHtmlTag(String tagName, String html, StringBuilder sb) {
		String regex1 = "<" + tagName + ".*?>(.*?)</" + tagName + ">";
		// return html.replaceAll(regex, "<" + tagName +
		// ">"+"$1".replaceAll("<[^>]*>","")+"</" + tagName
		// + ">");
		String regex = "<[^>]*>";
		Pattern pattern1 = Pattern.compile(regex1);
		Matcher matcher1 = pattern1.matcher(html);
		Pattern pattern = Pattern.compile(regex);

		if (matcher1.find()) {
			String matcherText = matcher1.group(1);
			Matcher matcher = pattern.matcher(regex);
			if (matcher.find()) {
				String text = matcherText.replaceAll("<[^>]*>", "").replaceAll(" ", "");
				text = "<" + tagName + ">" + text + "</" + tagName + ">";
				sb.append(html.substring(0, matcher1.start()));
				sb.append(text);
			} else {
				sb.append(html.substring(0, matcher1.end()));
			}
			html = html.substring(matcher1.end());
			cleanSelfStyleSubHtmlTag(tagName, html, sb);
		} else {
			sb.append(html);

		}
	}

	public static String configOneByOne(String str) {
		int position = str.indexOf(RtSign.BlANKTAG);
		if (position > 0) {
			String val = "${\"id\":\"TBL" + Math.random() + "\",\"type\":\"text\",\"ligerui\":{\"width\":\"200px\"}}";
			str = str.substring(0, position) + val + str.substring(position + RtSign.BlANKTAG.length());
			return configOneByOne(str);
		}
		return str;
	}

	public static String trimHtmlTxt(String str, boolean trimExpr) {
		if (trimExpr) {
			str = str.replaceAll(" ", "");
			str = str.replaceAll("  ", "");
			str = str.replace(" ", "");
			str = str.replaceAll("<[^>]*>", "");
		}
		if (str != null && str.length() > 0) {
			str = str.replaceAll("&nbsp;", "");
			return str.trim();
		}
		return "";
	}

	public static void test(String str) {
		String test = "([\\u4e00-\\u9fa5\\w]*)";
		Pattern pattern = Pattern.compile(test);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			for (int i = 0; i <= matcher.groupCount(); i++) {
				System.out.println(i + ":" + matcher.group(i));
			}
		} else {
			System.out.println("no find..");
		}
	}

	public static String trimHtmlTxt(String str) {
		if (str != null && str.length() > 0) {
			str = str.replace("&nbsp;", "");
			return str.trim();
		}
		return "";
	}

	public static String decode(String val) throws UnsupportedEncodingException {
		try {
			return URLDecoder.decode(val, "UTF-8");
		} catch (Exception e) {
			System.out.println(val);
			e.printStackTrace();
			return URLDecoder.decode(val.replaceAll("%", "%25"), "UTF-8");
		}

	}

	public static String encode(String val) throws UnsupportedEncodingException {
		return URLEncoder.encode(val, "UTF-8");
	}

	public static String removeSelfClosingTag(String content, String tagName) {
		String regex = "<(" + tagName + ")\\s+([^>]*)/>";
		// String content =
		// "<p class=\"a DocDefaults \" style=\"text-align: center;\"><a
		// name=\"RTPAGE_3__压力容器定期检验结论报告\" /><span class=\"a0 \"
		// style=\"font-weight: bold;font-size: 16.0pt;;font-family:
		// 'SimSun';\">压力容器定期检验结论报告</span></p>";
		content = content.replaceAll(regex, "<$1 $2></" + tagName + ">");
		return content;
	}

}
