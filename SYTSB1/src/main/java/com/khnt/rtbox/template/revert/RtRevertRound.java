package com.khnt.rtbox.template.revert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Br;
import org.docx4j.wml.CTBookmark;
import org.docx4j.wml.P;
import org.docx4j.wml.STBrType;
import org.docx4j.wml.P.Hyperlink;

import com.khnt.core.exception.KhntException;
import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.template.handle.export.RtExportAsstFactory;
import com.khnt.rtbox.template.model.RtExportAssts;
import com.khnt.rtbox.template.parse.page.RtPaging;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.HtmlUtils;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年5月31日 下午5:07:33 去掉HYPERLINK，导出WORD文档
 */
public class RtRevertRound {
	static Log log = LogFactory.getLog(RtRevertRound.class);

	public void replaceTag(RtPage rtPage, Map<String, RtExportData> transMap, WordprocessingMLPackage wordMLPackage, HashMap<String, Object> infoMap, String view) throws Exception {
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
		log.debug("RtRevertRound replace $ tag begin...");
		RtRevert rtRevert = new RtRevert();
		List<Object> rs = Docx4jUtil.getAllElementFromObject(documentPart, Hyperlink.class);
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		String failReason = null;
		RtExportAssts rtExportAssts = new RtExportAssts();
		Vector<Future<RtExportAssts>> resultFuture = new Vector<Future<RtExportAssts>>();
		for (Object obj : rs) {
			Hyperlink hyperlink = (Hyperlink) obj;
			String id = hyperlink.getId();
			String title = documentPart.getRelationshipsPart().getRelationshipByID(id).getTarget();
			if (StringUtil.isNotEmpty(title)) {
				title = HtmlUtils.decode(title);
				if(title.contains("id")) {
					Future<RtExportAssts> future = fixedThreadPool.submit(new RtFilterTask(transMap, title, rtRevert, hyperlink, wordMLPackage, infoMap));
					resultFuture.add(future);
				}
			}
		}
		try {
			// 等待计算结果，最长等待timeout秒，timeout秒后中止任务
			for (Future<RtExportAssts> future : resultFuture) {
				RtExportAssts reds = future.get(6000, TimeUnit.SECONDS);
				if (reds != null && !reds.isEmpty()) {
					rtExportAssts.addAll(reds);
				}
			}
		} catch (InterruptedException e) {
			failReason = "主线程在等待计算结果时被中断！";
			e.printStackTrace();
		} catch (ExecutionException e) {
			failReason = "主线程等待计算结果，但计算抛出异常！";
			e.printStackTrace();
		} catch (TimeoutException e) {
			failReason = "主线程等待计算结果超时，因此中断任务线程！";
			fixedThreadPool.shutdownNow();
			e.printStackTrace();
		}

		log.debug("main thread finished!!");
		if (StringUtil.isNotEmpty(failReason)) {
			log.error(failReason);
			throw new KhntException("导出报告时线程发生错误");
		}
		fixedThreadPool.shutdown();
		
		
		if(view==null||transMap==null){
			System.out.println("-------------------"+view+"---------------------");
			List<Object> CTBookmarks = Docx4jUtil.getAllElementFromObject(documentPart, CTBookmark.class);
			boolean flag = true;
			boolean flagm = false;
			for (Object _bookmark : CTBookmarks) {
				CTBookmark bookmark = (CTBookmark) _bookmark;
				String name = bookmark.getName();
				
				if (name.contains(RtPaging.PAGE_PRE)) {
					if(name.contains("目录")){
						//报告有目录页
						flagm = true;
					}
					//||name.endsWith("检验报告")
					if((name.contains("结论报告"))&&flagm){
						flag = false;
					}
					if(flag){
						continue;
					}
					Br breakObj = new Br();
					breakObj.setType(STBrType.PAGE);
					Object po = bookmark.getParent();
					
					if(po instanceof P){
						P p = (P)po;
						List<Object> list = p.getContent();
						List<Object> listN = new ArrayList<Object>();
						for (int i = 0; i < list.size(); i++) {
							listN.add(list.get(i));
						}
						Object obj = list.get(0);
						p.getContent().removeAll(list);
						p.getContent().add(breakObj);
						//p.getContent().add(obj);
						for (int i = 0; i < listN.size(); i++) {
							p.getContent().add(listN.get(i));
						}
						
						//documentPart.addObject(p);
					}
					//P paragraph =  Context.getWmlObjectFactory().createP();
					
				}
			}
			
			
		}
		
//		log.debug("main thread finished!!");
//		if (StringUtil.isNotEmpty(failReason)) {
//			log.error(failReason);
//		}
//		fixedThreadPool.shutdown();

		// important!
		if (rtExportAssts != null && !rtExportAssts.isEmpty()) {
			RtExportAsstFactory.flush(rtExportAssts);
		}
		log.debug("RtRevertRound replaceTag successed...");
	}

	public static void main(String[] args) {
		String value = "${id:TBL00509,name:TBL00509,type:checkbox,ligerui:{data:[{id:符合要求,text:符合要求},{id:不符合要求,text:不符合要求},{id:无此项,text:无此项}]}}";
		String regex = "data:\\[(.*)\\]";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		if (matcher.find()) {
			System.out.println(matcher.group(1));
		} else {
			System.out.println("no..");
		}
	}
}
