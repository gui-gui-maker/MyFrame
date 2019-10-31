<%@page import="com.khnt.pub.worktask.service.WorktaskManager" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<head pageStatus="${param.status}">
<title>工作流测试-业务视图</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function () {
		$(".nav_tab").ligerLayout({
			topHeight: 30,
			space: 5
		});

		//使用 bpm:ifPer标签判定当前处理环节的流程功能 
		$("#form2").initForm({
			toolbar: [
				{
					text: '保存', icon: 'save', click: function () {
					$("#form2").submit();
				}
				},
				{
					text: '保存并提交', icon: 'submit', click: function () {
					getServiceFlowConfig("test_service_code", "", function (result, data) {
						if (result) {
							$("#form2").attr("action", "pub/bpm/test_service/start.do?flowId=" + data.id);
							$("#form2").submit();
						}
					});
				}
				},
				{
					text: '取消', icon: 'cancel', click: function () {
					api.close();
				}
				}
			],
			success: function (resp) {
				if (resp.success) {
					top.$.notice("保存成功！");
					api.close();
				}
				else {
					top.$.dialog.tips('保存失败！<br />' + resp.msg, 5, "k/kui/images/icons/dialog/32X32/hits.png", null, 0)
				}
			}
		});
	});

	var dataBus = "";
	function setFileType() {
		dataBus = '{"leader":"1"}';
	}
	var stitle = "自动化工作流引擎测试-自动路由选择";

	function doStamped() {
		dataBus = '{"back":"2"}';
	}

	//准备提交流程
	function readySubmit() {
		//选取下一步人员
		selectUnitOrUser(1, 1, "", "", function (data) {
			if (!data || !data.code || data.code.length == 0) {
				$.ligerDialog.alert("请选择下一步人员！");
				return;
			}
			var dataBus = $.ligerui.toJSON(parseUserToJson(data));
			$("#dataBus").val(dataBus);
			$("#form2").submit();
		});
	}

	function parseUserToJson(data) {
		var codeArr = data.code.split(",");
		var nameArr = data.name.split(",");
		var dataBus = {paticipator: []};
		for (var i = 0; i < codeArr.length; i++) {
			dataBus.paticipator.push({id: codeArr[i], name: nameArr[i]});
		}
		return dataBus;
	}
</script>
</head>
<body>
	<form id="form2" action="pub/bpm/test_service/save.do"
		  getAction="pub/bpm/test_service/detail.do?id=${param.id}">
		<input type="hidden" name="dataBus" id="dataBus"/>
		<input type="hidden" name="id" value="${param.id}"/><br/><br/>
		<table border="1" cellpadding="3" cellspacing="0" width=""
			   class="l-detail-table">
			<tr>
				<td class="l-t-td-left">名字：</td>
				<td class="l-t-td-right"><input type="text" name="name"
												ltype='text' validate="{required:true,maxlength:40}"></td>
			</tr>
			<tr>
				<td class="l-t-td-left">状态：</td>
				<td class="l-t-td-right"><input type="text" name="state"
												ltype="text" validate="{required:true}"/></td>
			</tr>
		</table>
	</form>
</body>
</html>
