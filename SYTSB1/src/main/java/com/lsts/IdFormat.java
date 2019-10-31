package com.lsts;


/**
 * 带，号的id组装成字符串，
 * @author ch
 *
 */
public class IdFormat {

	/**
	 * 将id的之间加上
	 * @param id
	 * @param ch id两边加的符号，用于区分字符串和数字等
	 * @return
	 */
	public static String formatIdStr(String id){
		String chStr="'";
		String idStr="";
		String[] ids=id.split(",");
		for(int i=0;i<ids.length;i++){
			idStr+=(i==0?"":",")+chStr+ids[i]+chStr;
		}
		return idStr;
	}
}
