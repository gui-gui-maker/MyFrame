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
	            { text:'保存', id:'save',icon:'save', click:saveInfo },
	            { text:'关闭', id:'close',icon:'cancel', click:close }
	        ],
	        getSuccess:function(res){
	        
	        }
	    });
	    
	    function saveInfo(){
	    	var deviceId = "${param.id}";
	    	var formData = $("#formObj").getValues();
			var data = {};
			data = $.ligerui.toJSON(formData);
	        $.ajax({
	            url: "device/basic/modifyDevice.do?deviceId="+deviceId,
	            data: data,	//JSON.stringify(json)把json转化成字符串
	            cache:false,    
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            success: function (data, stats) {
	                if (data["success"]) {
	                	top.$.notice("批量修改成功！");
	               		api.close();
	                	api.data.window.submitAction();
	                } else {
	                	$.ligerDialog.error(data.msg);
	                }
	            },
	            error: function (data) {
	                $.ligerDialog.error(data.msg);
	            }
	        });
	    }
	});
	
	function close(){
		api.close();
	}
	
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
	
	function callBack(id,name,address,com_code){
		//判断选择单位类型关联相应字段 0 :使用单位 1：制造单位 2： 安装代为 3：设计单位 4：产品监检单位 5:锅炉产品监检单位 9：维保单位
		if(com_type=='0'){
			$('#fk_company_info_use_id').val(id);
			$('#company_name').val(name);
		}else if(com_type=='9'){
			$('#fk_maintain_unit_id').val(id);
			$('#maintain_unit_name').val(name);
		}
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
	
	function selectStreet(){
		var type = '${param.device_type}';
		var area_code = $("#device_area_code").val();
		if(area_code==""){
			area_code = '${param.device_area_code}';
		}
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
	
</script>
</head>
<body>
<form name="formObj" id="formObj" method="post" >
	<fieldset class="l-fieldset">
		<legend class="l-legend">
			<div>基本信息</div>
		</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">使用单位：</td>
					<td class="l-t-td-right" colspan="3"><input
						name="fk_company_info_use_id" id="fk_company_info_use_id"
						type="hidden" /> <input type="text" id="company_name"
						name="company_name" ltype="text" onclick="selectorg('0')"
						ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('0')}}]}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">使用登记证号：</td>
					<td class="l-t-td-right"><input name="registration_num"
						validate="{required:false,maxlength:30}" ltype='text'
						id="registration_num" /></td>
					<td class="l-t-td-left">注册登记机构：</td>
					<td class="l-t-td-right"><input name="registration_agencies"
						validate="{required:false,maxlength:200}" ltype='text'
						id="registration_agencies" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">登记人员：</td>
					<td class="l-t-td-right"><input name="registration_op"
						validate="{required:false,maxlength:64}"
						value="<sec:authentication property="principal.name" />"
						ltype="text" id="registration_op" /></td>
					<td class="l-t-td-left">登记日期：</td>
					<td class="l-t-td-right"><input name="registration_date"
						id="registration_date" type="text" ltype="date"
						validate="{required:false}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">维保单位：</td>
					<td class="l-t-td-right" colspan="3"><input
						name="fk_maintain_unit_id" id="fk_maintain_unit_id" type="hidden" />
						<input type="text" id="maintain_unit_name"
						name="maintain_unit_name" ltype="text" onclick="selectorg('9')"
						ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg('9')}}]}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left" style="width: 150px">维保单位联系人：</td>
					<td class="l-t-td-right"><input name="maintenance_man"
						validate="{required:false,maxlength:64}" ltype="text"
						id="maintenance_man" />
					</td>
					<td class="l-t-td-left" style="width: 150px">维保单位<br />联系人电话：</td>
					<td class="l-t-td-right">
							<input name="maintenance_tel"
								validate="{required:false,maxlength:64}" value="" type="text"
								ltype="text" id="maintenance_tel" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left" style="width: 150px">安全管理人员：</td>
					<td class="l-t-td-right"><input name="security_op"
						validate="{required:false,maxlength:64}" ltype="text"
						id="security_op" />
					</td>
					<td class="l-t-td-left" style="width: 150px">安全管理<br />联系电话：</td>
					<td class="l-t-td-right">
							<input name="security_tel"
								validate="{required:false,maxlength:64}" value="" type="text"
								ltype="text" id="security_tel" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">设备所在区域：</td>
					<td class="l-t-td-right"><input type="hidden"
						id="device_area_code" name="device_area_code" /> <input
						type="text" id="device_area_name" name="device_area_name"
						readonly="readonly" validate="{required:false}" ltype="text"
						onclick="selectArea()" />
					</td>
					<c:if test="${param.device_type=='3'}">
						<td class="l-t-td-left" style="width: 150px">设备所在街道：</td>
						<td class="l-t-td-right"><input type="hidden"
							id="device_street_code" name="device_street_code" /> <input
							type="text" id="device_street_name" name="device_street_name"
							readonly="readonly" validate="{required:false}" ltype="text"
							onclick="selectStreet()" />
						</td>
					</c:if>
				</tr>
				<tr>
					<td class="l-t-td-left">设备使用地点：</td>
					<td class="l-t-td-right" colspan="3"><input
						id="device_use_place" name="device_use_place"
						validate="{maxlength:100}" value="" type="text" ltype="text" /></td>
				</tr>
			</table>
		</fieldset>
</body>
</html>
