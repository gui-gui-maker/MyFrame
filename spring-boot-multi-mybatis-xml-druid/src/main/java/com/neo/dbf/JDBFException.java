package com.neo.dbf;


import java.io.PrintStream;
import java.io.PrintWriter;

@SuppressWarnings("serial")
public class JDBFException extends Exception {
	
	private Throwable detail;

	public JDBFException(String s) {
		this(s, null);
	}

	public JDBFException(Throwable throwable) {
		this(throwable.getMessage(), throwable);
	}

	public JDBFException(String s, Throwable throwable) {
		super(s);
		this.detail = throwable;
	}

	public String getMessage() {
		if (this.detail == null) {
			return super.getMessage();
		}

		return super.getMessage();
	}

	public void printStackTrace(PrintStream printstream) {
		if (this.detail == null) {
			super.printStackTrace(printstream);
			return;
		}
		PrintStream printstream1 = printstream;
		printstream1.println(this);
		this.detail.printStackTrace(printstream);
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintWriter printwriter) {
		if (this.detail == null) {
			super.printStackTrace(printwriter);
			return;
		}
		PrintWriter printwriter1 = printwriter;

		printwriter1.println(this);
		this.detail.printStackTrace(printwriter);
	}
}