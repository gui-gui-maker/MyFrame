<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>检验收费标准配置</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
			
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		//setForm();
				    		if(confirm("确定保存？")){
						    	//表单提交
						    	$("#formObj").submit();
							}
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

	function selectCode(){
		var type = $("input[id='device_type-txt']").ligerComboBox().getValue();	
		var category = $("input[id='device_category-txt']").ligerComboBox().getValue();
		type = type.substring(0,1);
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

	function callOK(code, name){ 
		$('#device_sort_code').val(code);
		$('#device_name').val(name);
		
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="inspectionInfo/pay/saveBasic.do?status=${param.status}"
		getAction="inspectionInfo/pay/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>检验收费标准配置</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">设备类型：</td>
					<td class="l-t-td-right">
						<u:combo name="device_type" code="device_classify" validate="required:true" />
					</td><!-- tree="true" attribute="treeLeafOnly:true" -->				
					<td class="l-t-td-left">设备类别：</td>
					<td class="l-t-td-right">
						<u:combo name="device_category" code="device_classify" validate="required:true" tree="true" attribute="treeLeafOnly:false"/>
					</td>			
				</tr>
				<tr>
					<td class="l-t-td-left">设备名称：</td>
					<td class="l-t-td-right">
						<input type="hidden" id="device_sort_code" name="device_sort_code"   />
						<input type="text" name="device_name" id="device_name"  validate="{required:true}" ltype="text" onclick="selectCode()"/>
					</td>
					<td class="l-t-td-left">检验类别：</td>
					<td class="l-t-td-right"><u:combo name="check_type" code="BASE_CHECK_TYPE" validate="required:true"/></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">收费指标：</td>
					<td class="l-t-td-right">
						<input type="text" name="pay_key" id="pay_key"  validate="{required:false}" ltype="text" />
					</td>
					<td class="l-t-td-left">收费金额：</td>
					<td class="l-t-td-right">
						<input name="pay_value" id="pay_value" type="text" ltype='float' validate="{required:true,maxlength:18}"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">状态：</td>
					<td class="l-t-td-right">
						<input type="radio" name="data_status"  id="data_status" ltype="radioGroup" validate="{required:false}"
						ligerui="{value:'0',data: [ { text:'启用', id:'0' }, { text:'停用', id:'99' } ] }"/>
					</td>
					<td class="l-t-td-left">&nbsp;</td>
					<td class="l-t-td-right">&nbsp;</td>
				</tr>
				<tr>
					<td class="l-t-td-left">描述：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="pay_desc" id="pay_desc" rows="3" cols="25" class="l-textarea" validate="{maxlength:1000}"></textarea>
					</td>		
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
