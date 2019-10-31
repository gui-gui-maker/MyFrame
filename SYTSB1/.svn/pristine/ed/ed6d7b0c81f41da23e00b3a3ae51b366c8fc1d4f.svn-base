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
		var url = "url:app/oa/car/selectCar_list1.jsp";
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
		action="oa/car/cost/save.do"
		getAction="oa/car/cost/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id"/>
		
		<input type="hidden" name="writeManCode" value="<sec:authentication property="principal.id" />"/>
		<input  name="writeMan"   type="hidden"  value="<sec:authentication property="principal.name" htmlEscape="false"/>"/>
		<input  name="writeTime"   type="hidden" />
		<table border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">

			<tr>
				<td class="l-t-td-left">车牌号：</td>
				<td class="l-t-td-right">
				<input type="hidden" name="car.id" id="carid"/>
				
				<input id="carNum" name="car.carNum" type="text" ltype='text' readonly="readonly" validate="{required:true}" onclick="selectCar()" 
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectCar()}}]}"/></td>
				<td class="l-t-td-left">费用类型：</td>
				<td class="l-t-td-right"><u:combo name="costType"
						code="oa_car_cost_type"  validate="required:true"/></td>

			</tr>
			<tr>
				<td class="l-t-td-left">登记时间：</td>
				<td class="l-t-td-right" >
						<input name="registerDate" type="text"
					ltype='date' validate="{required:true,maxlength:50}" ligerUi="{format:'yyyy-MM-dd'}"/>
				</td>
				<td class="l-t-td-left">费用(元)：</td>
				<td class="l-t-td-right">
				<input name="cost" type="text" validate="{required:true,maxlength:50}"  ltype="spinner" ligerui="{type:'float',suffix:'元',isNegative:false}" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">备&nbsp;&nbsp;&nbsp;&nbsp;注：</td>
				<td class="l-t-td-right" colspan="3">
				<textarea name="remark" id="remark" rows="7" cols="7"></textarea>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>

