<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="freemarker.template.SimpleDate"%>
<%@page import="java.text.DateFormat"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.pageStatus}">
<%@ include file="/k/kui-base-form.jsp"%>
<%

	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	String now = df.format(new Date());

%>
<style type="text/css">
	.progressName {
        font-size: 10pt;
        font-weight: 70;
        color: #555;
        width: 40px;
        height: 25px;
        text-align: center;
        white-space: nowrap;
        overflow: hidden;
    }
</style>
<script type="text/javascript" src="/pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
var fileId = "";
var fileName = "";
var flag = false;
var file = null;
	$(function () {
			var receiptUploadConfig = {
	    			fileSize : "300mb",//文件大小限制
	    			folder:"supervise/order",
	    			businessId : "",//业务ID
	    			buttonId : "receiptfilesBtn",//上传按钮ID
	    			container : "receiptfilesDIV",//上传控件容器ID
	    			title : "请选择附件",//文件选择框提示
	    			extName : "doc,docx,pdf,xls,xlsx",//文件扩展名限制
	    			saveDB : true,//是否往数据库写信息
	    			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
	    			fileNum : 1,//限制上传文件数目
	    			workItem : "new",//页面多点上传文件标识符号
	    			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
	    				
	    				addAttachFile(files);
	    			}
	    		};
			var receiptUploader= new KHFileUploader(receiptUploadConfig);
			
		$("#formObj").initForm({    //参数设置示例
			toolbar: [
	    		{ text : '保存', icon : 'save', click : saveClose } ,
			 	{ text:  "关闭", icon: "cancel", click: function(){
			 			api.close();						
					}
				}
			],
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			},getSuccess : function(res) {
				//现用文件
				var newFileList = res.newFileList;
				if(newFileList!=null&&newFileList!=""&&newFileList!="undefined"){
					showNewFileList(newFileList);
				}
				//旧文件
				var oldFileList = res.oldFileList;
				if(oldFileList!=null&&oldFileList!=""&&oldFileList!="undefined"){
					showOldFileList(oldFileList);
				}else{
					$("#oldFiles").hide();
				}
				if("${param.pageStatus}"=="detail"){
					$("#receiptfilesBtn").hide();
				}
				if("${param.step}"=="0"){
					$("#id").val("");
				}
				$("#u_id").val("${param.u_id}");
			}
		});
	});
	
	function saveClose(){
		$("body").mask("正在保存，请等待...");
		if ($("#formObj").validate().form()) {
			var formData = $("#formObj").getValues();
			$("#formObj").submit();
			/* $.ajax({
	            url: "qualityManagerFilesAction/update.do?u_id="+$('#u_id').val(),
	            type: "POST",
	            datatype: "json",
	            contentType: "application/json; charset=utf-8",
	            data: $.ligerui.toJSON(formData),
	            success: function (data, stats) {
	                if (data["success"]) {
	                		top.$.notice("保存成功！");
	                		api.data.window.Qm.refreshGrid();
	                        api.close();
	                } else {
	        	    	$("body").unmask();
                		api.data.window.Qm.refreshGrid();
	                	$.ligerDialog.error('保存失败！');
	                }
	            },
	            error: function (data) {
	    	    	$("body").unmask();
            		api.data.window.Qm.refreshGrid();
	                $.ligerDialog.error('保存失败！');
	            }
	        }); */
	     } else {
		    $("body").unmask();
    		api.data.window.Qm.refreshGrid();
	     }
	}	
	
	function callback(file_id){
		flag = true;
		$("#fileId").val(file_id);
	}
	
	//显示新体系文件
	function showNewFileList(files){
		var isEdit = true;
        if ("${param.pageStatus}" == "detail") {
        	$("#receiptfilesBtn").hide();
        	isEdit = false;
        }
        if (files) {
        	for(var i=0;i<files.length;i++){
        		createFileView(files[i].id,files[i].fileName,isEdit,"newReceiptfiles",true,function(fid){});
        	}
            getUploadFile();
        }
	}
	//显示旧体系文件
	function showOldFileList(files){
		var isEdit = true;
        if ("${param.pageStatus}" == "detail") {
        	$("#receiptfilesBtn").hide();
        	isEdit = false;
        }
        if (files) {
            $.each(files, function (i) {
            	createFileView(files[i].id,files[i].fileName,isEdit,"oldReceiptfiles",true,function(fid){});
            });
            getUploadFile();
        }
	}
	
	function addAttachFile(files){
    	var isEdit = true;
        if ("${param.pageStatus}" == "detail") {
        	$("#receiptfilesBtn").hide();
        	isEdit = false;
        }
        if (files) {
            $.each(files, function (i) {
            	var fileName=$("#fileName").val();
				if(fileName==""){
					$("#fileName").val(files[i].name);
				}
            	createFileView(files[i].id,files[i].name,isEdit,"newReceiptfiles",true,function(fid){});
            });
            getUploadFile();
        }
	}
	//将上传的所有文件id写入隐藏框中
	function getUploadFile(){
		var attachId="";
		var i=0;
		$("#newReceiptfiles li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		$("#oldReceiptfiles li").each(function(){
			attachId+=(attachId==""?"":",")+this.id;
			i=i+1;
		});
		if (attachId != "") {
            attachId = attachId.substring(0, attachId.length);
        }
		$("#fileId").val(attachId);
	}
	
	
</script>
</head>
<body>
<form id="formObj" action="qualityManagerFilesAction/update.do" getAction="qualityManagerFilesAction/detail.do?id=${param.id}">
    <table cellpadding="3" class="l-detail-table">
    	<br/>
	    <input name="id" id="id" type="hidden"/>
	    <input name="authority" id="authority" type="hidden"  />
	    <input name="filePath" id="filePath" type="hidden"   />
	    <input name="uploadTime" id="uploadTime" type="hidden" value="<%=now%>"/>
	    <input name="status" id="status" type="hidden" value="0"/>
	    <input name="archiveDate" id="archiveDate" type="hidden" />
	    <input name="delManId" id="delManId" type="hidden" />
	    <input name="delManName" id="delManName" type="hidden"  />
	    <input name="delDate" id="delDate" type="hidden" />
	    <input name="u_id" id="u_id" type="hidden" />
        <tr>
           
            <td class="l-t-td-left">文件编号：</td>
            <td class="l-t-td-right">
            	<input name="fileNum" id="fileNum" type="text" ltype="text" validate="{required:true,maxlength:32}" />
            </td>
             <td class="l-t-td-left">文件名字：</td>
            <td class="l-t-td-right">
            	<input name="fileName" id="fileName" type="text" ltype="text" validate="{required:true,maxlength:200}" />
            </td>
        </tr>
        <tr>
           
            <td class="l-t-td-left">质量体系类别：</td>
            <td class="l-t-td-right">
            	<input name="fileType" id="fileType" ltype="select" validate="{required:true}"  ligerui="{
					selectBoxHeight:200,
					readonly:true,
					tree:{checkbox:false,data: <u:dict code="tzsb_quality_classify"/>}
					}" />
            </td>
             <td class="l-t-td-left">实施日期：</td>
            <td class="l-t-td-right">
            	<input name="implementDate" id="implementDate" type="text" ltype="date" validate="{required:true}" />
            </td>
        </tr>
        <tr>
           
            <td class="l-t-td-left">部门：</td>
            <td class="l-t-td-right">
	            	<input name="departmentId" id="departmentId" type="hidden" value="<%=user.getDepartment().getId() %>" />
	            	<input name="department" id="department" type="text" ltype="text" value="<%=user.getDepartment().getOrgName() %>"
	            	 validate="{required:true,maxlength:200}" readonly="readonly"/>
            </td>
             <td class="l-t-td-left">登记人：</td>
            <td class="l-t-td-right">
	            <input name="registrantId" id="registrantId" type="hidden" value="<%=user.getId() %>" />
	            	<input name="registrant" id="registrant" type="text" ltype="text"  value="<%=user.getName() %>"
	            	 validate="{required:true,maxlength:200}" readonly="readonly"/>
            </td>
        </tr>
         <tr>
				<td class="l-t-td-left">新体系文件<br></br>附件：</td>
				<td  class="l-t-td-right" colspan="3" id="receiptfilesDIV">
						<input name="fileId" id="fileId" type="hidden"/>
						<a class="l-button3"  id="receiptfilesBtn" >+</a>
					<div class="l-upload-ok-list"><ul id="newReceiptfiles"></ul></div>
				</td>
		</tr> 
		<tr id="oldFiles">
				<td class="l-t-td-left">旧体系文件<br></br>附件：</td>
				<td  class="l-t-td-right" colspan="3" id="oldReceiptfilesDIV">				
					<div class="l-upload-ok-list"><ul id="oldReceiptfiles"></ul></div>
				</td>
		</tr> 

    </table>
</form>
</body>
</html>
