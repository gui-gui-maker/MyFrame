package com.fwxm.scientific.web;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontProvider;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.khnt.base.Factory;

public class Html2PdfUtil {

	public static String  convertHtml2PDF(HttpServletResponse response,String content,String fileName)   {
		ByteArrayInputStream inStream = new ByteArrayInputStream(content.getBytes());
		try {

			Document document = new Document(PageSize.A4);
			//document.setMargins(30, 30, 30, 30);
				PdfWriter	pdfwriter = PdfWriter.getInstance(document,response.getOutputStream());
				
				document.open();
				XMLWorkerHelper wh = XMLWorkerHelper.getInstance();
				InputStream cssInput = null;
				
				FontProvider myFontProvider = new FontProvider() {
					@Override
					public boolean isRegistered(String fontname) {
						return true;
					}

					@Override
					public Font getFont(String fontname, String encoding, boolean embedded, float size, int style,
							BaseColor color) {
						BaseFont bf = null;
						try {
//							bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
//							String urlFont=request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
							String path=Factory.getWebRoot()+"simsun.ttc,1";
							bf=BaseFont.createFont(path, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
						} catch (DocumentException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
						Font font = new Font(bf, size, style, color);
						font.setColor(color);
						return font;
					}

				};
				wh.parseXHtml(pdfwriter, document, inStream, cssInput, myFontProvider);
				response.addHeader("Content-Disposition", "attachment;filename=" +
		                new String( (fileName + ".pdf").getBytes(),  
		                        "iso-8859-1"));

				document.close();
		} catch (Exception e) {
			System.out.println(e);
		}
			return null;
	}
}
