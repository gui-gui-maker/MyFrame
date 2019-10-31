<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'同步', id:'save',icon:'save', click:saveClose },          
	            { text:'关闭', id:'close',icon:'cancel', click:close }
	        ],
	        getSuccess:function(res){ 
	        }
	    });	
	    
	    function saveClose(){
	    	$("body").mask("正在同步数据，请稍后！");
	    	var infoIds = "${param.ids}";
	    	//var formData = $("#formObj").getValues();
			var data = {};
			//data = $.ligerui.toJSON(formData);
	        $.ajax({
	            url: "inspectionInfo/basic/updateDevices.do?infoIds="+infoIds,
	            data: data,	//JSON.stringify(json)把json转化成字符串
	            cache:false,    
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (resp) {
	                if (resp["success"]) {
	                	$("body").unmask();
	                	top.$.notice("设备同步成功！");
	                	api.data.window.submitAction();
	                	api.close();
	                } else {
	                	$("body").unmask();
	                	$.ligerDialog.error(resp.msg);
	                }
	            },
	            error: function (resp) {
	            	$("body").unmask();
	                $.ligerDialog.error(resp.msg);
	            }
	        });
	    }

	   function close(){
	        api.close();
	    }
	});	
</script>
</head>
<body>

<form name="formObj" id="formObj" method="post" >
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>同步设备信息</div>
		</legend>
	<table width="100%">
		<tr>
			<td class="l-t-td-left">报告书覆盖&nbsp;&nbsp;&nbsp;&nbsp;<br/>设备信息内容：</td>
			<td class="l-t-td-right">
				<div class="mdy">
				使用单位代码、单位内部编号、金属二维码、使用登记证号、设备型号、出厂编号、安全管理员、安全管理联系电话、设备使用地点、制造日期、施工单位许可证编号。
				</div>
			</td>	
		</tr>	
	</table>
	</fieldset>
</body>
</html>
