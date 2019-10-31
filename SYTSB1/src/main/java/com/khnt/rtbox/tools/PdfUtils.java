package com.khnt.rtbox.tools;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfCopy;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

public class PdfUtils {

	public static void main(String[] args) {
		String[] files = { "D:/aanode/hn1.pdf", "D:/aanode/5.pdf" };
		String savepath = "D:/aanode/total.pdf";
		try {
			// mergePdfFiles(files, savepath);
			mergePdfFilesMarkFoot(files, savepath, 3);
			// createPdf(savepath);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// partitionPdfFile("C:\\a.pdf", 2);
	}

	public static void createPdf(String filename) throws IOException, DocumentException {
		// step 1
		Document document = new Document();
		// step 2
		PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
		RtPdfPageHeaderFooter event = new RtPdfPageHeaderFooter(2, 5);
		writer.setPageEvent(event);
		// step 3
		document.open();
		// step 4
		for (int i = 0; i < 3;) {
			i++;
			// document.add(new Paragraph("Test " + i));
			document.newPage();
		}
		// step 5
		document.close();
	}

	public static void mergePdfFiles(String[] files, String savepath) throws IOException, DocumentException {
		try {
			Document document = new Document(new PdfReader(files[0]).getPageSize(1));

			PdfCopy copy = new PdfCopy(document, new FileOutputStream(savepath));
			HeaderFooter footer = new HeaderFooter(new Phrase("页码："), true);
			footer.setBorder(Rectangle.NO_BORDER);
			document.setFooter(footer);
			document.open();

			for (int i = 0; i < files.length; i++) {
				PdfReader reader = new PdfReader(files[i]);

				int n = reader.getNumberOfPages();

				for (int j = 1; j <= n; j++) {

					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
			}
			document.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void mergePdfFilesMarkFoot(String[] files, String savepath, int startPage)
			throws IOException, DocumentException {
		try {

			int total = 0;
			for (int i = 0; i < files.length; i++) {
				PdfReader reader = new PdfReader(files[i]);
				int n = reader.getNumberOfPages();
				total += n;
			}
			System.out.println("共计:" + total + "页");

			Document document = new Document(PageSize.A4);
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(savepath));
			RtPdfPageHeaderFooter headerFooter = new RtPdfPageHeaderFooter(startPage, total);
			writer.setPageEvent(headerFooter);
			document.open();

			for (int i = 0; i < files.length; i++) {
				PdfReader reader = new PdfReader(files[i]);
				int n = reader.getNumberOfPages();
				for (int j = 1; j <= n; j++) {
					PdfImportedPage page = writer.getImportedPage(reader, j);
					Rectangle newPageSize = new Rectangle(page.getWidth(), page.getHeight());
					document.setPageSize(newPageSize);
					PdfContentByte pdfContentByte = writer.getDirectContent();
					document.newPage();
					pdfContentByte.addTemplate(page, 0, 0);

				}
			}
			document.close();
			writer.close();

		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		} catch (DocumentException e) {
			e.printStackTrace();
			throw e;
		}
	}

	public static void partitionPdfFile(String filepath, int N) {
		Document document = null;
		PdfCopy copy = null;

		try {
			PdfReader reader = new PdfReader(filepath);

			int n = reader.getNumberOfPages();

			if (n < N) {
				System.out.println("The document does not have " + N + " pages to partition !");
				return;
			}

			int size = n / N;
			String staticpath = filepath.substring(0, filepath.lastIndexOf("\\") + 1);
			String savepath = null;
			ArrayList<String> savepaths = new ArrayList<String>();
			for (int i = 1; i <= N; i++) {
				if (i < 10) {
					savepath = filepath.substring(filepath.lastIndexOf("\\") + 1, filepath.length() - 4);
					savepath = staticpath + savepath + "0" + i + ".pdf";
					savepaths.add(savepath);
				} else {
					savepath = filepath.substring(filepath.lastIndexOf("\\") + 1, filepath.length() - 4);
					savepath = staticpath + savepath + i + ".pdf";
					savepaths.add(savepath);
				}
			}

			for (int i = 0; i < N - 1; i++) {
				document = new Document(reader.getPageSize(1));
				copy = new PdfCopy(document, new FileOutputStream(savepaths.get(i)));
				document.open();
				for (int j = size * i + 1; j <= size * (i + 1); j++) {
					document.newPage();
					PdfImportedPage page = copy.getImportedPage(reader, j);
					copy.addPage(page);
				}
				document.close();
			}

			document = new Document(reader.getPageSize(1));
			copy = new PdfCopy(document, new FileOutputStream(savepaths.get(N - 1)));
			document.open();
			for (int j = size * (N - 1) + 1; j <= n; j++) {
				document.newPage();
				PdfImportedPage page = copy.getImportedPage(reader, j);
				copy.addPage(page);
			}
			document.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
}
