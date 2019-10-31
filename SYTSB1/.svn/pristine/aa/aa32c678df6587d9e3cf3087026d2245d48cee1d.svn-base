<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
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
	       // $('#formObj').setValues({p30001001:res.data.elevatorParas[0].p30001001});
	        	$("#formObj").setValues(res.data.elevatorParas[0]);
	        	$("#formObj").setValues(res.data.boiler[0]);
	        	$("#formObj").setValues(res.data.crane[0]);
	        	$("#formObj").setValues(res.data.pressurevessels[0]);
	        	$("#formObj").setValues(res.data.engine[0]);
	        	$("#formObj").setValues(res.data.accessory[0]);
	        	$("#formObj").setValues(res.data.ridesPara[0]);
	        	$("#basic_id").val(res.data.id);
	            //setData(res.data);
	            
	        }
	    });
	    function setData(data){
	     
	    
	    	
	    }
	    
	   var type ="${param.device_type}";
	    
	    function saveClose(){
	    	if ($("#formObj").validate().form()) {
	    		if ($("#basic").validate().form()) {
	    			$("#save").attr("disabled","disabled");
			    	var data = $("#basic").getValues();
			    	/*
			    	if("1" == "${param.mdy_drc}"){
			    		var numCode = data["device_registration_code"];
				    	var myDate = new Date();
						var curYear = myDate.getFullYear();   
						var year = numCode.substring(10,14);
						if(year>=curYear){
							//$.ligerDialog.alert("设备注册代码有误，只能注册往年的设备，请核实！", "提示");
							//$("#save").removeAttr("disabled");
							//return;
						}
			    	}
			    	*/
			    	
			    	alert(type)
			    	if(type=="3"){
			    		var area_code = data["device_area_code"];
			    		var area_name = data["device_area_name"];
			    		if(area_code=='' || area_name==''){
			    			$.ligerDialog.alert("亲，设备所在区域不能为空哦，请补充！", "提示");
			    			$("#save").removeAttr("disabled");
							return;
			    		}else{
			    			if(area_code == "510100"){
			    				$.ligerDialog.alert("亲，设备所在区域不能选择成都市哦，请选择区县！", "提示");
				    			$("#save").removeAttr("disabled");
								return;
			    			}
			    		}
			    		var street_code = data["device_street_code"];
			    		var street_name = data["device_street_name"];
			    		if(street_code=='' || street_name==''){
			    			//$.ligerDialog.alert("亲，设备所在街道不能为空哦，请补充！", "提示");
			    			//$("#save").removeAttr("disabled");
							//return;
			    		}
			       		data["elevatorParas"]=[$("#elevatorPara").getValues()];
			       		 
						if("add" == pageStatus){
							if(data["device_qr_code"].length != 6){
								$.ligerDialog.alert("金属二维码必须是6位数字，请重新输入！", "提示");
								$("#save").removeAttr("disabled");
								return;
							}else{
								//if(checknumber(data["device_qr_code"])) { 
								if(isNaN(data["device_qr_code"])) { 
									$.ligerDialog.alert("金属二维码必须是6位数字，请重新输入！", "提示");
									$("#save").removeAttr("disabled");
									return; 
								} 
							}
						}
			    	}else if(type=="1"){
			    		
			    		 data["boiler"]=[$("#boilerPara").getValues()];
			    		 
			    	}else if(type=="4"){
			    		
			    		data["crane"]=[$("#cranePara").getValues()];
			    		
			    	}else if(type=="2"){
			    		
			    		data["pressurevessels"]=[$("#pressurevesselsPara").getValues()];
			    		
			    	}else if(type=="5"){
			    		
			    		data["engine"]=[$("#enginePara").getValues()];
			    		
			    	}else if(type=="6"){
			    		
			    		data["ridesPara"]=[$("#ridesPara").getValues()];
			    		
			    	}else if(type=="Q"||type=="F"||type=="7"){
			    		data["accessory"]=[$("#accessoryPara").getValues()];
			    		
			    	}

			        $.ajax({
			            url: "device/basic/saveBasic.do?type="+type,
			            type: "POST",
			            datatype: "json",
			            contentType: "application/json; charset=utf-8",
			            data: $.ligerui.toJSON(data),
			            success: function (resp) {
			                if (resp["success"]) {
			                		api.data.window.submitAction();
			                		api.close();
			                		top.$.dialog.notice({content:'保存成功！'});
			                } else {
			                	$.ligerDialog.error('保存失败！'+resp.msg);
			                	$("#save").removeAttr("disabled");
			                }
			            },
			            error: function (resp) {
			                $.ligerDialog.error('保存失败！' + resp.msg);
			                $("#save").removeAttr("disabled");
			            }
			        });
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
		//判断选择单位类型关联相应字段 0 :使用单位 1：制造单位 2： 安装代为 3：设计单位 4：产品监检单位 5:锅炉产品监检单位 9：维保单位 8：改造单位
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
		}else if(com_type=='8'){
			$('#fk_reform_unit_id').val(id);
			$('#reform_unit_name').val(name);
		}
	}

	function checknumber(qr_code) { 
		alert(qr_code);
		var nums = "1234567890"; 
	　	var i; 
		var c; 
		//Letters.length() ->>>>取字符长度
		for( i = 0; i < qr_code.length(); i ++ )   {   
	　　		c = qr_code.charAt(i); 
	　　		//在"nums"中找不到"c"   见下面的此函数的返回值
	　　		if (nums.indexOf(c) == -1)   { 
	　　　		return true; 
	　		} 
		} 
		return false; 
	} 
</script>
</head>
<body>

<body>


<form name="formObj" id="formObj" method="post" Action="device/basic/saveBasic.do?type=${param.device_type}"
			getAction="device/basic/getDevice.do?id=${param.id}&type=${param.device_type}">
		
	<div class="navtab">
		<div title="基本信息" id="basic" >
				<c:choose>
					<c:when test="${'2610' eq param.device_category}">
						<%@ include file="device_tanker_info.jsp"%>
					</c:when>
					<c:otherwise>
					
					
						
		
						<%@ include file="device_base_info.jsp"%>
	
			
						
					</c:otherwise>
				</c:choose>
		</div>
		
		<c:if test='${param.device_type=="3" }'>
			<div title="电梯参数信息" id="elevatorPara" >
				
				<c:choose>
					<c:when test="${'3300' eq param.device_category || '3310' eq param.device_category || '3320' eq param.device_category}">
						<%@ include file="device_elevator_info.jsp"%>
					</c:when>
					<c:otherwise>
						<%@ include file="device_elevator_info2.jsp"%>
					</c:otherwise>
				</c:choose>
			</div>
		
			</c:if>
		
			
		<c:if test='${param.device_type=="1" }'>
			<div title="锅炉参数信息" id="boilerPara" >
				<%@ include file="device_boiler_info.jsp"%>
			</div>
			</c:if>
	
		
		<c:if test='${param.device_type=="4" }'>
		<div title="起重参数信息" id="cranePara" >
				<%@ include file="device_crane_info.jsp"%>
		</div>
		</c:if>
		
		<c:if test='${param.device_type=="2"}'>
		<div title="压力容器参数信息" id="pressurevesselsPara" >
		
		<c:choose>
					<c:when test='${param.device_category=="2610"}'>
						<%@ include file="device_tanker_detail.jsp"%>
					</c:when>
					<c:otherwise>
						
					<%@ include file="device_pressurevessels_info.jsp"%>
					</c:otherwise>
		</c:choose>
		
		
		</div>
		</c:if>
		
		<c:if test='${param.device_type=="5" }'>
		<div title="场（厂）内机动车辆参数信息" id="enginePara" >
				<%@ include file="device_engine_info.jsp"%>
		</div>
		</c:if>
		
		<c:if test='${param.device_type=="6" }'>
		<div title="游乐设施参数信息" id="ridesPara" >
				<%@ include file="device_rides_info.jsp"%>
		</div>
		</c:if>
		
		<c:if test='${param.device_type=="Q"||param.device_type=="F"||param.device_type=="7" }'>
		<div title="安全阀参数信息" id="accessoryPara" >
				<%@ include file="device_accessory_info.jsp"%>
		</div>
		</c:if>
		<c:if test="${'detail' eq param.status}">
		<div title="使用单位信息" id="companyInfo" >
			<fieldset class="l-fieldset">
				<legend class="l-legend">基本信息</legend>
				<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<input id="id" name="id" type="hidden" />
				<input type="hidden" id="com_status" name="com_status"  value="01"/>
				<tr> 
		        	<td class="l-t-td-left"> 组织机构代码:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.com_code" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
		        	</td>
					<td class="l-t-td-left"> 营业执照注册号:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.licence_reg_no" type="text" ltype='text' validate="{required:true,maxlength:100}"/>
		        	</td>
				</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 单位名称:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.com_name" type="text" ltype='text' validate="{required:true,maxlength:255}"/>
			        </td>
			        <td class="l-t-td-left"> 法定代表人:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.com_legal_rep" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
			        </td>
				</tr>
				<tr> 
					<td class="l-t-td-left"> 地区编码:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.com_area_code" type="text" ltype='text' validate="{required:false,maxlength:8}"/>
		        	</td>
		        	<td class="l-t-td-left"> 单位地址:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.com_address" type="text" ltype='text' validate="{required:true,maxlength:255}"/>
		        	</td>
				</tr>
				<tr> 
		       		<td class="l-t-td-left"> 邮政编码:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.com_zip_code" type="text" ltype='text' validate="{required:false,maxlength:8}"/>
		        	</td>
		        	<td class="l-t-td-left"> 单位电话:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.com_tel" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
		        	</td>
				</tr>
		
		        <tr> 
		        	<td class="l-t-td-left">单位类型 :</td>
		        	<td class="l-t-td-right">    
		         		<u:combo name="enterInfo.com_type" code="BASE_COMPANY_TYPE" />
		        	</td>
		      		<td class="l-t-td-left">许可证级别及编号:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.license_num" type="text" ltype='text' validate="{required:true,maxlength:20}"/>
		        	</td>
		       	</tr>
		       	<tr> 
		        	<td class="l-t-td-left"> 联系人:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.com_contact_man" type="text" ltype='text' validate="{required:true,maxlength:32}"/>
		        	</td>
		        	<td class="l-t-td-left"> 传真:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.com_fax" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
		        	</td>
		       	</tr>
		       	<tr> 
		        	<td class="l-t-td-left"> 电子信箱:</td>
		        	<td class="l-t-td-right"> 
		        		<input name="enterInfo.com_email" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
		        	</td>
			        <td class="l-t-td-left"> 网址:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.com_web_site" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
			        </td>
		       	</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 安全管理部门:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.security_depart" type="text" ltype='text' validate="{required:false,maxlength:255}"/>
			        </td>
			        <td class="l-t-td-left"> 安全管理人员:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.security" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
			        </td>
		       	</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 资格证书:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.qualification_certificate" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
			        </td>
			        <td class="l-t-td-left"> 资格证书号:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.qualification_certificate_no" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
			        </td>
		       	</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 成立日期:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.com_fund_date" type="text" ltype='date' ligerui="{initValue:'',format:'yyyy-MM-dd'}" validate="{required:false}}"/>
			        </td>
			        <td class="l-t-td-left"> 批准成立机关:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.approve_depart" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
			        </td>
		       	</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 营业执照登记机构:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.licence_depart" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
			        </td>
			        <td class="l-t-td-left">&nbsp;</td>
			        <td class="l-t-td-right"> &nbsp;</td>
		       	</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 单位总人数(人):</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.employee_count" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
			        </td>
			        <td class="l-t-td-left"> 注册资金(万元):</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.registered_funds" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
			        </td>
		       	</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 固定资产(万元):</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.permanent_assets" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
			        </td>
			        <td class="l-t-td-left"> 管理者代表:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.manager_deputy" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
			        </td>
		       	</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 管理者代表职位:</td>
			        <td class="l-t-td-right"> 
			        	<input name="manager_deputy_pos" type="text" ltype='text' validate="{required:false,maxlength:100}"/>
			        </td>
			        <td class="l-t-td-left"> 质量保证负责人:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.quality_principal" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
			        </td>
		       	</tr>
		       	<tr> 
			        <td class="l-t-td-left"> 主管部门:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.charge_depart" type="text" ltype='text' validate="{required:false,maxlength:32}"/>
			        </td>
			       	<td class="l-t-td-left"> 授权维护单位:</td>
			        <td class="l-t-td-right"> 
			        	<input name="enterInfo.authorize_dapart" type="text" ltype='text' validate="{required:false,maxlength:1000}"/>
			        </td>
		       	</tr>
				<tr>	
					<td class="l-t-td-left">系统用户：</td>
					<td class="l-t-td-right">
						<input name="enterInfo.login_user_id" id="login_user_id" type="hidden" />
						<input name="enterInfo.login_user_name" id="login_user_name" type="text" ltype='text' validate="{required:true,maxlength:32}" onclick="selectLoginUser()" ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectLoginUser()}}]}"/>
					</td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right"><textarea name="enterInfo.remark" cols="20" rows="2" class="l-textarea"></textarea>	</td>	
				</tr>
			</table>
		</fieldset>
		</div>
	<!--  <div title="检验信息" id="inspectionInfo" >
		
		</div>
	--> 
	
		</c:if>
	</div>
</form>
</body>
	
</html>
