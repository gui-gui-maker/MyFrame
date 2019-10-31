package com.fusioncharts.exporter;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.UUID;

import com.fusioncharts.exporter.beans.ChartMetadata;
import com.fusioncharts.exporter.beans.ExportBean;
import com.fusioncharts.exporter.beans.ExportConfiguration;
import com.fusioncharts.exporter.beans.FusionChartsExportData;

public class FusionChartsExportHelper {
	private static HashMap<String, String> mimeTypes = new HashMap();

	private static HashMap<String, String> extensions = new HashMap();

	private static HashMap<String, String> handlerAssociationsMap = new HashMap();

	static {
		handlerAssociationsMap.put("PDF", "PDF");
		handlerAssociationsMap.put("JPEG", "IMG");
		handlerAssociationsMap.put("JPG", "IMG");
		handlerAssociationsMap.put("PNG", "IMG");
		handlerAssociationsMap.put("GIF", "IMG");

		mimeTypes.put("jpg", "image/jpeg");
		mimeTypes.put("jpeg", "image/jpeg");
		mimeTypes.put("png", "image/png");
		mimeTypes.put("gif", "image/gif");
		mimeTypes.put("pdf", "application/pdf");

		extensions.put("jpeg", "jpg");
		extensions.put("jpg", "jpg");
		extensions.put("png", "png");
		extensions.put("gif", "gif");
		extensions.put("pdf", "pdf");
	}

	public static HashMap<String, String> bang(String strParams) {
		HashMap params = new HashMap();
		if (strParams != null) {
			StringTokenizer stPipe = new StringTokenizer(strParams, "|");

			while (stPipe.hasMoreTokens()) {
				String keyValue = stPipe.nextToken();
				String[] keyValueArr = keyValue.split("=");
				if (keyValueArr.length > 1) {
					params.put(keyValueArr[0].toLowerCase(), keyValueArr[1]);
				}
			}
		}
		return params;
	}

	public static String getExportHandlerClassName(String strFormat) {
		String exporterSuffix = (String) handlerAssociationsMap.get(strFormat
				.toUpperCase());
		exporterSuffix = exporterSuffix != null ? exporterSuffix : strFormat
				.toUpperCase();
		String path = ExportConfiguration.RESOURCEPACKAGE + "."
				+ ExportConfiguration.EXPORTHANDLER
				+ exporterSuffix.toUpperCase();
		return path;
	}

	public static String getExtensionFor(String format) {
		return (String) extensions.get(format);
	}

	public static HashMap<String, String> getHandlerAssociationsMap() {
		return handlerAssociationsMap;
	}

	public static String getMimeTypeFor(String format) {
		return (String) mimeTypes.get(format);
	}

	public static HashMap<String, String> getMimeTypes() {
		return mimeTypes;
	}

	public static String getUniqueFileName(String filePath, String extension) {
		UUID uuid = UUID.randomUUID();
		String uid = uuid.toString();
		String uniqueFileName = filePath + "." + extension;
		do {
			uniqueFileName = filePath;

			if (!ExportConfiguration.FILESUFFIXFORMAT
					.equalsIgnoreCase("TIMESTAMP")) {
				uniqueFileName = uniqueFileName + uid + "_" + Math.random();
			} else {
				SimpleDateFormat sdf = new SimpleDateFormat("dMyHms");
				String date = sdf.format(Calendar.getInstance().getTime());
				uniqueFileName = uniqueFileName + uid + "_" + date + "_"
						+ Calendar.getInstance().getTimeInMillis();
			}
			uniqueFileName = uniqueFileName + "." + extension;
		} while (new File(uniqueFileName).exists());
		return uniqueFileName;
	}

	public static ExportBean parseExportRequestStream(
			FusionChartsExportData exportData) {
		ExportBean exportBean = new ExportBean();

		String stream = exportData.getStream();

		String parameters = exportData.getParameters();

		ChartMetadata metadata = new ChartMetadata();

		String strWidth = exportData.getMeta_width();

		String strHeight = exportData.getMeta_height();
		try {
			metadata.setWidth(Integer.parseInt(strWidth));
			metadata.setHeight(Integer.parseInt(strHeight));
		} catch (NumberFormatException nfe) {
			metadata.setWidth(-1);
			metadata.setHeight(-1);
		}

		String bgColor = exportData.getMeta_bgColor();

		String DOMId = exportData.getMeta_DOMId();

		metadata.setDOMId(DOMId);

		metadata.setBgColor(bgColor);

		exportBean.setMetadata(metadata);
		exportBean.setStream(stream);

		HashMap exportParamsFromRequest = bang(parameters);

		exportBean.addExportParametersFromMap(exportParamsFromRequest);

		return exportBean;
	}
}