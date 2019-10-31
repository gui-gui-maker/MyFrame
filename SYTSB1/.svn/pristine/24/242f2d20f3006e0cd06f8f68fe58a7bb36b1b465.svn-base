package com.khnt.rtbox.template.parse.convert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ZQ
 */
public class RtTpl {
	static Log log = LogFactory.getLog(RtTpl.class);

	/**
	 * 读取模板文件
	 * 
	 * @param rtPaging
	 * 
	 * @return
	 * @throws Exception
	 */
	public String getTemplateHtml(String filePath) throws Exception {
		File file = new File(filePath);
		log.debug("getTemplateHtml :" + filePath);
		if (file.exists()) {
			StringBuilder sb = new StringBuilder();
			BufferedReader br = null;
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(file);
				br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
				String line = null;
				while ((line = br.readLine()) != null) {
					sb.append(line + "\n");
				}
				return sb.toString();
			} catch (Exception e) {
				throw e;
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
					}
					br = null;
				}
				if (fis != null) {
					try {
						fis.close();
					} catch (Exception e) {
					}
					fis = null;
				}
			}
		} else {
			throw new Exception("Template html(" + filePath + ") file is not exist ");
		}
	}

}
