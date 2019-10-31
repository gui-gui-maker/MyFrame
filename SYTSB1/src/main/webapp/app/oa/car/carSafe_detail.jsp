<%@page import="java.util.Date"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	//jQuery页面载入事件
	var beanData;
	$(function() {
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
	
	function formatDate(strDate){
		   if(strDate==null||strDate=="")return ;
		  var strDateArr=strDate.split(" ");
		  var dates=strDateArr[0].split("-");
		  var newDates=dates[1]+"/"+dates[2]+"/"+dates[0];
		  var sysdates=new Date(newDates);
		  return sysdates;
	}
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
				return true;
			}
		});
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="oa/car/safe/save.do"
		getAction="oa/car/safe/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id"/>
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
			<tr>
				<td class="l-t-td-left">车牌号：</td>
				<td class="l-t-td-right">
				<input type="hidden" name="car.id" id="carid"/>
				<input id="carNum" name="car.carNum" type="text" ltype='text' readonly="readonly" validate="{required:true}" onclick="selectCar()" 
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectCar()}}]}"/>	
					
				</td>
				<td class="l-t-td-left">车险种类：</td>
				<td class="l-t-td-right"><u:combo name="safeType"
						code="oa_carSafe_type" ltype="select" validate="required:true" /></td>

			</tr>
			<tr>
				<td class="l-t-td-left">经办人：</td>
				<td class="l-t-td-right" >
				<input type="hidden" name="writeManCode" value="<sec:authentication property="principal.id" />"/>
				<input  name="writeMan"   type="hidden"  value="<sec:authentication property="principal.name" htmlEscape="false"/>"/>
				<input  name="managers"   type="text" ltype='text' validate="{required:true}"  value="<sec:authentication property="principal.name" htmlEscape="false"/>"/>
					
					</td>
				<td class="l-t-td-left">保险公司：</td>
				<td class="l-t-td-right"><input name="safeCompany" type="text"
					ltype='text' validate="{required:true,maxlength:50}" /></td>
			</tr>
			<tr>
				
				<td class="l-t-td-left">保费：</td>
				<td class="l-t-td-right">
				<input name="safeMoney" type="text" ltype='spinner' validate="{required:true,maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'元'}" /></td>
				<td class="l-t-td-left">联系电话：</td>
				<td class="l-t-td-right" >
				<input name="phone" type="text" ltype='text' validate="{required:true,maxlength:11,digits:true}" />
				</td>
			</tr>
			
			<tr>
				
				<td class="l-t-td-left">投保日期：</td>
				<td class="l-t-td-right"><input name="startDate" type="text"
					ltype='date' validate="{required:true,maxlength:50}" ligerUi="{format:'yyyy-MM-dd'}" value="<%=DateUtil.getDateTime("yyyy-MM-dd", new Date()) %>"/></td>
					<td class="l-t-td-left">结束日期：</td>
				<td class="l-t-td-right" ><input name="endDate" type="text"
					ltype='date' validate="{required:true,maxlength:50}" ligerUi="{format:'yyyy-MM-dd'}"/></td>

			</tr>
			<tr>
				<td class="l-t-td-left">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
				<td class="l-t-td-right" colspan="3">
				<textarea name="remark" id="remark" rows="7" cols="7" validate="{maxlength:500}"></textarea></td>


			</tr>
			
			

		</table>

	</form>
</body>
</html>

