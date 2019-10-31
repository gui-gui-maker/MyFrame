<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>供应商管理</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var pageStatus = "${param.status}";
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
	      				if(pageStatus == "addinpage"){
	      					if ($("#formObj").validate().form()) {
	      						var formData = $("#formObj").getValues();
					    		if(chkForm()){
								    $.ajax({
			                          url: "equipment2SupplierAction/save.do?status=add",
			                          type: "POST",
			                          dataType:'json',
			                          contentType: "application/json; charset=utf-8",
			                          data: $.ligerui.toJSON(formData),
			                          success:function (data) {
			                        	  var baseEquipmentSupplier = data.baseEquipmentSupplier;
			                        	  api.data.window.document.getElementById('eq_supplier_id').value =baseEquipmentSupplier.id;
			                        	  api.data.window.document.getElementById('eq_supplier_name').value =baseEquipmentSupplier.supplier_name;
			                        	  api.close();
			                          },
			                      });
					    		}	    					    		
					    	}
	      				}else{
	      					if ($("#formObj").validate().form()) {
	      						var formData = $("#formObj").getValues();
					    		if(chkForm()){
					    			$.ajax({
				                          url: "equipment2SupplierAction/save.do?status=${param.status}",
				                          type: "POST",
				                          dataType:'json',
				                          contentType: "application/json; charset=utf-8",
				                          data: $.ligerui.toJSON(formData),
				                          success:function (data) {
				                        	if (data.success) {
				                        		api.close();
				                        		api.data.window.refreshGrid();
				                        		top.$.dialog.notice({
				            	             		content: "保存成功！"
				            	            	});
				          	                }
				                          },
				                          error: function (data) {
				          	                $.ligerDialog.error('提示：' + data.msg);
				          	              }
				                      });
					    		}	    					    		
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
	function closewindow(){
		api.close();
	}
	String.prototype.Trim = function() {
		var m = this.match(/^\s*(\S+(\s+\S+)*)\s*$/);
		return (m == null) ? "" : m[1];
	}
	String.prototype.isMobile = function() {
		return (/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(this.Trim()));
	}
	String.prototype.isTel = function()
	{
		//"兼容格式: 国家代码(2到3位)-区号(2到3位)-电话号码(7到8位)-分机号(3位)"
	    //return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
	    return (/^(([0\+]\d{2,3}-)?(0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/.test(this.Trim()));
	}
	
	function chkForm() {
		with(document.formObj){
			if(supplier_tel.value.length>0){
				if (supplier_tel.value.isMobile()||supplier_tel.value.isTel())  {
					supplier_tel.value = supplier_tel.value.Trim();
				}else {
					$.ligerDialog.alert("供应商电话请输入正确的手机号码或座机号码！\n\n例如：13916752109或0833-2435195。");
					supplier_tel.focus();
					return false;
				}	
			}		
			if (supplier_contacts_tel.value.isMobile()||supplier_contacts_tel.value.isTel())  {
				supplier_contacts_tel.value = supplier_contacts_tel.value.Trim();
				//alert("您的电话/手机号码是:" + supplier_contacts_tel.value);
				return true;
			}else {
				$.ligerDialog.alert("联系人电话请输入正确的手机号码或座机号码！\n\n例如：13916752109或0833-2435195。");
				supplier_contacts_tel.focus();
				return false;
			}
		}
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="equipment2SupplierAction/save.do?status=${param.status}"
		getAction="equipment2SupplierAction/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<input type="hidden" id="create_by" name="create_by"/>
  		<input type="hidden" id="create_date" name="create_date"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>供应商信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">供应商名称</td>
					<td class="l-t-td-right"><input name="supplier_name" id="supplier_name" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>									
					<td class="l-t-td-left">供应商电话</td>
					<td class="l-t-td-right"><input name="supplier_tel" id="supplier_tel" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">联系人</td>
					<td class="l-t-td-right"><input name="supplier_contacts" id="supplier_contacts" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>									
					<td class="l-t-td-left">联系人电话</td>
					<td class="l-t-td-right"><input name="supplier_contacts_tel" id="supplier_contacts_tel" type="text" ltype='text' validate="{required:true,maxlength:32}"/></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">开始合作时间</td>
					<td class="l-t-td-right"><input name="cooperation_begin" id="cooperation_begin" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>									
					<td class="l-t-td-left">评价等级</td>
					<td class="l-t-td-right"><u:combo name="supplier_judge_level" code="BASE_EQ_SUPPLIER_LEVEL"/></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">评价描述</td>
					<td class="l-t-td-right" colspan="3"><textarea name="supplier_judge_desc" rows="6" cols="50" class="l-textarea" validate="{maxlength:2048}"></textarea></td>									
					</td>	
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
