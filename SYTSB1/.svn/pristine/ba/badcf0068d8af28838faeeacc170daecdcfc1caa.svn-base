<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="com.khnt.rbac.impl.bean.Org"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
<script type="text/javascript" src="app/oa/official_docs/js/_issue_app2.js"></script>
<%
	DateFormat ds = new SimpleDateFormat("yyyyMMddHHmmss");
	String nowNum = ds.format(new Date());
	String now = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String area_code = ((Org)user.getDepartment()).getAreaCode();
	String unit_code = ((Org)user.getDepartment()).getOrgCode();
%>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	var p;
	var status = "${param.status}";
	var bar = [];
		bar = [{
			text : '保存',
			icon : 'save',
			click : save
		}, {
			text : '取消',
			icon : 'cancel',
			click : function() {
				api.close();
			}
		}];
	$(function() {
		var receiptUploadConfig = {
    			fileSize : "10mb",//文件大小限制
    			folder:"supervise/order",
    			businessId : "",//业务ID
    			buttonId : "receiptfilesBtn",//上传按钮ID
    			container : "receiptfilesDIV",//上传控件容器ID
    			title : "请选择附件",//文件选择框提示
    			extName : "doc,docx,rar,zip,png,jpg,gif,xls,xlsx,ppt,pptx,7z,pdf,epub,ceb,txt,bmp",//文件扩展名限制
    			saveDB : true,//是否往数据库写信息
    			attType : "",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
    			fileNum : 30,//限制上传文件数目
    			callback : function(files){//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				
    				addAttachFile(files);
    			}
    		};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
		$("#baseForm").initForm({
			toolbar : bar,
			success : function(response) {//处理成功
				if (response.success) {

					//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
					top.$.notice("操作成功！", 3);
					api.close();
				} else {
					$.ligerDialog.error('保存失败！');
				}
			},
			//取得图片
			getSuccess : function(res) {
				var fileList = res.fileList;
				for (var i = 0; i < fileList.length; i++) {
					showSignPicture(fileList[i]);
				}
				
			}
		});
	})
	
	function showSignPicture(file){
		if(file){
			var data=file;
			createFileView(data.id,data.fileName,true,"receiptfiles",true,function(fid){
				deleteFileUp();
			})
			getUploadFile();
		}
	}
	
	//显示附件文件
    function showAttachFile(files){
    	if("${param.status}"=="detail"){
			$("#receiptfilesBtn").hide();
		}
		if(files!=null&&files!=""){
			$.each(files,function(i){
				var data=files[i];
				$("#receiptfilesBtn").hide();
				createFileView(data.id,data.name,$("head").attr("pageStatus")=="detail"?false:true,"receiptfiles",true,function(fid){
					deleteFileUp();
				})
			})
		}
    }
	function addAttachFile(files){
		if("${param.status}"=="detail"){
			$("#receiptfilesBtn").hide();
		}
		if(files){
			$.each(files,function(i){
				var data=files[i];
				createFileView(data.id,data.name,$("head").attr("pageStatus")=="detail"?false:true,"receiptfiles",true,function(fid){
					deleteFileUp();
				})
				getUploadFile();
			})
		}
	}
	function deleteFileUp(){
		$("#receiptfilesBtn").show();
		$("#procufilesBtn").show();
		getUploadFile();
	}
	//将上传的所有文件id写入隐藏框中
	function getUploadFile(){
		var attachId="";
		var i=0;
		$("#receiptfiles li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(i>=5){
			$("#receiptfilesBtn").hide();
		}
		$("#doc_ids").val(attachId);
	}
	function init(){
		
		
	}

	function save(){
	var draftData = $("#baseForm").getValues();
		$("#baseForm").submit();
	}
	function initUploader() {
		new KHFileUploader({
			buttonId : "upload_btn",
			container : "attsContainer",
			fileSize: "1200mb",
			extName : "doc,docx,rar,zip,png,jpg,gif,xls,xlsx,ppt,pptx,7z,pdf,epub,ceb,txt,bmp",
			callback : function(files) {
				$.each(files,function(){
					var fdata = {
						attId : this.id,
						attName : this.name
						
					};
					addAttachs(fdata);
				});
			}
		});
	}

</script>
</head>
<body onload="init()">
	<form name="baseForm" id="baseForm" method="post" action="contractInfoAction/saveFile.do"
	 getAction="contractInfoAction/detail.do?id=${param.id}">
	<table cellpadding="3" cellspacing="0" class="l-detail-table">
	<input name="id" id="id" type="hidden" value="${param.id }"/>
	 <tr>
				<td class="l-t-td-left">附件：</td>
				<td  class="l-t-td-right" colspan="3" id="receiptfilesDIV">
						<input name="doc_ids" id="doc_ids" type="hidden"/>
						<a class="l-button3"  id="receiptfilesBtn" >+</a>
					<div class="l-upload-ok-list"><ul id="receiptfiles"></ul></div>
				</td>
			</tr> 
			
		</table>
	</form>
</body>
</html>