package com.khnt.rtbox.tools;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.lowagie.text.Document;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;

public class RtPdfPageHeaderFooter extends PdfPageEventHelper {
	public static WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
	public static String TTF_PATH = webApplicationContext.getServletContext().getRealPath("/").replace("/", "/")
			+ "rtbox/print/ttf/SIMFANG.TTF";
//	public static String TTF_PATH="F:/svn/kh/src/TZSB/src/main/webapp/rtbox/print/ttf/SIMFANG.TTF";
	
	private int startPage;
	private int totalPage;

	public RtPdfPageHeaderFooter(int startPage, int totalPage) {
		this.startPage = startPage;
		this.totalPage = totalPage;
	}

	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		if (writer.getCurrentPageNumber() >= this.startPage) {
			PdfContentByte cb = writer.getDirectContent();
			cb.saveState();

			cb.beginText();

			BaseFont bf = null;
			try {
				bf = BaseFont.createFont(TTF_PATH, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
			} catch (Exception e) {
				e.printStackTrace();
			}

			cb.setFontAndSize(bf, 10);

			// Footer
			float y = document.bottom(-26);

			cb.showTextAligned(PdfContentByte.ALIGN_CENTER,
					"第 " + (writer.getCurrentPageNumber() - this.startPage + 1) + " 页  共 "
							+ (this.totalPage - this.startPage + 1) + " 页",
					(document.right() + document.left()) / 2, y, 0);

			cb.endText();

			cb.restoreState();
		}
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
}
