<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<head pageStatus="${not empty activityId?'modify':param.pageStatus}">
<title>工作流演示-起草</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function () {
		$("#form2").initForm({
			showToolbar:true,
			toolbar: [<c:if test="${empty activityId and param.status!='detail'}">{
				text: '保存', icon: 'save', click: function () {
					$("#form2").submit();
				}
			},{
				text: '提交审批', icon: 'submit', click: readyStart
			},</c:if><c:if test="${not empty activityId}">{
				text: '提交', icon: 'submit', click: submitApp
			},</c:if>
			{
				text: '取消', icon: 'cancel', click: function () {
					api.close();
				}
			}]<c:if test="${param.status!='detail'}">,
			success: function (resp) {
				if (resp.success) {
					top.$.notice("保存成功！");
					api.data.Qm.refreshGrid();
					api.close();
				}
				else {
					$.ligerDialog.error('操作失败！<br />' + resp.msg);
				}
			}</c:if>
		});
	});
	<c:if test="${not empty activityId}">
	function submitApp(){
		$("body").mask("正在处理，请稍后！");
		$.getJSON("demo/bpm/bussiness/submit.do",{
			activityId : "${activityId}",
			dataBus : "",
			signature : 1
		},function(resp){
			if(resp.success){
				top.$.notice("操作成功！",3);
				if(api.data.Qm) api.data.Qm.refreshGrid();
				api.close();
			}else{
				$.ligerDialog.error(resp.msg);
				$("body").unmask();
			}
		});
	}
	</c:if>
	<c:if test="${param.status!='detail'}">
	function readyStart(){
		// 准备启动流程，首先检查申请人的身份
		$.getJSON("demo/bpm/bussiness/readyStart.do?personId=" + $("#person_id").val(), function(resp){
			if(resp.success){
				$("#personType").val(resp.personType);
				//当申请人是部门负责人时，需要选择分管领导来审批
				if(resp.personType!="dm"){
					saveAndSubmit();
				}
				else{//否则，其他类型人员直接提交
					selectPersonAndStart();
				}
			}
		});
	}
	
	function selectPersonAndStart(){
		selectUnitOrUser(1, 0, 'nextPersonId', 'nextPerson',saveAndSubmit);
	}
	
	function saveAndSubmit(){
		getServiceFlowConfig("demo-bpm-leave", "", function (result, data) {
			if (result) {
				$("#form2").attr("action", "demo/bpm/bussiness/saveAndSubmit.do?flowId=" + data.id);
				$("#form2").submit();
			}
		});
	}</c:if>
</script>
</head>
<body>
	<form id="form2" action="demo/bpm/bussiness/save.do"
		  getAction="demo/bpm/bussiness/detail.do?id=${not empty activityId?serviceId:param.id}">
		<input type="hidden" name="personType" id="personType" />
		<input type="hidden" name="nextPerson" id="nextPerson" />
		<input type="hidden" name="nextPersonId" id="nextPersonId" />
		<jsp:include page="leave_detail_form.jsp" />
	</form>
</body>
</html>
