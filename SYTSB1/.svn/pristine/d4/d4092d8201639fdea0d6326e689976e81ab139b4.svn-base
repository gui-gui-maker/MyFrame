<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<head pageStatus="modify">
<title>工作流演示-浏览</title>
<%@include file="/k/kui-base-form.jsp" %>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function () {
		api.size(650,$(top).height()-100);
		api.position($(top).width()/2-325,30);
		$("#app_form").transform("detail");
		$("#form2").initForm({
			showToolbar: true,
			toolbar: [{
				text: '查看流程', icon: 'follow', click: function () {
					trackServiceProcess("${empty param.tid?serviceId:param.tid}");
				}
			},{
				text: '取消', icon: 'cancel', click: function () {
					api.close();
				}
			}],
			getSuccess:function(resp){
				$("#app_form").setValues(resp.data);
			}
		});
		$.getJSON("bpm/opinion/serviceOpinion.do?serviceId=${empty param.tid?serviceId:param.tid}",function(resp){
			$("#app_records").ligerGrid({
				columns:[
					{display:'审批人',name:'signerName',align:'left',width:100},
					{display:'审批时间',name:'signDate',align:'center',width:130},
					{display:'审批意见',name:'opinion',align:'left',width:350}
				],
				usePager: false,
				data:{Rows:resp.data},
			});
		});
	});
</script>
</head>
<body>
	<form id="form2" action="demo/bpm/bussiness/save.do"
		  getAction="demo/bpm/bussiness/detail.do?id=${empty param.tid?serviceId:param.tid}">
		<fieldset class="l-fieldset">
			<legend class="l-legend"><span>申请信息</span></legend>
			<jsp:include page="leave_detail_form.jsp" />
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend"><span>审批记录</span></legend>
			<div id="app_records"></div>
		</fieldset>
	</form>
</body>
</html>
