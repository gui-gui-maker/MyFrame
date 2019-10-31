<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="modify">
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
	
	
</script>

</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="oa/car/rent/backRentCar.do"
		getAction="oa/car/apply/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id" value="${param.id}"/>

		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
			    <td class="l-t-td-left">起始里程数：</td>
				<td class="l-t-td-right">
				<input name="startKm" type="text" id="startKm" ltype='spinner' validate="{required:true,maxlength:10,ltTo:'#endKm'}" ligerui="{type:'float',isNegative:false,suffix:'公里'}" onPropertyChange="calcCost()"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">结束里程数：</td>
				<td class="l-t-td-right">
				<input name="endKm" id="endKm" type="text" ltype='spinner' validate="{required:true,maxlength:10,gtTo:'#startKm'}" ligerui="{type:'float',isNegative:false,suffix:'公里'}" onPropertyChange="calcCost()"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">汽油单价：</td>
				<td class="l-t-td-right">
				<input type="text" ltype='text' name="oilPrice" validate="{required:true,maxlength:20}" ltype="spinner" ligerui="{type:'float',suffix:'元/升',suffixWidth:'30'}" />
				</td>
				
			</tr>
			<tr>
				<td class="l-t-td-left">实际结束时间：</td>
				<td class="l-t-td-right"><input type="text" ltype='date' name="endTime" validate="{required:true}" ligerui="{format:'yyyy-MM-dd'}"/>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>
