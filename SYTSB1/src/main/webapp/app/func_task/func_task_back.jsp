
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>

<head>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({ //参数设置示例
			toolbar : [ {
				text : '保存',
				//id : 'save',
				icon : 'save',
				click : save
			}, {
				text : '关闭',
				//id : 'close',
				icon : 'cancel',
				click : close
			} ],
			getSuccess : function(res) {
						
			}
		});
	})
		
	function close(){	
		api.close();
	}

	function save(){
		if($('#remarks').val()==null || $('#remarks').val()==undefined || $('#remarks').val()==""){
			top.$.dialog.notice({content:'请填写退回原因！'});
			return;
		}

		var url = "functionTaskInfo/backs.do?ids=${param.ids}";
		//验证表单数据
		if($('#form1').validate().form()){
			var formData = $("#form1").getValues();
			var data = {};
			data = formData;
			 
			$("body").mask("正在保存数据，请稍后！");
			$.ajax({
				url: url,
				type: "POST",
				datatype: "json",
			 	data: {dataStr : $.ligerui.toJSON(data)},
			 	success: function (resp) {
			    	$("body").unmask();
			     	if (resp["success"]) {
			        	if(api.data.window.Qm){
			           		api.data.window.Qm.refreshGrid();
			         	}
			         	top.$.dialog.notice({content:'保存成功'});
			        	api.data.window.Qm.refreshGrid();
			            api.close();
					}else{
			         	$.ligerDialog.error('提示：' + resp.msg);
			      	}
			   	},
				error: function (resp) {
			    	$("body").unmask();
			   		$.ligerDialog.error('提示：' + resp.msg);
			  	}
			});
		}
	}	
	</script>
</head>
<body>
	<form id="form1">
		<table border="1" cellpadding="0" cellspacing="0" width=""
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">退回原因：</td>
				<td class="l-t-td-right" colspan="2"><textarea
						name="remarks" id="remarks" rows="6" cols="25" class="l-textarea" validate="{required:true,maxlength:1000}"></textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
