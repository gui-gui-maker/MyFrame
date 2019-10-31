package com.khnt.rtbox.template.parse.tag.impl;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Body;
import org.docx4j.wml.P;
import org.docx4j.wml.P.Hyperlink;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Tr;

import com.khnt.base.Factory;
import com.khnt.rtbox.config.bean.BaseConfig;
import com.khnt.rtbox.config.bean.RtPage;
import com.khnt.rtbox.template.components.RtHyperlink;
import com.khnt.rtbox.template.constant.RtBlankType;
import com.khnt.rtbox.template.constant.RtField;
import com.khnt.rtbox.template.constant.RtPageType;
import com.khnt.rtbox.template.constant.RtPath;
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
 *         为用户第一次提交的DOCX，所有段落中的空白标记，订制唯一标识符及其他属性
 *         </p>
 */
public class FirstPCustomRound extends CustomRound {
	public static int customWidth = 120;

	public FirstPCustomRound() {
		this.count = new RtCount();
	}

	public FirstPCustomRound(RtCount count) {
		this.count = count;
	}

	@Override
	public void mark(MainDocumentPart documentPart, RtPage rtPage) throws Exception {
		log.debug("FirstPCustomRound mark begin...");
		List<BaseConfig> list = BaseUtil.getConfigList();
		/*
		 * List<BaseConfig> list = new ArrayList<BaseConfig>(); BaseConfig bc =
		 * new BaseConfig(); bc.setName("设备代码"); bc.setCode("sbdm");
		 * bc.setType("text"); list.add(bc);
		 */
		UnitBlankRound ubr = new UnitBlankRound();
		List<Object> rs = Docx4jUtil.getAllElementFromObject(documentPart, Hyperlink.class);

	
		// 用于保存前一次取到的序号和生成的序号内容
		//String preNum = "";
		StringBuffer preNum = new StringBuffer();
		
		// for (Object obj : rs) {
		for (int j = 0; j < rs.size(); j++) {

			Object obj = rs.get(j);
			// 检验项目及其内容前缀
			String name = RtPath.getPropetyValue("inspect_record", "record")+"__";

			Hyperlink hyperlink = (Hyperlink) obj;
			String val = Docx4jUtil.getElementContent(hyperlink);
			if (!val.contains(BlANKTAG)) {
				continue;
			}

			// find previous tc
			P p = (P) hyperlink.getParent();
			// ZQ EDIT 20170908 增加未在TC中的P的处理，BASECONFIG未完成
			if (p.getParent() instanceof Tc) {
				doMarkTc(documentPart, rtPage, ubr, p, preNum, name, list, rs, j, val, hyperlink);
			} else if (p.getParent() instanceof Body) {
				doMarkP(documentPart, rtPage, ubr, p, preNum, name, list, rs, j, val, hyperlink);
			}

		}

		log.debug("FirstPCustomRound mark successed...");
	}

	public void doMarkP(MainDocumentPart documentPart, RtPage rtPage, UnitBlankRound ubr, P p, StringBuffer preNum, String name, List<BaseConfig> list, List<Object> rs, int j,
			String val, Hyperlink hyperlink) throws Exception {
		String id = null;// 替换input的ID
		String inputType = null;// 替换input的ltype
		BaseConfig baseConfig = null;
		if (id == null) {
			
			//模板自定义起始序号时，起始序号取自模板
			if(this.count.getCount()==1&&rtPage.getFirstNum()!=null){
				this.count.setCount(rtPage.getFirstNum());
			}
			
			this.count.add1();
			id = this.count.id(RtPageType.TABLE);
		}
		val = HtmlUtils.decode(val);
		String linkText = val.replace(BlANKTAG, "${id:" + id + "}");
		RtHyperlink.setText(hyperlink, linkText);
		List<Object> hyperLinkList = Docx4jUtil.getAllElementFromObject(p, Hyperlink.class);

		String tag = "$" + autoConfigP(id, ubr, val, null, hyperLinkList.size(), inputType, baseConfig,rtPage);
		documentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId()).setTarget(tag);
	}

	public void doMarkTc(MainDocumentPart documentPart, RtPage rtPage, UnitBlankRound ubr, P p, StringBuffer preNum, String name, List<BaseConfig> list, List<Object> rs, int j,
			String val, Hyperlink hyperlink) throws Exception {
		String id = null;// 替换input的ID
		String inputType = null;// 替换input的ltype
		Tc tc = (Tc) p.getParent();
		Tr tr = (Tr) tc.getParent();
		List<Object> tcs = tr.getContent();
		BaseConfig baseConfig = null;
		String numId = "";
		
		// 取到整行的所有内容
		String trTcText = Docx4jUtil.getElementContent(tr);
					// System.out.println("--------行值-------"+trTcText+"-------------"+Utils.getNumFromStr(trTcText));
					// 取得行内容里面的数字及特殊字符
		String numStr = Utils.getNumFromStr(trTcText);
		//电梯原始记录检验类型☆
		String inspectStr = Utils.getInspectTypeFromStr(trTcText);
		
		System.out.println(trTcText+"---------"+numStr+"------------------"+inspectStr);
		
		if((RtPath.getPropetyValue("reportType", "")+",").contains(rtPage.getReportType()+",")){
			//机电类类报告才需要此操作 pingZhou 20171213
			
			String [] numss = numStr.split(" ");
			int ll = numss.length;
			if(ll>4) {
				ll = 4;
			}
			numStr = "";
			for (int i = 0; i < numss.length; i++) {
				
				String nums = numss[i];
				if(nums.contains(".")&&i>2) {
					ll = i+1;
					break;
				}
				numStr = numStr +" "+ numss[i];
			}
			
			// 生成需要的命名序号
			
			boolean flag = false;
			if (StringUtil.isNotEmpty(numStr)) {
				if (numStr.contains(".")) {
					String[] strs = numStr.split(" ");
					for (int i = 0; i < strs.length; i++) {
						if (strs[i].contains(".")) {
							preNum.delete(0, preNum.length());
							if(inspectStr!=null) {
								numId = numId + inspectStr+"_";
								preNum.append(inspectStr+"_");
							}
							preNum.append(strs[i].replace("★", "").replace(".", "_"));
							
							if (strs.length > i + 1) {
								numId = numId + strs[i].replace("★", "").replace(".", "_") + "_" + strs[i + 1].replace("★", "");
							} else {
								numId = numId + strs[i].replace("★", "").replace(".", "_");
							}
	
						}
						if (strs[i].contains("★")) {
							flag = true;
						}
					}
	
				} else if (StringUtil.isNotEmpty(preNum.toString())&&StringUtil.isNotEmpty(numStr) && !numStr.contains(".")) {
					String[] strs = numStr.split(" ");
					
					for (int i = 0; i < strs.length; i++) {
						if (strs[i].contains("★")) {
							flag = true;
						}
					}
					if(strs.length>1) {
						numId = preNum.toString() + "_" + strs[strs.length-1].replace("★", "");
					}
					
				}
			} else {
				// 跳出检验项目及其内容是设空值，以免影响后面命名
				preNum.delete(0, preNum.length());;
			}
	
			// "★"的做特殊标注，以便以后统计
			if (StringUtil.isNotEmpty(numId) && flag) {
				numId = numId + "S";
			}
		}
		
		System.out.println("--------------numId----------------"+numId);
		if (StringUtil.isEmpty(numId)) {
			for (int i = 0; i < tcs.size(); i++) {
				Tc curTc = (Tc) ((JAXBElement<?>) tcs.get(i)).getValue();
				if (tc == curTc) {
					if (i > 0) {
						// get the previous tc ,fit in baseconfig?
						Tc preTc = (Tc) ((JAXBElement<?>) tcs.get(i - 1)).getValue();
						String preTcText = Docx4jUtil.getElementContent(preTc);
						// 获取基础信息命名
						// /System.out.println("preTcText---------------------"+preTcText+"--------------");
						if("日期".equals(preTcText.replace(" ", "").replace(":", "").replace("：", "").trim())){
							baseConfig = getConfigByName(list, numStr, rtPage);
						}else{
							baseConfig = getConfigByName(list, preTcText, rtPage);
							if(baseConfig==null){
								baseConfig = getConfigByName(list, numStr, rtPage);
							}
						}
						
						
						if (baseConfig != null) {
							// System.out.println("---------code---------------------"+
							// baseConfig.getCode()+"--------------");
							// pingZhou2017/03/17修改 基础信息加上统一前缀
							id = "base__" + baseConfig.getCode();
							inputType = baseConfig.getType();
						}
					}
					break;
				}
			}
		} else {
			if((RtPath.getPropetyValue("reportType", "")+",").contains(rtPage.getReportType()+",")){
				//机电类类报告才需要此操作 pingZhou 20171213
				if("1".equals(rtPage.getModelType())||"4000".equals(rtPage.getDeviceType())) {
					Boolean flag = false;
					if (rs.size() > j + 1) {
						Hyperlink hyperlink2 = (Hyperlink) rs.get(j + 1);
						P p2 = (P) hyperlink2.getParent();
						Tc tc2 = (Tc) p2.getParent();
						List<Object> rsTc = tr.getContent();
						for (int i = 0; i < rsTc.size(); i++) {
							Tc tcn =  (Tc) ((JAXBElement<?>)rsTc.get(i)).getValue();
							System.out.println(tcn==tc);
							System.out.println(i+"----"+rsTc.size());
							if(tcn==tc&&i<rsTc.size()-1) {

								//倒数第二列，结论

								name = RtPath.getPropetyValue("inspect_record", "record")+"__";
								break;
							}/*else if(tcn==tc&&i==(rsTc.size()-2)) {
								
							//倒数第二列，结论
								
								name = RtPath.getPropetyValue("inspect_conclusion", "conclusion")+"__";
								break;
							}*/else if(tcn==tc) {
								//最后一列，备注
								/*flag = true;
								break;*/
								name = RtPath.getPropetyValue("inspect_conclusion", "conclusion")+"__";
								break;
							}
						}
						
					}
					if(!flag) {
						System.out.println("--------------" + name + "-------" + numId);
						id = name + numId;
						inputType = "select";
					}else {
						id = null;
					}


					
				}else {
					//原始记录
					if (rs.size() > j + 1) {
						Hyperlink hyperlink2 = (Hyperlink) rs.get(j + 1);
						P p2 = (P) hyperlink2.getParent();
						Tc tc2 = (Tc) p2.getParent();
						Tr tr2 = (Tr) tc2.getParent();
						if (!tr.equals(tr2)) {
							List<Object> rsTc = tr.getContent();
							Boolean flag = false;
							for (int i = 0; i < rsTc.size(); i++) {
								Tc tcn =  (Tc) ((JAXBElement<?>)rsTc.get(i)).getValue();
								if(tcn==tc&&i<rsTc.size()-1) {
									flag = true;
									name = RtPath.getPropetyValue("inspect_record", "record")+"__";
									break;
								}
							}
							
							if(!flag) {
								name = RtPath.getPropetyValue("inspect_conclusion", "conclusion")+"__";
							}
							
						}
						
					} else {
						name = RtPath.getPropetyValue("inspect_conclusion", "conclusion")+"__";
					}
		
					System.out.println("--------------" + name + "-------" + numId);
					id = name + numId;
		
					inputType = "select";
				}
			
			}
		}

		if (id == null) {
			//模板自定义起始序号时，起始序号取自模板
			if(this.count.getCount()==0&&rtPage.getFirstNum()!=null){
				this.count.setCount(rtPage.getFirstNum());
			}
			this.count.add1();
			id = this.count.id(RtPageType.TABLE);
		}
		val = HtmlUtils.decode(val);
		String linkText = val.replace(BlANKTAG, "${id:" + id + "}");
		RtHyperlink.setText(hyperlink, linkText);
		List<Object> hyperLinkList = Docx4jUtil.getAllElementFromObject(p, Hyperlink.class);

		String tag = "$" + autoConfigP(id, ubr, val, null, tc, hyperLinkList.size(), inputType, baseConfig,rtPage);
		documentPart.getRelationshipsPart().getRelationshipByID(hyperlink.getId()).setTarget(tag);
	}

	public String autoConfigP(String code, UnitBlankRound ubr, String wtValue, String name, int inputSize, String inputType, BaseConfig baseConfig, RtPage rtPage) throws Exception {
		RtEntity entity = new RtEntity();
		entity.setId(code);
		if (name != null) {
			entity.setName(name);
		} else {
			entity.setName(code);
		}
		LinkedHashMap<String, String> ligerui = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> attribute = new LinkedHashMap<String, String>();

		entity.setType(InputType.text.toString());
		ligerui.put("width", customWidth + "");

		if (ubr.ruleIns == null) {
			ubr.initRuleIns();
		}
		wtValue = wtValue.replace(BlANKTAG, "").trim();
		if (ubr.ruleIns.contains(wtValue)) {
			ligerui.put("suffix", wtValue);
		}

		entity.setLigerui(ligerui);
		entity.setAttr(attribute);
		String json = Utils.toJsonString(entity);
		return URLEncoder.encode(json, "UTF-8");
	}

	public String autoConfigP(String code, UnitBlankRound ubr, String wtValue, String name, Tc tc, int inputSize, String inputType, BaseConfig baseConfig, RtPage rtPage) throws Exception {
		RtEntity entity = new RtEntity();
		entity.setId(code);
		String deviceType = rtPage.getDeviceType();
		
		if (name != null) {
			entity.setName(name);
		} else {
			entity.setName(code);
		}
		LinkedHashMap<String, String> ligerui = new LinkedHashMap<String, String>();
		LinkedHashMap<String, String> attribute = new LinkedHashMap<String, String>();
		// 取到基础信息字段配置则用配置里面的类型，没取到则默认text
		if (inputType == null || StringUtil.isEmpty(inputType) || "text".equals(inputType)) {
			entity.setType(InputType.text.toString());// 默认为TEXTINPUT
			if (baseConfig != null && !StringUtil.isEmpty(baseConfig.getDefaultValue())) {
				entity.setDefaultValue(baseConfig.getDefaultValue());
			}
		} else {
			
			entity.setType(inputType);

			if ("select".equals(inputType) || !("print".equals(inputType) && (code.contains("inspect_op") || code.contains("audit_op")))) {
				// 下拉框可填写
				ligerui.put("isTextBoxMode", "true");
			}
			if ("select".equals(inputType) || ("print".equals(inputType) && code.contains("inspect_op"))) {
				// 检验人员可以多选
				ligerui.put("isMultiSelect", "true");
			}
			if (baseConfig != null && !StringUtil.isEmpty(baseConfig.getDefaultValue())) {
				ligerui.put("initValue", baseConfig.getDefaultValue());
			}

			// 检验项目及其内容处理
			// 检验结论
			if (code.contains(RtPath.getPropetyValue("inspect_conclusion", "conclusion")+"__")) {
				String concl = Factory.getSysPara().getProperty("khrtbox-conclusion");
				if (StringUtil.isNotEmpty(concl)) {
					ligerui.put("data", concl);

				} else {
					if("1".equals(rtPage.getModelType())) {
						//原始记录
						
						//使用码表，为了好调整选择项
						if("3000".equals(deviceType)) {
							//电梯
							ligerui.put("code","'"+ RtField.dtjlConclusionCode+"'");
						}else if("4000".equals(deviceType)) {
							//起重机
							ligerui.put("code", "'"+RtField.qzjlConclusionCode+"'");
						}else if("5000".equals(deviceType)) {
							//厂车
							ligerui.put("code", "'"+RtField.ccjlConclusionCode+"'");
						}else {
							ligerui.put("data", RtField.jlconclusion);
						}
						
						
						
						ligerui.put("initValue", "合格");
					}else {
						//报告
						//使用码表，为了好调整选择项
						if("3000".equals(deviceType)) {
							//电梯
							ligerui.put("code", "'"+RtField.dtbgConclusionCode+"'");
							ligerui.put("data", "<u:dict code='" +RtField.dtbgConclusionCode + "'/>");
						}else if("4000".equals(deviceType)) {
							//起重机
							ligerui.put("code", "'"+RtField.qzbgConclusionCode+"'");
							ligerui.put("data", "<u:dict code='" +RtField.qzbgConclusionCode + "'/>");
						}else if("5000".equals(deviceType)) {
							//厂车
							ligerui.put("code", "'"+RtField.ccbgConclusionCode+"'");
							ligerui.put("data", "<u:dict code='" +RtField.ccbgConclusionCode + "'/>");
						}else {

							ligerui.put("data", "'"+RtField.conclusion);
						}
						ligerui.put("initValue", "合格");
					}
				}
				ligerui.put("isMultiSelect", "false");
				ligerui.put("isTextBoxMode", "false");
				
			}
			// 检验结果
			if (code.contains(RtPath.getPropetyValue("inspect_record", "record")+"__")) {
				String concl = Factory.getSysPara().getProperty("khrtbox-record");
				if (StringUtil.isNotEmpty(concl)) {
					ligerui.put("data", concl);
					// ligerui.put("onSelected","changeRecord");

				} else {
					if("1".equals(rtPage.getModelType())) {
						//原始记录
						//使用码表，为了好调整选择项
						if("3000".equals(deviceType)) {
							//电梯
							ligerui.put("code", "'"+RtField.dtjlRecordCode+"'");
						}else if("4000".equals(deviceType)) {
							//起重机
							ligerui.put("code","'"+ RtField.qzjlRecordCode+"'");
						}else if("5000".equals(deviceType)) {
							//厂车
							ligerui.put("code", "'"+RtField.ccjlRecordCode+"'");
						}else {

							ligerui.put("data", RtField.jlrecord);
						}
						
						
						ligerui.put("initValue", "符合");
					}else {
						//报告 
						//使用码表，为了好调整选择项
						if("3000".equals(deviceType)) {
							//电梯
							ligerui.put("code", "'"+RtField.dtbgRecordCode+"'");
							ligerui.put("data", "<u:dict code='" +RtField.dtbgRecordCode + "'/>");
						}else if("4000".equals(deviceType)) {
							//起重机
							ligerui.put("code", "'"+RtField.qzbgRecordCode+"'");
							ligerui.put("data", "<u:dict code='" +RtField.qzbgRecordCode + "'/>");
						}else if("5000".equals(deviceType)) {
							//厂车
							ligerui.put("data", "<u:dict code='" +RtField.ccbgRecordCode + "'/>");
							ligerui.put("code", "'"+RtField.ccbgRecordCode+"'");
						}else {

							ligerui.put("data", RtField.record);
						}
						
						ligerui.put("initValue", "符合");
					}
					
					// ligerui.put("onSelected","changeRecord");
				}
				ligerui.put("isMultiSelect", "false");
				ligerui.put("isTextBoxMode", "true");
				
			}

			/*---------------------------*************在此处理特殊input类型的修改************************************ */
			/*
			 * 使用时是如此取的 String sql = entity.getSql(); String code =
			 * entity.getCode();// 码表名称 boolean tree = entity.getTree();
			 * 根据此代码来此给entity设置对应值
			 */

			/*
			 * if(code.contains("inspect_op")){
			 * //entity.setSql(baseConfig.getDataSql());
			 * entity.setSql("select u.id,u.name from sys_user u, " +
			 * "tzsb_inspection_info info where info.check_op_id like '%'||u.id||'%'  "
			 * + "and info.id='${param.info_id}'"); entity.setTree(false); }
			 */
			/*------------------------***********************************************************/
		}

		if (baseConfig != null) {
			if("1".equals(rtPage.getModelType())) {
				//检验原始记录 纯html
				if ("string".equals(baseConfig.getBindType())) {
					ligerui.put("data", baseConfig.getDataString());
				} else if ("code".equals(baseConfig.getBindType())) {
					// entity.setCode(baseConfig.getDataCode());

					ligerui.put("code",  "'"+baseConfig.getDataCode()+"'");
					ligerui.put("data", BaseUtil.getDataByCode(baseConfig.getDataCode()));
				}
			}else {
				//检验报告
				if ("string".equals(baseConfig.getBindType())) {
					ligerui.put("data", baseConfig.getDataString());
				} else if ("code".equals(baseConfig.getBindType())) {
					// entity.setCode(baseConfig.getDataCode());
					ligerui.put("code",  "'"+baseConfig.getDataCode()+"'");
					ligerui.put("data", "<u:dict code='" + baseConfig.getDataCode() + "'/>");
				} 
				//by pingZhou 因为后期统一处理，不在模板里面处理
				
				/*else if ("sql".equals(baseConfig.getBindType())) {
					// entity.setSql(baseConfig.getDataSql());
					ligerui.put("data", "<u:dict sql= '<%=" + baseConfig.getCode() + baseConfig.getId() + "%>'/>");
				}*/
				
				
				//if(baseConfig.getCode())
				
				
			}
			
		}

		if (tc != null && tc.getTcPr() != null && tc.getTcPr().getTcW() != null && tc.getTcPr().getTcW().getW() != null) {
			String tcW = tc.getTcPr().getTcW().getW().toString();
			int size = Integer.parseInt(tcW);
			int px = SizeConverter.wordWidthToPx(size) - 10;
			String content = Docx4jUtil.getElementContent(tc);
			content = content.replaceAll("\\$\\{.*\\}", "");
			if (content != null && content.trim().length() > 0) {
				px = px - content.trim().length() * 16;// 16约为1个字符占据的像素
			}
			px = px / inputSize;
			ligerui.put("width", px + "");
		} else {
			ligerui.put("width", customWidth + "");
		}

		if (ubr.ruleIns == null) {
			ubr.initRuleIns();
		}
		wtValue = wtValue.replace(BlANKTAG, "").trim();
		if (ubr.ruleIns.contains(wtValue)) {
			ligerui.put("suffix", wtValue);
		}

		entity.setLigerui(ligerui);
		entity.setAttr(attribute);
		String json = Utils.toJsonString(entity);
		return URLEncoder.encode(json, "UTF-8");
	}

	public BaseConfig getConfigByName(List<BaseConfig> list, String name, RtPage rtPage) {
		if (name == null||name.contains("${id:TBL")||StringUtil.isEmpty(name)) {
			return null;
		}
		name = name.trim().replace(" ", "");
		name = name.replace(":", "").replace("：", "");
		BaseConfig baseConfig = null;
		List<BaseConfig> fitBaseConfigs1 = new ArrayList<BaseConfig>();
		List<BaseConfig> fitBaseConfigs12 = new ArrayList<BaseConfig>();
		List<BaseConfig> fitBaseConfigs2 = new ArrayList<BaseConfig>();
		List<BaseConfig> fitBaseConfigs22 = new ArrayList<BaseConfig>();
		// baseConfig先找中文名称完全匹配的
		for (int i = 0; i < list.size(); i++) {
			//完全匹配
			if (list.get(i).getName().equals(name)) {
				fitBaseConfigs1.add(list.get(i));
			}
			//包含匹配
			//&&!"检测".equals(name)&&!"检验".equals(name)&&!"审核".equals(name)&&!"签发".equals(name)&&!"编制".equals(name)
			if (name.contains((list.get(i).getName()))) {
				fitBaseConfigs2.add(list.get(i));
			}
		}
		if (fitBaseConfigs1.size() == 1) {
			baseConfig = fitBaseConfigs1.get(0);
		} else if (fitBaseConfigs1.size() > 1) {
			for (BaseConfig bc : fitBaseConfigs1) {
				if (rtPage.getDeviceType().equals(bc.getDeviceType())) {
					fitBaseConfigs12.add(bc);
				}
			}
			if (fitBaseConfigs12.size() == 1) {
				baseConfig = fitBaseConfigs12.get(0);
			} else if (fitBaseConfigs12.size() > 1) {
				for (BaseConfig bc : fitBaseConfigs12) {
					if (rtPage.getReportType().equals(bc.getReportType())) {
						baseConfig = bc;
					}
				}
			}
		}
		// baseConfig未取到值再找包含的
		if (baseConfig == null && fitBaseConfigs2.size() > 0) {
			if (fitBaseConfigs2.size() == 1) {
				baseConfig = fitBaseConfigs2.get(0);
			} else if (fitBaseConfigs2.size() > 1) {
				/*
				 * for(BaseConfig baseConfig3 : fitBaseConfigs2){
				 * if(rtPage.getDeviceType
				 * ().equals(baseConfig3.getDeviceType())&&
				 * rtPage.getReportType().equals(baseConfig3.getReportType())){
				 * baseConfig = baseConfig3; } }
				 */
				for (BaseConfig bc : fitBaseConfigs2) {
					if (rtPage.getDeviceType().equals(bc.getDeviceType())) {
						fitBaseConfigs22.add(bc);
					}
				}
				if (fitBaseConfigs22.size() == 1) {
					baseConfig = fitBaseConfigs22.get(0);
				} else if (fitBaseConfigs22.size() > 1) {
					for (BaseConfig bc : fitBaseConfigs22) {
						if (rtPage.getReportType().equals(bc.getReportType())) {
							baseConfig = bc;
						}
					}
				}
			}
		}
		return baseConfig;
	}

}
