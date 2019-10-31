<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="add">
<title>任务提醒设置</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
$(function(){
	$("#formObj").initForm({
		toolbar:[{
			text: "清空",
			icon: "win-okval",
			click: clear
		},{
			text: "确定",
			icon: "win-okval",
			click: doOK
		},{
			text: "取消",
			icon: "close",
			click: function(){api.close();}
		}]
	});
});

function clear(){
	api.data.callback(null);
	api.close();
}

function changeRemind(o){
	if(o.checked){
		$("#remindCtr input").each(function(){
			$(this).ligerGetSpinnerManager().setEnabled();
		});
	}else{
		$("#remindCtr input").each(function(){
			$(this).ligerGetSpinnerManager().setDisabled();
		});
	}
}

function changeTimeoutRemind(o){
	if(o.checked){
		$("#tmRemindCtr :checkbox").ligerGetTextBoxManager().setEnabled();
	}else{
		$("#tmRemindCtr :checkbox").ligerGetTextBoxManager().setDisabled();
	}
}
function changeRollingRemind(o){
	if(o.checked){
		$("#rollingRemind").ligerGetSpinnerManager().setEnabled();
	}else{
		$("#rollingRemind").ligerGetSpinnerManager().setDisabled();
	}
}

function doOK(){
	var fdata = $("#formObj").getValues();
	var rst = {};
	if(fdata.ahead=="1"){
		rst["ahead"] = true;
		if(fdata.aheadDay=="" || fdata.aheadHour=="" ||(fdata.aheadDay=="0" && fdata.aheadHour=="0")){
			top.$.dialog.alert("【提前天数】和【小时】不能都为空，<br/>并且不能都为0！",null,api);
			return;
		}
		rst["aheadDay"] = fdata.aheadDay;
		rst["aheadHour"] = fdata.aheadHour;
	}else{
		rst["ahead"] = false;
	}
	if(fdata.timeout=="1"){
		rst["timeout"] = true;
		if(fdata.rolling=="1"){
			if(fdata.rollingHour=="" || fdata.rollingHour=="0"){
				top.$.dialog.alert("【循环提醒时间】不能为空，<br/>并且不能为0！",null,api);
				return;
			}
			rst["rolling"] = true;
			rst["rollingHour"] = fdata.rollingHour;		
		}else{
			rst["rolling"] = false;
		}
	}else{
		rst["timeout"] = false;
	}
	api.data.callback(rst);
	api.close();
}
function aheadHourChange(h){
	if(h.value=='')h.value='0';
	else if(h.value >= 24){
		h.value = h.value-24;
		$('#aheadDay').val(parseInt($('#aheadDay').val())+1);
	}
}
</script>
<style type="text/css">
	.l-legend span{
		display: inline-block;
		padding: 5px;
		line-height: 14px;
		height: 14px;
	}
	.l-t-td-left div,.l-t-td-right div,.l-t-td-left,.l-t-td-right{padding-left:0;padding-right:0;}
</style>
<body>
	<form id="formObj">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<span>
					<input type="checkbox" name="ahead" ltype="checkboxGroup"  onchange="changeRemind(this)" 
						ligerui="{initValue:'1',data:[{text:'提前提醒',id:'1'}]}" />
				</span>
			</legend>
			<table class="l-detail-table" style="width:245px;" id="remindCtr">
				<tr>
					<td class="l-t-td-left" style="width:50px;">提前&nbsp;</td>
					<td class="l-t-td-right" style="width:75px;">
						<input type="text" name="aheadDay" id="aheadDay" ltype="spinner" value="0" 
							onblur="if(this.value=='')this.value='0'"
							ligerui="{type:'int',suffix:'天',suffixWidth:15,isNegative:false}" />
					</td>
					<td class="l-t-td-right" style="width:120px;">
						<input type="text" name="aheadHour" ltype="spinner" value="0" 
							onblur="aheadHourChange(this)"
							ligerui="{type:'int',suffix:'小时提醒',suffixWidth:60,isNegative:false}" />
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<span>
					<input type="checkbox" name="timeout" ltype="checkboxGroup"  onchange="changeTimeoutRemind(this)" 
						ligerui="{initValue:'1',data:[{text:'到期提醒',id:'1'}]}" />
				</span>
			</legend>
			<table class="l-detail-table" style="width:280px;" id="tmRemindCtr">
				<tr>
					<td class="l-t-td-left" style="width:105px;">
						<input type="checkbox" name="rolling" id="rollingRemindEnable" onchange="changeRollingRemind(this)" 
								ltype="checkboxGroup" ligerui="{data:[{text:'循环提醒：',id:'1'}],isNegative:false}" />
					</td>
					<td class="l-t-td-left" style="width:35px;">每隔&nbsp;</td>
					<td class="l-t-td-right" style="width:140px;">
						<input type="text" name="rollingHour" id="rollingRemind" ltype="spinner" value="0"
							onblur="if(this.value=='')this.value='0'" 
							ligerui="{type:'int',suffix:'小时提醒一次',suffixWidth:80,disabled:true,isNegative:false}" />
					</td>
				</tr>
			</table>
		</fieldset>
	</form>
</body>