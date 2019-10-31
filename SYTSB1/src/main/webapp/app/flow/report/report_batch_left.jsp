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
	var acc_ids = "";
	var advance_time = "";
	var check_time = "";
	var enter_time = "";
	for(var i=0;i<trLen;i++){
		var trAObj = tr[i].cells(0).children(0).children(0).children(0).children(0);
		var hasCheck = tr[i].cells(0).children(1).innerHTML;	// 是否已经审核或签发（已经审核或签发的报告会有图片标记）
		// 一键审核或签发时，已经审核或签发的报告不再进行审核或签发操作
		if(hasCheck.length<1){
			if(info_ids.length>0){
				info_ids += ",";
			}
			info_ids += trAObj.id;
			
			// 获取业务信息检验时间、录入时间、审核时间、签发时间
			var times = tr[i].cells(0).children(2);	
			var temp = times.value.split(",");
			if(advance_time == ""){
				advance_time = temp[0];
			}
			if(check_time == ""){
				check_time = temp[1];
			}
			if(enter_time == ""){
				enter_time = temp[2];	
			}
			
			if(acc_ids.length>0){
				acc_ids += ",";
			}	
			acc_ids += 	temp[3];
		}		
	}

	if(info_ids==""){
		alert("亲，所选报告已审核，不需要再审核了哦！");
		parent.close();
		return;
	}
	var type = '${param.type}';
	/*
	if(type == '04'){
		if(hasCheck!=""){
			alert("亲，有报告已审核，不能进行批量审核操作了哦，请重新选择！");
			return;
		}
	}else{
		if(hasCheck!=""){
			alert("亲，有报告已签发，不能进行批量签发操作了哦，请重新选择！");
			return;
		}
	}*/
	
	var data = new Object();
	data.acc_id = acc_ids;
	data.flow_num = '${param.flow_num}';
	data.type = type;
	data.device_type = '${param.device_type}';
	data.device_sort_code = '${param.device_sort_code}';
	data.check_flow = '${param.check_flow}';
	data.org_id = '${param.org_id}';
	data.ids = info_ids;
	data.opid = '';
	data.advance_time = advance_time;
	data.check_time = check_time;
	data.enter_time = enter_time;
	data.op_type = '批量'; 	// 操作方式
	data.isBatch = '1'; 	// 1：批量操作
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
			<div class="l-page-title2-text"><h3>报告列表</h3></div>
			<div class="l-page-title2-note" style="text-align:left;margin-left:10px;">查看报告内容请点击下面的链接。</div>
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
						<div style="margin-left:10px;"><H4><LI><a id="<%=map.get("id")%>" onclick="showSaveBtn();" href="report/batch/doCheck.do?&type=${param.type}&device_type=${param.device_type}&id=<%=map.get("id")%>&report_id=<%=map.get("report_id")%>&flow_note_id=<%=map.get("flow_note_id")%>&activity_id=<%=map.get("activity_id")%>&opid=<%=i%>" target=right><%=i+1%>、<%=map.get("report_name")%><br>&nbsp;&nbsp;&nbsp;&nbsp;(<%=map.get("item_value")%>)</a></H4></div>
						<span class="op1" id="_opid<%=i%>"></span>
						<input type="hidden" id="infos<%=i%>" name="infos" class="infos" value="<%=map.get("advance_time")%>,<%=map.get("check_time")%>,<%=map.get("enter_time")%>,<%=map.get("activity_id")%>" />
					</td>
				</tr>
				<%
			}
		%>
	</table>
	</div>
</div>
</body>

