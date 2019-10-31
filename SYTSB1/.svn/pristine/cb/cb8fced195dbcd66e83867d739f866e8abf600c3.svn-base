<%@ page contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>打印准考证</title>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
    var photoAdd='${zpJlxx.photoAdd}';
    if(photoAdd!=""){
        $("#photos").attr("src","/fileUpload/downloadByObjId.do?id="+photoAdd);
    }
})
	
</script>
<style type="text/css" id="style1">
.tbl_fnl {
	width: 190mm;
	margin-left: auto;
	margin-right: auto;
	margin-top: 1em;
}

.l-grid-header {
	background: none;
}

.l-grid-header th {
	font-weight: bold;
}

.dataTd td,.l-grid-header th {
	border: 0px gray solid;
	font-size: 13px;
	padding: 5px;
	height:18px;
}
</style>
<style type="text/css" media="print">
.control {
	display: none;
}
.tbl_fnl td,.tbl_fnl th{
	font-size: 14px;
}
</style>
</head>

<style>
.wrap { width:620px; margin:0 auto; font-size:18px; }
h1, h2 { text-align:center; font-family:"黑体"; }
h1 { font-size:24px; }
table.first { border-collapse:collapse; text-align:center; margin-top:20px; }
table.first td { border:#000 solid 1px; padding:15px 6px; }
.text { width:550px; overflow:hidden; padding:0 20px 0 30px; }
.text p { text-indent:30px; line-height:38px; margin:0; padding:0; }
</style>
<body style="overflow:  scroll;">
<div class="wrap">
	<h1>成都医学院（筹）</h1>
	<br />
	<h1> ${dateString1 }年公开招聘面试准考证</h1>
	<br />
	<table border="0" align="center" cellpadding="0" cellspacing="0" width="620" class="first">
		<tr>
			<td width="100"><strong>考生姓名</strong></td>
			<td width="188">${zpJlxx.name }</td>
			<td width="95"><strong>性  别</strong></td>
			<td width="100">
				<c:if test="${zpJlxx.sex=='2' }">女</c:if>
				<c:if test="${zpJlxx.sex=='1' }">男</c:if>
			</td>
			<td width="138" rowspan="5"><img id="photos" src="app/zpmanage/open/images/psb.png" width="110"
							height="135" alt="" /></td>
		</tr>
		<tr>
			<td><strong>准考证号</strong></td>
			<td colspan="3">${cardNum }</td>
		</tr>
		<tr>
			<td><strong>身份证号</strong></td>
			<td>${zpJlxx.certificatesNum}</td>
			<td><strong>职位编码</strong></td>
			<td>${zpXqxx.gwNum }</td>
		</tr>
		<tr>
			<td><strong>职位名称</strong></td>
			<td colspan="3">${zpXqxx.gwName }/${zpXqxx.depName }</td>
		</tr>
		<tr>
			<td><strong>考场编号</strong></td>
			<td>${examRoom.num }</td>
			<td><strong>考场名称</strong></td>
			<td>${examRoom.name }</td>
		</tr>
		<tr>
			<td><strong>面试时间</strong></td>
			<td colspan="4">${dateString }</td>
		</tr>
		<tr>
			<td><strong>面试候考地点</strong></td>
			<td colspan="4">${examRoom.remark }</td>
		</tr>
	</table>
	<table width="620" border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td height="60" align="right" valign="bottom" style="padding-right:50px; font-size:14px; line-height:22px;">成都医学院（筹）&nbsp;&nbsp;<br />
				公开招聘领导小组办公室</td>
		</tr>
	</table>
	<br />
	<h2>考生考试纪律及要求</h2>
	<div class="text">
		<p>1、考生应持有效证件按规定时间到达候考室候考。逾期未到者，视为自动放弃。证件不符合规定要求的，取消考试资格。</p>
		<p>2、考生应听从考务工作人员的安排，遵守考试规则和考场纪律，考试及候考期间应关闭通信工具。考生需要入厕时，应向考务工作人员报告并由考务工作人员陪同。</p>
		<p>3、考生在考试时不得透露本人姓名等个人基本信息以及准考证号等可能影响考官公正评价的其它内容，必须按照主考官的指令在规定的时间进行准备和答题，考试时间结束时必须立即停止答题。</p>
	</div>
</div>
<div class="control" style="text-align: center; margin-top: 1em">
			<input value=" 打 印 " onclick="print()" type="button"
				class="l-button l-btn2" />
		</div>                                                      
</body>
</html>
