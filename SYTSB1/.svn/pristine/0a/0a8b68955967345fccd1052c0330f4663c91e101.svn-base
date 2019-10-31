<%@page import="java.util.Map"%>
<%@page import="java.util.HashMap"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="/k/kui-base-list.jsp" %>
<title>批量左页面</title>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>

<style type="text/css">
#workaround {width:100%;overflow:;text-align:center;margin:0px 40px 0px 0px;}

</style>

<script type="text/javascript">

	var ids = "${param.id}";
	var acc_ids = "${param.acc_id}";
	var acc_arr = new Array();
	acc_arr = acc_ids.split(",");

	//打印标记。默认false，点击打印按钮后，设置为true，打印完成后回置false;
	var printFlag = false ;
	//是否全部打印完成标记
	var finish = false ;
	var choseItem="";
	$(function(){
		//设置报告显示页面
		<% String viewURL = "app/flow/rtcommon/report_print_doc.jsp";%>
		//单项选择打开事件
		$(".choseItem").click(function(){
			share.data("itemId",$(this).attr("id"));
			//share.date("copie",$(this).next().next().val());
			if($(this).attr("class")=="hasAudit"){
				parent.disableBtn();
			}else{
				parent.ableBtn();
			}
			
			$("a").not(this).parent().css("background","white");
			$(this).parent().css("background","buttonface");
			choseItem = $(this).attr("id");
		});
		
	})
	//标志已操作
	function auditOk(){
		var img = "<img alt=' ' src='k/kui/images/icons/16/accept.png' border='0'>";
		var id = share.data("itemId");
		$("#"+id).prepend(img);
		$("#"+choseItem).attr("class","hasAudit");
		$("#"+id).next().attr("class","hasAudit");
	}
	function allOk(){
		var img = "<img alt=' ' src='k/kui/images/icons/16/accept.png' border='0'>";
		$('a').each(function(){
			$(this).prepend(img);
			$(this).attr("class","hasAudit");
		});
	}
	//初始化
	function init()
	{	
		//默认打开第一个业务报告
		getInput('row0').click();
	}
	//取得标签方法
	function getInput(o)
	{
		var Obj=document.all(o);
		return Obj;
	}
	
	function changePrintCopies(valuex) {
		$(".ipt_02").val(valuex);
		//用下面方式在XP IE8某系版本下无效  note by mr.dawn
		/*var len = getInput("_printcopie").length;
		for(var i = 0 ; i < len ; i++){
			getInput("_printcopies"+i).value=valuex;
		}*/
	}
	
	function printFirset(){
		if(!printFlag){
			assembleObj();
		}
		if(finish){
			alert("已经打印完成，如果重新打印请重新选择后再打印！");
			return ;
		}
		printFlag = true ;
		var printname = $('#printname').val();
		var copies = $('#_printcopies0').val();
		parent.right.doPrintreport(copies);
		
		
		//parent.right.doPrintreport(copies,printname);
	}
	
	function printAll(){
		if(!printFlag){
			assembleObj();
		}
		if(finish){
			alert("已经打印完成，如果重新打印请重新选择后再打印！");
			return ;
		}
		//设置打印状态
		printFlag = true ;
		obj[index].get(0).click();
	}
	function setAlreadyPrint(){
		//首先将当前被索引的对象标记为已打印
		var img = "<img alt=' ' src='k/kui/images/icons/16/accept.png' border='0'>";
		var id = share.data("itemId");
		obj[index].prepend(img);
		obj[index].attr("class","hasAudit");
		//索引增加
		index++;
		//如果索引增加后对象不为空，则继续打印
		if(obj[index]!=null || obj[index] != undefined ){
			printAll();
		} else {//否则，重置打印状态
			printFlag = false ;
			//提交流程
			addReportTransferInfos(ids);
			//提示打印完成
			alert("全部报告已经提交到打印机完成！");
			//打印完成后取消按钮禁用
			parent.ableBtn();
			//打印完成后重置索引
			index = 0 ;
			//标记打印完成标记
			finish = true ;
		}
	}
	//开始为打印装配对象
	var obj = new Array();
	var index = 0 ;
	function assembleObj(){
		$('.choseItem').each(function(index){
			var new_href = $(this).attr("href")+"&print_copies="+$(this).next().next().val();
			$(this).attr("href",new_href);
			obj[index] = $(this) ;
		});
	}
	//结束
	
	
	function addReportTransferInfos(ids){
		$.getJSON("report/transfer/autoCommit.do?ids="+ids, function(resp){
			if (resp["success"]) {
	         	//top.$.dialog.notice({content:'保存成功！'});
	     		//api.close();
	     	}else{
	      		$.ligerDialog.error('提示：' + resp.msg);
	    	}
		})
	}
</script>
</head>
<body  onload="init();">
<div id="workaround"  style="overflow:auto; width:100%; height:100%" >
	<div>
		<h1 align="center">打印配置</h1>
	</div>
	<hr>
	<div>
	<table width="100%">
		<tr class="d_tr">
			<td class="d_title"></td>
			<td class="d_input"></td>
		</tr>
		<%-- <tr class="d_tr">
			<td colspan='2' style="align:center">选择打印机名称：</td>
		</tr>
		<tr class="d_tr">
			<td colspan='2' style="align:center">
				<select style="width:98%; height:18px;"  id="printname" name="printname"  value="" validate="{required:true}">
					<% 
						Map<String,Object> map = (HashMap<String,Object>)request.getAttribute("mapPriter") ;
						if(map.containsKey("PRINTER")){
							String printers = (String)map.get("PRINTER");
							//System.out.println("============"+printers);
							if(printers!=null) {
								if(printers.contains("##")){
									String[] printer = printers.split("##");
									for(int i = 0 ; i < printer.length ; i++){
					%>
							<option value="<%=printer[i]%>" title=""><%=printer[i]%></option>
					<%
									}
								} else {
					%>
								<option value="<%=printers%>" title=""><%=printers%></option>
					<%			
								}
							} else {
					%>
								<option value="" title=""></option>
					<%			
							}
						}
					%>
			</td>
		</tr> --%>
		<tr class="d_tr">
			<td colspan='2' style="align:center">打印快捷填写份数</td>
		</tr>
		<tr class="d_tr">
			<td class="d_input" colspan='2' >
				<input type="text"  style="width:98%; height:18px; text-align: center"  id="printcopies" value="" class="ipt_01" onblur="changePrintCopies(this.value)"/>
			</td>
		</tr>
	</table>
	</div>
	<hr>
	<div style=" overflow:auto; width:100%; " >
	<h1 align="center">报告列表</h1>
	<hr>
	<c:forEach items="${mapList}" var="map" varStatus="count">
		<div align="center" id="infoList">
			<a id="row${count.index}" class="choseItem" href="<%=viewURL%>?is_print=1&re_print
			=${param.re_print}&infoId=${map.id}&flowNodeId=${map.flow_note_id}
			&activityId=${map.activity_id}&fk_report_id=${map.report_id}
			&advance_time=${map.advance_time}&report_sn=${map.item_value}&pdf_att=${map.pdf_att}&pdf_record_att=${map.pdf_record_att}
			&record=${param.record}" target=right>${map.report_name}
				<br>
				<span>${map.item_value}</span>
			</a>
			<br>
			打印份数：<input style="text-align: center" type="text" id="_printcopies${count.index}" name="_printcopie" class="ipt_01 ipt_02"  value="${map.printcopies}"/>
			
		</div><hr>
		
	</c:forEach>
	</div>
</div>
	
</body>
</html>