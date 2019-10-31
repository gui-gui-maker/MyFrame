
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="/pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	//jQuery页面载入事件

	document.onkeydown=function(event){
	    var e = event || window.event || arguments.callee.caller.arguments[0];           
	     if(e && e.keyCode==13){ // enter 键
	    	// checkPassWord();
	    }
	};
	$(function() {
		
		//配置资源选择器

		$("#formObj").initForm({
			toolbar : [ {
				text : '确定',
				id : 'save',
				icon : 'save',
				click : checkPassWord
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
				
			}
		});

	});
	function checkPassWord(){
		
		if ($("#formObj").validate().form()) {
			var passWord = $("#passWord").val();
			$.post("resourceSpace/checkPassWord.do",{userId:api.data.userId,spaceType:1,password:passWord},function(res){
				if(res.success){
					api.data.window.location.href=$("base").attr("href")+"app/cloud_platform/owner/hidden.jsp";
					api.close();
				}else{
					 $.ligerDialog.error(res.msg);
					//return;
				}
				
			})
		}
		
		//if($("#driver"))
	}
	
</script>

</head>
<body>
	<form name="formObj" id="formObj" method="post" >
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">请输入密码：</td>
				<td colspan="3" class="l-t-td-right">
					<input type="password" name="passWord" id="passWord"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

