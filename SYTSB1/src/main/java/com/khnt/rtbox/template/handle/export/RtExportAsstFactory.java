package com.khnt.rtbox.template.handle.export;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.R;
import org.springframework.web.context.ContextLoader;

import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.pub.fileupload.dao.AttachmentDao;
import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.template.constant.RtExportDataType;
import com.khnt.rtbox.template.model.RtExportAssts;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年7月7日 下午1:28:26 类说明
 */
public class RtExportAsstFactory {
	// private static RtExportAsst rtExportAsst;
	static Log log = LogFactory.getLog(RtExportAsstFactory.class);

	public static void excute(RtExportData rtExportData, WordprocessingMLPackage wordMLPackage, R r, RtExportAssts rtExportAssts) throws Exception {
		if (rtExportData == null) {
			throw new Exception("rtExportData can't be null...");
		}
		if (wordMLPackage == null) {
			throw new Exception("wordMLPackage can't be null...");
		}

		RtExportAsst rtExportAsst = new RtExportAsst();
		rtExportAsst.execute(rtExportData, wordMLPackage, r, rtExportAssts);
	}

	@Deprecated
	public static void addPicture(WordprocessingMLPackage wordMLPackage, int i, R r, String signId) throws Exception {
		if (wordMLPackage == null) {
			throw new Exception("wordMLPackage can't be null...");
		}

		RtExportAsst rtExportAsst = new RtExportAsst();
		rtExportAsst.addPicture(wordMLPackage, i, r, signId);
	}

	/**
	 * 后处理
	 * 
	 * @param rtExportAssts
	 * @throws Exception
	 */
	@Deprecated
	public static void flushBAK(RtExportAssts rtExportAssts) throws Exception {
		// 加载数据
		parseImageData(rtExportAssts);
		// 处理数据
		ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);
		String failReason = null;
		Map<String, String> imagesMap = new ConcurrentHashMap<String, String>();
		Vector<Future<String>> resultFuture = new Vector<Future<String>>();
		for (RtExportAsst rtExportAsst : rtExportAssts) {
			Future<String> future = fixedThreadPool.submit(new RtExportAsstImageTask(rtExportAsst, imagesMap));
			resultFuture.add(future);
		}
		try {
			// 等待计算结果，最长等待timeout秒，timeout秒后中止任务
			for (Future<String> future : resultFuture) {
				String result = future.get(6000, TimeUnit.SECONDS);
				System.out.println("flush success.." + result);
			}
		} catch (InterruptedException e) {
			failReason = "flush主线程在等待计算结果时被中断！"+e.toString();
			e.printStackTrace();
		} catch (ExecutionException e) {
			failReason = "flush主线程等待计算结果，但计算抛出异常！";
			e.printStackTrace();
		} catch (TimeoutException e) {
			failReason = "flush主线程等待计算结果超时，因此中断任务线程！";
			fixedThreadPool.shutdownNow();
			e.printStackTrace();
		}
		log.debug("flush main thread finished!!");
		if (StringUtil.isNotEmpty(failReason)) {
			log.error(failReason);
		}
		fixedThreadPool.shutdown();
	}

	public static void flush(RtExportAssts rtExportAssts) throws Exception {
		// 加载数据
		parseImageData(rtExportAssts);
		// 处理数据
		Map<String, String> imagesMap = new ConcurrentHashMap<String, String>();
		for (RtExportAsst rtExportAsst : rtExportAssts) {
			try {
				// 
				rtExportAsst.flush(imagesMap);
			} catch (Exception e) {
				log.error("圖片生成報錯："+rtExportAsst.getRtExportData().getValue());
				e.printStackTrace();
			}
			
		}
	}

	
	/**
	 * 加载图片附件数据
	 * 
	 * @param rtExportDatas
	 */
	public static void parseImageData(RtExportAssts rtExportAssts) {
		log.debug("RtExportAsstFactory flush parseImageData....");
		List<String> imageIdsList = new Vector<String>();
		for (RtExportAsst rtExportAsst : rtExportAssts) {
			RtExportData rtExportData = rtExportAsst.getRtExportData();
			Map<String, Object> funcMap = rtExportData.getFuncMap();
			if (funcMap != null && !funcMap.isEmpty()) {
				for (String key : funcMap.keySet()) {
					if (key.equals(RtExportDataType.EXPORT_DATA_IMAGE)) {
						imageIdsList.add(rtExportData.getValue());
					} else if (key.equals(RtExportDataType.EXPORT_DATA_SIGN)) {
						imageIdsList.add(rtExportData.getValue());
					}
				}
			}
		}
		// 查询附件数据
		if (imageIdsList != null && !imageIdsList.isEmpty()) {
			int size = imageIdsList.size();
			StringBuffer imagesSb = new StringBuffer();
			List<Attachment> atts = new ArrayList<Attachment>();
			for (int i = 0; i < size; i++) {
				imagesSb.append(imageIdsList.get(i)).append(",");
				// 100条查询一次
				if (size > 100 && size % 100 == 0) {
					if (imagesSb.length() > 0) {
						imagesSb.deleteCharAt(imagesSb.length() - 1);
					}
					List<Attachment> imageList = getImageAtts(imagesSb.toString());
					if (imageList != null && !imageList.isEmpty()) {
						atts.addAll(imageList);
					}
					imagesSb = new StringBuffer();
				}
			}
			if (imagesSb.length() > 0) {
				imagesSb.deleteCharAt(imagesSb.length() - 1);
			}
			List<Attachment> imageList = getImageAtts(imagesSb.toString());
			if (imageList != null && !imageList.isEmpty()) {
				atts.addAll(imageList);
			}
			if (atts != null && !atts.isEmpty()) {
				for (Attachment att : atts) {
					RtExportAssts reas = rtExportAssts.get(att.getId());
					for (RtExportAsst rtExportAsst : reas) {
						RtExportData red = rtExportAsst.getRtExportData();
						if (red != null) {
							red.setAttachment(att);
						}

					}

				}
			}

		}

		log.debug("RtExportAsstFactory flush parseImageData succeed....");

	}

	/**
	 * 批量查询附件数据
	 * 
	 * @param ids
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Attachment> getImageAtts(String ids) {
		log.debug("RtExportAsstFactory flush parseImageData getImageAtts....");
		if (StringUtil.isEmpty(ids)) {
			return null;
		}
		AttachmentDao attachmentDao = (AttachmentDao) ContextLoader.getCurrentWebApplicationContext().getBean("attachmentDao");
		List<Attachment> atts = attachmentDao.createQuery("from Attachment where id in (:ids)").setParameterList("ids", ids.split(",")).list();
		if (atts == null || atts.isEmpty()) {
			return null;
		}
		log.debug("RtExportAsstFactory flush parseImageData getImageAtts succeed....");
		return atts;
	}
}
