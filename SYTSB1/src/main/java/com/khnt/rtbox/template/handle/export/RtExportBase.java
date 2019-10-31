package com.khnt.rtbox.template.handle.export;

import java.util.Map;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.R;

import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.template.constant.RtExportDataType;
import com.khnt.rtbox.template.model.RtExportAssts;

/**
 * @author ZQ
 * @version 2016年7月7日 上午11:24:31 类说明
 */
public abstract class RtExportBase implements RtExportInf {
	public RtExportData rtExportData;
	public Object value;
	public WordprocessingMLPackage wordMLPackage;
	public MainDocumentPart documentPart;
	public R r;

	public RtExportAssts rtExportAssts;// 需要后处理的数据；主要 用于导出图片

	/**
	 * 执行
	 * 
	 */
	public void execute(RtExportData rtExportData, WordprocessingMLPackage wordMLPackage, R r, RtExportAssts rtExportAssts) throws Exception {
		this.setRtExportData(rtExportData);
		if (this.getRtExportData() == null) {
			throw new Exception("保存出错，数据为空，请设置....");
		}
		this.setWordMLPackage(wordMLPackage);
		if (wordMLPackage != null) {
			this.setDocumentPart(wordMLPackage.getMainDocumentPart());
		}
		this.setR(r);
		this.setRtExportAssts(rtExportAssts);

		Map<String, Object> funcMap = rtExportData.getFuncMap();
		if (funcMap != null && !funcMap.isEmpty()) {
			for (String key : funcMap.keySet()) {
				this.value = funcMap.get(key);
				if (key.equals(RtExportDataType.EXPORT_DATA_COLOR)) {
					this.color();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_IMAGE)) {
					this.image();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_VALUE)) {
					this.value();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_BOLD)) {
					this.bold();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_ITALIC)) {
					this.italic();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_SIZE)) {
					this.size();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_FAMILY)) {
					this.family();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_SUB)) {
					this.sub();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_SUP)) {
					this.sup();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_MARK)) {
					this.mark();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_SIGN)) {
					this.sign();
				}else if (key.equals("page")) {
					continue;
				} else {
					throw new Exception("未知的处理方式");
				}
			}
		}
	
	}

	/**
	 * ZQ EDIT 2017 1011 已将此方法合并到sign()
	 * 
	 * @param wordMLPackage
	 * @param i
	 * @param r
	 * @param signId
	 * @throws Exception
	 */
	@Deprecated
	public void addPicture(WordprocessingMLPackage wordMLPackage, int i, R r, String signId) throws Exception {
		this.setWordMLPackage(wordMLPackage);
		if (wordMLPackage != null) {
			this.setDocumentPart(wordMLPackage.getMainDocumentPart());
		}
		this.setR(r);

		this.addPictureImp(i, signId);
	}

	public RtExportData getRtExportData() {
		return rtExportData;
	}

	public void setRtExportData(RtExportData rtExportData) {
		this.rtExportData = rtExportData;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public WordprocessingMLPackage getWordMLPackage() {
		return wordMLPackage;
	}

	public void setWordMLPackage(WordprocessingMLPackage wordMLPackage) {
		this.wordMLPackage = wordMLPackage;
	}

	public MainDocumentPart getDocumentPart() {
		return documentPart;
	}

	public void setDocumentPart(MainDocumentPart documentPart) {
		this.documentPart = documentPart;
	}

	public R getR() {
		return r;
	}

	public void setR(R r) {
		this.r = r;
	}

	public RtExportAssts getRtExportAssts() {
		return rtExportAssts;
	}

	public void setRtExportAssts(RtExportAssts rtExportAssts) {
		this.rtExportAssts = rtExportAssts;
	}

}
