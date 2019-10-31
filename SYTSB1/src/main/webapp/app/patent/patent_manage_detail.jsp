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

   <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
   <script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
  <script type="text/javascript">
  $(function() {
  	$("#certForm").initForm({
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
				alert
				
				if (response.attachs != null && response.attachs != undefined){
					showAttachFile(response.attachs);
				}
			}
  	});		
  	// 附件上传
		var receiptUploadConfig = {
  			fileSize : "100mb",	//文件大小限制
  			businessId : "",	//业务ID
  			buttonId : "procufilesBtn",		//上传按钮ID
  			container : "procufilesDIV",	//上传控件容器ID
  			title : "图片",	//文件选择框提示
  			extName : "jpg,gif,png,bmp,mp4,AVI,wma,rmvb,rm,flash,mid,3GP,doc,pdf,txt,xls,rtf,ppt",	//文件扩展名限制
  			workItem : "",	//页面多点上传文件标识符号
  			fileNum : 100,	//限制上传文件数目
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
	 function choosePerson(flag){
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
                 if(flag=="1"){
                	 $("#patent_man").val(p.id);
                     $("#patentMan").val(p.name); 
                 }else if(flag=="2"){
                	 $("#patent_scend").val(p.id);
                     $("#patentScend").val(p.name); 
                 }else if(flag=="3"){
                	 $("#patent_third").val(p.id);
                     $("#patentThird").val(p.name); 
                 }else if(flag=="4"){
                	 $("#other_man").val(p.id);
                     $("#otherMan").val(p.name); 
                 }
                 
             }
         });
     }
  </script>
</head>
<body>

<form id="certForm" name="certForm" method="post" action="patentAction/saveBasic.do?status=${param.status}" getAction="patentAction/detailPatent.do?id=${param.id}">
  	<input type="hidden" name="id" id="id"/>
  	
  	<input type="hidden" name="data_status" id="data_status" />
	<table cellpadding="3" cellspacing="0" class="l-detail-table"  >
		<tr>
		   <td class="l-t-td-left">专利编号</td>
	       <td class="l-t-td-right"><input name="patent_num" id="patent_num" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	      <td class="l-t-td-left">专利类型</td>
	       <td class="l-t-td-right" ><u:combo  name="patent_type" code="patent_type" validate="required:true"></u:combo></td>
	      
	     
	    </tr>
	    <tr>
	     <td class="l-t-td-left">专利名称</td>
	       <td class="l-t-td-right" colspan="3" ><input name="patent_name" id="patent_name" type="text" ltype='text' validate="{required:true,maxlength:36}" /></td>
	    </tr>
	    <tr>
	       	
	       <td class="l-t-td-left">见刊日期</td>
	       <td class="l-t-td-right" ><input name="invent_date" id="invent_date" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"  /></td>	
	      <td class="l-t-td-left">截至日期</td>
	       <td class="l-t-td-right" ><input name="invent_end" id="invent_end" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}"  /></td>
	    </tr>
	  
	    <tr>
	     <td class="l-t-td-left">第一起草人</td>
	       <td class="l-t-td-right" >
	       <input type="hidden" name="invent_man_id" id="invent_man_id" /> 
	       <input name="invent_man" id="invent_man" type="text" ltype='text' validate="{maxlength:36}" 
	       onclick="selectUnitOrUser(1,1,'invent_man_id','invent_man')"
				 ligerui="{iconItems:[{icon:'user'}]}"
					 /></td>
	    	<td class="l-t-td-left">第二起草人</td>
	       	<td class="l-t-td-right">
	       	 <input type="hidden" name="invent_scend_id" id="invent_scend_id" /> 
	       	 <input name="invent_scend" id="invent_scend" type="text" ltype='text' validate="{maxlength:36}" 
	       onclick="selectUnitOrUser(1,1,'invent_scend_id','invent_scend')"
	    	   ligerui="{iconItems:[{icon:'user'}]}"
					 /></td>
	     
	    </tr>
	    <tr>
	      	<td class="l-t-td-left">第三起草人</td>
	       	<td class="l-t-td-right">
	       	<input type="hidden" name="invent_third_id" id="invent_third_id" /> 
	       	 <input name="invent_third" id="invent_third" type="text" ltype='text' validate="{maxlength:36}" 
	       onclick="selectUnitOrUser(1,1,'invent_third_id','invent_third')"
	    	   ligerui="{iconItems:[{icon:'user'}]}"
					 /></td>
	       	<td class="l-t-td-left">参与起草人</td>
	       	<td class="l-t-td-right">
	       	<input type="hidden" name="other_man_id" id="other_man_id" /> 
	       	 <input name="other_man" id="other_man" type="text" ltype='text' validate="{maxlength:36}" 
	       onclick="selectUnitOrUser(1,1,'other_man_id','other_man')"
	    	   ligerui="{iconItems:[{icon:'user'}]}"
					  /></td>
	    </tr>
	  <tr>
	        <td class="l-t-td-left">专利简介</td>
	       <td class="l-t-td-right" colspan="3" > <textarea name="patent_brief" id="patent_brief" rows="5" cols="25" class="l-textarea"></textarea></td>
	    </tr> 
	  
	      <tr>
					<td class="l-t-td-left">附件：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="uploadFiles" type="hidden" id="uploadFiles" validate="{maxlength:1000}" />
						<p id="procufilesDIV">
						 <c:if test='${param.pageStatus!="detail" }'>
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
							</c:if>
						</p>
				    	<div class="l-upload-ok-list">
							<ul id="procufiles"></ul>
						</div>
					</td>
				</tr>
				
	</table> 
	</form>
</body>
</html>