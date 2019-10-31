package com.khnt.rtbox.template.handle.export;

import java.util.Map;
import java.util.concurrent.Callable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ZQ
 * @version 2017年10月11日 下午5:29:58 类说明
 */
class RtExportAsstImageTask implements Callable<String> {
	RtExportAsst rtExportAsst;
	Map<String, String> imagesMap;
	static Log log = LogFactory.getLog(RtExportAsstImageTask.class);

	public RtExportAsstImageTask(RtExportAsst rtExportAsst, Map<String, String> imagesMap) {
		this.rtExportAsst = rtExportAsst;
		this.imagesMap = imagesMap;
	}

	@Override
	public String call() throws Exception {
		return rtExportAsst.flush(imagesMap);
	}

}
