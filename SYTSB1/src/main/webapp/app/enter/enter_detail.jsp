<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/enter/device_list.js"></script>
<script type="text/javascript">
var pageStatus = "${param.status}";		
// 设备类别
var deviceType = <u:dict code="device_classify"/>
var  flag = ${param.flag}

$(function() {
	createDeviceGrid();
	$("#formObj").initForm({
		/*
		  toolbar:[
                   {text:"保存", icon:"save", click:function(){
                           submitForm();
                       }
                   },
	            	{text:"取消", icon:"cancel", click:function(){
			        	api.close();
		    }}
               ],
        */
        toolbarPosition :"bottom",
		getSuccess : function(resp) {
			deviceGrid.loadData({
				Rows : resp.data["deviceList"]
			});
		},
		success : function(resp) {//处理成功
			if (resp.success) {
				top.$.notice("保存成功！");
				if(flag=='2'){
					api.data.window.submitAction();//执行列表页面函数
					api.close();
				}else{
					api.data.changeWindow.change(resp.id,resp.com_name,resp.com_address);
					api.close();
				}
				
			} else {
				if (resp.rep == "rep")
					$.ligerDialog.alert('用户编号或者登录名重复，请重填？');
				else {
					$.ligerDialog.error('保存失败' + resp)
				}
			}
		}
	});
	/*
	if ("${param.status}" == "add") {
		$.get("rbac/user/newUserCode.do", function(response) {
			if (response.success) {
				$("#userCode").val(response.data.value);
			} else {
				$.ligerDialog.error("获取用户编号发生错误，请稍后再试，或者请联系系统维护人员!");
			}
		});
	}
	*/
});
</script>
</head>
<body>
<form name="formObj" id="formObj" method="post" Action="enter/basic/saveBasic.do"
			getAction="enter/basic/getDetail.do?id=${param.id}">
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>企业基本信息</div>
		</legend>
		<%@ include file="enter_base_info.jsp"%>
	</fieldset>	
	<c:if test="${'detail' eq param.status}">
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>设备信息</div>
		</legend>
		<div id="deviceList"></div>
	</fieldset>
	</c:if>
</form>
</body>
</html>