<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head pageStatus="${param.pageStatus}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<title>设置文件重要等级</title>
<script type="text/javascript">
//jQuery页面载入事件

var beanData;
document.onkeydown=function(event){
    var e = event || window.event || arguments.callee.caller.arguments[0];           
     if(e && e.keyCode==13){ // enter 键
    	 save();
    }
};
$(function() {

	//配置资源选择器

	$("#formObj").initForm({
		toolbar : [ {
			text : '保存',
			id : 'save',
			icon : 'save',
			click : save
		}, {
			text : '关闭',
			id : 'close',
			icon : 'cancel',
			click : function() {
				api.close();
			}
		} ],
		toolbarPosition : "bottom",
		success : function(responseText) {//处理成功
			if (responseText.success) {
				top.$.notice("密码修改成功 ");
				api.data.window.Qm.refreshGrid();
				api.close();
			} else {
				$.ligerDialog.error('密码修改失败：' + responseText.msg);
			}
		}
	});
	
});
function save(){
	if ($("#formObj").validate().form()) {
		var oldPassWord = $('#old_hidden_password').val();
		var newPassWord = $('#new_hidden_password').val();
		$.post("resourceSpace/updateSpaceHiddenPassword2.do",{spaceId:api.data.spaceId,opassword:oldPassWord,password:newPassWord},function(res){
			if(res.success){
				top.$.notice("密码修改成功 ");
				api.close();
			}else{
				 $.ligerDialog.error('密码修改失败:'+res.msg);
			}
			
		})
	}
}


</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" >
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">旧密码：</td>
				<td colspan="3" class="l-t-td-right">
					<input type="text" name="old_hidden_password" id="old_hidden_password" ltype="text" class="{required:true,maxlength:10}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">新密码：</td>
				<td colspan="3" class="l-t-td-right">
					<input type="text" name="new_hidden_password" id="new_hidden_password" ltype="text" class="{required:true,maxlength:10}"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>