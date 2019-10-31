package com.khnt.rtbox.template.model;

import java.util.Iterator;
import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.template.handle.export.RtExportAsst;

/**
 * @author ZQ
 * @version 2017年10月11日 上午9:54:55 类说明
 */
public class RtExportAssts extends Vector<RtExportAsst> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RtExportAssts get(String id) {
		if (StringUtils.isEmpty(id)) {
			return null;
		}
		Iterator<RtExportAsst> param = this.iterator();
		RtExportAssts rtExportAssts = null;
		while (param.hasNext()) {
			RtExportAsst rtExportAsst = param.next();
			RtExportData rtExportData = rtExportAsst.getRtExportData();
			if (id.equals(rtExportData.getValue())) {
				if (rtExportAssts == null) {
					rtExportAssts = new RtExportAssts();
				}
				rtExportAssts.add(rtExportAsst);
			}
		}
		return rtExportAssts;
	}

}
