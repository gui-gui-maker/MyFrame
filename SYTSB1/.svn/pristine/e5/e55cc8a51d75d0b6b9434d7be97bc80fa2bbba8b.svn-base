<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head pageStatus="${param.status}">
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript"
		src="app/device/device_copy_info.js"></script>
	<script type="text/javascript">
	var pageStatus="${param.status}";
	$(function () {
		createDeviceInfoGrid();
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:saveInfo},
	           /*  { text:'清空', id:'reset',icon:'modify', click:clearForm}, */
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
		      		deviceCopyInfoGrid.loadData({
		    			Rows : []
		    		});
	            	//api.data.window.refreshGrid();
	            	//api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}
		});
	});	
	
	function saveInfo(){
		//验证表单数据
		if ($("#formObj").validate().form()) {
			
			// 验证grid
			if(!validateGrid(deviceCopyInfoGrid)){
				return false;
			}
			     
			if(confirm("亲，确定保存吗？")){
				$("body").mask("正在保存数据，请稍后！");
				$("#save").attr("disabled","disabled");

				url="device/basic/copyDevice.do";
				var formData = $("#formObj").getValues();
				var data = {};
				data = formData;
				data["device_registration_codes"] = deviceCopyInfoGrid.getData();

				var count = "${param.count}";
				if(deviceCopyInfoGrid.getData().length!=parseInt(count)){
					$("body").unmask();
					$.ligerDialog.alert("您填写的设备注册代码个数（"+deviceCopyInfoGrid.getData().length+"）不等于复制台数（"+count+"）");
					$("#save").removeAttr("disabled");
					return;
				}

				$.ajax({
					url: url,
					type: "POST",
				 	datatype: "json",
				 	contentType: "application/json; charset=utf-8",
				 	data: $.ligerui.toJSON(data),
				  	success: function (resp) {
				   		$("body").unmask();
				      	if (resp["success"]) {
				         	top.$.dialog.notice({content:'复制成功！'});
				         	if(api.data.window.Qm){
				                api.data.window.Qm.refreshGrid();
				   			}
				         	api.close();
				     	}else{
							$("body").unmask();
				      		$.ligerDialog.error('提示：' + resp.msg);
				    	}
				  	},
					error: function (resp) {
				   		$("body").unmask();
						$.ligerDialog.error('提示：' + resp.msg);
						$("#save").removeAttr("disabled");
					}
				});
			}        
		}
	}
	
	function setValues(valuex,name){
		if(valuex==""){
			return;
		}
		var selected = deviceCopyInfoGrid.rows;
		if (!selected) { alert('请选择行'); return; }
		var org_id;
		for(var i in selected){
			
	       	if(name=='device_registration_code'){
		    	if(valuex==''|| valuex==null || valuex == undefined){
		    		device_registration_code = selected[i].device_registration_code;
	         	}else{
	         		device_registration_code = valuex;
	            	
	            }
		  	}	    
		  	
			deviceCopyInfoGrid.updateRow(selected[i],{
				device_registration_code: device_registration_code
		    });
		}
	}
	
	function close(){
		api.close();
	}	
</script>
</head>
	<body>
		<form name="formObj" id="formObj" method="post"
			action="device/basic/copyDevice.do"
			getAction="">
			<input id="id" name="id"  type="hidden" value="${param.id}"/>
			<input id="count" name="count"  type="hidden" value="${param.count}"/>
			<fieldset>
				<legend class="l-legend">
					<div>
						 设备注册代码录入
					</div>
				</legend>
				<div id="infos"></div>
			</fieldset>	
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						便捷填写
					</div>
				</legend>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					
					<tr>
						<td class="l-t-td-left">设备注册代码：</td>
						<td class="l-t-td-right">
							<input type="text" name="device_registration_codes" id="device_registration_codes" ltype="text" onchange="setValues(this.value,'device_registration_code')" validate="{required:false}" />	
						</td>
						<td class="l-t-td-left">&nbsp;&nbsp;</td>
						<td class="l-t-td-right">&nbsp;&nbsp;</td>							
					</tr>
				</table>
			</fieldset>
		</form>
	</body>
</html>