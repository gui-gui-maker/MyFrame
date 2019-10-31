<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<% String type=request.getParameter("type");
String title="";
boolean isRepair=false;
if("1".equals(type)){
	title="维修";
	isRepair=true;
}else{
	title="保养";
	isRepair=false;
}
%>
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
		var url = "url:app/oa/car/selectCar_list1.jsp?state=0";
		var width = 800	;
		top.$.dialog({
			width : width,
			height : 400,
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
	function getDriver(){
		var title = "驾驶员选择";
		var url = "url:app/oa/car/select_driver_list.jsp";
		var width = 750	;
		top.$.dialog({
			width : width,
			height : 400,
			lock : true,
			parent : api,
			id : "win98",
			title : title,
			content : url,
			cancel: true,
			ok : function() {
				var data = this.iframe.contentWindow.driverSelect();
				//$("#driverCode").val(data.id);
		        $("#driver").val(data.name);
				return true;
			}
		});
	}
	
</script>

</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="oa/car/repair/saveRepairInfo.do"
		getAction="oa/car/repair/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id"/>
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<input type="hidden" name="type" value="<%=type%>"/>
			<tr>
				<td class="l-t-td-left">车牌号：</td>
				<td class="l-t-td-right">
				<input type="hidden" name="car.id" id="carid"/>
				
				<input id="carNum" name="car.carNum" type="text" ltype='text' readonly="readonly" validate="{required:true}" onclick="selectCar()" 
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectCar()}}]}"/>
					
				</td>
				<td class="l-t-td-left">驾驶员：</td>
				<td class="l-t-td-right">
				<input id="driver" name="driver" type="text" ltype='text'  validate="{maxlength:20}"  
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){getDriver()}}]}"/>
				</td>

			</tr>
			<tr>
				<td class="l-t-td-left"><%=title %>日期：</td>
				<td class="l-t-td-right" ><input  name="repairTime"  readonly type="text"
					ltype='date' validate="{required:true}" ligerUi="{format:'yyyy-MM-dd'}" value="<%=DateUtil.getDateTime("yyyy-MM-dd", new Date()) %>"/></td>
				<td class="l-t-td-left"><%=title %>金额：</td>
				<td class="l-t-td-right">
				<input name="repairMoney" type="text" ltype='spinner' validate="{required:false,maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'元'}" /></td>
			</tr>
			 <%if(!isRepair) {%>
			 <tr>
				<td class="l-t-td-left">下次保养日期：</td>
				<td class="l-t-td-right"><input  name="nextDate"  readonly type="text"
					ltype='date' validate="{required:false}" ligerUi="{format:'yyyy-MM-dd'}"/>
			    </td>
			    <td class="l-t-td-left">管理部门：</td>
				<td class="l-t-td-right" >
				<%-- <input type="hidden" name="repairRoomCode" id="repairRoomCode" value="<sec:authentication property="principal.department.id"/>"/>
				<input readonly  name="repairRoom" id="repairRoom" value="<sec:authentication property="principal.department.orgName" htmlEscape="false" />" readonly type="text"
					ltype='text' validate="{required:true,maxlength:50}"/> --%>
				<input type="hidden" name="repairRoomCode" id="repairRoomCode"/>
					<input id="repairRoom" name="repairRoom" type="text" ltype='text' readonly="readonly" validate="{maxlength:50}" onclick="selectUnitOrUser(0,0,'repairRoomCode','repairRoom','')" 
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectUnitOrUser(0,0,'repairRoomCode','repairRoom','')}}]}"/>
					</td>
			   </td>
			 </tr>
			 <tr>
				<td class="l-t-td-left">保养公里数：</td>
				<td class="l-t-td-right"><input name="maintenanceKM" type="text"
					ltype='spinner' validate="{maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'KM',suffixWidth:'45'}"/></td>
			    <td class="l-t-td-left">下次保养公里数：</td>
				<td class="l-t-td-right"><input name="nextMaintenanceKM" type="text"
					ltype='spinner' validate="{maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'KM',suffixWidth:'45'}"/></td>
			 </tr>
			 <%} 
			 else{
				 %>
			<tr>
				 <td class="l-t-td-left">管理部门：</td>
				<td class="l-t-td-right" colspan="3">
				<input type="hidden" name="repairRoomCode" id="repairRoomCode" value="<sec:authentication property="principal.department.id"/>"/>
				<input readonly  name="repairRoom" id="repairRoom" value="<sec:authentication property="principal.department.orgName" htmlEscape="false" />" readonly type="text"
					ltype='text' validate="{required:true,maxlength:50}"/>
			   </td>
			</tr>
				 <%
			 }
			 %>
			<tr>
				<td class="l-t-td-left"><%=title %>厂家：</td>
				<td class="l-t-td-right" colspan="3">
				<input name="repairFactory" type="text"
					ltype='text' validate="{required:false,maxlength:50}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
				<td class="l-t-td-right" colspan="3">
				   <textarea name="remark" id="remark" rows="7" cols="7" validate="{maxlength:500}"></textarea>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>

