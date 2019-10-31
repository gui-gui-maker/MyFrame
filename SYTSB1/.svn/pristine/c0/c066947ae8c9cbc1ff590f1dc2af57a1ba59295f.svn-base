<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/car/js/choose_car.js"></script>
<style>
html,body{font-size: 13px;}
</style>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
%>

<script type="text/javascript">
	var pageStatus="${param.status}";
	var use_days = 1;
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'审核', id:'save',icon:'save', click:save},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	initData(resp);
	        	use_days = resp.data[0].use_days;
	        	if("0" == resp.data[0].data_status){
	        		$('#dep_deal_result').ligerGetRadioGroupManager().setValue("1");
				}else if("1" == resp.data[0].data_status){
					initData1(resp);					
					initData3(resp);
					initData4(resp);
					$('#office_deal_result').ligerGetRadioGroupManager().setValue("1");
				}else if("2" == resp.data[0].data_status){
					initData1(resp);
					$('#leader_deal_result').ligerGetRadioGroupManager().setValue("1");
				}else if("3" == resp.data[0].data_status){
					initData1(resp);
					initData3(resp);
					$('#fleet_deal_result').ligerGetRadioGroupManager().setValue("1");
				}
	        },
	        success: function (response) {//处理成功
				$("body").unmask();
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
               		api.close();
                	api.data.window.refreshGrid();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}
	    });	 
	});
	
	function initData(resp){
		$("#applySn").html(resp.data[0].apply_sn);
		$("#applyDate").html(resp.data[0].apply_date.substring(0,10));
		if(resp.data[0].use_start_date.length>16){
			$("#useStartDate").html(resp.data[0].use_start_date.substring(0,16));
		}else{
			$("#useStartDate").html(resp.data[0].use_start_date.substring(0,10));
		}
		if(resp.data[0].use_end_date.length>16){
			$("#useEndDate").html(resp.data[0].use_end_date.substring(0,16));
		}else{
			$("#useEndDate").html(resp.data[0].use_end_date.substring(0,10));
		}
		$("#useDepName").html(resp.data[0].use_dep_name);
		$("#useUserName").html(resp.data[0].use_user_name+"/"+resp.data[0].use_user_phone);
		//$("#useUserPhone").html(resp.data[0].use_user_phone);
		$("#applyReason").html(resp.data[0].apply_reason);
		$("#driveRoute").html(resp.data[0].drive_route);
		$("#useDays").html(resp.data[0].use_days + " 天");
		$("#passengersCount").html(resp.data[0].passengers_count + " 人");
		if("0" == resp.data[0].need_driver){
			$("#needDriver").html("否");
		}else{
			$("#needDriver").html("是");
		}
		if(resp.data[0].apply_remark!=null){
			$("#applyRemark").html(resp.data[0].apply_remark);
		}
	}
	
	function initData1(resp){
		if("1" == resp.data[0].dep_deal_result){
			$("#depDealResult").html("同意");
		}else if("0" == resp.data[0].dep_deal_result){
			$("#depDealResult").html("不同意");
		}else{
			$("#depDealResult").html("");
		}
		$("#depDealRemark").html(resp.data[0].dep_deal_remark);
		$("#depDealName").html(resp.data[0].dep_deal_name);
		$("#depDealDate").html(resp.data[0].dep_deal_date.substring(0,10));
	}
	
	function initData2(resp){
		if("1" == resp.data[0].office_deal_result){
			$("#officeDealResult").html("同意");
		}else if("0" == resp.data[0].office_deal_result){
			$("#officeDealResult").html("不同意");
		}else{
			$("#officeDealResult").html("");
		}
		$("#officeDealRemark").html(resp.data[0].office_deal_remark);
		$("#officeDealName").html(resp.data[0].office_deal_name);
		$("#officeDealDate").html(resp.data[0].office_deal_date.substring(0,10));
	}
	
	function initData3(resp){
		if("1" == resp.data[0].leader_deal_result){
			$("#leaderDealResult").html("同意");
		}else if("0" == resp.data[0].leader_deal_result){
			$("#leaderDealResult").html("不同意");
		}else{
			$("#leaderDealResult").html("");
		}
		$("#leaderDealRemark").html(resp.data[0].leader_deal_remark);
		$("#leaderDealName").html(resp.data[0].leader_deal_name);
		if(resp.data[0].leader_deal_date!=null){
			$("#leaderDealDate").html(resp.data[0].leader_deal_date.substring(0,10));
		}
	}
	
	function initData4(resp){
		if("1" == resp.data[0].fleet_deal_result){
			$("#fleetDealResult").html("同意");
		}else if("0" == resp.data[0].fleet_deal_result){
			$("#fleetDealResult").html("不同意");
		}else{
			$("#leaderDealResult").html("");
		}
		$("#fleetDealRemark").html(resp.data[0].fleet_deal_remark);
		$("#fleetDealName").html(resp.data[0].fleet_deal_name);
		if(resp.data[0].fleet_deal_date!=null){
			$("#fleetDealDate").html(resp.data[0].fleet_deal_date.substring(0,10));
		}
		$("#plateNumber").html(resp.data[0].plate_number);
		$("#driverUserName").html(resp.data[0].driver_user_name);
		$("#startKm").html(resp.data[0].start_km);
		if(resp.data[0].out_date!=null){
			$("#outDate").html(resp.data[0].out_date.substring(0,16));
		}
		$("#sendDealName").html(resp.data[0].send_deal_name);
		if(resp.data[0].send_date!=null){
			$("#sendDate").html(resp.data[0].send_date.substring(0,16));
		}
	}
	
	function save(){
    	//验证表单数据
		if($('#formObj').validate().form()){			
			//表单提交
			var id = "${param.id}";
	    	var formData = $("#formObj").getValues();
			var data = {};
			data = $.ligerui.toJSON(formData);
			
			var data_status = "${param.data_status}";
			if("0" == data_status){
				var dep_deal_result =$('#dep_deal_result').ligerGetRadioGroupManager().getValue();
				if("0" == dep_deal_result){
					if($('#dep_deal_remark').val()==null||$('#dep_deal_remark').val()==undefined||$('#dep_deal_remark').val()==""){
						$.ligerDialog.alert('亲，审核结果为不同意时，请填写备注！');
						return;
					}
				}
			}else if("1" == data_status){
				var office_deal_result =$('#office_deal_result').ligerGetRadioGroupManager().getValue();
				if("0" == office_deal_result){
					if($('#office_deal_remark').val()==null||$('#office_deal_remark').val()==undefined||$('#office_deal_remark').val()==""){
						$.ligerDialog.alert('亲，审核结果为不同意时，请填写备注！');
						return;
					}
				}
			}else if("2" == data_status){
				var leader_deal_result =$('#leader_deal_result').ligerGetRadioGroupManager().getValue();
				if("0" == leader_deal_result){
					if($('#leader_deal_remark').val()==null||$('#leader_deal_remark').val()==undefined||$('#leader_deal_remark').val()==""){
						$.ligerDialog.alert('亲，审核结果为不同意时，请填写备注！');
						return;
					}
				}
			}else if("3" == data_status){
				var fleet_deal_result =$('#fleet_deal_result').ligerGetRadioGroupManager().getValue();
				if("0" == fleet_deal_result){
					if($('#fleet_deal_remark').val()==null||$('#fleet_deal_remark').val()==undefined||$('#fleet_deal_remark').val()==""){
						$.ligerDialog.alert('亲，审核结果为不同意时，请填写备注！');
						return;
					}
				}else if("1" == fleet_deal_result){
					/*
					if($('#plate_number').val()==null||$('#plate_number').val()==undefined||$('#plate_number').val()==""){
						$.ligerDialog.alert('亲，审核结果为同意时，请填写车牌号！');
						return;
					}
					if($('#driver_user_name').val()==null||$('#driver_user_name').val()==undefined||$('#driver_user_name').val()==""){
						$.ligerDialog.alert('亲，审核结果为同意时，请填写驾驶员！');
						return;
					}
					if($('#start_km').val()==null||$('#start_km').val()==undefined||$('#start_km').val()==""){
						$.ligerDialog.alert('亲，审核结果为同意时，请填写公里起数！');
						return;
					}
					if($('#out_date').val()==null||$('#out_date').val()==undefined||$('#out_date').val()==""){
						$.ligerDialog.alert('亲，审核结果为同意时，请选择出车时间！');
						return;
					}
					*/
				}
			}
			
			$("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "car/apply/checks.do?id="+id,
	            data: data,	//JSON.stringify(json)把json转化成字符串
	            cache:false,    
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (data, stats) {
	            	$("body").unmask();
	                if (data["success"]) {
	                	top.$.notice("保存成功！");
	               		api.close();
	                	api.data.window.refreshGrid();
	                } else {
	                	$.ligerDialog.error(data.msg);
	                }
	            },
	            error: function (data) {
	            	$("body").unmask();
	                $.ligerDialog.error(data.msg);
	            }
	        });
	    }
    }
	
	function close(){
    	api.close();
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post"
		action="car/apply/checks.do"
		getAction="car/apply/getDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}" />
		<input type="hidden" name="data_status" id="data_status" value="${param.data_status}" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>用车申请单</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">编号：</td>
					<td class="l-t-td-right">
						<div id="applySn"></div>
					</td>
					<td class="l-t-td-left">填报日期：</td>
					<td class="l-t-td-right">
						<div id="applyDate"></div>
					</td>
					<td class="l-t-td-left">用车部门：</td>
					<td class="l-t-td-right">
						<div id="useDepName"></div>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">申请用车起：</td>
					<td class="l-t-td-right">
						<div id="useStartDate"></div>
					</td>
					<td class="l-t-td-left">用车止：</td>
					<td class="l-t-td-right">
						<div id="useEndDate"></div>
					</td>
					<td class="l-t-td-left">联系人：</td>
					<td class="l-t-td-right">
						<div id="useUserName"></div>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">用车任务：</td>
					<td class="l-t-td-right">
						<div id="applyReason"></div>
					</td>
					<td class="l-t-td-left">用车天数：</td>
					<td class="l-t-td-right">
						<div id="useDays"></div>
					</td>
					<td class="l-t-td-left">行驶路线：</td>
					<td class="l-t-td-right">
						<div id="driveRoute"></div>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">乘车人数：</td>
					<td class="l-t-td-right" >
						<div id="passengersCount"></div>
					</td>
					<td class="l-t-td-left">是否需要司机：</td>
					<td class="l-t-td-right">
						<div id="needDriver"></div>
					</td>
					<td class="l-t-td-left">&nbsp;&nbsp;</td>
					<td class="l-t-td-right">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="5">
						<div id="applyRemark"></div>
					</td>
				</tr>
			</table>
		</fieldset>
		<c:choose>
			<c:when test="${param.data_status eq '0'}">
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>部门负责人审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right"><input type="radio"
								name="dep_deal_result" id="dep_deal_result" ltype="radioGroup"
								validate="{required:true}"
								ligerui="{initValue:'1',data: [ { text:'同意', id:'1' }, { text:'不同意', id:'0' }] }" />
							</td>
							<td class="l-t-td-left"></td>
							<td class="l-t-td-right"></td>
						</tr>
						<tr>
							<td class="l-t-td-left">备注：</td>
							<td class="l-t-td-right" colspan="3"><textarea
									name="dep_deal_remark" id="dep_deal_remark" rows="3" cols="25"
									class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
							</td>
						</tr>
					</table>
				</fieldset>
			</c:when>
			<c:when test="${param.data_status eq '1'}">
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>部门负责人审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right">
								<div id="depDealResult"></div>
							</td>
							<td class="l-t-td-left">审核人：</td>
							<td class="l-t-td-right"  width="100">
								<div id="depDealName"></div>
							</td>
							<td class="l-t-td-left">审核时间：</td>
							<td class="l-t-td-right">
							<div id="depDealDate"></div>
							</td>
							<td class="l-t-td-left">备注：</td>
							<td class="l-t-td-right" width="215">
								<div id="depDealRemark"></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>分管院领导审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right">
								<div id="leaderDealResult"></div>
							</td>
							<td class="l-t-td-left">审核人：</td>
							<td class="l-t-td-right" width="100">
								<div id="leaderDealName"></div>
							</td>
							<td class="l-t-td-left">审核时间：</td>
							<td class="l-t-td-right" width="133">
								<div id="leaderDealDate"></div>
							</td>
							<td class="l-t-td-left" >备注：</td>
							<td class="l-t-td-right" width="215">
								<div id="leaderDealRemark"></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>车队负责人审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right">
								<div id="fleetDealResult"></div>
							</td>
							<td class="l-t-td-left">审核人：</td>
							<td class="l-t-td-right" width="100">
								<div id="fleetDealName"></div>
							</td>
							<td class="l-t-td-left">审核时间：</td>
							<td class="l-t-td-right">
								<div id="fleetDealDate"></div>
							</td>
							<td class="l-t-td-left" >备注：</td>
							<td class="l-t-td-right" width="215">
								<div id="fleetDealRemark"></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>车队派车</div>
					</legend>
					<table cellpadding="3" cellspacing="0" class="l-detail-table">
						<tr>
							<td class="l-t-td-left">车牌号：</td>
							<td class="l-t-td-right">
								<div id="plateNumber"></div>
							</td>
							<td class="l-t-td-left">驾驶员：</td>
							<td class="l-t-td-right">
								<div id="driverUserName"></div>
							</td>
							<td class="l-t-td-left">公里起数：</td>
							<td class="l-t-td-right">
								<div id="startKm"></div>
							</td>
						</tr>
						<tr>
							<td class="l-t-td-left">出车时间：</td>
							<td class="l-t-td-right">
								<div id="outDate"></div>
							</td>
							<td class="l-t-td-left">派车人：</td>
							<td class="l-t-td-right">
								<div id="sendDealName"></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>办公室负责人审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right"><input type="radio"
								name="office_deal_result" id="office_deal_result"
								ltype="radioGroup" validate="{required:true}"
								ligerui="{initValue:'1',data: [ { text:'同意', id:'1' }, { text:'不同意', id:'0' }] }" />
							</td>
							<td class="l-t-td-left"></td>
							<td class="l-t-td-right"></td>
						</tr>
						<tr>
							<td class="l-t-td-left">备注：</td>
							<td class="l-t-td-right" colspan="3"><textarea
									name="office_deal_remark" id="office_deal_remark" rows="3"
									cols="25" class="l-textarea"
									validate="{required:false,maxlength:1000}"></textarea></td>
						</tr>
					</table>
				</fieldset>
			</c:when>
			<c:when test="${param.data_status eq '2'}">
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>部门负责人审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right">
								<div id="depDealResult"></div>
							</td>
							<td class="l-t-td-left">审核人：</td>
							<td class="l-t-td-right" width="100">
								<div id="depDealName"></div>
							</td>
							<td class="l-t-td-left">审核时间：</td>
							<td class="l-t-td-right">
								<div id="depDealDate"></div>
							</td>
							<td class="l-t-td-left" >备注：</td>
							<td class="l-t-td-right" width="215">
								<div id="depDealRemark" ></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>分管院领导审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right"><input type="radio"
								name="leader_deal_result" id="leader_deal_result"
								ltype="radioGroup" validate="{required:true}"
								ligerui="{initValue:'1',data: [ { text:'同意', id:'1' }, { text:'不同意', id:'0' }] }" />
							</td>
							<td class="l-t-td-left"></td>
							<td class="l-t-td-right"></td>
						</tr>
						<tr>
							<td class="l-t-td-left">备注：</td>
							<td class="l-t-td-right" colspan="3"><textarea
									name="leader_deal_remark" id="leader_deal_remark" rows="3"
									cols="25" class="l-textarea"
									validate="{required:false,maxlength:1000}"></textarea></td>
						</tr>
					</table>
				</fieldset>
			</c:when>
			<c:when test="${param.data_status eq '3'}">
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>部门负责人审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right">
								<div id="depDealResult"></div>
							</td>
							<td class="l-t-td-left">审核人：</td>
							<td class="l-t-td-right" width="100">
								<div id="depDealName"></div>
							</td>
							<td class="l-t-td-left">审核时间：</td>
							<td class="l-t-td-right">
								<div id="depDealDate"></div>
							</td>
							<td class="l-t-td-left">备注：</td>
							<td class="l-t-td-right" width="215">
								<div id="depDealRemark"></div>
						</tr>
					</table>
				</fieldset>
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>分管院领导审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right">
								<div id="leaderDealResult"></div>
							</td>
							<td class="l-t-td-left">审核人：</td>
							<td class="l-t-td-right" width="100">
								<div id="leaderDealName"></div>
							</td>
							<td class="l-t-td-left">审核时间：</td>
							<td class="l-t-td-right" width="143">
								<div id="leaderDealDate"></div>
							</td>
							<td class="l-t-td-left" >备注：</td>
							<td class="l-t-td-right" width="215">
								<div id="leaderDealRemark"></div>
							</td>
						</tr>
					</table>
				</fieldset>
				<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>车队负责人审核</div>
					</legend>
					<table width="100%">
						<tr>
							<td class="l-t-td-left">审核结果：</td>
							<td class="l-t-td-right"><input type="radio"
								name="fleet_deal_result" id="fleet_deal_result"
								ltype="radioGroup" validate="{required:true}"
								ligerui="{initValue:'1',data: [ { text:'同意', id:'1' }, { text:'不同意', id:'0' }] }" />
							</td>
							<!-- 
							<td class="l-t-td-left">车牌号：</td>
							<td class="l-t-td-right"><input type="hidden" name="fk_car_id"
								id="fk_car_id" value="" /> <input name="plate_number" type="text"
								readonly="readonly" id="plate_number" title="点击此处选择车辆"
								ltype='text' validate="{required:false}"
								onclick="selectCar('1','1','fk_car_id','plate_number','0',true,'start_km','${param.id}')"
								ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectCar('1','1','fk_car_id','plate_number','0',true,'start_km','${param.id}')}}]}" />
							</td>
							 -->
							 <td class="l-t-td-left"></td>
							<td class="l-t-td-right"></td>
						</tr>
						<tr>
							<td class="l-t-td-left">备注：</td>
							<td class="l-t-td-right" colspan="3"><input type="text"
								name="fleet_deal_remark" id="fleet_deal_remark" ltype="text"
								validate="{required:false,maxlength:1000}" /></td>
						</tr>
					</table>
				</fieldset>
			</c:when>
		</c:choose>
	</form>
</body>
</html>
