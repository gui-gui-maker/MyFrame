<%@page import="com.khnt.pub.worktask.service.WorktaskManager" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<head pageStatus="detail">
<title>工作流测试-业务视图</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function () {
		//使用 bpm:ifPer标签判定当前处理环节的流程功能 
		$("#toolbar1").ligerButton({
			items: [
				{
					text: '查看过程图', icon: 'follow', click: function () {
					trackProcess("${processId}");
				}
				},
				"-",
				{
					text: '查看意见', icon: 'follow', click: function () {

				}
				},
				"-",
				{
					text: '取消', icon: 'cancel', click: function () {
					api.close();
				}
				}
			]
		});
	});
	
</script>
<style type="text/css">
.l-t-td-right span {
	color:red;
	font:16px 'Courier New';
}
</style>
</head>
<body>
	<div class="scroll-tm">
		<form id="form2" action="pub/bpm/test_service/save.do"
			  getAction="pub/bpm/test_service/detail.do?id=${serviceId}">
			<input type="hidden" name="id" value="${serviceId}"/><br/>
			<br/>
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
				<tr>
					<td class="l-t-td-left">流程ID：</td>
					<td class="l-t-td-right">${processId} | 获取方式：<span>&#36;{processId}</span></td>
				</tr>
				<tr>
					<td class="l-t-td-left">环节ID：</td>
					<td class="l-t-td-right">${activityId} | 获取方式：<span>&#36;{activityId}</span></td>
				</tr>
				<tr>
					<td class="l-t-td-left">业务ID：</td>
					<td class="l-t-td-right">${serviceId} | 获取方式：<span>&#36;{serviceId}</span></td>
				</tr>
				<tr>
					<td class="l-t-td-left">任务状态：</td>
					<td class="l-t-td-right">${worktaskStatus} | 获取方式：<span>&#36;{worktaskStatus}</span></td>
				</tr>
				<tr>
					<td class="l-t-td-left">自定义：</td>
					<td class="l-t-td-right">${param.testParam1} | 获取方式：<span>&#36;{param.testParam1}</span></td>
				</tr>
			</table>
		</form>
	</div>
	<div class="toolbar-tm">
		<div class="toolbar-tm-bottom">
			<div id="toolbar1"></div>
		</div>
	</div>
</body>
</html>
