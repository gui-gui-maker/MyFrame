package com.fusioncharts.exporter.encoders;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.util.Iterator;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

public class BasicEncoder implements Encoder {
	String defaultFormat = "JPEG";

	public void encode(BufferedImage bufferedImage,
			FileImageOutputStream fileImageOutputStream) throws Throwable {
		encode(bufferedImage, fileImageOutputStream, 1.0F);
	}

	public void encode(BufferedImage bufferedImage,
			FileImageOutputStream fileImageOutputStream, float quality)
			throws Throwable {
		encode(bufferedImage, fileImageOutputStream, quality,
				this.defaultFormat);
	}

	public void encode(BufferedImage bufferedImage,
			FileImageOutputStream fileImageOutputStream, float quality,
			String format) throws Throwable {
		Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(format);
		ImageWriter writer = (ImageWriter) writers.next();

		ImageWriteParam iwp = writer.getDefaultWriteParam();

		writer.setOutput(fileImageOutputStream);
		writer.write(null, new IIOImage(bufferedImage, null, null), iwp);
		fileImageOutputStream.close();
	}

	public void encode(BufferedImage bufferedImage, OutputStream outputStream)
			throws Throwable {
		encode(bufferedImage, outputStream, 1.0F);
	}

	public void encode(BufferedImage bufferedImage, OutputStream outputStream,
			float quality) throws Throwable {
		encode(bufferedImage, outputStream, quality, this.defaultFormat);
	}

	public void encode(BufferedImage bufferedImage, OutputStream outputStream,
			float quality, String format) throws Throwable {
		ImageOutputStream ios = null;
		try {
			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(format);
			ImageWriter writer = (ImageWriter) writers.next();

			ImageWriteParam iwp = writer.getDefaultWriteParam();

			ios = ImageIO.createImageOutputStream(outputStream);

			writer.setOutput(ios);
			writer.write(null, new IIOImage(bufferedImage, null, null), iwp);
			ios.close();
		} catch (IllegalArgumentException e) {
			if (ios != null) {
				ios.close();
			}
			throw new Throwable();
		} catch (IOException e) {
			if (ios != null) {
				ios.close();
			}
			throw new Throwable();
		}
	}

	public void encode(BufferedImage bufferedImage, Writer out, float quality,
			String format) throws Throwable {
		ImageOutputStream ios = null;
		try {
			Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(format);
			ImageWriter writer = (ImageWriter) writers.next();

			ImageWriteParam iwp = writer.getDefaultWriteParam();

			ios = ImageIO.createImageOutputStream(out);

			writer.setOutput(ios);
			writer.write(null, new IIOImage(bufferedImage, null, null), iwp);
			ios.close();
		} catch (IllegalArgumentException e) {
			if (ios != null) {
				ios.close();
			}
			throw new Throwable();
		} catch (IOException e) {
			if (ios != null) {
				ios.close();
			}
			throw new Throwable();
		}
	}
}