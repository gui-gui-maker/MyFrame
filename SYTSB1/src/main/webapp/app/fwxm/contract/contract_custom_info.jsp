<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var rkstatusData = <u:dict code="BASE_COMPANY_TYPE"/>
	$(function () {
		$("input[name='com_type']").ligerComboBox({ 
			isShowCheckBox: true, 
			isMultiSelect: true,
            data: rkstatusData,
            onSelected:function(value,text)
            {
            	$('input[name="com_type"]').val(text)
            }
     	}); 
	});

	// 选择系统人员
	function selectLoginUser(){	
		top.$.dialog({
			parent: api,
			width : 700, 
			height : 500, 
			lock : true, 
			title:"选择系统用户",
			content: 'url:app/enter/choose_user_list.jsp',
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(id,name){
		$('#login_user_id').val(id);		// 系统用户ID
		$('#login_user_name').val(name);	// 系统用户姓名
	}
	function getInfoByOrgID(orgid){
		$.ajax({
			url : "enter/basic/getEnterByCode.do?code=" + orgid,
			type : "POST",
			async : false,
			success : function(callbackData1) {
				//alert(callbackData.content.com_name);
				if(callbackData1.success){ 
					var callbackData = callbackData1.content;
					
						$("#licence_reg_no").attr("readonly", false);
						$("#com_name").attr("readonly", false);
						$.ligerui.get("formObjopSave").setEnabled(true);
						$("#com_name").val(callbackData.com_name);
						$("#com_address").val(callbackData.com_address);
						$("#com_legal_rep").val(callbackData.com_legal_rep);
						$("#com_area_code").val(callbackData.com_area_code);
						$("#com_zip_code").val(callbackData.com_zip_code);
						$("#com_tel").val(callbackData.com_tel);
						$("#com_contact_man").val(callbackData.com_contact_man);
						$("#com_email").val(callbackData.com_email);
						$("#com_web_site").val(callbackData.com_web_site);
						$("#com_fund_date").val(callbackData.com_fund_date);
						$("#registered_funds").val(callbackData.registered_funds);
						$("#approve_depart").val(callbackData.approve_depart);
						$("#licence_reg_no").val(callbackData.licence_reg_no);
					
				}else{
					alert(callbackData1.message);
				}
				
				
			} 
		});
	}
	
	function selectType(){
		top.$.dialog({
			parent: api,
			width : 700, 
			height : 500, 
			lock : true, 
			title:"选择客户分类",
			content: 'url:app/fwxm/contract/custom/custom_type_list.jsp',
			data : {"window" : window}
		});
	}
	
	function selectLevel(){
		top.$.dialog({
			parent: api,
			width : 700, 
			height : 500, 
			lock : true, 
			title:"选择客户分级",
			content: 'url:app/fwxm/contract/custom/custom_level_list.jsp',
			data : {"window" : window}
		});
	}
	
	function selectLevelBack(id,levels,main_product){
		$("#custom_level_id").val(id);
		$("#custom_level").val(levels);
		$("#product").val(main_product);
	}
	
	function selectTypeBack(id,type){
		$("#custom_type_id").val(id);
		$("#custom_type").val(type);
	}
	
	</script>
	</head>
<body>


				
	
	<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<input id="id" name="id" type="hidden" />
		<input type="hidden" id="com_status" name="com_status"  value="0"/>
		<tr> 
        	<td class="l-t-td-left"> 组织机构代码:</td>
        	<td class="l-t-td-right"> 
        		<input id="com_code" name="com_code" type="text" ltype='text' validate="{maxlength:32}"
        		<c:if test="${'add' eq param.status}">onChange="getInfoByOrgID(this.value);"</c:if> />
        	</td>
			<td class="l-t-td-left"> 营业执照注册号:</td>
        	<td class="l-t-td-right"> 
        		<input id="licence_reg_no" name="licence_reg_no" type="text" ltype='text' validate="{maxlength:100}"/>
        	</td>
		</tr>
       	<tr> 
	        <td class="l-t-td-left"> 单位名称:</td>
	        <td class="l-t-td-right"> 
	        	<input id="com_name" name="com_name" type="text" ltype='text' validate="{required:true,maxlength:255}"/>
	        </td>
	        <td class="l-t-td-left"> 法定代表人:</td>
	        <td class="l-t-td-right"> 
	        	<input id="com_legal_rep" name="com_legal_rep" type="text" ltype='text' validate="{maxlength:32}"/>
	        </td>
		</tr>
			<tr> 
	        <td class="l-t-td-left"> 客户分类:</td>
	        <td class="l-t-td-right"> 
	        <input id="custom_type_id" name="custom_type_id" type="hidden" />
	        	<input id="custom_type" name="custom_type" type="text" ltype='text' validate="{required:true,maxlength:255}"
				onclick="selectType()"
				ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectType()}}]}"/>
	        
	        	<!-- <u:combo name="custom_type" code="contract_custom_type" modify="true" validate="{required:true}"></u:combo> -->
	        </td>
	        <td class="l-t-td-left"> 客户分级:</td>
	        <td class="l-t-td-right"> 
	         <input id="custom_level_id" name="custom_level_id" type="hidden" />
	        	<input id="custom_level" name="custom_level" type="text" ltype='text' validate="{required:true,maxlength:255}"
				onclick="selectLevel()"
				ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectLevel()}}]}"/>
	        	<!-- <u:combo name="custom_level" code="contract_custom_level" modify="true"  validate="{required:true}"></u:combo> -->
	        </td>
		</tr>
			<tr> 
	        <td class="l-t-td-left"> 使用特种设备类型:</td>
	        <td class="l-t-td-right"> 
	        	<u:combo name="use_product_type" code="device_type" validate="{required:true}"></u:combo>
	        </td>
	        <td class="l-t-td-left"> 主要生产产品:</td>
	        <td class="l-t-td-right"> 
	        	<input id="product" name="product" type="text" ltype='text' validate="{required:false,maxlength:200}"/>
	        </td>
		</tr>
		<tr> 
			<td class="l-t-td-left"> 地区编码:</td>
        	<td class="l-t-td-right"> 
        		<input id="com_area_code" name="com_area_code" type="text" ltype='text' validate="{required:false,maxlength:8}"/>
        	</td>
        	<td class="l-t-td-left"> 单位地址:</td>
        	<td class="l-t-td-right"> 
        		<input id="com_address" name="com_address" type="text" ltype='text' validate="{required:true,maxlength:255}"/>
        	</td>
		</tr>
		<tr> 
       		<td class="l-t-td-left"> 邮政编码:</td>
        	<td class="l-t-td-right"> 
        		<input name="com_zip_code" type="text" ltype='text' validate="{required:false,maxlength:8}"/>
        	</td>
        	<td class="l-t-td-left"> 单位电话:</td>
        	<td class="l-t-td-right"> 
        		<input id="com_tel" name="com_tel" type="text" ltype='text' validate="{maxlength:32}"/>
        	</td>
		</tr>

        <tr> 
         <td class="l-t-td-left"> 营业执照登记机构:</td>
	        <td class="l-t-td-right"> 
	        	<input name="licence_depart" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
	        </td>
	        <td class="l-t-td-left">许可证级别及编号:</td>
        	<td class="l-t-td-right"> 
        		<input name="license_num" type="text" ltype='text' validate="{maxlength:20}"/>
        	</td>
       	</tr>
       	<tr> 
        	<td class="l-t-td-left"> 联系人:</td>
        	<td class="l-t-td-right"> 
        		<input id="com_contact_man" name="com_contact_man" type="text" ltype='text' validate="{maxlength:32}"/>
        	</td>
        	<td class="l-t-td-left"> 传真:</td>
        	<td class="l-t-td-right"> 
        		<input name="com_fax" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
        	</td>
       	</tr>
       	<tr> 
        	<td class="l-t-td-left"> 电子信箱:</td>
        	<td class="l-t-td-right"> 
        		<input id="com_email"  name="com_email" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
        	</td>
	        <td class="l-t-td-left"> 网址:</td>
	        <td class="l-t-td-right"> 
	        	<input id="com_web_site" name="com_web_site" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
	        </td>
       	</tr>
       	<tr> 
	        <td class="l-t-td-left"> 安全管理部门:</td>
	        <td class="l-t-td-right"> 
	        	<input name="security_depart" type="text" ltype='text' validate="{required:false,maxlength:255}"/>
	        </td>
	        <td class="l-t-td-left"> 安全管理人员:</td>
	        <td class="l-t-td-right"> 
	        	<input name="security" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
	        </td>
       	</tr>
       	<tr> 
	        <td class="l-t-td-left"> 资格证书:</td>
	        <td class="l-t-td-right"> 
	        	<input name="qualification_certificate" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
	        </td>
	        <td class="l-t-td-left"> 资格证书号:</td>
	        <td class="l-t-td-right"> 
	        	<input name="qualification_certificate_no" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
	        </td>
       	</tr>
       	<tr> 
	        <td class="l-t-td-left"> 成立日期:</td>
	        <td class="l-t-td-right"> 
	        	<input id="com_fund_date" name="com_fund_date" type="text" ltype='date' ligerui="{initValue:'',format:'yyyy-MM-dd'}" validate="{required:false}}"/>
	        </td>
	        <td class="l-t-td-left"> 批准成立机关:</td>
	        <td class="l-t-td-right"> 
	        	<input id="approve_depart" name="approve_depart" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
	        </td>
       	</tr>
       	<tr> 
	        <td class="l-t-td-left"> 单位总人数(人):</td>
	        <td class="l-t-td-right"> 
	        	<input name="employee_count" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
	        </td>
	        <td class="l-t-td-left"> 注册资金(万元):</td>
	        <td class="l-t-td-right"> 
	        	<input id="registered_funds" name="registered_funds" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
	        </td>
       	</tr>
       	<tr> 
	        <td class="l-t-td-left"> 固定资产(万元):</td>
	        <td class="l-t-td-right"> 
	        	<input name="permanent_assets" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
	        </td>
	        <td class="l-t-td-left"> 管理者代表:</td>
	        <td class="l-t-td-right"> 
	        	<input name="manager_deputy" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
	        </td>
       	</tr>
       	<tr> 
	        <td class="l-t-td-left"> 管理者代表职位:</td>
	        <td class="l-t-td-right"> 
	        	<input name="manager_deputy_pos" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
	        </td>
	        <td class="l-t-td-left"> 质量保证负责人:</td>
	        <td class="l-t-td-right"> 
	        	<input name="quality_principal" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
	        </td>
       	</tr>
       	<tr> 
	        <td class="l-t-td-left"> 主管部门:</td>
	        <td class="l-t-td-right"> 
	        	<input name="charge_depart" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
	        </td>
	       	<td class="l-t-td-left"> 授权维护单位:</td>
	        <td class="l-t-td-right"> 
	        	<input name="authorize_dapart" type="text" ltype='text' validate="{required:false,maxlength:1000}"/>
	        </td>
       	</tr>
		<tr>	
			
			<td class="l-t-td-left">备注：</td>
			<td class="l-t-td-right"><textarea name="remark" cols="20" rows="2" class="l-textarea"></textarea>	</td>	
		</tr>
	</table>
</body>
</html>