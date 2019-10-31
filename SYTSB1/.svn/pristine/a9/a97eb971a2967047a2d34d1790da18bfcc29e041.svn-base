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
	            { text:'保存', id:'save',icon:'save', click:save},
	          
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(res){
	        }
	    });

	    function save(){
	    	var id = "${param.id}";
	    	var formData = $("#formObj").getValues();
			var data = {};
			data = $.ligerui.toJSON(formData);
	        $.ajax({
	            url: "report/draw/modify.do?id="+id,
	            data: data,	//JSON.stringify(json)把json转化成字符串
	            cache:false,    
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (data, stats) {
	                if (data["success"]) {
	                	top.$.notice("修改成功！");
	               		api.close();
	                	api.data.window.refreshGrid();
	                } else {
	                	$.ligerDialog.error(data.msg);
	                }
	            },
	            error: function (data) {
	                $.ligerDialog.error(data.msg);
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
			<div>报告领取记录</div>
		</legend>
	<table width="100%">		
		<tr>
			<td class="l-t-td-left">领取人：</td>
			<td class="l-t-td-right">
				<input name="pulldown_op" id="pulldown_op" type="text" ltype='text' validate="{maxlength:20}" />
			</td>
			<td class="l-t-td-left">联系电话：</td>
			<td class="l-t-td-right">
				<input name="linkmode" id="linkmode" type="text" ltype="text" validate="{maxlength:40}"/>
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">报告书编号：</td>
			<td class="l-t-td-right" colspan="3">
				<input name="report_sn" id="report_sn" type="text" ltype="text" validate="{maxlength:200}"/>
			</td>
		</tr>
	</table>
	</fieldset>
</body>
</html>
