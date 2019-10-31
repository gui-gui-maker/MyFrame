<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	//jQuery页面载入事件

	var beanData;

	$(function() {

		//配置资源选择器

		$("#formObj").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("处理成功");
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


		//页面初始化
		var status = "${param.status}";
		if (status == "modify") {
			$("form").setValues();
		}
	});
	
	
</script>

</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="oa/car/info/saveUsedState.do"
		getAction="oa/car/info/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id" value="${param.id}"/>

		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">交车情况：</td> 
				<td class="l-t-td-right"><input type="text" ltype="select" name="carState" ligerui="{data:[{text:'无故未交车',id:'无故未交车'},{text:'因公未交车',id:'因公未交车'},{text:'值班车',id:'值班车'},{text:'领导用车集中停放',id:'领导用车集中停放'}]}" validate="{required:true,maxlength:20}" /></td>
			</tr>
		</table>

	</form>
</body>
</html>

