<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head pageStatus="${param.status}">
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript"
		src="app/maintenance/maintenance_info.js"></script>
	<script type="text/javascript">
	var pageStatus="${param.status}";
	var orgList=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where PROPERTY='dep' and parent_id='100000' order by orders "/>;
	var priorityList=<u:dict code="BASE_PRIORITY"/>;
	var functionList=<u:dict code="MAINTENANCE_FUNCTION"/>;
	var data_statusList=<u:dict code="MAINTENANCE_DATA_STATUS"/>;
	//var userList=<u:dict sql="select u.id code, u.NAME text from SYS_USER u,SYS_ORG o where u.ORG_ID=o.ID AND (o.ORG_CODE like 'jd%' or o.ORG_CODE like 'cy%' or ORG_CODE in ('caiwu','fuwu','ziliang','xxzx')) order by orders"></u:dict>;
	$(function () {
		createInfoGrid();
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:saveInfo},
	            { text:'清空', id:'reset',icon:'modify', click:clearForm},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	if(resp.success){
					maintenanceGrid.loadData({
						Rows : resp.maintenanceInfo
					});
					$("#formObj").setValues(resp.data);
				}
	        },
	        success: function (response) {//处理成功
	    		if (response.success) {
		      		top.$.dialog.notice({
			             content: "保存成功！"
					});
		      		maintenanceGrid.loadData({
		    			Rows : []
		    		});
	            	//api.data.window.refreshGrid();
	            	//api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}
		});
		/*
		var org_id;
        $("#org_ids").ligerComboBox({
      		data: orgList, isMultiSelect: false,
          	onSelected: function (newvalue)
        	{
        		org_id = newvalue;     
          	}
		});
		var userList1=<u:dict sql="select u.id code, u.NAME text from SYS_USER u,SYS_ORG o where u.ORG_ID=o.ID "/>;
		$("#advance_user_ids").ligerComboBox({data:userList1});
		*/
	});	
	
	function clearForm() {
		maintenanceGrid.loadData({
			Rows : []
		});
    	$(':input', $("#formObj")).each(function() {
	    	var type = this.type;
	    	var tag = this.tagName.toLowerCase(); // normalize case
	    	// it's ok to reset the value attr of text inputs,
	    	// password inputs, and textareas
	    	if (type == 'text' || type == 'password' || tag == 'textarea')
	    		this.value = "";
	    	// checkboxes and radios need to have their checked state cleared
	    	// but should *not* have their 'value' changed
	    	else if (type == 'checkbox' || type == 'radio')
	    		this.checked = false;
	    	// select elements need to have their 'selectedIndex' property set to -1
	    	// (this works for both single and multiple select elements)
	    	else if (tag == 'select')
	    		this.selectedIndex = -1;
    	});

    };
		
	function saveInfo(){
		//验证表单数据
		if ($("#formObj").validate().form()) {
			
			// 验证grid
			if(!validateGrid(maintenanceGrid)){
				return false;
			}
			     
			if(confirm("亲，确定保存吗？")){
				$("body").mask("正在保存数据，请稍后！");
				$("#save").attr("disabled","disabled");
				url="maintenance/saveBasic.do?status="+pageStatus;
				var formData = $("#formObj").getValues();
				var data = {};
				data = formData;
				data["maintenanceInfo"] = maintenanceGrid.getData();
				$.ajax({
					url: url,
					type: "POST",
				 	datatype: "json",
				 	contentType: "application/json; charset=utf-8",
				 	data: $.ligerui.toJSON(data),
				  	success: function (resp) {
				   		$("body").unmask();
				      	if (resp["success"]) {
				       		//if(api.data.window.Qm){
				            //    api.data.window.Qm.refreshGrid();
				   			//}
				         	top.$.dialog.notice({content:'保存成功！'});
				     		//api.close();
				         	clearForm();
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
		var selected = maintenanceGrid.rows;
		if (!selected) { alert('请选择行'); return; }
		var org_id;
		var org_name;
		var priority;
		var info_type;
		var advance_date;
		var advance_user_name;
		var func_name;
		var pro_desc;
		var create_date;
		var create_user_name;
		var receive_date;
		var receive_user_name;
		for(var i in selected){
			if(name=='priority'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		        	priority = selected[i].priority;
		      	}else{
		        	var text= $("input[name='prioritys']").ligerGetComboBoxManager().getValue();
		      		priority = text;
		      	}
		  	}
			if(name=='info_type'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		        	info_type = selected[i].info_type;
		      	}else{
		        	var text= $("input[name='types']").ligerGetComboBoxManager().getValue();
		      		info_type = text;
		      	}
		  	}
			if(name=='org_id'){
				if(valuex==''|| valuex==null || valuex == undefined){
	            	org_id = selected[i].org_id;
	         	}else{
	            	var text= $("input[name='org_ids']").ligerGetComboBoxManager().getValue();
	            	$('#org_name').val(valuex);
	            	org_name = valuex;
	            	org_id = text;
	            	//userList=<u:dict sql="select u.id code, u.NAME text from SYS_USER u,SYS_ORG o where u.ORG_ID=o.ID AND o.id = 'org_id'"></u:dict>;
	            	//$("input[name='advance_user_ids']").ligerComboBox({data:userList});	            
	            }
	       	}	
	       	
	       	if(name=='advance_user_name'){
		    	if(valuex==''|| valuex==null || valuex == undefined){
	            	advance_user_name = selected[i].advance_user_name;
	         	}else{
	            	advance_user_name = valuex;
	            	
	            }
		  	}	    
		  	if(name=='advance_date'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		        	advance_date = selected[i].advance_date;
		      	}else{
		      		advance_date = valuex;
		      	}
		  	}
		  	if(name=='func_name'){
		    	if(valuex==''|| valuex==null || valuex == undefined){
	            	func_name = selected[i].func_name;
	         	}else{
	            	func_name = valuex;
	            	
	            }
		  	}
		  	if(name=='pro_desc'){
		    	if(valuex==''|| valuex==null || valuex == undefined){
	            	pro_desc = selected[i].pro_desc;
	         	}else{
	            	pro_desc = valuex;
	            	
	            }
		  	}
		  	if(name=='create_user_name'){
		    	if(valuex==''|| valuex==null || valuex == undefined){
	            	create_user_name = selected[i].create_user_name;
	         	}else{
	            	create_user_name = valuex;
	            	
	            }
		  	}	    
		  	if(name=='create_date'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		        	create_date = selected[i].create_date;
		      	}else{
		      		create_date = valuex;
		      	}
		  	}
		  	if(name=='receive_user_name'){
		    	if(valuex==''|| valuex==null || valuex == undefined){
	            	receive_user_name = selected[i].receive_user_name;
	         	}else{
	            	receive_user_name = valuex;
	            	
	            }
		  	}	    
		  	if(name=='receive_date'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		        	receive_date = selected[i].receive_date;
		      	}else{
		      		receive_date = valuex;
		      	}
		  	}	
			maintenanceGrid.updateRow(selected[i],{
				info_type: info_type,
				priority: priority,
		   		org_id: org_id,
		   		advance_user_name: advance_user_name,
			  	advance_date: advance_date,
			  	func_name:func_name,
			  	pro_desc:pro_desc,
			  	create_user_name: create_user_name,
			  	create_date: create_date,
			  	receive_user_name: receive_user_name,
			  	receive_date: receive_date
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
			action="maintenance/saveBasic.do"
			getAction="maintenance/getDetail.do?id=${param.id}">
			<input id="id" name="id"  type="hidden"  />
			<!-- 
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						基本信息
					</div>
				</legend>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left">标题：</td>
						<td class="l-t-td-right">
							<input name="title" id="title" type="text" ltype='text' validate="{required:false,maxlength:200}"/>
						</td>							
					</tr>
					<tr>
						<td class="l-t-td-left">备注：</td>
						<td class="l-t-td-right">
							<textarea name="remarks" id="remarks" rows="2" cols="25" class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
						</td>							
					</tr>
				</table>
			</fieldset>
			 -->
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						 无附件批量受理
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
						<td class="l-t-td-left">类型：</td>
						<td class="l-t-td-right">
							<input type="text" id="types" name="types" ltype="select" validate="{required:false}" ligerui="{
								value:'',
								readonly:true,
								data: [ { text:'新功能', id:'1' }, { text:'升级功能', id:'2' }, { text:'系统维护', id:'3' }, { text:'数据统计', id:'4' }, { text:'优化功能', id:'5' }],
								suffixWidth:'140'
							}" onchange="setValues(this.value,'info_type')"/>
						</td>
						<!-- 
						<td class="l-t-td-left">优先级：</td>
						<td class="l-t-td-right">
							<input type="text" id="prioritys" name="prioritys" ltype="select" validate="{required:false}" ligerui="{
								value:'',
								readonly:true,
								data: [ { text:'一般', id:'0' }, { text:'重要', id:'1' }, { text:'紧急', id:'2' }],
								suffixWidth:'140'
							}" onchange="setValues(this.value,'priority')"/>
						</td>	
						 -->	
						<td class="l-t-td-left">功能模块：</td>
						<td class="l-t-td-right">
							<!-- <input type="text" name="func_names" id="func_names" ltype="text" onchange="setValues(this.value,'func_name')" validate="{required:false}" /> -->
							<input type="text" name="func_names"
							id="func_names" ltype="select" onchange="setValues(this.value,'func_name')" validate="{required:true}"
							ligerui="{
								readonly:true,
								tree:{checkbox:false,data: <u:dict code="MAINTENANCE_FUNCTION"/>}
							}" />	
						</td> 					
					</tr>
					<tr>
						<td class="l-t-td-left">功能说明：</td>
						<td class="l-t-td-right">
							<input type="text" name="pro_descs" id="pro_descs" ltype="text" onchange="setValues(this.value,'pro_desc')" validate="{required:false}" />	
						</td> 
						<td class="l-t-td-left">来源部门：</td>
						<td class="l-t-td-right">
							<input type="text" name="org_ids" id="org_ids" ltype="select" onchange="setValues(this.value,'org_id')" validate="{required:false}" ligerui="{
							readonly:true,
							tree:{checkbox:false,data: <u:dict sql="select id code, ORG_NAME text from SYS_ORG where PROPERTY='dep' and parent_id='100000' order by orders "/>}
							}"/>	
							<input type="hidden"  name="org_name" id="org_name"/>
						</td>									
					</tr>
					<tr>
						<td class="l-t-td-left">报告人：</td>
						<td class="l-t-td-right">
							<input type="text" name="advance_user_names" id="advance_user_names" ltype="text" onchange="setValues(this.value,'advance_user_name')" validate="{required:false}" />	
						</td>
						<td class="l-t-td-left">报告日期：</td>
						<td class="l-t-td-right">
							<input name="advance_dates"
								type="text" ltype="date" validate="{required:false}"
									ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="advance_dates"  onchange="setValues(this.value,'advance_date')"/>
						</td>								
					</tr>
					<tr>
						<td class="l-t-td-left">记录人：</td>
						<td class="l-t-td-right">
							<input type="text" name="create_user_names" id="create_user_names" ltype="text" onchange="setValues(this.value,'create_user_name')" validate="{required:false}" />	
						</td>
						<td class="l-t-td-left">记录日期：</td>
						<td class="l-t-td-right">
							<input name="create_dates"
								type="text" ltype="date" validate="{required:false}"
									ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="create_dates"  onchange="setValues(this.value,'create_date')"/>
						</td>								
					</tr>
					<tr>
						<td class="l-t-td-left">受理人：</td>
						<td class="l-t-td-right">
							<input type="text" name="receive_user_names" id="receive_user_names" ltype="text" onchange="setValues(this.value,'receive_user_name')" validate="{required:false}" />	
						</td>
						<td class="l-t-td-left">受理日期：</td>
						<td class="l-t-td-right">
							<input name="receive_dates"
								type="text" ltype="date" validate="{required:false}"
									ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="receive_dates"  onchange="setValues(this.value,'receive_date')"/>
						</td>								
					</tr>
				</table>
			</fieldset>	
		</form>
	</body>
</html>