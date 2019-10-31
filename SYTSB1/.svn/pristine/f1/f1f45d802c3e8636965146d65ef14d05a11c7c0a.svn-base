<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
var pageStatus="${param.pageStatus}";
var com_ids = "${param.com_ids}";
	<%
		String curDate = DateUtil.getCurrentDateTime();
	%>
	$(function () {
	    $("#formObj").initForm({    //参数设置示例
	       toolbar:[
	            { text:'保存', id:'save',icon:'save', click:save},
	            { text:'关闭', id:'close',icon:'cancel', click:close}
	        ],
	        getSuccess:function(res){
	        },
	        success: function (response) {//处理成功
				$("body").unmask();
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	            	//top.$.notice("保存成功！");
               		api.close();
                	api.data.window.refreshGrid();
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
    			title : "图片、Word、Excel",	//文件选择框提示
    			extName : "jpg,gif,png,bmp,doc,xls,xlsx",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 1,	//限制上传文件数目
    			callback : function(file){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(file);
    			}
    	};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
	    
	    function save(){
	    	//验证表单数据
			if($('#formObj').validate().form()){
				$("body").mask("正在保存数据，请稍后！");
				//表单提交
    			$("#formObj").submit();
				
				/*
				var ids = "${param.ids}";
		    	var formData = $("#formObj").getValues();
				var data = {};
				data = $.ligerui.toJSON(formData);
				var send_msg_type =$('#send_msg_type').ligerGetRadioGroupManager().getValue();
				
		        $.ajax({
		            url: "maintenance/info/finishs2.do?ids="+ids+"&send_msg_type="+send_msg_type,
		            data: data,	//JSON.stringify(json)把json转化成字符串
		            cache:false,    
		            type: "POST",
		            datatype: "json",
		            contentType: "application/json; charset=utf-8",
		            success: function (data, stats) {
		            	$("body").unmask();
		                if (data["success"]) {
		                	top.$.notice("保存成功！");
		               		api.close();
		                	api.data.window.refreshGrid();
		                } else {
		                	$.ligerDialog.error(data.msg);
		                }
		            },
		            error: function (data) {
		            	$("body").unmask();
		                $.ligerDialog.error(data.msg);
		            }
		        });*/
		    }
	    }

	   	function close(){
	    	api.close();
		}
	   
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
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="maintenance/info/finishs2.do">
		<input type="hidden" name="id" id="id" value="${param.id}" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>完成情况</div>
			</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">开发员：</td>
					<td class="l-t-td-right">
						<input name="develop_user_name" id="develop_user_name" type="text" ltype='text' validate="{required:true,maxlength:20}" value="科鸿-高雅"/>
					</td>
					<td class="l-t-td-left">开始开发日期：</td>
							<td class="l-t-td-right">
								<input name="develop_start_date"
									type="text" ltype="date" validate="{required:true}"
										ligerui="{initValue:'<%=curDate %>',format:'yyyy-MM-dd'}" id="develop_start_date"  />
							</td>	
				</tr>
				<tr>
					<td class="l-t-td-left">完成开发日期：</td>
					<td class="l-t-td-right"><input name="develop_end_date"
						type="text" ltype="date" validate="{required:true}"
						ligerui="{initValue:'<%=curDate%>',format:'yyyy-MM-dd'}"
						id="develop_end_date" />
					</td>
					<td class="l-t-td-left">测试员：</td>
					<td class="l-t-td-right">
						<input name="test_user_name" id="test_user_name" type="text" ltype='text' validate="{required:true,maxlength:20}" value="科鸿-高雅"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">测试日期：</td>
					<td class="l-t-td-right">
						<input name="test_date"
									type="text" ltype="date" validate="{required:true}"
										ligerui="{initValue:'<%=curDate %>',format:'yyyy-MM-dd'}" id="test_date"  />
					</td>
					<td class="l-t-td-left"></td>
					<td class="l-t-td-right"></td>
				</tr>
				<tr>
					<td class="l-t-td-left">完成/更新情况：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							name="develop_desc" id="develop_desc" rows="5" cols="25"
							class="l-textarea" validate="{required:false,maxlength:1000}">已按要求处理完成</textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">填表人：</td>
					<td class="l-t-td-right">
						<input name="write_user_name" id="write_user_name" type="text" ltype='text' validate="{required:true,maxlength:20}" value="科鸿-高雅"/>
					</td>
					<td class="l-t-td-left">填表日期：</td>
					<td class="l-t-td-right"><input name="write_date"
						type="text" ltype="date" validate="{required:true}"
						ligerui="{initValue:'<%=curDate%>',format:'yyyy-MM-dd'}"
						id="write_date" />
					</td>					
				</tr>
				<tr>
					<td class="l-t-td-left">反馈方式：</td>
					<td class="l-t-td-right" colspan="3"><input type="radio"
						name="send_msg_type" id="send_msg_type" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'微信', id:'1' }, { text:'短信', id:'2' }, { text:'微信和短信', id:'3' }, { text:'不反馈', id:'0' } ] }" />
					</td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>更新附加说明</div>
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
