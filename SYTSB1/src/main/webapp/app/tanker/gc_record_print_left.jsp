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

var pcount=0;
function printAll()
{
	var tr=getInput('table_detail_big').rows;
	var trLen=tr.length;
	if (trLen>pcount) {
		var trAObj = tr[pcount].cells(0).children(0).children(0).children(0).children(0);
		var trAObjOldHref=trAObj.href;
		trAObj.href=trAObj.href+"&printout=yes&info_id=${param.id}";
		//alert(trAObj.href);
		//获取打印份数
		var trPobj = tr[pcount+1].cells(1).children(0);
		trAObj.href = trAObj.href+"&printcopies="+trPobj.value;
		//获取打印页码
		var trPage = tr[pcount+1].cells(3).children(0);
		trAObj.href = trAObj.href+"&printpages="+trPage.value;
		trAObj.click();
		trAObj.href=trAObjOldHref;
		
		pcount=pcount+2;
	}
	else
	{
		//var trAObj=tr[pcount-1].cells(0).children(0).children(0).children(0).children(0);
		//trAObj.click();
		resetPrint();
		alert("打印信息全部发送到打印机！");
		//parent.close();
	}
}

function resetPrint(){
	pcount = 0;
}

function changePrintCopies(valuex) {
	
	var len = getInput("_printcopie").length;
	for(var i = 0 ; i < len ; i++){
		getInput("_printcopies"+i).value=valuex;
	}
}

function changePrintPages(valuex) {
	var len = getInput("_printpage").length;
	for(var i = 0 ; i < len ; i++){
		getInput("_printpages"+i).value=valuex;
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
			<td class="d_title">打印份数</td>
			<td class="d_input" >
				<input type="text" id="printcopies" value="" class="ipt_01" onchange="changePrintCopies(this.value)">
			</td>
		</tr>
		<tr class="d_tr">
			<td class="d_title">打印页码</td>
			<td class="d_input" >
				<input type="text" id="printpages" value="" class="ipt_01" onchange="changePrintPages(this.value)">
			</td>
		</tr>
	</table>
	<div style="width:278px; height:610px; overflow:scroll;">
	<table border="0" cellpadding="0" cellspacing="0" id="table_detail_big">
			<%
				List<Map<String, Object>> mapList = (List<Map<String, Object>>)request.getSession().getAttribute("mapList");
				for (int i = 0; i < mapList.size(); i++) {
					Map<String, Object> map = mapList.get(i);
			%>
			<tr>
				<td colspan='4' align="left">
					<div><H5><LI><a id="_href<%=i%>" href="report/item/record/doGcPrint.do?id=<%=map.get("id")%>&report_id=<%=map.get("report_id")%>&opid=<%=i%>" target=right><%=i+1%>、<%=map.get("ysjl_name")%><br>&nbsp;&nbsp;&nbsp;&nbsp;(<%=map.get("report_sn")%>)</a></H5></div><span class="op1" id="_opid<%=i%>"></span>
				</td>
			</tr>
			<tr class="d_tr">
				<td class="d_title">份数</td>
				<td class="d_input" >
					<input type="text" id="_printcopies<%=i%>" name="_printcopie" value="<%=map.get("printcopies")%>" class="ipt_01" >
				</td>
				<td class="d_title">页码</td>
				<td class="d_input" >
					<input type="text" id="_printpages<%=i%>" name="_printpage" value="<%=map.get("printpages")%>" class="ipt_01" >
				</td>
			</tr>
			<%}%>
	</table>
	</div>
</div>
</body>

