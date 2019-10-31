<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.*" %>
<%@include file="/k/kui-base-list.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<script type="text/javascript" src="pub/bpm/js/util.js"></script>
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
.d_title {text-align:right;padding:0px 5px 0px 0px;width:25%;font-size:12px;}
.d_input {width:25%;}
</style>
<script language="javascript">
function init()
{	
	getInput('table_detail_big').rows(0).cells(0).children(0).children(0).children(0).children(0).click();
}

var pcount=0;
function printAll()
{
	var tr=getInput('table_detail_big').rows;
	var trLen=tr.length;
	if (trLen>pcount) {
		var trAObj = tr[pcount].cells(0).children(0).children(0).children(0).children(0);
		var trAObjOldHref=trAObj.href;	
		trAObj.href=trAObj.href+"&printout=yes";
		//获取打印份数
		//var trPobj = tr[pcount+1].cells(1).children(0);
		//trAObj.href = trAObj.href+"&printcopies="+trPobj.value;
		trAObj.click();
		trAObj.href=trAObjOldHref;
	}
	else
	{
		//var trAObj=tr[pcount-1].cells(0).children(0).children(0).children(0).children(0);
		//trAObj.click();
		alert("打印信息已全部发送到打印机！");
		parent.close();
	}
	pcount++;
	//pcount=pcount+2;
}

function changePrintCopies(valuex) {
	
	var len = getInput("_printcopie").length;
	for(var i = 0 ; i < len ; i++){
		getInput("_printcopies"+i).value=valuex;
	}
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
	<table>
		<tr class="d_tr">
				<td class="d_title"></td>
				<td class="d_input"></td>
			</tr>
	</table>
	<div style="width:278px; height:610px; overflow:scroll;">
	<table border="0" cellpadding="0" cellspacing="0" id="table_detail_big">
			<%
				List<Map<String, Object>> mapList = (List<Map<String, Object>>)request.getSession().getAttribute("mapList");
			    String flag = request.getAttribute("flag").toString();
			    System.out.println("cecece"+flag);
				for (int i = 0; i < mapList.size(); i++) {
					Map<String, Object> map = mapList.get(i);
			%>
			<tr>
				<td colspan='2'>
					<div><H5><LI><a id="_href<%=i%>" href="report/query/doPrintTags.do?flag=<%=flag%>&id=<%=map.get("id")%>&report_id=<%=map.get("report_id")%>&device_id=<%=map.get("device_id")%>&opid=<%=i%>" target=right><%=i+1%>、<%=map.get("device_name")%><br/>&nbsp;&nbsp;&nbsp;&nbsp;(<%=map.get("device_registration_code")%>)</a></H5></div><span class="op1" id="_opid<%=i%>"></span>
				</td>
			</tr>
			<!-- 
			<tr class="d_tr">
				<td class="d_title">打印份数</td>
				<td class="d_input" >
					<input type="text" id="_printcopies<%=i%>" name="_printcopie" value="<%=map.get("printcopies")%>" class="ipt_01" >
				</td>
			</tr>
			 -->
			<%}%>
	</table>
	</div>
</div>
</body>

