package com.khnt.rtbox.template.html;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.tools.HtmlUtils;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年3月17日 下午1:17:10 将HTML中标记替换为INPUT
 */
public class RtHtmlParser {
	static Log log = LogFactory.getLog(RtHtmlParser.class);
	public List<String> jsFuns = new ArrayList<String>();

	public String parse(String content, RtPage rtPage) throws Exception {
		log.debug("RtHtmlParser parse...");
		content = content.replaceAll("<ahref", "<a href");
		Parser htmlParser = Parser.createParser(content, "UTF-8");
		// NodeFilter filter = new NodeClassFilter(ParagraphTag.class);
		NodeFilter filter = new NodeClassFilter(LinkTag.class);
		NodeList nodes = htmlParser.extractAllNodesThatMatch(filter);
		StringBuilder swap = new StringBuilder();
		int position = 0;// 偏移量
		for (int len = 0, l = nodes.size(); len < l; len++) {
			// Node tag = nodes.elementAt(len);
			// String html = tag.toHtml();
			LinkTag tag = (LinkTag) nodes.elementAt(len);
			String html = tag.extractLink();
			if (html == null || html.trim().length() <= 0) {
				continue;
			}
			System.out.println(tag+"---"+position+"--"+tag.getStartPosition()+"---"+content.length());
			if(tag.getStartPosition()<position){
		  		position = tag.getStartPosition() + tag.toHtml().length();
				continue;
			}
			swap.append(content.substring(position, tag.getStartPosition()));
			html = HtmlUtils.decode(html);
			int pos = html.indexOf("$");
			if (pos >= 0) {
				html = html.substring(pos, html.length());
				html = this.findTagToParse(html,rtPage);
				swap.append(html);
			}
			position = tag.getStartPosition() + tag.toHtml().length();
		}
		swap.append(content.substring(position, content.length()));
		log.debug("RtHtmlParser parse success...");

		// 害怕LINKTAG不给力，再解析一遍
		return swap.toString();
	}

	private String reParse(String content) throws Exception {
		StringBuilder html = new StringBuilder();
		int pos = 0;
		String regex = "$";
		String startARegex = "<ahref";
		String endARegex = "</a>";
		int endALength = endARegex.length();
		while (pos >= 0) {
			int startPos = content.indexOf(regex, pos);
			if (startPos < 0) {
				break;
			}
			int startApos = content.lastIndexOf(startARegex, startPos);

			int endPos = content.indexOf(">", startApos);
			if (endPos < 0) {
				throw new Exception("错误的标记...");
			}

			int endApos = content.indexOf(endARegex, endPos);
			if (endApos < 0) {
				throw new Exception("错误的标记...");
			}

			endApos += endALength;
			html.append(content.substring(pos, startApos));

			String linkTag = content.substring(startApos, endApos);
			linkTag = HtmlUtils.decode(linkTag);
			int beginTag = linkTag.indexOf("$");
			int endTag = linkTag.indexOf(">");
			endTag = linkTag.lastIndexOf("}", endTag) + 1;
			linkTag = linkTag.substring(beginTag, endTag);
			html.append(findTagToParse(linkTag, null));
			pos = endApos;
		}
		html.append(content.substring(pos, content.length()));
		return html.toString();
	}

	/**
	 * 标记转input author pingZhou
	 * 
	 * @param val
	 * @param rtPage 
	 * @return
	 * @throws Exception
	 */
	public String findTagToParse(String val, RtPage rtPage) throws Exception {
		// val = StringEscapeUtils.unescapeHtml(val);
		// val = URLDecoder.decode(val, "UTF-8");
		String regex = "\\$\\{(.+?)\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(val);
		if (matcher.find()) {
			String temp = findTag(val);
			System.out.println("---------"+temp+"------------------");
			if(StringUtil.isEmpty(temp)){
				return val;
			}
			RtEntityParser entityParser = new RtEntityParser(temp);
			String input = entityParser.createInput(rtPage);

			if (entityParser.getSuffix() != null) {
				// val = val.replace(entityParser.getSuffix(), "");
				// System.out.println(entityParser.getSuffix());
			}
			if (entityParser.getJsFuns() != null && !entityParser.getJsFuns().isEmpty()) {
				this.jsFuns.addAll(entityParser.getJsFuns());
			}

			// String ahead = val.substring(0, val.indexOf("$"));
			String behind = val.substring(val.indexOf("$") + temp.length() + 1, val.length());
			// val = ahead + input + behind;
			val = input + behind;
			return findTagToParse(val,rtPage);
		}
		return val;
	}

	/**
	 * 心累的${}嵌套解析, 等待使用正则替换..
	 * 
	 * @param html
	 * @return
	 * @throws Exception
	 */
	private String findTag(String html) throws Exception {
		String regex = "\\$\\{";
		// html = HtmlUtils.trimHtmlTxt(html, true);
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(html);
		StringBuilder val = new StringBuilder();
		if (matcher.find()) {
			int position = 0;
			position = html.indexOf("${") + 1;//
			String temp = html.substring(position, html.length());

			int next$ = temp.indexOf("$");
			if (next$ != -1) {
				temp = temp.substring(0, next$);
			}
			int last = temp.lastIndexOf("}");
			val.append(temp.substring(0, last + 1));
			// position = position + last + 1;
			// html = html.substring(position, html.length());
			// findTag(html);
		}
		return val.toString();

	}

	public String jsFunsToString() {
		if (this.jsFuns != null && !this.jsFuns.isEmpty()) {
			StringBuilder js = new StringBuilder();
			for (String val : jsFuns) {
				js.append(val);
			}
			return js.toString();
		} else {
			return "";
		}
	}

	public List<String> getJsFuns() {
		return jsFuns;
	}

	public void setJsFuns(List<String> jsFuns) {
		this.jsFuns = jsFuns;
	}

	public static void main(String[] args) {
		String html = "<body><p><span>sd</span>fsdfdsf${id:'123',attr:{id:'dd'},ligerui:{id:'ddd',tree:{data:[{id:1}]}}}sdfsd${id:'333',tree:{id:11}}f</p></body>";
		RtHtmlParser parser = new RtHtmlParser();
		try {
			html = parser.parse(html,null);
			System.out.println(html);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
