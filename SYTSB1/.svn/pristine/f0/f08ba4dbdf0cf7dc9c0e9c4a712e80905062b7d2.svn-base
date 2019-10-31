<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
var com_ids = "${param.com_ids}";
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
		            url: "functionTaskInfo/finishs.do?ids="+ids,
		            data: data,	//JSON.stringify(json)把json转化成字符串
		            cache:false,    
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		            success: function (data, stats) {
		            	$("body").unmask();
		                if (data["success"]) {
		                	top.$.notice("保存成功！");
		               		api.close();
		                	api.data.window.refreshGrid();
		                } else {
		                	$.ligerDialog.error(data.msg);
		                }
		            },
		            error: function (data) {
		            	$("body").unmask();
		                $.ligerDialog.error(data.msg);
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
				<div>完成情况</div>
			</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">质管部确认完成情况：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							name="zlb_desc" id="zlb_desc" rows="3" cols="25"
							class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							name="zlb_remark" id="zlb_remark" rows="3" cols="25"
							class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
