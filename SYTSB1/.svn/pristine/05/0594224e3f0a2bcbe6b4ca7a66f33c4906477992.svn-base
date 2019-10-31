<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title></title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<style type="text/css">
html { overflow: scroll; } 
.op1 {}
a:link { text-decoration:underline;} 
#workaround {width:100%;text-align:center;}
#table_detail_big {width:100%;border:0px solid #FF0000;margin-top:10px;}
.d_tr {height:25px;}
.d_title {text-align:center;padding:5px 0px 5px 0px;width:25%;font-size:12px;}
</style>
<%@ include file="/k/kui-base-form.jsp"%>
<script language="javascript">
$(function() {
	init();
})

function init(){	
	getInput('table_detail_big').rows(0).cells(0).children(0).children(0).children(0).children(0).click();
}

function saveAll(){
	var tr=getInput('table_detail_big').rows;
	var trLen=tr.length;
	var info_ids = "";
	for(var i=0;i<trLen;i++){
		var trAObj = tr[i].cells(0).children(0).children(0).children(0).children(0);
		var hasCheck = tr[i].cells(0).children(1).innerHTML;	// 是否已经校核（已经校核的原始记录会有图片标记）
		// 一键校核时，已经校核的原始记录不再进行校核操作
		if(hasCheck.length<1){
			if(info_ids.length>0){
				info_ids += ",";
			}
			info_ids += trAObj.id;
		}		
	}

	if(info_ids==""){
		alert("亲，所选原始记录已校核，不需要再校核了哦！");
		parent.close();
		return;
	}

	
	var data = new Object();
	data.ids = info_ids;
	data.opid = '';
	parent.save(data);
}

function showSaveBtn(){
	parent.showSaveBtn();
}

function getInput(o)
{
	var Obj=document.all(o);
	return Obj;
}

</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" ><!-- onload="init();" -->
<div id="workaround">
	<div class="item-tm" >
		<div class="l-page-title2 has-icon has-note" style="height: 60px">
			<div class="l-page-title2-div"></div>
			<div class="l-page-title2-text"><h3>原始记录列表</h3></div>
			<div class="l-page-title2-note" style="text-align:left;margin-left:10px;">查看原始记录的内容请点击下面的链接。</div>
			<div class="l-page-title2-icon"></div>
		</div>
	</div>
	<div style="width:278px; height:610px; overflow:scroll;">
	<table border="0" cellpadding="0" cellspacing="0" id="table_detail_big" width="278px" >
		<%
			List<Map<String, Object>> mapList = (List<Map<String, Object>>)request.getSession().getAttribute("mapList");
			for (int i = 0; i < mapList.size(); i++) {
				Map<String, Object> map = mapList.get(i);
				%>
				<tr height='38px'>
					<td colspan='2' align="left" >
						<div style="margin-left:10px;"><H4><LI><a id="<%=map.get("id")%>" onclick="showSaveBtn();" href="report/item/record/doGcChecks.do?id=<%=map.get("id")%>&report_id=<%=map.get("report_id")%>&opid=<%=i%>" target=right><%=i+1%>、<%=map.get("ysjl_name")%><br>&nbsp;&nbsp;&nbsp;&nbsp;(<%=map.get("internal_num")%>)</a></H4></div>
						<span class="op1" id="_opid<%=i%>"></span>
					</td>
				</tr>
				<%
			}
		%>
	</table>
	</div>
</div>
</body>

