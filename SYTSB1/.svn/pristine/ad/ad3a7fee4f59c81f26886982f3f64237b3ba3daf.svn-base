package com.khnt.rtbox.print.service;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.khnt.core.crud.manager.impl.EntityManageImpl;
import com.khnt.rtbox.print.bean.PrintPdfLog;
import com.khnt.rtbox.print.dao.PrintPdfLogDao;

/*******************************************************************************
 * <p>
 * 
 * </p>
 * 
 * @ClassName PrintPdfLogManager
 * @JDK 1.5
 * @author CODER_V3.0
 * @date 2018-07-27 14:02:49
 */
@Service("printPdfLogManager")
public class PrintPdfLogManager extends EntityManageImpl<PrintPdfLog, PrintPdfLogDao> {
	@Autowired
	PrintPdfLogDao printPdfLogDao;
	public static void main(String[] args) {
		String cmd = "cmd /c phantomjs D:/workspace2/.metadata/.plugins/org.eclipse.wst.server.core/tmp7/wtpwebapps/TZSB/rtbox/phantomjs/printPdf.js http://192.168.0.110:8081/rtbox/app/templates/XCTS_YLRQDQ2017/index1.jsp?fk_report_id=402880485eb6aa8d015eb79f40bb0002^&fk_inspection_info_id=402880fe6462d3a2016463cb33100093^&pageStatus=detail D:/rtbox//tmp/2018-07-06/null/parts/1.pdf" ;
		Process process;
		try {
			process = Runtime.getRuntime().exec(cmd);
			printMessage(process.getInputStream(), "input");
			printMessage(process.getErrorStream(), "error");
			int value = process.waitFor();
			System.out.println(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}
	
	private static void printMessage(final InputStream input, final String preFix) {
		new Thread(new Runnable() {
			public void run() {
				Reader reader = null;
				try {
					reader = new InputStreamReader(input, "GBK");
					BufferedReader bf = new BufferedReader(reader);
					String line = null;
					try {
						while ((line = bf.readLine()) != null) {
							System.out.println(preFix + ":" + line);
						
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				}

			}
		}).start();
	}

}
