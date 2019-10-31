package com.khnt.rtbox.template.revert;

import java.io.IOException;
import java.io.OptionalDataException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBElement;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.docx4j.jaxb.Context;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.wml.CTBookmark;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.P.Hyperlink;
import org.docx4j.wml.R.Sym;

import com.khnt.rtbox.config.bean.RtExportData;
import com.khnt.rtbox.config.service.RtPageManager;
import com.khnt.rtbox.template.components.RtSym;
import com.khnt.rtbox.template.constant.RtExportDataType;
import com.khnt.rtbox.template.constant.RtField;
import com.khnt.rtbox.template.constant.RtPath;
import com.khnt.rtbox.template.handle.export.RtExportAsstFactory;
import com.khnt.rtbox.template.model.RtExportAssts;
import com.khnt.rtbox.tools.Docx4jUtil;
import com.khnt.rtbox.tools.HtmlUtils;
import com.khnt.rtbox.tools.Utils;
import com.khnt.utils.StringUtil;

/**
 * @author ZQ
 * @version 2017年10月11日 下午5:29:58 类说明
 */
class RtFilterTask implements Callable<RtExportAssts> {
	Map<String, RtExportData> transMap;
	String title;
	RtRevert rtRevert;
	Hyperlink hyperlink;
	WordprocessingMLPackage wordMLPackage;
	HashMap<String, Object> infoMap;
	static Log log = LogFactory.getLog(RtFilterTask.class);

	public RtFilterTask(Map<String, RtExportData> transMap, String title, RtRevert rtRevert, Hyperlink hyperlink, WordprocessingMLPackage wordMLPackage,
			HashMap<String, Object> infoMap) {
		this.transMap = transMap;
		this.title = title;
		this.rtRevert = rtRevert;
		this.hyperlink = hyperlink;
		this.wordMLPackage = wordMLPackage;
		this.infoMap = infoMap;

	}

	@Override
	public RtExportAssts call() throws Exception {
		return filter(transMap, title, rtRevert, hyperlink, wordMLPackage, infoMap);
	}

	private RtExportAssts filter(Map<String, RtExportData> transMap, String wtValue, RtRevert rtRevert, Hyperlink hyperlink, WordprocessingMLPackage wordMLPackage,
			HashMap<String, Object> infoMap) throws Exception {

		boolean findInDB = false;
		boolean signFlag = false;

String id = "";
		
		
		
		if(wtValue.indexOf("<u:dict")!=-1) {
			System.out.println("----------------"+wtValue);
			int start = wtValue.indexOf("<u:dict");
			
			int end = wtValue.indexOf("%>'/>");
			if(end==-1) {
				
				 end = wtValue.indexOf("%>'\\>");
				
			}
			if(end!=1&&end!=-1) {
				
				wtValue = wtValue.substring(0,start)+wtValue.substring(end+"%>'/>".length(),wtValue.length());
				
			}
			
		}
		String wtValuep = wtValue;
		String [] dataws1 = wtValue.split("\\$");
		
		if(wtValue.indexOf("[{")!=-1) {
			System.out.println("----------------"+wtValue);
			int start = wtValue.indexOf("[{");
			
			int end = wtValue.indexOf("}]");
		
			if(end!=1&&end!=-1) {
				
				wtValue = wtValue.substring(0,start)+wtValue.substring(end+"}]".length(),wtValue.length());
				
			}
			
		}
		String dataw = wtValue;
		String [] dataws = dataw.split("\\$");
		com.alibaba.fastjson.JSONObject json = new com.alibaba.fastjson.JSONObject();
		
		String link = "";
		
		if(dataws.length>1) {
			link = dataws[1];
				//json = com.alibaba.fastjson.JSONObject.parseObject(dataws[1]);
			wtValue = "\\$" + URLDecoder.decode(dataws1[1], "utf-8");
			
		}else {
			link = dataw;
				//json = com.alibaba.fastjson.JSONObject.parseObject(dataw);
			wtValue =  URLDecoder.decode(wtValuep, "utf-8");
			
			
		}
		//直接转json容易出错，所以手动转了
		String[] linkss = link.substring(1, link.length()-1).split(",");
		
		for (int i = 0; i < linkss.length; i++) {
			if(StringUtil.isNotEmpty(linkss[i])) {
				String[] datas = linkss[i].split(":");
				if(datas.length>1) {
					json.put(datas[0].replaceAll("\"", ""), datas[1].replaceAll("\"", ""));
				}
				
			}
		}

		if(json.containsKey("id")){
			
			id = json.getString("id");
		}
		RtExportAssts returnReas = new RtExportAssts();
		if (id.contains("base__")&&id.contains("_op")) {
			if(("base__"+RtPath.getPropetyValue("signOp", "").replace(";", ";base__")).contains(id.substring(0, id.lastIndexOf("_op")))){
				doOpExport(wtValue, returnReas,id);
				findInDB = true;
				signFlag = true;
			}

		} else if (id.contains("base__")&&(id.contains("_time")||id.contains("_date")||id.contains("report_sn"))) {
			if(transMap.containsKey(id)&&transMap.get(id)!=null) {
				RtExportData rtExportData = transMap.get(id);
				String value = rtExportData.getValue();
				if(value.length()>=10&&(id.contains("_time")||id.contains("_date"))) {
					wtValue = value.substring(0, 4)+"年"+value.substring(5, 7)+"月"+value.substring(8, 10)+"日";
				}else {
					wtValue = value;
				}
				
			}else {
				
				if(infoMap.get(id.replace("base__", ""))!=null){
					wtValue = infoMap.get(id.replace("base__", "")).toString();
					findInDB = false;
					signFlag = true;
				}else{
					signFlag = false;
				}
			}
		}
		if(!signFlag){
		
			if(wtValue.contains("checkItem")){
				System.out.println("-------------------checkItem---------------------");
				Sym sym = null;
					if (transMap!=null&&transMap.containsKey(id)){
						RtExportData rtExportData = transMap.get(id);
						String value = rtExportData.getValue();
						if(value!=null&&!"null".equals(value)&&StringUtil.isNotEmpty(value)){
							sym = RtSym.createF052Sym();
						}else{
							sym = RtSym.createF0A3Sym();//
						}
					}else{
						sym = RtSym.createF0A3Sym();//
					}
					if(sym!=null){
						R r = Context.getWmlObjectFactory().createR();
						r.getContent().add(sym);
						P p = (P) hyperlink.getParent();
						p.getContent().add(0,r);
					}
				
				
			} else if (transMap != null && !transMap.isEmpty()) {
				
				
	
				for (String key : transMap.keySet()) {
					// String regex = "(\\$\\{.*\"id\":\"" + key +
					// "\".*\\})";
					String regex = "(\\$\\{.*id:" + key + "[^" + RtField.separator + "].*})";
					wtValue = wtValue.replace("\"", "");
					if (Utils.transMatch(wtValue, regex)) {
						 if(wtValue.contains("TBL00644")){
							 System.out.println("...");
							 }
						
						findInDB = true;
						String suffixValue = match(wtValue);
						RtExportData rtExportData = transMap.get(key);
						String value = rtExportData.getValue();
						String image = rtExportData.getImage();
						if("base__jcgc".equals(rtExportData.getName())){
							System.out.println("-------------------------"+rtExportData.getName());
						}
						if(json.containsKey("page")){
							String page = json.getString("page");
							if("1".equals(page)){
								if(rtExportData.getFuncMap()==null){
									rtExportData.setFuncMap(new HashMap<String, Object>());
								}
								rtExportData.getFuncMap().put("size", "20px");
								rtExportData.getFuncMap().put("page", "1");
							}
						}else{
							if(rtExportData.getFuncMap()!=null&&rtExportData.getFuncMap().get("page")!=null&&"1".equals(rtExportData.getFuncMap().get("page").toString())){
								rtExportData.getFuncMap().put("size",null);
								rtExportData.getFuncMap().remove("page");
							}
						}
						String _wtValue = rtRevert.trans(wtValue, key, value == null ? "" : value.toString());
						if (suffixValue != null) {
							_wtValue = _wtValue + " " + suffixValue;
						}
	
						// >>>>>>>>>>>>>>>>>>>>>>>>>替换&lt;&gt;
						_wtValue = _wtValue.replace("&lt;", "<");
						_wtValue = _wtValue.replace("&gt;", ">");
						// >>>>>>>>>>>>>>>>>>>>>>>>>
	
						P p = (P) hyperlink.getParent();
						List<R> rs = new ArrayList<R>();
						if (image != null) {
							// pingZhou待处理
							// 处理图片
							R r = Context.getWmlObjectFactory().createR();
							RtExportAsstFactory.excute(rtExportData, wordMLPackage, r, returnReas);
							rs.add(r);
						} else if (_wtValue.contains("<sub>") || _wtValue.contains("<sup>")) {
							// wtValue是否含有<sub></sub>标签，如果有把字符串拆成多段
							List<RtExportData> rtExportDatas = splitValue(_wtValue, rtExportData);
	
							for (RtExportData red : rtExportDatas) {
								R r = Context.getWmlObjectFactory().createR();
								if (StringUtil.isNotEmpty(red.getValue())) {
									Text text = Context.getWmlObjectFactory().createText();
									text.setValue(red.getValue());
									r.getContent().add(text);
								}
								RtExportAsstFactory.excute(red, wordMLPackage, r, returnReas);
								rs.add(r);
							}
						} else {
							if (wtValue.contains("checkbox")) {
								wtValue = wtValue.trim();
								wtValue = wtValue.replaceAll("\\\\", "");
								String dataRegex = "data:\\[(.*)\\]";
								Pattern pattern = Pattern.compile(dataRegex);
								Matcher matcher = pattern.matcher(wtValue);
								if (!matcher.find()) {
									throw new Exception("checkbox object ligerui  data match error: " + wtValue);
								}
								String data = matcher.group(1);
								if (StringUtil.isEmpty(data)) {
									throw new Exception("checkbox object ligerui  data match group error: " + wtValue);
								}
								data = data.substring(1, data.length() - 1);
								String[] datas = data.split("\\},\\{");
								JSONArray dataArray = new JSONArray();
								for (String _data : datas) {
									// id:符合要求,text:符合要求
									String[] _datas = _data.split(",");
									JSONObject object = new JSONObject();
									for (String __data : _datas) {
										String[] objects = __data.split(":");
										object.put(objects[0], objects[1]);
									}
									dataArray.put(object);
								}
								if (dataArray != null && dataArray.length() > 0) {
									String[] checkeds = _wtValue.split(";");
									for (int i = 0; i < dataArray.length(); i++) {
										JSONObject _data = dataArray.getJSONObject(i);
										String _id = _data.getString("id");
										String _text = _data.getString("text");
										boolean flag = false;
										for (String checked : checkeds) {
											if (checked.equals(_id)) {
												flag = true;
												break;
											}
										}
										if (flag) {
											Sym sym = RtSym.createF052Sym();//
											R r = Context.getWmlObjectFactory().createR();
											r.getContent().add(sym);
											rs.add(r);
											R rText = Context.getWmlObjectFactory().createR();
											Text text = Context.getWmlObjectFactory().createText();
											text.setValue(_text);
											rText.getContent().add(text);
											rs.add(rText);
										} else {
											Sym sym = RtSym.createF0A3Sym();//
											R r = Context.getWmlObjectFactory().createR();
											r.getContent().add(sym);
											rs.add(r);
											R rText = Context.getWmlObjectFactory().createR();
											Text text = Context.getWmlObjectFactory().createText();
											// text.setValue("□"+ _text);
											text.setValue(_text);
											rText.getContent().add(text);
											rs.add(rText);
										}
									}
								} else {
									throw new Exception("checkbox object ligerui  data parse error: " + wtValue);
								}
	
							}else {
	
								// 处理用户手动换行问题，导出时也要换行
								if (_wtValue.contains("\n")) {
									try {
										Tc tc = (Tc) p.getParent();
										tc.getContent().remove(0);
										String[] values = _wtValue.split("\n");
										for (int i = 0; i < values.length; i++) {
											P p1 = Context.getWmlObjectFactory().createP();
											R r1 = Context.getWmlObjectFactory().createR();
											if (StringUtil.isNotEmpty(values[i])) {
												Text text = Context.getWmlObjectFactory().createText();
												text.setValue(values[i]);
												r1.getContent().add(text);
											}
											RtExportAsstFactory.excute(rtExportData, wordMLPackage, r1, returnReas);
											p1.getContent().add(0, r1);
											tc.getContent().add(p1);
										}
									} catch (Exception e) {
										e.printStackTrace();
										R r = Context.getWmlObjectFactory().createR();
										if (StringUtil.isNotEmpty(_wtValue)) {
											Text text = Context.getWmlObjectFactory().createText();
											text.setValue(_wtValue);
											r.getContent().add(text);
										}
										RtExportAsstFactory.excute(rtExportData, wordMLPackage, r, returnReas);
										rs.add(r);
	
									}
	
								} else {
									R r = Context.getWmlObjectFactory().createR();
									if (StringUtil.isNotEmpty(_wtValue)) {
										Text text = Context.getWmlObjectFactory().createText();
										text.setValue(_wtValue);
										r.getContent().add(text);
									}
									RtExportAsstFactory.excute(rtExportData, wordMLPackage, r, returnReas);
									rs.add(r);
								}
	
							}
						}
	
						int idx = indexOf(p.getContent(), hyperlink);
						if (idx >= 0) {
							p.getContent().remove(idx);
							for (int n = rs.size() - 1; n >= 0; n--) {
								p.getContent().add(idx, rs.get(n));
							}
						}
						break;
					}
				}
			}
			
		}
		if (!findInDB) {
			// 置空
			if (wtValue.contains("checkbox")) {
				List<R> rs = new ArrayList<R>();
				wtValue = wtValue.trim();
				wtValue = wtValue.replaceAll("\\\\", "");
				String dataRegex = "data:\\[(.*)\\]";
				Pattern pattern = Pattern.compile(dataRegex);
				Matcher matcher = pattern.matcher(wtValue);
				if (!matcher.find()) {
					throw new Exception("checkbox object ligerui  data match error: " + wtValue);
				}
				String data = matcher.group(1);
				if (StringUtil.isEmpty(data)) {
					throw new Exception("checkbox object ligerui  data match group error: " + wtValue);
				}
				data = data.substring(1, data.length() - 1);
				String[] datas = data.split("\\},\\{");
				JSONArray dataArray = new JSONArray();
				for (String _data : datas) {
					// id:符合要求,text:符合要求
					String[] _datas = _data.split(",");
					JSONObject object = new JSONObject();
					for (String __data : _datas) {
						String[] objects = __data.split(":");
						object.put(objects[0], objects[1]);
					}
					dataArray.put(object);
				}
				if (dataArray != null && dataArray.length() > 0) {
					for (int i = 0; i < dataArray.length(); i++) {
						JSONObject _data = dataArray.getJSONObject(i);
						String _text = _data.getString("text");
						R rText = Context.getWmlObjectFactory().createR();
						Text text = Context.getWmlObjectFactory().createText();
						text.setValue("□" + _text);
						rText.getContent().add(text);
						rs.add(rText);
					}
				} else {
					throw new Exception("checkbox object ligerui  data parse error: " + wtValue);
				}
				P p = (P) hyperlink.getParent();
				int idx = indexOf(p.getContent(), hyperlink);
				if (idx >= 0) {
					p.getContent().remove(idx);
					for (int n = rs.size() - 1; n >= 0; n--) {
						p.getContent().add(idx, rs.get(n));
					}

				}

			} else {
				wtValue = HtmlUtils.trimHtmlTxt(wtValue);
				wtValue = wtValue.replace("\\", "");
				String regex = "(\\$\\{.*\\})";
				String Blank = "";
				if (Utils.transMatch(wtValue, regex)) {
					String suffixValue = match(wtValue);
					wtValue = rtRevert.transBlank(wtValue, Blank);
					if (suffixValue != null) {
						wtValue = wtValue + " " + suffixValue;
					}
				}
				P p = (P) hyperlink.getParent();
				R r = Context.getWmlObjectFactory().createR();
				if (StringUtil.isNotEmpty(wtValue)) {
					Text text = Context.getWmlObjectFactory().createText();
					text.setValue(wtValue);
					r.getContent().add(text);
				}
				int idx = indexOf(p.getContent(), hyperlink);
				if (idx >= 0) {
					p.getContent().remove(idx);
					p.getContent().add(idx, r);

				}
			}

		}
		return returnReas;

	}

	private void doOpExport(String wtValue, RtExportAssts returnReas,String id) throws Exception {
		List<R> rs = new ArrayList<R>();
		HttpServletRequest request = (HttpServletRequest) infoMap.get("request");
		ServletContext application = request.getServletContext();
		//去掉前缀
		id = id.replace("base__", "");
		
		if (id.contains("inspect_op")) {
			String check_op = infoMap.get("check_op").toString();
			
			if (!"inspect_op".equals(id) &&transMap!=null && transMap.containsKey(id)) {
				// 处理单独检验员 pingZhou-20170829
				RtExportData rtExportData = transMap.get(id);
				if (rtExportData != null && rtExportData.getValue() != null && StringUtil.isNotEmpty(rtExportData.getValue())) {
					String check_opdb = rtExportData.getValue();
					if(!check_op.equals(check_opdb)){
						String [] check_opss = check_opdb.split(",");
						if(check_opss.length==1){
							check_opss = check_opdb.split(";");
						}
						check_op = null;
						for (int i = 0; i < check_opss.length; i++) {
							if(infoMap.get("check_op"+check_opss[i])!=null){
								String sign = infoMap.get("check_op"+check_opss[i]).toString();
								if(check_op==null){
									check_op = sign;
								}else{
									check_op = check_op +","+ sign;
								}
							}
							
							
						}
					}
					
				}
			}

			String[] check_ops = check_op.split(",");
			if(check_ops.length==1){
				check_ops = check_op.split(";");
			}
			
			for (int i = 0; i < check_ops.length; i++) {
				R r = Context.getWmlObjectFactory().createR();
				byte[] signByte = application.getAttribute("sign_"+check_ops[i])==null?null:(byte[])(application.getAttribute("sign_"+check_ops[i]));
				opSignExcute(check_ops[i], r, returnReas,signByte);
				rs.add(r);
			}
			P p = (P) hyperlink.getParent();
			int idx = indexOf(p.getContent(), hyperlink);
			if (idx >= 0) {
				p.getContent().remove(idx);
				for (int n = rs.size() - 1; n >= 0; n--) {
					p.getContent().add(idx, rs.get(n));
				}
			}
		} else if (id.contains("enter_op")) {
			//String op = infoMap.get("enter_op").toString();
			//opSign(hyperlink, wordMLPackage, returnReas, op);
			R r = Context.getWmlObjectFactory().createR();
			wtValue = infoMap.get("enter_op").toString();

			if (!"enter_op".equals(id) &&transMap!=null && transMap.containsKey(id)) {
				// 处理单独审核员 pingZhou-20170829
				RtExportData rtExportData = transMap.get(id);
				if (rtExportData != null && rtExportData.getValue() != null && StringUtil.isNotEmpty(rtExportData.getValue())) {
					wtValue = rtExportData.getValue();
					wtValue = infoMap.get("check_op"+wtValue).toString();
				}
			}
			byte[] signByte = application.getAttribute("sign_"+wtValue)==null?null:(byte[])(application.getAttribute("sign_"+wtValue));
			opSignExcute(wtValue, r, returnReas,signByte);
			rs.add(r);
			P p = (P) hyperlink.getParent();
			int idx = indexOf(p.getContent(), hyperlink);
			if (idx >= 0) {
				p.getContent().remove(idx);
				for (int n = rs.size() - 1; n >= 0; n--) {
					p.getContent().add(idx, rs.get(n));
				}
			}

		} else if (id.contains("confirm_op")) {
			R r = Context.getWmlObjectFactory().createR();
			if(infoMap.get("confirm_op")!=null&&StringUtil.isNotEmpty(infoMap.get("confirm_op").toString())){
				wtValue = infoMap.get("confirm_op").toString();
			}else{
				wtValue = infoMap.get("enter_op").toString();
			}
			if (!"confirm_op".equals(id) &&transMap!=null&& transMap.containsKey(id)) {
				// 西昌编制人员需要选择，没有选择就默认为录入人员 兼容新旧报告 pingZhou-20170829
				RtExportData rtExportData = transMap.get(id);
				if (rtExportData != null && rtExportData.getValue() != null && StringUtil.isNotEmpty(rtExportData.getValue())) {
					wtValue = rtExportData.getValue();
					wtValue = infoMap.get("check_op"+wtValue).toString();
					
				}else if(infoMap.get("enter_op")!=null){
					wtValue = infoMap.get("enter_op").toString();
				}
			}
			byte[] signByte = application.getAttribute("sign_"+wtValue)==null?null:(byte[])(application.getAttribute("sign_"+wtValue));
			opSignExcute(wtValue, r, returnReas,signByte);
			rs.add(r);
			P p = (P) hyperlink.getParent();
			int idx = indexOf(p.getContent(), hyperlink);
			if (idx >= 0) {
				p.getContent().remove(idx);
				for (int n = rs.size() - 1; n >= 0; n--) {
					p.getContent().add(idx, rs.get(n));
				}
			}
			//String op = infoMap.get("confirm_op").toString();
			//opSign(hyperlink, wordMLPackage, returnReas, op);

		} else if (id.contains("audit_op")) {
			R r = Context.getWmlObjectFactory().createR();
			wtValue = infoMap.get("audit_op").toString();

			if (!"audit_op".equals(id) &&transMap!=null && transMap.containsKey(id)) {
				// 处理单独审核员 pingZhou-20170829
				RtExportData rtExportData = transMap.get(id);
				if (rtExportData != null && rtExportData.getValue() != null && StringUtil.isNotEmpty(rtExportData.getValue())) {
					wtValue = rtExportData.getValue();
					wtValue = infoMap.get("check_op"+wtValue).toString();
				}
			}
			byte[] signByte = application.getAttribute("sign_"+wtValue)==null?null:(byte[])(application.getAttribute("sign_"+wtValue));
			opSignExcute(wtValue, r, returnReas,signByte);
			rs.add(r);
			P p = (P) hyperlink.getParent();
			int idx = indexOf(p.getContent(), hyperlink);
			if (idx >= 0) {
				p.getContent().remove(idx);
				for (int n = rs.size() - 1; n >= 0; n--) {
					p.getContent().add(idx, rs.get(n));
				}
			}

		} else if (id.contains("sign_op")) {
			R r = Context.getWmlObjectFactory().createR();
			String op = infoMap.get("sign_op").toString();
			byte[] signByte = application.getAttribute("sign_"+op)==null?null:(byte[])(application.getAttribute("sign_"+op));
			opSignExcute(op, r, returnReas,signByte);
			rs.add(r);
			P p = (P) hyperlink.getParent();
			int idx = indexOf(p.getContent(), hyperlink);
			if (idx >= 0) {
				p.getContent().remove(idx);
				for (int n = rs.size() - 1; n >= 0; n--) {
					p.getContent().add(idx, rs.get(n));
				}
			}
			//String op = infoMap.get("sign_op").toString();
			//opSign(hyperlink, wordMLPackage, returnReas, op);
		}

	}

	private void opSign(Hyperlink hyperlink, WordprocessingMLPackage wordMLPackage, RtExportAssts rtExportDatas, String wtValue) throws Exception {
		List<R> rs = new ArrayList<R>();
		R r = Context.getWmlObjectFactory().createR();
		opSignExcute(wtValue, r, rtExportDatas,null);
		rs.add(r);
		P p = (P) hyperlink.getParent();
		int idx = indexOf(p.getContent(), hyperlink);
		if (idx >= 0) {
			p.getContent().remove(idx);
			for (int n = rs.size() - 1; n >= 0; n--) {
				p.getContent().add(idx, rs.get(n));
			}
		}

	}

	private void opSignExcute(String wtValue, R r, RtExportAssts rtExportDatas,byte[] signByte) throws Exception {
		RtExportData rtExportData = new RtExportData();
		Map<String, Object> funcMap = new HashMap<String, Object>();
		funcMap.put(RtExportDataType.EXPORT_DATA_SIGN, wtValue);
		rtExportData.setFuncMap(funcMap);
		rtExportData.setValue(wtValue);
		rtExportData.setSignByte(signByte);
		RtExportAsstFactory.excute(rtExportData, wordMLPackage, r, rtExportDatas);
	}

	@Deprecated
	public RtExportAssts filterbak(Map<String, RtExportData> transMap, String wtValue, RtRevert rtRevert, Hyperlink hyperlink, WordprocessingMLPackage wordMLPackage,
			HashMap<String, Object> infoMap) throws Exception {
		boolean findInDB = false;
		RtExportAssts returnReas = new RtExportAssts();
		if (wtValue.contains("inspect_op") || wtValue.contains("audit_op") || wtValue.contains("sign_op") || wtValue.contains("enter_op") || wtValue.contains("confirm_op")) {

			List<R> rs = new ArrayList<R>();
			if (wtValue.contains("inspect_op")) {
				String check_op = infoMap.get("check_op").toString();
				if (!"inspect_op".equals(wtValue) && transMap.containsKey(wtValue)) {
					// 处理单独检验员 pingZhou-20170829
					RtExportData rtExportData = transMap.get(wtValue);
					if (rtExportData != null && rtExportData.getValue() != null && StringUtil.isNotEmpty(rtExportData.getValue())) {
						check_op = rtExportData.getValue();
					}
				}

				String[] check_ops = check_op.split(",");

				for (int i = 0; i < check_ops.length; i++) {
					R r = Context.getWmlObjectFactory().createR();
					RtExportAsstFactory.addPicture(wordMLPackage, i, r, check_ops[i]);
					rs.add(r);
					// addSignPicture(wordMLPackage,check_ops[i],i,r,);
				}
				P p = (P) hyperlink.getParent();
				int idx = indexOf(p.getContent(), hyperlink);
				if (idx >= 0) {
					p.getContent().remove(idx);
					for (int n = rs.size() - 1; n >= 0; n--) {
						p.getContent().add(idx, rs.get(n));
					}
				}
			} else if (wtValue.contains("enter_op")) {
				R r = Context.getWmlObjectFactory().createR();
				wtValue = infoMap.get("enter_op").toString();
				RtExportAsstFactory.addPicture(wordMLPackage, 0, r, wtValue);
				rs.add(r);
				P p = (P) hyperlink.getParent();
				int idx = indexOf(p.getContent(), hyperlink);
				if (idx >= 0) {
					p.getContent().remove(idx);
					for (int n = rs.size() - 1; n >= 0; n--) {
						p.getContent().add(idx, rs.get(n));
					}
				}
				// addSignPicture(wordMLPackage,wtValue,0,r);

			} else if (wtValue.contains("confirm_op")) {
				R r = Context.getWmlObjectFactory().createR();
				wtValue = infoMap.get("confirm_op").toString();
				RtExportAsstFactory.addPicture(wordMLPackage, 0, r, wtValue);
				rs.add(r);
				P p = (P) hyperlink.getParent();
				int idx = indexOf(p.getContent(), hyperlink);
				if (idx >= 0) {
					p.getContent().remove(idx);
					for (int n = rs.size() - 1; n >= 0; n--) {
						p.getContent().add(idx, rs.get(n));
					}
				}
				// addSignPicture(wordMLPackage,wtValue,0,r);

			} else if (wtValue.contains("audit_op")) {
				R r = Context.getWmlObjectFactory().createR();
				wtValue = infoMap.get("audit_op").toString();

				if (!"audit_op".equals(wtValue) && transMap.containsKey(wtValue)) {
					// 处理单独审核员 pingZhou-20170829
					RtExportData rtExportData = transMap.get(wtValue);
					if (rtExportData != null && rtExportData.getValue() != null && StringUtil.isNotEmpty(rtExportData.getValue())) {
						wtValue = rtExportData.getValue();
					}
				}

				RtExportAsstFactory.addPicture(wordMLPackage, 0, r, wtValue);
				rs.add(r);
				P p = (P) hyperlink.getParent();
				int idx = indexOf(p.getContent(), hyperlink);
				if (idx >= 0) {
					p.getContent().remove(idx);
					for (int n = rs.size() - 1; n >= 0; n--) {
						p.getContent().add(idx, rs.get(n));
					}
				}
				// addSignPicture(wordMLPackage,wtValue,0,r);

			} else if (wtValue.contains("sign_op")) {
				R r = Context.getWmlObjectFactory().createR();
				wtValue = infoMap.get("sign_op").toString();
				RtExportAsstFactory.addPicture(wordMLPackage, 0, r, wtValue);
				rs.add(r);
				P p = (P) hyperlink.getParent();
				int idx = indexOf(p.getContent(), hyperlink);
				if (idx >= 0) {
					p.getContent().remove(idx);
					for (int n = rs.size() - 1; n >= 0; n--) {
						p.getContent().add(idx, rs.get(n));
					}
				}
				// addSignPicture(wordMLPackage,wtValue,0,r);

			}

			findInDB = true;

		} else if (wtValue.contains("inspect_time") || wtValue.contains("audit_time") || wtValue.contains("sign_time")) {
			if (wtValue.contains("inspect_time")) {
				wtValue = infoMap.get("inspect_time").toString();
			} else if (wtValue.contains("audit_time")) {
				wtValue = infoMap.get("audit_time").toString();
			} else if (wtValue.contains("sign_time")) {
				wtValue = infoMap.get("sign_time").toString();
			}
			findInDB = false;
		} else if (transMap != null && !transMap.isEmpty()) {

			for (String key : transMap.keySet()) {
				// String regex = "(\\$\\{.*\"id\":\"" + key +
				// "\".*\\})";
				String regex = "(\\$\\{.*id:" + key + "[^" + RtField.separator + "].*})";
				wtValue = wtValue.replace("\"", "");
				// if(wtValue.contains("TBL00007__2_1")&&key.contains("TBL00007")){
				// System.out.println("...");
				// }
				if (Utils.transMatch(wtValue, regex)) {
					findInDB = true;
					String suffixValue = match(wtValue);
					RtExportData rtExportData = transMap.get(key);
					String value = rtExportData.getValue();
					String image = rtExportData.getImage();
					String _wtValue = rtRevert.trans(wtValue, key, value == null ? "" : value.toString());
					if (suffixValue != null) {
						_wtValue = _wtValue + " " + suffixValue;
					}

					// >>>>>>>>>>>>>>>>>>>>>>>>>替换&lt;&gt;
					_wtValue = _wtValue.replace("&lt;", "<");
					_wtValue = _wtValue.replace("&gt;", ">");
					// >>>>>>>>>>>>>>>>>>>>>>>>>

					P p = (P) hyperlink.getParent();
					List<R> rs = new ArrayList<R>();
					if (image != null) {
						// pingZhou待处理
						// 处理图片
						R r = Context.getWmlObjectFactory().createR();
						RtExportAsstFactory.excute(rtExportData, wordMLPackage, r, returnReas);
						rs.add(r);
					} else if (_wtValue.contains("<sub>") || _wtValue.contains("<sup>")) {
						// wtValue是否含有<sub></sub>标签，如果有把字符串拆成多段
						List<RtExportData> rtExportDatas = splitValue(_wtValue, rtExportData);

						for (RtExportData red : rtExportDatas) {
							R r = Context.getWmlObjectFactory().createR();
							if (StringUtil.isNotEmpty(red.getValue())) {
								Text text = Context.getWmlObjectFactory().createText();
								text.setValue(red.getValue());
								r.getContent().add(text);
							}
							RtExportAsstFactory.excute(red, wordMLPackage, r, returnReas);
							rs.add(r);
						}
					} else {
						if (wtValue.contains("checkbox")) {
							wtValue = wtValue.trim();
							wtValue = wtValue.replaceAll("\\\\", "");
							String dataRegex = "data:\\[(.*)\\]";
							Pattern pattern = Pattern.compile(dataRegex);
							Matcher matcher = pattern.matcher(wtValue);
							if (!matcher.find()) {
								throw new Exception("checkbox object ligerui  data match error: " + wtValue);
							}
							String data = matcher.group(1);
							if (StringUtil.isEmpty(data)) {
								throw new Exception("checkbox object ligerui  data match group error: " + wtValue);
							}
							data = data.substring(1, data.length() - 1);
							String[] datas = data.split("\\},\\{");
							JSONArray dataArray = new JSONArray();
							for (String _data : datas) {
								// id:符合要求,text:符合要求
								String[] _datas = _data.split(",");
								JSONObject object = new JSONObject();
								for (String __data : _datas) {
									String[] objects = __data.split(":");
									object.put(objects[0], objects[1]);
								}
								dataArray.put(object);
							}
							if (dataArray != null && dataArray.length() > 0) {
								String[] checkeds = _wtValue.split(";");
								for (int i = 0; i < dataArray.length(); i++) {
									JSONObject _data = dataArray.getJSONObject(i);
									String _id = _data.getString("id");
									String _text = _data.getString("text");
									boolean flag = false;
									for (String checked : checkeds) {
										if (checked.equals(_id)) {
											flag = true;
											break;
										}
									}
									if (flag) {
										Sym sym = RtSym.createF052Sym();//
										R r = Context.getWmlObjectFactory().createR();
										r.getContent().add(sym);
										rs.add(r);
										R rText = Context.getWmlObjectFactory().createR();
										Text text = Context.getWmlObjectFactory().createText();
										text.setValue(_text);
										rText.getContent().add(text);
										rs.add(rText);
									} else {
										Sym sym = RtSym.createF0A3Sym();//
										R r = Context.getWmlObjectFactory().createR();
										r.getContent().add(sym);
										rs.add(r);
										R rText = Context.getWmlObjectFactory().createR();
										Text text = Context.getWmlObjectFactory().createText();
										// text.setValue("□"+ _text);
										text.setValue(_text);
										rText.getContent().add(text);
										rs.add(rText);
									}
								}
							} else {
								throw new Exception("checkbox object ligerui  data parse error: " + wtValue);
							}

						} else {

							// 处理用户手动换行问题，导出时也要换行
							if (_wtValue.contains("\n")) {
								try {
									Tc tc = (Tc) p.getParent();
									tc.getContent().remove(0);
									String[] values = _wtValue.split("\n");
									for (int i = 0; i < values.length; i++) {
										P p1 = Context.getWmlObjectFactory().createP();
										R r1 = Context.getWmlObjectFactory().createR();
										if (StringUtil.isNotEmpty(values[i])) {
											Text text = Context.getWmlObjectFactory().createText();
											text.setValue(values[i]);
											r1.getContent().add(text);
										}
										RtExportAsstFactory.excute(rtExportData, wordMLPackage, r1, returnReas);
										p1.getContent().add(0, r1);
										tc.getContent().add(p1);
									}
								} catch (Exception e) {
									e.printStackTrace();
									R r = Context.getWmlObjectFactory().createR();
									if (StringUtil.isNotEmpty(_wtValue)) {
										Text text = Context.getWmlObjectFactory().createText();
										text.setValue(_wtValue);
										r.getContent().add(text);
									}
									RtExportAsstFactory.excute(rtExportData, wordMLPackage, r, returnReas);
									rs.add(r);

								}

							} else {
								R r = Context.getWmlObjectFactory().createR();
								if (StringUtil.isNotEmpty(_wtValue)) {
									Text text = Context.getWmlObjectFactory().createText();
									text.setValue(_wtValue);
									r.getContent().add(text);
								}
								RtExportAsstFactory.excute(rtExportData, wordMLPackage, r, returnReas);
								rs.add(r);
							}

						}
					}

					int idx = indexOf(p.getContent(), hyperlink);
					if (idx >= 0) {
						p.getContent().remove(idx);
						for (int n = rs.size() - 1; n >= 0; n--) {
							p.getContent().add(idx, rs.get(n));
						}
					}
					break;
				}
			}
		}

		if (!findInDB) {
			// 置空
			if (wtValue.contains("checkbox")) {
				List<R> rs = new ArrayList<R>();
				wtValue = wtValue.trim();
				wtValue = wtValue.replaceAll("\\\\", "");
				String dataRegex = "data:\\[(.*)\\]";
				Pattern pattern = Pattern.compile(dataRegex);
				Matcher matcher = pattern.matcher(wtValue);
				if (!matcher.find()) {
					throw new Exception("checkbox object ligerui  data match error: " + wtValue);
				}
				String data = matcher.group(1);
				if (StringUtil.isEmpty(data)) {
					throw new Exception("checkbox object ligerui  data match group error: " + wtValue);
				}
				data = data.substring(1, data.length() - 1);
				String[] datas = data.split("\\},\\{");
				JSONArray dataArray = new JSONArray();
				for (String _data : datas) {
					// id:符合要求,text:符合要求
					String[] _datas = _data.split(",");
					JSONObject object = new JSONObject();
					for (String __data : _datas) {
						String[] objects = __data.split(":");
						object.put(objects[0], objects[1]);
					}
					dataArray.put(object);
				}
				if (dataArray != null && dataArray.length() > 0) {
					for (int i = 0; i < dataArray.length(); i++) {
						JSONObject _data = dataArray.getJSONObject(i);
						String _text = _data.getString("text");
						R rText = Context.getWmlObjectFactory().createR();
						Text text = Context.getWmlObjectFactory().createText();
						text.setValue("□" + _text);
						rText.getContent().add(text);
						rs.add(rText);
					}
				} else {
					throw new Exception("checkbox object ligerui  data parse error: " + wtValue);
				}
				P p = (P) hyperlink.getParent();
				int idx = indexOf(p.getContent(), hyperlink);
				if (idx >= 0) {
					p.getContent().remove(idx);
					for (int n = rs.size() - 1; n >= 0; n--) {
						p.getContent().add(idx, rs.get(n));
					}

				}

			} else {
				wtValue = HtmlUtils.trimHtmlTxt(wtValue);
				String regex = "(\\$\\{.*\\})";
				String Blank = "";
				if (Utils.transMatch(wtValue, regex)) {
					String suffixValue = match(wtValue);
					wtValue = rtRevert.transBlank(wtValue, Blank);
					if (suffixValue != null) {
						wtValue = wtValue + " " + suffixValue;
					}
				}
				P p = (P) hyperlink.getParent();
				R r = Context.getWmlObjectFactory().createR();
				if (StringUtil.isNotEmpty(wtValue)) {
					Text text = Context.getWmlObjectFactory().createText();
					text.setValue(wtValue);
					r.getContent().add(text);
				}
				int idx = indexOf(p.getContent(), hyperlink);
				if (idx >= 0) {
					p.getContent().remove(idx);
					p.getContent().add(idx, r);

				}
			}

		}
		return returnReas;

	}

	public List<RtExportData> splitValue(String value, RtExportData rtExportData) throws OptionalDataException, ClassNotFoundException, IOException {
		if (StringUtil.isEmpty(value)) {
			return null;
		}
		List<RtExportData> list = new ArrayList<RtExportData>();
		String regex = "<sub>|<sup>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		if (matcher.find()) {
			String[] vals = value.split(regex);
			for (int i = 0; i < vals.length; i++) {
				if (vals[i].indexOf("</sub>") != -1) {
					String[] tagAndText = vals[i].split("</sub>");
					if (tagAndText.length == 1) {
						RtExportData dataValue = (RtExportData) rtExportData.deepClone();
						dataValue.setValue(tagAndText[0]);
						if(dataValue.getFuncMap()==null){
							dataValue.setFuncMap(new HashMap<String, Object>());
						}
						dataValue.getFuncMap().put("sub", true);
						list.add(dataValue);
					} else if (tagAndText.length == 2) {
						RtExportData dataValue = (RtExportData) rtExportData.deepClone();
						dataValue.setValue(tagAndText[0]);
						if(dataValue.getFuncMap()==null){
							dataValue.setFuncMap(new HashMap<String, Object>());
						}
						dataValue.getFuncMap().put("sub", true);
						list.add(dataValue);
						if (!StringUtil.isEmpty(tagAndText[1])) {
							RtExportData dataValue2 = (RtExportData) rtExportData.deepClone();
							dataValue2.setValue(tagAndText[1]);
							list.add(dataValue2);
						}
					}
				} else if (vals[i].indexOf("</sup>") != -1) {
					String[] tagAndText = vals[i].split("</sup>");
					if (tagAndText.length == 1) {
						RtExportData dataValue = (RtExportData) rtExportData.deepClone();
						dataValue.setValue(tagAndText[0]);
						if(dataValue.getFuncMap()==null){
							dataValue.setFuncMap(new HashMap<String, Object>());
						}
						dataValue.getFuncMap().put("sup", true);
						list.add(dataValue);
					} else if (tagAndText.length == 2) {
						RtExportData dataValue = (RtExportData) rtExportData.deepClone();
						dataValue.setValue(tagAndText[0]);
						if(dataValue.getFuncMap()==null){
							dataValue.setFuncMap(new HashMap<String, Object>());
						}
						dataValue.getFuncMap().put("sup", true);
						list.add(dataValue);
						if (!StringUtil.isEmpty(tagAndText[1])) {
							RtExportData dataValue2 = (RtExportData) rtExportData.deepClone();
							dataValue2.setValue(tagAndText[1]);
							list.add(dataValue2);
						}
					}
				} else {
					RtExportData dataValue = (RtExportData) rtExportData.deepClone();
					dataValue.setValue(vals[i]);
					list.add(dataValue);
				}

			}
		} else {
			rtExportData.setValue(value);
			list.add(rtExportData);
		}
		return list;
	}

	public int indexOf(List<Object> list, Object o) {
		for (int i = 0, l = list.size(); i < l; i++) {
			Object obj = list.get(i);
			if (obj instanceof JAXBElement) {
				if (((JAXBElement<?>) obj).getDeclaredType() == Hyperlink.class) {
					Hyperlink hyperlink = (Hyperlink) ((JAXBElement<?>) obj).getValue();
					if (hyperlink == o) {
						return i;
					}
				} else {
					log.info(((JAXBElement<?>) obj).getDeclaredType().toString());
				}
			}
		}
		return -1;
	}

	public String match(String value) {
		if (StringUtil.isEmpty(value)) {
			return null;
		}
		value = value.trim();
		String suffix = "suffix:";
		int pos = value.indexOf(suffix);
		if (pos < 0) {
			return null;
		}
		pos += suffix.length();
		int pos1 = value.indexOf(",", pos);
		int pos2 = value.indexOf("}", pos);
		if (pos1 > pos2) {
			pos2 = pos1;
		}
		String suffixValue = value.substring(pos, pos2);
		return suffixValue;
	}
}
