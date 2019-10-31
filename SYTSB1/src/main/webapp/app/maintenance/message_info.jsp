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
	            { text:'发送', id:'save',icon:'save', click:sendMsg},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(res){
	        }
	    });
	    
	    function sendMsg(){
	    //验证表单数据
			if($('#formObj').validate().form()){
				$("body").mask("正在保存数据，请稍后！");
		    	var formData = $("#formObj").getValues();
				var data = {};
				data = $.ligerui.toJSON(formData);
				var send_msg_type =$('#send_msg_type').ligerGetRadioGroupManager().getValue();
		        $.ajax({
		            url: "maintenance/info/sendMsg.do?send_msg_type="+send_msg_type,
		            data: data,	//JSON.stringify(json)把json转化成字符串
		            cache:false,    
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		            success: function (data, stats) {
		            	$("body").unmask();
		                if (data["success"]) {
		                	top.$.notice("发送成功！");
		               		api.close();
		                	//api.data.window.refreshGrid();
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
		<fieldset class="l-fieldset" Style="width: 35%;margin-left: 30%">
			<legend class="l-legend">
				<div>发送临时消息</div>
			</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">手机号码：</td>
					<td class="l-t-td-right"><input name="mobile" id="mobile"
						type="text" ltype="text" validate="{required:true,maxlength:11}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">发送内容：</td>
					<td class="l-t-td-right" ><textarea
							name="content" id="content" rows="3" cols="25"
							class="l-textarea" validate="{required:true}"></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">发送信息：</td>
					<td class="l-t-td-right" ><input type="radio"
						name="send_msg_type" id="send_msg_type" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'微信', id:'1' }, { text:'短信', id:'2' }, { text:'微信和短信', id:'3' }] }" />
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
