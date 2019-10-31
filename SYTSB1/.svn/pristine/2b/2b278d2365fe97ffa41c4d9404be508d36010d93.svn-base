package com.fusioncharts.exporter;

import java.io.File;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fusioncharts.exporter.beans.ExportConfiguration;
import com.fusioncharts.exporter.beans.LogMessageSetVO;

public class ErrorHandler {
	private static Logger logger = LoggerFactory.getLogger(ErrorHandler.class.getName());

	public static String buildResponse(LogMessageSetVO logMessageSetVO, boolean isHTML) {
		StringBuffer err_buf = new StringBuffer();
		StringBuffer warn_buf = new StringBuffer();
		String errors = "";
		String notices = "";
		Set<LOGMESSAGE> errorSet = logMessageSetVO.getErrorsSet();
		Set<LOGMESSAGE> warningSet = logMessageSetVO.getWarningSet();

		for (Enum error : errorSet) {
			err_buf.append(error.toString());
		}
		for (Enum warning : warningSet) {
			err_buf.append(warning.toString());
		}

		if (err_buf.length() > 0)
			errors = (isHTML ? "<BR>" : "&") + "statusMessage=" + err_buf.substring(0) + (isHTML ? "<BR>" : "&")
					+ "statusCode=" + Status.FAILURE.getCode();
		else
			errors = "statusMessage=" + Status.SUCCESS + "&statusCode=" + Status.SUCCESS.getCode();
		if (warn_buf.length() > 0) {
			notices = (isHTML ? "<BR>" : "&") + "notice=" + warn_buf.substring(0);
		}
		String otherMessages = logMessageSetVO.getOtherMessages();
		otherMessages = otherMessages == null ? "" : otherMessages;

		logger.info("Errors=" + errors);
		logger.info("Notices=" + notices);
		logger.info("Miscellaneous Messages=" + otherMessages);
		return errors + notices + otherMessages;
	}

	public static String buildResponse(String eCodes, boolean isHTML) {
		StringTokenizer tokenizer = new StringTokenizer(eCodes, ",");

		StringBuffer err_buf = new StringBuffer();
		StringBuffer warn_buf = new StringBuffer();
		String errors = "";
		String notices = "";
		String errCode = null;

		while (tokenizer.hasMoreTokens()) {
			errCode = tokenizer.nextToken();
			if (errCode.length() > 0) {
				if (errCode.indexOf("E") != -1) {
					err_buf.append(LOGMESSAGE.valueOf(errCode));
				} else {
					warn_buf.append(LOGMESSAGE.valueOf(errCode));
				}
			}
		}

		if (err_buf.length() > 0)
			errors = (isHTML ? "<BR>" : "&") + "statusMessage=" + err_buf.substring(0) + (isHTML ? "<BR>" : "&")
					+ "statusCode=" + Status.FAILURE.getCode();
		else
			errors = "statusMessage=" + Status.SUCCESS + "&statusCode=" + Status.SUCCESS.getCode();
		if (warn_buf.length() > 0)
			notices = (isHTML ? "<BR>" : "&") + "notice=" + warn_buf.substring(0);
		logger.info("Errors=" + errors);
		logger.info("Notices=" + notices);
		return errors + notices;
	}

	public static LogMessageSetVO checkServerSaveStatus(String fileName) {
		LogMessageSetVO errorSetVO = new LogMessageSetVO();

		String pathToSaveFolder = ExportConfiguration.SAVEABSOLUTEPATH;

		File saveFolder = new File(pathToSaveFolder);
		if (!saveFolder.exists()) {
			errorSetVO.addError(LOGMESSAGE.E508);
		} else if (!saveFolder.canWrite()) {
			errorSetVO.addError(LOGMESSAGE.E403);
		} else {
			String completeFilePath = pathToSaveFolder + File.separator + fileName;
			File saveFile = new File(completeFilePath);

			if (saveFile.exists()) {
				errorSetVO.addWarning(LOGMESSAGE.W509);

				if (ExportConfiguration.OVERWRITEFILE) {
					errorSetVO.addWarning(LOGMESSAGE.W510);

					if (!saveFile.canWrite()) {
						errorSetVO.addError(LOGMESSAGE.E511);
					}

				} else if (!ExportConfiguration.INTELLIGENTFILENAMING) {
					errorSetVO.addError(LOGMESSAGE.E512);
				}

			}

		}

		return errorSetVO;
	}

	public static boolean doesServerSaveFolderExist() {
		boolean saveFolderExists = true;
		String pathToSaveFolder = ExportConfiguration.SAVEABSOLUTEPATH;

		File saveFolder = new File(pathToSaveFolder);
		if (!saveFolder.exists()) {
			saveFolderExists = false;
		}
		return saveFolderExists;
	}

	public static enum ErrorType {
		ERROR, WARNING;

		public String toString() {
			return super.toString();
		}
	}

	public static enum LOGMESSAGE {
		E100(" Insufficient data.", ErrorHandler.ErrorType.ERROR), E101(" Width/height not provided.",
				ErrorHandler.ErrorType.ERROR), W102(" Insufficient export parameters.", ErrorHandler.ErrorType.WARNING),

		E400(" Bad request.", ErrorHandler.ErrorType.ERROR), E401(" Unauthorized access.",
				ErrorHandler.ErrorType.ERROR), E403(" Directory write access forbidden.",
						ErrorHandler.ErrorType.ERROR), E404(" Export Resource not found.",
								ErrorHandler.ErrorType.ERROR), E507(" Insufficient Storage.",
										ErrorHandler.ErrorType.ERROR), E508(" Server Directory does not exist.",
												ErrorHandler.ErrorType.ERROR), W509(" File already exists.",
														ErrorHandler.ErrorType.WARNING), W510(
																" Export handler's Overwrite setting is on. Trying to overwrite.",
																ErrorHandler.ErrorType.WARNING), E511(
																		" Overwrite forbidden. File cannot be overwritten",
																		ErrorHandler.ErrorType.ERROR),

		E512("Intelligent File Naming is Turned off.", ErrorHandler.ErrorType.ERROR), W513(
				"Background Color not specified. Taking White (FFFFFF) as default background color.",
				ErrorHandler.ErrorType.WARNING),

		W514("Using intelligent naming of file by adding unique suffix to the exising name.",
				ErrorHandler.ErrorType.WARNING), W515("The filename has changed - ", ErrorHandler.ErrorType.WARNING),

		E516(" Unable to encode buffered image.", ErrorHandler.ErrorType.ERROR), E600("Internal Server Error",
				ErrorHandler.ErrorType.ERROR), E517(" Invalid Export format.", ErrorHandler.ErrorType.ERROR);

		private String errorMessage = null;
		private String errorType = null;

		private LOGMESSAGE(String message, ErrorHandler.ErrorType type) {
			this.errorMessage = message;
			this.errorType = type.toString();
		}

		public String toString() {
			return this.errorMessage;
		}

		public String type() {
			return this.errorType;
		}
	}

	public static enum Status {
		SUCCESS(1, "Success"), FAILURE(0, "Failure");

		private final int statusCode;
		private final String statusMessage;

		private Status(int statusCode, String statusMessage) {
			this.statusCode = statusCode;
			this.statusMessage = statusMessage;
		}

		public int getCode() {
			return this.statusCode;
		}

		public String toString() {
			return this.statusMessage;
		}
	}
}