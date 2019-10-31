package com.fusioncharts.exporter.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Set;
import java.util.logging.Level;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fusioncharts.exporter.ErrorHandler;
import com.fusioncharts.exporter.FusionChartsExportHelper;
import com.fusioncharts.exporter.beans.ExportBean;
import com.fusioncharts.exporter.beans.ExportConfiguration;
import com.fusioncharts.exporter.beans.ExportParameterNames;
import com.fusioncharts.exporter.beans.FusionChartsExportData;
import com.fusioncharts.exporter.beans.LogMessageSetVO;
import com.fusioncharts.exporter.resources.FCExporter_Format;

public class FCExporter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private boolean SAVEFOLDEREXISTS = true;

	private static Logger logger = LoggerFactory.getLogger(FCExporter.class.getName());

	private boolean checkExportResources() {
		boolean allExportResourcesFound = true;

		Iterator localIterator = FusionChartsExportHelper.getHandlerAssociationsMap().values().iterator();

		while (localIterator.hasNext()) {
			String exportFormat = (String) localIterator.next();
			String exporterClassName = FusionChartsExportHelper.getExportHandlerClassName(exportFormat);
			try {
				Class exporterClass = Class.forName(exporterClassName);
				FCExporter_Format fcExporter = (FCExporter_Format) exporterClass.newInstance();
			} catch (ClassNotFoundException e) {
				logger.debug(ErrorHandler.LOGMESSAGE.E404.toString() + ":" + exporterClassName);
				allExportResourcesFound = false;
			} catch (InstantiationException e) {
				logger.debug(ErrorHandler.LOGMESSAGE.E404.toString() + ":" + exporterClassName);
				allExportResourcesFound = false;
			} catch (IllegalAccessException e) {
				logger.debug(ErrorHandler.LOGMESSAGE.E404.toString() + ":" + exporterClassName);
				allExportResourcesFound = false;
			}
		}

		return allExportResourcesFound;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		FusionChartsExportData exportData = new FusionChartsExportData(request.getParameter("stream"),
				request.getParameter("parameters"), request.getParameter("meta_width"),
				request.getParameter("meta_height"), request.getParameter("meta_DOMId"),
				request.getParameter("meta_bgColor"));
		ExportBean exportBean = FusionChartsExportHelper.parseExportRequestStream(exportData);

		String exportTargetWindow = (String) exportBean
				.getExportParameterValue(ExportParameterNames.EXPORTTARGETWINDOW.toString());
		String exportAction = (String) exportBean.getExportParameterValue(ExportParameterNames.EXPORTACTION.toString());
		String exportFormat = (String) exportBean.getExportParameterValue(ExportParameterNames.EXPORTFORMAT.toString());

		LogMessageSetVO logMessageSetVO = exportBean.validate();

		Set errorsSet = logMessageSetVO.getErrorsSet();
		boolean isHTML = exportBean.isHTMLResponse();

		if ((errorsSet != null) && (!errorsSet.isEmpty())) {
			String meta_values = exportBean.getMetadataAsQueryString(null, true, isHTML);
			logMessageSetVO.setOtherMessages(meta_values);
			writeError(response, isHTML, logMessageSetVO, exportTargetWindow);
			return;
		}

		if (!exportAction.equals("download")) {
			if (!this.SAVEFOLDEREXISTS) {
				logMessageSetVO.addError(ErrorHandler.LOGMESSAGE.E508);

				String meta_values = exportBean.getMetadataAsQueryString(null, true, isHTML);
				logMessageSetVO.setOtherMessages(meta_values);
				writeError(response, isHTML, logMessageSetVO, exportTargetWindow);
				return;
			}

			String fileNameWithoutExt = (String) exportBean
					.getExportParameterValue(ExportParameterNames.EXPORTFILENAME.toString());
			String extension = FusionChartsExportHelper.getExtensionFor(exportFormat.toLowerCase());

			String fileName = fileNameWithoutExt + "." + extension;
			logMessageSetVO = ErrorHandler.checkServerSaveStatus(fileName);
			errorsSet = logMessageSetVO.getErrorsSet();
		}
		if ((errorsSet != null) && (!errorsSet.isEmpty())) {
			String meta_values = exportBean.getMetadataAsQueryString(null, true, isHTML);
			logMessageSetVO.setOtherMessages(meta_values);
			writeError(response, isHTML, logMessageSetVO, exportTargetWindow);
			return;
		}

		String exporterClassName = FusionChartsExportHelper.getExportHandlerClassName(exportFormat);
		try {
			Class exporterClass = Class.forName(exporterClassName);

			FCExporter_Format fcExporter = (FCExporter_Format) exporterClass.newInstance();
			Object exportObject = fcExporter.exportProcessor(exportBean);
			String status = fcExporter.exportOutput(exportObject, response);
		} catch (ClassNotFoundException e) {
			logMessageSetVO.addError(ErrorHandler.LOGMESSAGE.E404);

			String meta_values = exportBean.getMetadataAsQueryString(null, true, isHTML);
			logMessageSetVO.setOtherMessages(meta_values);
			writeError(response, isHTML, logMessageSetVO, exportTargetWindow);
		} catch (InstantiationException e) {
			logMessageSetVO.addError(ErrorHandler.LOGMESSAGE.E404);

			String meta_values = exportBean.getMetadataAsQueryString(null, true, isHTML);
			logMessageSetVO.setOtherMessages(meta_values);
			writeError(response, isHTML, logMessageSetVO, exportTargetWindow);
		} catch (IllegalAccessException e) {
			logMessageSetVO.addError(ErrorHandler.LOGMESSAGE.E404);

			String meta_values = exportBean.getMetadataAsQueryString(null, true, isHTML);
			logMessageSetVO.setOtherMessages(meta_values);
			writeError(response, isHTML, logMessageSetVO, exportTargetWindow);
		}
	}

	public void init(ServletConfig config) throws ServletException {
		logger.info("FCExporter Servlet Init called");

		ExportConfiguration.loadProperties();
		File f = new File(ExportConfiguration.SAVEPATH);
		boolean savePathAbsolute = f.isAbsolute();
		logger.info("Is SAVEPATH on server absolute?" + savePathAbsolute);
		ExportConfiguration.SAVEABSOLUTEPATH = savePathAbsolute ? ExportConfiguration.SAVEPATH
				: config.getServletContext().getRealPath(ExportConfiguration.SAVEPATH);

		this.SAVEFOLDEREXISTS = ErrorHandler.doesServerSaveFolderExist();
		if (!this.SAVEFOLDEREXISTS) {
			logger.warn(ErrorHandler.LOGMESSAGE.E508.toString() + "Path used: " + ExportConfiguration.SAVEABSOLUTEPATH);
		}
		checkExportResources();
	}

	private void writeError(HttpServletResponse response, boolean isHTML, LogMessageSetVO logMessageSetVO,
			String exportTargetWindow) {
		response.setContentType("text/html");
		if ((exportTargetWindow != null) && (exportTargetWindow.equalsIgnoreCase("_self")))
			response.addHeader("Content-Disposition", "attachment;");
		else {
			response.addHeader("Content-Disposition", "inline;");
		}
		try {
			PrintWriter out = response.getWriter();
			out.print(ErrorHandler.buildResponse(logMessageSetVO, isHTML));
		} catch (IOException localIOException) {
		}
	}
}