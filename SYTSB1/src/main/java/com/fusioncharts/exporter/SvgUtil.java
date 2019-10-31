package com.fusioncharts.exporter;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.imageio.ImageIO;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.JPEGTranscoder;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.apache.fop.svg.PDFTranscoder;

/**
 * svg转换工具类(以下方法开发足够用了)
 * 
 * @param svg
 * @param pdf
 * @throws IOException
 * @throws TranscoderException
 */
public class SvgUtil {
	// svg文件转成
	public static void convertSvgFile2Pdf(File svg, File pdf)
			throws IOException, TranscoderException {
		InputStream in = new FileInputStream(svg);
		OutputStream out = new FileOutputStream(pdf);
		out = new BufferedOutputStream(out);
		convert2Pdf(in, out);
	}

	// svg文件转为png
	public static void convertSvgFile2Png(File svg, File png)
			throws IOException, TranscoderException {
		InputStream in = new FileInputStream(svg);
		OutputStream out = new FileOutputStream(png);
		out = new BufferedOutputStream(out);
		convert2PNG(in, out);
	}

	// svg文件转为png
	public static BufferedImage convertSvgCode2Png(String svg, File png)
			throws IOException, TranscoderException {
		OutputStream out = new FileOutputStream(png);
		out = new BufferedOutputStream(out);
		convert2PNG(svg, out);
		return ImageIO.read(png);
	}
	
	// svg文件转为png
	public static BufferedImage convertSvgCode2Jpeg(String svg, File png)
			throws IOException, TranscoderException {
		OutputStream out = new FileOutputStream(png);
		out = new BufferedOutputStream(out);
		convert2JPG(svg, out);
		return ImageIO.read(png);
	}

	public static void convert2PNG(InputStream in, OutputStream out)
			throws IOException, TranscoderException {
		Transcoder transcoder = new PNGTranscoder();
		try {
			TranscoderInput input = new TranscoderInput(in);
			try {
				TranscoderOutput output = new TranscoderOutput(out);
				transcoder.transcode(input, output);
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}

	public static void convert2PNG(String svg, OutputStream out)
			throws IOException, TranscoderException {
		InputStream in = new ByteArrayInputStream(svg.getBytes("UTF-8"));
		Transcoder transcoder = new PNGTranscoder();
		try {
			TranscoderInput input = new TranscoderInput(in);
			try {
				TranscoderOutput output = new TranscoderOutput(out);
				transcoder.transcode(input, output);
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}
	public static void convert2JPG(String svg, OutputStream out)
			throws IOException, TranscoderException {
		InputStream in = new ByteArrayInputStream(svg.getBytes("UTF-8"));
		Transcoder transcoder = new JPEGTranscoder();
		try {
			TranscoderInput input = new TranscoderInput(in);
			try {
				TranscoderOutput output = new TranscoderOutput(out);
				transcoder.transcode(input, output);
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}

	// 字符串转成pdf
	public static byte[] convertStr2PdfToByte(String svg, File pdf) throws IOException,
				TranscoderException {
		convertStr2Pdf(svg,pdf);
		return getBytes(pdf);
	}

	// 字符串转成pdf
	public static void convertStr2Pdf(String svg, File pdf) throws IOException,
			TranscoderException {
		InputStream in = new ByteArrayInputStream(svg.getBytes("UTF-8"));
		OutputStream out = new FileOutputStream(pdf);
		out = new BufferedOutputStream(out);
		convert2Pdf(in, out);
	}

	public static void convert2Pdf(InputStream in, OutputStream out)
			throws IOException, TranscoderException {
		Transcoder transcoder = new PDFTranscoder();
		try {
			TranscoderInput input = new TranscoderInput(in);
			try {
				TranscoderOutput output = new TranscoderOutput(out);
				transcoder.transcode(input, output);
			} finally {
				out.close();
			}
		} finally {
			in.close();
		}
	}

	public static void main(String[] args) {
		try {
			convertSvgFile2Pdf(new File("e:\\tt.svg"),
					new File("D:\\tt.pdf"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TranscoderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 获得指定文件的byte数组
	 */
	public static byte[] getBytes(File file) {
		byte[] buffer = null;
		try {
			FileInputStream fis = new FileInputStream(file);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();
			buffer = bos.toByteArray();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer;
	}

}