package com.fusioncharts.exporter.encoders;

import java.awt.image.BufferedImage;
import java.io.OutputStream;

import javax.imageio.stream.FileImageOutputStream;

public abstract interface Encoder {
	public abstract void encode(BufferedImage paramBufferedImage,
			FileImageOutputStream paramFileImageOutputStream) throws Throwable;

	public abstract void encode(BufferedImage paramBufferedImage,
			FileImageOutputStream paramFileImageOutputStream, float paramFloat)
			throws Throwable;

	public abstract void encode(BufferedImage paramBufferedImage,
			FileImageOutputStream paramFileImageOutputStream, float paramFloat,
			String paramString) throws Throwable;

	public abstract void encode(BufferedImage paramBufferedImage,
			OutputStream paramOutputStream) throws Throwable;

	public abstract void encode(BufferedImage paramBufferedImage,
			OutputStream paramOutputStream, float paramFloat) throws Throwable;

	public abstract void encode(BufferedImage paramBufferedImage,
			OutputStream paramOutputStream, float paramFloat, String paramString)
			throws Throwable;
}