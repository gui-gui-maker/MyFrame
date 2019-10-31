<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String orgId = user.getDepartment().getId();
	String orgName = user.getDepartment().getOrgName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>检验员报检</title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-form.jsp"%>
<!-- <script type="text/javascript"
	src="app/tzsb/data/device/shortcutIns/report_insp.js"></script> -->
<script type="text/javascript"
	src="app/tzsb/inspection/flow/insing/support/ChipCombobox.js"></script>


<script type="text/javascript">
	var pageStatus = "${param.status}";
	var check_type = "";
	var deviceId =  "${param.id}";
	
	
	
	$(function() {
		$("#form-insp")
				.initForm(
						{ //参数设置示例
							toolbar : [ {
								text : '保存',
								icon : 'save',
								click : save
							}, {
								text : '关闭',
								//id : 'close',
								icon : 'cancel',
								click : close
							} ],
							success : function(resp) {//处理成功
								//api.close();
								if (resp.success) {
									top.$.notice("任务分配成功！");
									//var infoId = resp.infoId;
									//var orgId = resp.orgId;
									//var fkReportId = resp.reportId;
									//var deviceId = resp.deviceId;
									//api.data.window.openReportI(infoId,orgId,fkReportId,deviceId);
									api.data.window.submitAction();
									api.close();
								} else {
									$.ligerDialog.error('生成失败:' + resp.message);
								}
							},
							getSuccess : function(res) {
								if (res.success) {
									$('#reportComName').val(api.data.com_name);
									$('#reportComAddress').val(api.data.com_address);
									$('#fk_company_info_use_id').val(api.data.com_id);
									$('#com_name').val(api.data.com_name);
									//$("#form-insp").setValues({fk_unit_id:comId,com_name:comName});
								}

							}
						});
	});
	
	function reReportTypes() {
		var check_type = $('#check_type').ligerComboBox().getValue();
		var device_sort = $('#device_sort_code').ligerComboBox().getValue();
		if(check_type!="" && device_sort!=""){
			$.get("reportOpinspAction/getReportTypes.do?check_type=" + check_type
					+ "&device_type=" + device_sort, function(data) {
				try {
					setReportTypes(JSON.parse(data));
					$("#quick_fkReportId").ligerGetComboBoxManager().setData(
							JSON.parse(data));
				} catch (e) {
	
				}
			});
		}
	}
	function close(url) {
		api.close();
	}
	function save(url) {
		
		var temp = $('#check_op_name').val();
		
		
		
		if(temp.indexOf(",")!=-1){
			var tp = temp.split(",");
			
			if(tp.length>2){
				
				$.ligerDialog.alert("参检人员只能选择2人，请重新选择！");
				return;
				
			}
			
		}
		
		
		$('#form-insp').submit();

	}
	
	function selectorg() {

		top.$.dialog({
					parent : api,
					width : 800,
					height : 550,
					lock : true,
					title : "选择企业信息",
					content : 'url:app/tzsb/inspection/flow/insing/support/enter_list.jsp',
					data : {
						"parentWindow" : window
					}
				});
	}
	//选择报检单位回调
	function callBack(id, name, code, address) {
		//设备主表
		$('#fk_company_info_use_id').val(id);
		$('#company_name').val(name);
		//检验主表
		$('#com_name').val(name);
		$('#com_address').val(address);
		//检验明细表
		$('#reportComName').val(name);
		$('#reportComAddress').val(address);

	}
	//选择报检设备回调方法
	function onChooseDeviceBack() {
		reReportTypes();

	}

	function afterSortSelected(val, text) {
		$('#device_sort').val(text);
		reReportTypes();
	}

	//设置报告类型
	function setReportTypes(data) {
		reportType = data;

	}

	
	
	function afterSortSelectedO(val, text) {
		$('#check_op_name').val(text);
	}
	function afterSortSelectedI(val, text) {
		$('#item_op_name').val(text);
	}

	

</script>
</head>
<body>
	<form id="form-insp" action="inspectionInfo/basic/addPlan.do?ids=${param.ids}"
		getAction="inspectionInfo/basic/getInfo.do?id=${param.id}">
	
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>检验业务信息</div>
			</legend>
			<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
				<tr>
				
				<!--  <input name="check_unit_id" value='' type="hidden" />-->
					
					<td class="l-t-td-left">报告类型：</td>
					<td class="l-t-td-right" colspan="3"><input type="text"
						id="report_type" ltype="select" name="report_type"
						validate="{required:true}" 
						ligerui="{
							//initValue:'3000',
							readonly:true,
							data: <u:dict sql="select distinct t.fk_report_id,r.report_name   from base_unit_flow t,BASE_REPORTS r where t.fk_report_id=r.id and t.device_type='2000' and t.report_name like '在用常压%' and r.flag='1'"/>,
							}" />
					</td>
					
				</tr>
			
				<tr>
				<td class="l-t-td-left">检验日期：</td>
					<td class="l-t-td-right"><input name="advance_time"
						type="text" ltype="date" validate="{required:true}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}"
						id="advance_time" /></td>
					<td class="l-t-td-left">检验负责人：</td>
					<td class="l-t-td-right"><input name="item_op_name"
						id="item_op_name" type="hidden"/> 	
						<input type="text" name="item_op_id" id="item_op_id" ltype="select"  validate="{required:true}" ligerui="{
							readonly:true,
							onSelected:afterSortSelectedI,
							tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
							}"/>
					</td>
					
				</tr>
				<tr>
					<td class="l-t-td-left">参检人员：</td>
					<td class="l-t-td-right" colspan="3"><input   name="check_op_name" id="check_op_name" type="hidden"/> 
					<input type="text" name="check_op_id" id="check_op_id" ltype="select"  validate="{required:true}" ligerui="{
							readonly:true,
							onSelected:afterSortSelectedO,
							tree:{checkbox:true,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
							}"/>
					</td>
				</tr>
				
				
			</table>
		</fieldset>
	</form>
</body>
</html>