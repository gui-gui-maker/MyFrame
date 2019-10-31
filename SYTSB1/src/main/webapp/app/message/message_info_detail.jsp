<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head pageStatus="${param.status}">
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript">
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	if(resp.success){
					$("#formObj").setValues(resp.data);
				}
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
	            	//api.data.window.refreshGrid();
	            	//api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}
		});

	});	
	
	function close(){
		api.close();
	}	
</script>
</head>
	<body>
		<form name="formObj" id="formObj" method="post"
			getAction="messageHistory/getDetail.do?id=${param.id}">
			<input id="id" name="id"  type="hidden"  />
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						基本信息
					</div>
				</legend>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left">消息类别：</td>
						<td class="l-t-td-right">
							<input type="text" id="type" name="type" ltype="select" validate="{required:false}" ligerui="{
								value:'',
								readonly:true,
								data: [ { text:'文本消息', id:'1' }],
								suffixWidth:'140'
							}" />
						</td>
						<td class="l-t-td-left">文本消息类型：</td>
						<td class="l-t-td-right">
							<input type="text" id="msg_type" name="msg_type" ltype="select" validate="{required:false}" ligerui="{
								value:'',
								readonly:true,
								data: [ { text:'短信', id:'0' }, { text:'微信', id:'1' }],
								suffixWidth:'140'
							}" />
						</td>	
					</tr>
					<tr>
						<td class="l-t-td-left">发送号码：</td>
						<td class="l-t-td-right">
							<input type="text" name="mobile" id="mobile" ltype="text" validate="{required:false}" /> 
						</td>
						<td class="l-t-td-left">发送状态：</td>
						<td class="l-t-td-right">
							<input type="text" id="status" name="status" ltype="select" validate="{required:false}" ligerui="{
								value:'',
								readonly:true,
								data: <u:dict code="SEND_MSG_STATUS"></u:dict>,
								suffixWidth:'140'
							}" />
						</td> 
					</tr>
					<tr>
						<td class="l-t-td-left">发送内容：</td>
						<td class="l-t-td-right" colspan="3">
							<textarea name="content" id="content" rows="2" cols="25" class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
						</td> 								
					</tr>
					<tr>
						<td class="l-t-td-left">操作人：</td>
						<td class="l-t-td-right">
							<input type="text" name="user_name" id="user_name" ltype="text" validate="{required:false}" />	
						</td>
						<td class="l-t-td-left">发送时间：</td>
						<td class="l-t-td-right">
							<input name="send_time"
								type="text" ltype="date" validate="{required:false}"
									ligerui="{initValue:'',format:'yyyy-MM-dd HH:mm:ss'}" id="send_time" />
						</td>								
					</tr>
				</table>
			</fieldset>	
		</form>
	</body>
</html>