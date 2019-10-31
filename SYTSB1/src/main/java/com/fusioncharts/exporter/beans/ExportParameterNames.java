package com.fusioncharts.exporter.beans;

public enum ExportParameterNames {
	EXPORTACTION("exportaction"), EXPORTFORMAT("exportformat"), EXPORTFILENAME(
			"exportfilename"), EXPORTTARGETWINDOW("exporttargetwindow");

	private String paramName;

	private ExportParameterNames(String paramName) {
		this.paramName = paramName;
	}

	public String toString() {
		return this.paramName;
	}
}