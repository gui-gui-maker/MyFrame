<%@page import="java.util.Map"%>
<%@page
	import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>科研项目申请书审核</title>
<%@ include file="/k/kui-base-form.jsp"%>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
	CurrentSessionUser sessionUser = (CurrentSessionUser) SecurityContextHolder.getContext().getAuthentication()
			.getPrincipal();
	String user = sessionUser.getSysUser().getName();
	String apply_type = request.getParameter("apply_type");
%>
<SCRIPT type=text/javascript src="app/common/ueditor/ueditor.config.js"></SCRIPT>
<SCRIPT type=text/javascript src="app/common/ueditor/ueditor.all.min.js"></SCRIPT>
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tr").hide();
		$("#formObj").initForm({
			success : function(response) {//处理成功
				if (response.success) {
					top.$.dialog.notice({
						content : "保存成功！"
					});
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败！<br/>' + response.msg);
				}
			},
			getSuccess : function(response) {
				if (response.attachs != null && response.attachs != undefined)
					showAttachFile(response.attachs);
			},
			toolbar : [ {
				text : "保存",
				icon : "save",
				click : function() {
					//表单验证
					if ($("#formObj").validate().form()) {
						$("#formObj").submit();
						
					}
				}
			}, {
				text : "关闭",
				icon : "cancel",
				click : function() {
					api.close();
				}
			} ],
			toolbarPosition : "bottom"
		});

	});

	function closewindow() {
		api.close();
	}
	function auditJg(flag) {
		if (flag != "1") {
			$("#tr").show();
		} else {
			$("#tr").hide();
		}
	}
	$(document).ready(function() {
		$.ajax({
			url : "tjy2ScientificResearchAction/auditDetails.do?id=${param.id}&userid=<%=sessionUser.getId()%>",
			dataType : "json",
			type : "post",
			async : false,
			success : function(data) {
				$("#peoject_name").text(
						data["peoject_name"]);
				$("#project_head").text(
						data["project_head"]);
				if (data["project_money"] != null) {
					$("#project_money").text(function() {
							return data["project_money"]+ "万元";
					});
				} else {
					$("#project_money").text(function() {
							return "";
					});
				}
				if(data["project_audit"]==null){
					$("#project_audit").val("<%=user%>");
				}else{
					$("#project_audit").val(data["project_audit"]);
				}
				if(data["project_audit_date"]==null){
					$("#project_audit_date").val("<%=nowTime%>");
				}else{
					$("#project_audit_date").val(data["project_audit_date"]);
				}
				if(data["grade1"]!=null){
					$("#item1").val(data["grade1"]);
				}
				if(data["grade2"]!=null){
					$("#item2").val(data["grade2"]);
				}
				if(data["grade3"]!=null){
					$("#item3").val(data["grade3"]);
				}
				if(data["grade4"]!=null){
					$("#item4").val(data["grade4"]);
				}
				if(data["grade5"]!=null){
					$("#item5").val(data["grade5"]);
				}
				if(data["grade6"]!=null){
					$("#item6").val(data["grade6"]);
				}
				if(data["grade7"]!=null){
					$("#item7").val(data["grade7"]);
				}
				if(data["grade8"]!=null){
					$("#item8").val(data["grade8"]);
				}
				if(data["grade9"]!=null){
					$("#item9").val(data["grade9"]);
				}
				if(data["grade10"]!=null){
					$("#item10").val(data["grade10"]);
				}
			}
		});
});
</script>
<style>
th, td {
	margin: 0;
	padding: 0;
	border: 1px solid #000000;
	height: 65px;
	font-size: 16px
}

td input {
	height: 65px;
	text-align: center;
}

table {
	border-collapse: collapse;
	border: 1px solid #000000;
	text-align: center;
}

input,textarea{
	margin: 0;
	padding: 0;
	font: 16px Verdana, Helvetica, Arial, sans-serif;
}
.text{
	text-align: left;
	text-indent: 2em
}
</style>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="tjy2ScientificResearchAction/auditPass.do?id=${param.id}">
		<input type="hidden" name="id" id="id" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>科研项目申请书审核</div>
			</legend>
			<div style="border: 1px solid #000000">
				<table cellpadding="0" cellspacing="0" class="l-detail-table">
					<tr>
						<th colspan="4"><h1 style="text-align: center">四川省特种设备检研究院科研项目立项网络评审表</h1></th>
					</tr>
					<tr>
						<td>项目名称</td>
						<td colspan="3" id="peoject_name"></td>
					</tr>
					<tr>
						<td>项目负责人</td>
						<td id="project_head"></td>
						<td>申请经费</td>
						<td id="project_money">万元</td>
					</tr>
					<tr>
						<td colspan="3">评议内容</td>
						<td>评分</td>
					</tr>
					<tr>
						<td colspan="4"
							style="text-align: center; font-size: initial; font-weight: bold;">评分等级：（一般：0-4；较好：5-8；很好：9-10）</td>
					</tr>


					<tr>
						<td>项目意义</td>
						<td colspan="2" class="text">是否以研究质检技术为核心，以质检业务工作中急需解决的关键性技术问题和疑难问题为重点</td>
						<td ><input type="text" id="item1" name="item1"/></td>
					</tr>
					<tr>
						<td>先进性</td>
						<td colspan="2" class="text">项目研究内容是否明确，技术水平是否具备先进性</td>
						<td><input type="text" id="item2" name="item2"/></td>
					</tr>
					<tr>
						<td>创新性</td>
						<td colspan="2" class="text">国内外同类研究现状分析及存在的问题（含查新结果）</td>
						<td><input type="text" id="item3" name="item3"/></td>
					</tr>
					<tr>
						<td>难易程度</td>
						<td colspan="2" class="text">是涉及学科面、技术复杂程度、工作难度</td>
						<td><input type="text" id="item4" name="item4"/></td>
					</tr>
					<tr>
						<td>技术路线</td>
						<td colspan="2" class="text">是否具有科学性、合理性、可行性</td>
						<td><input type="text" id="item5" name="item5"/></td>
					</tr>
					<tr>
						<td>项目效益</td>
						<td colspan="2" class="text">经济效益、社会效益情况分析</td>
						<td><input type="text" id="item6" name="item6"/></td>
					</tr>
					<tr>
						<td>考核指标</td>
						<td colspan="2" class="text">年度计划内容编写、考核指标是否充实、恰当、可行</td>
						<td><input type="text" id="item7" name="item7"/></td>
					</tr>
					<tr>
						<td>前期研究</td>
						<td colspan="2" class="text">有关的研究前期准备、基础和实验条件情况 实现预期目标的可能性</td>
						<td><input type="text" id="item8" name="item8"/></td>
					</tr>
					<tr>
						<td>经费预算</td>
						<td colspan="2" class="text">是否符合实际</td>
						<td><input type="text" id="item9" name="item9"/></td>
					</tr>
					<tr>
						<td>项目成员</td>
						<td colspan="2" class="text">科研能力和技术水平是否与拟申报立项项目相适应</td>
						<td><input type="text" id="item10" name="item10"/></td>
					</tr>
					<tr>
						<td colspan="3">总分(百分制)</td>
						<td><input type="text" id="total" name="total" readonly="readonly"/></td>
					</tr>
					<tr>
						<td>评审意见（项目整体评价）：</td>
						<td colspan="3"><textarea rows="3" cols="20" id="project_opinion" name="project_opinion">评审意见！</textarea></td>
					</tr>
					<tr>
						<td colspan="1">专家评审：</td>
						<td><input type="text" id="project_audit" name="project_audit"/></td>
						<td colspan="1">日期</td>
						<td><input type="text" id="project_audit_date" name="project_audit_date"/></td>
					</tr>
					<!-- <tr>
					<td class="l-t-td-left">审核意见：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea id="remark" rows="2" cols="25" name="remark" class="l-textarea" validate="{maxlength:256}" ></textarea>
					</td>	
				</tr> -->
				</table>
			</div>
		</fieldset>
	</form>
	<%-- <form name="formObj" id="formObj" method="post" action="tjy2ScientificResearchAction/auditPass.do?id=${param.id}">
		<input type="hidden" name="id" id="id"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>科研项目申请书审核</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">审核意见：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea id="remark" rows="2" cols="25" name="remark" class="l-textarea" validate="{maxlength:256}" ></textarea>
					</td>	
				</tr>
			</table>
		</fieldset>
	</form>
</div> --%>
</body>
</html>
