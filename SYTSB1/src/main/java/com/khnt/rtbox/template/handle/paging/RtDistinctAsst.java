package com.khnt.rtbox.template.handle.paging;


/**
 * @author ZQ
 * @version 2016年7月6日 下午5:44:21 分页复制唯一性处理助手类
 */
public class RtDistinctAsst {

	public static String execute(String newCode, String xml) throws Exception {
		RtBookmarkDistinct rtDistinct = new RtBookmarkDistinct();
		rtDistinct.execute(newCode, xml);
		return xml;
	}
}
