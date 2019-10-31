package com.khnt.rtbox.template.parse.tag.impl;

import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.bind.JAXBElement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.P;
import org.docx4j.wml.P.Hyperlink;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;

import com.khnt.rtbox.config.bean.BaseConfig;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.template.components.RtHyperlink;
import com.khnt.rtbox.template.constant.RtPageType;
import com.khnt.rtbox.template.constant.RtSign;
import com.khnt.rtbox.template.model.RtEntity;
import com.khnt.rtbox.template.model.enums.InputType;
import com.khnt.rtbox.template.parse.convert.RtCount;
import com.khnt.rtbox.template.parse.tag.CustomRound;
import com.khnt.rtbox.tools.BaseUtil;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.HtmlUtils;
import com.khnt.rtbox.tools.SizeConverter;
import com.khnt.rtbox.tools.Utils;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 *         <p>
 *         将WORD中含有CHECKBOX的地方进行首次转换
 *         </p>
 */
public class CheckboxCustomRound extends CustomRound {

	public static String CHECKBOX = "□";
	public static int customWidth = 120;

	public CheckboxCustomRound() {
		this.count = new RtCount();
	}

	public CheckboxCustomRound(RtCount count) {
		this.count = count;
	}

	@Override
	public void mark(MainDocumentPart documentPart, RtPage rtPage) throws Exception {
		log.debug("CheckboxCustomRound mark begin...");
		List<Object> tcs = Docx4jUtil.getAllElementFromObject(documentPart, Tc.class);
		for (Object obj : tcs) {
			Tc tc = (Tc) obj;
			// is w:vMerge?
			// if (hasRuleOutTag(tc)) {
			// continue;
			// }
			String content = Docx4jUtil.getElementContent(tc);

			if (StringUtil.isNotEmpty(content)) {
				if (content.contains(CHECKBOX)) {
					// clear , for example 'enter'
					tc.getContent().clear();
					// create p
					P p = Context.getWmlObjectFactory().createP();
					tc.getContent().add(p);

					this.count.add1();
					String id = this.count.id(RtPageType.TABLE);
					String linkText = "${id:" + id + "}";

					String tag = "$" + autoConfig(id, id, content, tc);
					// create hyperlink
					Hyperlink link = RtHyperlink.create(documentPart, tag, linkText);
					p.getContent().add(link);
				}
			}
		}

		log.debug("CheckboxCustomRound mark successed...");
	}

	public boolean hasRuleOutTag(Tc tc) {
		if (tc.getTcPr() != null && tc.getTcPr().getVMerge() != null) {
			return true;
		}
		return false;
	}

	public String autoConfig(String code, String name, String content, Tc tc) throws Exception {
		RtEntity entity = new RtEntity();
		entity.setId(code);
		if (name != null) {
			entity.setName(name);
		} else {
			entity.setName(code);
		}

		entity.setType(InputType.checkbox.toString());//
		LinkedHashMap<String, String> ligerui = new LinkedHashMap<String, String>();

		if (StringUtil.isEmpty(content)) {
			throw new Exception("content 口  td has something wrong...");
		}

		String[] datas = content.split(CHECKBOX);
		JSONArray array = new JSONArray();
		for (String data : datas) {
			if (StringUtil.isNotEmpty(data)) {
				JSONObject object = new JSONObject();
				data = data.trim();
				object.put("id", data);
				object.put("text", data);
				array.put(object);
			}
		}
		ligerui.put("data", array.toString());
		entity.setLigerui(ligerui);
		String json = Utils.toJsonString(entity);
		return URLEncoder.encode(json, "UTF-8");
	}

}
