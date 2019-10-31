<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>添加系统台账</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
				$("body").unmask();
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	            	//window.location="/app/maintenance/maintenance_detail2.jsp";
	          		//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
	          		//$("#basic_id").attr("value",response.data.id);
	         		//api.data.window.refreshGrid();
	            	//api.close();
	            	
	            	if("${param.status}"=="modify"){
	            		api.data.window.refreshGrid();
		            	api.close();
	            	}else{
	            		clearForm($("#formObj"));
		            	$("#procufiles").html("");
	            	}
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				if (response.task_attachs != null && response.task_attachs != undefined){
					showAttachFile(response.task_attachs);
				}
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		if(confirm("确定保存？")){
				    			$("body").mask("正在保存数据，请稍后！");
				    			//表单提交
				    			$("#formObj").submit();
					    	}
				    	}
	      			}
	      		},
				{
					text: "清空", icon: "modify", click: function(){
						//$("#formObj").find("tbody").empty();
						//$("#formObj").setValues("");
						clearForm($("#formObj"));
					}
				}/* ,
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				} */
			], toolbarPosition: "bottom"
		});		
	    
	    function clearForm(form) {
	    	$(':input', form).each(function() {
		    	var type = this.type;
		    	var tag = this.tagName.toLowerCase(); // normalize case
		    	// it's ok to reset the value attr of text inputs,
		    	// password inputs, and textareas
		    	if (type == 'text' || type == 'password' || tag == 'textarea' || type == 'hidden')
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
		
		// 附件上传
		var receiptUploadConfig = {
    			fileSize : "100mb",	//文件大小限制
    			businessId : "",	//业务ID
    			buttonId : "procufilesBtn",		//上传按钮ID
    			container : "procufilesDIV",	//上传控件容器ID
    			title : "图片、Word、Excel",	//文件选择框提示
    			extName : "*",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 1,	//限制上传文件数目
    			callback : function(file){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(file);
    			}
    	};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
	});
	
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
  	
	function selectUser(){
		var org_id = $("#org_id").val();
		var org_name = $("#org_name").val();
		if(org_id == "" || org_name == "" ){
			$.ligerDialog.alert("请先选择业务部门！");
			return;
		}
		top.$.dialog({
			width : 200,
			height : 420,
			lock : true,
			title : "选择报告人",
			content : 'url:app/maintenance/choose_user_list.jsp?org_id='+org_id,
			data : {
				"window" : window
			}
		});
	}
	
	function callUser(id, name){ 
		$('#advance_user_id').val(id);
		$('#advance_user_name').val(name);
	}		
	
	function selectUnit(){
		top.$.dialog({
			width : 290,
			height : 420,
			lock : true,
			title : "单位/部门选择",
			content : 'url:app/maintenance/choose_unit_list.jsp',
			data : {
				"window" : window
			}
		});
	}
	function callUnit(id,text){
		$("#org_name").val(text);
		$("#org_id").val(id);
	}
	function selectTask(){
		var url = 'url:app/func_task/func_task_list4.jsp';
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择任务书",
			content: url,
			data : {"parentWindow" : window}
		});
	}
	
	function callBackTask(id,name,sn){
		$('#fk_func_task_id').val(id);
		$('#func_task_name').val(name);
		$('#func_task_sn').val(sn);
	}
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="maintenance/info/saveBasic.do?status=${param.status}"
		getAction="maintenance/info/getDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="basic_id" value="${param.id}"/>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">类型：</td>
					<td class="l-t-td-right"><input type="text" id="info_type"
						name="info_type" ltype="select" validate="{required:true}"
						ligerui="{
								value:'',
								readonly:true,
								data: [ { text:'新增功能', id:'1' }, { text:'运行维护', id:'2' }, { text:'数据处理', id:'4' }, { text:'其他', id:'6' }],
								suffixWidth:'140'
							}" />
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
					<td class="l-t-td-right"><!--<input type="text" name="func_name"
						id="func_name" ltype="text" validate="{required:true}" />-->
						<u:combo name="func_name" code="MAINTENANCE_FUNCTION" modify="false" validate="required:true"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">功能说明：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="pro_desc" id="pro_desc" rows="3" cols="25" class="l-textarea" validate="{required:true,maxlength:2000}"></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">来源部门：</td>
					<td class="l-t-td-right">
					<input name="org_name" type="text" readonly="readonly" id="org_name" title="点击此处选择业务部门"
					ltype='text' validate="{required:true}" onclick="selectUnit()"
					value="" /><!-- <sec:authentication property="principal.unit.orgName" htmlEscape="false" /> -->
						<!-- 
						<input type="text" name="org_id"
							id="org_id" ltype="select" validate="{required:false}"
							ligerui="{
								readonly:true,
								tree:{checkbox:false,data: <u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' or ORG_CODE in ('caiwu','fuwu','ziliang','xxzx') order by orders "/>}
							}" />
						 -->
						<input type="hidden" name="org_id" id="org_id" />
					</td>
					<td class="l-t-td-left">报告人：</td>
					<td class="l-t-td-right">
						<input type="hidden" name="advance_user_id" id="advance_user_id" />
						<input type="text"
							name="advance_user_name" id="advance_user_name" ltype="text"
								validate="{required:true}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectUser()}}]}" onclick="selectUser()"/><!-- onclick="selectUser()" -->
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">报告日期：</td>
					<td class="l-t-td-right"><input name="advance_date"
						type="text" ltype="date" validate="{required:false}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="advance_date" />
					</td>
					<td class="l-t-td-left">记录人：</td>
					<td class="l-t-td-right"><input type="text"
						name="create_user_name" id="create_user_name" ltype="text"
						validate="{required:false}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">记录日期：</td>
					<td class="l-t-td-right"><input name="create_date"
						type="text" ltype="date" validate="{required:false}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="create_date" />
					</td>
					<td class="l-t-td-left">受理人：</td>
					<td class="l-t-td-right"><input type="text"
						name="receive_user_name" id="receive_user_name" ltype="text"
						validate="{required:false}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">受理日期：</td>
					<td class="l-t-td-right"><input name="receive_date"
						type="text" ltype="date" validate="{required:false}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="receive_date" />
					</td>
					<td class="l-t-td-left">任务书：</td>
					<td class="l-t-td-right">
						<input name="fk_func_task_id" id="fk_func_task_id" type="hidden" />
						<input name="func_task_name" id="func_task_name" type="hidden" />
						<input type="text" name="func_task_sn" id="func_task_sn" ltype="text"
						validate="{required:false}" onclick="selectTask()"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectTask()}}]}"/>
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>附加说明</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">附件：</td>
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
			</table>
		</fieldset>
	</form>
</body>
</html>
