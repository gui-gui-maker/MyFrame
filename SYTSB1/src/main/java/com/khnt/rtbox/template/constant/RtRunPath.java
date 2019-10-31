package com.khnt.rtbox.template.constant;

import com.khnt.base.Factory;

/**
 * @author ZQ
 * @version 2016年3月16日 下午9:45:06 类说明
 */
public class RtRunPath {

	// public static String PROJECT_PATH =
	// "D:/workspace3/Rtbox/src/main/webapp/"; // 最后的/为必须
	//public static String PROJECT_PATH = Factory.getSysPara().getProperty("PROJECT_PATH", ""); // 最后的/为必须
	 public static String PROJECT_PATH = "".equals(RtPath.getPropetyValue("PROJECT_PATH", ""))?
			 Factory.getWebRoot():RtPath.getPropetyValue("PROJECT_PATH", "");
}
