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
		src="app/report/report_error_info.js"></script>
	<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
	<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
	<%
		CurrentSessionUser user = SecurityUtil.getSecurityUser();
		String org_id = user.getDepartment().getId();
		String org_name = user.getDepartment().getOrgName();
		String user_id = user.getId();
		String user_name = user.getName();
		String curDate = DateUtil.getCurrentDateTime();
		// 数据类型（01：质量部已记录，待审核 03：质量部已审核并已发送至责任人，检验员待处理 04：检验员已纠正，部门负责人待确认 05：部门负责人已确认，质量部待确认  06：质量部确认已整改
		// 2016-09-02质量部孟凡昊提出修改，去掉流程05：部门负责人已确认，质量部待确认 
		// 2017-08-01质量部谢方提出修改，增加流程05：部门负责人已确认，质量部待确认 
		String type = request.getParameter("type");	
		// 不符合项目（0：报告 1：其他）其中报告需要填写不符合纠正流转表，其他不需要
		String error_category = request.getParameter("error_category");	
	%>
	<script type="text/javascript">
	var pageStatus="${param.status}";
	var op_type="${param.type}";
	var errorCategoryList=<u:dict code="REPORT_ERROR_CATEGORY"/>;
	var errorTypeList=<u:dict code="REPORT_ERROE_TYPE1"/>;
	var errorStatus=<u:dict code="error_status"/>;
	 
	$(function () {
		createInfoGrid();
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	       <%
	       		if(StringUtil.isEmpty(type)){
	       			%>
	       			{ text:'选择复制', id:'copy',icon:'copy', click:copyInfo},
	       			{ text:'保存', id:'save',icon:'save', click:saveInfo},
	       			<%
	       		}else{
	       			if("01".equals(type)){	// 01：检验员已提交纠正流转表，等待业务服务部确认
  						%>
  						{
	      					text: "保存", icon: "save", click: function(){
	      						var confirm_result = $("#confirm_result").val();
      							if(confirm_result.length==0){
      								$.ligerDialog.alert("请选择纠正措施！");
      								return;
      							}
      							if ($("#formObj").validate().form()) {
									if(confirm("亲，确定保存吗？保存后原报告将作废，并且不能撤销哦！")){
						    			formObj.action="report/error/confirm.do";
						    			$("#formObj").submit();
							    	}		    				    		
								}
			      			}
		      			},
      					<%
  					}else if("02".equals(type)){	// 02：业务服务部已确认，等待责任部门负责人确认纠正完成情况
  						%>
  						{
	      					text: "保存", icon: "save", click: function(){
	      						var dep_head_remark = $("#dep_head_remark").val();
      							if(dep_head_remark.length==0){
      								$.ligerDialog.alert("请填写完成情况！");
      								return;
      							}
      							if ($("#formObj").validate().form()) {
									if(confirm("亲，确定保存？保存后不可修改哦！")){
						    			formObj.action="report/error/app_dep_finish.do";
						    			$("#formObj").submit();
							    	}		    				    		
								}
			      			}
		      			},
      					<%
  					}else if("03".equals(type)){	// 03：质量部审核通过，检验员待处理
  						%>
  						{
	      					text: "保存", icon: "save", click: function(){
	      						var deal_remark = $("#deal_remark").val();
      							if(deal_remark.length==0){
      								$.ligerDialog.alert("请填写处理结果！");
      								return;
      							}
      							var uploadFiles = $("#uploadFiles").val();
      							if(uploadFiles.length==0){
      								$.ligerDialog.alert("请上传见证照片！");
      								return;
      							}
      							
      							if ($("#formObj").validate().form()) {
									if(confirm("亲，确定保存？保存后不可修改哦！")){
						    			formObj.action="report/error/deal.do";
						    			$("#formObj").submit();
							    	}		    				    		
								}
			      			}
		      			},
      					<%
  					}else if("04".equals(type)){	// 04：检验员已纠正，部门负责人待确认
  						%>
  						{
	      					text: "保存", icon: "save", click: function(){
	      						var dep_head_remark = $("#dep_head_remark").val();
      							if(dep_head_remark.length==0){
      								$.ligerDialog.alert("请填写完成情况！");
      								return;
      							}
      							if ($("#formObj").validate().form()) {
									if(confirm("亲，确定保存？保存后不可修改哦！")){
						    			formObj.action="report/error/app_dep_finish.do";
						    			$("#formObj").submit();
							    	}		    				    		
								}
			      			}
		      			},
      					<%
  					}else if("05".equals(type)){	// 04：部门负责人已确认，质量部待确认
  						%>
  						{
	      					text: "保存", icon: "save", click: function(){
	      						var qua_end_result = $("#qua_end_result").val();
      							if(qua_end_result.length==0){
      								$.ligerDialog.alert("请填写整改情况！");
      								return;
      							}
      							if ($("#formObj").validate().form()) {
									if(confirm("亲，确定保存？保存后不可修改哦！")){
						    			formObj.action="report/error/qua_head_check.do";
						    			$("#formObj").submit();
							    	}		    				    		
								}
			      			}
		      			},
      					<%
  					}
	       		}
	       %>
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(resp){
	        	if(resp.success){
					reportErrorGrid.loadData({
						Rows : resp.reportErrorInfo
					});
					$("#formObj").setValues(resp.data);
					
					if (resp.attachs != null && resp.attachs != undefined){
						showAttachFile(resp.attachs);
					}
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
	    
	 // 附件上传
		var receiptUploadConfig = {
    			fileSize : "10mb",	//文件大小限制
    			businessId : "",	//业务ID
    			buttonId : "procufilesBtn",		//上传按钮ID
    			container : "procufilesDIV",	//上传控件容器ID
    			title : "图片",	//文件选择框提示
    			extName : "jpg,gif,png,bmp",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 1,	//限制上传文件数目
    			callback : function(file){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(file);
    			}
    	};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
	});	
		
	function saveInfo(){
		//验证表单数据
		if ($("#formObj").validate().form()) {
			// 验证grid
			if(!validateGrid(reportErrorGrid)){
				return false;
			}
			if(confirm("亲，确定保存吗？")){
				$("#save").attr("disabled","disabled");
				url="report/error/saveBasic.do";
				var formData = $("#formObj").getValues();
				var data = {};
				data = formData;
				data["reportErrorInfo"] = reportErrorGrid.getData();
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
				      		$.ligerDialog.error(resp.msg);
				      		$("#save").removeAttr("disabled");
				    	}
				  	},
					error: function (resp) {
				   		$("body").unmask();
						$.ligerDialog.error(resp.msg);
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
		var selected = reportErrorGrid.rows;
		if (!selected) { alert('请选择行'); return; }
		var error_category;
		var error_type;
		var error_desc;
		var report_sn;
		for(var i in selected){
			if(name=='error_category'){
		    	if(valuex==''|| valuex==null || valuex ==undefined){
		        	error_category = selected[i].error_category;
		      	}else{
		        	var text= $("input[name='error_categorys']").ligerGetComboBoxManager().getValue();
		      		error_category = text;
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
		  	if(name=='report_sn'){
		    	if(valuex==''|| valuex==null || valuex == undefined){
	            	report_sn = selected[i].report_sn;
	         	}else{
	            	report_sn = valuex;
	            	
	            }
		  	}	    
			reportErrorGrid.updateRow(selected[i],{
				error_type: error_type,
				error_category: error_category,
		   		error_desc: error_desc,
		   		report_sn: report_sn
		    });
		}
	}
	
	function changeFlag(flag){	
		if(flag=="4"){
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
		$.post("report/error/queryReportInfos.do?ids="+ids, function(resp) {
			if (resp.success) {
				reportErrorGrid.addRows(resp.list);
				/*reportPrintGrid.loadData({
					Rows : resp.list
				});*/
			}else{
				$.ligerDialog.error(resp.msg);
			}
		});
	}	
	
	function selectUser(){
		//var org_id = $("#error_dep_id").val();
		var org_id = $("#error_dep_id").ligerGetComboBoxManager().getValue();
		if(org_id == ""){
			$.ligerDialog.alert("请先选择责任部门！");
			return;
		}
		top.$.dialog({
			width : 200,
			height : 420,
			lock : true,
			title : "选择责任人",
			content : 'url:app/report/choose_user_list.jsp?org_id='+org_id,
			data : {
				"window" : window
			}
		});
	}
	
	function callUser(id, name){ 
		$('#error_user_id').val(id);
		$('#error_user_name').val(name);
	}
	
	// 选择审核员
	function selUser() {
		var receiverIdNode = "find_user_id";
		var receiverNameNode = "find_user_name";
		selectUnitOrUser(1, 0, receiverIdNode, receiverNameNode, function(
				callbackData) {
			var userId = callbackData["code"];
			$.ajax({
				url : "rbac/user/detail.do?id=" + userId,
				type : "POST",
				async : false,
				success : function(callbackData) {
					var employee = callbackData.data.employee;
					$("#find_user_id").val(employee.id);
					$("#find_user_name").val(employee.name);
					//$("#idcard").val(callbackData.data.employee.idNo);
				} 
			});
		});
	}
	
	function showDetail(title,url){
		$.post("report/error/record/queryRecordID.do?report_error_id=${param.id}", function(resp) {
			if (resp.success) {
				top.$.dialog({
					width : $(top).width() * 0.65,
					height : $(top).height() * 0.7,
					lock : true,
					title : title,
					data : {
						"window" : window
					},
					content : 'url:' + url + '&id=' + resp.data.record_id + '&org_id=' + resp.data.error_dep_id
				});
			}else{
				$.ligerDialog.error(resp.msg);
			}
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
	function selUser12() {
		selectUnitOrUser(1, 1,"" , "", function(callbackData) {
			var userId = callbackData["code"];
			var nameArr = callbackData["name"];
			$("#error_user_id").val(userId);
			$("#error_user_name").val(nameArr);

		});
	}
	
	//添加附件
	function addAttachFile(files){
		if("${param.status}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			var attContainerId="procufiles";
			$.each(files,function(i){
				var file=files[i];
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile();
		}
	}
	
	// 显示附件
    function showAttachFile(files){
    	if("${param.status}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			//详情
			var attContainerId="procufiles";
			if("${param.status}"=="detail"){
				$.each(files,function(i){
					var file=files[i];
					 //显示附件
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
											"</li>");
				});
			}
			//修改
			else if("${param.status}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>");
				});
				getUploadFile();
			}
		}
    }
	  
  	// 将上传的电子签名id写入隐藏框中
	function getUploadFile(){
		var attachId="";
		var i=0;
		$("#procufiles li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(attachId!=""){
			attachId=attachId.substring(0,attachId.length);
		}
		$("#uploadFiles").val(attachId);
	}
	
	function close(){
		api.close();
	}	
</script>
</head>
	<body>
		<form name="formObj" id="formObj" method="post"
			action="report/error/saveBasic.do"
			getAction="report/error/getDetail.do?id=${param.id}">
			<input id="report_error_id" name="id"  type="hidden" value="${param.id}"/>
			<input id="report_error_sn" name="sn"  type="hidden"  />
			<input id="create_user_id" name="create_user_id"  type="hidden"  />
			<input id="create_user_name" name="create_user_name"  type="hidden"  />
			<input id="create_date" name="create_date"  type="hidden"  />
			
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
<!-- 							<input type="text" name="error_dep_id" id="error_dep_id" ltype="select" validate="{required:true}" /> -->
<!-- 							ligerui="{initValue:'', -->
<%-- 							tree:{checkbox:false,data: <u:dict sql="select id code, ORG_NAME text from SYS_ORG where (ORG_CODE like 'jd%' or ORG_CODE like 'cy%') and ORG_CODE!='cy4_1' order by orders"/>} --%>
<!-- 							}" -->
						</td>	
						<td class="l-t-td-left">责任人：</td>
						<td class="l-t-td-right">
							<input type="hidden" id="error_user_id" name="error_user_id"  />  
							<input type="text" id="error_user_name" name="error_user_name" 
								validate="{required:true}" ltype="text"
							onclick="selUser12()" ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selUser12()}}]}" />
						</td>							
					</tr>
					<tr>
						<td class="l-t-td-left">审核员：</td>
						<td class="l-t-td-right">
							<input type="hidden" id="find_user_id" name="find_user_id"  /> 
							<input name="find_user_name" id="find_user_name" type="text" ltype='text' validate="{required:true,maxlength:32}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selUser()}}]}"/>
							<!-- 
							<input type="text" name="find_user_id" id="find_user_id" ltype="select" validate="{required:true}" ligerui="{
							initValue:'',
							tree:{checkbox:false,data: <u:dict sql="select t.id code,t.name text from employee t,sys_user u where t.id = u.employee_id and t.org_id='${param.org_id}' and u.status='1'"/>}
							}"/>
							 --> 
						</td>	
						<td class="l-t-td-left">审核日期：</td>
						<td class="l-t-td-right">
							<input id="find_date" name="find_date" type="text" ltype="date" validate="{required:true}"
								ligerui="{initValue:'<%=curDate %>',format:'yyyy-MM-dd'}" />
						</td>						
					</tr>
					<tr>
						<td class="l-t-td-left">不符合来源：</td>
						<td class="l-t-td-right" colspan="3">
							<input type="radio" name="error_from"  id="error_from" ltype="radioGroup"
								ligerui="{onChange:changeFlag,value:'1',data: [ { text:'质量抽查', id:'1' }, { text:'部门自查', id:'2' }, { text:'内部审核', id:'3' }, { text:'外部输入', id:'4' } ] }"/>					
						</td>
					</tr>
					<tr id="error_fromTr" style="display:none;">
						<td class="l-t-td-left">外部输入：</td>
						<td class="l-t-td-right">
							<input name="report_error_sn" id="report_error_sn" type="text" ltype='text' validate="{required:false, maxlength:40}" value=""/>
						</td>
					</tr>	
					<tr>
						<td class="l-t-td-left">整改期限：</td>
						<td class="l-t-td-right" >
							<input id="solve_end_date" name="solve_end_date" type="text" ltype="date" validate="{required:true}"
								ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
						</td>
					</tr>				
				</table>
			</fieldset>
			<fieldset class="l-fieldset">
				<legend class="l-legend">
					<div>
						 不符合报告明细表
					</div>
				</legend>
				<div id="infos"></div>
			</fieldset>	
			<%
				if(StringUtil.isNotEmpty(type)){
					%>
					<fieldset class="l-fieldset">
						<legend class="l-legend">
							<div>检验员处理结果（非报告的其他不符合直接在此栏回复即可）</div>
						</legend>
						<table cellpadding="3" cellspacing="0" class="l-detail-table">
							<tr>
								<td class="l-t-td-left">处理结果：</td>
								<td class="l-t-td-right">
									<input name="deal_remark" id="deal_remark" type="text" ltype='text' validate="{maxlength:1000}"/>
								</td>
								<%
									if("04".equals(type) || "05".equals(type) || "06".equals(type)){
										if("0".equals(error_category)){
											%>
											<td class="l-t-td-left">
												<a href="javascript:void(0);" onclick="javascript:showDetail('查看检验报告/证书不符合纠正流转表','app/report/report_error_record_detail.jsp?status=detail');" title="查看检验报告/证书不符合纠正流转表">查看检验报告/证书不符合纠正流转表</a>
											</td>	
											<td class="l-t-td-right"></td>	
											<%	
										}
									}
								%>
							</tr>
							<%
								if("03".equals(type)){
									%>
									<tr>
										<td class="l-t-td-left">见证照片：</td>
										<td class="l-t-td-right">
											<input name="uploadFiles" type="hidden" id="uploadFiles" validate="{maxlength:1000}" />
											<p id="procufilesDIV">
												<a class="l-button" id="procufilesBtn">
													<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
												</a>
											</p>
									    	<div class="l-upload-ok-list">
												<ul id="procufiles"></ul>
											</div>
										</td>
									</tr>
									<%
								}else{
									%>
									<tr>
										<td class="l-t-td-left">见证照片：</td>
										<td class="l-t-td-right">
											<input name="uploadFiles" type="hidden" id="uploadFiles" validate="{maxlength:1000}" />
											<!-- 
											<p id="procufilesDIV">
												<a class="l-button" id="procufilesBtn">
													<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
												</a>
											</p>
											 -->
									    	<div class="l-upload-ok-list">
												<ul id="procufiles"></ul>
											</div>
										</td>
									</tr>
									<%
								}
							%>
							
							<%
							if(!"03".equals(type)){
								%>
								<tr>
									<td class="l-t-td-left">检验员：</td>
									<td class="l-t-td-right">
										<input name="deal_user_name" id="deal_user_name" type="text" ltype='text' />
									</td>	
									<td class="l-t-td-left">纠正完成日期：</td>
									<td class="l-t-td-right">
										<input id="deal_date" name="deal_date" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}" />
									</td>						
								</tr>
								<%
							}
							%>
						</table>
					</fieldset>
					<%
					if("04".equals(type)){
						%>
						<!-- 2016-09-02孟凡昊提出修改，去掉责任部门负责人确认流程 -->
						<!-- 2017-08-01谢方提出修改，增加责任部门负责人确认流程 -->
						<fieldset class="l-fieldset">
								<legend class="l-legend">
									<div>部门负责人确认</div>
								</legend>
								<table cellpadding="3" cellspacing="0" class="l-detail-table">
									<tr>
										<td class="l-t-td-left">完成情况：</td>
										<td class="l-t-td-right" colspan="3">
											<input name="dep_head_remark" id="dep_head_remark" type="text" ltype='text' validate="{maxlength:1000}"/>
										</td>
									</tr>
								</table>
							</fieldset>
						<%
						}else if("05".equals(type)){
							%>
							<!-- 2016-09-02孟凡昊提出修改，去掉责任部门负责人确认流程 -->
							<!-- 2017-08-01谢方提出修改，增加责任部门负责人确认流程 -->
							<fieldset class="l-fieldset">
								<legend class="l-legend">
									<div>部门负责人确认</div>
								</legend>
								<table cellpadding="3" cellspacing="0" class="l-detail-table">
									<tr>
										<td class="l-t-td-left">完成情况：</td>
										<td class="l-t-td-right" colspan="3">
											<input name="dep_head_remark" id="dep_head_remark" type="text" ltype='text' validate="{maxlength:1000}" ligerui="{readonly:true}"/>
										</td>											
									</tr>
									<tr>
										<td class="l-t-td-left">确认人：</td>
										<td class="l-t-td-right">
											<input name="dep_head_name" id="dep_head_name" type="text" ltype='text' ligerui="{readonly:true}"/>
										</td>
										<td class="l-t-td-left">确认日期：</td>
										<td class="l-t-td-right">
											<input id="dep_head_check_date" name="dep_head_check_date" type="text" ltype="date" ligerui="{readonly:true,format:'yyyy-MM-dd'}" />
										</td>											
									</tr>
								</table>
							</fieldset>
							<fieldset class="l-fieldset">
								<legend class="l-legend">
									<div>质量部整改确认</div>
								</legend>
								<table cellpadding="3" cellspacing="0" class="l-detail-table">
									<tr>
										<td class="l-t-td-left">整改情况：</td>
										<td class="l-t-td-right" colspan="3">
											<input name="qua_end_result" id="qua_end_result" type="text" ltype='text' validate="{maxlength:1000}"/>
										</td>	
									</tr>
								</table>
							</fieldset>
							<%
						}
				}else{
					%>
					<c:choose>
						<c:when test="${param.status eq 'add' || param.status eq 'modify'}">
							<fieldset class="l-fieldset">
								<legend class="l-legend">
									<div>
										便捷填写
									</div>
								</legend>
								<table border="1" cellpadding="3" cellspacing="0" width=""
									class="l-detail-table">
									<tr>
										<td class="l-t-td-left">项目：</td>
										<td class="l-t-td-right">
											<input type="text" id="error_categorys" name="error_categorys" ltype="select" validate="{required:false}" ligerui="{
												value:'',
												readonly:true,
												data: <u:dict code="REPORT_ERROR_CATEGORY"/>,
												suffixWidth:'140'
											}" onchange="setValues(this.value,'error_category')"/>
										</td>
										<td class="l-t-td-left">不符合事实陈述：</td>
										<td class="l-t-td-right">
											<input name="error_descs" id="error_descs" type="text" ltype='text' onchange="setValues(this.value,'error_desc')"/>
										</td>
									</tr>
									<tr>
										<td class="l-t-td-left">报告编号：</td>
										<td class="l-t-td-right">
											<input name="report_sns" id="report_sns" type="text" ltype='text' onchange="setValues(this.value,'report_sn')"/>											
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
								</table>
							</fieldset>	
						</c:when>
						<c:otherwise>
							<fieldset class="l-fieldset">
								<legend class="l-legend">
									<div>检验员处理结果</div>
								</legend>
								<table cellpadding="3" cellspacing="0" class="l-detail-table">
									<tr>
										<td class="l-t-td-left">处理结果：</td>
										<td class="l-t-td-right">
											<input name="deal_remark" id="deal_remark" type="text" ltype='text' validate="{maxlength:1000}"/>
										</td>
										<%
											if("0".equals(error_category)){
												%>
												<td class="l-t-td-left">
													<a href="javascript:void(0);" onclick="javascript:showDetail('查看检验报告/证书不符合纠正流转表','app/report/report_error_record_detail.jsp?status=detail');" title="查看检验报告/证书不符合纠正流转表">查看检验报告/证书不符合纠正流转表</a>
												</td>	
												<td class="l-t-td-right"></td>	
												<%
											}
										%>							
									</tr>
									<tr>
										<td class="l-t-td-left">见证照片：</td>
										<td class="l-t-td-right">
											<input name="uploadFiles" type="hidden" id="uploadFiles" validate="{maxlength:1000}" />
											<p id="procufilesDIV">
												<a class="l-button" id="procufilesBtn">
													<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
												</a>
											</p>
									    	<div class="l-upload-ok-list">
												<ul id="procufiles"></ul>
											</div>
										</td>
									</tr>
									<tr>
										<td class="l-t-td-left">检验员：</td>
										<td class="l-t-td-right">
											<input name="deal_user_name" id="deal_user_name" type="text" ltype='text' />
										</td>	
										<td class="l-t-td-left">纠正完成日期：</td>
										<td class="l-t-td-right">
											<input id="deal_date" name="deal_date" type="text" ltype="date" ligerui="{format:'yyyy-MM-dd'}" />
										</td>						
									</tr>
								</table>
							</fieldset>	
							<!-- 2016-09-02孟凡昊提出修改，去掉责任部门负责人确认流程 -->
							<!-- 2017-08-01谢方提出修改，增加责任部门负责人确认流程 -->					
							<fieldset class="l-fieldset">
								<legend class="l-legend">
									<div>部门负责人确认</div>
								</legend>
								<table cellpadding="3" cellspacing="0" class="l-detail-table">
									<tr>
										<td class="l-t-td-left">完成情况：</td>
										<td class="l-t-td-right" colspan="3">
											<input name="dep_head_remark" id="dep_head_remark" type="text" ltype='text' validate="{maxlength:1000}"/>
										</td>	
									</tr>
									<tr>
										<td class="l-t-td-left">确认人：</td>
										<td class="l-t-td-right">
											<input name="dep_head_name" id="dep_head_name" type="text" ltype='text'/>
										</td>
										<td class="l-t-td-left">确认日期：</td>
										<td class="l-t-td-right">
											<input name="dep_head_check_date" id="dep_head_check_date" type="text" ltype='date'/>
										</td>
									</tr>
								</table>
							</fieldset>
							<fieldset class="l-fieldset">
								<legend class="l-legend">
									<div>质量部整改确认</div>
								</legend>
								<table cellpadding="3" cellspacing="0" class="l-detail-table">
									<tr>
										<td class="l-t-td-left">整改情况：</td>
										<td class="l-t-td-right" colspan="3">
											<input name="qua_end_result" id="qua_end_result" type="text" ltype='text' validate="{maxlength:1000}"/>
										</td>	
									</tr>
									<tr>
										<td class="l-t-td-left">确认人：</td>
										<td class="l-t-td-right">
											<input name="qua_head_name" id="qua_head_name" type="text" ltype='text'/>
										</td>
										<td class="l-t-td-left">确认日期：</td>
										<td class="l-t-td-right">
											<input name="qua_head_check_date" id="qua_head_check_date" type="text" ltype='date'/>
										</td>
									</tr>
								</table>
							</fieldset>
						</c:otherwise>
					</c:choose>
					<%
				}
			%>
		</form>
	</body>
</html>