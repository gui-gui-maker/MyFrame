package com.khnt.rtbox.template.constant;

import com.khnt.base.Factory;

/**
 * @author ZQ
 * @version 2017年2月9日 上午9:49:43 类说明
 */
public class RtCharset {
	// public static String UTF_8 = "UTF-8";
	public static String XML = "UTF-8";
	public static String JACOB_CHARSET = Factory.getSysPara().getProperty("JACOB_CHARSET", "GBK"); // 最后的/为必须
}
