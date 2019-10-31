package com.fusioncharts.exporter.beans;

public class FusionChartsExportData {
	protected String stream = null;
	protected String parameters = null;
	protected String meta_width = null;
	protected String meta_height = null;
	protected String meta_DOMId = null;
	protected String meta_bgColor = null;

	public FusionChartsExportData(String stream, String parameters,
			String meta_width, String meta_height, String meta_DOMId,
			String meta_bgColor) {
		this.stream = stream;
		this.parameters = parameters;
		this.meta_width = meta_width;
		this.meta_height = meta_height;
		this.meta_DOMId = meta_DOMId;
		this.meta_bgColor = meta_bgColor;
	}

	public String getMeta_bgColor() {
		return this.meta_bgColor;
	}

	public String getMeta_DOMId() {
		return this.meta_DOMId;
	}

	public String getMeta_height() {
		return this.meta_height;
	}

	public String getMeta_width() {
		return this.meta_width;
	}

	public String getParameters() {
		return this.parameters;
	}

	public String getStream() {
		return this.stream;
	}

	public void setMeta_bgColor(String meta_bgColor) {
		this.meta_bgColor = meta_bgColor;
	}

	public void setMeta_DOMId(String meta_DOMId) {
		this.meta_DOMId = meta_DOMId;
	}

	public void setMeta_height(String meta_height) {
		this.meta_height = meta_height;
	}

	public void setMeta_width(String meta_width) {
		this.meta_width = meta_width;
	}

	public void setParameters(String parameters) {
		this.parameters = parameters;
	}

	public void setStream(String stream) {
		this.stream = stream;
	}
}