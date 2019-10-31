<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.lsts.inspection.bean.ReportItemValue"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="/WEB-INF/Ming.tld" prefix="ming"%>
<%@ page import="com.ming.webreport.*"%>
<%@ page import="java.util.*"%>
<%@ page import="util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head pageStatus="add">
		<title>监督检验报告录入</title>
		<%
			MRDataSet mrds = new MRDataSet();
			DataRecord rec = new DataRecord();

			String inspection_date = ""; 		// 检验日期
			String last_inspection_date = ""; // 下次检验日期
			String com_name = "";
			String check_op_name = "";  // 参检人员
			String report_sn = "";		// 报告书编号

			// 1、加载业务信息表数据
			Map<String, Object> infoMap = (Map<String, Object>) request
					.getAttribute("INSPECTIONINFO");
			for (String key : infoMap.keySet()) {
				if (("REPORT_COM_NAME").equals(key)) { // 制造单位
					rec.setValue("MADE_UNIT_NAME", TS_Util.nullToString(infoMap
							.get(key)));
					com_name = TS_Util.nullToString(infoMap.get(key));
				} else if (("CHECK_OP_NAME").equals(key)) { // 检验员
					check_op_name = TS_Util.nullToString(infoMap.get(key));
					if (check_op_name.indexOf(",") != -1) {
						check_op_name = check_op_name.replaceAll(",", " ");
					}
				} else if (("ADVANCE_TIME").equals(key)) { // 检验日期
					if (!infoMap.get(key).equals("")
							&& infoMap.get(key) != null) {
						inspection_date = TS_Util.nullToString(infoMap.get(key)
								.toString().split(" ")[0]);
					}
				} else if (("REPORT_SN").equals(key)) { // 报告书编号
					report_sn = TS_Util.nullToString(infoMap.get(key));
					rec.setValue(key, report_sn);
				} else {
					rec.setValue(key, TS_Util.nullToString(infoMap.get(key)));
				}
			}

			// 2、加载监督检验明细数据
			Map<String, Object> zzjdInfoMap = (Map<String, Object>) request
					.getAttribute("INSPECTIONZZJDINFO");
			for (String key : zzjdInfoMap.keySet()) {
				if (("DESIGN_DATE").equals(key)) { // 设计日期
					if (!"".equals(zzjdInfoMap.get(key))
							&& zzjdInfoMap.get(key) != null) {
						rec.setValue(key, TS_Util.nullToString(zzjdInfoMap.get(
								key).toString()));
					}
				} else if (("MADE_DATE").equals(key)) { // 制造日期
					if (!"".equals(zzjdInfoMap.get(key))
							&& zzjdInfoMap.get(key) != null) {
						rec.setValue(key, TS_Util.nullToString(zzjdInfoMap.get(
								key).toString()));
					}
				} else if (("INSPECTION_DATE").equals(key)) { // 检验日期
					if (!"".equals(zzjdInfoMap.get(key))
							&& zzjdInfoMap.get(key) != null) {
						if(StringUtil.isEmpty(inspection_date)){
							inspection_date = TS_Util.nullToString(zzjdInfoMap.get(
									key).toString().split(" ")[0]);
						}
					}
				} else if (("LAST_INSPECTION_DATE").equals(key)) { // 下次检验日期
					if (!"".equals(zzjdInfoMap.get(key))
							&& zzjdInfoMap.get(key) != null) {
						if(StringUtil.isEmpty(last_inspection_date)){
							last_inspection_date = TS_Util.nullToString(zzjdInfoMap.get(
									key).toString().split(" ")[0]);
						}
					}
				}else if (key.contains("GLSJWJ_JDQD_")) { // 锅炉设计文件鉴定清单
					if("GLSJWJ_JDQD_ZTBH".equals(key) || "GLSJWJ_JDQD_BTTBH".equals(key) ||
							"GLSJWJ_JDQD_DLGSBH".equals(key) || "GLSJWJ_JDQD_GTBH".equals(key) ||
							"GLSJWJ_JDQD_GRQBH".equals(key) || "GLSJWJ_JDQD_JWQBH".equals(key) ||
							"GLSJWJ_JDQD_SMQBH".equals(key) || "GLSJWJ_JDQD_ZRQBH".equals(key) ||
							"GLSJWJ_JDQD_RYLXTTBH".equals(key) || "GLSJWJ_JDQD_QDJSHZBBH".equals(key) ||
							"GLSJWJ_JDQD_ZJSGDBH".equals(key) || "GLSJWJ_JDQD_ZZQGDBH".equals(key) ||
							"GLSJWJ_JDQD_ZRZQLDGDBH".equals(key) || "GLSJWJ_JDQD_ZRZQRDGDBH".equals(key)){
						String jdqd = TS_Util.nullToString(zzjdInfoMap.get(key));
						if (StringUtil.isNotEmpty(jdqd)) {
							String[] jdqdList = jdqd.split(",");
							if(jdqdList.length>0){
								for(int i=0;i<jdqdList.length;i++){
									String gzzlmc_key = key+"_"+(i+1);
									rec.setValue(gzzlmc_key, TS_Util.nullToString(jdqdList[i]));
								}
							}						
						}
					}
				} else if (("COM_NAME").equals(key)) { // 使用单位
					rec.setValue("COM_NAME", TS_Util
							.nullToString(zzjdInfoMap.get(key)));
					com_name = TS_Util
							.nullToString(zzjdInfoMap.get(key));
				} else if (("REPORT_SN").equals(key)) { // 报告书编号
					if(StringUtil.isEmpty(report_sn)){
						report_sn = TS_Util.nullToString(zzjdInfoMap.get(key));
						rec.setValue(key, report_sn);
					}
				} else {
					rec.setValue(key, TS_Util
							.nullToString(zzjdInfoMap.get(key)));
				}
			}

			// 3、加载报检检验项目明细表
			List<ReportItemValue> reportItemValueList = (List) request
					.getAttribute("REPORTITEMVALUE");
			for (ReportItemValue riv : reportItemValueList) {
				if (("REPORT_SN").equals(riv.getItem_name())) { // 报告书编号
					if (StringUtil.isEmpty(report_sn)) {
						report_sn = TS_Util.nullToString(zzjdInfoMap.get(riv.getItem_name()));
						rec.setValue(TS_Util.nullToString(riv.getItem_name()), report_sn);
					}
				} else {
					rec.setValue(TS_Util.nullToString(riv.getItem_name()), TS_Util.nullToString(riv.getItem_value()));
				}
			}

			// 4、加载报告基本信息表数据
			Map<String, Object> reportMap = (Map<String, Object>) request.getAttribute("REPORT");
			for (String key : reportMap.keySet()) {
				if (("REPORT_NAME").equals(key)) { // 报告名称
					String report_name = TS_Util.nullToString(reportMap.get(key));
					if (report_name.contains("锅炉设计文件")) {
						rec.setValue("DEVICE_NO", report_sn.split("-")[1]); // 序号（由报告书编号中的序号部分生成）
					}
				}
			}

			rec.setValue("INSPECTION_OP_STR", check_op_name); // 检验员
			rec.setValue("INSPECTION_DATE", inspection_date); // 检验日期
			rec.setValue("LAST_INSPECTION_DATE", last_inspection_date); // 下次检验日期

			// 显示电子签名
			Map<String, Object> imgMap = (Map<String, Object>) request.getAttribute("IMAGES");

			byte[] check_op_img = (byte[]) imgMap.get("check_op_img");
			byte[] examine_op_img = (byte[]) imgMap.get("examine_op_img");
			byte[] issue_op_img = (byte[]) imgMap.get("issue_op_img");
			byte[] enter_op_img = (byte[]) imgMap.get("enter_op_img");

			//设置打印签名不可见，电子签名可见
			rec.setValue("INSPECTION_OP_STR", "");
			rec.setValue("INSPECTION_AUDIT_STR", "");
			rec.setValue("INSPECTION_CONFIRM_STR", "");
			rec.setValue("INSPECTION_ENTER_STR", "");

			//检验员电子签名
			rec.setValue("INSPECTION_OP_PICTURE", check_op_img != null ? check_op_img : "");
			//审核电子签名
			rec.setValue("INSPECTION_AUDIT_PICTURE", examine_op_img != null ? examine_op_img : "");
			//签发(批准)电子签名
			rec.setValue("INSPECTION_CONFIRM_PICTURE", issue_op_img != null ? issue_op_img : "");
			//编制电子签名
			rec.setValue("INSPECTION_ENTER_PICTURE", enter_op_img != null ? enter_op_img : "");

			// 4、加载报告基本信息和参数
			Map<String, Object> paraMap = (Map<String, Object>) request.getAttribute("REPORTPARA");
			// 报表参数设置
			rec.setValue("TotalP", paraMap.get("TotalP"));
			//rec.setValue("JGHZH", "TS7110306-2019");
			mrds.addRow(rec);

			MREngine engine = new MREngine(pageContext);
			engine.setRootPath((String) paraMap.get("report_root_path")); // 报告模板所在目录
			engine.addReport((String) paraMap.get("report_file")); // 模板文件名
			engine.addMRDataSet((String) paraMap.get("MRDataSet"), mrds); // 数据集
			engine.bind();
		%>
		<%@ include file="/k/kui-base-form.jsp"%>
		<%@ include file="report_zzjd.js.jsp"%>

		<script language="javascript">
			var com_name = "<%=com_name%>";
			$(function() {
		 		manager = $("#btn").ligerButton({
		           click: function (){}
		    	}
			);
		
			var height = $(window).height()-$('.toolbar-tm').height();
			$("#scroll-tm").css({height:height});
			setReports();
		
			$('#save').click(function(){
				$("#save").attr("disabled","disabled");
				$("#sub").attr("disabled","disabled");
				doSave();
				var infoId = "${infoId}";
				var reportId ="${report_type}";
				var flag = "${param.isSub}";
				if(flag=="no"){
					api.data.pwindow.api.data.window.saveReport(infoId,reportId);
					api.data.pwindow.api.close();
					api.close();
				}else{
					api.data.pwindow.saveReport(infoId,reportId);
					api.close();
				}
			});
		
			$('#sub').click(function(){
				$("#sub").attr("disabled","disabled");
				$("#save").attr("disabled","disabled");
				doSave();
				var flag = "${param.isSub}";
				if(flag=="no"){
					api.data.pwindow.api.data.window.refreshGrid();
					api.data.pwindow.api.close();
					api.close();
				}else{
					api.data.window.refreshGrid();
					api.close();
				}
			});
		
			$('#cancel').click(function(){			
				var flag = "${param.isSub}";
				if(flag=="no"){
					api.data.pwindow.api.close();
					api.data.pwindow.api.data.window.refreshGrid();
					api.close();
					allert("a");
				}else{
					api.data.window.refreshGrid();
					api.close();
					allert("b");
				}
			});
		})
	
		//保存开始禁用按钮
		function disableButton(){
			//$.ligerDialog.alert(111);
		}
		//保存结束按钮可用
		function enableButton(){
			//$.ligerDialog.alert(222);
		}
	</script>
	</head>
	<body>
		<div id="scroll-tm" style="overflow: hidden">
			<ming:MRViewer id="MRViewer" shownow="true" width="100%"
				height="100%" simple="false" canedit="true"
				invisiblebuttons="Close,PrintPopup,ExportPopup,Find,FindNext"
				postbackurl="" wrapparams="true" />
		</div>
		<div class="toolbar-tm">
			<div class="toolbar-tm-bottom" style="text-align: center;">
				<a id="sub" class="l-button-warp l-button" ligeruiid="Button1012">
					<span class="l-button-main l-button-hasicon"> <span
						class="l-button-text">保存并关闭</span> <span
						class="l-button-icon l-icon-search"></span> </span> </a>
				<a id="save" class="l-button-warp l-button" ligeruiid="Button1012">
					<span class="l-button-main l-button-hasicon"> <span
						class="l-button-text">保存</span> <span
						class="l-button-icon l-icon-save"></span> </span> </a>

				<a id="cancel" class="l-button-warp l-button" ligeruiid="Button1012">
					<span class="l-button-main l-button-hasicon"> <span
						class="l-button-text">关闭</span> <span
						class="l-button-icon l-icon-cancel"></span> </span> </a>
			</div>
		</div>
	</body>
</html>