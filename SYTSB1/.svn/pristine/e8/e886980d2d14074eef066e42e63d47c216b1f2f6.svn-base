package com.khnt.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 字符串工具类
 * 
 * @author 2011-12-29
 */
public class StringUtil {

	static final String RANDOM_CHARS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	private final static Logger log = LoggerFactory.getLogger(StringUtil.class);

	/**
	 * 
	 * 判断字符是否为空.
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(String source) {
		return (source == null || (source.isEmpty()));
	}

	/**
	 * 
	 * 判断字符是否为空.
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isNotEmpty(String source) {
		return !isEmpty(source);
	}

	/**
	 * 
	 * 将字符串转换成utf-8类型
	 * 
	 * @param str
	 * @return
	 */
	public static String transformUtf8(String str) {
		if (StringUtil.isEmpty(str)) {
			return "";
		}
		String restr = str;
		try {
			String encode = getStrEncode(str);
			if(!"GBK".equals(encode)){
				restr = new String(str.getBytes(encode), "utf-8");
			}
			return restr;
		} catch (UnsupportedEncodingException e) {
			log.error(e.getMessage());
			return restr;
		}
	}

	/**
	 * 检测字符串的编码格式
	 * 
	 * @param str
	 * @return
	 */
	public static String getStrEncode(String str) {
		if (str == null || str.isEmpty())
			return null;
		String substr = str.substring(0, 1);
		String[] encodes = new String[] { "UTF-8", "GBK", "ISO-8859-1", "GB2312", "GB18030" };
		for (String encode : encodes) {
			try {
				// 匹配字符编码
				if (substr.equals(new String(substr.getBytes(), encode))) {
					// 返回编码名称
					return encode;
				} else {
					continue;
				}
			} catch (Exception er) {
			}
		}
		return null;
	}
	
	public static void main(String[] args) {
		System.out.println(getStrEncode("你好"));
		
	}
	
	

	/**
	 * 
	 * 随机产生一个UUID
	 * 
	 * @return 返回字符串类型的UUID
	 */
	public static String getUUID() {
		String s = UUID.randomUUID().toString();
		// 去掉“-”符号
		return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
	}

	/**
	 * 
	 * 转换NULL为"";即：空值转换; 如str不为null时原值返回
	 * 
	 * @param str
	 * @return
	 */
	public static String transformNull(String str) {
		return str == null ? "" : str;

	}

	public static String randomChars(int len) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < len; i++) {
			int number = random.nextInt(RANDOM_CHARS.length());
			sb.append(RANDOM_CHARS.charAt(number));
		}
		return sb.toString();
	}

	/**
	 * 将汉字转换为全拼
	 * 
	 * @param src
	 * @return
	 */
	public static String getPingYin(String src) {
		char[] t1 = null;
		t1 = src.toCharArray();
		String[] t2 = new String[t1.length];
		HanyuPinyinOutputFormat t3 = new HanyuPinyinOutputFormat();
		t3.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		t3.setToneType(HanyuPinyinToneType.WITH_TONE_MARK);
		t3.setVCharType(HanyuPinyinVCharType.WITH_U_UNICODE);
		String t4 = "";
		int t0 = t1.length;
		try {
			for (int i = 0; i < t0; i++) {
				// 判断是否为汉字字符
				if (Character.toString(t1[i]).matches("[\\u4E00-\\u9FA5]+")) {
					t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], t3);
					t4 += t2[0];
				} else {
					t4 += Character.toString(t1[i]);
				}
			}
			// System.out.println(t4);
			return t4;
		} catch (BadHanyuPinyinOutputFormatCombination e1) {
			e1.printStackTrace();
		}
		return t4;
	}

	/**
	 * 将字符解析为字符内容原本的对象，比如：字符串"true"、"false"即为布尔值。如果所有字符都是数字的话，表示为数字
	 * 
	 * @return
	 */
	public static Object parseOriginalType(String str) {
		if ("true".equals(str) || "false".equals(str))
			return Boolean.valueOf(str);
		else if (isNumeric(str))
			return new BigDecimal(str);
		else
			return str;
	}

	public static boolean isNumeric(String str) {
		return str.matches("\\d+|\\d+.\\d+");
	}
}