<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
<head pageStatus="${param.status}">
</head>
<%
	String pageStatus = request.getParameter("status");
	//是否拥有可修改设备注册代码的权限
	boolean hasMdyDRC = StringUtil.isNotEmpty(request.getParameter("mdy_drc")) && "1".equals(request.getParameter("mdy_drc"))?true:false;	
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:saveClose },
	          
	            { text:'关闭', id:'close',icon:'cancel', click:close }
	        ],
	        getSuccess:function(res){
	        	$("#formObj").setValues(res.data);	   
	        	$("#basic_id").val(res.data.id);        
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
	            	api.data.window.submitAction();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}
	    });
	    function setData(data){
	     
	    
	    	
	    }
	    
	   var type ="${param.device_type}";
	    
	    function saveClose(){
		     if ($("#formObj").validate().form()) {
		     	$("#save").attr("disabled","disabled");
		    	var data = $("#formObj").getValues();
		    	var numCode = data["device_registration_code"];
		    	if("1" == "${param.mdy_drc}"){
					if(numCode.length!=20){
						$.ligerDialog.alert("设备注册代码有误，请核实！", "提示");
						return;
					}
				}
				if(confirm("确定保存？")){
					//表单提交
					$("#formObj").submit();
				}
			}
		}
	    
		function saveAdd(){
	 		save("saveAdd");
	   	}
	   	
	   	function close(){
	        api.close();
	    }
	});
	
var  com_type=null; //页面选择单位类型
	
	function selectorg(type){
		com_type=type;
		var url = 'url:app/enter/enter_open_list.jsp';
		if (type != "") {
			url += '?com_type='+com_type;
		}
		
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择企业信息",
			content: url,
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(id,name,address,com_code,fddbr,tel){
		//判断选择单位类型关联相应字段 0 :使用单位 1：制造单位 2： 安装代为 3：设计单位 4：产品监检单位 5:锅炉产品监检单位 9：维保单位
		if(com_type=='0'){
			$('#fk_company_info_use_id').val(id);
			$('#company_name').val(name);
			$('#com_code').val(com_code);
		}else if(com_type=='1'){
			$('#fk_company_info_make_id').val(id);
			$('#make_units_name').val(name);
		}else if(com_type=='2'){
			$('#fk_company_info_install_id').val(id);
			$('#construction_units_name').val(name);
		}else if(com_type=='3'){
			$('#fk_design_units_id').val(id);
			$('#cbz').val(name);
		}else if(com_type=='4'){
			$('#monitor_unit_id').val(id);
			$('#cp_by2').val(name);
		}else if(com_type=='5'){
			$('#fk_inspection_unit_id').val(id);
			$('#cp_by1').val(name);
		}else if(com_type=='9'){
			$('#fk_maintain_unit_id').val(id);
			$('#maintain_unit_name').val(name);
			$('#maintenance_tel').val(tel);
		}
	}


function selectCode(){
	var type =${param.device_type};	
	top.$.dialog({
		width : 280,
		height : 550,
		lock : true,
		title : "设备名称",
		content : 'url:app/device/device_sort_type.jsp?status=detail&type='+type,				
		data : {
			"window" : window
		}
	});
}

function callOK(code, name)
{ 
	$('#device_sort_code').val(code);
	$('#device_name').val(name);
}



function selectArea(){
	top.$.dialog({
		width : 280,
		height : 550,
		lock : true,
		title : "设备名称",
		content : 'url:app/device/device_area_code.jsp?status=detail',			
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

function setValue(valuex,name){
	if(valuex==""){
		return;
	}
	if(name=='device_name'){
		$('#device_name').val(valuex)
	}
}

function initInfo(){
	$.getJSON("device/basic/getDevice.do?id=${param.id}&type=${param.device_type}", function(resp){
		if(resp.success){
			$("#formObj").setValues(resp.data);	 
			//alert(res.data.cableway[0]);
			//$("#formObj").setValues(res.data.cableway[0]);  
	        $("#basic_id").val(resp.data.id);        
		}
	})
}

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

function selectArea(){
	top.$.dialog({
		width : 280,
		height : 550,
		lock : true,
		title : "设备所在地",
		content : 'url:app/device/device_area_code.jsp?status=detail',
				
		data : {
			"window" : window
		}
	});
}

function callArea(code, name){ 
	$('#device_area_code').val(code);
	$('#device_area_name').val(name);
}
</script>
<body <%if(!"add".equals(pageStatus)){%>onload="initInfo();"<%}%>>	
<form name="formObj" id="formObj" method="post" action="device/basic/saveBasic1.do?type=${param.device_type}"
			getAction="device/basic/getDevice.do?id=${param.id}">		
<fieldset class="l-fieldset">
	<legend class="l-legend">
		<div>基本情况</div>
	</legend>
	<table cellpadding="3" cellspacing="0" class="l-detail-table">
		<tr>
				<td class="l-t-td-left">
					<input id="basic_id" name="id" type="hidden" />
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
			<!-- 
				<tr>
					<td class="l-t-td-left" >设备注册代码：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="text" name="device_registration_code" ltype='text' id="device_registration_code" validate="{required:true,maxlength:20}" />
					</td>
				</tr>
			 -->
			<!-- <input name="device_registration_code" type="hidden"/> -->
			<tr>
				<!--  <td class="l-t-td-left">设备种类：</td>
				<td class="l-t-td-right"><u:combo name="device_big_class"  code="device_type" /></td>-->
				<td class="l-t-td-left">设备类别：</td>
				<td class="l-t-td-right">
					<!--  <input type="text" id="device_sort_code"  name="device_sort_code" onclick="showDialogCombo()"  ltype="text"  validate="{required:true}" 
						ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){showDialogCombo()}}]}"  /></td>-->
				

				<input type="text" id="device_sort" name="device_sort" ltype="select" validate="{required:true}"  ligerui="{
				value:'${param.device_category}',
				readonly:true,
				tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' 
				 and t.code_table_values_id in (select t3.id from PUB_CODE_TABLE_VALUES  t3 where t3.sort='${param.device_type}000')"/>}
				
				}"/>
			</td>
				<td class="l-t-td-left">设备名称：</td>
				<td class="l-t-td-right">
				<input type="hidden"  id="device_sort_code" name="device_sort_code"   >
				
				
				<!-- <input type="text" id="device_sort_code" name="device_sort_code" ltype="select"  onchange="setValue(this.value,'device_name')" validate="{required:true}"  ligerui="{
				readonly:true,
				tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' 
				 and t.value like '${param.device_type}%'"/>}
				
				}"/> -->
				
				<input type="text"  name="device_name" id="device_name"  validate="{required:true}" ltype="text" onclick="selectCode()"/>
				</td>
			</tr>
			<tr>	
				<td class="l-t-td-left">使用单位：</td>
				<td class="l-t-td-right" colspan="3">
						<input name="fk_company_info_use_id" id="fk_company_info_use_id" type="hidden" />
						<input name="com_code" id="com_code" type="hidden" />
						
						<!--<input type="text" ltype='text'  name="company_name"
							validate="{required:false,maxlength:50}" readonly="readonly" id="make_units_name" ligerui="{suffix:'<input type=button value=选择 class='l-button3' onclick=selectorg(0)>'}" />-->
						
					  <input type="text" id="company_name" name="company_name"   ltype="text"  validate="{required:true}" onclick="selectorg('0')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('0')}}]}"/>
				</td>
			</tr>
			<tr>	
				<td class="l-t-td-left">法定代表人：</td>
				<td class="l-t-td-right">
					<input type="text" id="legal_name" name="legal_name"   ltype="text" validate="{maxlength:20}" />
				</td>
				<td class="l-t-td-left">客运索道负责人：</td>
				<td class="l-t-td-right">
					<input type="text" id="security_op" name="security_op"   ltype="text" validate="{maxlength:20}" />
				</td>
			</tr>
			<tr>	
				<td class="l-t-td-left">联系电话：</td>
				<td class="l-t-td-right">
					<input type="text" id="security_tel" name="security_tel" ltype="text" validate="{maxlength:64}" />
				</td>
				<td class="l-t-td-left" style="width: 150px">设备所在地：</td>
				<td class="l-t-td-right"> 
					<input type="hidden" id="device_area_code" name="device_area_code"  />  
					
					<input type="text" id="device_area_name" name="device_area_name"   readonly="readonly" validate="{required:true}" ltype="text" onclick="selectArea()" /> 
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">登记机关：</td>
				<td class="l-t-td-right">
					<input type="text" id="registration_agencies" name="registration_agencies" ltype="text" validate="{maxlength:200}" />
				</td>
				<td class="l-t-td-left">使用登记证号：</td>
				<td class="l-t-td-right" ><input name="registration_num"
					validate="{required:false,maxlength:30}"  ltype='text' id="registration_num" /></td>	
			</tr>
			<tr>
				<td class="l-t-td-left">通讯地址：</td>
				<td class="l-t-td-right" colspan="3">
					<input type="text" id="device_use_place" name="device_use_place" ltype="text" validate="{maxlength:100}" />
				</td>
			</tr>
			<tr>
				<td class="l-t-td-left">整机制造单位：</td>
				<td class="l-t-td-right">
					<input name="fk_company_info_make_id" id="fk_company_info_make_id" type="hidden" />
					<input type="text" id="make_units_name" name="make_units_name"   ltype="text"  validate="{required:false}" onclick="selectorg('1')"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('1')}}]}"/>
				</td>
				<td class="l-t-td-left">首次安装竣工日期：</td>
				<td class="l-t-td-right"><input name="first_install_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="first_install_date" /></td>
			</tr>
			<tr>
				<td class="l-t-td-left">改造竣工日期：</td>
				<td class="l-t-td-right"><input name="remake_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="remake_date" /></td>
				<td class="l-t-td-left">重大维修竣工日期：</td>
				<td class="l-t-td-right"><input name="repair_date"
					type="text" ltype="date" validate="{required:false}"
					ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="repair_date"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left" >备注：</td>
				<td class="l-t-td-right" colspan="3">
					<textarea name="remark" rows="2" cols="25" class="l-textarea" validate="{maxlength:200}"></textarea>
				</td>
			</tr>	
	</table>
	</fieldset>
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>主要部件制造单位</div>
		</legend>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr>
				<td class="l-t-td-left">驱动迂回装置：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_1" type="text" ltype="text" id="zybj_zzdw_1" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">托压索轮组：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_2" type="text" ltype="text" id="zybj_zzdw_2" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">抱索器：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_3" type="text" ltype="text" id="zybj_zzdw_3" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">运载工具：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_4" type="text" ltype="text" id="zybj_zzdw_4" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">运载索：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_5" type="text" ltype="text" id="zybj_zzdw_5" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">承载索：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_6" type="text" ltype="text" id="zybj_zzdw_6" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">牵引索（平衡索）：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_7" type="text" ltype="text" id="zybj_zzdw_7" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">减速机：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_8" type="text" ltype="text" id="zybj_zzdw_8" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">支架及鞍座：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_9" type="text" ltype="text" id="zybj_zzdw_9" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">电气设备：</td>
				<td class="l-t-td-right"><input name="zybj_zzdw_10" type="text" ltype="text" id="zybj_zzdw_10" validate="{maxlength:200}"/></td>
			</tr>
		</table>
	</fieldset>
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>基本技术参数</div>
		</legend>
		<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<tr >
				<td class="l-t-td-left">客运索道型式：</td>
				<td class="l-t-td-right" colspan="3"><input name="jbjscs_1" type="text" ltype="text" id="jbjscs_1" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">平距：</td>
				<td class="l-t-td-right"><input name="jbjscs_2" type="text" ltype="text" id="jbjscs_2" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">斜长：</td>
				<td class="l-t-td-right"><input name="jbjscs_3" type="text" ltype="text" id="jbjscs_3" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">高差：</td>
				<td class="l-t-td-right"><input name="jbjscs_4" type="text" ltype="text" id="jbjscs_4" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">运量：</td>
				<td class="l-t-td-right"><input name="jbjscs_5" type="text" ltype="text" id="jbjscs_5" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">速度（本次检验）：</td>
				<td class="l-t-td-right"><input name="jbjscs_6" type="text" ltype="text" id="jbjscs_6" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">索距：</td>
				<td class="l-t-td-right"><input name="jbjscs_13" type="text" ltype="text" id="jbjscs_13" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">支架数目：</td>
				<td class="l-t-td-right"><input name="jbjscs_7" type="text" ltype="text" id="jbjscs_7" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">主电机型式和功率：</td>
				<td class="l-t-td-right"><input name="jbjscs_8" type="text" ltype="text" id="jbjscs_8" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">张紧油压<br/>（重锤重量）：</td>
				<td class="l-t-td-right"><input name="jbjscs_9" type="text" ltype="text" id="jbjscs_9" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">运载索（牵引、平衡索）：</td>
				<td class="l-t-td-right"><input name="jbjscs_10" type="text" ltype="text" id="jbjscs_10" validate="{maxlength:200}"/></td>
			</tr>
			<tr>
				<td class="l-t-td-left">承载索：</td>
				<td class="l-t-td-right"><input name="jbjscs_11" type="text" ltype="text" id="jbjscs_11" validate="{maxlength:200}"/></td>
				<td class="l-t-td-left">运载工具数量和类型<br/>（本次检验）：</td>
				<td class="l-t-td-right"><input name="jbjscs_12" type="text" ltype="text" id="jbjscs_12" validate="{maxlength:200}"/></td>
			</tr>
		</table>
	</fieldset>
</form>
</body>
</html>