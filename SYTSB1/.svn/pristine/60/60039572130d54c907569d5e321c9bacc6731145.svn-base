<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %> 
<%@page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%
DateFormat ds = new SimpleDateFormat("yyyyMMdd");
String date = ds.format(new Date());
%>
    <title></title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload1/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">

$(function() {
    /* $("#form1").initForm({
		success: function (response) {//处理成功
    		if (response.success) {
			  
    			$("#procufiles").remove();
            	top.$.dialog.notice({
             		content: "保存成功！"
            	});
            	api.close();
         		//api.data.window.refreshGrid();
    		} else {
           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
      		}
		}
	});		 */
	
	var receiptUploadConfig = {
			fileSize : "10mb",	//文件大小限制
			businessId : "",	//业务ID
			folder:<%=date%>,
			attType : "0",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
			buttonId : "procufilesBtn",		//上传按钮ID
			container : "procufilesDIV",	//上传控件容器ID
			title : "图片",	//文件选择框提示
			extName : "doc,pdf,png,gif,jpg,jpeg",	//文件扩展名限制
			workItem : "",	//页面多点上传文件标识符号
			fileNum : 100,	//限制上传文件数目
			callback : function(file){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
			    $("#uploadName").val(file[0].name);
			    $("#uploadIds").val(file[0].id);
				  $.ajax({
	                    /*  url: "uploadsAction/a/saveUpload.do?uploadPath="+file[0].path+"&uploadId="+file[0].id, */
	                    url: "uploadsAction/a/saveUpload.do",		
	                     type: "POST",
	                     data:"&files="+$.ligerui.toJSON(file),
	                     dataType:'json',
	                     async: false,
	                     success:function (data) {
	                        if(data.success){
	                        	//$("#uploadId").val(data.id)
	                     	addAttachFile(file);
	                    
	                        }else{
	                            $.ligerDialog.warn(data.msg);
	                        }
	                     },
	                     error:function () {
	                         $.ligerDialog.warn("提交失败！");
	                     }
	                 }); 
			}
	};
	var receiptUploader= new KHFileUploader(receiptUploadConfig);
	});
        
   	// 将上传的所有文件id写入隐藏框中
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

	//添加附件
	function addAttachFile(files){

    		if("${param.pageStatus}"=="detail"){
    			$("#procufilesBtn").hide();
    		}
    		if(files){
    			var attContainerId="procufiles";
    			$.each(files,function(i){
    				var file=files[i];
    				
    				 $("#procufiles").append("<li id='"+file.id+"'>"+
    						"<div><a href='#' onclick='editor(\""+file.path+"\",\""+file.name+"\",\""+status+"\");return false'>"+file.name+"</a></div>"+
    						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
    						"</li>"); 
    			});
    			//getUploadFile();
    		}
    	}
	//编辑word文档
	function editor(docId,docName,status){
		var type="";
		var id=$("#uploadId").val()
		var documentDoc=$("#uploadDoc").val()
//		alert(documentDoc)
		var doc="0";
		if(documentDoc!=""&&documentDoc!=null){
			doc="1";
		}
		if(status==""){
			type="modify";
		}else{
			type="add";
		}
		//打开生成报告页面
	 	openContentDoc({
	 		docId : docId,
	 		doc: doc,
			status : "draft",
			id:id,
			type:type,
			window:window,
			title : docName,
			pdf:true,
			tbar : {
				edit : false,
				print : false,
				layout : true
			}
		}); 
		
	}
	
	// 显示附件文件
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
    											"<div><a href='#' onclick='editor(\""+file.uploadPath+"\",\""+file.uploadName+"\",\""+status+"\");return false'>"+file.uploadName+"</a></div>"+
    											"</li>");
    			     });
    			}
    			//修改
    			else if("${param.pageStatus}"=="modify"){
    				$.each(files,function(i){
        				var file=files[i];
    					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
    							"<div><a href='#' onclick='editor(\""+file.uploadPath+"\",\""+file.uploadName+"\",\""+status+"\");return false'>"+file.uploadName+"</a></div>"+
    							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.uploadPath+"\",this,getUploadFile)'>&nbsp;</div>"+
    							"</li>");
    				getUploadFile();
    				});
    			}
    		}
        }
    </script>
</head>

<body>
	<%-- <form id="form1" action="uploadsAction/a/save1.do" getAction="uploadsAction/a/detail1.do?id=${param.id}">
		<input type="hidden" id="uploadId" name="id"/>
		<input type="hidden" id="fileId" name="fileId" value="${param.sn}"/>
		
		
			<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>档案正文和附件上传</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">选择上传的类型</td>
					<td class="l-t-td-right"><input type="radio" name="uploadType"  id="uploadType" ltype="radioGroup"
					ligerui="{value:'附件',data: [ { text:'检验报告', id:'0' }, { text:'原始记录', id:'1' }, { text:'自检报告', id:'2' }] }"/></td>
					<u:combo  name="status"  code="TJY2_DA_UPLOAD" validate="required:true"></u:combo>
					<td class="l-t-td-left">文件：</td>
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
	</form> --%>
	<form name="form1" id="form1" method="post"
				action="uploadsAction/a/save1.do">
				<input type="hidden" name="id" id="uploadId" />
				<input type="hidden" name="uploadId" id="uploadIds" />
				<input type="hidden" name="uploadName" id="uploadName" />
				 <input type="hidden" name="fileId" id="fileId" value="${param.ids}" />
				<table cellpadding="3" cellspacing="0" class="l-detail-table">
			             <tr>
			                 <td class="l-t-td-left">选择上传的类型</td>
			             <td class="l-t-td-right"><input type="radio" name="uploadType"  id="uploadType" ltype="radioGroup"
					ligerui="{value:'附件',data: [ { text:'检验报告', id:'0' }, { text:'原始记录', id:'1' }, { text:'自检报告', id:'2' }] }"/></td>
<%-- 					<u:combo  name="status"  code="TJY2_DA_UPLOAD" validate="required:true"></u:combo> --%>
					<td class="l-t-td-left">文件：</td>
					<td class="l-t-td-right">
						<input name="uploadFiles" type="hidden" id="uploadFiles" validate="{maxlength:1000}" />
						<p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
						</p>
				    	<div class="l-upload-ok-list" id="div">
							<ul id="procufiles"></ul>
						</div>
					</td>	
                  </tr>	
				</table>
			</form>
			<script type="text/javascript">
			
				$(function() {
					$("#form1")
							.initFormList(
									{
										root : 'list',
										getAction : "uploadsAction/a/detailUpload.do?fileId=${param.ids}",
										//getActionParam:{},	//取数据时附带的参数，一般只在需要动态取特定值时用到
										actionParam : {
										}, //保存时会在当前表单上附加此数据，如：{fkId:$("#form1>#id")}会把Form1下的id为id的值带上去,可以是一个对象或选择器
										delAction : 'uploadsAction/a/deleteUpload.do', //删除数据的action
										delActionParam : {
											id:'id',
											uploadId:'uploadId'
										}, //默认为选择行的id 
										columns : [ {
											display : 'empId',
											name : 'fileId',
											width : '1%',
											hide : true
										}, {
											display : 'id',
											name : 'id',
											width : '10%',
											hide : true 
										}, {
											display : 'uploadId',
											name : 'uploadId',
											width : '10%',
											hide : true 
										},  {
											display : '上传类型',
											name : 'uploadType',
											width : '20%'
										},  {
											display : '文件名称',
											name : 'uploadName',
											width : '20%'
										}],
										/*   onSelectRow : function(rowdata, rowindex) {
											  $("#image_upload").attr("src","upload/"+rowdata.uploadPath);
											  $("#procufiles").attr("href","fileupload/downloadByFilePath.do?path="+rowdata.uploadPath+"&fileName="+rowdata.uploadName);
											  $("#form_upload").setValues(rowdata);
											  
												
											}, */
											 success: function (data, stats) {
									                if (data["success"]) {
									                	//grid.updateRow(row, datas);
									                	  $("#procufiles").empty();
									                		top.$.notice("保存成功！");
									             
									                        
									                } else {
									                	$.ligerDialog.error('请先保存个人信息！');
									                }
									            }
									});
				});
			
			</script>
</body>
</html>