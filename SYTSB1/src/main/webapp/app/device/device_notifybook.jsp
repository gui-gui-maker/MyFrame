<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/WEB-INF/Ming.tld" prefix="ming" %>
<%@ page import="com.ming.webreport.*"%>
<%@ page import="com.khnt.foundation.*,cfd.pub.*"%>
<%@ page import="java.io.File" %>
<%@ page import="java.util.*" %>

<%  
	
	CfdUser user = (CfdUser) session.getAttribute(CfdUser.NAME);
	String deviceId = request.getParameter("deviceId");	
	//取得所选设备的单位信息
	Entity comEn = new Entity();
	String comsql = "select t.*, t.rowid from tsjc_company_info t where t.id in (select t1.fk_company_info_use_id from tsjc_device_document t1 where t1.id in ("+deviceId+"))";
	comEn.excuteQuery(comsql);

	if(comEn.getResultCount()!=0&&comEn.getResultCount() > 1)
    {
		%>
		<script language="javascript">
		<!--
			alert("请注意，选择设备不全部是同一单位");
			//
			//window.close();
		//-->
		</script>
		<%
    }
	//取得设备信息
	Entity deviceEn = new Entity();
	String deviceSql = "	select t.ID,t.DEVICE_REGISTRATION_CODE,t.FACTORY_CODE,t.REGISTRATION_NUM,t.DEVICE_AREA_CODE,T.DEVICE_SORT_CODE,T6.REGIONAL_NAME,T.SECURITY_OP,T.SECURITY_TEL,	"+
	"    T6.REGIONAL_CODE," +
	"	 decode(t_b.boiler_model, '', t_s.equipment_model, t_b.boiler_model) DEVICE_MODEL,	"+
	"	 decode(t5.container_name, '', t.device_name, t5.container_name) as DEVICE_NAME	"+
	"	  from tsjc_device_document          t,	"+
	"	       tsjc_device_boiler            t_b,	"+
	"	       tsjc_device_pressurevessels   t5,	"+
	"	       tsjc_administrative_divisions t6,	"+
	"	       tsjc_device_special           t_s	"+
	"	  where t.id = t_b.fk_tsjc_device_document_id(+)	"+
	"	  and t.id = t5.fk_tsjc_device_document_id(+)	"+
	"	  and t.id = t_s.fk_tsjc_device_document_id(+)	"+
	"	  and t.device_area_code = t6.id	"+
	"	  and t.id in ("+deviceId+")";
	deviceEn.excuteQuery(deviceSql);
	//设备记录数
	int deviceCount = deviceEn.getResultCount();
	
	//循环判断所选设备是否在属于同一个科室以及是否在同一区域
	String unit = "";
	String last_area_code = "";
	String regional_name = "" ;
	/*************** 2011-9-27  定义区域代码 *****************/
	String regional_code = deviceEn.getStringValue("regional_code",0);
	/*************** 2011-9-27  定义区域代码  end*****************/
	int unitCount = 0 ;
	boolean not_same_area = false ;
	for(int i = 0 ; i < deviceEn.getResultCount() ; i++){
		String device_sort_code = deviceEn.getStringValue("device_sort_code",i);
		String device_area_code = deviceEn.getStringValue("device_sort_code",i);
		regional_name = deviceEn.getStringValue("regional_name",i);
		if(!last_area_code.equals("") && !last_area_code.equals(device_area_code))
			not_same_area = true ;
		Entity sortEn = new Entity();
		String sortSql = "select t.main_code from tsjc_device_categories t where t.device_code = '"+device_sort_code+"'";
		sortEn.excuteQuery(sortSql);
		String main_code = sortEn.getStringValue("main_code",0);
		
		String unit_flag = "";
		if(main_code.equals("1000")||main_code.equals("2000") || main_code.equals("8000")){
			unit_flag = "1" ;
		} else if(main_code.equals("3000") || main_code.equals("4000") || main_code.equals("5000") || main_code.equals("6000")){
			unit_flag = "5" ;
		}
		
		if(!main_code.equals("3000")){
			Entity unitEn = new Entity();
			String unitSql = "select t.*, t.rowid from tsjy_uint_area_rela t where t.area_code='"+deviceEn.getStringValue("device_area_code",i)+"' and t.flag='"+unit_flag+"'";
			unitEn.excuteQuery(unitSql);
			unitCount = unitEn.getResultCount();
			if(unitEn.getResultCount() > 0 ) {
				unit = unitEn.getStringValue("fk_unit_id",0);
			}
		} else {
			Entity statusEn = new Entity();
			String statusSql = " select t5.unit_id "+
			"   from tsjc_warning_status t, "+
			"    tsjc_warning_deal   t1, "+
			"    employee            t2, "+
			"    euser               t3, "+
			"   employee_position   t4, "+
			"   position            t5 "+
			" where t.id = t1.fk_tsjc_warning_status_id "+
			"   and t.deal_time = t1.deal_time "+
			"   and t1.deal_man = t2.name "+
			"   and t2.id = t3.employee_id "+
			"   and t2.id = t4.employee_id "+
			"   and t4.position_id = t5.id "+
			"   and t.fk_tsjc_device_document_id = '"+deviceEn.getStringValue("id",i)+"'";
			statusEn.excuteQuery(statusSql);
			if(statusEn.getResultCount()>0)
				unit = statusEn.getStringValue("unit_id",0);
			System.out.println("qqqqqqqqqqqqq"+unit);
		}
	}
	if(not_same_area)
    {
		%>
		<script language="javascript">
		<!--
			alert("选择设备不在同一区域");
			window.close();
		//-->
		</script>
		<%
    }
	if(unitCount > 1)
    {
		%>
		<script language="javascript">
		<!--
			alert("选择设备不属于同一个科室管理");
			window.close();
		//-->
		</script>
		<%
    }
	//获取流水号
	//String serial_no = JYUtil.getSerialCode(unit);
	String reportfile = "";
	if(deviceCount>1)
		reportfile = "YJSJCHMUL";
	else 
		reportfile = "YJSJCHSIN";
	String web_root = com.khnt.foundation.Resources.getProperty("WEB_ROOT");
	String reportpath = "tsjy/reports";//报表路径
    String pathreportfile = web_root+reportpath+"/"+reportfile+".mrf";
	
	int total_page = 0;

	
	//判读文件是否存在
    File dir = new File(pathreportfile);
    if (!dir.exists())
    {
%>
<script language="javascript">
<!--
	alert("报表模板：<%=reportfile+".mrf"%>不存在");
	window.close();
//-->
</script>
<%
        System.out.println("The   file   is   not   exist");
    }
	else
	{
		
		// 构造DataRecord、MRDataSet对象
		MRDataSet mrds = new MRDataSet();
		DataRecord rec = new DataRecord();
		
		//===================固化部分信息============================
		//rec.setValue("WARNING_SERIAL_NO",serial_no);//报告书编号
		rec.setValue("ROW_COUNT",deviceCount);//设备记录数
		rec.setValue("REGIONAL_NAME",regional_name);
		/*******************  2011-9-27  将区域代码放入报表  *******************/
		rec.setValue("REGIONAL_CODE",regional_code);
		/*******************  2011-9-27  将区域代码放入报表  end *******************/
		rec.setValue("SEND_NAME",user.getName());
		rec.setValue("YEAR",Util.getCurrentDateString("yyyy"));
		rec.setValue("MONTH",Util.getCurrentDateString("MM"));
		rec.setValue("DAY",Util.getCurrentDateString("dd"));
		rec.setValue("YEAR1",Util.getCurrentDateString("yyyy"));
		rec.setValue("MONTH1",Util.getCurrentDateString("MM"));
		rec.setValue("DAY1",Util.getCurrentDateString("dd"));
		
		for (int i = 0; i < deviceEn.getResultCount(); i++){
			for (int j = 0; j <deviceEn.getResult(i).getNumberOfAttribute(); j++) {
				if(deviceEn.getValue(deviceEn.getResult(i).getNameOfAttribute(j), 0) instanceof Date)
					rec.setValue(deviceEn.getResult(i).getNameOfAttribute(j)+(i+1), deviceEn.getFormatStringValue(deviceEn.getResult(i).getNameOfAttribute(j), i,"yyyy-MM-dd"));	
				else
					rec.setValue(deviceEn.getResult(i).getNameOfAttribute(j)+(i+1), deviceEn.getStringValue(deviceEn.getResult(i).getNameOfAttribute(j), i));
			}
		}
		rec.setValue("INSPECTION_OP_PICTURE",cfd.tsjy.BlobData.ImageEncoder(user.getEmployeeId()));
		
		for (int i = 0; i <comEn.getResult(0).getNumberOfAttribute(); i++) {
			if(comEn.getValue(comEn.getResult(0).getNameOfAttribute(i), 0) instanceof Date)
				rec.setValue(comEn.getResult(0).getNameOfAttribute(i), comEn.getFormatStringValue(comEn.getResult(0).getNameOfAttribute(i), 0,"yyyy-MM-dd"));	
		else
			rec.setValue(comEn.getResult(0).getNameOfAttribute(i), comEn.getStringValue(comEn.getResult(0).getNameOfAttribute(i), 0));
		}
		//读取已经保存好的数据
		/*
        NamedEntity report = NamedEntity.newInstance("tsjy_report_item_value");
        report.setValue("fk_report_id", report_type);
        report.setValue("fk_inspection_info_id", request.getParameter("id"));
        report.search(-1);
        for (int i = 0; i < report.getResultCount(); i++)
        {
        	if(report.getStringValue("item_name", i).toUpperCase().indexOf("PICTURETEXT") != -1){
        		rec.setValue(report.getStringValue("item_name", i)+"P",cfd.tsjy.BlobData.getBlobFromDb(report.getStringValue("item_value",i)));
        		rec.setValue(report.getStringValue("item_name", i), report.getStringValue("item_value", i));  
        	} else {
            	rec.setValue(report.getStringValue("item_name", i), report.getStringValue("item_value", i));  
        	}
        }
	for (int i = 0; i <comEn.getResult(0).getNumberOfAttribute(); i++) {
			if(comEn.getValue(comEn.getResult(0).getNameOfAttribute(i), 0) instanceof Date)
				rec.setValue(comEn.getResult(0).getNameOfAttribute(i), comEn.getFormatStringValue(comEn.getResult(0).getNameOfAttribute(i), 0,"yyyy-MM-dd"));	
	else
		rec.setValue(comEn.getResult(0).getNameOfAttribute(i), comEn.getStringValue(comEn.getResult(0).getNameOfAttribute(i), 0));
	}
        */
		
		rec.setValue("INSPECTION_OP_PICTURE",cfd.tsjy.BlobData.ImageEncoder(user.getEmployeeId()));
		mrds.addRow(rec);
		//======================================
		try{
			MREngine engine = new MREngine(pageContext);
			engine.setRootPath(reportpath);
			engine.addReport(reportfile);
			
			engine.addMRDataSet("BGDS", mrds);
			
			engine.bind();
		}catch(Exception e)
		{ 
			e.printStackTrace(); 
		} 


	
%>
<html>
<head>
<title> 报告书录入 </title>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">


<script language="javascript">
<!--

var serial_no = "";
 function   getNewDate(strInterval,NumDay,dtDate)
{   
	
	var dtTmp=new Date(dtDate.replace(/-/g,"/"));
	if (isNaN(dtTmp)) {
		dtTmp=new Date();
	}
	switch (strInterval) {
		case "s":return new Date(Date.parse(dtTmp)+(1000*NumDay));
		case "n":return new Date(Date.parse(dtTmp)+(60000*NumDay));
		case "h":return new Date(Date.parse(dtTmp)+(3600000*NumDay));
		case "d":return new Date(Date.parse(dtTmp)+(86400000*NumDay));
		case "w":return new Date(Date.parse(dtTmp)+((86400000*7)*NumDay));
		case "m":return new Date(dtTmp.getFullYear(),(dtTmp.getMonth())+NumDay,dtTmp.getDate(),dtTmp.getHours(),dtTmp.getMinutes(),dtTmp.getSeconds());
		case "y":return new Date((dtTmp.getFullYear()+NumDay),dtTmp.getMonth(),dtTmp.getDate(),dtTmp.getHours(),dtTmp.getMinutes(),dtTmp.getSeconds());
	}
}


function MRViewer_BeforePostBack() 
{
  failed = MRViewer.CheckRequired();
  if (failed != "")
  {
    alert("下列内容必须输入：\r\n" + failed);
    return false;//返回false终止数据提交
  }
  return true;
}

function MRViewer_AfterPostBack()
{
	//window.close();
	alert("保存完成");
}

//设置报表属性
function setReports()
{
	
	//设置显示页
	report = MRViewer.Report;
	pagecount = MRViewer.PageCount;
	
	doSubmitsetReports();
	//===============================
	MRViewer.Preview();
}
//提交前把所有报表上的数据都设置为可提交
function doSubmitsetReports()
{
	//把每一个是Memo的对象中间包含“”的并且不是总页数的控件对象都设置为可以提交的状态,提交KEY = memo中的字段名
	report = MRViewer.Report;
	pagecount = MRViewer.PageCount;
	for(var i=0;i<pagecount;i++){
		page = report.Pages(i);
		objCount = page.ObjectCount;
		for(var j=0;j<objCount;j++){
			obj = page.Objects(j); 
			if(obj!=null && obj.Prop("TypeName")=="Memo"){
				if(obj.Prop("memo").indexOf("\"")!=-1&&obj.Prop("memo").indexOf("TotalP")==-1)
				{
					obj.Prop("PostbackOption.Key") = obj.Prop("memo").split("\"")[1];
				}
			}
		}
	}
}



//通过XML发送数据

//2006年07月04日 星期二 9:02:55 lybykw //2006年11月09日 星期四 17:14:35

//注意，在调用 SendXML() 方法以前，此方法在调用以前必须以 window.setTimeout("函数名",10); 延时，否则速度会很慢，这个问题已解决，采用异步与同步的方法解决。



//创建xml对象

//2006年11月09日 星期四 17:09:55 lybykw

function SendXML(Url,synchro) {

	var Obj=new XMLObj();

	//默认情况下，synchro为同步。

	if(synchro) {

		synchro=true;

	}

	else {

		synchro=false;

	}

	Obj.sendXML(Url,synchro);

	return Obj.returnXML();



}
function SendText(Url,synchro) {

	var Obj=new XMLObj();

	//默认情况下，synchro为同步。

	if(synchro) {

		synchro=true;

	}

	else {

		synchro=false;

	}

	Obj.sendXML(Url,synchro);

	return Obj.returnText();



}



//xml http 对象

//2006年11月09日 星期四 17:10:10 lybykw

function XMLObj()

{

	var xml=this;

	this.config={

		about:"",

		synchro:false

	}

	this.Http=new ActiveXObject("Microsoft.XMLHTTP");



	this.sendXML=function(Url,synchro)

	{

		try {

			xml.Http.onreadystatechange = xml.returnXML;

			xml.Http.open("get", Url, synchro);	//true|不同步（异步），false|同步

			xml.Http.send();

		} catch(e) {

			alert("SendXml:" + e + "\n请使用 Internet Explorer (IE) 6.0 以上版本进入系统。");

		}

	}



	this.returnXML=function()

	{

		if (xml.Http.readystate == 4) {

			var v="";

			v=xml.Http.responseText;

			v=v.replace(/\s/gi, "");	//先把返回值，变成字符串才行。

			return v;

		}

	}

	this.returnText=function()

	{

		if (xml.Http.readystate == 4) {

			var v="";

			v=xml.Http.responseText;

			return v;

		}

	}

}

//画图
function MRViewer_OnObjectClick(objId)
{
	var activeName = MRViewer.GetObjectProp(objId,"name");
	if(activeName==undefined){
		return;
	}
	
	if(activeName.indexOf("check")!=-1){
		if(activeName==undefined){
			return;
		}
		var value = MRViewer.GetObjectProp(objId, "Memo");
		if (value=="√"){
			v2 = MRViewer.GetObjByName(activeName+"A");
			if(v2){
				v2.Prop("Memo") = "";
			}
			MRViewer.SetObjectProp(objId, "Memo", "");
		} else {
			v2 = MRViewer.GetObjByName(activeName+"A");
			if(v2){
				v2.Prop("Memo") = "　";
			}
			MRViewer.SetObjectProp(objId, "Memo", "√");
		}
		MRViewer.Populate();
	}
	if(activeName.indexOf("time")!=-1){
		if(activeName==undefined){
			return;
		}
		var ObjName = activeName+"A";
		 v2 = MRViewer.GetObjByName(ObjName);
		 if(v2){
			 v2.Prop("Memo") = "/";
			 MRViewer.Populate();
		 }
    }
}

function doSave(){
	alert(111);
	//先生成告知书编号
	if(serial_no == ""){
		var urls = "tsjc/jsp/device/dealserialno.jsp?unit=<%=unit%>&deviceId=<%=deviceId%>";
		var temp = SendXML(urls);
		alert(temp);
		if(temp!="faile"){
			var v2 = MRViewer.GetObjByName("serial_no");
			if(v2){
				v2.Prop("Memo") = temp;
				MRViewer.Populate();
			} else {
				alert("执行错误，请关闭页面重新操作");
				return;
			}
			serial_no = temp ;
		} else {
			alert("保存出现错误,请重新操作");
			return;
		}
	}
	var Obj=formObj;
	var url = "/cfdinfo?CONTROLLER=cfd.tsjc.application.Registration&op=saveGZ&deviceId=<%=deviceId%>&serial_no="+serial_no;

	MRViewer.postbackurl = document.location.protocol + '//' + document.location.host + url;
	MRViewer.PostBack();
	window.returnValue="reOpen";
}
//-->
</script>
</head>
<body style="margin: 0" onload="setReports()">

<table height="100%" width="100%" border=0 cellpadding=0 cellspacing=0>
  <tr height="90%"> 
    <td valign="top">
	<ming:MRViewer id="MRViewer" shownow="true"  width="100%"   height="100%" simple="false" canedit="true" invisiblebuttons="Close,PrintPopup,ExportPopup,Find,FindNext"  postbackurl="" canedit="true" wrapparams="true"  />
    </td>
  </tr>
  <tr height="10%">
  <td align=center valign=top>&nbsp;
	<form name="formObj" method="post" action="" onsubmit="return doSubmit(this);">
	    <table width=736 border=1  cellpadding=0 cellspacing=0 >
		<div id=printBtn align=center >
		<input type="button" name="ptBtn1" value="保存" onclick="doSave()">
		<input type="button" name="ptBtnclose" value="关闭" onclick="window.close()">
		</div>
		</table>
	</form>
	</td>
  </tr>
</table> 
</body>
</html>
<%}%>