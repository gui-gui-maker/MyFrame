<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>详情页面</title>
<%@include file="/k/kui-base-form.jsp" %>

<script type="text/javascript" src="/app/fwxm/scientific/instruction/selectUser/selectUnitOrUser.js"></script>
 <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
var pageStatus="${param.status}";
	$(function () {
		$("#formObj").initForm({    //参数设置示例
			toolbar : [ {
   	        	text : '保存',
   	        	id : 'save',
   		        icon : 'save',
   		        click : save
   	          },{
     	        	text : '提交',
       	        	id : 'submit',
       		        icon : 'save',
       		        click : submit
       	          }, {
   	           	text : '关闭',
   		        id : 'close',
   		        icon : 'cancel',
   		        click : close
   	          } ],
			success: function (response) {
				if(response.success){
					top.$.notice("保存成功！",3);
					api.data.window.Qm.refreshGrid();
					api.close();
				}
				else{
					$.ligerDialog.error("操作失败！<br/>" + response.msg);
				}
			},
			getSuccess: function(resp){
				 if(resp.data){
					 var readers = [];
					 if(resp.data.projectCyManId!=null&&resp.data.projectCyManId!=""){
					 var id=resp.data.projectCyManId.split(",");
					 var name=resp.data.projectCyMan.split(",");
                     for(var i=0;i<id.length;i++){
                         readers.push({
                             types : "reader",
                             name: name[i],
                             id: id[i]
                         });
                     } 
                     addReader(readers,false);
					 }
					 if (resp.attachs!= null && resp.attachs!= undefined){
							showAttachFile(resp.attachs);
						}
                 }
			}
		});

		function submit(){
			if($('#formObj').validate().form()){
			var tjUserId="<sec:authentication property='principal.id' htmlEscape='false' />";
			var tjUserName="<sec:authentication property='principal.name' htmlEscape='false' />";
			if(${param.tj!=0}){//审批、审核
				 top.$.dialog({
						width : 400,
						height : 200,
						lock: true,
						parent: null,
						data: {window: window},
						title: "审核结论",
						content: 'url:app/fwxm/scientific/instruction/choose_opinion_rw.jsp',
						cancel: true,
						ok : function() {
							  var data = this.iframe.contentWindow.getSelectResult();
							  if(${param.statuss==6}){
								  selectUnitOrUser1(1,0, null, null, function(datas){
					           			if(datas.code!=null&&datas.code.length>0&&datas.code!="undefined"){
					           				//doSubmit(data.code,data.name,null,null)
					           				doSumbit(data.opinion,data.remark,datas.code,datas.name)
					           			}else{
					           				top.$.dialog.alert("请选择一个用户!");
					           			}
				           			});
							  }else{
								  doSumbit(data.opinion,data.remark,null,null);
							  }
							
						}
				 });
			}else{
	     	   doSumbit(null,null,null,null);
			}
			}
		}

	    function doSumbit(opinion,remark,pshId,pshName){
	    	var formData = $("#formObj").getValues();
	        formData["tjType"]="2";
	        if(${param.tj!=0}){//审核环节
	            formData["remark"]=remark;
	            formData["audit_opinion"]=remark;
	            formData["opinion"]=opinion;
// 	            formData["uploadFiles1"]=uploadFiles;
// 	            formData["back"]=back;
	        }
	        if(pshId!=null){
	            formData["psh_id"]=pshId;
	            formData["psh_man"]=pshName;
	            formData["opinion"]=opinion;
	        }
	       var instruction=$.ligerui.toJSON(formData);
	    $("body").mask("提交中...");
	       $.ajax({
	           url: "com/tjy2/instructionRw/saveBasicTx.do",
	           type: "POST",
	           data:{"instruction":instruction},
	           success : function(data, stats) {
					$("body").unmask();
					console.log(data);
					if (data["success"]) {
						if("${param.tj!=1}"){
							top.$.dialog.notice({
								content : '提交成功'
							});
							api.data.window.Qm.refreshGrid();
							api.close();
						}
					} else {
						$.ligerDialog.error('提示：' + data.msg);
					}
				},
	           error : function(data) {
	               $("body").unmask();
	               $.ligerDialog.error('保存数据失败！');
	           }
	           });
	       
	    }
		
		function save(){
			if($('#formObj').validate().form()){
	    	var formData = $("#formObj").getValues();
	    	console.log(333333333);
	    	console.log(formData);
			$.ajax({
		           url: "com/tjy2/instructionRw/saveBasicTx.do",
		           type: "POST",
		           data:{"instruction":$.ligerui.toJSON(formData)},
		           success : function(data, stats) {
						$("body").unmask();
						if (data["success"]) {
								top.$.dialog.notice({
									content : '提交成功'
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
		           });
			}
		}
		
		function close(){	
		 api.close();
		}		
		// 附件上传
		var receiptUploadConfig = {
    			fileSize : "10mb",	//文件大小限制
    			businessId : "",	//业务ID
    			buttonId : "procufilesBtn",		//上传按钮ID
    			container : "procufilesDIV",	//上传控件容器ID
    			title : "图片、Word、Excel",	//文件选择框提示
    			extName : "doc,docx,xls,xlsx,pptx,pps",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 1,	//限制上传文件数目
    			callback : function(file){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(file);
    			}
    	};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
		
	});
	function selectType(){
		top.$.dialog({
			parent: api,
			width : 700, 
			height : 500, 
			lock : true, 
			title:"选择项目",
			content: 'url:app/fwxm/scientific/instruction/instruction_type_list.jsp',
			data : {"window" : window}
		});
	}
	function selectTypeBack(id,type){
		$("#projectName").val(type);
		$("#tjy2InstructionInfoId").val(id);
	}
	   function selectReaders(){
	        
           var readers = $("#form1").data("readers"); 
           selectUnitOrUser("5",1,"","",function(datas){
               if(!datas.code)return;
               var codeArr = datas.code.split(",");
               var nameArr = datas.name.split(",");
               var readers = [];
               var existReaders = $("#form1").data("readers")||[];
               for(var i in codeArr){
                   var exist = false;
                   for(var j in existReaders){
                       if(existReaders[j].stakeholderId == codeArr[i] && existReaders[j].valid == true)
                           exist=true;
                   }
                   if(exist) continue;
                   readers.push({
                       types : "reader",
                       name: nameArr[i],
                       id: codeArr[i]
                   });
               }
               addReader(readers,true);
           });
       }
      
      var CCnames='';//参与人姓名
      var CCids='';//参与人id
       function addReader(newReaders,isNew){
       var names = '';
       var ids = '';
       var repids='';
       var repNames='';
       //alert(names);
        for(var i in newReaders){
               if(CCids.indexOf(newReaders[i].id)==-1){
                   names = names+newReaders[i].name+",";
                   ids = ids+newReaders[i].id+",";
                   var mtext='<span class="label label-read" id="' + (isNew?newReaders[i].id:newReaders[i].id) + '"><span class="text">' + newReaders[i].name;
                   mtext = mtext+','
                 
                   $("#reader_td").prepend(mtext);
               } else if (newReaders[i].name){
                   repNames = repNames+newReaders[i].name+",";
               }
          }
          //alert(names);
          if(repNames != ""){
                   $.ligerDialog.warn("人员："+"<span style='color:red;'>"+repNames.substring(0,repNames.length-1)+"</span>"+" 已存在！");
          }
          CCnames = CCnames+names;
          CCids = CCids+ids;
          /*if(CCnames!=""){
             CCnames = CCnames.substring(0,CCnames.length-1);
          }
          if(CCids!=""){
             CCids = CCids.substring(0,CCids.length-1);
          }*/
          $("#projectCyMan").val(CCnames);
          $("#projectCyManId").val(CCids);
          //alert($("#clCcr").val());
          //alert($("#clCcid").val());
       
       
       }
       function deleteReader(id,isNew){
           $("#"+id).remove();
           var ids = CCids.split(",");
           var names = CCnames.split(",");
           for(var i in ids){
              if(id==ids[i]){
                  ids[i]="";
                  names[i]="";
              }
           }
           var cid="";
           var cname="";
           for(var i in ids){
              if(ids[i]!=""){
                  cid = cid+ids[i]+',';
                  cname = cname+names[i]+',';
              }
           }
           CCnames = cname;
           CCids = cid;
           $("#projectCyMan").val(CCnames);
          $("#projectCyManId").val(CCids);
       }
       function selectUser() {
	        selectUnitOrUser(1, 1, "projectHeadId", "projectHead");
	    }
       
   	//添加附件
   	function addAttachFile(files){
   		if("${param.status}"=="detail" || ${param.tj==1}){
   			$("#procufilesBtn").hide();
   		}
   		if(files){
   			var attContainerId="procufiles";
   			$.each(files,function(i){
   				var file=files[i];
				var  busId='${param.id}';
   				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
   						//"<div><a href='fileupload/downloadByFilePath.do?path="+encodeURI(file.path)+"&fileName="+encodeURI(file.name)+"'>"+file.name+"</a></div>"+
   						"<div><a href='#' onclick='openFile(\""+file.id+"\",\""+file.name+"\",\""+busId+"\");return false'>"+file.name+"</a></div>"+
   						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
   						"</li>");
   			});
   			getUploadFile();
   		}
   	}
   	
   	// 显示附件
       function showAttachFile(files){
       	if("${param.status}"=="detail" || ${param.tj==1}){
   			$("#procufilesBtn").hide();
   		}
   		if(files){
   			//详情
   			var attContainerId="procufiles";
				var  busId='${param.id}';
   			if("${param.status}"=="detail"|| ${param.tj==1}){
   				$.each(files,function(i){
   					var file=files[i];
   					 //显示附件
   					 

   					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
   											//"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
   											"<div><a href='#' onclick='openFile(\""+file.id+"\",\""+file.fileName+"\",\""+busId+"\");return false'>"+file.fileName+"</a></div>"+
   											"</li>");
//    					$("#"+attContainerId).append("<li id='"+file.id+"' name='"+file.name+"'>"+
//    							"<div><a href='fileupload/downloadByObjId.do?id="+file.id+"'>"+file.name+"</a></div>"+
//    							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadOneFile)'>&nbsp;</div>"+
//    							"</li>");
   					
   				});
   			}
   			//修改
   			else if("${param.status}"=="modify"){
   				$.each(files,function(i){
   					var file=files[i];
   					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
   							//"<div><a href='fileupload/downloadByFilePath.do?path="+encodeURI(file.filePath)+"&fileName="+encodeURI(file.fileName)+"'>"+file.fileName+"</a></div>"+
   							"<div><a href='#' onclick='openFile(\""+file.id+"\",\""+file.fileName+"\",\""+busId+"\");return false'>"+file.fileName+"</a></div>"+
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
    function openFile(fid,fname,busId){
    	 top.$.dialog({
    	        width: $(top).width(),
    	        height: $(top).height(),
    	        title: fname,
    	        lock: false,
    	        reSize: false,
    	        content: "url:app/fwxm/scientific/instruction/fileupload/ntko_editor.jsp",
    	        data: {fid:fid,status:'edit',fname:fname,busId:busId}
    	    });
    }
     	

</script>
<style type="">
.l-t-td-center{text-align: center;
	width: 200px;
}
.l-t-td-input{
padding:5px 15px 5px 0px;border:1px solid ;border-bottom: 1px;
}
</style>
</head>
<body>
<form id="formObj"  getAction="com/tjy2/instructionRw/detailBasic.do?id=${param.id}">
   <input type="hidden" name="id"/>
    <input type="hidden" name="status" />
    <input type="hidden" name="limit_start_date" />
    <input type="hidden" name="limit_end_date" />
    <input name="projectAcceptanceDate" id="projectAcceptanceDate" type="hidden"></input>
             <input name="kj_id" id="kj_id" type="hidden"></input>
             <input name="kj_man" id="kj_man" type="hidden"/>
             <input name="fzr_id" id="fzr_id" type="hidden"></input>
             <input name="fzr_man" id="fzr_man" type="hidden"></input>
             <input name="zr_man" id="zr_man" type="hidden"></input>
            <input name="zr_id" id="zr_id" type="hidden"></input>
            <input name="psh_id" id="psh_id" type="hidden"></input>
            <input name="psh_man" id="psh_man" type="hidden"></input>
    <table  class="l-detail-table" width="80%">
   		<tr>
   			<td colspan="2" class="l-t-td-left" style="text-align: center;font-size: 20px;"><b>四川省特种设备检验研究院科研技术委员会<br />作业指导书制（修订）任务书</b></td>
   		</tr>
   </table>
    <table cellpadding="3"  border="1" cellspacing="0" cellpadding="0" Align="center" width="80%">
    <tr>
            <td class="l-t-td-center">编号：</td>
            <td class="l-t-td-input" >
            	<input name="rwNumber" id="rwNumber" ligerUi="{disabled:true}" type="text" ltype="text" validate="{maxlength:100}" readonly="readonly" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-center">项目名称：</td>
            <td class="l-t-td-input" >
              <input name="tjy2InstructionInfoId" id="tjy2InstructionInfoId" type="hidden"/>
            	<input name="projectName" id="projectName" type="text" ltype="text" validate="{required:false,maxlength:100}" ligerUi="{disabled:true}" />
            </td>
        </tr>
        <tr>
             <td class="l-t-td-center">项目负责人：</td>
            <td class="l-t-td-input" >
            <input name="projectHeadId" id="projectHeadId" type="hidden"></input>
            	<input name="projectHead" id="projectHead"  type="text" ltype="text" validate="{required:false,maxlength:32}" ligerUi="{disabled:true}" />
            </td>
            
        </tr>
        <tr>
          <td class="l-t-td-center">项目参与人员：</td>
            <td class="l-t-td-input"  id="reader_td">
            <input name="projectCyManId" id="projectCyManId" type="hidden"></input>
            <input name="projectCyMan"  id="projectCyMan" type="hidden"  />
<!--            <span class="l-button label" title="添加参与人员"> -->
<!--                                     <span  class="l-a l-icon-add"  onclick="selectReaders();">&nbsp;</span> -->
            </td>
        </tr>
         <tr>
         <td class="l-t-td-center">标准审查人员：</td>
            <td class="l-t-td-input">
            	<input name="reviewMan" id="reviewMan" type="text"  ligerUi="{disabled:true}"  ltype="text" validate="{required:false,maxlength:32}" />
            </td>
         
        </tr> 
        <tr>
        	<td class="l-t-td-center">审核人员：</td>
            <td class="l-t-td-input">
            	<input name="auditMan" id="auditMan" type="text" ltype="text" ligerUi="{disabled:true}" validate="{required:false,maxlength:32}" />
            </td>
        </tr>
          <tr>
         <td class="l-t-td-center">批准人员：</td>
            <td class="l-t-td-input">
            <input name="sign_id" id="sign_id" type="hidden"></input>
            	<input name="sign_man" id="sign_man" type="text" ltype="text" ligerUi="{disabled:true}" validate="{required:false,maxlength:32}" />
            </td>
        </tr> 
        <tr>
            <td class="l-t-td-center">制订/修订：</td>
            <td class="l-t-td-input">
            	<input name="type" id="type" type="text" ltype="text" ligerUi="{disabled:true}" validate="{required:false,maxlength:32}" />
            </td>
        </tr>
        <tr>
          <td class="l-t-td-center">制定内容：</td>
            <td class="l-t-td-input" >
            	<input name="developContent" id="developContent" type="text" ligerUi="{disabled:true}" ltype="text" validate="{required:false,maxlength:1000}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-center">要求：</td>
            <td class="l-t-td-input">
            	<input name="requirements" id="requirements" type="text" ltype="text" ligerUi="{disabled:true}" validate="{required:false,maxlength:1000}" />
            </td>
           
        </tr>
         <tr>
            <td class="l-t-td-center">协调专家支持或购<br/>买服务：</td>
            <td class="l-t-td-input">
            	<input name="commitmentsType" id="commitmentsType" type="text" ligerUi="{disabled:true}" ltype="text" validate="{required:false,maxlength:32}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-center">项目经费预算：</td>
            <td class="l-t-td-input">
            	<input name="budgetMoney" id="budgetMoney" type="text" ltype="text" ligerUi="{disabled:true}" validate="{required:false,maxlength:32}" />
            </td>
        </tr>
        <tr>
        	
            <td class="l-t-td-center">项目开始时间：</td>
            <td class="l-t-td-input">
            	<input name="projectStartDate" id="projectStartDate" type="text" ltype="date" ligerUi="{disabled:true}" validate="{required:false}" />
            </td>
        </tr>
         
        <tr>
            <td class="l-t-td-center">项目结束时间：</td>
            <td class="l-t-td-input">
            	<input name="projectEndDate" id="projectEndDate" type="text" ltype="date" ligerUi="{disabled:true}" validate="{required:false}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-center">项目填报开始日期：</td>
            <td class="l-t-td-input">
            <input id="limit_start_date" name="limit_start_date"   type="text" ltype="date" validate="{required:true}" />
            	
            </td>
        </tr>
        <tr>
        	<td class="l-t-td-center">项目填报结束日期：</td>
            <td class="l-t-td-input">
            	<input name="limit_end_date" id="limit_end_date" type="text" ltype="date" validate="{required:true}" />
            </td>
        </tr>
        <tr>
            <td class="l-t-td-center">填写文件：</td>
            <td class="l-t-td-input">
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
        <tr>
            <td class="l-t-td-center">评审会意见：</td>
            <td class="l-t-td-input">
            <textarea rows="" ligerUi="{disabled:true}" cols="" name="psh_opinion"></textarea>
            </td>
           
        </tr>
       

    </table>
     
</form>
</body>
</html>
