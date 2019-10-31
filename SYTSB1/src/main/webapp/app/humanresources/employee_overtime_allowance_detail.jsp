<%@page import="com.ctc.wstx.util.DataUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.DateUtil"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%

	CurrentSessionUser sessionUser = SecurityUtil.getSecurityUser();
	String unit=sessionUser.getUnit().getOrgName();
	String user=sessionUser.getName();
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
	java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
	String str_date1 = formatter.format(currentTime); //将日期时间格式化 

%>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/humanresources/overtime_allowance/inner_opinsp.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/photograph/js/photograph.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/payment/unit/ChipCombobox.js"></script>
<link type="text/css" rel="stylesheet"
	href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript">
var opAttr=[];
var p;
var pageStatus = "${param.status}"
	deviceType=<u:dict code="OVERTIME_OPINION"/>
$(function() {
	initGrid();
	$("#formObj").initForm({
		toolbar : [{
			text : '保存',
			icon : 'save',
			click : save
		}, {
			text : '取消',
			icon : 'cancel',
			click : function() {
				api.close();
			}
		}],
		success : function(response) {//处理成功
			if (response.success) {
				top.$.notice("保存成功！");					
				api.close();
				api.data.window.Qm.refreshGrid();
			} else {
				$.ligerDialog.error('保存失败！<br/>' + response.msg);
			}
		},
		getSuccess: function (response) {
			if(response.success){
				if(response.data!=null){
					$("#formObj").setValues(response.data)
					changeOp();
				}
				if(response.data!=null&&response.data.allowancefos!=null){
					deviceGrid.loadData({Rows : response.data.allowancefos});
				}
			}
			else{
				$.ligerDialog.error("获取信息失败！<br/>" + response.msg);
			}
		}

	});
})


//function save(){
	//alert($("#type").ligerComboBox().getValue());
//	$("#formObj").submit();
// }

function save(){
	if($('#formObj').validate().form())
	{
	
		var formData = $("#formObj").getValues();
		
        var data = {};
        data = formData;
        
      /*  var tmp= getData();
        
      
        if(tmp==""){
			
			top.$.notice("请填写供应商目录及评价信息！");
			return;
		}*/
        //验证grid
        if(!validateGrid(deviceGrid))
		{
        	return false;
		}
			
      /*   if(deviceGrid.getData().length!=opAttr.length){
			$.ligerDialog.warn('请填写每个申请人的意见！');
     		return;
     	}
         */
        var applyData = deviceGrid.getData();
        var has = {};
		for (var i = 0; i < applyData.length; i++) {
			var applyop = applyData[i];

			if(has[applyop.other_applicants_id+""]==undefined&&has[applyop.other_applicants_id+""]==null){
				has[applyop.other_applicants_id+""]=i;
			}else{
				$.ligerDialog.warn('同一加班人不能有多个意见！');
	     		return;
			}
		}
		
        data["allowancefos"] = getData();
        $("body").mask("正在保存数据，请稍后！");
        $.ajax({
        	url: "employeeallowanceAction/savebasic.do",
            type: "POST",
            datatype: "json",
            contentType: "application/json; charset=utf-8",
           	data: $.ligerui.toJSON(data),
            success: function (data, stats) {
            	$("body").unmask();
                if (data["success"]) {
                	if(api.data.window.Qm)
                	{
                		api.data.window.Qm.refreshGrid();
                	}
                    top.$.dialog.notice({content:'保存成功'});
                    api.close();
                }else
                {
                	$.ligerDialog.error('提示：' + data.msg);
                }
            },
            error: function (data,stats) {
				alert(JSON.stringify(data));
            	$("body").unmask();
                $.ligerDialog.error('提示：' + data.msg);
            }
        });
	}
}


/*function selEmployee(){
	var windows=top.$.dialog({
		width: 800,
        height:500,
        max: false,
        min: false,
		lock:true,
		parent:api,
		title : "选择人员",
		data : {callback : function(data){
			if(data){
				$("#assessor_id").val(data.id);
				$("#ssesso").val(data.name);
			}
		}},
		
		content: 'url:app/tzsb/quality/subcontract/subreview_open_list.jsp'
	});
}*/

function selectFile(){
	var files_list = $.ligerDialog.open({ 
		 title: '选择', 
		 width: 600, 
		 height: 350,
		 parent:api, 
		 url: 'app/humanresources/select_allowaance_file.jsp',                                                                                                                                                                                                                                                                                        
		 data: {"window" : window},
		 buttons: [
		    { text: '确定', onclick: function(){
		    	var rows = files_list.frame.f_select();
			    if (!rows){
			    	top.$.notice("请选择行！");
			        return;
			    }
			    files_list.close();
			    $("#fk_request_id").val(rows[0].id);
			    $("#sn").val(rows[0].sn);
			    $('#applicants_id').val(rows[0].applicants_id);
			    $('#applicants').val(rows[0].applicants);
			    $('#other_applicants_id').val(rows[0].other_applicants_id);
			    $('#other_applicants').val(rows[0].other_applicants);
			    $('#department_id').val(rows[0].department_id);
			    $('#department').val(rows[0].department);
			    $('#overtime_type').val(rows[0].overtime_type);
			    $('#overtime_day').val(rows[0].overtime_day);
			    $('#overtime_hour').val(rows[0].overtime_hour);
			    $('#role_flag').val(rows[0].role_flag);
			    
			    changeOp();
			    
			    
		    } },
			{ text: '取消', onclick: function(){
				 files_list.close();
		    } }
		 ]
	});
	
}

function changeFlag(flag) {
	$("#back").hide();
	if (flag == "补休") {
		$("#overType").show();
		$("#money").hide();
		$("#subsidy_money").val("");
	} else {
		$("#back").show();
		$("#money").show();
		$("#overType").hide();
		$("#take_time_date").val("");
		$("#take_enddate").val("");
	}
}
//补休时间
function restDate() {
	startDate = $("#take_time_date").val();
	if (startDate == "") {
		$.ligerDialog.alert("请先选择开始时间~");
		document.getElementById("take_enddate").value = "";
		return;
	}
	endDate = $("#take_enddate").val();
	var startDate_text = new Date(startDate.replace('-', '/'));
	var endDate_text = new Date(endDate.replace('-', '/'));
	if ((endDate_text - startDate_text) < 0) {
		$.ligerDialog.alert("结束时间不能小于开始时间哦~");
		document.getElementById("take_enddate").value = "";
		return;
	}
}


function changeOp(){
	
	var other_applicants = $("#other_applicants").val();
	var other_applicants_id = $("#other_applicants_id").val();
		opAttr = [];
		var other_applicants_ids = other_applicants_id.split(",");
		
		var other_applicants = other_applicants.split(",");
		for (var i = 0; i < other_applicants_ids.length; i++) {
			var op = {};
			op["id"]=other_applicants_ids[i];
			op["text"]=other_applicants[i];
			opAttr[opAttr.length]=op;
			
		}
		
		//[{id:'',text:''},{id:'',text:''}]
		
	
	
}

/*function selectReceiver(isMain) {
	selectUnitOrUser('00', 1, "", "", function(datas) {
		if (datas) {
			 $("#assessor_id").val(datas.code)employeeallowanceAction
			  $("#assessor").val(datas.name.replace(/,/gi,"，"))
			 var pre = $("#depart_id").val();
			$("#depart_id").val(pre + (pre==""?"":",") + datas.code);
			var eid = "#depart_name";
			pre = $(eid).val();
			$(eid).val(pre + (pre==""?"":"，") + datas.name.replace(/,/gi,"，")); 
		}
	});
}
}*/


//validate="{required:true,maxlength:1000}"
//	ligerUi="{iconItems:[{icon:'add',click:selectFile}]}"

</script>
</head>
<body>

		<form name="formObj" id="formObj" method="post"
			action="employeeallowanceAction/savebasic.do"
			getAction="employeeallowanceAction/detail.do?id=${param.id}">
			
			<h1 id="sg2" align="center"
			style="padding: 5mm 0 2mm 0; font-family: 微软雅黑; font-size: 6mm;">加班补助申请
		</h1>
		</br>
			<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>
				补助申请:
			</div>
		</legend>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<input name="id" id="id" type="hidden" />
			<input id="flow_step" name="flow_step" type="hidden" value="0" />
			<input name="dept_op_id" id="dept_op_id" type="hidden" />
			<input name="leader_op_id" id="leader_op_id" type="hidden" />
			<input id="data_status" name="data_status" type="hidden" value="1" />
			<input name="handle_op" id="handle_op" type="hidden" />
			<input name="handle_op_id" id="handle_op_id" type="hidden" />
			<input name="enter_op" id="enter_op" type="hidden" />
			<input name="enter_op_id" id="enter_op_id" type="hidden" />
			<input name="enter_time" id="enter_time" type="hidden" />
			<input name="minister_audit" id="minister_audit" type="hidden" />
			<input name="minister_audit_id" id="minister_audit_id" type="hidden" />
			<input name="minister_audit_time" id="minister_audit_time" type="hidden" />
			<input name="minister_audit_remark" id="minister_audit_remark" type="hidden" />
			<input name="personnel_audit" id="personnel_audit" type="hidden" />
			<input name="personnel_audit_id" id="personnel_audit_id" type="hidden" />
			<input name="personnel_audit_time" id="personnel_audit_time" type="hidden" />
			<input name="personnel_audit_remark" id="personnel_audit_remark" type="hidden" />
			<input name="leader_audit" id="leader_audit" type="hidden" />
			<input name="leader_audit_op" id="leader_audit_op" type="hidden" />
			<input name="leader_audit_time" id="leader_audit_time" type="hidden" />
			<input name="leader_audit_remark" id="leader_audit_remark" type="hidden" />
			<input name="dean_audit" id="dean_audit" type="hidden" />
			<input name="dean_id" id="dean_id" type="hidden" />
			<input name="dean_audit_time" id="dean_audit_time" type="hidden" />
			<input name="dean_audit_remark" id="dean_audit_remark" type="hidden" />
			<input name="sub_status" id="sub_status" type="hidden" value="0"/>
			<input name="dept_opinion" id="dept_opinion" type="hidden" />
			<input name="dept_op_date" id="dept_op_date" type="hidden" />
			<input name="leader_opinion" id="leader_opinion" type="hidden" />
			<input name="leader_date" id="leader_date" type="hidden" />
			<input name="start_flow" id="start_flow" type="hidden" value="1"/>
			<input name="overtime_date_start" id="overtime_date_start" type="hidden"/>
			<input name="overtime_end_start" id="overtime_end_start" type="hidden"/>
			<input name="role_flag" id="role_flag" type="hidden" />
			<input name="applicants_id" id="applicants_id" type="hidden" />
			<input name="applicants" id="applicants" type="hidden" />
			<input name="fk_request_id" id="fk_request_id" type="hidden" />
					<tr>

						<td class="l-t-td-left">编号：</td>
						<td class="l-t-td-right" colspan="3">
						<input type="text"
							name="sn" id="sn"  ltype="text" readonly="readonly" onclick="selectFile()" ligerUi="{iconItems:[{icon:'add',click:selectFile}]}" />
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">加班人：</td>
				 		<td class="l-t-td-right">
					 		<input type="hidden" name="other_applicants_id" id="other_applicants_id" />
					 		<input type="text" id="other_applicants" name="other_applicants"  ltype="text" validate="{required:true,maxlength:1000}" onclick="selectFile()"
					 		 readonly="readonly"
					 			ligerUi="{iconItems:[{icon:'add',click:selectFile}]}" />
					 			
				 		</td>
						
						<td class="l-t-td-left">部门：</td>
						<td class="l-t-td-right">
						<input name="department"  id="department" type="text" ltype='text' readonly="readonly" />
						<input type="hidden" name="department_id" id="department_id" />
						</td>
						
					</tr>
					<tr>
						<td class="l-t-td-left">加班情形：</td>
						<td class="l-t-td-right"><input type="text" id="overtime_type"
							name="overtime_type" ltype='text' readonly="readonly"
							 /></td>
					 </tr>
					<tr>
					<td class="l-t-td-left">加班时间（天数）：</td>
				 		<td class="l-t-td-right">
				 		<input type="text" id="overtime_day" name="overtime_day" ltype="text" readonly="readonly"  /></td>
					<td class="l-t-td-left">加班时间（小时）：</td>
				 		<td class="l-t-td-right">
				 		<input type="text" id="overtime_hour" name="overtime_hour" ltype="text" readonly="readonly"  /></td>
				 								
					</tr>
					

				
			</table>
	</fieldset>
	<fieldset class="l-fieldset" >
		<legend class="l-legend">
			
		<div>补助详情 </div>
		</legend>
		<div style="height:300px;" id="device"></div>
	</fieldset>				
		</fieldset>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">补助总金额：</td>
				 		<td class="l-t-td-right">
				 		<input type="text" id="sumMoney" name="sumMoney" ltype="text" readonly="readonly" value="0.00" ligerui="{suffix:'元'}" /></td>
				 								
					</tr>

				
			</table>
		
		</form>
		<div id="panl_select"></div>
</body>
</html>
