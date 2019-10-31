
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="/pub/selectUser/selectUnitOrUser.js"></script>
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
					top.$.notice("处理成功 ");
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('处理失败' + responseText.msg);
				}
			},
			getSuccess : function(response) {
				if (response.success)
					beanData = response.data;
				else {
					$.ligerDialog.alert("获取数据错误!");
					return;
				}

			}
		});

	});
	function save(){
		if ($("#formObj").validate().form()) {
			var id = sessionStorage.getItem("owner_root");
			var passWord = $("#passWord").val();
			$.post("resourceSpace/updateSpaceNotHidden.do",{id:id,passWord:passWord},function(res){
				if(res.success){
					
					api.data.window.setF();
					api.data.window.hiddenType="0";
					sessionStorage.setItem("hiddenType", "0");
					api.data.window.refreshData(api.data.pid)
					top.$.notice("设置成功！");
					api.close();
				}else{
					$.ligerDialog.error(res.msg);
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

