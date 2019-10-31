package com.khnt.rtbox.template.parse.tag;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

import com.khnt.rtbox.config.bean.RtPage;

/**
 * @author ZQ
 * @version 2016年3月11日 下午1:33:37 轮循机制
 */
public abstract class Tag {
	static Log log = LogFactory.getLog(Tag.class);

	private List<Round> members = new ArrayList<Round>();

	/**
	 * 注册轮循机制成员
	 * 
	 * @param member
	 */
	public void attach(Round member) {
		this.members.add(member);
		log.debug(("attach round member ... " + member.getClass()));
	}

	/**
	 * 删除轮循机制成员
	 * 
	 * @param member
	 */
	public void detach(Round member) {
		this.detach(member);
		log.debug(("detach round member ... " + member.getClass()));
	}

	/**
	 * 执行成员标记
	 * @param rtPage 
	 * 
	 * @throws Exception
	 */
	public void mark(MainDocumentPart documentPart, RtPage rtPage) throws Exception {
		log.debug(("begin to mark one by one... "));
		for (Round member : members) {
			member.mark(documentPart,rtPage);
		}
		log.debug(("all mark successed ... "));
	}
}
