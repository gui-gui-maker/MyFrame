<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="ckeditor/adapters/jquery.js"></script>
<script type="text/javascript">
	//jQuery页面载入事件

	var beanData;

	$(function() {

		//配置资源选择器

		$("#formObj").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + responseText)
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
	function selectCar(){
		var title = "车辆选择";
		var url = "url:app/oa/car/selectCar_list.jsp";
		var width = 700	;
		top.$.dialog({
			width : width,
			height : 500,
			lock : true,
			parent : api,
			id : "win98",
			title : title,
			content : url,
			cancel: true,
			ok : function() {
				var datas = this.iframe.contentWindow.getSelectResult();
				
				$("#carid").val(datas.carid);
				$("#carNum").val(datas.carnum);
				$("#driver").val(datas.driver);
				return true;
			}
		});
	}
	
</script>

</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="oa/car/apply/save.do"
		getAction="oa/car/apply/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id"/>

	
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">申请处（室）：</td>
				<td class="l-t-td-right">
				<input type="hidden" name="applyRoomCode" value="${currentSessionUser.unit.deptcode}"/>
				<input name="applyRoom" type="text"
					ltype='text' validate="{required:true,maxlength:50}" value="<sec:authentication property="principal.unit.orgName" htmlEscape="false"/>"/></td>
				<td class="l-t-td-left">车牌号：</td>
				<td class="l-t-td-right">
				<input  type="hidden" name="car.id" id="carid"/>
				<input name="car.carNum" type="text" id="carNum" onclick="selectCar()" readonly
					ltype='text' validate="{required:true,maxlength:50}" /></td>

			</tr>
			<tr>
				<td class="l-t-td-left">驾驶员：</td>
				<td class="l-t-td-right" ><input  name="driver" id="driver"   type="text"
					ltype='text' validate="{required:true,maxlength:15}"/></td>
			</tr>
			
			<tr>
				<td class="l-t-td-left">目的地：</td>
				<td class="l-t-td-right" colspan="3"><input  name="destination"   type="text"
					ltype='text' validate="{required:true,maxlength:50}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">出车时间：</td>
				<td class="l-t-td-right"><input name="startTime" type="text" readonly
					ltype='date' validate="{required:true}" ligerUi="{format:'yyyy-MM-dd'}"/></td>
				<td class="l-t-td-left">返回时间：</td>
				<td class="l-t-td-right"><input name="endTime" type="text" readonly 
					ltype='date' validate="{required:true }" ligerUi="{format:'yyyy-MM-dd'}"/></td>

			</tr>
			<tr>
				
					<td class="l-t-td-left">用车事由：</td>
				<td class="l-t-td-right" colspan="3"><textarea name="usedCarReason"
						id="usedCarReason" rows="7" cols="7"></textarea></td>
			</tr>
			
			<c:choose>
			 <c:when test="${param.status=='detail'}">
			 <tr>
				<td class="l-t-td-left">处室领导：</td>
				<td class="l-t-td-right" colspan="3">
				<input name="destinationMan" type="text" id="destinationMan" 
					ltype='text' validate="{required:true,maxlength:50}" /></td>
			</tr>
			 </c:when>
			</c:choose>

		</table>

	</form>
</body>
</html>

