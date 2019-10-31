package com.khnt.rtbox.template.handle.paging;

import java.io.File;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.flatOpcXml.FlatOpcXmlCreator;
import org.docx4j.dml.CTBlip;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.openpackaging.contenttype.ContentType;
import org.docx4j.openpackaging.contenttype.ContentTypeManager;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.exceptions.InvalidOperationException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.Part;
import org.docx4j.openpackaging.parts.PartName;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPart;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.openpackaging.parts.relationships.RelationshipsPart;
import org.docx4j.openpackaging.parts.relationships.RelationshipsPart.AddPartBehaviour;
import org.docx4j.relationships.Relationship;
import org.docx4j.relationships.Relationships;
import org.docx4j.wml.Body;
import org.docx4j.wml.CTBookmark;
import org.docx4j.wml.CTMarkupRange;
import org.docx4j.wml.CTRel;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.P;
import org.docx4j.wml.P.Hyperlink;
import org.docx4j.wml.SectPr;
import org.docx4j.wml.Text;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.khnt.core.exception.KhntException;
import com.khnt.rtbox.template.constant.RtCharset;
import com.khnt.rtbox.template.constant.RtField;
import com.khnt.rtbox.template.constant.RtPageType;
import com.khnt.rtbox.template.parse.convert.RtCount;
import com.khnt.rtbox.template.parse.page.RtPaging;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.FileUtils;

/**
 * @author ZQ
 * @version 2016年7月5日 上午9:58:42 分页操作，复制分页，删除分页，预览单页
 */
public class RtPagingOption {

	Log log = LogFactory.getLog(getClass());

	/**
	 * 重选目录后重置word
	 * 
	 * @param oldDocPath
	 * @param docPath
	 * @param dirs
	 * @throws Exception
	 */
	public void reset(File oldDocFile, File doc, List<String> dirs) throws KhntException {
		log.debug("RtPagingOption reset begin...");
		try {
			WordprocessingMLPackage oldWordMLPackage = WordprocessingMLPackage.load(oldDocFile);
			MainDocumentPart oldDocumentPart = oldWordMLPackage.getMainDocumentPart();
			List<Object> objs = oldDocumentPart.getContent();

			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
			MainDocumentPart newDocumentPart = wordMLPackage.getMainDocumentPart();

			// normal header,footer
			setSectPr(oldDocumentPart, newDocumentPart,oldWordMLPackage,wordMLPackage);
			// // sick header,footer ,just check
			for (Object obj : objs) {
				if (obj instanceof P) {
					P p = (P) obj;
					if (p.getPPr() != null && p.getPPr().getSectPr() != null) {
						SectPr sectPr1 = p.getPPr().getSectPr();
						List<CTRel> hdrFtrRefs1 = sectPr1.getEGHdrFtrReferences();
						for (CTRel rel : hdrFtrRefs1) {
							Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(rel.getId());
							Relationship newRelationship = newDocumentPart.getRelationshipsPart().getRelationshipByID(rel.getId());
							if (relationship != null && newRelationship == null) {
								Part part = oldDocumentPart.getRelationshipsPart().getPart(relationship);
								FlatOpcXmlCreator.createRawXmlPart(part);
								Relationships rss = part.getRelationshipsPart().getRelationships();

								for (Relationship rs : rss.getRelationship()) {
									Part pt = oldDocumentPart.getRelationshipsPart().getPart(rs);
									if (pt instanceof BinaryPartAbstractImage) {
										log.debug(relationship.getId() + " has ref Id:" + rs.getId() + " part is BinaryPartAbstractImage");
										String extension = rs.getTarget().substring(rs.getTarget().indexOf(".") + 1);
										log.debug("extension:" + extension + ",contentType:" + pt.getContentType());
										PartName partName = new PartName("/word/" + rs.getTarget());
										BinaryPart oldPart = (BinaryPart) oldWordMLPackage.getParts().getParts().get(new PartName("/word/" + rs.getTarget()));
										BinaryPart newPart = new BinaryPart(partName);
										newPart.setBinaryData(oldPart.getBytes());
										newPart.setContentType(new ContentType(oldPart.getContentType()));
										// Namespaces. IMAGE
										newPart.setRelationshipType(rs.getType());
										part.addTargetPart(newPart, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);

										wordMLPackage.getContentTypeManager().addDefaultContentType(extension, pt.getContentType());
									}
								}

								newRelationship = newDocumentPart.addTargetPart(part, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);
								newRelationship.setId(relationship.getId());
							}
						}
					}
				}
			}
			for (String dirName : dirs) {
				boolean begin = false;
				if ("首页".equals(dirName)) {
					begin = true;
				}

				for (Object obj : objs) {
					if (obj instanceof P) {
						P p = (P) obj;
						List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(p, CTBookmark.class);
						for (Object _bookmark : CTBookmarks) {
							CTBookmark bookmark = (CTBookmark) _bookmark;
							String name = bookmark.getName();
							if (name.contains(RtPaging.PAGE_PRE)) {
								begin = false;
								if (name.endsWith(dirName)) {
									begin = true;
								}
							}
						}
					}
					if (begin) {
						newDocumentPart.getContent().add(obj);
						// copy link
						List<Object> links = Docx4jUtil.getAllElementFromObject(obj, Hyperlink.class);
						for (Object _hyperlink : links) {
							Hyperlink hyperlink = (Hyperlink) _hyperlink;

							Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId());
							try {
								newDocumentPart.getRelationshipsPart().addRelationship(relationship);
							} catch (InvalidOperationException e) {
								if ("External".equalsIgnoreCase(relationship.getTargetMode())) {
									Relationship newRelationship = newDocumentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId());
									this.log.debug("you template looks like something went wrong,old : " + relationship.getTarget() + " , new :" + newRelationship.getTarget());
									continue;
								} else {
									e.printStackTrace();
									throw e;
								}
							}
						}
						// copy image
						List<Object> drawings = Docx4jUtil.getAllElementFromObject(obj, Drawing.class);
						for (Object _drawing : drawings) {
							Drawing drawing = (Drawing) _drawing;
							List<Object> inlines = drawing.getAnchorOrInline();
							for (Object _inline : inlines) {
								Inline inline = (Inline) _inline;
								String embed = inline.getGraphic().getGraphicData().getPic().getBlipFill().getBlip().getEmbed();
								if (StringUtils.isEmpty(embed)) {
									continue;
								}
								Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(embed);

								PartName partName = new PartName("/word/" + relationship.getTarget());
								BinaryPart oldPart = (BinaryPart) oldWordMLPackage.getParts().getParts().get(new PartName("/word/" + relationship.getTarget()));
								BinaryPart newPart = new BinaryPart(partName);
								newPart.setBinaryData(oldPart.getBytes());
								newPart.setContentType(new ContentType(oldPart.getContentType()));
								// Namespaces. IMAGE
								newPart.setRelationshipType(relationship.getType());
								Relationship newRelationship = wordMLPackage.getMainDocumentPart().addTargetPart(newPart);
								newRelationship.setId(relationship.getId());
							}
						}
					}
				}
			}
			HashMap<PartName, Part> parts = oldWordMLPackage.getParts().getParts();
			for (PartName key : parts.keySet()) {
				// header and foot deal with setSectPr
				if ("xml".equals(key.getExtension()) && !key.getName().contains("document") && !key.getName().contains("header") && !key.getName().contains("footer")) {
					Part part = parts.get(key);
					FlatOpcXmlCreator.createRawXmlPart(part);
					wordMLPackage.getMainDocumentPart().addTargetPart(part, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);
				}
			}
			wordMLPackage.save(doc);
		} catch (Exception e) {
			this.log.error(e);
			throw new KhntException(e);
		}
		log.debug("RtPagingOption reset success...");
	}

	public void setSectPr(MainDocumentPart oldDocumentPart, MainDocumentPart newDocumentPart, WordprocessingMLPackage oldWordMLPackage, WordprocessingMLPackage wordMLPackage) throws Docx4JException {
		Body body = oldDocumentPart.getContents().getBody();
		newDocumentPart.getContents().getBody().setSectPr(body.getSectPr());
		List<CTRel> hdrFtrRefs = body.getSectPr().getEGHdrFtrReferences();
		for (CTRel rel : hdrFtrRefs) {
			Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(rel.getId());
			if (relationship != null) {
				Part part = oldDocumentPart.getRelationshipsPart().getPart(relationship);
				FlatOpcXmlCreator.createRawXmlPart(part);
				Relationships rss = part.getRelationshipsPart().getRelationships();

				for (Relationship rs : rss.getRelationship()) {
					Part pt = oldDocumentPart.getRelationshipsPart().getPart(rs);
					if (pt instanceof BinaryPartAbstractImage) {
						log.debug(relationship.getId() + " has ref Id:" + rs.getId() + " part is BinaryPartAbstractImage");
						String extension = rs.getTarget().substring(rs.getTarget().indexOf(".") + 1);
						log.debug("extension:" + extension + ",contentType:" + pt.getContentType());
						PartName partName = new PartName("/word/" + rs.getTarget());
						BinaryPart oldPart = (BinaryPart) oldWordMLPackage.getParts().getParts().get(new PartName("/word/" + rs.getTarget()));
						BinaryPart newPart = new BinaryPart(partName);
						newPart.setBinaryData(oldPart.getBytes());
						newPart.setContentType(new ContentType(oldPart.getContentType()));
						// Namespaces. IMAGE
						newPart.setRelationshipType(rs.getType());
						part.addTargetPart(newPart, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);

						wordMLPackage.getContentTypeManager().addDefaultContentType(extension, pt.getContentType());
					}
				}
				
				Relationship newRelationship = newDocumentPart.addTargetPart(part, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);
				newRelationship.setId(relationship.getId());
			}
		}
	}

	public void copyPage(File oldDocFile, File doc, String dirName, String newCode, String newDirName) throws KhntException {
		log.debug("RtPagingOption copyPage begin...");
		try {
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(oldDocFile);
			MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
			List<Object> objs = documentPart.getContent();
			org.docx4j.relationships.ObjectFactory factory = new org.docx4j.relationships.ObjectFactory();
			boolean begin = false;
			if ("首页".equals(dirName)) {
				begin = true;
			}
			List<Object> newContent = new ArrayList<Object>();
			int idx = -1;
			for (Object obj : objs) {
				if (obj instanceof P) {
					P p = (P) obj;
					List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(p, CTBookmark.class);
					for (Object _bookmark : CTBookmarks) {
						CTBookmark bookmark = (CTBookmark) _bookmark;
						String name = bookmark.getName();
						if (name.contains(RtPaging.PAGE_PRE)) {
							begin = false;
							if (name.contains(dirName) && !"首页".equals(dirName)) {
								begin = true;
								// 排除已复制分页
								String regex = "__.*__";
								Pattern pattern = Pattern.compile(regex);
								Matcher matcher = pattern.matcher(name);
								if (matcher.find()) {
									begin = false;
								}
							}
						}
					}
				}
				if (begin) {

					String xml = XmlUtils.marshaltoString(obj);
					Object newObj = XmlUtils.unmarshalString(xml);
					if (idx < 0) {
						idx = documentPart.getContent().indexOf(obj);
						if (newObj instanceof P) {
							P p = (P) newObj;
							List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(p, CTBookmark.class);
							List<Object> CTMarkupRanges = Docx4jUtil.getAllElementFromObject(p, CTMarkupRange.class);
							for (Object _bookmark : CTBookmarks) {
								CTBookmark bookmark = (CTBookmark) _bookmark;
								String name = bookmark.getName();
								if (name.contains(RtPaging.PAGE_PRE) && name.contains(dirName)) {
									String[] tmpNames = name.split("__");
									if (tmpNames.length < 2) {
										throw new Exception("error copy page.." + dirName);
									}
									bookmark.setName(tmpNames[0] + "__" + newCode + "__" + newDirName);
									String length = newCode.split("_")[1];// 4_1
									BigInteger bmId = new BigInteger(String.valueOf(bookmark.getId().intValue() + Integer.parseInt(length) + 50000));
									bookmark.setId(bmId);
									((CTMarkupRange) CTMarkupRanges.get(0)).setId(bmId);
								}
							}
						}
					}
					newContent.add(newObj);

					// copy link,edit new link id、 target
					List<Object> links = Docx4jUtil.getAllElementFromObject(newObj, Hyperlink.class);
					for (Object _hyperlink : links) {
						Hyperlink hyperlink = (Hyperlink) _hyperlink;
						Relationship relationship = documentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId());
						String target = relationship.getTarget();
						target = parseTarget(target, newCode, hyperlink);
						Relationship rel = factory.createRelationship();
						rel.setType(relationship.getType());
						rel.setTarget(target);
						rel.setTargetMode(relationship.getTargetMode());
						documentPart.getRelationshipsPart().addRelationship(rel);
						hyperlink.setId(rel.getId());
					}

					// copy image
					List<Object> drawings = Docx4jUtil.getAllElementFromObject(newObj, Drawing.class);
					for (Object _drawing : drawings) {
						Drawing drawing = (Drawing) _drawing;
						List<Object> inlines = drawing.getAnchorOrInline();
						for (Object _inline : inlines) {
							Inline inline = (Inline) _inline;
							CTBlip CTBlip = inline.getGraphic().getGraphicData().getPic().getBlipFill().getBlip();
							String embed = CTBlip.getEmbed();
							if (StringUtils.isEmpty(embed)) {
								continue;
							}
							Relationship relationship = documentPart.getRelationshipsPart().getRelationshipByID(embed);

							PartName partName = new PartName("/word/" + relationship.getTarget());
							BinaryPart oldPart = (BinaryPart) wordMLPackage.getParts().getParts().get(new PartName("/word/" + relationship.getTarget()));
							BinaryPart newPart = new BinaryPart(partName);
							newPart.setBinaryData(oldPart.getBytes());
							newPart.setContentType(new ContentType(oldPart.getContentType()));
							// Namespaces. IMAGE
							newPart.setRelationshipType(relationship.getType());
							wordMLPackage.getMainDocumentPart().addTargetPart(newPart);
						}
					}

				}
			}
			documentPart.getContent().addAll(idx + newContent.size(), newContent);
			wordMLPackage.save(doc);
		} catch (Exception e) {
			e.printStackTrace();
			this.log.error(e);
			throw new KhntException(e);
		}

		log.debug("RtPagingOption copyPage end...");
	}

	public void delPage(File oldDocFile, File doc, String dirName, String newCode) throws KhntException {
		log.debug("RtPagingOption delPage begin...");
		try {
			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(oldDocFile);
			MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
			List<Object> objs = documentPart.getContent();
			boolean begin = false;
			if ("首页".equals(dirName)) {
				begin = true;
			}
			List<Object> newContent = new ArrayList<Object>();
			for (Object obj : objs) {
				if (obj instanceof P) {
					P p = (P) obj;
					List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(p, CTBookmark.class);
					for (Object _bookmark : CTBookmarks) {
						CTBookmark bookmark = (CTBookmark) _bookmark;
						String name = bookmark.getName();
						if (name.contains(RtPaging.PAGE_PRE)) {
							begin = false;
							if (name.contains(dirName) && !"首页".equals(dirName)) {
								begin = true;
							}
						}
					}
				}
				if (begin) {
					newContent.add(obj);
					// del link
					List<Object> links = Docx4jUtil.getAllElementFromObject(obj, Hyperlink.class);
					for (Object _hyperlink : links) {
						Hyperlink hyperlink = (Hyperlink) _hyperlink;
						Relationship relationship = documentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId());
						documentPart.getRelationshipsPart().removeRelationship(relationship);
					}

					// shouldn't del image
				}
			}
			documentPart.getContent().removeAll(newContent);
			wordMLPackage.save(doc);
		} catch (Exception e) {
			this.log.error(e);
			throw new KhntException(e);
		}

		log.debug("RtPagingOption delPage end...");
	}

	public String parseTarget(String target, String newCode, Hyperlink hyperlink) throws Exception {
		String regex = RtPageType.TABLE + "\\d{" + RtCount.DEFAULT_BIT + "}";
		String id = findVal(regex, target);
		if (id == null) {
			return target;
		}
		String newId = RtField.getName(id, newCode);
		List<Object> objs = Docx4jUtil.getAllElementFromObject(hyperlink, Text.class);
		for (Object obj : objs) {
			Text text = (Text) obj;
			String textValue = text.getValue();
			textValue = textValue.replace(id, newId);
			text.setValue(textValue);
		}
		target = target.replace(id, newId);
		return target;
	}

	public String replaceId(String xml, String newCode) {
		String regex = "TBL\\d{" + RtCount.DEFAULT_BIT + "}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(xml);
		while (matcher.find()) {
			String matchVal = matcher.group();
			xml = xml.replace(matchVal, matchVal + newCode);
		}
		return xml;
	}

	/**
	 * 将老ID替换为新分页ID，并将超链接替换为insertStr
	 * 
	 * @param xmlFile
	 * @param newCode
	 * @return
	 * @throws Exception
	 */
	@Deprecated
	public String parseXml(String xml, File relsFile, String newCode) throws Exception {
		SAXReader reader = new SAXReader();
		Document rels = reader.read(relsFile);
		@SuppressWarnings("unchecked")
		List<Element> rs = rels.selectNodes("/Relationships");

		StringBuilder page = new StringBuilder();
		String regex = "TBL\\d{" + RtCount.DEFAULT_BIT + "}";
		String rIdRegex = "rId\\d{1,10}";
		int position = 0;
		String hyperEnd = "</w:hyperlink>";
		int hyperEndLength = hyperEnd.length();

		while (position >= 0) {
			int startHyperPos = xml.indexOf("<w:hyperlink ", position);
			if (startHyperPos < 0) {
				break;
			}
			int endHyperPos = xml.indexOf(hyperEnd, startHyperPos);
			endHyperPos += hyperEndLength;

			String hyperlink = xml.substring(startHyperPos, endHyperPos);
			String id = findVal(regex, hyperlink);
			if (id == null) {
				continue;
			}
			String newId = RtField.getName(id, newCode);
			String rid = findVal(rIdRegex, hyperlink);
			if (rid == null) {
				throw new Exception("错误的超链接未找到rid...");
			}
			// 获取超链接
			Element relationship = this.getValRels(rs, rid);
			String linkText = relationship.attributeValue("Target");
			linkText = linkText.replace(id, newId);
			// String newHyperlink = LinkTagFactory.createLinkTagStr(newId,
			// linkText);
			String newHyperlink = null;
			page.append(xml.substring(position, startHyperPos));
			page.append(newHyperlink);
			position = endHyperPos;
		}
		page.append(xml.substring(position, xml.length()));

		// 处理唯一
		xml = RtDistinctAsst.execute(newCode, page.toString());
		return xml;
	}

	public String findVal(String regex, String xml) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(xml);
		if (matcher.find()) {
			String matchVal = matcher.group();
			return matchVal;
		}
		return null;
	}

	@Deprecated
	@SuppressWarnings("unchecked")
	public Element getValRels(List<Element> rs, String rid) throws Exception {
		Element relationship = null;
		for (Element rr : rs) {
			List<Element> rrs = rr.elements("Relationship");
			for (Element rrr : rrs) {
				if (rid.equals(rrr.attributeValue("Id"))) {
					relationship = rrr;
					break;
				}
			}
			if (relationship != null) {
				break;
			}
		}
		// LINK
		if (relationship == null) {
			throw new Exception("xml与rels的值不匹配...");
		}
		return relationship;
	}

	/**
	 * 删除分页
	 * 
	 * @param oldXmlPath
	 * @param xmlPath
	 * @param rtCode
	 * @param oldCode
	 * @param relsPath
	 * @throws Exception
	 */
	@Deprecated
	public void delPage(String oldXmlPath, String newXmlPath, String rtCode, String oldCode, String relsPath) throws Exception {
		StringBuilder page = new StringBuilder();
		// String basePath = Factory.getWebRoot();
		// 查找到需要复制的节点 <w:endBreak code=?>
		File oldFile = new File(oldXmlPath);
		if (!oldFile.exists()) {
			throw new KhntException("没有找到 rtCode[" + rtCode + "]中老版本LEVEL0XML：" + oldXmlPath);
		}
		String fullXml = FileUtils.readFileToString(oldFile, RtCharset.XML);
		int startPos = fullXml.indexOf("<w:startBreak code=\"" + oldCode + "\" />");
		if (startPos < 0) {
			throw new KhntException("没有找到 rtCode[" + rtCode + "]XML模板中的开始分页代码:" + oldCode);
		}

		String endRegex = "<w:endBreak code=\"" + oldCode + "\" />";
		int endPos = fullXml.indexOf(endRegex);
		if (endPos < 0) {
			throw new KhntException("没有找到 rtCode[" + rtCode + "]XML模板中的结束分页代码:" + oldCode);
		}
		endPos += endRegex.length();

		page.append(fullXml.substring(0, startPos));
		page.append(fullXml.substring(endPos, fullXml.length()));
		FileUtils.saveFileFromString(new File(newXmlPath), page.toString(), RtCharset.XML);
	}

	public void previewSingle(File oldDocFile, File doc, String dirName, String newCode) throws KhntException {
		log.debug("RtPagingOption previewSingle begin...");
		try {
			WordprocessingMLPackage oldWordMLPackage = WordprocessingMLPackage.load(oldDocFile);
			MainDocumentPart oldDocumentPart = oldWordMLPackage.getMainDocumentPart();

			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
			MainDocumentPart newDocumentPart = wordMLPackage.getMainDocumentPart();

			List<Object> objs = oldDocumentPart.getContent();
			org.docx4j.relationships.ObjectFactory factory = new org.docx4j.relationships.ObjectFactory();
			boolean begin = false;
			if ("首页".equals(dirName)) {
				begin = true;
			}
			// 处理复制页问题
			int l = newCode.lastIndexOf("_");
			String suffix = newCode.substring(l + 1, newCode.length());

			List<Object> newContent = new ArrayList<Object>();
			int idx = -1;
			for (Object obj : objs) {
				if (obj instanceof P) {
					P p = (P) obj;
					List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(p, CTBookmark.class);
					for (Object _bookmark : CTBookmarks) {
						CTBookmark bookmark = (CTBookmark) _bookmark;
						String name = bookmark.getName();

						if (name.contains(RtPaging.PAGE_PRE)) {
							begin = false;
							if ((name.endsWith(dirName) || (name + suffix).endsWith(dirName)) && !"首页".equals(dirName)) {
								begin = true;
								String tmp = null;
								if (newCode.contains("_")) {
									tmp = "_" + newCode + "__";
									if (!name.contains(tmp)) {
										// 处理附页复制页情况pingZhou20171020
										tmp = "_" + newCode.substring(0, l) + "__";
										System.out.println("------------------" + tmp);
										if (!name.contains(tmp)) {
											begin = false;
											break;
										}

									}
								} else {
									String regex = "__.*__";
									Pattern pattern = Pattern.compile(regex);
									Matcher matcher = pattern.matcher(name);
									if (matcher.find()) {
										begin = false;
										break;
									}
								}
							}
						}
					}
				}
				if (begin) {
					if (idx < 0) {
						idx = oldDocumentPart.getContent().indexOf(obj);
					}
					String xml = XmlUtils.marshaltoString(obj);
					Object newObj = XmlUtils.unmarshalString(xml);
					// documentPart.getContent().add(idx + 1, newObj);
					newContent.add(newObj);
					List<Object> links = Docx4jUtil.getAllElementFromObject(newObj, Hyperlink.class);
					for (Object _hyperlink : links) {
						Hyperlink hyperlink = (Hyperlink) _hyperlink;
						Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId());
						String target = relationship.getTarget();
						Relationship rel = factory.createRelationship();
						rel.setType(relationship.getType());
						rel.setTarget(target);
						rel.setTargetMode(relationship.getTargetMode());
						newDocumentPart.getRelationshipsPart().addRelationship(rel);
						hyperlink.setId(rel.getId());
					}

					// copy image
					List<Object> drawings = Docx4jUtil.getAllElementFromObject(obj, Drawing.class);
					for (Object _drawing : drawings) {
						Drawing drawing = (Drawing) _drawing;
						List<Object> inlines = drawing.getAnchorOrInline();
						for (Object _inline : inlines) {
							Inline inline = (Inline) _inline;
							String embed = inline.getGraphic().getGraphicData().getPic().getBlipFill().getBlip().getEmbed();
							if (StringUtils.isEmpty(embed)) {
								continue;
							}
							Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(embed);

							PartName partName = new PartName("/word/" + relationship.getTarget());
							BinaryPart oldPart = (BinaryPart) oldWordMLPackage.getParts().getParts().get(new PartName("/word/" + relationship.getTarget()));
							BinaryPart newPart = new BinaryPart(partName);
							newPart.setBinaryData(oldPart.getBytes());
							newPart.setContentType(new ContentType(oldPart.getContentType()));
							// Namespaces. IMAGE
							newPart.setRelationshipType(relationship.getType());
							Relationship newRelationship = wordMLPackage.getMainDocumentPart().addTargetPart(newPart);
							newRelationship.setId(relationship.getId());
						}
					}

					if (obj instanceof P) {
						P p = (P) obj;
						if (p.getPPr() != null && p.getPPr().getSectPr() != null) {
							SectPr sectPr = p.getPPr().getSectPr();
							List<CTRel> hdrFtrRefs = sectPr.getEGHdrFtrReferences();
							for (CTRel rel : hdrFtrRefs) {
								Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(rel.getId());
								if (relationship != null) {
									Part part = oldDocumentPart.getRelationshipsPart().getPart(relationship);
									// resolve part store has changed,and
									// sourcePartStore not set
									FlatOpcXmlCreator.createRawXmlPart(part);
									newDocumentPart.addTargetPart(part, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);
									if (newDocumentPart.getRelationshipsPart().getRelationshipByID(rel.getId()) == null) {
										newDocumentPart.getRelationshipsPart().addRelationship(relationship);
									}

								}
							}
						}
					}

				}
			}

			newDocumentPart.getContent().addAll(newContent);

			HashMap<PartName, Part> parts = oldWordMLPackage.getParts().getParts();
			for (PartName key : parts.keySet()) {
				if ("xml".equals(key.getExtension()) && !key.getName().contains("document")) {
					Part part = parts.get(key);
					FlatOpcXmlCreator.createRawXmlPart(part);
					wordMLPackage.getMainDocumentPart().addTargetPart(part, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);
				}
			}
			wordMLPackage.save(doc);
			// Docx4J.save(wordMLPackage2, doc, Docx4J.FLAG_SAVE_ZIP_FILE);
		} catch (Exception e) {
			this.log.error(e);
			throw new KhntException(e);
		}

		log.debug("RtPagingOption previewSingle end...");
	}

	public static void main(String[] args) {
		RtPagingOption rpo = new RtPagingOption();
		List<String> test = new ArrayList<String>();
		test.add("首页");
		rpo.reset(new File("C:\\Users\\ZQ\\Desktop\\TEST\\1.docx"), new File("C:\\Users\\ZQ\\Desktop\\TEST\\3t.docx"), test);
	}

	public static void copyPart(RelationshipsPart oldRelationshipsPart, MainDocumentPart newDocumentPart, String type) throws Docx4JException {
		Relationship rel = oldRelationshipsPart.getRelationshipByType(type);
		if (rel != null) {
			Part p = oldRelationshipsPart.getPart(rel);
			FlatOpcXmlCreator.createRawXmlPart(p);
			newDocumentPart.addTargetPart(p, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);
		}
	}

	public static void copy(RelationshipsPart oldRelationshipsPart, MainDocumentPart newDocumentPart, ContentTypeManager newContentTypeManager) throws Docx4JException {
		// copyPart(oldRelationshipsPart, newDocumentPart, Namespaces.THEME);
		// copyPart(oldRelationshipsPart, newDocumentPart, Namespaces.STYLES);
		// copyPart(oldRelationshipsPart, newDocumentPart,
		// Namespaces.WEB_SETTINGS);
		// copyPart(oldRelationshipsPart, newDocumentPart,
		// Namespaces.FONT_TABLE);
		// copyPart(oldRelationshipsPart, newDocumentPart, Namespaces.COMMENTS);
		// copyPart(oldRelationshipsPart, newDocumentPart,
		// Namespaces.COMMENTS_EXTENDED);
		// copyPart(oldRelationshipsPart, newDocumentPart, Namespaces.ENDNOTES);
		// copyPart(oldRelationshipsPart, newDocumentPart,
		// Namespaces.FOOTNOTES);
		// copyPart(oldRelationshipsPart, newDocumentPart,
		// Namespaces.NUMBERING);
		// copyPart(oldRelationshipsPart, newDocumentPart, Namespaces.HEADER);
		// copyPart(oldRelationshipsPart, newDocumentPart, Namespaces.FOOTER);
	}
	

	/**
	 * 重置模板word
	 * 
	 * @param oldDocPath
	 * @param docPath
	 * @param dirs
	 * @throws Exception
	 */
	public void resetModel(File oldDocFile, File doc, List<String> dirs) throws KhntException {
		log.debug("RtPagingOption reset begin...");
		try {
			WordprocessingMLPackage oldWordMLPackage = WordprocessingMLPackage.load(oldDocFile);
			MainDocumentPart oldDocumentPart = oldWordMLPackage.getMainDocumentPart();
			List<Object> objs = oldDocumentPart.getContent();

			WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
			MainDocumentPart newDocumentPart = wordMLPackage.getMainDocumentPart();
			org.docx4j.relationships.ObjectFactory factory = new org.docx4j.relationships.ObjectFactory();
			// normal header,footer
			setSectPr(oldDocumentPart, newDocumentPart,oldWordMLPackage,wordMLPackage);
			List<Object> newContent = new ArrayList<Object>();
			int idx = -1;
			// // sick header,footer ,just check
			for (Object obj : objs) {
				if (obj instanceof P) {
					P p = (P) obj;
					if (p.getPPr() != null && p.getPPr().getSectPr() != null) {
						SectPr sectPr1 = p.getPPr().getSectPr();
						List<CTRel> hdrFtrRefs1 = sectPr1.getEGHdrFtrReferences();
						for (CTRel rel : hdrFtrRefs1) {
							Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(rel.getId());
							Relationship newRelationship = newDocumentPart.getRelationshipsPart().getRelationshipByID(rel.getId());
							if (relationship != null && newRelationship == null) {
								Part part = oldDocumentPart.getRelationshipsPart().getPart(relationship);
								FlatOpcXmlCreator.createRawXmlPart(part);
								Relationships rss = part.getRelationshipsPart().getRelationships();

								for (Relationship rs : rss.getRelationship()) {
									Part pt = oldDocumentPart.getRelationshipsPart().getPart(rs);
									if (pt instanceof BinaryPartAbstractImage) {
										log.debug(relationship.getId() + " has ref Id:" + rs.getId() + " part is BinaryPartAbstractImage");
										String extension = rs.getTarget().substring(rs.getTarget().indexOf(".") + 1);
										log.debug("extension:" + extension + ",contentType:" + pt.getContentType());
										PartName partName = new PartName("/word/" + rs.getTarget());
										BinaryPart oldPart = (BinaryPart) oldWordMLPackage.getParts().getParts().get(new PartName("/word/" + rs.getTarget()));
										BinaryPart newPart = new BinaryPart(partName);
										newPart.setBinaryData(oldPart.getBytes());
										newPart.setContentType(new ContentType(oldPart.getContentType()));
										// Namespaces. IMAGE
										newPart.setRelationshipType(rs.getType());
										part.addTargetPart(newPart, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);

										wordMLPackage.getContentTypeManager().addDefaultContentType(extension, pt.getContentType());
									}
								}

								newRelationship = newDocumentPart.addTargetPart(part, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);
								newRelationship.setId(relationship.getId());
							}
						}
					}
				}
			}
			int endNameLT = 0;
			String precode = "";
			for (String dirName : dirs) {
				boolean begin = false;
				boolean copy = false;
				String endNameL = "";
				String newCode = "";
				
				if ("首页".equals(dirName)) {
					begin = true;
				}
				
				for (Object obj : objs) {
					/*if (obj instanceof P) {
						P p = (P) obj;
						List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(p, CTBookmark.class);
						for (Object _bookmark : CTBookmarks) {
							CTBookmark bookmark = (CTBookmark) _bookmark;
							String name = bookmark.getName();
							if (name.contains(RtPaging.PAGE_PRE)) {
								begin = false;
								if (name.contains(dirName) && !"首页".equals(dirName)) {
									begin = true;
									// 排除已复制分页
									String regex = "__.*__";
									Pattern pattern = Pattern.compile(regex);
									Matcher matcher = pattern.matcher(name);
									if (matcher.find()) {
										begin = false;
									}
								}
							}
						}
					}*/
					if (obj instanceof P) {
						P p = (P) obj;
						List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(p, CTBookmark.class);
						for (Object _bookmark : CTBookmarks) {
							CTBookmark bookmark = (CTBookmark) _bookmark;
							String name = bookmark.getName();
							if (name.contains(RtPaging.PAGE_PRE)) {
								begin = false;
								copy = false;
								String[] names = name.split("_");
								String endName = names[names.length-1];
								endNameL = endName;
								if (name.endsWith(dirName)) {
									begin = true;
									endNameLT = 0;
								}else if(dirName.startsWith(endName)){
									
									endNameLT = endNameLT + 1;
									
									begin = true;
									copy = true;
								}/*else{
									endNameLT = 0;
								}*/
							}
						}
					}
					if (begin) {
						if(!copy){
							newDocumentPart.getContent().add(obj);
							// copy link
							List<Object> links = Docx4jUtil.getAllElementFromObject(obj, Hyperlink.class);
							for (Object _hyperlink : links) {
								Hyperlink hyperlink = (Hyperlink) _hyperlink;

								Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId());
								try {
									newDocumentPart.getRelationshipsPart().addRelationship(relationship);
								} catch (InvalidOperationException e) {
									if ("External".equalsIgnoreCase(relationship.getTargetMode())) {
										Relationship newRelationship = newDocumentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId());
										this.log.debug("you template looks like something went wrong,old : " + relationship.getTarget() + " , new :" + newRelationship.getTarget());
										continue;
									} else {
										e.printStackTrace();
										throw e;
									}
								}
							}
							// copy image
							List<Object> drawings = Docx4jUtil.getAllElementFromObject(obj, Drawing.class);
							for (Object _drawing : drawings) {
								Drawing drawing = (Drawing) _drawing;
								List<Object> inlines = drawing.getAnchorOrInline();
								for (Object _inline : inlines) {
									Inline inline = (Inline) _inline;
									String embed = inline.getGraphic().getGraphicData().getPic().getBlipFill().getBlip().getEmbed();
									if (StringUtils.isEmpty(embed)) {
										continue;
									}
									Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(embed);

									PartName partName = new PartName("/word/" + relationship.getTarget());
									BinaryPart oldPart = (BinaryPart) oldWordMLPackage.getParts().getParts().get(new PartName("/word/" + relationship.getTarget()));
									BinaryPart newPart = new BinaryPart(partName);
									newPart.setBinaryData(oldPart.getBytes());
									newPart.setContentType(new ContentType(oldPart.getContentType()));
									// Namespaces. IMAGE
									newPart.setRelationshipType(relationship.getType());
									Relationship newRelationship = wordMLPackage.getMainDocumentPart().addTargetPart(newPart);
									newRelationship.setId(relationship.getId());
								}
							}
						}else{
						
							String xml = XmlUtils.marshaltoString(obj);
							Object newObj = XmlUtils.unmarshalString(xml);
							
							if (idx < 0) {
								idx = oldDocumentPart.getContent().indexOf(obj);
								if (newObj instanceof P) {
									P p = (P) newObj;
									List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(p, CTBookmark.class);
									List<Object> CTMarkupRanges = Docx4jUtil.getAllElementFromObject(p, CTMarkupRange.class);
									for (Object _bookmark : CTBookmarks) {
										CTBookmark bookmark = (CTBookmark) _bookmark;
										String name = bookmark.getName();
										if (name.contains(RtPaging.PAGE_PRE)) {
											String[] tmpNames = name.split("__");
											if (tmpNames.length < 2) {
												throw new Exception("error copy page.." + dirName);
											}
											String codes = name.split("__")[0];
											String code = codes.substring(7, codes.length());
											if(!precode.equals(code)) {
												endNameLT = 1;
											}
											precode = code;
											newCode = code+"_"+endNameLT ;
											bookmark.setName(tmpNames[0] + "__" + code+"_"+endNameLT + "__" + endNameL+endNameLT);
											String length = (code+"_"+endNameLT).split("_")[1];// 4_1
											BigInteger bmId = new BigInteger(String.valueOf(bookmark.getId().intValue() + Integer.parseInt(length) + 50000));
											bookmark.setId(bmId);
											((CTMarkupRange) CTMarkupRanges.get(0)).setId(bmId);
										}
									}
								}
							}else {
								idx = oldDocumentPart.getContent().indexOf(obj);
								if (newObj instanceof P) {
									P p = (P) newObj;
									List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(p, CTBookmark.class);
									List<Object> CTMarkupRanges = Docx4jUtil.getAllElementFromObject(p, CTMarkupRange.class);
									for (Object _bookmark : CTBookmarks) {
										CTBookmark bookmark = (CTBookmark) _bookmark;
										String name = bookmark.getName();
										if (name.contains(RtPaging.PAGE_PRE)) {
											String[] tmpNames = name.split("__");
											if (tmpNames.length < 2) {
												throw new Exception("error copy page.." + dirName);
											}
											String codes = name.split("__")[0];
											String code = codes.substring(7, codes.length());
											if(!precode.equals(code)) {
												endNameLT = 1;
											}
											precode = code;
											newCode = code+"_"+endNameLT ;
											bookmark.setName(tmpNames[0] + "__" + code+"_"+endNameLT + "__" + endNameL+endNameLT);
											String length = (code+"_"+endNameLT).split("_")[1];// 4_1
											BigInteger bmId = new BigInteger(String.valueOf(bookmark.getId().intValue() + Integer.parseInt(length) + 50000));
											bookmark.setId(bmId);
											((CTMarkupRange) CTMarkupRanges.get(0)).setId(bmId);
										}
									}
								}
							}
							newContent.add(newObj);

							// copy link,edit new link id、 target
							// copy link
							// copy link,edit new link id、 target
							List<Object> links = Docx4jUtil.getAllElementFromObject(newObj, Hyperlink.class);
							for (Object _hyperlink : links) {
								Hyperlink hyperlink = (Hyperlink) _hyperlink;
								Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId());
								String target = relationship.getTarget();
								target = parseTarget(target, newCode, hyperlink);
								Relationship rel = factory.createRelationship();
								rel.setType(relationship.getType());
								rel.setTarget(target);
								rel.setTargetMode(relationship.getTargetMode());
								newDocumentPart.getRelationshipsPart().addRelationship(rel);
								hyperlink.setId(rel.getId());
							}
							// copy image
							List<Object> drawings = Docx4jUtil.getAllElementFromObject(obj, Drawing.class);
							for (Object _drawing : drawings) {
								Drawing drawing = (Drawing) _drawing;
								List<Object> inlines = drawing.getAnchorOrInline();
								for (Object _inline : inlines) {
									Inline inline = (Inline) _inline;
									String embed = inline.getGraphic().getGraphicData().getPic().getBlipFill().getBlip().getEmbed();
									if (StringUtils.isEmpty(embed)) {
										continue;
									}
									Relationship relationship = oldDocumentPart.getRelationshipsPart().getRelationshipByID(embed);

									PartName partName = new PartName("/word/" + relationship.getTarget());
									BinaryPart oldPart = (BinaryPart) oldWordMLPackage.getParts().getParts().get(new PartName("/word/" + relationship.getTarget()));
									BinaryPart newPart = new BinaryPart(partName);
									newPart.setBinaryData(oldPart.getBytes());
									newPart.setContentType(new ContentType(oldPart.getContentType()));
									// Namespaces. IMAGE
									newPart.setRelationshipType(relationship.getType());
									Relationship newRelationship = wordMLPackage.getMainDocumentPart().addTargetPart(newPart);
									newRelationship.setId(relationship.getId());
								}
							}
							newDocumentPart.getContent().add(newObj);
						}
						
					}
				}
			}
			HashMap<PartName, Part> parts = oldWordMLPackage.getParts().getParts();
			for (PartName key : parts.keySet()) {
				// header and foot deal with setSectPr
				if ("xml".equals(key.getExtension()) && !key.getName().contains("document") && !key.getName().contains("header") && !key.getName().contains("footer")) {
					Part part = parts.get(key);
					FlatOpcXmlCreator.createRawXmlPart(part);
					wordMLPackage.getMainDocumentPart().addTargetPart(part, AddPartBehaviour.OVERWRITE_IF_NAME_EXISTS);
				}
			}
			
			wordMLPackage.save(doc);
		} catch (Exception e) {
			this.log.error(e);
			throw new KhntException(e);
		}
		log.debug("RtPagingOption reset success...");
	}

	
}
