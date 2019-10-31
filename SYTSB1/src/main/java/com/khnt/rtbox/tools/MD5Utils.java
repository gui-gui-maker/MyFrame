package com.khnt.rtbox.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;

/**
 * @author ZQ
 * @version 2016年3月15日 上午11:25:30 类说明
 */
public class MD5Utils {
	static char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * 对文件全文生成MD5摘要
	 * 
	 * @param file
	 * @param MessageDigestType
	 * @return
	 * @throws Exception
	 */
	public static String getMD5(File file, String MessageDigestType) throws Exception {
		FileInputStream fis = null;
		try {
			MessageDigest md = MessageDigest.getInstance(MessageDigestType);
			fis = new FileInputStream(file);
			byte[] buffer = new byte[2048];
			int length = -1;
			while ((length = fis.read(buffer)) != -1) {
				md.update(buffer, 0, length);
			}
			byte[] b = md.digest();
			return byteToHexStringSingle(b);
		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (fis != null) {
					fis.close();
				}
			} catch (IOException ex) {
			}
		}
	}

	/**
	 * 对一段String生成MD5加密信息
	 * 
	 * @param message
	 * @param MessageDigestType
	 * @return
	 * @throws Exception
	 */
	public static String getMD5(String message, String MessageDigestType) throws Exception {
		try {
			MessageDigest md = MessageDigest.getInstance(MessageDigestType);

			byte[] b = md.digest(message.getBytes("utf-8"));
			return byteToHexStringSingle(b);// byteToHexString(b);
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 把byte[]数组转换成十六进制字符串表示形式
	 * 
	 * @param byteArray
	 * @return
	 */
	public static String byteToHexStringSingle(byte[] byteArray) {
		StringBuffer md5StrBuff = new StringBuffer();
		for (int i = 0; i < byteArray.length; i++) {
			if (Integer.toHexString(0xFF & byteArray[i]).length() == 1) {
				md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
			} else {
				md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
			}
		}
		return md5StrBuff.toString();
	}

	public static void main(String[] args) throws Exception {
		String templeteDocFilePath = "D:/rtbox/templete/word/" + "201603/";// 模板存放地

		String _docxFile = templeteDocFilePath + "1.docx";
		String md5 = getMD5(new File(_docxFile), "sha-1");
		System.out.println(md5);
		
		
	}
}
