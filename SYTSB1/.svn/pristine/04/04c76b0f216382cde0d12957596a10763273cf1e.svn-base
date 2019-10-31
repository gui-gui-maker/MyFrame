<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>新增设备</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/inspection/device_info.js"></script>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
					top.$.dialog.notice({
	             		content: "保存成功！"
	            	});	
					//api.data.window.api.close();
					api.close(); 
					api.data.pwindow.Qm.refreshGrid();
					 
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){

			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(url){
	      				
	      				url="device/basic/saveSimple.do?type="+document.getElementById("device_sort_code_val").value.substring(0,1);
	      				//alert(document.getElementById("device_sort_code_val").value+'---');
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		var formData = $("#formObj").getValues();
					        var data = {};
					        data = $.ligerui.toJSON(formData);
					      //  alert(data);
				    		$.ajax({
					            url: url,
					            type: "POST",
					            datatype: "json",
					           contentType: "application/json; charset=utf-8",
					            data: data,
					            success: function (data, stats) {
					                if (data["success"]) {
					                	api.close();
					                   // api.data.window.submitAction();
					                } else {
					                	$.ligerDialog.error('保存失败！'+responseText.data);
					                }
					            },
					            error: function (data) {
					                $.ligerDialog.error('保存失败！' + data.msg);
					            }
					        });
				    		 
							    //表单提交
							    //$("#formObj").submit();
							
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});
	});
	
	function getNum(numCode){	
		$.getJSON("device/basic/getNum.do?device_code="+numCode, function(resp){
				
				if(resp.success){
				
				
					$.ligerDialog.alert("注册代码已存在，请核实！", "提示");
					
					$('#device_registration_code').val()=="";
					
					return;
				}
				
			})

		}
</script>
</head>
<body>

	<form name="formObj" id="formObj">

		
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>设备信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">设备品种：</td>
					<td class="l-t-td-right">
						<!--  <input type="text" id="device_sort_code"  name="device_sort_code" onclick="showDialogCombo()"  ltype="text"  validate="{required:true}" 
							ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){showDialogCombo()}}]}"  /></td>-->
					<input type="text" id="device_sort_code" name="device_sort_code" ltype="select" validate="{required:true}"  ligerui="{
					readonly:true,
					tree:{checkbox:false,data: <u:dict sql="select t.id,t.code_table_values_id pid,t.value code,t.name text from PUB_CODE_TABLE_VALUES t,pub_code_table t1 where t.code_table_id=t1.id and   t1.code = 'device_classify' and t.value  like '${param.type}%'"/>}
					}"/>
					</td>
					<td class="l-t-td-left" style="width: 150px">设备所在地：</td>
				<td class="l-t-td-right">
				<input type="text" id="device_area_code" name="device_area_code" ltype="select"  validate="{required:true}"  
				ligerui="{
					initValue:'511101',
					readonly:true,
					data: <u:dict sql="select id,parent_id pid,regional_code code, regional_name text from V_AREA_CODE"/>,
				}"/>
				
				</td>
			</tr>
		
				<tr>

			
				  <td class="l-t-td-left">设备名称：</td>

				<td class="l-t-td-right"><input name="device_name"
					validate="{required:true,maxlength:64}" ltype='text' id="device_name" /></td>		
					<td class="l-t-td-left">使用登记证号：</td>
				<td class="l-t-td-right" ><input name="registration_num"
					validate="{required:false,maxlength:128}"  ltype='text' id="registration_num" /></td>				
				</tr>
				
				<tr>
					<td class="l-t-td-left">设备注册代码：</td>
				<td class="l-t-td-right"><input name="device_registration_code"

					validate="{required:true,maxlength:64}" ltype='text' id="device_registration_code" onchange="getNum(this.value)" /></td>
				   <td class="l-t-td-left" style="width: 150px">出厂编号：</td>
				<td class="l-t-td-right"><input name="factory_code"
					validate="{required:false,maxlength:40}" ltype="text" id="factory_code" /></td>								
				</tr>
			
			</table>
		</fieldset>
	</form>
</body>
</html>
