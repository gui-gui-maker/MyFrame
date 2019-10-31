package com.khnt.rtbox.template.handle.export;

import java.io.File;
import java.math.BigInteger;
import java.util.Map;

import org.docx4j.XmlUtils;
import org.docx4j.jaxb.Context;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTVerticalAlignRun;
import org.docx4j.wml.Color;
import org.docx4j.wml.Drawing;
import org.docx4j.wml.HpsMeasure;
import org.docx4j.wml.RFonts;
import org.docx4j.wml.STVerticalAlignRun;

import com.khnt.base.Factory;
import com.khnt.pub.fileupload.bean.Attachment;
import com.khnt.rtbox.template.components.RtImage;
import com.khnt.rtbox.template.constant.RtExportDataType;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.rtbox.tools.Utils;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2016年7月7日 上午9:29:30 导出事务处理
 */
public class RtExportAsst extends RtExportBase {
	public String mediaPath;
	public static int pId = 0;
	public Map<String, String> imagesMap;

	/**
	 * 特殊操作，例如图片导出，需要将ID先加入导出LIST，再统一操作
	 * 
	 * @param rtExportDatas
	 * @throws Exception
	 */
	public String flush(Map<String, String> imagesMap) throws Exception {
		this.imagesMap = imagesMap;

		log.debug("RtExportAsst flush....");
		Map<String, Object> funcMap = rtExportData.getFuncMap();
		if (funcMap != null && !funcMap.isEmpty()) {
			for (String key : funcMap.keySet()) {
				this.value = funcMap.get(key);
				if (key.equals(RtExportDataType.EXPORT_DATA_IMAGE)) {
					this.imageFlush();
				} else if (key.equals(RtExportDataType.EXPORT_DATA_SIGN)) {
					this.signFlush();
				}
			}
		}

		log.debug("RtExportAsst flush succeed....");
		return this.getRtExportData().getValue();
	}

	@Override
	public void color() throws Exception {
		log.debug("RtExportAsst color for id: " + this.getRtExportData().getName());
		Color color = Context.getWmlObjectFactory().createColor();
		// /color.setVal(Utils.rgbToHex(String.valueOf(value)));
		color.setVal(String.valueOf(value).substring(1, String.valueOf(value).length()));
		if (this.getR().getRPr() == null) {
			this.getR().setRPr(Context.getWmlObjectFactory().createRPr());
		}
		this.getR().getRPr().setColor(color);
	}

	@Override
	public void image() throws Exception {
		this.rtExportAssts.add(this);
	}

	@Override
	public void value() throws Exception {

	}

	public String getMediaPath() {
		return mediaPath;
	}

	public void setMediaPath(String mediaPath) {
		this.mediaPath = mediaPath;
	}

	/**
	 * 朱 2017/03/08
	 */
	@Override
	public void bold() throws Exception {
		log.debug("RtExportAsst size for id: " + this.getRtExportData().getName());
		BooleanDefaultTrue bdt = Context.getWmlObjectFactory().createBooleanDefaultTrue();
		if (this.getR().getRPr() == null) {
			this.getR().setRPr(Context.getWmlObjectFactory().createRPr());
		}
		this.getR().getRPr().setB(bdt);
	}

	/**
	 * 朱 2017/03/08
	 */
	@Override
	public void italic() throws Exception {
		log.debug("RtExportAsst size for id: " + this.getRtExportData().getName());
		BooleanDefaultTrue bdt = Context.getWmlObjectFactory().createBooleanDefaultTrue();
		if (this.getR().getRPr() == null) {
			this.getR().setRPr(Context.getWmlObjectFactory().createRPr());
		}
		this.getR().getRPr().setI(bdt);
	}

	/**
	 * 朱 2017/03/08
	 */
	@Override
	public void size() throws Exception {
		System.out.println("RtExportAsst size for id: " + this.getRtExportData().getName());
		log.debug("RtExportAsst size for id: " + this.getRtExportData().getName());
		HpsMeasure hpsMeasure = Context.getWmlObjectFactory().createHpsMeasure();
		int vs = String.valueOf(value).lastIndexOf(".");
		if (value != null && !"null".equals(value) && !StringUtil.isEmpty(value.toString())) {

			if (vs > 0) {
				hpsMeasure.setVal(new BigInteger(String.valueOf(value).replace("px", "").substring(0, vs)).add(new BigInteger("10")));
			} else {
				hpsMeasure.setVal(new BigInteger(String.valueOf(value).replace("px", "")).add(new BigInteger("10")));
			}
		}
		if (this.getR().getRPr() == null) {
			this.getR().setRPr(Context.getWmlObjectFactory().createRPr());
		}
		this.getR().getRPr().setSz(hpsMeasure);
		this.getR().getRPr().setSzCs(hpsMeasure);
	}

	@Override
	public void family() throws Exception {
		log.debug("RtExportAsst family for id: " + this.getRtExportData().getName());
		RFonts fonts = Context.getWmlObjectFactory().createRFonts();
		fonts.setAscii(String.valueOf(value));
		fonts.setEastAsia(String.valueOf(value));
		fonts.setHAnsi(String.valueOf(value));
		if (this.getR().getRPr() == null) {
			this.getR().setRPr(Context.getWmlObjectFactory().createRPr());
		}
		this.getR().getRPr().setRFonts(fonts);
	}

	@Override
	public void sub() throws Exception {
		log.debug("RtExportAsst sub for id: " + this.getRtExportData().getName());
		CTVerticalAlignRun cTVerticalAlignRun = Context.getWmlObjectFactory().createCTVerticalAlignRun();
		cTVerticalAlignRun.setVal(STVerticalAlignRun.SUBSCRIPT);
		if (this.getR().getRPr() == null) {
			this.getR().setRPr(Context.getWmlObjectFactory().createRPr());
		}
		this.getR().getRPr().setVertAlign(cTVerticalAlignRun);
	}

	@Override
	public void sup() throws Exception {
		log.debug("RtExportAsst sup for id: " + this.getRtExportData().getName());
		CTVerticalAlignRun cTVerticalAlignRun = Context.getWmlObjectFactory().createCTVerticalAlignRun();
		cTVerticalAlignRun.setVal(STVerticalAlignRun.SUPERSCRIPT);
		if (this.getR().getRPr() == null) {
			this.getR().setRPr(Context.getWmlObjectFactory().createRPr());
		}
		this.getR().getRPr().setVertAlign(cTVerticalAlignRun);
	}

	@Deprecated
	public void addPictureImp(int i, String signId) throws Exception {
		this.getR().getContent().clear();
		Attachment att = this.getRtExportData().getAttachment();
		/*
		 * if (i != 0) { R r1 = Context.getWmlObjectFactory().createR(); ((P)
		 * this.getR().getParent()).getContent().add(r1); this.setR(r1); }
		 */
		if (att != null) {
			File imageFile = new File(Factory.getWebRoot() + "/upload/" + att.getFilePath());
			// pingZhou2017/04/12
			Drawing drawing = RtImage.createDrawing(wordMLPackage, imageFile, pId + 1000, pId, signId, null, null, "1000");

			this.getR().getContent().add(drawing);
			pId++;

		} else {
			log.error("未找到任何附件");
		}
	}

	@Override
	public void mark() throws Exception {
		// 导出无需导出标注
	}

	@Override
	public void imageFlush() throws Exception {
		log.debug("RtExportAsst imageFlush for id: " + this.getRtExportData().getName());
		this.getR().getContent().clear();
		if (this.getRtExportData() != null && this.getRtExportData().getAttachment() != null) {
			if (this.imagesMap != null && imagesMap.containsKey(this.getRtExportData().getValue())) {
				String xml = imagesMap.get(this.getRtExportData().getValue());
				Object newObj = XmlUtils.unmarshalString(xml);
				this.getR().getContent().add(newObj);
			} else {
				// Attachment att = atts;
				File imageFile = null;
				try {
					imageFile = new File(RtPath.imagePath + "/" + this.getRtExportData().getAttachment().getFilePath());
					if (!imageFile.exists()) {
						imageFile = new File(this.getRtExportData().getAttachment().getFilePath());
					}
				} catch (Exception e) {
					imageFile = new File(this.getRtExportData().getAttachment().getFilePath());
				}

				// pingZhou2017/04/12
				String img = this.getRtExportData().getImage();
				String width = img.split(";")[0];
				String cx1 = Double.parseDouble(width.substring(6, width.length() - 2)) * 2.54 / 72 * 420 + "";
				int w = cx1.indexOf(".");
				String cx = cx1;
				if (w != -1) {
					cx = cx1.substring(0, w);
				}

				Drawing drawing = RtImage.createDrawing(wordMLPackage, imageFile, Utils.randomInt(1000), 0, this.getRtExportData().getName(), cx, null, null);
				this.getR().getContent().add(drawing);

				String xml = XmlUtils.marshaltoString(drawing);
				imagesMap.put(this.getRtExportData().getValue(), xml);
			}

		} else {
			log.error("未找到任何附件");
		}

	}

	@Override
	public void sign() throws Exception {
		this.rtExportAssts.add(this);
	}

	@Override
	public void signFlush() throws Exception {
		this.getR().getContent().clear();
		if (this.getRtExportData() != null && this.getRtExportData().getAttachment() != null) {
			if (this.imagesMap != null && imagesMap.containsKey(this.getRtExportData().getValue())) {
				String xml = imagesMap.get(this.getRtExportData().getValue());
				Object newObj = XmlUtils.unmarshalString(xml);
				this.getR().getContent().add(newObj);
			} else {
				Drawing drawing = null;
				if(this.getRtExportData().getSignByte()==null){
					File imageFile = new File(Utils.getWebPhysicalPath(this.getRtExportData().getAttachment().getFilePath()));
					drawing = RtImage.createDrawing(wordMLPackage, imageFile, Utils.randomInt(1000), 0, this.getRtExportData().getValue(), null, null, "1000");
					
				}else{
					drawing = RtImage.createDrawingByByte(wordMLPackage, this.getRtExportData().getSignByte(), Utils.randomInt(1000), 0, this.getRtExportData().getValue(), null, null, "1000");
					
				}
				this.getR().getContent().add(drawing);
				String xml = XmlUtils.marshaltoString(drawing);
				imagesMap.put(this.getRtExportData().getValue(), xml);
			}

		} else {
			log.error("未找到任何附件");
		}
	}

}
