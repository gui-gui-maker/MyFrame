package com.khnt.rtbox.template.revert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.khnt.rtbox.tools.Utils;

/**
 * @author ZQ
 * @version 2016年4月6日 上午12:40:18 根据填报数据导出WORD文档
 */
public class RtRevert {
	public String trans(String str, String id, String value) {
		int pos = str.indexOf("$");
		if (pos > 0) {
			str = str.substring(pos, str.length());
		}
		String regex = "(\\$\\{.*id:" + id + ".*\\})";
		// String regex = "(\\$\\{.*\"id\":\"" + id + "\".*\\})";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		if (matcher.find()) {
			int idPosition = str.indexOf(id);
			String preStr = str.substring(0, idPosition);
			int first$Position = preStr.lastIndexOf("${");
			String remainStr = str.substring(idPosition, str.length());
			int $position = remainStr.indexOf("${");
			String tempStr = null;
			if ($position > 0) {
				tempStr = remainStr.substring(0, $position);
				int signPosition = tempStr.lastIndexOf("}");

				return str.substring(0, first$Position) + value + str.substring(idPosition + signPosition + 1, str.length());
			} else {
				int signPosition = remainStr.lastIndexOf("}");
				return str.substring(0, first$Position) + value + str.substring(idPosition + signPosition + 1, str.length());
			}
		}
		return str;

	}

	public String transBlank(String str, String value) {
		String regex = "\\$\\{.*\\}";
		while (Utils.transMatch(str, regex)) {
			int first$Position = str.indexOf("${");
			String remainStr = str.substring(first$Position + "${".length(), str.length());
			int $position = remainStr.indexOf("${");
			String tempStr = null;
			if ($position > 0) {
				tempStr = remainStr.substring(0, $position);
				int signPosition = tempStr.lastIndexOf("}");
				str = str.substring(0, first$Position) + value + str.substring(first$Position + "${".length() + signPosition + 1, str.length());
			} else {
				int signPosition = remainStr.lastIndexOf("}");
				str = str.substring(0, first$Position) + value + str.substring(first$Position + "${".length() + signPosition + 1, str.length());
			}
		}
		return str;
	}
}
