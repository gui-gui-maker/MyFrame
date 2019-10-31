<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>添加处理记录</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

String deal_status = request.getParameter("deal_status");
String nextDate = request.getParameter("nextDate");
String nowDate = DateUtil.getLastDateStringOfYear("yyyy-MM-dd");
CurrentSessionUser user = SecurityUtil.getSecurityUser();
%>
</head>
<title></title>
<script language="javascript">
$(function() {
    $("#formObj").initForm({
		success: function (response) {//处理成功
		}, getSuccess: function (response){
			
		}, toolbar: [
      		{
      			text: "确定", icon: "save", click: function(){
			        doSubmit();			
      			}
      		},
			{
				text: "关闭", icon: "cancel", click: function(){
					api.close();
				}
			}
		], toolbarPosition: "bottom"
	});		
});
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}
//求两个日期的差
function getDateTimeDesc(startDateTime,endDateTime)
{
	var c = new Date(startDateTime.substring(0,4),(parseInt(startDateTime.substring(5,7),10)-1).toString(),startDateTime.substring(8,10),startDateTime.substring(11,13),startDateTime.substring(14,16));
	var d = new Date(endDateTime.substring(0,4),(parseInt(endDateTime.substring(5,7),10)-1).toString(),endDateTime.substring(8,10),endDateTime.substring(11,13),endDateTime.substring(14,16));
	return (d-c)/1000/60/60/24;
}
//该函数检查开始日期是否小于结束日期，小于返回1，等于返回0，大于返回－1
function Date1VsDate21 (date1str,date2str) 
{
	date1_len=date1str.length;    
	yy=""; 
	mm=""; 
	dd=""; 
	flag="0";
	if (date1_len!=0)
		{
			for(i=0;i<date1_len;i++)
			{       	
				a=date1str.charAt(i);         
				if(i<4)
				{
					yy=yy+a;
				}                 			  		
				else if (flag=="0" && a!='-')
				{
					mm=mm+a;
				}                 		
				else if (flag=="0" && a=='-'&&i>4) 
				{
					flag="1";
				}
		
				else if (flag=="1") 
				{
					dd=dd+a;
				} 
			}
		}   	
	if (mm.length==1) {mm="0"+mm;}
	if (dd.length==1) {dd="0"+dd;}
	date_str1=yy+mm+dd;

	date2_len=date2str.length;    
	yy=""; 
	mm=""; 
	dd=""; 
	flag="0";
	if (date2_len!=0)
		{
			for(i=0;i<date2_len;i++)
			{       	
				a=date2str.charAt(i);         
				if(i<4)
				{
					yy=yy+a;
				}                 			  		
				else if (flag=="0" && a!='-')
				{
					mm=mm+a;
				}                 		
				else if (flag=="0" && a=='-'&&i>4) 
				{
					flag="1";
				}
		
				else if (flag=="1") 
				{
					dd=dd+a;
				} 
			}
		}   	
	if (mm.length==1) {mm="0"+mm;}
	if (dd.length==1) {dd="0"+dd;}
	date_str2=yy+mm+dd;


	if (parseInt( date_str1)>parseInt( date_str2))
	{
		return -1;
	}
	else if(parseInt( date_str1)==parseInt( date_str2))
	{
		return 0;
	}
	else 
	{
		return 1;
	}
}
function FormatDate(s)
{
	if(s==null){
		return '';
	}
	var d;
	s=s.replace(/\.0$/,"");
	s=s.replace(/\-/ig,"/");
	s=s.replace(/\:/ig,"/");
	s=s.replace(/\s/ig,"/");
	s=s.split("/");
	if(s[0]==undefined)	s[0]="0000"
	if(s[1]==undefined)	s[1]="00"
	if(s[2]==undefined)	s[2]="00"
	if(s[3]==undefined)	s[3]="00"
	if(s[4]==undefined)	s[4]="00"
	if(s[5]==undefined)	s[5]="00"
	d=new Date(s[0],s[1]-1,s[2],s[3],s[4],s[5]);
	return d;//返回时间
}
function doSubmit()
{
	var nextDate=FormatDate("<%=nextDate%>");
	var nowDate =FormatDate("<%=nowDate%>");
	if (!$("#formObj").validate().form())
	{
		return false;
	}
	<%if(deal_status.equals("100")) {%>
		var value1 = $("#value1").val();
		var value=FormatDate(value1);
		if(value>nextDate){
			alert("预检日期不能超过下次检验日期");
			return false;
		}
	<%} else if(deal_status.equals("101")){%>
	          var jcdate=$("input[name='value2']").val();
			if(jcdate!=""){
				var value=FormatDate(jcdate);
				if(value>nowDate){
					alert("检验日期不能超过当前日期！");
					return false;
				}
			}
	<%} else if(deal_status.equals("102")){%>
		
	var yjdate=$("input[name='value2']").val();
		if($("#value1").val()=="6" ){
			if(yjdate==""){
				alert("主选项是“使用单位要求的预检日期超过了下次检验日期”，则请输入“预检日期”");
				return false;
			}
			if(yjdate!=""){
				var value=FormatDate(yjdate);
				if(value<=nextDate){
					alert("主选项是“使用单位要求的预检日期超过了下次检验日期”，则预检日期不能早于下次检验日期");
					return false;
				}
			}
		}
		<%} else if(deal_status.equals("104")){%>
		
		  var gznum=$("input[name='value2']").val();
		if(gznum.length<6 ){
			alert("告知书编号不能小于6位");
			return false;
			
		}
	<%} else if (deal_status.equals("105")){%>
		var zgdate=FormatDate($("input[name='value2']").val());
		if(zgdate>nextDate){
			alert("整改日期不能晚于下次检验日期");
			return false;
		}
		if(zgdate<nowDate){
			alert("整改日期不能早于当前日期");
			return false;
		}
	<%} else if (deal_status.equals("106")){%>
	    var chose=$("#value1").val();
		if(chose=="1"||chose=="2"){
			var tgznum=$("input[name='value2']").val();
			if(tgznum==""){
				alert("请输入通知书编号");
				return false ;
			}
			if(tgznum.length< 7 || tgznum.length > 13){
				alert("通知书编号不正确");
				return false ;
			}
			
			if($("input[name='value3']").val()==""){
				alert("请输入送达日期");
				return false ;
			}
		} else if (chose=="4"){
			
			if($("input[name='value4']").val()==""){
				alert("请输入延期说明");
				return false ;
			}
		} else if (chose=="12"){
			if($("input[name='value5']").val()==""){
				alert("请输入其他机构名称");
				return false ;
			}
		} else if (chose=="13"){
			if($("input[name='value5']").val()==""){
				alert("请输入其他机构名称");
				return false ;
			}
		} else if (chose=="6"){
			if($("input[name='value6']").val()=="" && $("input[name='value7']").val()==""){
				alert("请输入重复设备的单位或者重复注册代码");
				return false ;
			}
		} else if (chose=="7"){
			if($("input[name='value8']").val()==""){
				alert("请输入下次检验日期");
				return false ;
			}
		} else if (chose=="9"){
			if( $("input[name='value9']").val()==""){
				alert("请输入维保单位名称");
				return false ;
			}
		} else if ( chose=="11"){
			if($("input[name='value10']").val()==""){
				alert("请输入其他情况内容");
				return false ;
			}
		}
	<%}%>
	if(!confirm("请检查填写内容，确认无误后点击确定")){
		return false;
	}
	$(api.data.pwindow.document).find('#deal_remark').val($("#remark").val())
	api.close();
}


	function setRemark(){
		<% if (deal_status.equals("106")){%>
			if( $("#value1").val()=="4"){
				 $("input[name='value9']").val("使用单位已向我院申请压力容器延期检验,我院已批准,并已向分局送达《固定式压力容器特殊检验情况的处理报告》");
			}else{
				$("input[name='value9']").val("");
			}
		<%}%>
		var str = "" ;
		var max=$(".c_params").size()+1;
		for(var i=1 ; i < max ;i++){
			var para=$("input[name='para"+i+"']").val();
			var value=$("input[name='value"+i+"']").val();
			//alert(i+"="+para+"-"+value);
				if(value!=""){
					if(para!='')
						str += para+": "+value+";  ";
					else
						str+=value+";  ";
				   }
		}
		$("#remark").val(str);
	}

	function checkRate(input)   
	{   
		var re = /^[\/|1-9]+[\/|0-9]*]*$/;
		if (!re.test(input.value))   
		{   
			alert("电话输入不合法"); 
			input.value=""; 
	        input.focus();   
	        return false;   
		}
		return true ;
	}  
	function setItem(){
		<%if(deal_status.equals("102")) {%>
			if($("#value1").val()=="6" ){
				document.getElementById("advance_time").style.display="";
			} else {
				document.getElementById("advance_time").style.display="none";
				$("input[name='value2']").val("");
			}
		<%} else if(deal_status.equals("106")){ %>
	       var chose=$("#value1").ligerComboBox().getValue();
			if(chose=="1"||chose=="2"){
				document.getElementById("notice").style.display="";
			} else {
				document.getElementById("notice").style.display="none";
				$("input[name='value2']").val("");
				$("input[name='value3']").val("");
			}
			if (chose=="4"){
				document.getElementById("delay").style.display="";
			}else {
				document.getElementById("delay").style.display="none";
				$("input[name='value4']").val("");
			}
			if (chose=="5" || chose=="13"){
				document.getElementById("other_org").style.display="";
			}else {
				document.getElementById("other_org").style.display="none";
				$("input[name='value5']").val("");
			}
 			if (chose=="6"){
 				document.getElementById("overlap").style.display="";
			}else {
				document.getElementById("overlap").style.display="none";
				$("input[name='value6']").val("");
				$("input[name='value7']").val("");
			}
 			if (chose=="9"){
 				document.getElementById("maintain").style.display="";
			}else {
				document.getElementById("maintain").style.display="none";
				$("input[name='value9']").val("");
			}
 			if (chose=="7"){
 				document.getElementById("next").style.display="";
			}else {
				document.getElementById("next").style.display="none";
				$("input[name='value8']").val("");
			}
 			if (chose=="11"){
 	 			alert("选择“其他特殊情况”时，请先与业务办相关人员联系");
 	 			document.getElementById("other_reason").style.display="";
			}else {
				document.getElementById("other_reason").style.display="none";
				$("input[name='value10']").val("");
			}
		<%}%>
	}
	//alert("<%=deal_status%>");
</script>

</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="setRemark()">
<form name="formObj"  id="formObj" method="post" action="">
<table border="0" cellpadding="0" cellspacing="0" id="ow_big_table"  class="l-detail-table">
	<tr>
		<td class="//obt2">
			
			<div style="overflow-y:auto;height:100%;padding:8px 8px 8px 8px;">
				<table border="0" cellpadding="0" cellspacing="0" align="center" class="table_detail">
					<%
					//已报检
					if(deal_status.equals("100")) {%>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1"  class="c_params" value="预检日期">
							<input type= "hidden" class="c_params" name="para2"  class="c_params"value="接受报检人">
							<td class="l-t-td-left">预检日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value1" id="value1"  value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}"  class="ipt_01" readonly>
							</td>
							<td class="l-t-td-left">接受报检人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value2" value="<%=user.getName()%>" ltype='text' validate="{required:true,maxlength:25}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
					<%} 
					//已检验合格，待出报告
					else if (deal_status.equals("101")) {%>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1" value="报告书编号">
							<input type= "hidden" class="c_params" name="para2" value="检验日期">
							<td class="l-t-td-left">报告书编号:</td>
							<td class="l-t-td-right">
								<input type="text" name="value1" value="" ltype='text' validate="{required:true,maxlength:32}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">检验日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value2" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para3" value="检验人员">
							<input type= "hidden" class="c_params" name="para4" value="整改内容">
							<td class="l-t-td-left">检验人员:</td>
							<td class="l-t-td-right">
						
								<input type="text" name="value3" value="" ltype='text' validate="{required:false,maxlength:8}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">整改内容:</td>
							<td class="l-t-td-right">
								<input type="text" name="value4" value=""ltype='text' validate="{required:true,maxlength:32}"  class="ipt_01" onchange="setRemark()" >
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para5" value="检验结论">
							<td class="l-t-td-left">检验结论:</td>
							<td class="l-t-td-right" colspan="3">
							<!-- value5 -->
							<input type="text" id="value5"  name="value5" ltype="select" validate="{required:false}"  onchange="setRemark();setItem();" ligerui="{
				readonly:true,
				data: [{ id: 1 , text: '合格' }, { id: 2, text: '不合格'},{id:3,text:'允许运行'},{id:4,text:'监督运行'},
				{id:5,text:'整改后运行'},{id:6,text:'压力容器1级'},{id:7,text:'压力容器2级'},{id:8,text:'压力容器3级'},{id:9,text:'压力容器4级'}]
				}"/>
							</td>
						</tr>
					<%} 
					//使用单位无故拖延或拒绝检验
					else if(deal_status.equals("102"))
					{
					%>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1" value="">
							<td class="l-t-td-left">主选项:</td>
							<td class="l-t-td-right" colspan="3">
								<!-- value1 -->
							<input type="text" id="value1" name="value1" ltype="select" validate="{required:false}"  onchange="setRemark();setItem();"  ligerui="{
								readonly:true,
								data: [{ id: 1 , text: '拒绝检验' }, { id: 2, text: '电话及现场均无法联系使用单位'},{id:3,text:'多次催检，检验时间未确定'},{id:4,text:'使用单位无法确定检验时间'},
								{id:5,text:'维保单位无法确定检验时间'},{id:6,text:'使用单位要求的预检日期超过了下次检验日期'},{id:7,text:'使用方相关安全管理人员不在本市，无法确定检验时间'},{id:8,text:'该设备为流动式设备，经核实现在外地施工'}]
								}"/>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para3" value="核实人">
							<input type= "hidden" class="c_params" name="para6" value="联系电话">
							<td class="l-t-td-left">核实人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value3" value="" ltype='text' validate="{required:true,maxlength:8}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">联系电话:</td>
							<td class="l-t-td-right">
								<input type="text" name="value6" value="" ltype='text' validate="{required:false,maxlength:32}"  class="ipt_01" onblur="this.value=trim(this.value);setRemark()" onchange='checkRate(this)'>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para4" value="联系日期">
							<input type= "hidden" class="c_params" name="para5" value="使用单位联系人">
							<td class="l-t-td-left">联系日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value4" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
							<td class="l-t-td-left">使用单位联系人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value5" value="" ltype='text' validate="{required:true,maxlength:8}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
						<tr class="d_tr" id="advance_time" style="display:none">
							<input type= "hidden" class="c_params" name="para2" value="预检日期">
							<td class="l-t-td-left">预检日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value2" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
						</tr>
					<%} 
					//现场核实该设备不存在或其使用单位不存在
					else if(deal_status.equals("103"))
					{
					%>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1" value="">
							<td class="l-t-td-left">主选项:</td>
							<td class="l-t-td-right" colspan="3">
							<!-- value1 -->
							<input type="text" id="value1" name="value1" ltype="select" validate="{required:false}"  onchange="setRemark();setItem();"  ligerui="{
								readonly:true,
								data: [{ id: 1 , text: '经现场核实该设备不存在' }, { id: 2, text: '经现场核实该使用单位不存在'},{id:3,text:'经现场核实，该设备已拆除'}]
								}"/>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para2" value="核实人">
							<input type= "hidden" class="c_params" name="para3" value="核实日期">
							<td class="l-t-td-left">核实人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value2" value=""ltype='text' validate="{required:false,maxlength:8}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">核实日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value3" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para4" value="联系电话">
							<td class="l-t-td-left">联系电话:</td>
							<td class="l-t-td-right">
								<input type="text" name="value4" value="" ltype='text' validate="{required:false,maxlength:32}"  class="ipt_01" onblur="this.value=trim(this.value);setRemark()" onchange='checkRate(this)'>
							</td>
						</tr>
					<%} 
					//已检验，不合格
					else if(deal_status.equals("104"))
					{
					%>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1" value="检验日期">
							<input type= "hidden" class="c_params" name="para2" value="已书面告知使用单位及分局，告知书编号">
							<td class="l-t-td-left">检验日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value1" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
							<td class="l-t-td-left">告知书编号:</td>
							<td class="l-t-td-right">
								<input type="text" name="value2" ltype='text' onchange="setRemark()" validate="{required:true,maxlength:32}">
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para3" value="送达（传真）日期">
							<input type= "hidden" class="c_params" name="para4" value="检验员">
							<td class="l-t-td-left">送达（传真）日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value3" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
							<td class="l-t-td-left">检验员:</td>
							<td class="l-t-td-right">
								<input type="text" name="value4" value="" ltype='text' validate="{required:true,maxlength:8}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
					<%} 
					// 已检验，等待技术整改
					else if(deal_status.equals("105"))
					{
					%>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1" value="整改内容">
							<td class="l-t-td-left">整改内容:</td>
							<td class="l-t-td-right" colspan="3">
								<input type="text" name="value1" value="" ltype='text' validate="{required:true,maxlength:100}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para2" value="整改期限">
							<input type= "hidden" class="c_params" name="para3" value="检验员">
							<td class="l-t-td-left">整改期限:</td>
							<td class="l-t-td-right">
								<input type="text" name="value2" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
							<td class="l-t-td-left">检验员:</td>
							<td class="l-t-td-right">
								<input type="text" name="value3" value="" ltype='text' validate="{required:true,maxlength:8}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
					<%} 
					//其他检验机构验证
					else if(deal_status.equals("7"))
					{
					%>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1" value="">
							<td class="l-t-td-left">主选项:</td>
							<td class="l-t-td-right" colspan="3">
							<!-- value1 -->
							<input type="text" id="value1" name="value1" ltype="select" validate="{required:false}"  onchange="setRemark();setItem();"  ligerui="{
									readonly:true,
									data: [{ id: 1 , text: '该单位此类特种设备系自行检验' }, { id: 2, text: '该设备已由其他检验机构检验'},{id:3,text:'使用单位已联系其他检验机构'}]
									}"/>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para3" value="核实人">
							<input type= "hidden" class="c_params" name="para4" value="联系日期">
							<td class="l-t-td-left">核实人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value3" value="" ltype='text' validate="{required:true,maxlength:32}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">联系日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value4" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para5" value="使用单位联系人">
							<input type= "hidden" class="c_params" name="para6" value="联系电话">
							<td class="l-t-td-left">使用单位联系人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value5" value="" ltype='text' validate="{required:true,maxlength:32}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">联系电话:</td>
							<td class="l-t-td-right">
								<input type="text" name="value6" value="" ltype='text' validate="{required:true,maxlength:32}"  class="ipt_01" onblur="this.value=trim(this.value);setRemark()"  onchange='checkRate(this)'>
							</td>
						</tr>
						<tr class="d_tr" id="other_org" >
							<input type= "hidden" class="c_params" name="para2" value="检验机构">
							<td class="l-t-td-left">检验机构名称:</td>
							<td class="l-t-td-right" colspan="3">
								<input type="text" name="value2" value="" ltype='text' validate="{required:true,maxlength:32}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
						<%}
							// 重大问题
						
					else if(deal_status.equals("106"))
					{
					%>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1" value="">
							<td class="l-t-td-left">主选项:</td>
							<td class="l-t-td-right" colspan="3">
								<!-- value1 -->
							<input type="text" id="value1" name="value1" ltype="select" validate="{required:false}"  onchange="setRemark();setItem();"  ligerui="{
								readonly:true,
								data: [{ id: 1 , text: '设备出现重大问题，已向使用单位及分局送达书面通知' }, { id: 2, text: '设备出现重大问题，已向使用单位送达书面通知'}
								,{id:3,text:'设备出现重大问题，分局已书面通知责令其停止使用'},{id:4,text:'企业申请压力容器延期检验'}
								,{id:5,text:'该设备不属于特种设备监察范围'},{id:6,text:'该设备与系统中其他设备重复'}
								,{id:7,text:'系统数据有误'},{id:8,text:'使用单位未安装对讲电话'}
								,{id:9,text:'原维保单位资质审查未通过'},{id:10,text:'使用单位未签订维保合同或脱保'}
								,{id:11,text:'其他特殊情况'}, { id: 12, text: '该设备已由其他检验机构检验'},{id:13,text:'使用单位已联系其他检验机构'}]
								}"/>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para11" value="核实人">
							<input type= "hidden" class="c_params" name="para12" value="联系日期">
							<td class="l-t-td-left">核实人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value11" value="" ltype='text' validate="{required:true,maxlength:8}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">联系日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value12" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para13" value="使用单位联系人">
							<input type= "hidden" class="c_params" name="para14" value="联系电话">
							<td class="l-t-td-left">使用单位联系人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value13" value="" ltype='text' validate="{required:true,maxlength:8}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">联系电话:</td>
							<td class="l-t-td-right">
								<input type="text" name="value14" value="" ltype='text' validate="{required:false,maxlength:32}"  class="ipt_01" onblur="this.value=trim(this.value);setRemark()"  onchange='checkRate(this)'>
							</td>
						</tr>
						<tr class="d_tr" id="notice" style="display:">
							<input type= "hidden" class="c_params" name="para2" value="通知书编号">
							<input type= "hidden" class="c_params" name="para3" value="送达日期">
							<td class="l-t-td-left">通知书编号:</td>
							<td class="l-t-td-right">
								<input type="text" name="value2" value="" ltype='text' validate="{required:true,maxlength:32}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">送达日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value3" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
						</tr>
						<tr class="d_tr" id="delay" style="display:none">
							<input type= "hidden" class="c_params" name="para4" value="说明">
							<td class="l-t-td-left">延期说明:</td>
							<td class="l-t-td-right" colspan="3">
								<input type="text" name="value4" value="" size="95" ltype='text' validate="{required:true,maxlength:150}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
						<tr class="d_tr" id="other_org" style="display:none">
							<input type= "hidden" class="c_params" name="para5" value="检验机构">
							<td class="l-t-td-left">其他机构名称:</td>
							<td class="l-t-td-right" colspan="3">
								<input type="text" name="value5" value="" ltype='text' validate="{required:true,maxlength:100}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
						<tr class="d_tr" id="overlap" style="display:none">
							<input type= "hidden" class="c_params" name="para6" value="与此单位设备重复">
							<input type= "hidden" class="c_params" name="para7" value="重复注册代码(或出厂编号)">
							<td class="l-t-td-left">与此单位设备重复:</td>
							<td class="l-t-td-right">
								<input type="text" name="value6" value="" ltype='text' validate="{required:true,maxlength:100}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left" align="left">重复注册代码(或出厂编号):</td>
							<td class="l-t-td-right">
								<input type="text" name="value7" value="" ltype='text' validate="{required:true,maxlength:32}"   class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
						<tr class="d_tr" id="next" style="display:none">
							<input type= "hidden" class="c_params" name="para8" value="下次检验日期为">
							<td class="l-t-td-left">下次检验日期为:</td>
							<td class="l-t-td-right">
								<input type="text" name="value8" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
						</tr>
						<tr class="d_tr" id="maintain" style="display:none">
							<input type= "hidden" class="c_params" name="para9" value="维保单位名称">
							<td class="l-t-td-left">维保单位名称:</td>
							<td class="l-t-td-right" colspan="3">
								<input type="text" name="value9" value="" ltype='text' validate="{required:true,maxlength:100}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
						<tr class="d_tr" id="other_reason" style="display:none">
							<input type= "hidden" class="c_params" name="para10" value="说明">
							<td class="l-t-td-left">其他情况内容:</td>
							<td class="l-t-td-right" colspan="3">
								<input type="text" name="value10" value=""ltype='text' validate="{required:true,maxlength:400}"  class="ipt_01" onchange="setRemark()">
							</td>
						</tr>
					<%} // 设备已停用
					else if(deal_status.equals("107"))
					{
					%>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1" value="">
							<td class="l-t-td-left">主选项:</td>
							<td class="l-t-td-right" colspan="3">
							<!-- value1 -->
							<input type="text" id="value1" name="value1" ltype="select" validate="{required:false}"  onchange="setRemark();setItem();"  ligerui="{
								readonly:true,
								data: [{ id: 1 , text: '经现场核实，该设备未使用，已敦促使用单位办理停用手续并通知分局' }, { id: 2, text: '经现场核实，该设备未使用'},{id:3,text:'经现场核实，该设备已停用'}]
								}"/>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para2" value="核实人">
							<input type= "hidden" class="c_params" name="para3" value="联系日期">
							<td class="l-t-td-left">核实人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value2" value="" ltype='text' validate="{required:true,maxlength:8}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">联系日期:</td>
							<td class="l-t-td-right">
								<input type="text" name="value3" value="" ltype="date" onchange="setRemark()" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" class="ipt_01" readonly>
							</td>
						</tr>
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para4" value="使用单位联系人">
							<input type= "hidden" class="c_params" name="para5" value="联系电话">
							<td class="l-t-td-left">使用单位联系人:</td>
							<td class="l-t-td-right">
								<input type="text" name="value4" value="" ltype='text' validate="{required:true,maxlength:32}"  class="ipt_01" onchange="setRemark()">
							</td>
							<td class="l-t-td-left">联系电话:</td>
							<td class="l-t-td-right">
								<input type="text" name="value5" value="" ltype='text' validate="{required:false,maxlength:32}"  class="ipt_01" onblur="this.value=trim(this.value);setRemark()" onchange='checkRate(this)'>
							</td>
						</tr>
					<%} else if(deal_status.equals("108"))
					{
					%>
						
						<tr class="d_tr">
							<input type= "hidden" class="c_params" name="para1" value="">
							<td class="l-t-td-left">备注:</td>
							<td class="l-t-td-right" colspan="3">
								<textarea name="value1"  class="area_text" rows="3"  ltype='text' validate="{required:true,maxlength:500}"  onchange="setRemark()"/></textarea>
							</td>
							
						</tr>
						
					<%} %>
					<tr class="d_tr">
						<td class="l-t-td-left">备注预览：</td>
						<td class="l-t-td-right" colspan="3">
							<textarea name="remark"  id="remark" class="area_text" rows="3"  ltype='text' validate="{required:true,maxlength:1000}"  readonly/></textarea>
						</td>
					</tr>
					
				</table>
			</div>

		</td>
	</tr>
</table>
</form>

</body>
