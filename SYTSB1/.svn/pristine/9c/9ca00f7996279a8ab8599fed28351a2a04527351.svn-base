package com.khnt.rtbox.template.components;

import java.io.File;

import org.docx4j.dml.CTGraphicalObjectFrameLocking;
import org.docx4j.dml.CTNonVisualDrawingProps;
import org.docx4j.dml.CTNonVisualGraphicFrameProperties;
import org.docx4j.dml.CTOfficeArtExtensionList;
import org.docx4j.dml.CTPositiveSize2D;
import org.docx4j.dml.wordprocessingDrawing.Anchor;
import org.docx4j.dml.wordprocessingDrawing.CTEffectExtent;
import org.docx4j.dml.wordprocessingDrawing.CTPosH;
import org.docx4j.dml.wordprocessingDrawing.CTPosV;
import org.docx4j.dml.wordprocessingDrawing.Inline;
import org.docx4j.dml.wordprocessingDrawing.STAlignH;
import org.docx4j.dml.wordprocessingDrawing.STAlignV;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.BinaryPartAbstractImage;
import org.docx4j.wml.Drawing;

/**
 * @author ZQ
 * @version 2017年3月14日 下午2:15:32 类说明
 */
public class RtImage {

	public static Drawing createDrawing(WordprocessingMLPackage wordMLPackage, File imageFile, int docPrId, int cNvPrId, String filenameHint, String width, String altText, String height)
			throws Exception {
		java.io.InputStream is = null;
		try {
			is = new java.io.FileInputStream(imageFile);
			long length = imageFile.length();
			// You cannot create an array using a long type.
			// It needs to be an int type.
			if (length > Integer.MAX_VALUE) {
				throw new Exception("File too large!!");
			}
			byte[] bytes = new byte[(int) length];
			int offset = 0;
			int numRead = 0;
			while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
				offset += numRead;
			}
			// Ensure all the bytes have been read in
			if (offset < bytes.length) {
				throw new Exception("Could not completely read file " + imageFile.getName());
			}
			Drawing drawing = null;
			if (width != null) {
				drawing = newImage(wordMLPackage, bytes, filenameHint, altText, docPrId, cNvPrId, Long.parseLong(width),0);
			
			}else if (height != null) {
				drawing = newImage(wordMLPackage, bytes, filenameHint, altText, docPrId, cNvPrId, 1000,Long.parseLong(height));
			
			} else {
				drawing = newImage(wordMLPackage, bytes, filenameHint, altText, docPrId, cNvPrId);
			}
			return drawing;
		} catch (Exception e) {
			throw e;
		} finally {
			if (is != null) {
				is.close();
			}
		}

	}

	/**
	 * Create image, without specifying width 原尺寸
	 */
	public static Drawing newImage(WordprocessingMLPackage wordMLPackage, byte[] bytes, String filenameHint, String altText, int id1, int id2) throws Exception {

		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);

		Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, false);

		Drawing drawing = Context.getWmlObjectFactory().createDrawing();
		drawing.getAnchorOrInline().add(inline);
		return drawing;

	}

	/**
	 * Create image, specifying width in twips 定义宽度
	 */
	public static Drawing newImage(WordprocessingMLPackage wordMLPackage, byte[] bytes, String filenameHint, String altText, int id1, int id2, long cx,long cy) throws Exception {

		BinaryPartAbstractImage imagePart = BinaryPartAbstractImage.createImagePart(wordMLPackage, bytes);
		CTNonVisualGraphicFrameProperties ct =  new CTNonVisualGraphicFrameProperties();
		Anchor anchor = new Anchor();
		anchor.setAllowOverlap(true);
		anchor.setBehindDoc(false);
		anchor.setLocked(false);
		anchor.setLayoutInCell(true);
		//赋值
		anchor.setAnchorId(bytes);
		//布局
		CTPosH ctPosH = new CTPosH();
		ctPosH.setAlign(STAlignH.CENTER);
		anchor.setPositionH(ctPosH);
		CTPosV ctPosV = new CTPosV();
		ctPosV.setAlign(STAlignV.CENTER);
		anchor.setPositionV(ctPosV);
		//宽度
		Inline inline = imagePart.createImageInline(filenameHint, altText, id1, id2, cx, false);
		/*if(cy!=0){
			CTPositiveSize2D size2d = new CTPositiveSize2D();
			size2d.setCx(cx*560);
			size2d.setCy(cy*560);
			inline.setExtent(size2d);
		}*/
		
		/*CTEffectExtent ce = new CTEffectExtent();
		ce.setB(0);
		ce.setR(695325);
		ce.setL(0);
		ce.setT(0);
		inline.setEffectExtent(ce);
		CTPositiveSize2D size2d = new CTPositiveSize2D();*/
		Drawing drawing = Context.getWmlObjectFactory().createDrawing();
		drawing.getAnchorOrInline().add(inline);
		
		return drawing;

	}

	/**
	 * 通过流插入图片
	 * author pingZhou
	 * @param wordMLPackage
	 * @param signByte
	 * @param docPrId
	 * @param cNvPrId
	 * @param filenameHint
	 * @param width
	 * @param altText
	 * @param height
	 * @return
	 * @throws Exception
	 */
	public static Drawing createDrawingByByte(WordprocessingMLPackage wordMLPackage, byte[] signByte, int docPrId, int cNvPrId, String filenameHint, String width, String altText, String height)
			throws Exception {
		Drawing drawing = null;
		try {
			if (width != null) {
				drawing = newImage(wordMLPackage, signByte, filenameHint, altText, docPrId, cNvPrId, Long.parseLong(width),0);
			
			}else if (height != null) {
				drawing = newImage(wordMLPackage, signByte, filenameHint, altText, docPrId, cNvPrId, 1000,Long.parseLong(height));
			
			} else {
				drawing = newImage(wordMLPackage, signByte, filenameHint, altText, docPrId, cNvPrId);
			}
			
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		return drawing;
	}
}
