package com.khnt.rtbox.template.handle.paging;

/**
 * @author ZQ
 * @version 2016年7月6日 下午3:47:41 分页进行唯一处理的接口
 */
public interface RtDistinctInf {
	/**
	 * 处理书签的唯一性
	 * 
	 * @throws Exception
	 */
	public void execute(String newCode,String xml) throws Exception;
}
