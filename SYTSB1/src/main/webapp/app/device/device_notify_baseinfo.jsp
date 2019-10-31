<%@page import="com.khnt.utils.DateUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
</head>
<body>
<form name="formObj" id="formObj" method="post"  >
   		 <input name="id"   type="hidden" id="appId"/> 
		<input name="created_by"   type="hidden" id="created_by" /> 
		<input name="created_date"   type="hidden" id="created_date"/> 
	   <fieldset class="l-fieldset TzsbApplication">
		<legend class="l-legend">基本信息</legend>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">施工单位名称：</td>
				<td class="l-t-td-right" colspan="3">
                 <input  name="fk_construction_units_id" id="fk_construction_units_id-1" type="hidden" /> 
						<input type="text" id="construction_units_name-1" name="construction_units_name"
						ltype="text" validate="{required:true}" onclick="selectorg('0')"
						ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('0')}}]}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">设备类别：</td>
				<td class="l-t-td-right">
						<input  id="tzsb_application_device_sort" name="device_sort" type="hidden"/>
				<input type="text"
					id="tzsb_application_device_sort_code" name="device_sort_code" ltype="select"
					validate="{required:true}"
					ligerui="{
				selectBoxHeight:400,
				initValue:'3000',
				readonly:true,
				tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' "/>}
				}" />
				</td>

				<td class="l-t-td-left">告知书编号：</td>
				<td class="l-t-td-right"><input name="application_code"
					validate="{required:false,maxlength:128}" ltype='text' readonly="readonly"
					id="application_code" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">施工类别：</td>
				<td class="l-t-td-right">
				<u:combo name="construct_sort" code="BASE_CONSTRUCT_SORT"></u:combo>
				<td class="l-t-td-left">告知日期：</td>
				<td class="l-t-td-right">
				<input name="application_date" class="date_input"
					id="application_date" type="text" ltype="date"
					validate="{required:false}"
					ligerui="{initValue:'<%=DateUtil.getCurrentDateTime() %>',format:'yyyy-MM-dd'}" />
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="l-fieldset" id="TzsbAppEngineerSituations">
		<legend class="l-legend">工程情况</legend>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<input name="id"   type="hidden" /> 
		<input name="fk_tzsb_application_id"   type="hidden" /> 
			<tr>
				<td class="l-t-td-left">工程名称：</td>
				<td class="l-t-td-right" colspan="3"><input name="engineering_name"
					validate="{required:false,maxlength:200}" ltype='text'
					id="engineering_name" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">建设单位名称：</td>
				<td class="l-t-td-right" colspan="3">
			<input name="fk_construct_units_id" id="tzsb_app_engineersituations_fk_construct_units_id" type="hidden" /> 
					<input type="text" id="tzsb_app_engineersituations_company_name" name="tzsb_app_engineersituations_company_name"
					ltype="text" validate="{required:true}" onclick="selectorg('1')"
					ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('1')}}]}" />
				
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">工程负责人：</td>
				<td class="l-t-td-right"><input name="engineering_principal"
					validate="{required:false,maxlength:64}" ltype='text'
					id="engineering_principal" /></td>
				<td class="l-t-td-left">负责人电话：</td>
				<td class="l-t-td-right"><input name="principal_tel"
					validate="{required:false,maxlength:64}" ltype='text'
					id="principal_tel" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">工程设计单位：</td>
				<td class="l-t-td-right" colspan="3">
<input name="engineering_devise_units" id="engineering_devise_units" type="hidden" />
					<input type="text" id="engineering_devise_units_name" name="engineering_devise_units_name"
					ltype="text" validate="{required:false}" onclick="selectorg('2')"
					ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('2')}}]}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">合同编号：</td>
				<td class="l-t-td-right"><input name="pact_num"
					validate="{required:false,maxlength:64}" ltype='text'
					id="pact_num" /></td>
				<td class="l-t-td-left">合同签订日期：</td>
				<td class="l-t-td-right">
<input name="pact_date" id="pact_date" type="text" ltype="date" class="date_input" validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">主要施工项目：</td>
				<td class="l-t-td-right"><input name="construct_item"
					validate="{required:false,maxlength:64}" ltype='text'
					id="construct_item" /></td>
				<td class="l-t-td-left">设备数：</td>
				<td class="l-t-td-right"><input name="device_num"
					validate="{required:false,maxlength:64}" ltype='text'
					id="device_num" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">工程计划开工日期：</td>
				<td class="l-t-td-right">
			<input name="plan_begin_date" id="plan_begin_date" type="text" ltype="date" class="date_input"
					validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
				</td>
				<td class="l-t-td-left">工程计划竣工日期：</td>
				<td class="l-t-td-right">
			<input name="plan_end_date" id="plan_end_date" type="text" ltype="date" class="date_input"
					validate="{required:false}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">工程土地总预算：</td>
				<td class="l-t-td-right"><input name="construction_total_budget"
					validate="{required:false,maxlength:64}" ltype='text'
					id="construction_total_budget" /></td>
				<td class="l-t-td-left">工程设备总预算：</td>
				<td class="l-t-td-right"><input name="device_total_budget"
					validate="{required:false,maxlength:64}" ltype='text'
					id="device_total_budget" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left" style="width: 150px">工程施工行政区划：</td>
				<td class="l-t-td-right">
					<input type="text"
					id="engineering_district" name="engineering_district" ltype="select"
					validate="{required:true}"
					ligerui="{
					initValue:'511101',
					readonly:true,
					data: <u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE"/>,
				}" />

				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">工程施工地址：</td>
				<td class="l-t-td-right" colspan="3"><input name="engineering_address"
					validate="{required:false,maxlength:64}" ltype='text'
					id="engineering_address" /></td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="l-fieldset TzsbApplication">
		<legend class="l-legend">施工单位基本情况</legend>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">单位名称：</td>
				<td class="l-t-td-right" colspan="3"><input name="com_name" readonly="readonly"
					validate="{required:false,maxlength:64}" ltype='text'
					id="com_name" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">单位地址：</td>
				<td class="l-t-td-right" colspan="3"><input name="com_address" readonly="readonly"
					validate="{required:false,maxlength:64}" ltype='text'
					id="com_address" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">组织机构代码：</td>
				<td class="l-t-td-right"><input name="com_code" readonly="readonly"
					validate="{required:false,maxlength:64}" ltype='text'
					id="com_code" /></td>
				<td class="l-t-td-left">现法定代表人：</td>
				<td class="l-t-td-right"><input name="com_legal_rep"
					validate="{required:false,maxlength:64}" ltype='text' readonly="readonly"
					id="com_legal_rep" /></td>
			</tr>
						<tr>
				<td class="l-t-td-left">许可证编号：</td>
				<td class="l-t-td-right"><input name="construction_units_permit_no"
					validate="{required:false,maxlength:64}" ltype='text'
					id="construction_units_permit_no" /></td>
				<td class="l-t-td-left">许可证有效期：</td>
				<td class="l-t-td-right">
			<input name="construction_units_permit_date"
					id="construction_units_permit_date" type="text" ltype="date" class="date_input"
					validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
				</td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="l-fieldset" id="TzsbAppConstrucationOrg">
		<legend class="l-legend">现场施工组织</legend>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<input name="id"   type="hidden" /> 
		<input name="fk_tzsb_application_id"   type="hidden" /> 
			<tr>
				<td class="l-t-td-left">现场施工机构：</td>
				<td class="l-t-td-right" colspan="3">
			<input
					name="fk_construction_units_id" id="fk_construction_units_id-2"
					type="hidden" /> <input type="text" id="construction_units_name-2"
					name="construction_units_name" ltype="text" validate="{required:true}"
					onclick="selectorg('3')"
					ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('3')}}]}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">施工现场负责人：</td>
				<td class="l-t-td-right"><input name="construction_principal"
					validate="{required:false,maxlength:64}" ltype='text'
					id="device_name" /></td>
				<td class="l-t-td-left">现场负责人移动电话：</td>
				<td class="l-t-td-right"><input name="construction_principal_mobil"
					validate="{required:false,maxlength:128}" ltype='text'
					id="registration_num" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">现场技术负责人：</td>
				<td class="l-t-td-right"><input name="technical_director"
					validate="{required:false,maxlength:64}" ltype='text'
					id="construction_principal" /></td>
				<td class="l-t-td-left">技术负责人移动电话：</td>
				<td class="l-t-td-right"><input name="technical_director_mobil"
					validate="{required:false,maxlength:128}" ltype='text'
					id="technical_director_mobil" /></td>
			</tr>
		</table>
	</fieldset>
		<fieldset class="l-fieldset TzsbApplication" >
			<legend class="l-legend">备注</legend>
			<div>
				<textarea rows="3" cols="20" name="application_remark" id="application_remark"></textarea>
			</div>
		</fieldset>
		<fieldset class="l-fieldset TzsbApplication">
			<legend class="l-legend">安全监察机构意见</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">接收告知书人员：</td>
					<td class="l-t-td-right" colspan="1"><input name="receive_op"
						validate="{required:false,maxlength:64}" ltype='text'
						id="receive_op" /></td>
					<td class="l-t-td-left">接收日期：</td>
					<td class="l-t-td-right">
			 <input name="receive_date"
						id="receive_date" type="text" ltype="date" class="date_input"
						validate="{required:false}"
						ligerui="{initValue:'<%=DateUtil.getCurrentDateTime() %>',format:'yyyy-MM-dd'}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">监察机构意见：</td>
					<td class="l-t-td-right" colspan="3"><input name=supervise_results
						validate="{required:false,maxlength:64}" ltype='text'
						id="supervise_results" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">意见通知书编号：</td>
					<td class="l-t-td-right" colspan="1"><input name="results_notice_num"
						validate="{required:false,maxlength:64}" ltype='text'
						id="results_notice_num" /></td>
					<td class="l-t-td-left">发出意见书日期：</td>
					<td class="l-t-td-right">
			<input name="results_notice_date"
						id="results_notice_date" type="text" ltype="date" class="date_input"
						validate="{required:false}"
						ligerui="{initValue:'<%=DateUtil.getCurrentDateTime() %>',format:'yyyy-MM-dd'}" />
					</td>
				</tr>
			</table>
		</fieldset>
		</form>
</body>
</html>