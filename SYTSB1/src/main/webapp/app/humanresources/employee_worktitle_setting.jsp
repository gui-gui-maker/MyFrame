<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!--获取当前登录人  -->
<%
	CurrentSessionUser curUser = SecurityUtil.getSecurityUser();
	User uu = (User)curUser.getSysUser();
	com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
	String userId=e.getId();//员工ID
	String uId = SecurityUtil.getSecurityUser().getId();//用户ID
%>
<head pageStatus = "${param.pageStatus}">
<title>职务任免</title>
<%@include file="/k/kui-base-form.jsp"%>
<%-- <%String  name = java.net.URLDecoder.decode((String)request.getParameter("name"), "UTF-8");%> --%>
<link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="k/kui/frame/ligerTree.js"></script>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
$(function () {
	if("${param.pageStatus}"=="detail"){
		tbar=[{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	}else{
		tbar=[{text: "保存", icon: "save", click: function(){
	    	if ($("#formObj").validate().form()) {
	    		$("#formObj").submit();
	    	}else{
	    		$.ligerDialog.error('提示：' + '请填写完整后保存！');
	    	}}},
			{text: "关闭", icon: "cancel", click: function(){api.close();}}];
	}
	$("#formObj").initForm({
		success: function (response) {//处理成功
    		if (response.success) {
            	top.$.dialog.notice({
             		content: "保存成功！"
            	});
            	api.data.window.Qm.refreshGrid();
            	api.close();
    		} else {
           		$.ligerDialog.error(response.msg);
      		}
		}, getSuccess: function (response){
			$("#formObj").setValues(response.worktitleRecord);
			if (response.attachs != null && response.attachs != undefined){
				showAttachFile(response.attachs);
			}
		},
		showToolbar: true,
        toolbarPosition: "bottom",
        toolbar: tbar
	});
	var receiptUploadConfig = {
			fileSize : "10mb",	//文件大小限制
			businessId : "",	//业务ID
			buttonId : "procufilesBtn",		//上传按钮ID
			container : "procufilesDIV",	//上传控件容器ID
			title : "文件",	//文件选择框提示
			extName : "jpg,gif,png,bmp,mp4,AVI,wma,rmvb,rm,flash,mid,3GP,doc,pdf,txt,xls,rtf,ppt",	//文件扩展名限制
			workItem : "",	//页面多点上传文件标识符号
			fileNum : 1,	//限制上传文件数目
			callback : function(file){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
				addAttachFile(file);
			}
	};
	var receiptUploader= new KHFileUploader(receiptUploadConfig);
})
//添加附件
function addAttachFile(files){
	if(files){
		var attContainerId="procufiles";
		$.each(files,function(i){
			var file=files[i];
			$("#"+attContainerId).append("<li id='"+file.id+"'>"+
					"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"'>"+file.name+"</a></div>"+
					"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
					"</li>");
		});
		getUploadFile();
	}
}
//显示附件文件
function showAttachFile(files){
	if("${param.pageStatus}"=="detail"){
		$("#procufilesBtn").hide();
	}
	if(files){
		//详情
		var attContainerId="procufiles";
		if("${param.pageStatus}"=="detail"){
			$.each(files,function(i){
				var file=files[i];
				//显示附件
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
										"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"'>"+file.fileName+"</a></div>"+
										"</li>");
				//显示图片
				/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'><image style='width:60px;height:60px' src='upload/"+file.filePath+"'></a></div>"+
						"</li>"); */
			});
		}
		//修改
		else if("${param.pageStatus}"=="modify"){
			$.each(files,function(i){
				var file=files[i];
				//显示图片
				/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'><image style='width:60px;height:60px' src='upload/"+file.filePath+"'></a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>"); */
				//显示文件名称
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"'>"+file.fileName+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile();
		}
	}
}
//将上传的附件id写入隐藏框中
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
</script>
</head>
<body>
	<form name="formObj" id="formObj" action="employeeBaseAction/saveWorkTitle.do?pageStatus=${param.pageStatus}" 
	getAction="employeeBaseAction/getWorkTitle.do?empId=${param.empId}&recordId=${param.recordId}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="empId" name="empId" value="${param.empId}"/>
		<input type="hidden" id="worktitleId" name="worktitleId"/>
		<input type="hidden" id="status" name="status" value="1"/>
		<input type="hidden" id="createDate" name="createDate" value="<%=new SimpleDateFormat("yyyy-MM-dd").format(new Date())%>"/>
		<input type="hidden" id="createId" name="createId" value="<%=uId %>"/>
		<input type="hidden" id="createBy" name="createBy" value="<%=curUser.getName() %>"/>
		<table class="l-detail-table">
			<tr>
				<!-- 新任职务 -->
				<td class="l-t-td-left">职务</td>
				<td class="l-t-td-right" colspan="3"><input  name="worktitleName" id="worktitleName" type="text" ltype='text'/></td>
			</tr>
			<tr>
				<!-- 开始时间 -->
				<td class="l-t-td-left">开始时间</td>
				<td class="l-t-td-right" ><input  name="startDate" id="startDate" type="text" ltype='date'/></td>
				<td class="l-t-td-left">到期时间</td>
				<td class="l-t-td-right" ><input  name="endDate" id="endDate" type="text" ltype='date'/></td>
			</tr>
			<tr> 
		        <td class="l-t-td-left"> 文件</td>
		        <td class="l-t-td-right" colspan="3">
		        		<input name="uploadFiles" id="uploadFiles" type="hidden" />
						<c:if  test='${param.pageStatus!="detail" }'>
						<p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
						</p>
						</c:if>
				    	<div class="l-upload-ok-list">
							<ul id="procufiles"></ul>
						</div>
					</td>
		    </tr>
	</table>
  </form>
</body>
</html>