<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html>
	<head pageStatus="${param.status}">
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript"
		src="app/report/report_error_record_info2.js"></script>
	<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		//String org_id = user.getDepartment().getId();
		String org_name = user.getDepartment().getOrgName();
		String user_id = user.getId();
		String user_name = user.getName();
		String curDate = DateUtil.getCurrentDateTime();
		String type = request.getParameter("type");	// 数据类型（01：检验员已提交纠正流转表 02：业务服务部已确认 03：责任部门负责人已确认）
		String org_id = request.getParameter("org_id");	
		if(StringUtil.isEmpty(org_id)){
			org_id = user.getDepartment().getId();
		}
	%>
	<script type="text/javascript">
	var pageStatus="${param.status}";
	var op_type="${param.type}";
	var reportStatusList=<u:dict code="ERROR_REPORT_STATUS"/>;
	var errorTypeList=<u:dict code="REPORT_ERROE_TYPE1"/>;

	$(function () {
		createInfoGrid();
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	       		//{ text:'选择复制', id:'copy',icon:'copy', click:copyInfo},
	       		{ text:'保存', id:'save',icon:'save', click:saveInfo},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	if(resp.success){
					errorRecordGrid.loadData({
						Rows : resp.reportErrorRecordInfo
					});
					$("#formObj").setValues(resp.data);
				}
	        },
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
			}
		});
		var report_error_id = "${param.report_error_id}";
		if(report_error_id != "" && report_error_id != null && report_error_id != undefined){
			$("#error_fromTr").attr('style','display:block');
		}
	});	
		
	function saveInfo(){
		//验证表单数据
		if ($("#formObj").validate().form()) {
			
			// 验证grid
			if(!validateGrid(errorRecordGrid)){
				return false;
			}
			     
			if(confirm("亲，确定保存吗？")){
				$("#save").attr("disabled","disabled");
				url="report/error/record/saveBasic2.do?status="+pageStatus+"&report_error_id=${param.report_error_id}";
				var formData = $("#formObj").getValues();
				var data = {};
				data = formData;
				data["reportErrorRecordInfo"] = errorRecordGrid.getData();
				$("body").mask("正在保存数据，请稍后！");
				$.ajax({
					url: url,
					type: "POST",
				 	datatype: "json",
				 	contentType: "application/json; charset=utf-8",
				 	data: $.ligerui.toJSON(data),
				  	success: function (resp) {
				   		$("body").unmask();
				      	if (resp["success"]) {
				       		if(api.data.window.Qm){
				                api.data.window.Qm.refreshGrid();
				   			}
				         	top.$.dialog.notice({content:'保存成功！'});
				     		api.close();
				     	}else{
				      		$.ligerDialog.error('提示：' + resp.msg);
				      		$("#save").removeAttr("disabled");
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
		var selected = errorRecordGrid.rows;
		if (!selected) { alert('请选择行'); return; }
		var report_status;
		var error_type;
		var deal_remark;
		var error_desc;
		for(var i in selected){
			if(name=='report_status'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		        	report_status = selected[i].report_status;
		      	}else{
		        	var text= $("input[name='report_statuss']").ligerGetComboBoxManager().getValue();
		      		report_status = text;
		      	}
		  	}
			if(name=='error_type'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		        	error_type = selected[i].error_type;
		      	}else{
		        	var text= $("input[name='error_types']").ligerGetComboBoxManager().getValue();
		      		error_type = text;
		      	}
		  	}
	       	
	       	if(name=='error_desc'){
		    	if(valuex==''|| valuex==null || valuex == undefined){
	            	error_desc = selected[i].error_desc;
	         	}else{
	            	error_desc = valuex;
	            	
	            }
		  	}	    
		  	if(name=='deal_remark'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		        	deal_remark = selected[i].deal_remark;
		      	}else{
		      		deal_remark = valuex;
		      	}
		  	}
			errorRecordGrid.updateRow(selected[i],{
				error_type: error_type,
				report_status: report_status,
		   		error_desc: error_desc,
			  	deal_remark: deal_remark
		    });
		}
	}
	
	function changeFlag(flag){	
		if(flag!="2"){
			$("#error_fromTr").attr('style','display:block');
		}else{
			$("#error_fromTr").attr('style','display:none');
		}	
	}
	
	function copyInfo(){
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择复制",
			content: 'url:app/report/copy_report_error_list.jsp',
			data : {"window" : window}
		});
	}
	
	function callBack(ids){
		$.post("report/error/record/queryReportInfos.do?ids="+ids, function(resp) {
			if (resp.success) {
				errorRecordGrid.addRows(resp.list);
				/*reportPrintGrid.loadData({
					Rows : resp.list
				});*/
			}else{
				$.ligerDialog.error(resp.msg);
			}
		});
	}	
	// 选择责任人
	function selUser12() {
		selectUnitOrUser(1, 1,"" , "", function(callbackData) {
			var userId = callbackData["code"];
			var nameArr = callbackData["name"];
			$("#error_user_id").val(userId);
			$("#error_user_name").val(nameArr);

		});
	}
	function chooseOrg(){
	    top.$.dialog({
	        width: 800,
	        height: 450,
	        lock: true,
	        parent: api,
	        title: "选择部门",
	        content: 'url:app/qualitymanage/choose_org.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(!p){
	                top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#error_dep_id").val(p.id);
	            $("#error_dep_name").val(p.name);
	            
	        }
	    });
	}  
	function close(){
		api.close();
	}	
</script>
</head>
	<body>
		<form name="formObj" id="formObj" method="post"
			action="report/error/record/saveBasic2.do"
			getAction="report/error/record/getReportErrorDetail.do?id=${param.report_error_id}">
			<input id="id" name="id"  type="hidden"  />
			<input id="sn" name="sn"  type="hidden"  />
			<input id="report_sn" name="report_sn"  type="hidden"  />
			<input id="new_report_sn" name="new_report_sn"  type="hidden"  />
			<input id="confirm_flag" name="confirm_flag"  type="hidden" value="${param.confirm_flag}" />
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						基本信息
					</div>
				</legend>
				<table border="1" cellpadding="3" cellspacing="0" width=""
					class="l-detail-table">
					<tr>
						<td class="l-t-td-left">责任部门：</td>
						<td class="l-t-td-right">
							<input name="error_dep_id" id="error_dep_id" type="hidden" value="<%=org_id %>"/>
							<input name="error_dep_name" id="error_dep_name" type="text" ltype='text' 
							ligerui="{readonly:true,value:'<%=org_name %>'}"  onclick="chooseOrg();" />
						</td>	
						<td class="l-t-td-left">责任人：</td>
						<td class="l-t-td-right">
							<input name="error_user_id" id="error_user_id" type="hidden" value="<%=user_id %>"/>
							<input name="error_user_name" id="error_user_name" type="text" ltype='text' 
							onclick="selUser12()" ligerui="{readonly:true,value:'<%=user_name %>',iconItems:[{icon:'add',click:function(){selUser12()}}]}" />
<%-- 							ligerui="{readonly:true,value:'<%=user_name %>'}" /> --%>
						</td>							
					</tr>
					<tr>
						<td class="l-t-td-left">审核员：</td>
						<td class="l-t-td-right">
							<input type="hidden" id="find_user_id" name="find_user_id"  /> 
							<input name="find_user_name" id="find_user_name" type="text" ltype='text' ligerui="{readonly:true}" />
							<!-- 
							<input type="text" name="find_user_id" id="find_user_id" ltype="select" validate="{required:true}" ligerui="{
							initValue:'',
							tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t where t.org_id='${param.org_id}' "/>}
							}"/>
							 -->
						</td>	
						<td class="l-t-td-left">审核日期：</td>
						<td class="l-t-td-right">
							<input id="find_date" name="find_date" type="text" ltype="date" validate="{readonly:true}"
								ligerui="{format:'yyyy-MM-dd'}" />
						</td>						
					</tr>
					<tr>
						<td class="l-t-td-left">不符合来源：</td>
						<td class="l-t-td-right" colspan="3">
							<input type="radio" name="error_from"  id="error_from" ltype="radioGroup"
								ligerui="{readonly:true,data: [ { text:'部门自查', id:'2' }, { text:'质量抽查', id:'1' }, { text:'内部审核', id:'3' }, { text:'外部输入', id:'4' } ] }"/>					
						</td>
					</tr>
					<tr id="error_fromTr" style="display:none;">
						<td class="l-t-td-left">不符合编号：</td>
						<td class="l-t-td-right" colspan="3">
							<input name="report_error_sn" id="report_error_sn" type="text" ltype='text' validate="{required:true, maxlength:40}" ligerui="{readonly:true}"/>
						</td>
					</tr>					
				</table>
			</fieldset>
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						 检验报告/证书不符合纠正明细表
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
				<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
					<tr>
						<td class="l-t-td-left">报告状态：</td>
						<td class="l-t-td-right">
							<input type="text" id="report_statuss" name="report_statuss" ltype="select" validate="{required:false}" ligerui="{
								value:'',
								readonly:true,
								data: <u:dict code="ERROR_REPORT_STATUS"/>,
								suffixWidth:'140'
								}" onchange="setValues(this.value,'report_status')"/>
						</td>
						<td class="l-t-td-left">不符合类型：</td>
						<td class="l-t-td-right">
							<input type="text" id="error_types" name="error_types" ltype="select" validate="{required:false}" ligerui="{
								value:'',
								readonly:true,
								data: <u:dict code="REPORT_ERROE_TYPE1"/>,
								suffixWidth:'140'
							}" onchange="setValues(this.value,'error_type')"/>
						</td>
					</tr>
					<tr>
						<td class="l-t-td-left">不符合事实陈述：</td>
						<td class="l-t-td-right" colspan="3">
							<textarea name="error_descs" id="error_descs" rows="3" cols="25" class="l-textarea" onchange="setValues(this.value,'error_desc')" validate="{required:false,maxlength:1000}"></textarea>
						</td>		
					</tr>
				</table>
			</fieldset>	
		</form>
	</body>
</html>