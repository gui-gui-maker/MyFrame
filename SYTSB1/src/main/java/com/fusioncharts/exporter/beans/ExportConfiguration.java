package com.fusioncharts.exporter.beans;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fusioncharts.exporter.FusionChartsExportHelper;

public class ExportConfiguration {
	private static Logger logger = LoggerFactory.getLogger(ExportConfiguration.class
			.getName());

	public static String EXPORTHANDLER = "FCExporter_";

	public static String RESOURCEPACKAGE = "com.fusioncharts.exporter.resources";

	public static String SAVEPATH = "./";

	public static String SAVEABSOLUTEPATH = "./";

	public static String HTTP_URI = "http://yourdomain.com/";

	public static String TMPSAVEPATH = "";

	public static boolean OVERWRITEFILE = false;

	public static boolean INTELLIGENTFILENAMING = true;

	public static String FILESUFFIXFORMAT = "TIMESTAMP";

	public static void loadProperties() {
		Properties props = new Properties();
		try {
			props.load(FusionChartsExportHelper.class
					.getResourceAsStream("/fusioncharts_export.properties"));

			EXPORTHANDLER = props.getProperty("EXPORTHANDLER", EXPORTHANDLER);
			RESOURCEPACKAGE = props.getProperty("RESOURCEPACKAGE",
					RESOURCEPACKAGE);
			SAVEPATH = props.getProperty("SAVEPATH", SAVEPATH);

			HTTP_URI = props.getProperty("HTTP_URI", HTTP_URI);
			TMPSAVEPATH = props.getProperty("TMPSAVEPATH", TMPSAVEPATH);

			String OVERWRITEFILESTR = props.getProperty("OVERWRITEFILE",
					"false");
			OVERWRITEFILE = new Boolean(OVERWRITEFILESTR).booleanValue();

			String INTELLIGENTFILENAMINGSTR = props.getProperty(
					"INTELLIGENTFILENAMING", "true");
			INTELLIGENTFILENAMING = new Boolean(INTELLIGENTFILENAMINGSTR)
					.booleanValue();

			FILESUFFIXFORMAT = props.getProperty("FILESUFFIXFORMAT",
					FILESUFFIXFORMAT);
		} catch (NullPointerException e) {
			logger.info("NullPointer: Properties file not FOUND");
		} catch (FileNotFoundException e) {
			logger.info("Properties file not FOUND");
		} catch (IOException e) {
			logger.info("IOException: Properties file not FOUND");
		}
	}
}