<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.Date" %>
<%@page import="java.text.SimpleDateFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
	<script type="text/javascript" src="app/car/js/choose_car.js"></script>

    <script type="text/javascript">
    var pageStatus="${param.pageStatus}";
    $(function () {
    	if("add"==pageStatus || "modify"==pageStatus){
			tbar=[
				{text: "保存", id: 'save', icon: "save", click: saveInfo},
				{text: "关闭", id: 'close', icon: "cancel", click: function(){api.close();}}
			];
		}else{
			tbar=[
				{text: "关闭", id: 'close', icon: "cancel", click: function(){api.close();}}
			];
		}
    	$("#form1").initForm({    //参数设置示例
    		success: function (res) {
    			if(res.success){
    				top.$.dialog.notice({
                 		content: "保存成功！"
                	});		
    				api.data.window.Qm.refreshGrid();
    				api.close();
    			}else{
    				$.ligerDialog.error('保存失败！<br/>' + res.msg);
    			}
    		},
    		getSuccess: function (resp){
    			if(resp.success){
					if(pageStatus!="detail"){
						$("#fkCarId").val(resp.carInfo.id);
	           	 		$("#carNum").val(resp.carInfo.carNum);
		           	 	$("#carBrand").val(resp.carInfo.carBrand);
		       	 		$("#loadNumber").val(resp.carInfo.loadNumber);
			       	 	$("#carMileage").val(resp.carInfo.carMileage);
			   	 		$("#engineNo").val(resp.carInfo.engineNo);
			   	 		$("#frameNo").val(resp.carInfo.frameNo);
	            	}else{
	            		//$("#fkCarId").html(api.data.fk_car_id);
	           	 		$("#carNum").html(resp.carInfo.carNum);
		           	 	$("#carBrand").html(resp.carInfo.carBrand);
		       	 		$("#loadNumber").html(resp.carInfo.loadNumber);
			       	 	$("#carMileage").html(resp.carInfo.carMileage);
			   	 		$("#engineNo").html(resp.carInfo.engineNo);
			   	 		$("#frameNo").html(resp.carInfo.frameNo);
	            	}
            	}
			},
			showToolbar: true,
            toolbarPosition: "bottom",
            toolbar: tbar
    	});
    });
  	//保存
	function saveInfo(){
		if ($("#form1").validate().form()) {
			var id = $("#id").val();
			var fkCarId = $("#fkCarId").val();
			var yearMonth = $("#yearMonth").val();
			if(id=="" || id=="undefined"){
				if(fkCarId!=""&&fkCarId!="undefined"&&yearMonth!=""&&yearMonth!="undefined"){
					$.ajax({
						url: "carMpgAction/getCarMpg.do?fkCarId="+fkCarId+"&yearMonth="+yearMonth,
						type: "post",
					 	datatype: "json",
					 	contentType: "application/json; charset=utf-8",
					  	success: function (resp) {
					      	if (resp.success) {
					       		if(resp.data!=null && resp.data.length>0){
					       			$.ligerDialog.error('提示：' + '此车辆'+yearMonth+'的油耗信息已填写！');
					       		}else{
					       			$("#form1").submit();
					       		}
					     	}else{
					     		$.ligerDialog.error('提示：' + '验证车辆油耗数据是否存在失败！');
					    	}
					  	},
						error: function (resp) {
							$.ligerDialog.error('提示：' + '验证车辆油耗数据是否存在失败！');
						}
					});
				}else{
					$.ligerDialog.error('提示：' + '请检查车辆信息或年月份是否填写完整！');
				}
			}else{
				$("#form1").submit();
			}
    	}else{
    		$.ligerDialog.error('提示：' + '请将信息填写完整后保存！');
    	}
	}
	//选择车辆
	function selectCarInfo(){
		selectCar('1','1','','','00',function(datas){
			var carInfo = datas.jsonObj.carInfo[0];
			if(carInfo != null && carInfo != "undefined"){
				
			}
			$("#fkCarId").val(carInfo.id);
   	 		$("#carNum").val(carInfo.car_num);
       	 	$("#carBrand").val(carInfo.car_brand);
   	 		$("#loadNumber").val(carInfo.load_number);
       	 	$("#carMileage").val(carInfo.car_mileage);
   	 		$("#engineNo").val(carInfo.engine_no);
   	 		$("#frameNo").val(carInfo.frame_no);
		});
	}
	//计算相关数据
	function valueChange(obj){
		var inputChar = event.keyCode;
		//1.判断是否有多于一个小数点
		if(inputChar==190 ) {//输入的是否为.
			var index1 = obj.value.indexOf(".") + 1;//取第一次出现.的后一个位置
			var index2 = obj.value.indexOf(".",index1);
			while(index2!=-1) {
				obj.value = obj.value.substring(0,index2);
				index2 = obj.value.indexOf(".",index1);
			}
		}
		//2.如果输入的不是.或者不是数字，替换 g:全局替换
		obj.value = obj.value.replace(/[^(\d|.)]/g,"");
		if(obj.name !="oilMoney"){
			var kmInitial = $("#kmInitial").val();
			var kmFinal = $("#kmFinal").val();
			var oilLitres = $("#oilLitres").val();
			if(kmInitial!=""&&kmInitial!="undefined"&&kmFinal!=""&&kmFinal!="undefined"){
				var kmReal = kmFinal-kmInitial;
				$("#kmReal").val(kmReal);
				if(oilLitres!=""&&oilLitres!="undefined"){
					var oilWear = (oilLitres/kmReal*100).toFixed(2);
					$("#oilWear").val(oilWear);
				}else{
					$("#oilWear").val("");
				}
			}else{
				$("#kmReal").val("");
			}
		}
	}
    </script>
</head>
<body>
    <form id="form1" action="carMpgAction/saveInfo.do?pageStatus=${param.pageStatus}" getAction="carMpgAction/getDetail.do?id=${param.id}">
    	<input type="hidden" id="id" name="id"/>
    	<input type="hidden" id="fkCarId" name="fkCarId"/>
    	<input type="hidden" id="createUserId" name="createUserId"/>
        <input type="hidden" id="createUserName" name="createUserName"/>
        <input type="hidden" id="createDate" name="createDate"/>
        <input type="hidden" id="lastModifyUserId" name="lastModifyUserId"/>
        <input type="hidden" id="lastModifyUserName" name="lastModifyUserName"/>
        <input type="hidden" id="lastModifyDate" name="lastModifyDate"/>
    	<input type="hidden" id="dataStatus" name="dataStatus" value="0"/>
       <fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					车辆信息
				</div>
			</legend>
			<table id="carInfo" border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		   <tr> 
	        <td class="l-t-td-left"> 车牌号</td>
			<td class="l-t-td-right"><input name="carNum" id="carNum" type="text"
				readonly="readonly" title="点击此处选择车辆"
				ltype='text' validate="{required:true}"
				onclick="selectCarInfo()"
				ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectCarInfo()}}]}" />
			</td>
			<td class="l-t-td-left"> 品牌型号</td>
	        <td class="l-t-td-right"> 
	        <input name="carBrand" id="carBrand" type="text" ltype='text' readonly="readonly" unselectable='on'/>
	        </td>
	       </tr>
	       <tr> 
	        <td class="l-t-td-left"> 核定载人数</td>
			<td class="l-t-td-right">
			<input name="loadNumber" id="loadNumber" type="text" ltype='text' readonly="readonly" unselectable='on'/>
			</td>
			<td class="l-t-td-left"> 行驶里程</td>
	        <td class="l-t-td-right"> 
	        <input name="carMileage" id="carMileage" type="text" ltype='text' readonly="readonly" unselectable='on' />
	        </td>
	       </tr>
	       <tr> 
	        <td class="l-t-td-left"> 发动机号</td>
	        <td class="l-t-td-right"> 
	        <input name="engineNo" id="engineNo" type="text" ltype='text' readonly="readonly" unselectable='on'/>
	        </td>
	        <td class="l-t-td-left"> 车辆识别代码</td>
	        <td class="l-t-td-right"> 
	        <input name="frameNo" id="frameNo" type="text" ltype='text' readonly="readonly" unselectable='on'/>
	        </td>
	       </tr>
	      </table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>
					油耗信息
				</div>
			</legend>
			<table id="cmInfo" border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
	       <tr> 
	        <td class="l-t-td-left"> 年月份</td>
	        <td class="l-t-td-right"> 
	        	<input id="yearMonth" name="yearMonth" type="text" ltype='date' value="<%=nowTime%>" ligerui="{format:'yyyy-MM'}" validate="{required:true,maxlength:32}"/>
	        </td>
	        <td class="l-t-td-left"> 月初公里数</td>
	        <td class="l-t-td-right"> 
	        	<input id="kmInitial" name="kmInitial" type="text" ltype='text' title="只能输入数字" onkeyup="valueChange(this)" validate="{required:true,maxlength:32}"/>
	        </td>
	       </tr>
	       <tr> 
	        <td class="l-t-td-left"> 月底公里数</td>
	        <td class="l-t-td-right"> 
	        	<input id="kmFinal" name="kmFinal" type="text" ltype='text' title="只能输入数字" onkeyup="valueChange(this)"/>
	        </td>
	        <td class="l-t-td-left"> 实际公里数</td>
	        <td class="l-t-td-right"> 
	        	<input id="kmReal" name="kmReal" type="text" ltype='text' readonly="readonly" unselectable='on'/>
	        </td>
	       </tr>
	       <tr> 
	        <td class="l-t-td-left"> 加油金额</td>
	        <td class="l-t-td-right"> 
	        	<input id="oilMoney" name="oilMoney" type="text" ltype='text' title="只能输入数字" onkeyup="valueChange(this)"/>
	        </td>
	        <td class="l-t-td-left"> 加油升数</td>
	        <td class="l-t-td-right"> 
	        	<input id="oilLitres" name="oilLitres" type="text" ltype='text' title="只能输入数字" onkeyup="valueChange(this)"/>
	        </td>
	        </tr>
	       <tr> 
	        <td class="l-t-td-left"> 油耗</td>
	        <td class="l-t-td-right"> 
	        	<input id="oilWear" name="oilWear" type="text" ltype='text' validate="{required:true,maxlength:22}}"  readonly="readonly" unselectable='on' minValue="0"/>
	        </td>
	        </tr>
	      </table>
		</fieldset>
    </form> 
</body>
</html>
