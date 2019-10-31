<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>原始记录检验项目解析信息配置</title>
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

	// 选择报告类型
	function selectReport(){	
		top.$.dialog({
			parent: api,
			width : 600, 
			height : 500, 
			lock : true, 
			title:"选择报告类型",
			content: 'url:app/inspection/choose_reports_list.jsp',
			data : {"parentWindow" : window}
		});
	}
	
	function callBackReport(id, report_name){
		$('#fk_report_id').val(id);			// 报告类型ID
		$('#report_name').val(report_name);	// 报告名称
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="report/record/parse/saveBasic.do?status=${param.status}"
		getAction="report/record/parse/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>原始记录检验项目解析信息配置</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">报告类型：</td>
					<td class="l-t-td-right">
						<input id="fk_report_id" name="fk_report_id"  type="hidden"  />
						<input type="text" id="report_name" name="report_name" ltype='text' validate="{required:true}"
								ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectReport()}}]}" onclick="selectReport()"/>
					</td>		
					<td class="l-t-td-left">检验项目代码：</td>
					<td class="l-t-td-right">
						<input type="text" name="item_name" id="item_name"  validate="{required:true}" ltype="text" />
					</td>			
				</tr>
				<tr>
					<td class="l-t-td-left">检验项目默认值：</td>
					<td class="l-t-td-right">
						<input type="text" name="item_value" id="item_value"  validate="{required:false}" ltype="text" value="符合"/>
					</td>
					<td class="l-t-td-left">是否单独解析：</td>
					<td class="l-t-td-right"><u:combo name="is_check" code="ba_sf" validate="required:true" attribute="initValue:'1'"/></td>	
				</tr>
				<tr>
					<td class="l-t-td-left">解析判断运算符：</td>
					<td class="l-t-td-right">
						<input type="text" name="check_type" id="check_type"  validate="{required:false}" ltype="text" />
					</td>
					<td class="l-t-td-left">解析判断标准值：</td>
					<td class="l-t-td-right">
						<input name="check_value" id="check_value" type="text" validate="{required:false}" ltype="text"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">解析判断依据：</td>
					<td class="l-t-td-right">
						<input type="text" name="check_key" id="check_key"  validate="{required:false}" ltype="text" />
					</td>
					<td class="l-t-td-left">状态：</td>
					<td class="l-t-td-right">
						<input type="radio" name="data_status"  id="data_status" ltype="radioGroup" validate="{required:true}"
						ligerui="{value:'0',data: [ { text:'启用', id:'0' }, { text:'停用', id:'99' } ] }"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">描述：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remarks" id="remarks" rows="3" cols="25" class="l-textarea" validate="{maxlength:1000}"></textarea>
					</td>		
				</tr>
			</table>
		</fieldset>
	</form>
</div>
</body>
</html>
