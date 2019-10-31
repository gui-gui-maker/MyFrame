<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
	<%
		String curDate = DateUtil.getCurrentDateTime();
	%>
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
	    //验证表单数据
			if($('#formObj').validate().form()){
				$("body").mask("正在保存数据，请稍后！");
		    	var ids = "${param.ids}";
		    	var formData = $("#formObj").getValues();
				var data = {};
				data = $.ligerui.toJSON(formData);
				$.ajax({
			   		url: "inspectionInfo/basic/reportCheck.do?ids=${param.id}&check=nopass",
			     	type: "POST",
			   		datatype: "json",
			    	contentType: "application/json; charset=utf-8",
			     	success: function (resp) {
			     		$("body").unmask();
			        	if(resp.success){
			                if(api.data.window.Qm){
		                		api.data.window.Qm.refreshGrid();
		                	}
			                top.$.dialog.notice(resp.msg,'10');
		                    api.data.window.api.data.window.Qm.refreshGrid();
		                    api.data.window.api.close();
		                    api.close();
			           	}else{
			           		$.ligerDialog.error(resp.msg);
			           	}
			  		},
			 		error: function (data) {
			 			$("body").unmask();
			   			$.ligerDialog.error(resp.msg);
			    	}
				});
		    }
	    }

	   function close(){
	        api.close();
	    }
	});
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>校核不通过</div>
			</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							name="report_confirm_remark" id="report_confirm_remark" rows="3" cols="25"
							class="l-textarea" validate="{required:true,maxlength:1000}"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
