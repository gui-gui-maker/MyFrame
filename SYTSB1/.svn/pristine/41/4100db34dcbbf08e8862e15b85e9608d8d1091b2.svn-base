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
	var dayRentalPrice=0;//日租价(元/日)
	var basicMileage=0;//基本里程(公里/日)
	var basicTime=0;//基本时间(小时/日)
	var exceedMileagePrice=0;//超基本里程单价(元/公里) 
	var exceedTimePrice=0;//超基本时间单价(元/小时)
	$(function() {
		var status="${param.status}";
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
	});
	
	function selectRentCar(){
		var title = "车辆选择";
		var url = "url:app/oa/car/selectCar_list.jsp?state=1";
		var width = 700	;
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
				$.getJSON("oa/car/info/getPrice.do?carid="+datas.carid,function(data){
					   data=data.data;
					   if(data.dayRentalPrice!=null){
						   dayRentalPrice=parseFloat(data.dayRentalPrice);
					   }
					   if(data.basicMileage!=null){
						   basicMileage=parseFloat(data.basicMileage);
					   }
					   if(data.basicTime!=null){
						   basicTime=parseFloat(data.basicTime);
					   }
					   if(data.exceedMileagePrice!=null){
						   exceedMileagePrice=parseFloat(data.exceedMileagePrice);
					   }
					   if(data.exceedTimePrice!=null){
						   exceedTimePrice=parseFloat(data.exceedTimePrice);
					   }
					   calcCost();
					});
				return true;
			}
		});
	}
	
	function calcCost(){
		//租车总费用
		if(dayRentalPrice>0){
			var cost=dayRentalPrice;
			var rentTime=0;
			var runKm=0;
			var startKm=0;
			var endKm=0;
			var startTime=0;
			var endTime=0;
			
			if($("#startKm").val()!=""){
				startKm=parseFloat($("#startKm").val());
			}
			if($("#endKm").val()!=""){
				endKm=parseFloat($("#endKm").val());
			}
			if($("#leaseTime").val()!=""){
				startTime=Date.parse($("#leaseTime").val().replace(/-/g,"/"));
			}
			if($("#returnTime").val()!=""){
				endTime=Date.parse($("#returnTime").val().replace(/-/g,"/"));
			}
			runKm=endKm-startKm;
			rentTime=(endTime-startTime)/(1000*3600);
			//计算超里程费用
			if(runKm>basicMileage){
				cost=cost+(runKm-basicMileage)*exceedMileagePrice;
			}
			//计算超时费用
			if(rentTime>basicTime){
				cost=cost+(rentTime-basicTime)*exceedTimePrice;
			}
			if(cost>0){
				$("#money").val(cost);
			}
			else{
				$("#money").val('');
			}
		}
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
		action="oa/car/rent/save.do"
		getAction="oa/car/rent/detail.do?id=${param.id}">
		<input name="id" type="hidden" id="id"/>
		<table border="1" cellpadding="3" cellspacing="0" class="l-detail-table">
			
			<tr>
			    <td class="l-t-td-left">租车时间：</td>
				<td class="l-t-td-right">
				<input name="leaseTime" id="leaseTime" type="text" readonly ltype='date' validate="{required:true,ltTo:'#returnTime'}" ligerUi="{format:'yyyy-MM-dd hh:mm'}" onPropertyChange="calcCost()"/>
				</td>
				<td class="l-t-td-left">还车时间：</td>
				<td class="l-t-td-right">
				<input name="returnTime" id="returnTime" type="text" readonly ltype='date' validate="{required:true,gtTo:'#leaseTime'}" ligerUi="{format:'yyyy-MM-dd hh:mm'}" onPropertyChange="calcCost()"/>
				</td>
			</tr>
			<tr>
			    <td class="l-t-td-left">起始里程数：</td>
				<td class="l-t-td-right">
				<input name="startKm" type="text" id="startKm" ltype='spinner' validate="{required:true,maxlength:10,ltTo:'#endKm'}" ligerui="{type:'float',isNegative:false,suffix:'公里'}" onPropertyChange="calcCost()"/>
				</td>
				<td class="l-t-td-left">结束里程数：</td>
				<td class="l-t-td-right">
				<input name="endKm" id="endKm" type="text" ltype='spinner' validate="{required:true,maxlength:10,gtTo:'#startKm'}" ligerui="{type:'float',isNegative:false,suffix:'公里'}" onPropertyChange="calcCost()"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">用车部门：</td>
				<td class="l-t-td-right">
				<input type="hidden" name="applyDepartmentId" id="applyDepartmentId" readonly/>
				<input name="applyDepartmentName" id="applyDepartmentName" type="text" ltype='text' validate="{required:true,maxlength:50}"  readonly onClick="selectUnitOrUser(0,0,'applyDepartmentId','applyDepartmentName','')"
				ligerui="{iconItems:[
				                   {img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectUnitOrUser(0,0,'applyDepartmentId','applyDepartmentName','')}}
				                   ]}" />
				</td>
			    <td class="l-t-td-left">车牌号：</td>
				<td class="l-t-td-right">
				<input type="hidden" name="carid" id="carid"/>
				<input name="carNum" id="carNum" ltype="text"
				ligerui="{iconItems:[
				                   {img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){selectRentCar()}}
				                   ]}"  
				validate="{required:true,maxlength:50}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">驾驶员：</td>
				<td class="l-t-td-right" >
				<input id="driver" name="driver" type="text" ltype='text'  validate="{required:true,maxlength:15}"  
					ligerui="{iconItems:[{img:'k/kui/images/icons/16/icon-down.png',click:function(val,e,srcObj){getDriver()}}]}"/>	
				</td>
				<td class="l-t-td-left">车辆费用：</td>
				<td class="l-t-td-right">
				<input name="money" id="money" type="text" ltype='spinner' validate="{required:true,maxlength:10}" ligerui="{type:'float',isNegative:false,suffix:'元'}"/>
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">租恁公司：</td>
				<td class="l-t-td-right">
				<input name="company" id="company" type="text" ltype='text' validate="{required:true,maxlength:50}"/></td>
				<td class="l-t-td-left">公司联系电话：</td>
				<td class="l-t-td-right">
				<input name="phone" id="phone" type="text" ltype='text' validate="{required:false,maxlength:11}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">用车事由：</td>
				<td class="l-t-td-right" colspan="3">
				<textarea name="reason"	id="reason" rows="7" cols="5" validate="{required:false,maxlength:500}"></textarea>
				</td>
			</tr>
		</table>

	</form>
</body>
</html>

