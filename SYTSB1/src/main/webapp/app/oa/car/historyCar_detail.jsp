<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
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
		var status="${param.status}";
	   if(status!="detail"){
			$("#endTime").ligerDateEditor().bind("changeDate",function(value){
				isUsed();
			});
			/* $("#startTime").ligerDateEditor().bind("changeDate",function(value){
				isUsed();
			}); */
		}
		
		
		//配置资源选择器

		$("#formObj").initForm({
			success : function(responseText) {//处理成功
				if (responseText.success) {
					top.$.notice("保存成功！");
					api.data.window.Qm.refreshGrid();
					api.close();
				} else {
					$.ligerDialog.error('保存失败' + responseText.msg);
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
	function addDates (sysDates,interval, value) {
		
     var d = sysDates.clone();
     if (!interval || value === 0) return d;

     switch(interval.toLowerCase()) {
         case Date.MILLI:
             d.setMilliseconds(sysDates.getMilliseconds() + value);
             break;
         case Date.SECOND:
             d.setSeconds(sysDates.getSeconds() + value);
             break;
         case Date.MINUTE:
             d.setMinutes(sysDates.getMinutes() + value);
             break;
         case Date.HOUR:
             d.setHours(sysDates.getHours() + value);
             break;
         case Date.DAY:
             d.setDate(sysDates.getDate() + value);
             break;
         case Date.MONTH:
             var day = sysDates.getDate();
             if (day > 28) {
                 day = Math.min(day, sysDates.getFirstDateOfMonth().add('mo', value).getLastDateOfMonth().getDate());
             }
             d.setDate(day);
             d.setMonth(sysDates.getMonth() + value);
             break;
         case Date.YEAR:
             d.setFullYear(sysDates.getFullYear() + value);
             break;
     }
     return d;
 	}
	function isUsed(){
		var start=$("#startTime").val();
		var carid=$("#carid").val();
		var end=$("#endTime").val();
		if(!(start==""||start==null||start==undefined||end==""||end==null||end==undefined||carid==""||carid==null||carid==undefined)){
			var dstr="<%=DateUtil.getDateTime("yyyy-MM-dd", new Date())%>";
			var nowDate = new Date(Date.parse(dstr.replace(/-/g, "/")));
			var startDate = formatDate(start);
			var endDate = formatDate(end);
/* 
			if (startDate > nowDate) {
				$.ligerDialog.error('出车时间不能在今天之后！');
				$("#startTime").val('');
				return;
			} */
			if (endDate < startDate && endDate != null) {
				$.ligerDialog.error('返回时间不能在出车时间之前！');
				return;
			}
		}
	}
	function getDriver() {
		var title = "驾驶员选择";
		var url = "url:app/oa/car/select_driver_list.jsp";
		var width = 750;
		top.$.dialog({
			width : width,
			height : 400,
			lock : true,
			parent : api,
			id : "win98",
			title : title,
			content : url,
			cancel : true,
			ok : function() {
				var data = this.iframe.contentWindow.driverSelect();
				//$("#driverCode").val(data.id);
				$("#driver").val(data.name);
				return true;
			}
		});
	}
	function init(){
		$("#startTime").val("${param.startTime}");
	}
</script>
</head>
<body onload="init()">
	<form name="formObj" id="formObj" method="post"
		action="oa/car/apply/save.do"
		getAction="oa/car/apply/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id" /> <input name="applitorCode"
			type="hidden" /> <input name="applitorName" type="hidden" /> <input
			name="applitorTime" type="hidden" /> <input name="state"
			type="hidden" id="state" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本信息</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0"
				class="l-detail-table">
				<tr>
					<td class="l-t-td-left">车牌号：</td>
					<td class="l-t-td-right"><input type="hidden" name="car.id"
						id="carid" /> <input id="carNum" name="car.carNum" type="text"
						ltype='text' readonly="readonly" validate="{required:true}"/></td>
					<td class="l-t-td-left">驾驶员：</td>
					<td class="l-t-td-right"><input id="driver" name="driver"
						type="text" ltype='text' validate="{maxlength:15}"
						ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){getDriver()}}]}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">目的地：</td>
					<td class="l-t-td-right" colspan="3"><input name="destination"
						type="text" ltype='text' readonly="readonly" validate="{required:true,maxlength:50}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">出车时间：</td>
					<td class="l-t-td-right"><input name="startTime"
						id="startTime" type="text" readonly="readonly" ltype='text'/></td>
					<td class="l-t-td-left">返回时间：</td>
					<td class="l-t-td-right"><input name="endTime" id="endTime"
						type="text" ltype='date'
						validate="{required:true,gtTo:'#startTime'}"
						ligerUi="{format:'yyyy-MM-dd hh:mm'}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">用车事由：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							name="usedCarReason" id="usedCarReason" rows="7" cols="7" readonly="readonly"
							validate="{required:true,maxlength:500}"></textarea></td>
				</tr>
				<tr>
					<td class="l-t-td-left">用车部门：</td>
					<td class="l-t-td-right"><input type="hidden"
						name="applyRoomCode" readonly
						value="<sec:authentication property="principal.department.id"/>" />
						<input name="applyRoom" type="text" ltype='text'
						validate="{required:true,maxlength:50}" readonly
						value="<sec:authentication property="principal.department.orgName"/>" /></td>
				<td class="l-t-td-left">车辆状态：</td>
							<td class="l-t-td-right"><input
								name="state" type="text" id="state"
								ltype='text' validate="{required:true,maxlength:50}" /></td>
						</tr>
				<c:choose>
					<c:when test="${param.status=='detail'}">
						<tr>
							<td class="l-t-td-left">处理人：</td>
							<td class="l-t-td-right" colspan="3"><input
								name="destinationMan" type="text" id="destinationMan"
								ltype='text' validate="{required:true,maxlength:50}" /></td>
						</tr>
					</c:when>
				</c:choose>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>用车信息</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0"
				class="l-detail-table">
				<tr>
					<td class="l-t-td-left">出差审批单：</td>
					<td class="l-t-td-right"><input id="businessTravelApproval" name="businessTravelApproval" type="text"
						ltype='text'/></td>
					<td class="l-t-td-left">加油记录：</td>
					<td class="l-t-td-right"><input id="refuelRecord" name="refuelRecord" type="text"
						ltype='text'/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">派车里程记录：</td>
					<td class="l-t-td-right"><input id="startKm" name="startKm" type="text"
						ltype='spinner' validate="{required:true}" 
						 ligerui="{suffix:'公里',type:'float',isNegative :false}"/></td>
					<td class="l-t-td-left">归还里程记录：</td>
					<td class="l-t-td-right"><input id="endKm" name="endKm"
						type="text" ltype='spinner' validate="{required:true}" 
						 ligerui="{suffix:'公里',type:'float',isNegative :false}"/>
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
