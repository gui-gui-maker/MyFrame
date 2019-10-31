package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import com.khnt.utils.StringUtil;

public class Base64Utils {
	public static void main(String[] args) throws Exception {
		 
		//本地图片地址
		String url = "C:/Users/Public/Pictures/Sample Pictures/0eb30f2442a7d9337bfbfd5aa74bd11373f00143.jpg";
		
//		String str = Base64Utils.ImageToBase64ByLocal(url);
		String str ="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EEAAge0CBOy2G7EHAggggAACCCCAAAIIIIAAAggggAACvQkQsOuNmoIQQAABBBBAAAEEEEAAAQQQQAABBBDYLkDAbrsReyCAAAIIIIAAAggggAACCCCAAAIIINCbAAG73qgpCAEEEEAAAQQQQAABBBBAAAEEEEAAge0CBOy2G7EHAggggAACCCCAAAIIIIAAAggggAACvQkQsOuNmoIQQAABBBBAAAEEEEAAAQQQQAABBBDYLkDAbrsReyCAAAIIIIAAAggggAACCCCAAAIIINCbAAG73qgpCAEEEEAAAQQQQAABBBBAAAEEEEAAge0CBOy2G7EHAggggAACCCCAAAIIIIAAAggggAACvQkQsOuNmoIQQAABBBBAAAEEEEAAAQQQQAABBBDYLkDAbrsReyCAAAIIIIAAAggggAACCCCAAAIIINCbAAG73qgpCAEEEEAAAQT4e8PDAAABWElEQVQQQAABBBBAAAEEEEAAge0CBOy2G7EHAggggAACCCCAAAIIIIAAAggggAACvQkQsOuNmoIQQAABBBBAAAEEEEAAAQQQQAABBBDYLkDAbrsReyCAAAIIIIAAAggggAACCCCAAAIIINCbAAG73qgpCAEEEEAAAQQQQAABBBBAAAEEEEAAge0CBOy2G7EHAggggAACCCCAAAIIIIAAAggggAACvQkQsOuNmoIQQAABBBBAAAEEEEAAAQQQQAABBBDYLkDAbrsReyCAAAIIIIAAAggggAACCCCAAAIIINCbAAG73qgpCAEEEEAAAQQQQAABBBBAAAEEEEAAge0CBOy2G7EHAggggAACCCCAAAIIIIAAAggggAACvQkQsOuNmoIQQAABBBBAAAEEEEAAAQQQQAABBBDYLkDAbrsReyCAAAIIIIAAAggggAACCCCAAAIIINCbwP8HFe908IQRPw4AAAAASUVORK5CYII=";
		
		//System.out.println(str);
		
		
		String filePath = "E:"+"\\PRJECT\\2014TSsyts\\SYTS\\target\\m2e-wtp\\web-resources\\upload\\financial\\sign\\a64b96a0-951e-46ba-b1fd-dda45acde0fc.txt";
		StringBuffer sb = new StringBuffer();
	    // BufferedReader:从字符输入流中读取文本，缓冲各个字符，从而实现字符、数组和行的高效读取。
		BufferedReader bufReader = null;
		File file = new File(filePath);
	    // FileReader:用来读取字符文件的便捷类。
		bufReader = new BufferedReader(new FileReader(file));
		// buf = new BufferedReader(new InputStreamReader(new
		// FileInputStream(file)));
		String temp = null;
		while ((temp = bufReader.readLine()) != null) {
			sb.append(temp);
	    }
		//获取图片
		String rs = "";
		rs = sb.toString().substring(22,sb.toString().length()-1);
		System.out.println(sb.toString().substring(0,22));
		Base64Utils.Base64ToImage(rs,"C:/Users/Public/Pictures/Sample Pictures/test2.ico");
	}

	
	/**
	 * 本地图片转换成base64字符串
	 * @param imgFile	图片本地路径
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:40:46
	 */
	public static String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
 
 
		InputStream in = null;
		byte[] data = null;
 
		// 读取图片字节数组
		try {
			in = new FileInputStream(imgFile);
			
			data = new byte[in.available()];
			in.read(data);
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 对字节数组Base64编码
		BASE64Encoder encoder = new BASE64Encoder();
 
		return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}

	
	/**
	 * base64字符串转换成图片
	 * @param imgStr		base64字符串
	 * @param imgFilePath	图片存放路径
	 * @return
	 *
	 * @author ZHANGJL
	 * @dateTime 2018-02-23 14:42:17
	 */
	public static boolean Base64ToImage(String imgStr,String imgFilePath) { // 对字节数组字符串进行Base64解码并生成图片
 
		if (StringUtil.isEmpty(imgStr)) // 图像数据为空
			return false;
 
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] b = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {// 调整异常数据
					b[i] += 256;
				}
			}
 
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(b);
			out.flush();
			out.close();
 
			return true;
		} catch (Exception e) {
			return false;
		}
 
	}
}