package com.khnt.rtbox.template.components;

import java.math.BigInteger;

import javax.xml.bind.JAXBElement;

import org.docx4j.jaxb.Context;
import org.docx4j.wml.CTBookmark;
import org.docx4j.wml.CTMarkupRange;
import org.docx4j.wml.ObjectFactory;
import org.docx4j.wml.P;
import org.docx4j.wml.R;

/**
 * @author ZQ
 * @version 2017年3月9日 下午9:34:01 类说明
 */
public class RtBookmark {
	public static JAXBElement<CTBookmark> create(P p, R r, String name, int id) throws Exception {

		// Find the index
		int index = p.getContent().indexOf(r);

		if (index < 0) {
			throw new Exception("P does not contain R!");
		}

		ObjectFactory factory = Context.getWmlObjectFactory();
		BigInteger ID = BigInteger.valueOf(id);

		// Add bookmark end first
		CTMarkupRange mr = factory.createCTMarkupRange();
		mr.setId(ID);
		JAXBElement<CTMarkupRange> bmEnd = factory.createBodyBookmarkEnd(mr);
		p.getContent().add(index + 1, bmEnd);

		// Next, bookmark start
		CTBookmark bm = factory.createCTBookmark();
		bm.setId(ID);
		bm.setName(name);
		JAXBElement<CTBookmark> bmStart = factory.createBodyBookmarkStart(bm);
		p.getContent().add(index, bmStart);
		return bmStart;
	}
}
