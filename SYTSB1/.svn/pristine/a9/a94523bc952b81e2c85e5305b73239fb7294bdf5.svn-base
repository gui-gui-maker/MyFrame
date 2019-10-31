package com.fusioncharts.exporter.beans;

import java.util.HashSet;
import java.util.Set;

import com.fusioncharts.exporter.ErrorHandler.LOGMESSAGE;

public class LogMessageSetVO {
	Set<LOGMESSAGE> errorsSet;
	Set<LOGMESSAGE> warningSet;
	String otherMessages = null;

	public LogMessageSetVO() {
		this.errorsSet = new HashSet<LOGMESSAGE>();
		this.warningSet = new HashSet<LOGMESSAGE>();
	}

	public void addError(LOGMESSAGE error) {
		if (this.errorsSet == null) {
			this.errorsSet = new HashSet<LOGMESSAGE>();
		}
		this.errorsSet.add(error);
	}

	public void addWarning(LOGMESSAGE warning) {
		if (this.warningSet == null) {
			this.warningSet = new HashSet<LOGMESSAGE>();
		}
		this.warningSet.add(warning);
	}

	public Set<LOGMESSAGE> getErrorsSet() {
		return this.errorsSet;
	}

	public String getOtherMessages() {
		return this.otherMessages;
	}

	public Set<LOGMESSAGE> getWarningSet() {
		return this.warningSet;
	}

	public void setErrorsSet(Set<LOGMESSAGE> errorsSet) {
		this.errorsSet = errorsSet;
	}

	public void setOtherMessages(String otherMessages) {
		this.otherMessages = otherMessages;
	}

	public void setWarningSet(Set<LOGMESSAGE> warningSet) {
		this.warningSet = warningSet;
	}
}