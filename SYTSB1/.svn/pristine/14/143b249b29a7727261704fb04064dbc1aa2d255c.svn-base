<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.lsts.inspection.bean.ReportItemValue"%>
<%@ page import="com.lsts.report.bean.ReportItem"%>
<%@page import="com.khnt.pub.fileupload.bean.Attachment"%>
<%@page import="com.khnt.pub.fileupload.service.AttachmentManager"%>
<%@page import="com.lsts.inspection.dao.ReportPerDao"%>
<%@page import="com.lsts.common.dao.AttachmentsDao"%>
<%@page import="java.io.IOException"%>
<%@page import="util.TS_Util"%>
<%@ taglib uri="/WEB-INF/Ming.tld" prefix="ming" %>
<%@ page import="com.ming.webreport.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.khnt.utils.StringUtil" %>
<%@ page import="com.khnt.utils.DateUtil"%>
<%@ page import="com.lsts.ImageTool"%>
<%@ page import="com.lsts.inspection.bean.*" %>
<%@ page import="com.lsts.report.bean.*" %>
<%@ page import="oracle.sql.*" %>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<%
	InspectionInfo inspectionInfo = (InspectionInfo)request.getSession().getAttribute("inspectionInfo");
    //InspectionZZJDInfo inspectionZZJDInfo = (InspectionZZJDInfo)request.getSession().getAttribute("inspectionZZJDInfo");
    Report report = (Report)request.getSession().getAttribute("report");
    
    String advance_time = "";
    String last_inspection_date = "";
	String enter_time = "";
	String check_time = "";
	if(inspectionInfo!=null){
		advance_time = inspectionInfo.getAdvance_time()!=null?DateUtil.getDateTime("yyyy-MM-dd",inspectionInfo.getAdvance_time()):"";
		enter_time = inspectionInfo.getEnter_time()!=null?DateUtil.getDateTime("yyyy-MM-dd",inspectionInfo.getEnter_time()):"";
		check_time = inspectionInfo.getExamine_Date()!=null?DateUtil.getDateTime("yyyy-MM-dd",inspectionInfo.getExamine_Date()):"";
	}
    
    String report_item = inspectionInfo.getReport_item();
    String com_name = "";
    String report_sn = inspectionInfo.getReport_sn();
    
    // 构造DataRecord、MRDataSet对象
    MRDataSet mrds = new MRDataSet();
    DataRecord rec = new DataRecord();
    
    Map<String, Object> zzjdInfoMap = (Map<String, Object>) request.getSession().getAttribute("INSPECTIONZZJDINFO");
	for (String key : zzjdInfoMap.keySet()) {
		String zzjdInfo_value = TS_Util.nullToString(zzjdInfoMap.get(key)).replaceAll("\r|\n", "");
		if (("MADE_UNIT_NAME").equals(key)) { // 制造单位
			if (!"".equals(zzjdInfoMap.get(key)) && zzjdInfoMap.get(key) != null) {
				rec.setValue(key, TS_Util.nullToString(zzjdInfo_value));
			} else {
				com_name = inspectionInfo.getReport_com_name();
				rec.setValue("MADE_UNIT_NAME", inspectionInfo.getReport_com_name());
			}
		} else if (("INSTALL_UNIT_NAME").equals(key)) { // 安装单位
			if (!"".equals(zzjdInfoMap.get(key)) && zzjdInfoMap.get(key) != null) {
				rec.setValue(key, TS_Util.nullToString(zzjdInfo_value));
			} else {
				com_name = inspectionInfo.getReport_com_name();
				rec.setValue("INSTALL_UNIT_NAME", inspectionInfo.getReport_com_name());
			}
		} else if (("CONSTRUCTION_UNIT_NAME").equals(key)) { // 施工单位名称
			if (!"".equals(zzjdInfoMap.get(key)) && zzjdInfoMap.get(key) != null) {
				rec.setValue(key, TS_Util.nullToString(zzjdInfo_value));
			} else {
				com_name = inspectionInfo.getReport_com_name();
				rec.setValue("CONSTRUCTION_UNIT_NAME", inspectionInfo.getReport_com_name());
			}
		} else if (("INSPECTION_DATE").equals(key)) { // 检验日期
			if (!"".equals(zzjdInfoMap.get(key)) && zzjdInfoMap.get(key) != null) {
				if (StringUtil.isEmpty(advance_time)) {
					advance_time = TS_Util.nullToString(zzjdInfo_value.split(" ")[0]);
				}
			}
		} else if (("LAST_INSPECTION_DATE").equals(key)) { // 下次检验日期
			if (!"".equals(zzjdInfoMap.get(key)) && zzjdInfoMap.get(key) != null) {
				if (StringUtil.isEmpty(last_inspection_date)) {
					last_inspection_date = TS_Util.nullToString(zzjdInfo_value.split(" ")[0]);
				}
			}
		} else if (key.contains("GLSJWJ_JDQD_")) { // 锅炉设计文件鉴定清单
			if ("GLSJWJ_JDQD_ZTBH".equals(key) || "GLSJWJ_JDQD_BTTBH".equals(key)
					|| "GLSJWJ_JDQD_DLGSBH".equals(key) || "GLSJWJ_JDQD_GTBH".equals(key)
					|| "GLSJWJ_JDQD_GRQBH".equals(key) || "GLSJWJ_JDQD_JWQBH".equals(key)
					|| "GLSJWJ_JDQD_SMQBH".equals(key) || "GLSJWJ_JDQD_ZRQBH".equals(key)
					|| "GLSJWJ_JDQD_RYLXTTBH".equals(key) || "GLSJWJ_JDQD_QDJSHZBBH".equals(key)
					|| "GLSJWJ_JDQD_ZJSGDBH".equals(key) || "GLSJWJ_JDQD_ZZQGDBH".equals(key) 
					|| "GLSJWJ_JDQD_ZRZQLDGDBH".equals(key) || "GLSJWJ_JDQD_ZRZQRDGDBH".equals(key)) {
				String jdqd = TS_Util.nullToString(zzjdInfoMap.get(key));
				if (StringUtil.isNotEmpty(jdqd)) {
					String[] jdqdList = jdqd.split(",");
					if (jdqdList.length > 0) {
						for (int i = 0; i < jdqdList.length; i++) {
							String gzzlmc_key = key + "_" + (i + 1);
							rec.setValue(gzzlmc_key, TS_Util.nullToString(jdqdList[i]));
						}
					}
				}
			}
		} else {
			rec.setValue(key, TS_Util.nullToString(zzjdInfo_value));
		}
	}

	// 报告书编号
	rec.setValue("REPORT_SN", report_sn);

	// 序号
	if (report.getReport_name().contains("锅炉设计文件")) {
		rec.setValue("DEVICE_NO", report_sn.split("-")[1]); // 序号（由报告书编号中的序号部分生成）
	}

	//设置人员姓名
	//rec.setValue("INSPECTION_OP_STR", inspectionInfo.getEnter_op_name());
	//rec.setValue("INSPECTION_AUDIT_STR", inspectionInfo.getExamine_name());
	//rec.setValue("INSPECTION_CONFIRM_STR", inspectionInfo.getIssue_name());

	String newdate = "";
	// 将签发日期按照yyyy年MM月dd日方式显示
	if (inspectionInfo.getIssue_Date() != null) {
		String confirm_date = DateUtil.getDate(inspectionInfo.getIssue_Date());
		if (StringUtil.isNotEmpty(confirm_date)) {
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getIssue_Date());
			rec.setValue("CONFIRM_DATE", newdate);
		}
	}
	// 将审核日期按照yyyy年MM月dd日方式显示
	if (inspectionInfo.getExamine_Date() != null) {
		String examine_date = DateUtil.getDate(inspectionInfo.getExamine_Date());
		if (StringUtil.isNotEmpty(examine_date)) {
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getExamine_Date());
			rec.setValue("AUDIT_DATE", newdate);
		}
	}

	// 将检验日期按照yyyy年MM月dd日方式显示
	if (inspectionInfo.getAdvance_time() != null) {
		String advance_time1 = DateUtil.getDate(inspectionInfo.getAdvance_time());
		if (StringUtil.isNotEmpty(advance_time1)) {
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getAdvance_time());
			rec.setValue("INSPECTION_DATE", newdate);
		}
	}

	// 将检验日期按照yyyy年MM月dd日方式显示
	if (inspectionInfo.getLast_check_time() != null) {
		String advance_time1 = DateUtil.getDate(inspectionInfo.getLast_check_time());
		if (StringUtil.isNotEmpty(advance_time1)) {
			newdate = DateUtil.getDateTime("yyyy年MM月dd日", inspectionInfo.getLast_check_time());
			rec.setValue("LAST_INSPECTION_DATE", newdate);
		}
	} else {
		rec.setValue("LAST_INSPECTION_DATE", "/");
	}

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

	// 加载报检检验项目明细表
	List<ReportItemValue> reportItemValueList = (List) request.getSession().getAttribute("REPORTITEMVALUE");
	for (ReportItemValue riv : reportItemValueList) {
		String item_value = TS_Util.nullToString(riv.getItem_value()).replaceAll("\r|\n", "");	
		if (("REPORT_SN").equals(riv.getItem_name())) { // 报告书编号
			if (StringUtil.isEmpty(report_sn)) {
				report_sn = TS_Util.nullToString(zzjdInfoMap.get(riv.getItem_name()));
				rec.setValue(TS_Util.nullToString(riv.getItem_name()), report_sn);
			}
		} else {
			if(("INSPECTION_DATE").equals(riv.getItem_name())){
				if(StringUtil.isNotEmpty(advance_time)){
					rec.setValue(TS_Util.nullToString(riv.getItem_name()), advance_time);
				}
			}else if(("AUDIT_DATE").equals(riv.getItem_name())){
				if(StringUtil.isNotEmpty(check_time)){
					rec.setValue(TS_Util.nullToString(riv.getItem_name()), check_time);
				}
			}else{
				rec.setValue(TS_Util.nullToString(riv.getItem_name()), TS_Util.nullToString(riv.getItem_value()));
			}
		}
	}

	//设置打印签名不可见，电子签名可见
	rec.setValue("INSPECTION_OP_STR", "");
	rec.setValue("INSPECTION_AUDIT_STR", "");
	rec.setValue("INSPECTION_CONFIRM_STR", "");
	rec.setValue("INSPECTION_ENTER_STR", "");

	// 机构核准证号
	//rec.setValue("JGHZH", "TS7110306-2019");
	rec.setValue("TotalP", StringUtil.isNotEmpty(report_item) ? report_item.split(",").length : 0);
	mrds.addRow(rec);

	// 创建报表引擎对象，指定报表根目录，传递结果集，绑定报表
	MREngine engine = new MREngine(pageContext,
			StringUtil.isNotEmpty(report.getRootpath()) ? report.getRootpath() : "");
	engine.setUnicodeOption(1);
	// 用MRDataSet对象为报表提供数据集：
	engine.addMRDataSet(StringUtil.isNotEmpty(report.getMrdataset()) ? report.getMrdataset() : "", mrds);
	engine.addReport(StringUtil.isNotEmpty(report.getReport_file()) ? report.getReport_file() : "");//报表文件
	engine.bind();
%>
<html>
<head>
<title> 报表信息 </title>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<%@include file="/k/kui-base-list.jsp"%>
<script language="javascript">
	var com_name = "<%=com_name%>";
	
	$(function() {
		var height = $(window).height()-$('.toolbar-tm').height();
		$("#scroll-tm").css({height:height});
		setReports();
	})
	
	//设置报表属性
	function setReports(){
		//MRViewer.ShowToolbar=false;
		report = MRViewer.Report;
		pagecount = MRViewer.PageCount;
		
		//设置显示页
		var ss = "<%=report_item%>".split(",");
		var ss_length = ss.length;
		var status = false;
		for(var i=0;i<pagecount;i++){
			for(var j=0;j<ss_length;j++){
				if((i+1)==ss[j]){
					status = true;
					page = report.Pages(i);
					page.Prop("Visible") = "True";
					break;
				}else{
					try{
						page = report.Pages(i);
						page.Prop("Visible") = "False";
					} catch(e){}
				}
			}
		}

		//alert(ss_length+"-----"+(ss_length%2)+"-----"+(ss_length%2==1))
		//如果为基数页的话，自动在页后添加空白页
		if(ss_length%2==1){
			//alert();
			//report.AddPage();
		}
		if(!status){
			alert("报告模板上没有对应的索引！");
			api.close();	// window.close();
		}
	
		parseValueReports();
		MRViewer.Preview();
	}
	
	function parseValueReports(){
		//把每一个是Memo的对象中间包含“”的并且不是总页数的控件对象都设置为可以提交的状态,提交KEY = memo中的字段名
		report = MRViewer.Report;
		pagecount = MRViewer.PageCount;
		var ss = '${REPORTPARA.report_item}'.split(",");
		for(var i=0;i<pagecount;i++){
			try{
				page = report.Pages(i);
				objCount = page.ObjectCount;
				for(var j=0;j<objCount;j++){
					obj = page.Objects(j); 
					if(obj.Prop("memo").indexOf("[BGDS.\"COM_NAME\"]")!=-1){
						if(com_name.indexOf("尊宴餐饮娱乐有限公司")!=-1){
							obj.Prop("Memo") = "成都珺龍尊宴餐饮娱乐有限公司";
						}else{
							obj.Prop("Memo") = com_name;
						}
					}
				}
			} catch(e){}
		}
	}
	
	function save(){
		var type = '${param.type}';
		var isOp = parent.left._opid${param.opid}.innerHTML;
		if(type == '04'){
			if(isOp!=""){
				alert("亲，这份报告已审核，不需要再审核了哦！");
				return;
			}
		}else{
			if(isOp!=""){
				alert("亲，这份报告已签发，不需要再签发了哦！");
				return;
			}
		}
		
		var data = new Object();
		data.acc_id = '${param.activity_id}';
		data.flow_num = '${param.flow_note_id}';
		data.type = type;
		data.device_type = '${param.device_type}';
		data.ids = '${param.id}';
		data.opid = '${param.opid}';
		data.advance_time = '<%=advance_time%>';
		data.check_time = '<%=check_time%>';
		data.enter_time = '<%=enter_time%>';
		data.op_type = '单份'; 	// 1：操作方式
		data.isBatch = '0';		// 0：单份操作
		parent.save(data);
	}
	
	function showBB(){
		$("#sssss").show();
	}
	
	$(window).load(function() {
		$("#MRViewer").append('<param name="wmode" value="transparent" />');
	});
</script>
</head>
<body>
<form name="formObj" method="post" action="">
</form>
<div class="scroll-tm" style="overflow: hidden" id="sssss"> 
	<ming:MRViewer id="MRViewer" shownow="true"  width="100%"   height="100%"
	 simple="false" 
	 invisiblebuttons="Print,Export,Close,PrintPopup,ExportPopup,Find,FindNext" 
	 postbackurl=""  canedit="false"   wrapparams="true"  />
</div>
</body>
</html>