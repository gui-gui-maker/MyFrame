<%@page import="com.ctc.wstx.util.DataUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.utils.DateUtil"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%

	CurrentSessionUser sessionUser = SecurityUtil.getSecurityUser();
	String unit=sessionUser.getUnit().getOrgName();
	String userName=sessionUser.getName();
	String id=sessionUser.getId();
	java.text.SimpleDateFormat formatter = new java.text.SimpleDateFormat("yyyy-MM-dd"); 
	java.util.Date currentTime = new java.util.Date();//得到当前系统时间 
	String str_date1 = formatter.format(currentTime); //将日期时间格式化 

%>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="app/humanresources/overtime_allowance/inner_opinsp.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/photograph/js/photograph.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/payment/unit/ChipCombobox.js"></script>
<link type="text/css" rel="stylesheet"
	href="app/qualitymanage/css/form_detail.css" />
<script type="text/javascript">
var opAttr=[];
var p;
var pageStatus = "${param.status}"

$(function() {
	initGrid();
	$("#formObj").initForm({
		toolbar : [{
			text : '保存',
			icon : 'save',
			click : save
		}, {
			text : '取消',
			icon : 'cancel',
			click : function() {
				api.close();
			}
		}],
		success : function(response) {//处理成功
			if (response.success) {
				top.$.notice("保存成功！");					
				api.close();
				api.data.window.Qm.refreshGrid();
			} else {
				$.ligerDialog.error('保存失败！<br/>' + response.msg);
			}
		},
		getSuccess: function (response) {
			if(response.success){
				if(response.data!=null){
					$("#formObj").setValues(response.data)
					if (response.attachs != null && response.attachs != undefined)
						showAttachFile(response.attachs,"procufiles");
					
				
				if (response.attachs2 != null && response.attachs2 != undefined)
					showAttachFile(response.attachs2,"otherprocufiles");
					
			
			if (response.attachs3 != null && response.attachs3 != undefined)
				showAttachFile(response.attachs3,"anotherprocufiles");
					
		}
				if(response.data!=null&&response.data.allowancefos!=null){
					deviceGrid.loadData({Rows : response.data.allowancefos});
				}
			}
			else{
				$.ligerDialog.error("获取信息失败！<br/>" + response.msg);
			}
		}
		
	});
	var receiptUploadConfig = {
			fileSize : "10mb",	//文件大小限制
			businessId : "",	//业务ID
			buttonId : "procufilesBtn",		//上传按钮ID
			container : "procufilesDIV",	//上传控件容器ID
			title : "图片",	//文件选择框提示
			extName : "jpg,gif,png,bmp,mp4,AVI,wma,rmvb,rm,flash,mid,3GP,doc,pdf,txt,xls,rtf,ppt",	//文件扩展名限制
			workItem : "",	//页面多点上传文件标识符号
			fileNum : 10,	//限制上传文件数目
			callback : function(files){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理4
				addAttachFile(files,"");
				
			}
	};
	var receiptUploader= new KHFileUploader(receiptUploadConfig);
	var otherreceiptUploadConfig = {
			fileSize : "10mb",	//文件大小限制
			businessId : "",	//业务ID
			buttonId : "procufilesBtnt",		//上传按钮ID
			container : "procufilesDIVt",	//上传控件容器ID
			title : "图片",	//文件选择框提示
			extName : "jpg,gif,png,bmp,mp4,AVI,wma,rmvb,rm,flash,mid,3GP,doc,pdf,txt,xls,rtf,ppt",	//文件扩展名限制
			workItem : "",	//页面多点上传文件标识符号
			fileNum : 10,	//限制上传文件数目
			callback : function(files){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
				addAttachFile(files,"other");
				
			}
	};
	var otherreceiptUploader= new KHFileUploader(otherreceiptUploadConfig);
	var anotherreceiptUploadConfig = {
			fileSize : "10mb",	//文件大小限制
			businessId : "",	//业务ID
			buttonId : "procufilesBtns",		//上传按钮ID
			container : "procufilesDIVs",	//上传控件容器ID
			title : "文件",	//文件选择框提示
			extName : "jpg,gif,png,bmp,mp4,AVI,wma,rmvb,rm,flash,mid,3GP,doc,pdf,txt,xls,rtf,ppt",	//文件扩展名限制
			workItem : "",	//页面多点上传文件标识符号
			fileNum : 10,	//限制上传文件数目
			callback : function(files){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
				addAttachFile(files,"another");
				
			}
	};
	var anotherreceiptUploader= new KHFileUploader(anotherreceiptUploadConfig); 
})


function save(){
	//alert($("#type").ligerComboBox().getValue());
	var uploadFiles = $("#uploadFiles").val(); 
	if(uploadFiles.length<32){
		alert("请确认告知书和型式试验证书都已上传")
			}
	else{
		$("#formObj").submit();
	}
	
 }

//附件上传


function setData(data){

}

function selectFile(){
	var files_list = $.ligerDialog.open({ 
		 title: '选择', 
		 width: 800, 
		 height: 500,
		 parent:api, 
		 url: 'app/device/oldreport_select.jsp',                                                                                                                                                                                                                                                                                        
		 data: {"window" : window},
		 buttons: [
		    { text: '确定', onclick: function(){
		    	var rows = files_list.frame.f_select();
			    if (!rows){
			    	top.$.notice("请选择行！");
			        return;
			    }
			    files_list.close();
			    $("#fk_request_id").val(rows[0].id);
			    $('#template_name').val(rows[0].report_name);
			    $('#template_id').val(rows[0].report_code);
			    $('#role_flag').val(rows[0].role_flag);
			    
			    changeOp();
			    
			    
		    } },
			{ text: '取消', onclick: function(){
				 files_list.close();
		    } }
		 ]
	});
	
}

function changeOp(){
	
	var template_name = $("#template_name").val();
	var template_id = $("#template_id").val();
		opAttr = [];
		var template_ids = template_id.split(",");
		
		var template_name = template_name.split(",");
		for (var i = 0; i < template_ids.length; i++) {
			var op = {};
			op["id"]=template_ids[i];
			op["text"]=template_name[i];
			opAttr[opAttr.length]=op;
	
			
		}
}

function closewindow(){
	api.close();
}
 
function addAttachFile(files,type){
	
	var procufilesBtns = "procufilesBtn";
	var attContainerId="procufiles";
	if(type==""){
		
		attContainerId="procufiles";
	}else if(type=="other"){
		
		attContainerId="otherprocufiles";
		procufilesBtns = "procufilesBtnt";
		
	}else if(type=="another"){
		
		attContainerId="anotherprocufiles";
		procufilesBtns = "procufilesBtns";
	}
	
	if("${param.status}"=="detail"){
		$("#"+procufilesBtns).hide();
	}
	if(files){
		
		
		var ids = "";
		$.each(files,function(i){
			var file=files[i];
			/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
					"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
					"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile(\""+type+"\"))'>&nbsp;</div>"+
					"</li>"); */
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile(\""+type+"\"))'>&nbsp;</div>"+
							"</li>");
			if(ids!=""){
				ids = ids + ","+ file.id;
			}else{
				ids =  file.id;
			}
		});
		getUploadFile(type);
		/* if(type==""){
			$("#uploadFiles").val(ids);
		}else if(type=="other"){
			$("#uploadotherFiles").val(ids);
		}if(type=="another"){
			$("#uploadanotherFiles").val(ids);
		} */
		
		//getUploadanotherFile();
	}
}
 
function getUploadFile(type){
	var files = "procufiles";
	var ids = "uploadFiles";

   if(type=="other"){
	   files = "otherprocufiles";
		ids = "uploadotherFiles";

	}if(type=="another"){
		files = "anotherprocufiles";
		ids = "uploadanotherFiles";
	}
	
	var attachId="";
	var i=0;
	$("#"+files+" li").each(function(){
		attachId+=(i==0?"":",")+this.id;
		i=i+1;
	});
	if(attachId!=""){
		attachId=attachId.substring(0,attachId.length);
	}
	$("#"+ids).val(attachId);
}


//显示附件
function showAttachFile(files,id){
	
	if(id=="procufiles"){
		
		type="";
		procufilesBtns = "procufilesBtn";
	}else if(id=="otherprocufiles"){
		
		type="other";
		procufilesBtns = "procufilesBtnt";
		
	}else if(id=="anotherprocufiles"){
		
		type="another";
		procufilesBtns = "procufilesBtns";
	}
	
	
	if("${param.status}"=="detail"){
		
		$("#procufilesBtn").hide();
		$("#procufilesBtnt").hide();
		$("#procufilesBtns").hide();
	}

	if(files){
		var attContainerId=id;
		
		
		//详情
		if("${param.status}"=="detail"){
		
			$.each(files,function(i){
				var file=files[i];
				 //显示附件
				/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
						"</li>"); */
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
						"</li>");
			});
			 /*  if(type==""){
				$("#uploadFiles").val(ids);
			}else if(type=="other"){
				$("#uploadotherFiles").val(ids);
			}if(type=="another"){
				$("#uploadanotherFiles").val(ids); */
		}  
		//修改
		else if("${param.status}"=="modify"){
		
				$.each(files,function(i){
					var file=files[i];
					
					/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile(\""+type+"\"))'>&nbsp;</div>"+
							"</li>"); */
					 $("#"+attContainerId).append("<li id='"+file.id+"'>"+
								"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
								"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile(\""+type+"\"))'>&nbsp;</div>"+
								"</li>");

				});
				getUploadFile(type);
		}
	}
  	
}

function selectorg(){
	var url = 'url:app/enter/enter_open_list.jsp';
	
	top.$.dialog({
		parent: api,
		width : 800, 
		height : 500, 
		lock : true, 
		title:"选择企业信息",
		content: url,
		data : {"parentWindow" : window}
	});
}

function callBack(id,name,address,com_code,fddbr,tel){
		$('#fk_company_info_use_id').val(id);
		$('#company_name').val(name);
		$('#com_code').val(com_code);
		
}	

function setValues(valuex,name){
	//alert(valuex+name);

	if(valuex==""){
		return;
	}  
         var report_user;
         var report_user_id;
         
      
        
		
        
}



</script>
</head>
<body>

		<form name="formObj" id="formObj" method="post"
			action="oldReportAction/savebasic.do?status=${param.status}"
			getAction="oldReportAction/dle.do?ids=${param.id}">
			
			<h1 id="sg2" align="center"
			style="padding: 5mm 0 2mm 0; font-family: 微软雅黑; font-size: 6mm;">使用旧版电梯报告模板出具报告的申请
		</h1>
		
			<fieldset class="l-fieldset">
		
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
			<input name="id" id="id" type="hidden" />
			<input id="flow_step" name="flow_step" type="hidden" value="0" />
			<input id="data_status" name="data_status" type="hidden" value="1" />
			<input name="handle_op" id="handle_op" type="hidden" />
			<input name="handle_op_id" id="handle_op_id" type="hidden" />
			<input name="enter_op" id="enter_op" type="hidden" />
			<input name="enter_op_id" id="enter_op_id" type="hidden" />
			<%-- <input name="enter_time" id="enter_time" type="hidden" ligerui="{initValue:'<%=currentTime%>',format:'yyyy-MM-dd '}" /> --%>
			<input name="sub_status" id="sub_status" type="hidden" value="0"/>

			<input name="fk_request_id" id="fk_request_id" type="hidden" />
			
			<input name="leader_audit" id="handle_op" type="hidden" />
			<input name="leader_audit_op" id="handle_op_id" type="hidden" />
			<%-- <input name="leader_audit_time" id="leader_audit_time" type="hidden" ligerui="{initValue:'<%=currentTime%>',format:'yyyy-MM-dd '}" /> --%>
			<input name="leader_audit_remark" id="leader_audit_remark" type="hidden" />
			<input name="dean_audit" id="dean_audit" type="hidden" />
			<input name="dean_audit_op" id="dean_audit_op" type="hidden" />
			<%-- <input name="dean_audit_time" id="dean_audit_time" type="hidden"  ligerui="{initValue:'<%=currentTime%>',format:'yyyy-MM-dd '}"/> --%>
			<input name="dean_audit_remark" id="dean_audit_remark" type="hidden" />
			<input name="flag" id="flag" type="hidden" />
			<input name="remark" id="remark" type="hidden" />		
					<tr>
						<td class="l-t-td-left">申请人：</td>
				 		<td class="l-t-td-right">
					 		<input type="hidden" name="applicants_id" id="applicants_id" value="<%=id %>"/>
					 		<input type="text" id="applicants" name="applicants"  ltype="text" value="<%=userName %>" readonly="readonly" />
					 	</td>
				        <td class="l-t-td-left">申请原因：</td>
				        <td class="l-t-td-right"><input type="text" id="reason"
					name="reason" ltype='text' validate="{required:true}"		 /></td>
					 </tr>
					<tr>
						<td class="l-t-td-left">旧模板：</td>
				 		<td class="l-t-td-right" >
					 		<input type="hidden" name="template_id" id="template_id" />
					 		<input type="text" id="template_name" name="template_name"  ltype="text" validate="{required:true,maxlength:1000}" onclick="selectFile()"
					 		 readonly="readonly"
					 			ligerUi="{iconItems:[{icon:'add',click:selectFile}]}" />
					 			
				 		</td>
				 		<td class="l-t-td-left">可以使用人员：</td>
				 		<td class="l-t-td-right" >
					 		<input type="hidden" name="report_user_id" id="report_user_id" />
					 		<input type="text" id="report_user" name="report_user"  ltype="select" onchange="setValues(this.value,'report_user')" validate="{required:false}" ligerui="{
							readonly:true,
							tree:{checkbox:true,data: <u:dict sql="select t.id, t.pid, t.code, t.text from(select o.id as id,o.id as code, o.org_code  as tcode, o.ORG_NAME as text,o.PARENT_ID as pid from sys_org o union select e.id as id, e.id as code, e.code as tcode, e.NAME as text, e.ORG_ID as pid from employee e where e.ORG_ID != '100049') t where t.id!='100049' start with t.id in ('100020','100021','100022', '100024','100063') connect by t.pid = prior t.id ORDER BY T.TCODE"/>}
							}" />
					 			
				 		</td>
			</tr>
			<tr>
			            <td class="l-t-td-left">使用单位：</td>
						<td class="l-t-td-right">
						<input name="fk_company_info_use_id" id="fk_company_info_use_id" type="hidden" />
							<input name="com_code" id="com_code" type="hidden" />
							
						  <input type="text" id="company_name" name="company_name"   ltype="text"  validate="{required:true}" onclick="selectorg('0')"
											ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:selectorg}]}"/>
				 		</td>
				 		<td class="l-t-td-left">使用台数：</td>
				        <td class="l-t-td-right"><input type="text" id="report_nub"
					name="report_nub" ltype='text' validate="{required:true}"		 /></td>
			</tr>
			
			<tr>
				<td class="l-t-td-left"  colspan="4">见证材料</td>
			</tr>
			<tr>
					<td class="l-t-td-left" colspan="4">告知书（必要）</td>
					</tr
					<tr>
					<td class="l-t-td-right"  colspan="4">
						<input name="uploadFiles" type="hidden" id="uploadFiles" name="uploadFiles" validate="{maxlength:1000}" />
						<p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">上传附件</span></span>
							</a>
						</p>
				    	<div class="l-upload-ok-list">
							<ul id="procufiles"></ul>
						</div>
					</td>
				</tr>
			 <tr>
					<td class="l-t-td-left" colspan="4">型式试验证书（必要）：</td>
					</tr
					<tr>
					<td class="l-t-td-right" colspan="4" >
						<input name="uploadotherFiles" type="hidden" id="uploadotherFiles" validate="{maxlength:1000}" />
						<p id="procufilesDIVt">
							<a class="l-button" id="procufilesBtnt">
								<span class="l-button-main"><span class="l-button-text">上传附件</span></span>
							</a>
						</p>
				    	<div class="l-upload-ok-list">
							<ul id="otherprocufiles"></ul>
						</div>
						
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left" colspan="4">其它证明材料：</td>
					</tr
					<tr>
					<td class="l-t-td-right" colspan="4" >
						<input name="uploadanotherFiles" type="hidden" id="uploadanotherFiles" validate="{maxlength:1000}" />
						<p id="procufilesDIVs">
							<a class="l-button" id="procufilesBtns">
								<span class="l-button-main"><span class="l-button-text">上传附件</span></span>
							</a>
						</p>
				    	<div class="l-upload-ok-list">
							<ul id="anotherprocufiles"></ul>
						</div>
						
					</td>
				</tr> 
			</table>
	</fieldset>
	
	
</body>
</html>
