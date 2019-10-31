package com.khnt.rtbox.template.parse.page;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.CTBookmark;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.NodeClassFilter;
import org.htmlparser.tags.BodyTag;
import org.htmlparser.tags.HeadTag;
import org.htmlparser.tags.LinkTag;
import org.htmlparser.util.NodeList;

import com.khnt.base.Factory;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.template.components.RtBookmark;
import com.khnt.rtbox.template.constant.RtCharset;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.rtbox.template.model.RtDirectory;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.FileUtils;
import com.khnt.rtbox.tools.HtmlUtils;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年3月16日 上午10:35:05 类说明
 *          <p>
 *          <w:startBreak code="?" /> 开始分页
 *          </p>
 **          <p>
 *          <w:endBreak code="?" /> 结束分页
 *          </p>
 * 
 * 
 */
public class RtPaging {
	static Log log = LogFactory.getLog(RtPaging.class);
	private List<String> breakTags = new ArrayList<String>();
	private List<RtDirectory> splits = new ArrayList<RtDirectory>();
	private List<String> xmlSplits = new ArrayList<String>();
	private Map<String, String> component = new HashMap<String, String>();

	public static String PAGE_PRE = "RTPAGE_";// 书签分页前缀

	/**
	 * 初始化分页标记
	 * <p>
	 * 分页原则：段落中字段大于44号字体
	 * </P>
	 * <p>
	 * <w:bookmarkStart w:id="c_2" w:name="PAGE_额定出口温度" />
	 * </P>
	 * <P>
	 * <w:bookmarkEnd w:id="c_2" />
	 * </P>
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void initPaging(MainDocumentPart documentPart) throws Exception {
		log.debug("RtPage initPaging begin...");
		List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(documentPart, CTBookmark.class);
		for (Object _bookmark : CTBookmarks) {
			CTBookmark bookmark = (CTBookmark) _bookmark;
			String name = bookmark.getName();
			
			if (name.contains(RtPaging.PAGE_PRE)) {
				return;
			}
		}
		List<Object> objs = documentPart.getContent();
		int bookmarkId = 1;
		boolean hasNotice = false;
		boolean beginNotice = false;
		for (Object obj : objs) {
			if (obj instanceof P) {
				P p = (P) obj;
				String title = Docx4jUtil.getElementContent(p);
				title = HtmlUtils.trimHtmlTxt(title, true);// 去掉标签
				if ("注意事项".equals(title)) {
					hasNotice = true;
					break;
				}
			}

		}
		// 按大字号标题分页
		for (int i = 0; i < objs.size(); i++) {
			if (!hasNotice) {
				if (i < 10) {
					continue;
				}
			}

			Object obj = objs.get(i);
			if (obj instanceof P) {
				P p = (P) obj;
				String title = Docx4jUtil.getElementContent(p);
				title = HtmlUtils.trimHtmlTxt(title, true);// 去掉标签
				// 没有标题的P略过
				if (StringUtil.isEmpty(title)) {
					continue;
				}
				if(title.contains("工业压力管道射线检测报告")){
					System.out.println(111111);
				}
				log.debug(title);
				// 没有 R标签的P略过
				List<Object> comentRs = Docx4jUtil.getAllElementFromObject(p, R.class);
				if (comentRs == null || comentRs.isEmpty()) {
					continue;
				}

				if (!beginNotice && "注意事项".equals(title)) {
					beginNotice = true;
					String bookmarkName = PAGE_PRE + bookmarkId + "__" + title;
					R r = Context.getWmlObjectFactory().createR();
					p.getContent().add(0, r);

					RtBookmark.create(p, r, bookmarkName, bookmarkId);
					bookmarkId++;
					continue;
				}
				if (hasNotice && !beginNotice) {
					continue;
				}

				// 如果没有P。ppr.rpr，则查看P下面的R.rpr
				int sz = 0;
				if (p.getPPr() != null && p.getPPr().getRPr() != null && p.getPPr().getRPr().getSz() != null) {
					sz = p.getPPr().getRPr().getSz().getVal().intValue();
				} else {
					for (Object objR : comentRs) {
						R comentR = (R) objR;
						if (comentR != null && comentR.getRPr() != null && comentR.getRPr().getSz() != null) {
							int swapSz = comentR.getRPr().getSz().getVal().intValue();
							if (swapSz > sz) {
								sz = swapSz;
							}
						}
					}
				}
				if (sz < 30) {
					log.debug(" little size: " + sz + ",should not paging ");
					continue;
				} else {
					// 查看是否加粗
					boolean big = false;
					if (p.getPPr() != null && p.getPPr().getRPr() != null && p.getPPr().getRPr().getB() != null) {
						if (p.getPPr().getRPr().getB().isVal()) {
							big = true;
						}
					} else {
						for (Object objR : comentRs) {
							R comentR = (R) objR;
							if (comentR != null && comentR.getRPr() != null && comentR.getRPr().getB() != null) {
								if (comentR.getRPr().getB().isVal()) {
									big = true;
									break;
								}
							}
						}
					}
					// 未加粗查看是否黑体
					if (!big) {
						boolean black = false;
						for (Object objR : comentRs) {
							R comentR = (R) objR;
							if (comentR != null && comentR.getRPr() != null && comentR.getRPr().getRFonts() != null) {
								if ("黑体".equals(comentR.getRPr().getRFonts().getAscii())) {
									black = true;
									break;
								}
							}
						}
						if (!black) {
							continue;
						}
					}

					log.debug(" bigger size: " + sz + ",paging ");

					String bookmarkName = PAGE_PRE + bookmarkId + "__" + title;
					R r = Context.getWmlObjectFactory().createR();
					p.getContent().add(0, r);

					RtBookmark.create(p, r, bookmarkName, bookmarkId);
					bookmarkId++;
				}

			}
		}
		log.debug("RtPage initPaging end...");
	}

	/**
	 * 直接通过字符串分页符判定进行分页XML存储
	 * 
	 * @param file
	 * @throws Exception
	 */
	public void mainBreak(MainDocumentPart documentPart, RtPage rtPage) throws Exception {
		log.debug("RtPage mainBreak begin...");
		List<Object> cTBookmarks = Docx4jUtil.getAllElementFromObject(documentPart, CTBookmark.class);
		for (Object obj : cTBookmarks) {
			CTBookmark cTBookmark = (CTBookmark) obj;
			System.out.println(cTBookmark.getName());
			if (cTBookmark.getName().startsWith(PAGE_PRE)) {
				// find
				// P p = (P) cTBookmark.getParent();
				// if (p == null) {
				// continue;
				// }
				// String title = Docx4jUtil.getElementContent(p);
				String title = cTBookmark.getName().split("__")[1];
				this.breakTags.add(title);
			}
		}
		log.debug("XML total page:" + this.xmlSplits.size());
		log.debug("RtPage mainBreak end...");
	}

	/**
	 * 根据xmlSplits生成XML分页信息已经报告代码命名的全XML
	 * 
	 * @param doc
	 * 
	 * @param rtPage
	 * @throws Exception
	 */
	public void saveXml(RtPage rtPage) throws Exception {
		log.debug("RtPage saveXml begin...");
		// 生成完整模板XML
		String project_path = Factory.getWebRoot();
		String xmlPathFull = null;
		String xmlPath = null;
		if (rtPage != null) {
			xmlPathFull = RtPath.getTplXmlPathFull(rtPage.getRtCode());
			xmlPath = project_path + RtPath.getTplXmlPath(rtPage.getRtCode());
		} else {
			xmlPathFull = RtPath.outputXmlPath + "test/";
			xmlPath = RtPath.outputXmlPath + "test/xml/";
		}

		File file = new File(xmlPathFull);
		if (!file.getParentFile().exists()) {
			file.getParentFile().mkdirs();
		}

		// 按照拆分信息进行页面生成
		for (int i = 1, l = xmlSplits.size(); i <= l; i++) {
			String xml = xmlSplits.get(i - 1);
			String fileName = (rtPage == null ? "" : rtPage.getRtCode()) + i + ".xml";
			File xmlFile = new File(xmlPath + fileName);
			FileUtils.saveFileFromString(xmlFile, xml, RtCharset.XML);
		}
		log.debug("RtPage saveXml end...");
	}

	/**
	 * 根据XML分页标记对HTML进行分页
	 * 
	 * @param content
	 * @param raPage
	 * @throws Exception
	 */
	public void checkSplit(String content, RtPage rtPage) throws Exception {
		// System.out.println(content);
		log.debug("RtPaging checkSplit...");
		Parser headParser = Parser.createParser(content, RtCharset.XML);
		// 获取头部信息
		NodeFilter filter = new NodeClassFilter(HeadTag.class);
		NodeList nodes = headParser.extractAllNodesThatMatch(filter);
		int startHeadPos = 0;
		int startBodyPos = 0;
		int endBodyPos = 0;
		int endHtmlPos = 0;

		if (nodes.size() == 1) {
			Node tag = nodes.elementAt(0);
			startHeadPos = tag.getEndPosition();
		} else {
			throw new Exception("checkSplit html error: not only one head....");
		}

		// 获取BODY信息
		Parser bodyParser = Parser.createParser(content, RtCharset.XML);
		filter = new NodeClassFilter(BodyTag.class);
		nodes = bodyParser.extractAllNodesThatMatch(filter);
		if (nodes.size() == 1) {
			Node tag = nodes.elementAt(0);
			startBodyPos = tag.getStartPosition();
			endBodyPos = tag.getEndPosition();
			endHtmlPos = content.lastIndexOf("</body>");
		} else {
			throw new Exception("checkSplit html error: not only one body....");
		}
		component.put("head", content.substring(startHeadPos, startBodyPos - "</head>".length()));
		content = content.substring(endBodyPos, endHtmlPos);

		// 根据是否分页进行采集
		if ("0".equalsIgnoreCase(rtPage.getIsPage())) {
			RtDirectory rtDirectory = new RtDirectory();
			rtDirectory.setName("全报表");
			rtDirectory.setCode("1");
			rtDirectory.setPageContent(content);
			splits.add(rtDirectory);
		} else {
			Parser htmlParser = Parser.createParser(content, RtCharset.XML);
			filter = new NodeClassFilter(LinkTag.class);
			nodes = htmlParser.extractAllNodesThatMatch(filter);

			int position = 0;

			for (int i = 0, l = breakTags.size(); i < l; i++) {
				String breakTag = breakTags.get(i);
				boolean flag = false;
				for (int len = 0; len < nodes.size(); len++) {
					LinkTag tag = (LinkTag) nodes.elementAt(len);
					String name = tag.getAttribute("name");
					if (!name.contains(RtPaging.PAGE_PRE)) {
						continue;
					}
					if (name.endsWith(breakTag)) {
						// if (breakTag.equals(text)) {
						String html = tag.toHtml();
						if (!html.contains(PAGE_PRE)) {
							System.out.println("find:" + html);
							continue;
						}
						RtDirectory rtDirectory = new RtDirectory();
						int idx = splits.size();
						if (idx == 0) {
							rtDirectory.setName("首页");
						} else {
							rtDirectory.setName(breakTags.get(idx - 1));
						}
						String code = String.valueOf(splits.size() + 1);
						rtDirectory.setCode(code);
						if (position > tag.getStartPosition()) {
							continue;
						}
						rtDirectory.setPageContent(content.substring(position, tag.getStartPosition()));
						splits.add(rtDirectory);
						position = tag.getParent().getStartPosition();
						flag = true;
						break;
					}
				}
				if (!flag) {
					throw new Exception("没有找到分页：" + this.breakTags.get(i));
				}
			}
			RtDirectory rtDir = new RtDirectory();
			int idx = splits.size();
			if (idx == 0) {
				rtDir.setName("首页");
			} else {
				rtDir.setName(breakTags.get(idx - 1));
			}
			rtDir.setCode(String.valueOf(splits.size() + 1));
			rtDir.setPageContent(content.substring(position, content.length()));
			splits.add(rtDir);
			System.out.println("HTML总分页:" + splits.size());
			log.debug("HTML总分页:" + splits.size());
			if (splits.size() != (this.breakTags.size() + 1)) {
				throw new Exception("分页中有未知字符，导致分页失败");
			}
		}

	}

	public String getBookmarkName(String id, String name) {
		return PAGE_PRE + id + "__" + name;
	}

	public List<RtDirectory> getSplits() {
		return splits;
	}

	public Map<String, String> getComponent() {
		return component;
	}

}
