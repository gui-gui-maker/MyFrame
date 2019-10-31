<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>人员信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript">	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	            	api.data.window.submitAction();//执行列表页面函数
					api.close();
	            	//api.close();
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				if (response.attachs != null && response.attachs != undefined){
					showAttachFile(response.attachs);
				}
				if (response.employeePrinter != null && response.employeePrinter != undefined){
					if(response.employeePrinter.printer_name!="" && response.employeePrinter.printer_name!=null){
						if("detail" == '${param.status}'){
							document.getElementById("printer_name").innerHTML=response.employeePrinter.printer_name;
						}else{
							$("#printer_name").val(response.employeePrinter.printer_name);
						}
					}
					if(response.employeePrinter.printer_name_tags!="" && response.employeePrinter.printer_name_tags!=null){
						if("detail" == '${param.status}'){
							document.getElementById("printer_name_tags").innerHTML=response.employeePrinter.printer_name_tags;
						}else{
							$("#printer_name_tags").val(response.employeePrinter.printer_name_tags);
						}
					}
				}
			}, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		//if(checkBasic()){
				    			if(confirm("确定保存？")){
				    				//表单提交
				    				$("#formObj").submit();
					    		}
				    		//}				    		
				    	}
	      			}
	      		},
				{
					text: "关闭", icon: "cancel", click: function(){
						api.close();
					}
				}
			], toolbarPosition: "bottom"
		});		
		
		// 电子签名上传
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
		
		// 电子证书上传
		var receiptUploadConfig2 = {
    			fileSize : "10mb",	//文件大小限制
    			businessId : "",	//业务ID
    			buttonId : "procufilesBtn2",		//上传按钮ID
    			attType : "1",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
    			container : "procufilesDIV2",	//上传控件容器ID
    			title : "图片",	//文件选择框提示
    			extName : "jpg,gif,png,bmp",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 1,	//限制上传文件数目
    			callback : function(file){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile2(file);
    			}
    	};
		//var receiptUploader2= new KHFileUploader(receiptUploadConfig2);
	});
	
	//添加电子签名
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
	
	//添加电子证书
	function addAttachFile2(files){
		if("${param.status}"=="detail"){
			$("#procufilesBtn2").hide();
		}
		if(files){
			var attContainerId="procufiles2";
			$.each(files,function(i){
				var file=files[i];
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile2)'>&nbsp;</div>"+
						"</li>");
			});
			getUploadFile2();
		}
	}
	
	// 显示电子签名
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
    
    // 显示电子证书
    function showAttachFile2(files){
    	if("${param.status}"=="detail"){
			$("#procufilesBtn2").hide();
		}
		if(files){
			//详情
			var attContainerId="procufiles2";
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
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile2)'>&nbsp;</div>"+
							"</li>");
				});
				getUploadFile2();
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
	
	// 将上传的电子证书id写入隐藏框中
	function getUploadFile2(){
		var attachId="";
		var i=0;
		$("#procufiles2 li").each(function(){
			attachId+=(i==0?"":",")+this.id;
			i=i+1;
		});
		if(attachId!=""){
			attachId=attachId.substring(0,attachId.length);
		}
		$("#uploadFiles2").val(attachId);
	}
	
	function closewindow(){
		api.close();
	}
	
	function checkBasic(){
	
		var idNo = $("#idNo").val();
		if("" != idNo){
			if(!validateID(idNo)){
	        	$.ligerDialog.alert("您输入的身份证号码无效，请检查！");
	        	$("#idNo").focus();
	        	return false;
	        }
		} 
		var email = $("#email").val();
		if("" != email){
			if(!validateEmail(email)){
				$.ligerDialog.alert("您输入的电子邮箱无效，请检查！");
	        	$("#email").focus();
	        	return false;
			}
		}
		return true;
	}
	
	//验证邮箱格式是否正确
	function validateEmail(value) { 
		if("" != value){
			//对电子邮件的验证
			var myreg = /(\S)+[@]{1}(\S)+[.]{1}(\w)+/;
			if(!myreg.test(value)){
	       		return false;
			}
		} 
		return true;
	}
	
	// 选择所在部门
	function selectorg(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择所在部门",
			content: 'url:app/employee/employee_org_choose_list.jsp',
			data : {"parentWindow" : window}
		});
	}
	
	function callBack(id,name){
		$('#org_id').val(id);	// 所在部门ID
		$('#org_name').val(name);	// 所在部门名称
	}
	
	function checkID(el) {
		return;
	        if(!validateID(el.value)){
	        	$.ligerDialog.alert("您输入的身份证号码无效，请检查！");
	        	$("#idNo").focus();
	        }else{
	        	$.getJSON("employee/basic/validateID.do?idNo="+el.value, function(resp){
					if(!resp.success){
				   		$.ligerDialog.alert(resp.message);
				   		$("#idNo").focus();
				  	}
				})
	        }
	}
	
	//验证身份证号码是否正确
	function validateID(value){
			var checkFlag = new clsIDCard(value);
	        if(checkFlag.IsValid()){
	        	//checkFlag.GetBirthDate()	此方法返回的年月日中月份不包含'0'，例如：1988-7-21
	        	showBirthdayAndSex(value);	//根据身份证号码自动提取出生年月、性别
	        	return true;
	        }else{
	        	return false;
	        }	
	}
	
	//根据身份证号码自动提取出生年月、性别
	function showBirthdayAndSex(val) { 
		var birthdayValue; 
		if(15==val.length){ //15位身份证号码 
			birthdayValue = val.charAt(6)+val.charAt(7); 
			if(parseInt(birthdayValue)<10) { 
				birthdayValue = '20'+birthdayValue; 
			}else{ 
				birthdayValue = '19'+birthdayValue; 
			} 
			birthdayValue=birthdayValue+'-'+val.charAt(8)+val.charAt(9)+'-'+val.charAt(10)+val.charAt(11); 
			if(parseInt(val.charAt(14)/2)*2!=val.charAt(14)){
				$("#gender-txt").ligerGetComboBoxManager().setValue("1");
			}else{
				$("#gender-txt").ligerGetComboBoxManager().setValue("0");
			}		
		} 
		if(18==val.length) { //18位身份证号码 
			birthdayValue=val.charAt(6)+val.charAt(7)+val.charAt(8)+val.charAt(9)+'-'+val.charAt(10)+val.charAt(11)+'-'+val.charAt(12)+val.charAt(13); 	
			if(parseInt(val.charAt(16)/2)*2!=val.charAt(16)){				
				$("#gender-txt").ligerGetComboBoxManager().setValue("1");
			}else{
				$("#gender-txt").ligerGetComboBoxManager().setValue("0");
			}
		} 
		$("#birthDate").val(birthdayValue); 
	} 
</script>
</head>
<body>

<div class="navtab">
	<div title="基本信息" tabId="basicTab" style="height: 400px">
	<form name="formObj" id="formObj" method="post" Action="picture/savePic.do?status=${param.status}"
			getAction="picture/detail.do?id=${param.id}">
	<input id="id" name="id" type="hidden" />
	
		
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
			<td class="l-t-td-left"> 图片标题:</td>
        	<td class="l-t-td-right"  colspan="3">
        		<input id="affiche_title" name="pic_title" type="text" ltype='text' validate="{maxlength:200}"/>
        	</td> 
        	</tr>
        		<tr> 
	        <td class="l-t-td-left"> 是否滚动显示:</td>
	        <td class="l-t-td-right"> 
	        	<input type="radio" name="flag"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/>
	        </td>
	        <td class="l-t-td-left"> 公告状态:</td>
        	<td class="l-t-td-right"> 
        		<input type="radio" name="pic_status"  ltype="radioGroup" validate="{required:true}" ligerui="{ initValue:2,data: [ { text:'是', id:'1' }, { text:'否', id:'2' } ] }"/>
        	</td>
		</tr>
		<tr> 
		
        	<tr>
			<td class="l-t-td-left"> 上传图片:</td>
        	<td class="l-t-td-right"  colspan="3"> 
        	
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
		
	</form>
	</div>	
	
  	
	</form>
  	</div>
</div>
</body>
</html>
