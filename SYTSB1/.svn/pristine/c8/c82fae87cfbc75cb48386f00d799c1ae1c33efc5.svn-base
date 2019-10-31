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
<style>
html,body{font-size: 13px;}
</style>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String nowTime = dateFormat.format(new Date());
%>
<script type="text/javascript">
	var pageStatus="${param.status}";

	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:save},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	initData(resp);
				initData1(resp);
				initData2(resp);
				initData3(resp);
				initData4(resp);
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
		$("#plateNumber").html(resp.data[0].plate_number);
		$("#driverUserName").html(resp.data[0].driver_user_name);
		$("#outDate").html(resp.data[0].out_date.substring(0,16));
		$("#startKm").html(resp.data[0].start_km + " km");
		$("#sendDealName").html(resp.data[0].send_deal_name);
		$("#sendDate").html(resp.data[0].send_date.substring(0,10));
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
			$("#fleetDealResult").html("");
		}
		$("#fleetDealRemark").html(resp.data[0].fleet_deal_remark);
		$("#fleetDealName").html(resp.data[0].fleet_deal_name);
		$("#fleetDealDate").html(resp.data[0].fleet_deal_date.substring(0,10));
	}
	
	function save(){
    	//验证表单数据
		if($('#formObj').validate().form()){			
			//表单提交
			var id = "${param.id}";
	    	var formData = $("#formObj").getValues();
			var data = {};
			data = $.ligerui.toJSON(formData);
	
			$("body").mask("正在保存数据，请稍后！");
	        $.ajax({
	            url: "car/apply/receives.do?id="+id,
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
		action="car/apply/receives.do"
		getAction="car/apply/getDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}" />
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
					<td class="l-t-td-left">审核日期：</td>
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
						<div id="leaderDealName" ></div>
					</td>
					<td class="l-t-td-left">审核日期：</td>
					<td class="l-t-td-right">
						<div id="leaderDealDate"></div>
					</td>
					<td class="l-t-td-left">备注：</td>
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
					<td class="l-t-td-left">审核日期：</td>
					<td class="l-t-td-right">
						<div id="fleetDealDate"></div>
					</td>
					<td class="l-t-td-left">备注：</td>
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
					<td class="l-t-td-right">
						<div id="officeDealResult"></div>
					</td>
					<td class="l-t-td-left">审核人：</td>
					<td class="l-t-td-right" width="100">
						<div id="officeDealName"></div>
					</td>
					<td class="l-t-td-left">审核日期：</td>
					<td class="l-t-td-right">
						<div id="officeDealDate"></div>
					</td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" width="215">
						<div id="officeDealRemark"></div>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>车队收车</div>
			</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">返回时间：</td>
					<td class="l-t-td-right"><input id="back_date"
						name="back_date" type="text" ltype="date"
						validate="{required:true}"
						ligerui="{initValue:'<%=nowTime%>',format:'yyyy-MM-dd hh:mm'}" /></td>
					<td class="l-t-td-left">公里止数：</td>
					<td class="l-t-td-right"><input type="text" name="end_km"
						id="end_km" validate="{required:true}" ltype='spinner'
						validate="{maxlength:10}"
						ligerui="{type:'int',isNegative:false,suffix:'km',suffixWidth:'45'}" />
					</td>
					<td class="l-t-td-left">&nbsp;&nbsp;</td>
					<td class="l-t-td-right">&nbsp;&nbsp;</td>
					<td class="l-t-td-left">&nbsp;&nbsp;</td>
					<td class="l-t-td-right">&nbsp;&nbsp;</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>
</html>
