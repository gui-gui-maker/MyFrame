<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q" %>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page import="java.util.*" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<script type="text/javascript" src="${pageContext.request.contextPath}/app/k/km/lib/app-end.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.css" />
<script src="${pageContext.request.contextPath}/app/k/km/lib/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/app/k/km/lib/kh-mobile.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/pub/bpm/js/util.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no"/>
<meta name="apple-mobile-web-app-capable" content="yes"/>
<meta name="apple-mobile-web-app-status-bar-style" content="black"/>
<link rel="stylesheet" href="app/k/jqm/skins/default.css"/>
<script src="app/k/jqm/a-main.js" type="text/javascript"></script>
<!-- <script type="text/javascript" charset="utf-8" src="k/jqm/jquery2.js"></script>
<script type="text/javascript" charset="utf-8" src="k/jqm/jquery.mobile.js"></script> -->
<script src="app/k/km/lib/jquery.min.js"></script>
<% 
String id=request.getParameter("id");
if(id==null){
	id=(String)session.getAttribute("id");
}
%>
<% 
String dj_peoppleid=request.getParameter("dj_peoppleid");
if(dj_peoppleid==null){
	dj_peoppleid=(String)session.getAttribute("dj_peoppleid");
}
%>
<% 
String eId=request.getParameter("eId");
if(eId==null){
	eId=(String)session.getAttribute("eId");
}
%>
<script src="app/k/km/lib/kh-mobile.js"></script>
<script src="app/k/km/lib/kh-mobile-list.js"></script>
<script type="text/javascript">
var id ='<%=id%>';
var dj_peoppleid = '<%=dj_peoppleid%>';
var eId = '<%=eId%>';
$(function(){
	//alert(id);
		//会议室名称
		getcodetabl("meeting_room_type","fkOaMeetingRoom"); 
		//会议室布置形式
		getcodetabl("meeting_layout_type","hysbzxs");
		//是否制作会标
		getcodetabl("wx_meeting_IforNot","ifZzhb");
		//是否制作欢迎标语
		getcodetabl("wx_meeting_IforNot","ifHyby");
		//是否制作座牌
		getcodetabl("wx_meeting_IforNot", "ifZzhyzp");
		//是否制作就餐座牌
		getcodetabl("wx_meeting_IforNot", "ifZzjczp");
		//是否摆鲜花
		getcodetabl("wx_meeting_IforNot", "ifFlowers");
		//是否要水果
		getcodetabl("wx_meeting_IforNot", "ifFruits");
		//申请状态
	    getcodetabl("TJY2_MEETING_APSTATUS","status");
	    //会议状态
	    getcodetabl("wx_meeting_status","meeting_status");
        $("#form1").initForm({
			 getSuccess: function (res){
					if(res.data==null||res.success=="false"){
						alert("加载失败！");
					}else{
						var status=$("#status").text();
						var meeting_status=$("#meeting_status").text();
					    //alert(meeting_status);
					    if('<%=eId%>'!='<%=dj_peoppleid%>'){
					    	$("#next1").hide();
					    }else{
					    	if(status=="未提交" || status=="已撤销" || status=="审核不通过"){
								$("#next1").hide();
							}else if(status=="审核通过"){
								if(meeting_status=="会议已结束"){
									$("#next1").hide();
								}else{
									$("#next1").show();
								}
							}else{
								$("#next1").show();
							}
					    }
						
					}
					
			}
 	});		
 $("#next1").click(function() {
	//alert(dj_peoppleid);
	// alert(eId);
	// alert(id);
	<%-- if('<%=eId%>'!='<%=dj_peoppleid%>'){
        dAlert("只能撤销自己的申请！");
        return;
    } --%>
	$.ajax({
		url : "${pageContext.request.contextPath}/updates/yijina/ret1.do?id="+'<%=id%>'+"&typeCode=TJY2_OFFICE_MEETREQ_FLOW"+"&eId="+eId,
		type : "post",
		datatype : "json",
		contentType: "application/json; charset=utf-8",
      success : function (data) {
      	if(data.success){        	
      		id=data.data;
      		 dAlert("撤销成功！");
				$("#form1").transform("detail");   
				$("#next1").hide();
		       $("#form1").setValues("${pageContext.request.contextPath}/oa/meetingReq/info/detail.do?id="+id,function(res){
		      });     
      	}else{
      		//$("body").unmask();
      		dialogShow(data.msg, 300, 100);
      	}
      }
	});  		

});
});
function getcodetabl(code,id) {
	$.ajax({
		url : "${pageContext.request.contextPath}/employeeBaseAction/getcodetabl.do?tablname=" + code,
		type : "POST",
		async : false,
		success : function(json) {
			var s = "";
			if (json.success) {
				for ( var i in json.data) {
					s += "<option value='"+json.data[i].value+"'>"
							+ json.data[i].name + "</option>";
				}
				$("#" + id).html(s);

			}
		}
	});
}
</script>
<style>
.wrapper{ margin-top:28%;}

</style>
</head>
<body>
<div id="a-index" class="a-index">
	<div class="header">
		<div class="bg"></div>
		<div class="logo"></div>
		<div class="user">
			<h1>四川省特检院</h1>
			<h2>办公助手-申请详情</h2>
		</div>
	</div>
	</div>
	<div id="wrapper" class="wrapper">
        <div class="container">	
			<div class="page-panel" id="m-page-panel">
				<form id="form1" name="form1" method="post" action="" onsubmit="return false;" getAction="${pageContext.request.contextPath}/oa/meetingReq/info/detail.do?id=<%=id%>" pageStatus="detail">
					<table border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="">
						<tr class="tr1">
							<td width="100" class="tdtext1">申请部门：</td>
							<td><input type="text" class="form-control" name="sqDepartment"  id="sqDepartment" readonly="readonly" ext_name="申请部门" ext_isNull="1" ></td>
						</tr>				
				        <tr class="tr1">
							<td class="tdtext1">会议开始时间：</td>
							<td><input type="text" class="form-control" id="startTime" name="startTime" placeholder="时间格式为AAAA-BB-CC" ext_name="会议开始时间" maxLength="18" ></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">会议结束时间：</td>
							<td><input type="text" class="form-control" id="endTime" name="endTime" placeholder="时间格式为AAAA-BB-CC" ext_name="会议结束时间" maxLength="18" ></td>
						</tr>
			            <tr class="tr1">
							<td class="tdtext1">会议名称：</td>
							<td><input class="form-control"  name="name"  id="name" placeholder="请输入会议名称"  ext_name="会议名称"  ></td>
						</tr>
			             <tr class="tr1">
							<td class="tdtext1">会议室名称：</td>
							<td><select  class="form-control" id="fkOaMeetingRoom" name="fkOaMeetingRoom"  ext_name="会议室名称" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">参 会 人 数：</td>
							<td><input type="text" class="form-control" id="pnumber" name="pnumber"   placeholder="请输入整数" ext_name="参会人数" ext_isNull="1" maxLength="11"></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">会 议 地 点：</td>
							<td><input type="text" class="form-control" id="hyAdress" name="hyAdress" ext_isNull="1"></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">会议室布置形式：</td>
					    	<td><select class="form-control" id="hysbzxs" name="hysbzxs"></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否制作会标：</td>
					    	<td><select class="form-control" id="ifZzhb" name="ifZzhb"  ext_name="是否需要制作座牌" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否制作欢迎标语：</td>
					    	<td><select class="form-control" id="ifHyby" name="ifHyby"  ext_name="是否制作欢迎标语" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否需要制作座牌：</td>
					    	<td><select class="form-control" id="ifZzhyzp" name="ifZzhyzp"  ext_name="是否需要制作座牌" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否制作就餐座牌：</td>
					    	<td><select class="form-control" id="ifZzjczp" name="ifZzjczp"  ext_name="是否制作就餐座牌" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否摆鲜花：</td>
					    	<td><select class="form-control" id="ifFlowers" name="ifFlowers"  ext_name="是否摆鲜花" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">是否要水果：</td>
					    	<td><select class="form-control" id="ifFruits" name="ifFruits"  ext_name="是否要水果" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">需要办公室配合的其它事项：</td>
					    	<td><input class="form-control" id="officeOtherPz" name="officeOtherPz"></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">备注：</td>
					    	<td><input class="form-control" id="remarks" name="remarks"></td>
						</tr>
			            <tr class="tr1">
							<td class="tdtext1">承办人：</td>
							<td>
							 <input class="form-control" id="compere"  name="compere"   ext_name="承办人" ext_isNull="1"></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">申请状态：</td>
							<td><select class="form-control" name="status"  id="status"   ext_name="申请状态" ext_isNull="1" ></select></td>
						</tr>
						<tr class="tr1">
							<td class="tdtext1">会议状态：</td>
							<td><select class="form-control" name="meeting_status"  id="meeting_status"   ext_name="会议状态" ext_isNull="1" ></select></td>
						</tr>
			  </table>
		</form>
		</div>
		<div class="bt1" >
				<div class="text-center row">
					<div class=""><a id="next1" class="button button-block button-rounded button-primary">撤销</a></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
		