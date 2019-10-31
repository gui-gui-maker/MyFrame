package com.khnt.rtbox.template.handle.paging;

import com.khnt.rtbox.template.constant.RtField;

/**
 * @author ZQ
 * @version 2016年7月6日 下午5:47:30 类说明
 */
public class RtBookmarkDistinct implements RtDistinctInf {

	@Override
	public void execute(String newCode, String xml) throws Exception {
		StringBuilder page = new StringBuilder();
		int pos = 0;
		String startRegex = "<w:bookmarkStart";
		String markRegex = "w:name=\"";
		String endMarkRegex = "\"";
		int startLength = markRegex.length();
		while (pos >= 0) {
			int startPos = xml.indexOf(startRegex, pos);
			if (startPos < 0) {
				break;
			}
			startPos = xml.indexOf(markRegex, startPos);
			if (startPos < 0) {
				continue;
			}
			startPos += startLength;
			startPos = xml.indexOf(endMarkRegex, startPos);
			if (startPos < 0) {
				continue;
			}
			page.append(xml.substring(pos, startPos));
			page.append(RtField.separator + newCode);
			pos = startPos;
		}
	}
}
