<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head pageStatus="${param.status}">
		<title></title>
		
		
		
		
		<!-- 每个页面引入，页面编码、引入js，页面标题 -->
		<%@include file="/k/kui-base-form.jsp"%>
		
	
		<script type="text/javascript">
		
	
		
		

		
		$(function() {
			
		
				$("#form1").initForm({ //参数设置示例
					toolbar : [ {
						text : '确定',
						//id : 'save',
						icon : 'save',
					//	click : save
					}
				
					, 
					{
						text : '关闭',
						//id : 'close',
						icon : 'cancel',
						//click : close
					} ],
					getSuccess : function(res) {
					
					
						
							
						$("#form1").setValues(res.InspectionInfo);	
						$("#form1").setValues(res.DeviceDocument);	
						$("#form1").setValues(res.Inspection);	
						
					}
				});
			
			
			})
		
	
			
		
			
	
		
	
	  
	
		
		
			
	</script>
	</head>
	<body>
	

		<form id="form1"  getAction="department/basic/getInfo.do?id=${param.id}">
		
		<fieldset class="l-fieldset">
					<legend class="l-legend">
						<div>
							业务信息
						</div>
					</legend>
		<table border="1" cellpadding="3" cellspacing="0" width=""
				class="l-detail-table">
					<tr>
						<td class="l-t-td-left">检验性质：</td>
						<td class="l-t-td-right"><u:combo name="check_type"
							code="BASE_CHECK_TYPE" validate="required:true" /></td>
						<td class="l-t-td-left">业务流水号：</td>
						<td class="l-t-td-right"><input type="text" ltype="text"  name="sn" ></td>
					</tr>
					<tr>
						<td class="l-t-td-left">受检单位：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="com_name" ></td>
						<td class="l-t-td-left">设备类别：</td>
						<td class="l-t-td-right"><input type="text" id="device_sort_code" name="device_sort_code" ltype="select" validate="{required:true}"  ligerui="{
				
				
				initValue:'3000',
				
				tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' "/>}
				
				}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">设备名称：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="device_name" ></td>
						<td class="l-t-td-left">应收金额：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="advance_fees" ></td>
					</tr>
					<tr>
						<td class="l-t-td-left">检验日期：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="advance_time" /></td>
						<td class="l-t-td-left">检验部门：</td>
						<td class="l-t-td-right"><input type="text"   name="check_unit_id" id="check_unit_id"  ltype="select"  validate="{required:true}"
						ligerui="{
					
						
						tree:{checkbox:false,data: <u:dict sql="select t.id, t.parent_id, t.id code, t.org_name text from sys_org t where t.parent_id is not null"/>}
						}"/></td>
					</tr>
					<tr>
						<td class="l-t-td-left">检验联系人：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="check_op" ></td>
						<td class="l-t-td-left">联系人电话：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="check_tel" ></td>
					</tr>
					
					<tr>
						<td class="l-t-td-left">项目负责人：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="item_op_name" ></td>
						<td class="l-t-td-left">参检人员：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="check_op_name" ></td>
					</tr>
					
					<tr>
						<td class="l-t-td-left">录入人员：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="enter_op_name" ></td>
						<td class="l-t-td-left">录入时间：</td>
						<td class="l-t-td-right"><input type="text" ltype="text" name="enter_time" ></td>
					</tr>
					
				</table>
	</fieldset>
		</form>
		
			

			
			

	
	</body>
</html>