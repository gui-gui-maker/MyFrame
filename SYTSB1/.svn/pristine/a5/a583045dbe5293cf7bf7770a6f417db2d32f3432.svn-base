<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>人员信息</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="app/common/js/idCard.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
	    $("#formObj").initForm({
			success: function (response) {//处理成功
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	          		//保存基本信息（主表）后，id未自动赋值，故此处手动赋值
	          		$("#basic_id").attr("value",response.data.id);
	         		api.data.window.refreshGrid();
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
				/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
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
	
	//添加电子证书
	function addAttachFile2(files){
		if("${param.status}"=="detail"){
			$("#procufilesBtn2").hide();
		}
		if(files){
			var attContainerId="procufiles2";
			$.each(files,function(i){
				var file=files[i];
				/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile2)'>&nbsp;</div>"+
						"</li>"); */
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile2)'>&nbsp;</div>"+
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
				/* 	$("#"+attContainerId).append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
											"</li>"); */
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"</li>");
				});
			}
			//修改
			else if("${param.status}"=="modify"){
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
					/* $("#"+attContainerId).append("<li id='"+file.id+"'>"+
											"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
											"</li>"); */
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"</li>");
				});
			}
			//修改
			else if("${param.status}"=="modify"){
				$.each(files,function(i){
					var file=files[i];
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile2)'>&nbsp;</div>"+
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
	
	function showPositions(val, e, srcObj) {
		selectUnitOrUser(444, 1, null, null, function(data){
			$("#positionNames").val(data.name);
			$("#positionIds").val(data.code);
		});
	}
</script>
</head>
<body>
<div class="navtab">
	<div title="基本信息" tabId="basicTab" style="height: 400px">
	<form name="formObj" id="formObj" method="post" action="employee/basic/saveBasic.do?status=${param.status}"
		getAction="employee/basic/detail.do?id=${param.id}">
		<input type="hidden" name="id" id="basic_id" value="${param.id}"/>
		<!-- 
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>登录帐号</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">登录帐号：</td>
					<td class="l-t-td-right"><input name="account" id="account" type="text" ltype='text' validate="{required:true,maxlength:32}" /></td>									
					<td class="l-t-td-left">登录密码：</td>
					<td class="l-t-td-right"><input name="password" id="password" type="password" ltype='text' validate="{required:true,maxlength:18}" /></td>	
				</tr>
			</table>
		</fieldset>
		 -->
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>人员信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">姓名：</td>
					<td class="l-t-td-right"><input name="name" id="name" type="text" ltype='text' validate="{required:true,maxlength:50}" /></td>									
					<td class="l-t-td-left">性别：</td>
					<td class="l-t-td-right"><u:combo name="gender" code="BASE_SEX" /></td>	
				</tr>
				<tr>	
					<td class="l-t-td-left">身份证号码：</td>
					<td class="l-t-td-right"><input name="idNo" id="idNo" type="text" ltype='text' validate="{required:true,maxlength:18}" onchange="checkID(this);"/></td>								
					<td class="l-t-td-left">出生年月：</td>
					<td class="l-t-td-right"><input name="birthDate" id="birthDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">所属部门：</td>
					<td class="l-t-td-right">
						<input name="org.id" id="org_id" type="hidden" />
						<input name="org.orgName" id="org_name" type="text" ltype='text' onclick="selectorg()"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectorg()}}]}"/>
					</td>	
					<td class="l-t-td-left">岗位：</td>
					<td class="l-t-td-right">
						<input name="positionNames" id="positionNames" type="text" ltype='text' readonly validate="{required:false,maxlength:512}"  
						ligerui="{iconItems:[{icon:'role',click:function(val,e,srcObj){showPositions(val,e,srcObj)}}]}"/>
						<input name="positionIds" id="positionIds" type="hidden"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">员工身份：</td>
					<td class="l-t-td-right">
						<u:combo name="euserType" code="pub_user_type"  validate="required:true"  />
					</td>	
					<td class="l-t-td-left">入职时间：</td>
					<td class="l-t-td-right"><input name="hiredDate" id="hiredDate" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">联系电话：</td>
					<td class="l-t-td-right"><input name="tel" type="text" ltype="text" validate="{maxlength:200}"/></td>
					<td class="l-t-td-left">手机号码：</td>
					<td class="l-t-td-right"><input name="mobileTel" type="text" ltype="text" validate="{maxlength:11}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">住址电话：</td>
					<td class="l-t-td-right"><input name="homeTel" type="text" ltype="text" validate="{maxlength:100}"/></td>
					<td class="l-t-td-left">办公电话：</td>
					<td class="l-t-td-right"><input name="officeTel" type="text" ltype="text" validate="{maxlength:100}"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">现居地址：</td>
					<td class="l-t-td-right" colspan="3"><textarea name="currentPlace" id="currentPlace" rows="2" cols="25" class="l-textarea" validate="{maxlength:200}"></textarea></td>		
				</tr>
				<tr>
					<td class="l-t-td-left">是否为部门负责人：</td>
					<td class="l-t-td-right" colspan="3"><input type="radio" ltype="radioGroup" name="isHead" 
        ligerui="{value:'1',data: [ { text:'是', id:'0' }, { text:'否', id:'1' }] }" /> </td>		
				</tr>
				<!-- 
				<tr>
					<td class="l-t-td-left">民族：</td>
					<td class="l-t-td-right"><u:combo name="nation" code="ba_mz"/></td>		
					<td class="l-t-td-left">学历：</td>
					<td class="l-t-td-right"><u:combo name="degree" code="EDU_013" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">毕业院校：</td>
					<td class="l-t-td-right"><input name="school_name" type="text" ltype="text" validate="{maxlength:126}"/></td>
					<td class="l-t-td-left">毕业时间：</td>
					<td class="l-t-td-right"><input name="graduate_time" id="graduate_time" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" /></td>
				</tr>
				 -->
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>打印机设置</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">报告打印机名称：</td>
					<td class="l-t-td-right"><input name="printer_name" id="printer_name" type="text" ltype="text" validate="{maxlength:100}"/></td>
					<td class="l-t-td-left">标签打印机名称：</td>
					<td class="l-t-td-right"><input name="printer_name_tags" id="printer_name_tags"  type="text" ltype="text" validate="{maxlength:100}"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>电子签名</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">电子签名：</td>
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
	</div>	
	<div title="持证情况" tabId="certTab">
	<form id="certForm" name="certForm" method="post" action="employee/cert/saveCert.do?status=${param.status}">
  	<input type="hidden" name="id" id="cert_id"/>
  	<input type="hidden" name="employee.id" value="${param.id}"/>
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
	       <td class="l-t-td-left">证书类型：</td>
	       <td class="l-t-td-right" ><u:combo name="cert_type" code="BASE_LETTER" validate="required:true"/></td>	
	       <td class="l-t-td-left">证书编号：</td>
	       <td class="l-t-td-right"><input name="cert_no" id="cert_no" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	    </tr>
	    <!-- 
	    <tr>
	       <td class="l-t-td-left">证书类别：</td>
	       <td class="l-t-td-right" ><u:combo name="cert_category" code="BASE_CERT_TYPE" validate="required:true"/></td>	
	       <td class="l-t-td-left">证书代码：</td>
	       <td class="l-t-td-right"><input name="cert_code" id="cert_code" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	    </tr>
	     -->
	    <tr>
	       <td class="l-t-td-left">发证机构：</td>
	       <td class="l-t-td-right" ><input name="cert_issue_org" id="cert_issue_org" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	       <td class="l-t-td-left">证书级别：</td>
	       <td class="l-t-td-right"><u:combo name="cert_level" code="BASE_CERT_LEVEL" validate="required:true" /></td>
	    </tr>
	    <tr>
	    	<td class="l-t-td-left">初次取证日期：</td>
	       	<td class="l-t-td-right"><input name="first_get_date" id="first_get_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	       	<td class="l-t-td-left">发证日期：</td>
	       	<td class="l-t-td-right"><input name="cert_begin_date" id="cert_begin_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	    </tr>
	    <tr>
	       	<td class="l-t-td-left">有效截止日期：</td>
	       	<td class="l-t-td-right"><input name="cert_end_date" id="cert_end_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" validate="{required:true}" /></td>
	    </tr>
	</table> 
  	<script type="text/javascript">
    $("#certForm").initFormList({
    	root:'datalist',
        getAction:"employee/cert/getList.do?id=${param.id}",
        //getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
        actionParam:{"employee.id" : $("#formObj>[name='id']")},	//保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
        delAction:'employee/cert/deleteCerts.do',	//删除数据的action
        delActionParam:{ids:"id"},	//默认为选择行的id 
        columns:[
            //此部分配置同grid
            { display:'持证情况主键', name:'id', width:'1%', hide:true},
			{ display:'人员基本信息主键', name:'employee_id', width:'1%', hide:true},
            { display:'证书类型', name:'cert_type', width:'15%'},
            { display:'证书编号', name:'cert_no', width:'15%'},
            { display:'证书类别', name:'cert_category', width:'15%'},
            { display:'证书代码', name:'cert_code', width:'15%'},
            { display:'发证机构', name:'cert_issue_org', width:'25%'},
            { display:'证书级别', name:'cert_level', width:'15%'},
            { display:'发证日期', name:'cert_begin_date', width:'12%'},
            { display:'有效截止日期', name:'cert_end_date', width:'18%'},
            { display:'授权状态', name:'cert_status', width:'10%', render: function (item) {
                	if("0" == item["cert_status"]){
                		return "未授权";
                	}else if("1" == item["cert_status"]){
                		return "授权";
                	}
            	}
            }
        ]
    });
	</script>
	</form>
  	</div>
</div>
</body>
</html>
