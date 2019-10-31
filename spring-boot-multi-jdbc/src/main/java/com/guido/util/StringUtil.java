package com.guido.util;

public class StringUtil {

	public static boolean isNotEmpty(String a) {
		return null!=a && !"".equals(a);
	}
	
	public static boolean isEmpty(String a) {
		return !isNotEmpty(a);
	}
}
