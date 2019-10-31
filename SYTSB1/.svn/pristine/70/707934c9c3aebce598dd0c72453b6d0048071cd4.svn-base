package com.fusioncharts.exporter.beans;

import java.util.HashMap;
import java.util.Iterator;

import com.fusioncharts.exporter.ErrorHandler;
import com.fusioncharts.exporter.FusionChartsExportHelper;

public class ExportBean {
	private ChartMetadata metadata;
	private String stream;
	private HashMap<String, Object> exportParameters = null;

	public ExportBean() {
		this.exportParameters = new HashMap<String, Object>();

		this.exportParameters.put(
				ExportParameterNames.EXPORTFILENAME.toString(), "FusionCharts");
		this.exportParameters.put(ExportParameterNames.EXPORTACTION.toString(),
				"download");
		this.exportParameters.put(
				ExportParameterNames.EXPORTTARGETWINDOW.toString(), "_self");
		this.exportParameters.put(ExportParameterNames.EXPORTFORMAT.toString(),
				"PDF");
	}

	public void addExportParameter(String parameterName, Object value) {
		this.exportParameters.put(parameterName.toLowerCase(), value);
	}

	public void addExportParametersFromMap(
			HashMap<String, String> moreParameters) {
		this.exportParameters.putAll(moreParameters);
	}

	public HashMap<String, Object> getExportParameters() {
		return this.exportParameters;
	}

	public Object getExportParameterValue(String key) {
		return this.exportParameters.get(key);
	}

	public ChartMetadata getMetadata() {
		return this.metadata;
	}

	public String getMetadataAsQueryString(String filePath, boolean isError,
			boolean isHTML) {
		String queryParams = "";
		if (isError) {
			queryParams = queryParams + (isHTML ? "<BR>" : "&") + "width=0";
			queryParams = queryParams + (isHTML ? "<BR>" : "&") + "height=0";
		} else {
			queryParams = queryParams + (isHTML ? "<BR>" : "&") + "width="
					+ this.metadata.getWidth();
			queryParams = queryParams + (isHTML ? "<BR>" : "&") + "height="
					+ this.metadata.getHeight();
		}

		queryParams = queryParams + (isHTML ? "<BR>" : "&") + "DOMId="
				+ this.metadata.getDOMId();
		if (filePath != null) {
			queryParams = queryParams + (isHTML ? "<BR>" : "&") + "fileName="
					+ filePath;
		}

		return queryParams;
	}

	public String getParametersAndMetadataAsQueryString() {
		String queryParams = "";
		queryParams = queryParams + "?width=" + this.metadata.getWidth();
		queryParams = queryParams + "&height=" + this.metadata.getHeight();
		queryParams = queryParams + "&bgcolor=" + this.metadata.getBgColor();

		Iterator<String> iter = this.exportParameters.keySet().iterator();

		while (iter.hasNext()) {
			String key = (String) iter.next();
			String value = (String) this.exportParameters.get(key);
			queryParams = queryParams + "&" + key + "=" + value;
		}

		return queryParams;
	}

	public String getStream() {
		return this.stream;
	}

	public boolean isHTMLResponse() {
		boolean isHTML = false;
		String exportAction = (String) getExportParameterValue(ExportParameterNames.EXPORTACTION
				.toString());
		if (exportAction.equals("download"))
			isHTML = true;
		return isHTML;
	}

	public void setExportParameters(HashMap<String, Object> exportParameters) {
		this.exportParameters = exportParameters;
	}

	public void setMetadata(ChartMetadata metadata) {
		this.metadata = metadata;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}

	public LogMessageSetVO validate() {
		LogMessageSetVO errorSetVO = new LogMessageSetVO();
		if ((getMetadata().getWidth() == -1)
				|| (getMetadata().getHeight() == -1)
				|| (getMetadata().getWidth() == 0)
				|| (getMetadata().getHeight() == 0)) {
			errorSetVO.addError(ErrorHandler.LOGMESSAGE.E101);
		}

		if (getMetadata().getBgColor() == null) {
			errorSetVO.addWarning(ErrorHandler.LOGMESSAGE.W513);
		}

		if (getStream() == null) {
			errorSetVO.addError(ErrorHandler.LOGMESSAGE.E100);
		}

		if ((this.exportParameters == null)
				|| (this.exportParameters.isEmpty())) {
			errorSetVO.addWarning(ErrorHandler.LOGMESSAGE.W102);
		} else {
			String exportFormat = (String) getExportParameterValue(ExportParameterNames.EXPORTFORMAT
					.toString());
			boolean exportFormatSupported = FusionChartsExportHelper
					.getHandlerAssociationsMap().containsKey(exportFormat.toUpperCase());
			if (!exportFormatSupported) {
				errorSetVO.addError(ErrorHandler.LOGMESSAGE.E517);
			}
		}

		return errorSetVO;
	}
}