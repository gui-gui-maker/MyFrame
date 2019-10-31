<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.*" %>
<%@include file="/k/kui-base-list.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/k/kui-base-list.jsp" %>
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title></title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<style type="text/css">
.op1 {}
#workaround {width:100%;overflow:;text-align:center;margin:0px 40px 0px 0px;}
#table_detail_big {width:100%;border:0px solid #FF0000;margin-bottom:20px;}
.ipt_01 {border:0px;border-bottom:1px solid #8CBBDB;}
.d_tr {height:25px;}
.d_title {text-align:right;padding:0px 5px 0px 0px;width:15%;font-size:12px;}
.d_input {width:15%;}
</style>
<script language="javascript">
function init()
{	
	getInput('table_detail_big').rows(0).cells(0).children(0).children(0).children(0).children(0).click();
}

function checkPass(){
	$.ajax({
       	url: "inspectionInfo/basic/reportCheck.do?ids=${param.id}&check=pass",
           type: "POST",
           datatype: "json",
           contentType: "application/json; charset=utf-8",
           success: function (resp) {
           	if(resp.success){
           		$.ligerDialog.alert(resp.msg,'10');
           	}else{
           		$.ligerDialog.alert(resp.msg);
           	}
           },
           error: function (data) {
           	$.ligerDialog.alert(resp.msg);
           }
       });
}
function checkNoPass(){
	parent.checkNoPass();
	/*
	$.ajax({
       	url: "inspectionInfo/basic/reportCheck.do?ids=${param.id}&check=nopass",
           type: "POST",
           datatype: "json",
           contentType: "application/json; charset=utf-8",
           success: function (resp) {
           	if(resp.success){
                top.$.dialog.notice(resp.msg,'10');
           	}else{
           		$.ligerDialog.error(resp.msg);
           	}
           },
           error: function (data) {
           	$.ligerDialog.error(resp.msg);
           }
       });
	*/
}
function getInput(o)
{
	var Obj=document.all(o);
	return Obj;
}

</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init();">

<div id="workaround">
	<div class="item-tm" >
		<div class="l-page-title2 has-icon has-note" style="height: 60px">
			<div class="l-page-title2-div"></div>
			<div class="l-page-title2-text"><h3>报告列表</h3></div>
			<div class="l-page-title2-note" style="text-align:left;margin-left:10px;">查看报告内容请点击下面的链接。</div>
			<div class="l-page-title2-icon"></div>
		</div>
	</div>
	<div style="width:278px; height:610px; overflow:scroll;">
	<table border="0" cellpadding="0" cellspacing="0" id="table_detail_big">
			<%
				List<Map<String, Object>> mapList = (List<Map<String, Object>>)request.getSession().getAttribute("mapList");
				for (int i = 0; i < mapList.size(); i++) {
					Map<String, Object> map = mapList.get(i);
			%>
			<tr>
				<td colspan='4' align="left">
					<div><H5><LI><a id="_href<%=i%>" href="report/item/record/doCheck.do?id=<%=map.get("id")%>&report_id=<%=map.get("report_id")%>&opid=<%=i%>" target=right><%=i+1%>、<%=map.get("ysjl_name")%><br>&nbsp;&nbsp;&nbsp;&nbsp;(<%=map.get("report_sn")%>)</a></H5></div><span class="op1" id="_opid<%=i%>"></span>
				</td>
			</tr>
			<%}%>
	</table>
	</div>
</div>
</body>

