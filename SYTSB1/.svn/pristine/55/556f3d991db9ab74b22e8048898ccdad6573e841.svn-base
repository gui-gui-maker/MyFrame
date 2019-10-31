package com.khnt.rtbox.template.parse.tag;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import com.khnt.rtbox.config.bean.RtPage;

/**
 * @author ZQ
 * @version 2016年3月14日 下午11:33:23 轮循机制标记接口
 */
public interface Round {
	static Log log = LogFactory.getLog(Round.class);
	public static String BlANKTAG = "${}";

	public void mark(MainDocumentPart documentPart, RtPage rtPage) throws Exception;
}
