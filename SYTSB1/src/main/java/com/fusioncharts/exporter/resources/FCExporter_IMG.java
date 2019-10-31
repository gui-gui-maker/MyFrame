package com.fusioncharts.exporter.resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.stream.FileImageOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.batik.transcoder.TranscoderException;

import com.fusioncharts.exporter.ErrorHandler;
import com.fusioncharts.exporter.FusionChartsExportHelper;
import com.fusioncharts.exporter.SvgUtil;
import com.fusioncharts.exporter.beans.ChartMetadata;
import com.fusioncharts.exporter.beans.ExportBean;
import com.fusioncharts.exporter.beans.ExportConfiguration;
import com.fusioncharts.exporter.beans.ExportParameterNames;
import com.fusioncharts.exporter.beans.LogMessageSetVO;
import com.fusioncharts.exporter.encoders.BasicEncoder;
import com.fusioncharts.exporter.encoders.JPEGEncoder;
import com.fusioncharts.exporter.generators.ImageGenerator;
import com.khnt.utils.FileUtil;

public class FCExporter_IMG extends FCExporter_Format {
	private ExportBean exportBean = null;

	public String exportOutput(Object exportObj, HttpServletResponse response) {
		String action = (String) this.exportBean
				.getExportParameterValue("exportaction");
		String exportFormat = (String) this.exportBean
				.getExportParameterValue("exportformat");
		String exportTargetWindow = (String) this.exportBean
				.getExportParameterValue("exporttargetwindow");

		String fileNameWithoutExt = (String) this.exportBean
				.getExportParameterValue("exportfilename");
		String extension = FusionChartsExportHelper
				.getExtensionFor(exportFormat.toLowerCase());

		String fileName = fileNameWithoutExt + "." + extension;

		LogMessageSetVO logMessageSetVO = new LogMessageSetVO();

		BufferedImage chartImage = (BufferedImage) exportObj;
		boolean isHTML = false;
		if (action.equals("download")) {
			isHTML = true;
		}
		String noticeMessage = "";
		String meta_values = this.exportBean.getMetadataAsQueryString(null,
				false, isHTML);

		if (!action.equals("download")) {
			noticeMessage = "&notice=";

			String pathToSaveFolder = ExportConfiguration.SAVEABSOLUTEPATH;
			String completeFilePath = pathToSaveFolder + File.separator
					+ fileName;
			String completeFilePathWithoutExt = pathToSaveFolder
					+ File.separator + fileNameWithoutExt;
			File saveFile = new File(completeFilePath);

			if (saveFile.exists()) {
				noticeMessage = noticeMessage + ErrorHandler.LOGMESSAGE.W509;
				if ((!ExportConfiguration.OVERWRITEFILE)
						&& (ExportConfiguration.INTELLIGENTFILENAMING)) {
					noticeMessage = noticeMessage
							+ ErrorHandler.LOGMESSAGE.W514;
					completeFilePath = FusionChartsExportHelper
							.getUniqueFileName(completeFilePathWithoutExt,
									extension);
					File tempFile = new File(completeFilePath);
					fileName = tempFile.getName();
					noticeMessage = noticeMessage
							+ ErrorHandler.LOGMESSAGE.W515 + fileName;
					logMessageSetVO.addWarning(ErrorHandler.LOGMESSAGE.W515);
				}

			}

			String pathToDisplay = ExportConfiguration.HTTP_URI + "/"
					+ fileName;
			if (ExportConfiguration.HTTP_URI.endsWith("/")) {
				pathToDisplay = ExportConfiguration.HTTP_URI + fileName;
			}

			meta_values = this.exportBean.getMetadataAsQueryString(
					pathToDisplay, false, isHTML);
			try {
				FileImageOutputStream fios = new FileImageOutputStream(
						new File(completeFilePath));
				if ((exportFormat.toLowerCase().equalsIgnoreCase("jpg"))
						|| (exportFormat.toLowerCase().equalsIgnoreCase("jpeg"))) {
					JPEGEncoder jpegEncoder = new JPEGEncoder();
					try {
						jpegEncoder.encode(chartImage, fios);
					} catch (Throwable e) {
						logMessageSetVO.addError(ErrorHandler.LOGMESSAGE.E516);
					}
					chartImage = null;
				} else {
					BasicEncoder basicEncoder = new BasicEncoder();
					try {
						basicEncoder.encode(chartImage, fios, 1.0F,
								exportFormat.toLowerCase());
					} catch (Throwable e) {
						System.out
								.println(" Unable to encode the buffered image");
						logMessageSetVO.addError(ErrorHandler.LOGMESSAGE.E516);
					}

					chartImage = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if ((logMessageSetVO.getErrorsSet() == null)
					|| (logMessageSetVO.getErrorsSet().isEmpty())) {
				try {
					PrintWriter out = response.getWriter();
					out.print(meta_values + noticeMessage + "&statusCode="
							+ ErrorHandler.Status.SUCCESS.getCode()
							+ "&statusMessage=" + ErrorHandler.Status.SUCCESS);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else {
			try {
				response.setContentType(FusionChartsExportHelper
						.getMimeTypeFor(exportFormat.toLowerCase()));

				OutputStream os = response.getOutputStream();

				if (exportTargetWindow.equalsIgnoreCase("_self")) {
					response.addHeader("Content-Disposition",
							"attachment; filename=\"" + fileName + "\"");
				} else {
					response.addHeader("Content-Disposition",
							"inline; filename=\"" + fileName + "\"");
				}
				if ((exportFormat.toLowerCase().equalsIgnoreCase("jpg"))
						|| (exportFormat.toLowerCase().equalsIgnoreCase("jpeg"))) {
					JPEGEncoder jpegEncoder = new JPEGEncoder();
					try {
						jpegEncoder.encode(chartImage, os);
						os.flush();
					} catch (Throwable e) {
						System.out
								.println("Unable to (JPEG) encode the buffered image");
						logMessageSetVO.addError(ErrorHandler.LOGMESSAGE.E516);

						meta_values = this.exportBean.getMetadataAsQueryString(
								null, true, isHTML);

						response.reset();
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print(meta_values
								+ noticeMessage
								+ ErrorHandler.buildResponse(logMessageSetVO,
										isHTML));
						return null;
					}
					chartImage = null;
				} else {
					BasicEncoder basicEncoder = new BasicEncoder();
					try {
						basicEncoder.encode(chartImage, os, 1.0F,
								exportFormat.toLowerCase());
						os.flush();
					} catch (Throwable e) {
						System.out
								.println("Unable to encode the buffered image");
						logMessageSetVO.addError(ErrorHandler.LOGMESSAGE.E516);

						meta_values = this.exportBean.getMetadataAsQueryString(
								null, true, isHTML);

						response.reset();
						response.setContentType("text/html");
						PrintWriter out = response.getWriter();
						out.print(meta_values
								+ noticeMessage
								+ ErrorHandler.buildResponse(logMessageSetVO,
										isHTML));
						return null;
					}
					chartImage = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if ((logMessageSetVO.getErrorsSet() == null)
				|| (logMessageSetVO.getErrorsSet().isEmpty())) {
			meta_values = this.exportBean.getMetadataAsQueryString(null, true,
					isHTML);
			try {
				OutputStream os = response.getOutputStream();
				os.write((meta_values + noticeMessage + "&statusCode="
						+ ErrorHandler.Status.SUCCESS.getCode()
						+ "&statusMessage=" + ErrorHandler.Status.SUCCESS)
						.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return "";
	}

	public Object exportProcessor(ExportBean pExportBean) {
		this.exportBean = pExportBean;
		String stream = this.exportBean.getStream();
		ChartMetadata metadata = this.exportBean.getMetadata();
		BufferedImage chartImage = null;
		if(stream.indexOf("svg")!=-1){
			try {
				//检查stream是否具有url 为lchart_detail.jsp的页面
				String reg = "url\\([^\\)]+lChart_detail[^\\)]+\\)\"";
				Pattern pattern = Pattern.compile(reg);
				Matcher matcher = pattern.matcher(stream);
				StringBuffer sb = new StringBuffer();
				while(matcher.find()){
					String temp = matcher.group();
					String newValue = temp.replace("\"","");
					matcher.appendReplacement(sb, newValue);
				}
				matcher.appendTail(sb);
				stream = sb.toString();
				String fileNameWithoutExt = (String) this.exportBean
						.getExportParameterValue("exportfilename");
				
				String exportFormat = (String) this.exportBean.getExportParameterValue(ExportParameterNames.EXPORTFORMAT
						.toString());
				String fileName = fileNameWithoutExt + "."+exportFormat.toLowerCase();
				String pathToSaveFolder = ExportConfiguration.SAVEABSOLUTEPATH;
				String completeFilePath = pathToSaveFolder + File.separator+ fileName;
				if("png".equals(exportFormat.toLowerCase())){
					chartImage = SvgUtil.convertSvgCode2Png(stream, new File(completeFilePath));
				}else if("jpeg".equals(exportFormat.toLowerCase())||"jpg".equals(exportFormat.toLowerCase())){
					chartImage = SvgUtil.convertSvgCode2Jpeg(stream, new File(completeFilePath));
				}
				FileUtil.delAllFile(completeFilePath);
				return chartImage;
			} catch (IOException e) {
				return null;
			} catch (TranscoderException e) {
				return null;
			}
		}else{
			chartImage = ImageGenerator.getChartImage(stream,
					metadata);
			return chartImage;
		}
	}
}