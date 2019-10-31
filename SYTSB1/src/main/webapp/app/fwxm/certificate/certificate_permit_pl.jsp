<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title></title>
<%@include file="/k/kui-base-form.jsp"%> 
<script type="text/javascript" src="pub/bpm/js/util.js"></script>
 <script type="text/javascript" src="app/finance/js/jquery.autocomplete.js"></script>
 <script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
<link rel="Stylesheet" href="app/finance/css/jquery.autocomplete.css" />
  <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
   <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
  <script type="text/javascript">
  $(function() {
	  var tbar=[{ text: '保存', id: 'up', icon: 'save', click: save},
                { text: '关闭', id: 'close', icon:'cancel', click:function(){ api.data.window.Qm.refreshGrid();api.close();}}];
  	$("#certForm").initForm({
  		 showToolbar: true,
         toolbarPosition: "bottom",
  		 toolbar: tbar,
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	          		//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
	          		//$("#basic_id").attr("value",response.data.id);
	            	api.data.window.Qm.refreshGrid();
	            	api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				if("detail" == "${param.pageStatus}"){
					document.getElementById("employeeName").innerHTML=response.employeeBase.name;
				}else{
					$("#employeeName").val(response.employeeBase.name);
					$("#employeeId").val(response.employeeBase.id);
				}
				
				if (response.attachs != null && response.attachs != undefined){
					showAttachFile(response.attachs);
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
  			extName : "jpg,gif,png,bmp,mp4,AVI,wma,rmvb,rm,flash,mid,3GP,doc,pdf,txt,xls,rtf,ppt",	//文件扩展名限制
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
		if("${param.pageStatus}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			var attContainerId="procufiles";
			$.each(files,function(i){
				var file=files[i];
			/* 	$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>"); */
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile();
		}
	}

	// 显示附件
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
				/* 	$("#"+attContainerId).append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
											"</li>"); */
											
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"</li>");
				});
			}
			//修改
			else if("${param.pageStatus}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>");
				});
				getUploadFile();
			}
		}
  }
// 将上传的附件id写入隐藏框中
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
	 function choosePerson(){
         top.$.dialog({
             width: 800,
             height: 450,
             lock: true,
             parent: api,
             title: "选择人员",
             content: 'url:app/common/person_choose.jsp',
             cancel: true,
             ok: function(){
                 var p = this.iframe.contentWindow.getSelectedPerson();
                 if(!p){
                     top.$.notice("请选择人员！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                     return false;
                 }
                 $("#employeeId").val(p.id);
                 $("#employeeName").val(p.name);
             }
         });
     }
	 function save(){
		 var first_get_date=$("#first_get_date").val();
		 var cert_end_date=$("#cert_end_date").val();
		 var cert_issue_org=$("#cert_issue_org").val();
		 $.ajax({
             url: "employee/cert/batchCert.do?id=${param.id}",
             type: "POST",
            /*    dataType: "json", 
             contentType: "application/json; charset=utf-8",  */
             data:{"first_get_date":first_get_date,"cert_end_date":cert_end_date,"cert_issue_org":cert_issue_org},
             success : function(data, stats) {
					$("body").unmask();
					if (data["success"]) {
						
						top.$.dialog.notice({
							content : '保存成功'
						});
						api.data.window.Qm.refreshGrid();
						api.close();
					} else {
						$.ligerDialog.error('提示：' + data.message);
					}
				},
             error : function(data) {
                 $("body").unmask();
                 $.ligerDialog.error('保存数据失败！');
             }
         });}
  </script>
</head>
<body>

<form id="certForm" name="certForm" method="post"  getAction="employee/cert/detailCert.do?id=${param.id}&empId=${param.empId}">
  	<input type="hidden" name="id" id="cert_id"/>
  	<input type="hidden" name="employee.id" id="employeeId" />
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
	    <tr>
	   
	    	<td class="l-t-td-left">注册日期</td>
	       	<td class="l-t-td-right">
	       	<input name="first_get_date" id="first_get_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	       	 <td class="l-t-td-left">有效日期</td>
	       	<td class="l-t-td-right">
	       	<input name="cert_end_date" id="cert_end_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	     	
	    </tr>
	    
	    <tr>
	    	<td class="l-t-td-left">发证机构</td>
	    	<td colspan="3">
	    		<input style="height:30px" name="cert_issue_org" id="cert_issue_org" type="text" validate="{required:true}"/>
	    	</td>
	    </tr>
				
	</table> 
	</form>
</body>
</html>