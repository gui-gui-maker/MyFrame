<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head pageStatus="${param.status}">
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript"
		src="app/payment/change_money_info.js"></script>
	<%
		String pageStatus = request.getParameter("status");
	%>
	<script type="text/javascript">
	var pageStatus="${param.status}";
	$(function () {
		createInfoGrid();
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:saveInfo},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	if(resp.success){
					inspectionChangeGrid.loadData({
						Rows : resp.data["changeMoneyDTOList"]
					});
					$("#formObj").setValues(resp.data);
				}
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
		      		//inspectionChangeGrid.loadData({
		    		//	Rows : []
		    		//});
	            	api.data.window.refreshGrid();
	            	api.close();
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
			if(!validateGrid(inspectionChangeGrid)){
				return false;
			}
			     
			if(confirm("亲，确定保存吗？")){
				$("body").mask("正在保存数据，请稍后！");
				$("#save").attr("disabled","disabled");
				url="inspectionChange/saveBasic.do?status="+pageStatus;
				var formData = $("#formObj").getValues();
				var data = {};
				data = formData;
				data["changeMoneyInfo"] = inspectionChangeGrid.getData();
				$.ajax({
					url: url,
					type: "POST",
				 	datatype: "json",
				 	contentType: "application/json; charset=utf-8",
				 	data: $.ligerui.toJSON(data),
				  	success: function (resp) {
				   		$("body").unmask();
				      	if (resp.success) {
				       		if(api.data.window.Qm){
				                api.data.window.Qm.refreshGrid();
				   			}
				         	top.$.dialog.notice({content:'保存成功！请等待相关负责人审核通过后再收费！查看审批流程请到“金额修改记录”页面查看！'});
				     		api.close();
				     	}else{
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
		var selected = inspectionChangeGrid.rows;
		if (!selected) { alert('请选择行'); return; }
		var change_money;
		var remarks;
		for(var i in selected){			
		  	if(name=='change_money'){
		    	if(valuex==''|| valuex==null || valuex == undefined){
	            	change_money = selected[i].change_money;
	         	}else{
	            	change_money = valuex;
	            	
	            }
		  	}	    
		  	if(name=='remarks'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		    		remarks = selected[i].remarks;
		      	}else{
		      		remarks = valuex;
		      	}
		  	}	
			inspectionChangeGrid.updateRow(selected[i],{
				change_money: change_money,
				remarks: remarks
		    });
		}
	}
	
	function initInfo(){
		$.getJSON("inspectionChange/getReportInfos.do?status=${param.status}&info_ids="+api.data.info_ids, function(resp){
			if(resp.success){
				inspectionChangeGrid.loadData({
					Rows : resp.data["changeMoneyDTOList"]
				});
			}
		})
	}
	
	function close(){
		api.close();
	}	
</script>
</head>
	<body <%if("add".equals(pageStatus)){%>onload="initInfo();"<%}%>>
		<form name="formObj" id="formObj" method="post"
			action="inspectionChange/saveBasic.do"
			getAction="inspectionChange/getDetail.do?id=${param.id}">
			<input id="id" name="id"  type="hidden"  />
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						批量修改金额
					</div>
				</legend>
				<div id="infos"></div>
			</fieldset>	
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						便捷填写（在以下文本框中填写的内容将自动批量生成到上面的表格中）
					</div>
				</legend>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left">修改金额：</td>
						<td class="l-t-td-right">
							<input type="text" name="change_moneys" id="change_moneys" ltype="text" onchange="setValues(this.value,'change_money')" validate="{required:false}" />	
						</td> 
						<td class="l-t-td-left">备注：</td>
						<td class="l-t-td-right">
							<input type="text" name="remarkss" id="remarkss" ltype="text" onchange="setValues(this.value,'remarks')" validate="{required:false}" />	
						</td> 								
					</tr>
				</table>
			</fieldset>	
		</form>
	</body>
</html>