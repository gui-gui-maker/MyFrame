package com.khnt.rtbox.template.components;

import javax.xml.bind.JAXBException;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.R.Sym;

/**
 * @author ZQ
 * @version 2017年8月27日 下午9:52:55 特殊字符
 */
public class RtSym {
	public static Sym createSym(String _char) throws JAXBException {
		Sym sym = (Sym) Context.getWmlObjectFactory().createRSym();
		sym.setFont("Wingdings 2");
		sym.setChar(_char);
		return sym;
	}

	// 带√的框
	public static Sym createF052Sym() throws JAXBException {
		return createSym("F052");
	}
	// 空白框
	public static Sym createF0A3Sym() throws JAXBException {
		return createSym("F0A3");
	}
}
