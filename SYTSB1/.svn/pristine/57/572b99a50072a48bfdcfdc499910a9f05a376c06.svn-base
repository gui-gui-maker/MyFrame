<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
<%
	//是否拥有可修改设备注册代码的权限
	boolean hasMdyDRC = StringUtil.isNotEmpty(request.getParameter("mdy_drc")) && "1".equals(request.getParameter("mdy_drc"))?true:false;	
%>




function getNum(numCode){	
var myDate = new Date();
var curYear = myDate.getFullYear();   
var year = numCode.substring(10,14);
if(year>=curYear){
	$.ligerDialog.alert("设备注册代码有误，只能补录往年的设备，请核实！", "提示");
	return;
}
$.getJSON("device/basic/getNum.do?device_code="+numCode, function(resp){
		
		if(resp.success){
		
		
			$("#device_registration_code_change").val("");
			$.ligerDialog.alert("注册代码已存在，请核实！", "提示");
			
			return;
		}
		
	})

}

function selectCode(){
	
	var type ='${param.device_type}';
	var category = '${param.device_category}'
	top.$.dialog({
		width : 280,
		height : 550,
		lock : true,
		title : "设备名称",
		content : 'url:app/device/device_sort_type.jsp?status=detail&type='+type+'&category='+category,
				
		data : {
			"window" : window
		}
	});
}




function callOK(code, name)
{ 
   
	var type =${param.device_type};
	var category = '${param.device_category}'
	
	
	if(type==6 || category == '7310'){
		$('#device_sort_code').val(code);
		$('#device_code').val(name);
		
	}else{
		$('#device_sort_code').val(code);
		$('#device_name').val(name);
	}
}



function selectArea(){
	//var type =${param.device_type};
	var type = '${param.device_category}';
	top.$.dialog({
		width : 280,
		height : 550,
		lock : true,
		title : "设备所在区域",
		content : 'url:app/device/device_area_code.jsp?status=detail&type='+type,
		data : {
			"window" : window
		}
	});
}

function callArea(code, name)
{ 
	$('#device_area_code').val(code);
	$('#device_area_name').val(name);
}

function selectStreet(){
	var type = '${param.device_type}';
	var area_code = $("#device_area_code").val();
	top.$.dialog({
		width : 280,
		height : 550,
		lock : true,
		title : "设备所在街道",
		content : 'url:app/device/device_street_code.jsp?status=detail&type='+type+'&area_code='+area_code,
		data : {
			"window" : window
		}
	});
}

function callStreet(code, name){ 
	$('#device_street_code').val(code);
	$('#device_street_name').val(name);
}

function setValue(valuex,name){
	if(valuex==""){
		return;
	}
	if(name=='device_name'){
		$('#device_name').val(valuex)
	}
}
	
	</script>
<body>

				
	
           <table cellpadding="3" cellspacing="0" class="l-detail-table">
           
       
			<tr>
				<td class="l-t-td-left">
					<input id="basic_id" name="id" type="hidden" />
					<input  name="created_date" type="hidden" />
					<input  name="created_by" type="hidden" />
					<input  name="last_upd_by" type="hidden" />
					<input  name="last_upd_date" type="hidden" />
					<input  name="inspect_date" type="hidden" />
					<input  name="inspect_next_date" type="hidden" />
					<input  name="is_cur_check" type="hidden" />
				</td>
				<td class="l-t-td-right"></td>
				<td class="l-t-td-left"></td>
				<td class="l-t-td-right"></td>
			</tr>
			
			
				<tr>
						<td class="l-t-td-left">使用单位：</td>
						<td class="l-t-td-right">
							<input name="fk_company_info_use_id" id="fk_company_info_use_id" type="hidden" />
							<input name="com_code" id="com_code" type="hidden" />
							
							<!--<input type="text" ltype='text'  name="company_name"
								validate="{required:false,maxlength:50}" readonly="readonly" id="make_units_name" ligerui="{suffix:'<input type=button value=选择 class='l-button3' onclick=selectorg(0)>'}" />-->
							
						  <input type="text" id="company_name" name="company_name"   ltype="text"  validate="{required:true}" onclick="selectorg('0')"
											ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('0')}}]}"/>
						</td>
						<td class="l-t-td-left">使用地址：</td>
						<td class="l-t-td-right"><input name="com_address"
							validate="{required:false,maxlength:300}" ltype='text' id="com_address" />
						</td>
			     </tr>
				<tr>
					<td class="l-t-td-left">制造单位：</td>
					<td class="l-t-td-right" >
							<input name="fk_company_info_make_id" id="fk_company_info_make_id" type="hidden" />
							<input type="text" id="make_units_name" name="make_units_name"   ltype="text"  validate="{required:false}" onclick="selectorg('1')"
											ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('1')}}]}"/>
							
					</td>			
					<td class="l-t-td-left">制造日期：</td>
							<td class="l-t-td-right"><input name="make_date"
								validate="{required:false,maxlength:100}" ltype='text' id="make_date" />
					</td>			
			  </tr>			
					
			  <tr>
				<td class="l-t-td-left" style="width: 150px">安全管理人员：</td>
				<td class="l-t-td-right"><input name="security_op"
					validate="{required:false,maxlength:64}" ltype="text" id="security_op" /></td>
				<td class="l-t-td-left" style="width: 150px">联系电话：</td>
				<td class="l-t-td-right"><input name="security_tel"
					validate="{required:true,maxlength:64}"
					value="" type="text" ltype="text" id="security_tel" /></td>
							
			</tr>	
			
				<tr>
				
				<td class="l-t-td-left">设备类别：</td>
				<td class="l-t-td-right">
			
						<input type="text" id="device_sort" name="device_sort" ltype="select" validate="{required:true}"  ligerui="{
							value:'2600',
							readonly:true,
							tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' 
						 	and t.code_table_values_id in (select t3.id from PUB_CODE_TABLE_VALUES  t3 where t3.sort='${param.device_type}000') order by t.value"/>}
						}"/>
					
			</td>
				<td class="l-t-td-left">设备名称：</td>
				<td class="l-t-td-right">
				
							<input type="hidden"  id="device_sort_code" name="device_sort_code"  value="2610"  />
							<input type="text"  name="device_name" id="device_name"  value="常压罐车" validate="{required:true}" ltype="text" />
					
				</td>
			</tr>
				 <tr>				
					<td class="l-t-td-left">投入使用日期：</td>
								<td class="l-t-td-right"><input name="use_date" id="use_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
						</td>				
								
			
				 			
					<td class="l-t-td-left">上次检验日期：</td>
								
								<td class="l-t-td-right"><input name="inspect_date" id="inspect_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" /></td>
							
						</td>				
								
				</tr>				
	
				</td>
			</tr>
				 <tr>				
					<td class="l-t-td-left">产品编号：</td>
								<td class="l-t-td-right"><input name="factory_code"
									validate="{required:false,maxlength:100}" ltype='text' id="factory_code" />
						</td>				
								
			
				 			
							
								
				</tr>	
		
						
		
	</table>

	
</body>
</html>