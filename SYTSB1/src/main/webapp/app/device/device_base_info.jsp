<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head>
</head>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
var pageStatus="${param.status}"; 
<%
	//是否拥有可修改设备注册代码的权限
	boolean hasMdyDRC = StringUtil.isNotEmpty(request.getParameter("mdy_drc")) && "1".equals(request.getParameter("mdy_drc"))?true:false;	
%>




function getNum(numCode){	
var myDate = new Date();
var curYear = myDate.getFullYear();   
var year = numCode.substring(10,14);
if(year>=curYear){
	//$.ligerDialog.alert("设备注册代码有误，只能补录往年的设备，请核实！", "提示");
	//return;
}
$.getJSON("device/basic/getNum.do?device_code="+numCode, function(resp){
		
		if(resp.success){
		
		
			$("#device_registration_code_change").val("");
			$.ligerDialog.alert("注册代码已存在，请核实！", "提示");
			
			return;
		}
		
	})

}

function getQRCode(qrCode){	
	if("add" == pageStatus){
		if(qrCode.length != 6){
			$.ligerDialog.alert("金属二维码必须是6位数字，请重新输入！", "提示");
			return;
		}else{
			//if(checknumber(qrCode)) {
			if(isNaN(qrCode)) { 
				$.ligerDialog.alert("金属二维码必须是6位数字，请重新输入！", "提示");
				return; 
			} 
		}
		$.getJSON("device/basic/getQRCode.do?device_qr_code="+qrCode, function(resp){		
			if(resp.success){
				$("#device_qr_code").val("");
				$.ligerDialog.alert("金属二维码已存在，请核实！", "提示");
				return;
			}
		})
	}
}

function checknumber(qr_code) { 
	var Letters = "1234567890"; 
　	var i; 
	var c; 
	//Letters.length() ->>>>取字符长度
	for( i = 0; i < qr_code.length(); i ++ )   {   
　　		c = qr_code.charAt( i ); 
　　		//在"Letters"中找不到"c"   见下面的此函数的返回值
　　		if (Letters.indexOf( c ) ==-1)   { 
　　　		return true; 
　		} 
	} 
	return false; 
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
   
	var type ='${param.device_type}';
	var category = '${param.device_category}'
	
	if(type=="6" || category == '7310' || type=="1" || type=="2" || type=="0"){
		$('#device_sort_code').val(code);
		$('#device_sort_name').val(name);
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

function callArea(code, name){ 
	var type = '${param.device_type}';
	if("3" == type){
		if(code == "510100"){
			$.ligerDialog.alert("亲，设备所在区域不能选择成都市哦，请选择区县！", "提示");
			return;
		}else{
			$('#device_area_code').val(code);
			$('#device_area_name').val(name);
		}
	}else{
		$('#device_area_code').val(code);
		$('#device_area_name').val(name);
	}
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
			<% 
				if(hasMdyDRC){ 
					%>  
					<tr>
					<td class="l-t-td-left" >设备注册代码：</td>
					<td class="l-t-td-right" colspan="3">
							<input name="device_registration_code" validate="{required:false,maxlength:64}" ltype='text' id="device_registration_code_change" onchange="getNum(this.value)"  />					
					</td>
					</tr>
					<% 
				}else{
					%>
					<c:choose>
						<c:when test="${'modify' eq param.status || 'add' eq param.status}">
							<input type="hidden" name="device_registration_code"/>
						</c:when>
						<c:otherwise>
							<tr>
								<td class="l-t-td-left" >设备注册代码：</td>
								<td class="l-t-td-right" colspan="3">
									<input type="text" name="device_registration_code" ltype='text' id="device_registration_code"/>
								</td>
							</tr>
						</c:otherwise>
					</c:choose>
					
					<%
				}
			%>
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
					<td class="l-t-td-left">使用单位联系人：</td>
					<td class="l-t-td-right">
						<c:choose>
							<c:when test="${'7' eq param.device_type}">
								<input name="com_user_name" validate="{required:true,maxlength:20}" ltype="text" id="com_user_name" />
							</c:when>
							<c:otherwise>
								<input name="com_user_name" validate="{required:false,maxlength:20}" ltype="text" id="com_user_name" />
							</c:otherwise>
						</c:choose>
					</td>
			</tr>
			<tr>
				<td class="l-t-td-left">维保单位：</td>
				<td class="l-t-td-right">
						<input name="fk_maintain_unit_id" id="fk_maintain_unit_id" type="hidden" />
						<input type="text" id="maintain_unit_name" name="maintain_unit_name"   ltype="text"  validate="{required:false}" onclick="selectorg('9')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('9')}}]}"/>
						
				</td>
				<td class="l-t-td-left" style="width: 150px">维保单位<br/>联系电话：</td>
				<td class="l-t-td-right"><input name="maintenance_tel"
					validate="{required:false,maxlength:64}"
					value="" type="text" ltype="text" id="maintenance_tel" /></td>
			</tr>
			<!-- <input name="device_registration_code" type="hidden"/> -->
			<tr>
				<!--  <td class="l-t-td-left">设备种类：</td>
				<td class="l-t-td-right"><u:combo name="device_big_class"  code="device_type" /></td>-->
				<td class="l-t-td-left">设备类别：</td>
				<td class="l-t-td-right">
					<!--  <input type="text" id="device_sort_code"  name="device_sort_code" onclick="showDialogCombo()"  ltype="text"  validate="{required:true}" 
						ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){showDialogCombo()}}]}"  /></td>-->
				<c:choose>
					<c:when test="${param.device_category eq '7310'}">
						<input type="text" id="device_sort" name="device_sort" ltype="select" validate="{required:true}"  ligerui="{
							value:'${param.device_category}',
							readonly:true,
							tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify'  and t.value like '7310%' "/>}
						}"/>
					</c:when>
					<c:otherwise>
						<input type="text" id="device_sort" name="device_sort" ltype="select" validate="{required:true}"  ligerui="{
							value:'${param.device_category}',
							readonly:true,
							tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' 
						 	and t.code_table_values_id in (select t3.id from PUB_CODE_TABLE_VALUES  t3 where t3.value='${param.device_type}000') order by t.value"/>}
						}"/>
					</c:otherwise>
				</c:choose>
			</td>
				<td class="l-t-td-left">设备名称：</td>
				<td class="l-t-td-right">
				
				
				
				<!-- <input type="text" id="device_sort_code" name="device_sort_code" ltype="select"  onchange="setValue(this.value,'device_name')" validate="{required:true}"  ligerui="{
				readonly:true,
				tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' 
				 and t.value like '${param.device_type}%'"/>}
				
				}"/> -->
				
				<c:choose>
						<c:when test='${param.device_type=="6" || param.device_category=="7310" || param.device_type=="2" || param.device_type=="1" || param.device_type=="0" }'>
							<input type="text"  name="device_name" id="device_name"  validate="{required:true}" ltype="text"/>
						</c:when>
						<c:otherwise>
							<input type="hidden"  id="device_sort_code" name="device_sort_code"   />
							<input type="text"  name="device_name" id="device_name"  validate="{required:true}" ltype="text" onclick="selectCode()"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
			<tr>
			<c:choose>
				<c:when test='${param.device_type=="6" || param.device_category=="7310" || param.device_type=="2" || param.device_type=="1" || param.device_type=="0"}'>
					<td class="l-t-td-left">设备类型：</td>
					<td class="l-t-td-right" >
						<input type="hidden"  id="device_sort_code" name="device_sort_code"  />
						<input type="text"  name="device_sort_name" id="device_sort_name"  validate="{required:true}" ltype="text" onclick="selectCode()"/>
					</td>
					<td class="l-t-td-left">单位内部编号：</td>
					<td class="l-t-td-right"><input name="internal_num"
							validate="{required:false,maxlength:100}" ltype='text' id="internal_num" /></td>
				</c:when>
				<c:otherwise>
					<td class="l-t-td-left" style="width: 150px">设备代码：</td>
					<td class="l-t-td-right"><input name="device_code"
							validate="{required:false,maxlength:40}" ltype="text" id="device_code" /></td>
					<td class="l-t-td-left">单位内部编号：</td>
					<td class="l-t-td-right"><input name="internal_num"
							validate="{required:false,maxlength:100}" ltype='text' id="internal_num" /></td>
				</c:otherwise>
			</c:choose>
			</tr>
			<tr>
				<td class="l-t-td-left" style="width: 150px">出厂编号：</td>
				<td class="l-t-td-right">
					<c:choose>
						<c:when test="${'7' eq param.device_type}">
							<input name="factory_code" validate="{required:true,maxlength:20}" ltype="text" id="factory_code" />
						</c:when>
						<c:otherwise>
							<input name="factory_code" validate="{required:false,maxlength:20}" ltype="text" id="factory_code" />
						</c:otherwise>
					</c:choose>
				</td>
				<td class="l-t-td-left">制造许可证编号：</td>
				<td class="l-t-td-right"><input name="make_licence_no"
					validate="{required:false,maxlength:16}" ltype='text' id="make_licence_no" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">设备型号：</td>
				<td class="l-t-td-right" ><input name="device_model"
					validate="{required:false,maxlength:64}"  ltype='text' id="device_model" /></td>
				<td class="l-t-td-left">使用登记证号：</td>
				<td class="l-t-td-right" ><input name="registration_num"
					validate="{required:false,maxlength:30}"  ltype='text' id="registration_num" /></td>	
			</tr>
			<tr>
				<td class="l-t-td-left">登记机关：</td>
				<td class="l-t-td-right">
					<input name="registration_agencies" validate="{required:false,maxlength:200}" value="" type="text" ltype="text" id="registration_agencies"/>
				</td>
				<td class="l-t-td-left">设备使用场所：</td>
					<td class="l-t-td-right">
						<u:combo name="use_place" code="use_place" validate="required:true"  attribute="disabled:false"   />
					</td>
			</tr>
			<tr>
				<td class="l-t-td-left">登记日期：</td>
				<td class="l-t-td-right">
					<c:choose>
						<c:when test="${'7' eq param.device_type}">
							<input name="registration_date" id="registration_date"
					type="text" ltype="date" validate="{required:true}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" /></td>
						</c:when>
						<c:otherwise>
							<input name="registration_date" id="registration_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" /></td>
						</c:otherwise>
					</c:choose>
					</td>
				<td class="l-t-td-left">登记人员：</td>
				<td class="l-t-td-right"><input name="registration_op"
					validate="{required:false,maxlength:32}"  value="<sec:authentication property="principal.name" />" ltype="text"  id="registration_op" /></td>
			</tr>
			<!-- 
			<tr>
				<td class="l-t-td-left" style="width: 150px">安全单位名称</td>
				<td class="l-t-td-right" colspan="3"><input name="construction_units_name"
					validate="{required:false,maxlength:128}"
					value="" type="text" ltype="text" id="construction_units_name"/></td>
			</tr>
			 -->
			
			<tr>
				<td class="l-t-td-left">制造单位：</td>
				<td class="l-t-td-right" >
						<input name="fk_company_info_make_id" id="fk_company_info_make_id" type="hidden" />
					
				<c:choose>
						<c:when test="${'7' eq param.device_type}">
							<input type="text" id="make_units_name" name="make_units_name"   ltype="text"  validate="{required:true}" onclick="selectorg('1')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('1')}}]}"/>
						</c:when>
						<c:otherwise>
							<input type="text" id="make_units_name" name="make_units_name"   ltype="text"  validate="{required:false}" onclick="selectorg('1')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('1')}}]}"/>
						</c:otherwise>
					</c:choose>
				</td>
			
				<td class="l-t-td-left">制造日期：</td>
				<td class="l-t-td-right"><input name="make_date"
					type="text" ltype="text" validate="{required:true}" id="make_date"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">改造单位：</td>
				<td class="l-t-td-right" >
						<input name="fk_reform_unit_id" id="fk_reform_unit_id" type="hidden" />
						<input type="text" id="reform_unit_name" name="reform_unit_name"   ltype="text"  validate="{required:false}" onclick="selectorg('8')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('8')}}]}"/>
						
					</td>
				
				<td class="l-t-td-left">改造日期：</td>
				<td class="l-t-td-right"><input name="reform_date"
					type="text" ltype="text" validate="{required:false}" id="reform_date"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left" style="width: 150px">施工单位：</td>
				<td class="l-t-td-right">
				
				<input name="fk_company_info_install_id" id="fk_company_info_install_id" type="hidden" />
				
						
				<input type="text" id="construction_units_name" name="construction_units_name"   ltype="text"  validate="{required:false}" onclick="selectorg('2')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('2')}}]}"/>
				</td>
				<td class="l-t-td-left">施工单位<br/>许可证编号：</td>
				<td class="l-t-td-right" ><input name="construction_licence_no"
					validate="{required:false,maxlength:128}"  ltype='text' id="construction_licence_no" /></td>
				
			</tr>
			<tr>
				<td class="l-t-td-left" style="width: 150px">施工日期：</td>
				<td class="l-t-td-right"><input name="construction_date"
					validate="{required:false,maxlength:20}" type="text" ltype="text" id="construction_date" /></td>
				<td class="l-t-td-left">安全管理部门：</td>
				<td class="l-t-td-right"><input name="security_management"
					validate="{required:false,maxlength:128}" ltype="text" id="security_management" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left" style="width: 150px">安全管理人员：</td>
				<td class="l-t-td-right"><input name="security_op"
					validate="{required:false,maxlength:64}" ltype="text" id="security_op" /></td>
				<td class="l-t-td-left" style="width: 150px">安全管理<br/>联系电话：</td>
				<td class="l-t-td-right">
					<c:choose>
						<c:when test="${param.device_type eq '3'}">
							<input name="security_tel" validate="{required:true,maxlength:64}" value="" type="text" ltype="text" id="security_tel" />
						</c:when>
						<c:otherwise>
							<input name="security_tel" validate="{required:false,maxlength:64}" value="" type="text" ltype="text" id="security_tel" />
						</c:otherwise>
					</c:choose>
				</td>
				<!-- 
				<td class="l-t-td-left">下次检验日期：</td>
				<td class="l-t-td-right"><input name="inspect_next_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="inspect_next_date"/></td>
				 -->
			</tr>
			<!-- 
			<tr>
				<td class="l-t-td-left">安装竣工日期：</td>
				<td class="l-t-td-right"><input name="install_finish_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="install_finish_date" /></td>
				<td class="l-t-td-left">投用日期：</td>
				<td class="l-t-td-right"><input name="use_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="use_date"/></td>
			</tr>
			 -->
			<tr>
				<td class="l-t-td-left" style="width: 150px">设备所在区域：</td>
				<td class="l-t-td-right"> 
					<c:choose>
						<c:when test="${param.device_type eq '3' || param.device_type=='Q' || param.device_category=='7310'}">
							<input type="hidden" id="device_area_code" name="device_area_code"  />  
							<input type="text" id="device_area_name" name="device_area_name"   readonly="readonly" validate="{required:true}" ltype="text" onclick="selectArea()" /> 
						</c:when>
						<c:otherwise>
							<input type="hidden" id="device_area_code" name="device_area_code"  />  
							<input type="text" id="device_area_name" name="device_area_name"   readonly="readonly" validate="{required:false}" ltype="text" onclick="selectArea()" /> 
						</c:otherwise>
					</c:choose>
				</td>
					<c:choose>
						<c:when test="${param.device_type eq '3'}">
							<td class="l-t-td-left" style="width: 150px">设备所在街道：</td>
							<td class="l-t-td-right"> 
								<input type="hidden" id="device_street_code" name="device_street_code"  />  
								<input type="text" id="device_street_name" name="device_street_name"   readonly="readonly" validate="{required:false}" ltype="text" onclick="selectStreet()" /> 
							</td>
						</c:when>
						<c:otherwise>
							<c:if test='${param.device_type=="6" || param.device_category=="7310" || param.device_type=="2" || param.device_type=="1" }'>
								<td class="l-t-td-left" style="width: 150px">设备代码：</td>
								<td class="l-t-td-right"><input name="device_code"
										validate="{required:false,maxlength:40}" ltype="text" id="device_code" /></td>							
							</c:if>
						</c:otherwise>
					</c:choose>
			</tr>
			<tr>
				<c:choose>
						<c:when test='${param.device_type=="Q" || param.device_category=="7310" }'>
							<td class="l-t-td-left" style="width: 150px">安装位置：</td>
							<td class="l-t-td-right" colspan="3">
								<input name="device_use_place" validate="{required:true,maxlength:100}" value="" type="text" ltype="text" id="device_use_place"/>
							</td>
						</c:when>
						<c:when test='${param.device_type=="2" }'>
							<td class="l-t-td-left" style="width: 150px">设备使用地点：</td>
							<td class="l-t-td-right" colspan="3">
								<input name="device_use_place" validate="{required:false,maxlength:100}" value="" type="text" ltype="text" id="device_use_place"/>
							</td>
						</c:when>
						<c:when test='${param.device_type=="3" }'>
							<td class="l-t-td-left" style="width: 150px">设备使用地点：</td>
							<td class="l-t-td-right">
								<input name="device_use_place" validate="{required:true,maxlength:100}" value="" type="text" ltype="text" id="device_use_place"/>
							</td>
							<td class="l-t-td-left" style="width: 150px">金属二维码：</td>
							<td class="l-t-td-right">
								<c:choose>
									<c:when test="${param.status eq 'modify' }">
										<input name="device_qr_code" validate="{required:false,maxlength:6}" value="" type="text" ltype="text" title='金属二维码由成都市局管理，修改请联系信息中心协助。' id="device_qr_code" readonly="readonly"/>
									</c:when>
									<c:otherwise>
										<input name="device_qr_code" validate="{required:true,maxlength:6}" value="" type="text" ltype="text" id="device_qr_code" onchange="getQRCode(this.value)"/>
									</c:otherwise>
								</c:choose>
								
							</td>
						</c:when>
						<c:otherwise>
							<td class="l-t-td-left" style="width: 150px">设备使用地点<br/>/安装位置：</td>
							<td class="l-t-td-right" colspan="3">
								<input name="device_use_place" validate="{required:false,maxlength:100}" value="" type="text" ltype="text" id="device_use_place"/>
							</td>
						</c:otherwise>
				</c:choose>
			</tr>
			<c:if test="${param.status eq 'modify' }">
				<tr>
					<td class="l-t-td-left" colspan="4"><b>温馨提示：金属二维码由成都市局管理，修改请联系信息中心协助。</b></td>
				</tr>
			</c:if>
			<!-- <tr>
				<td class="l-t-td-left" style="width: 150px">检验结论：</td>
				<td class="l-t-td-right"><input name="inspect_conclusion"
					validate="{required:false,maxlength:64}"
					value="" type="text" ltype="text" id="inspect_conclusion"/></td>
				<td class="l-t-td-left">检验日期：</td>
				<td class="l-t-td-right"><input name="inspect_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="inspect_date" /></td>
			</tr>
			 -->
	</table>
</body>
</html>