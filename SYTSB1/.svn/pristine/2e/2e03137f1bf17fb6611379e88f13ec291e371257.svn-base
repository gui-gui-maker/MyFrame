<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="java.text.DateFormat"%>
<%@ page import="java.util.*" %> 
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
e.getId();
String userId=e.getId();
String uId = SecurityUtil.getSecurityUser().getId();
%>>
<%@page import="java.text.SimpleDateFormat"%>
<%@taglib uri="http://bpm.khnt.com" prefix="bpm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%
DateFormat ds = new SimpleDateFormat("yyyyMMdd");
String date = ds.format(new Date());
%>
<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String nowTime=""; 
				nowTime = dateFormat.format(new Date());%>
<head pageStatus="${param.pageStatus}">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
	<link type="text/css" rel="stylesheet" href="app/qualitymanage/css/form_detail.css" />
	<script type="text/javascript" src="pub/bpm/js/util.js"></script>
	<script type="text/javascript" src="app/common/lodop/LodopFuncs.js"></script>
	<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<!-- 	  <script type="text/javascript" src="app/qualitymanage/js/doc_word.js"></script> -->
	  <script type="text/javascript" src="app/humanresources/js/doc_order.js"></script>
	
	
    <script type="text/javascript">
    var tbar="";
    var jsyijian="${param.jsyijian}";
	var ab=pageStatus="${param.pageStatus}";
    var isChecked="${param.isChecked}";
    var serviceId = "${requestScope.serviceId}";//提交数据的id
	var activityId = "${requestScope.activityId}";//流程id
	var processId = "${requestScope.processId}";//退回idworkType
	//alert("${requestScope.title}");
	var status1="${requestScope.status}";
	var areaFlag;//改变状态
	var are1=null;
 	<bpm:ifPer function="TJY2_ZL_XGSQ_SH" activityId="${requestScope.activityId}">areaFlag="1";</bpm:ifPer>//申请单审核
 	<bpm:ifPer function="TJY2_ZL_XHSQ_CDDTJ" activityId="${requestScope.activityId}">areaFlag="2";</bpm:ifPer>//传递单提交
 	<bpm:ifPer function="TJY2_ZL_XGSQ_PZ" activityId="${requestScope.activityId}">areaFlag="4";</bpm:ifPer>//申请单批准
 	<bpm:ifPer function="TJY2_ZL_XGSQ_CSSH" activityId="${requestScope.activityId}">areaFlag="5";</bpm:ifPer>//传递单审核
 	<bpm:ifPer function="TJY2_ZL_XGSQ_CSSP" activityId="${requestScope.activityId}">areaFlag="3";</bpm:ifPer>//传递单审批
 	<bpm:ifPer function="TJY2_ZL_XGSQ_TJFS" activityId="${param.lcid}">areaFlag="6";</bpm:ifPer>//提交复审
 	<bpm:ifPer function="TJY2_ZL_XGSQ_CSSHFJ" activityId="${requestScope.activityId}">areaFlag="7";</bpm:ifPer>//传递单审批复审
 	<bpm:ifPer function="TJY2_ZL_XGSQ_CSSPFJ" activityId="${requestScope.activityId}">areaFlag="8";</bpm:ifPer>//传递单审批复审

    $(function () {
    	 var status="${param.pageStatus}";
    	if(isChecked!="" && typeof(isChecked)!="undefined"){
        	//$("#form1").transform("detail");
   	    	$("#cdd").transform("detail",function(){});
   	    	$("#cdd").setValues("quality/updateFile1/detail1.do?id=${requestScope.serviceId}");
   	    	$("#cdd1").setValues("quality/updateFile1/detail1.do?id=${requestScope.serviceId}");
   	    	$("#sh").setValues("quality/updateFile1/detail1.do?id=${requestScope.serviceId}");

   	    	if(areaFlag=="5"){
   		    	document.getElementById("cddYj").readOnly=false; 
   	    	}else if(areaFlag=="3"){
   		    	document.getElementById("cddSpyj").readOnly=false; 
   	    	}/*else if(areaFlag=="7"){
   		    	document.getElementById("cddYj2").readOnly=false; 
   	    	}else if(areaFlag=="8"){
   		    	document.getElementById("cddSpyj2").readOnly=false; 
   	    	}*/
				fx();
        	 tbar=[{ text: '退回', id: 'tuihui', icon: 'back', click: tuihui},
        	       { text: '不通过', id: 'submit2', icon: 'forbid', click: nosubmitSh},
        	       { text: '通过', id: 'submit1', icon: 'accept', click: submitSh},
                   { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        }else if(jsyijian!=""){
        	$("#cdd").transform("detail",function(){});
   	    	$("#cdd").setValues("quality/updateFile1/detail1.do?id=${param.id}");
   	    	$("#cdd1").setValues("quality/updateFile1/detail1.do?id=${param.id}");
   	    	$("#sh").setValues("quality/updateFile1/detail1.do?id=${param.id}");
	    	//document.getElementById("cddJsyj").readOnly=false; 
        	tbar=[{ text: '接收', id: 'jsyj', icon: 'save', click: cddjs},
       	       //{ text: '提交复审', id: 'tjfs', icon: 'save', click: cddtjfs},
               { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        } else {
        	//$("#sh").hide();
            tbar=[{ text: '保存并提交', id: 'up1', icon: 'save', click: directChange1 },
                  { text: '保存', id: 'up', icon: 'save', click: directChange },
                { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
        }
    	if ("${param.pageStatus}"=="detail"){
	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];}
    	$("#xgsq2").hide();
    	$("#xgsq1").hide();
    	$("#xgsq").hide();
    	$("#form1").initForm({
            showToolbar: false,
            toolbar: tbar,
            toolbarPosition: "bottom",
            success: function (response) {//处理成功
        		if (response.success) {
                	top.$.dialog.notice({
                 		content: "保存成功！"
                	});
                	api.close();
             		api.data.window.refreshGrid();
        		} else {
               		$.ligerDialog.error('保存失败！<br/>' + response.msg);
          		}
    		}, getSuccess: function (response){
    			var a="<%=user.getName()%>";
    	    	//alert(a);
    	    	$("#projectLeader").val(a);
    	    	var b="<%=user.getDepartment().getOrgName()%>";
    	    	
    	    	$("#department").val(b);
//     	    	var c="方案";
//     	    	$("#fileProperties").val(c);
    			if (response.attachs != null && response.attachs != undefined){
    				showAttachFile(response.attachs);}
       	    	//$("#form1").setValues("quality/updateFile1/detail1.do?id=${param.id}");
       	    	//alert(response.data.status);
    			if(jsyijian!=""){
    				$("#cddJsjb").val(a);
    				$("#cddJsbm").val(b);
    				$("#cddJsdate2").val("<%=nowTime%>");
    				$("#cddJsdate3").val("<%=nowTime%>");
    			}
    		}
    	});
    	var receiptUploadConfig = {
    			fileSize : "100mb",	//文件大小限制
    			folder:<%=date%>,
    			businessId : "",	//业务ID
    			buttonId : "procufilesBtn",		//上传按钮ID
    			container : "procufilesDIV",	//上传控件容器ID
    			title : "文件",	//文件选择框提示
    			extName : "doc,docx,pdf",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 1,	//限制上传文件数目
    			callback : function(files){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(files);
    			}
    	};
    	var receiptUploader= new KHFileUploader(receiptUploadConfig);
    });
    function fx(){
    	var files=null;
    	top.$.ajax({
            url: "quality/updateFile1/detail1.do?id=${requestScope.serviceId}",
            type: "GET",
            dataType:'json',
            async: false,
            success:function (response) {
            	//alert(response.attachs);
            	files=response.attachs;
       			$("#procufilesBtn").hide();
       			var attContainerId="procufiles";
       			$.each(files,function(i){
					var file=files[i];
					var path=file.filePath;
					path=path.replace("\\", ",");
					 //显示附件 target='_blank'
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='app/qualitymanage/file_word.jsp?path="+path+"'>"+file.fileName+"</a></div>"+
						"</li>");
				});
            }
        });
    }
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
//     window.onload = function() { 
//     }
	//添加附件
	function addAttachFile(files){
		status="add";
		if("${param.pageStatus}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			var attContainerId="procufiles";	
			$.each(files,function(i){
				var file=files[i];
				//if(file.id==""){
					
				$("#"+attContainerId).append("<li id='"+file.id+"'>"+
						"<div><a href='#' onclick='editor(\""+file.path+"\",\""+file.name+"\",\""+status+"\");return false'>"+file.name+"</a></div>"+
						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
						"</li>"); 
// 						"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
// 						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
// 						"</li>");
// 				}else{
// 					$.getJSON('fileupload/deleteAtt.do',{"id":file.id,"path":file.path}, function(resp) {
// 				    	if(resp.success){
// 				    		if(this){
// 				    			$(this).parent().remove();
// 				    		}
// 				    		if(getUploadFile){
// 				    			getUploadFile(id,path);
// 				    		}
// 							$("body").unmask();
// 				    	}
// 				    	else{
// 							$("body").unmask();
// 				    		alert("删除失败！");
// 				    	}
// 				    });
// 				}
			});
			getUploadFile();
		}
		are1=files;
		
	}
	// 显示附件文件
    function showAttachFile(files){
    	if("${param.pageStatus}"=="detail"){
			$("#procufilesBtn").hide();
		}
		if(files){
			var attContainerId="procufiles";
			if(jsyijian!=""){//接收详情
				$("#procufilesBtn").hide();
				$.each(files,function(i){
					var file=files[i];
					var path=file.filePath;
					path=path.replace("\\", ",");
					 //显示附件 target='_blank'
					$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='app/qualitymanage/file_word.jsp?path="+path+"'>"+file.fileName+"</a></div>"+
							"</li>");
				});
			}else{
				if("${param.pageStatus}"=="detail"){
					$.each(files,function(i){
						var file=files[i];
						var path=file.filePath;
						var name=file.fileName;
						path=path.replace("\\", ",");
						//name=name.replace("\\", ",");
						//alert(name);
						 //显示附件 target='_blank'
						$("#"+attContainerId).append("<li id='"+file.id+"'>"+
 							"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
	 							"</li>");//"app/archives/archives_word.jsp?path="+path
// 								"<div><a href='#' onclick='editor(\""+file.filePath+"\",\""+file.fileName+"\",\""+status+"\");return false'>"+file.fileName+"</a></div>"+
// 								"<div><a href='app/qualitymanage/file_word.jsp?path="+path+"&name="+file.fileName+"'>"+file.fileName+"</a></div>"+
// 								"</li>");
					});
				}else if("${param.pageStatus}"=="modify"){//修改
					$.each(files,function(i){
						var file=files[i];
						$("#"+attContainerId).append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>");
// 						"<div><a href='#' onclick='editor(\""+file.filePath+"\",\""+file.fileName+"\",\""+status+"\");return false'>"+file.fileName+"</a></div>"+
// 						"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.filePath+"\",this,getUploadFile)'>&nbsp;</div>"+
// 						"</li>");
					});
					getUploadFile();
				}
			}
		}
    }
  //编辑word文档
	function editor(docId,docName,status){
		var type="";
		var id=$("#conId").val();
		var documentDoc=$("#uploadFiles").val();
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
				edit : true
// 				print : true,
// 				layout : true
			}
		}); 
		
	}
	function cddjs(){//接收
		var cddJsyj=$("#cddJsyj").val();
		var id="${param.id}";
		$.ligerDialog.confirm('是否要接收！！！', function (yes){
	           if(!yes){return false;}
			top.$.ajax({
	            url: "quality/updateFile1/savejsyj.do?id="+id,
	            type: "POST",
	            //contentType: "application/json; charset=utf-8",
	            dataType:'json',
	            data:"&cddJsyj="+cddJsyj,
	            async: false,
	            success:function (data) {
	                if (data) {
	                	 top.$.notice('接收成功！！！',3);
	                	 api.data.window.Qm.refreshGrid();
	                     api.close();
	                }else{
	                	 $.ligerDialog.error('请出错了！请重试！!');
	                }
	            },
	        });
		});
	}
	function tuihui(){
		var id = "${requestScope.serviceId}";//提交数据的id
		var processId = "${requestScope.processId}";//退回id
		var cddYj=$("#cddYj").val();
    	var cddSpyj=$("#cddSpyj").val();
		$.ligerDialog.confirm('是否要退回？', function (yes){
	           if(!yes){return false;}
	    	 $("body").mask("正在处理，请稍后！");
	    	 var formData = $("#form1").getValues();
	    	 getServiceFlowConfig("TJY2_ZL_XGSQ1","",function(result,data){
	             if(result){
	                  top.$.ajax({
	                	  url: "quality/updateFile1/Cddreturn.do?id="+id+
	               		 "&typeCode=TJY2_ZL_XGSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
	                      type: "GET",
	                      dataType:'json',
	                      async: false,
	                      success:function (data) {
	                    	  top.$.ajax({
	                              url: "quality/updateFile1/saveyj.do?id="+serviceId+"&areaFlag="+areaFlag,
	                              type: "POST",
	                              //contentType: "application/json; charset=utf-8",
	                              dataType:'json',
	                              data:"&cddYj="+cddYj+"&cddSpyj="+cddSpyj,
	                              async: false,
	                              success:function (data) {
	                                  if (data) {
	                                 	 
	                                  }
	                              },
	                          });
	                    	  top.$.notice('已成功退回！！！',3);
	                    	  api.data.window.Qm.refreshGrid();
				                 api.close();
	                      },
	                  });
	             }else{
	              $.ligerDialog.alert("出错了！请重试！");
	              $("body").unmask();
	             }
	          });
	     });
	}
	function cddtjfs(){//复审
		var a="a";
		var id="${param.id}";
		var cddJsyj=$("#cddJsyj").val();
		var activityId="${param.lcid}";
		if(!cddJsyj){
			$.ligerDialog.error('请填写接受意见！!');
			 return;
		}
		$.ligerDialog.confirm('是否要提交到复审？', function (yes){
	           if(!yes){return false;}
	    	 $("body").mask("正在处理，请稍后！");
	    	 var formData = $("#form1").getValues();
	    	 getServiceFlowConfig("TJY2_ZL_XGSQ1","",function(result,data){
	             if(result){
	                  top.$.ajax({
	                	  url: "quality/updateFile1/zltj.do?id="+id+
	               		 "&typeCode=TJY2_ZL_XGSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag,
	                      type: "GET",
	                      dataType:'json',
	                      async: false,
	                      success:function (data) {
	                    	  if (data) {
	                    		  top.$.ajax({
	                  	            url: "quality/updateFile1/savejsyj.do?id="+id,
	                  	            type: "POST",
	                  	            dataType:'json',
	                  	            data:"&cddJsyj="+cddJsyj+"&a="+a,
	                  	            async: false,
	                  	            success:function (data) {
	                  	                if (data) {
	                  	                	 top.$.notice('已提交到复审！！！',3);
	                  	                	 api.data.window.Qm.refreshGrid();
	                  	                     api.close();
	                  	                }else{
	                  	                	 $.ligerDialog.error('请出错了！请重试！!');
	                  	                }
	                  	            },
	                  	        });
	                    	  }
	                      },
	                  });
	             }else{
	              $.ligerDialog.alert("出错了！请重试！");
	              $("body").unmask();
	             }
	          });
	     });
	}
    function sh() {
		top.$.dialog({
			width: 600,
			height: 350,
			lock: true,
			parent: api,
			data: {
				window: window
			},
			title: "审核",
			content: 'url:app/qualitymanage/update_apply_yijian.jsp?pageStatus=add&serviceId='+serviceId+'&activityId='+activityId+'&processId='+processId
		});
	}
    function nosubmitSh(){
    	var cddYj=$("#cddYj").val();
    	var cddYj2=$("#cddYj2").val();
    	var cddSpyj=$("#cddSpyj").val();
    	var cddSpyj2=$("#cddSpyj2").val();
    	var cddJsyj=$("#cddJsyj").val();
    	$.ligerDialog.confirm('是否要不通过？', function (yes){
           if(!yes){return false;}
    	 $("body").mask("正在处理，请稍后！");
    	 var formData = $("#form1").getValues();
    	 getServiceFlowConfig("TJY2_ZL_XGSQ1","",function(result,data){
             if(result){
                  top.$.ajax({
                	  url: "update/yijina/xgth.do?id="+serviceId+
              		 "&typeCode=TJY2_ZL_XGSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag+"&processId="+processId,
                      type: "GET",
                      dataType:'json',
                      async: false,
                      success:function (data) {
                    	  if (data) {$("body").unmask();
                     	 top.$.ajax({
                              url: "quality/updateFile1/saveyj.do?id="+serviceId+"&areaFlag="+areaFlag,
                              type: "POST",
                              //contentType: "application/json; charset=utf-8",
                              dataType:'json',
                              data:"&cddYj="+cddYj
                              +"&cddYj2="+cddYj2+"&cddSpyj="+cddSpyj+"&cddSpyj2="+cddSpyj2
                              +"&cddJsyj="+cddJsyj,
                              async: false,
                              success:function (data) {
                                  if (data) {
                                 	 
                                  }
                              },
                          });
                     	 
                        	 api.data.window.Qm.refreshGrid();
			                     //api.data.window.api.close();
			                     api.close();
                    	  }
                      },
                  });
             }else{
              $.ligerDialog.alert("出错了！请重试！");
              $("body").unmask();
             }
          });
     });
    }
    function submitSh(){
    	var serviceId = "${requestScope.serviceId}";//提交数据的id
    	var activityId = "${requestScope.activityId}";//流程id
    	var cddYj=$("#cddYj").val();
    	var cddYj2=$("#cddYj2").val();
    	var cddSpyj=$("#cddSpyj").val();
    	var cddSpyj2=$("#cddSpyj2").val();
    	var cddJsyj=$("#cddJsyj").val();
    	$.ligerDialog.confirm('是否提交通过？', function (yes){
            if(!yes){return false;}
             $("body").mask("提交中...");
             getServiceFlowConfig("TJY2_ZL_XGSQ1","",function(result,data){
                    if(result){
                         top.$.ajax({
                             url: "quality/updateFile1/zltj.do?id="+serviceId+
                            		 "&typeCode=TJY2_ZL_XGSQ1&status="+"&activityId="+activityId+"&areaFlag="+areaFlag,
                             type: "GET",
                             dataType:'json',
                             async: false,
                             success:function (data) {
                                 if (data) {$("body").unmask();
                                	 top.$.ajax({
                                         url: "quality/updateFile1/saveyj.do?id="+serviceId+"&areaFlag="+areaFlag,
                                         type: "POST",
                                         //contentType: "application/json; charset=utf-8",
                                         dataType:'json',
                                         data:"&cddYj="+cddYj
                                         +"&cddYj2="+cddYj2+"&cddSpyj="+cddSpyj+"&cddSpyj2="+cddSpyj2
                                         +"&cddJsyj="+cddJsyj,
                                         async: false,
                                         success:function (data) {
                                             if (data) {
                                            	 
                                             }
                                         },
                                     });
                                	 
   	                            	 api.data.window.Qm.refreshGrid();
   	 			                     //api.data.window.api.close();
   	 			                     api.close();
                                 }
                             },
                         });
                    }else{
                     $.ligerDialog.alert("出错了！请重试！");
                     
                     $("body").unmask();
                    }
                });
            });
    	
    }
        function directChange(){ 
        	var obj=$("#form1").validate().form();
    	 if(obj){
  		 	if(are1==null){
    			 $.ligerDialog.warn("请上传文件再保存！");
    		 }else{
    			 $("#status").val("7");
    		 	$("#form1").submit();
    		 }

    	 }else{
    		 return;
    	}      		
    	 api.data.window.refreshGrid();
	}   
        function directChange1(){ 
        	var obj=$("#form1").validate().form();
    	 if(obj){
//     		 if($("#department").val() != "" && $("#department").val() != undefined){
//   	           if($("#departmentId").val() == "" || $("#departmentId").val() == undefined){
//   	               $.ligerDialog.warn("部门id为空，请重新选择部门！");
//   	               return;
//   	           }
//   	         }
//   		 if($("#projectLeader").val() != "" && $("#projectLeader").val() != undefined){
//   	           if($("#projectLeaderId").val() == "" || $("#projectLeaderId").val() == undefined){
//   	               $.ligerDialog.warn("人员id为空，请重新选择人员！");
//   	               return;
//   	           }
//   	         }
//   		 	if(are1==null){
//     			 $.ligerDialog.warn("请上传文件再保存！");
//     		 }else{
        		 var serviceId ="${param.id}";//提交数据的id
        	    	$.ligerDialog.confirm('是否提交审核？', function (yes){
        	        	if(!yes){return false;}
        	         	$("body").mask("提交中...");
        	         	getServiceFlowConfig("TJY2_ZL_XGSQ1","",function(result,data){
        	                if(result){
        	                     top.$.ajax({
        	                         url: "quality/updateFile1/cddtj.do?id="+serviceId+"&typeCode=TJY2_ZL_XGSQ1&status=",
        	                         type: "GET",
        	                         dataType:'json',
        	                         async: false,
        	                         success:function (data) {
        	                             if (data) {
        	                            	 $("#status").val("4");
        	                            	 $("#form1").submit();
        	                            	 $("body").unmask();
        	                            	 top.$.notice('提交成功！！！',3);
        	                                Qm.refreshGrid();//刷新
        	                             }
        	                         },
        	                     });
        	                }else{
        	                	$("body").unmask();
        		                 $.ligerDialog.alert("出错了！请重试！");
        	                }
        	           });
        	      	});
    		 //}

    	 }else{
    		 return;
    	}      		api.data.window.refreshGrid();
	}   
        function chooseOrg(){
            top.$.dialog({
                width: 800,
                height: 450,
                lock: true,
                parent: api,
                title: "选择部门",
                content: 'url:app/common/org_choose.jsp',
                cancel: true,
                ok: function(){
                    var p = this.iframe.contentWindow.getSelectedPerson();
                    if(!p){
                        top.$.notice("请选择部门！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
                        return false;
                    }
                    $("#departmentId").val(p.id);
                    $("#department").val(p.name);
                }
            });
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
                    $("#projectLeaderId").val(p.id);
                    $("#projectLeader").val(p.name);
                    $("#department").val(p.org_name);
                    $("#departmentId").val(p.org_id);
                }
            });
        }
    </script>
</head>
<style>
.l-t-td-right1 {
    padding: 0;
    margin: 0;
}

.l-t-td-right1 .l-text {
    background-image: none;
}

.l-t-td-right1 .l-text-wrapper {
    width: 100%;
    height: 100px;
}

.l-textarea .l-text-wrapper {
    width: 100%;
    height: 100%;
}

.l-textarea-onerow {
    height: 30px;
}

.l-textarea-onerow div {
    padding: 0;
}

.l-t-td-right1 .l-text {
    border: none;
}

.l-t-td-right1 .l-text.change, .l-t-td-right1 .l-radio-group-wrapper.change
    {
    background: url("../images/x-input.png") repeat-x;
}

.l-t-td-right1 .l-text input {
    height: 100px;
    padding-top: 0;
    line-height: 24px;
}

.l-t-td-right1 .l-radio-group-wrapper {
    height: 100px;
    padding-left: 5px;
}

.l-t-td-right1 textarea {
    height: 100%;
}

.l-textarea-onerow textarea {
    height: 12px;
    padding: 6px 5px;
    width: 98%
}

.l-t-td-right1 label {
    height: 100px;
    line-height: 24px;
    display: inline-block;
}

.l-t-td-right1 div.input, .l-td-right div.input {
    border: none;
    padding-left: 5px;
}

.l-t-td-right1 .input-warp div {
    height: 100%;
    line-height: 28px;
}
</style>
<body >
<form id="form1" action="quality/updateFile1/save2.do" getAction="quality/updateFile1/detail1.do?id=${param.id}">
    <input type="hidden" id="id" name="id" value="${param.id}"/>
    
     <input type="hidden" id="qualityXybzFileId" name="qualityXybzFileId"/>
    <h1 id="xgsq2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">文&nbsp;件&nbsp;修&nbsp;改&nbsp;申&nbsp;请&nbsp;表 </h1></br>
    <table id="xgsq1" class="check">
			 <tr>
                    <td width="650px">&nbsp;</td>
                    <td>编号：</td>
                    <td class="l-t-td-right"><input ltype='text' readOnly="true" name="identifier" type="text"/></td>
            </tr>
	</table>
    <table id="xgsq" border="1" cellpadding="3" class="l-detail-table">
        <tr>
            <td class="l-t-td-left">文件名称</td>
            <td class="l-t-td-right"><input name="fileName" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">文件编号</td>
         	<td class="l-t-td-right"><input name="fileId" type="text" validate="{maxlength:32}" /></td>
        </tr>
        <tr>
            <td class="l-t-td-left">需修改的内容</td>
            <td colspan="3" class="l-t-td-right"><textarea name="needsUpdate" ltype="text" style="height:65px"
						class="l-textarea" validate="{maxlength:2000}"></textarea></td>
        </tr>
<!--         <tr> -->
<!--             <td class="l-t-td-left">修改的内容</td> -->
<!--             <td colspan="3" class="l-t-td-right"><textarea name="updateOcntent" ltype="text" validate="{maxlength:2000}"></textarea></td> -->
<!--         </tr> -->
        <tr>
            <td class="l-t-td-left">修改理由</td>
            <td colspan="3" class="l-t-td-right"><textarea name="updateReasons" ltype="text" validate="{maxlength:2000}"></textarea></td>
        </tr>
        
		<tr>
            <td class="l-t-td-left">编制人</td>
            <td class="l-t-td-right"><input name="current_man" type="text" ltype="text" value="<sec:authentication property="principal.name"/>"  ligerUi="{disabled:true}"/></td>
         	<td class="l-t-td-left">日期</td>
        	<td class="l-t-td-right"><input name="current_mandate" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}" /></td>
    	 </tr>
         <tr>
            <td class="l-t-td-left">审核人</td>
            <td class="l-t-td-right"><input name="sh_man" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">日期</td>
        	<td class="l-t-td-right"><input name="sh_mandate" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}" /></td>
        </tr>
         <tr>
            <td class="l-t-td-left">批准人</td>
            <td class="l-t-td-right"><input name="pz_man" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">日期</td>
        	<td class="l-t-td-right"><input name="pz_mandate" type="text" ltype='date' ligerui="{disabled:true,format:'yyyy-MM-dd'}" /></td>
        </tr> 
    </table> <!-- style="height:100px" -->
    
    <h1 id="cdd2" align="center" style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:6mm;">方案/报告/技术/质量管理文件审批传递单 </h1></br>
    <table id="cdd1" class="check">
			 <tr>
                    <td width="650px">&nbsp;</td>
                    <td align="center">编号</td>
                    <td class="l-t-td-right" width="160px"><input ltype='text' readOnly="true" name="identifierC" type="text"/></td>
            </tr>
	</table>
	<table id="cdd" border="1" cellpadding="3" class="l-detail-table">
		<input type="hidden" id="status" name="status"/>
       <input type="hidden" id="departmentId" name="departmentId"/>
       <input type="hidden" id="projectLeaderId" name="projectLeaderId"/>
		<tr>
<!-- 	 fileIdCdd	 projectsName   -->
            <td class="l-t-td-left">项目(文件)名称</td>
            <td class="l-t-td-right" colspan="2"><input id="projectsName" name="projectsName" type="text" ltype="text"  ligerUi="{disabled:true}"/></td>
<!--          	<td class="l-t-td-left">项目编号</td> -->
<!--          	<td class="l-t-td-right"><input name="projectNumber" type="text" ltype="text" validate="{required:true,maxlength:32}" /></td> -->
         	<td class="l-t-td-left">项目(文件)编号</td>
         	<td class="l-t-td-right" colspan="2"><input name="fileIdCdd" type="text" ltype="text" validate="{maxlength:32}"  ligerUi="{disabled:true}"/></td>
        </tr>
		<tr>
            <td class="l-t-td-left">负责部门</td>
            <td class="l-t-td-right" colspan="2"><input ligerui="{disabled:true}" name="department" id="department" type="text" ltype="text" value="<%=user.getDepartment().getOrgName() %>" /></td>
            <td class="l-t-td-left">项目负责人</td>
            <td class="l-t-td-right" colspan="2"><input name="projectLeader" id="projectLeader" type="text" ltype="text"  validate="{required:true}"
            		ligerui="{iconItems:[{icon:'user',click:choosePerson}]}" value="<sec:authentication property="principal.name"/>" /></td>
        </tr>
        <tr>
        	<td class="l-t-td-left">文件性质</td>
			<td class="l-t-td-right" colspan="5">
				<input type="radio" name="fileProperties"  id="fileProperties" ltype="radioGroup"
				ligerui="{value:'1',data:[{text:'方案', id:'1'}, {text:'报告', id:'2'}, {text:'技术文件',id:'3'}, 
				{text:'质量管理文件',id:'4'}, {text:'其它',id:'5'}]}" validate="{required:true}"/>		
			</td>	
		</tr>
<!-- 		<tr>								 -->
<!-- 			<td class="l-t-td-left">检验案例</td> -->
<!-- 			<td class="l-t-td-right" colspan="5"> -->
<!-- 				<input type="radio" name="testCase"  id="testCase" ltype="radioGroup" -->
<!-- 				ligerui="{value:'1',data: [ { text:'有', id:'1' }, { text:'无', id:'2' } ] }"/>		 -->
<!-- 			</td>								 -->
<!-- 		</tr> -->
		<tr>
			<td class="l-t-td-left">修改后的文件</td>
			<td class="l-t-td-right" colspan="5">
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
        	<td class="l-t-td-left">备注</td>
			<td class="l-t-td-right1" colspan="5">
			<textarea name="remarks" id="remarks" rows="7" cols="25" class="l-textarea"  validate="{maxlength:2000}"></textarea>
<!-- 			<input rows="4" id="remarks" name="remarks" type="text" ltype="text" /> -->
			</td>
		</tr>
	</table>

	<table id="sh" border="1" cellpadding="3" class="l-detail-table">
		<tr stlye="width=20px">
			<td  rowspan="2" class="l-t-td-left" >审核记录</td>
			<td class="l-t-td-left" rowspan="">意见</td>
			<td class="l-t-td-right1" colspan="6">
<!-- 			<input readonly="readonly" id="cddYj" name="cddYj" type="text" ltype="text" /> -->
				<textarea readonly="readonly" name="cddYj" id="cddYj" rows="7" cols="25" class="l-textarea"  validate="{maxlength:2000}"></textarea>
			
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left" colspan="2">审核</td>
            <td class="l-t-td-right" colspan="2"><input readonly="readonly" id="cddYjMan" name="cddYjMan" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">日期</td>
         	<td class="l-t-td-right" colspan="2"><input readonly="readonly" name="cddYjDate" type="text" ltype="date" ligerui="{disabled:true}"/></td>
		</tr>
		<tr stlye="width=20px">
			<td  rowspan="2" class="l-t-td-left" >审批记录</td>
			<td class="l-t-td-left" rowspan="">意见</td>
			<td class="l-t-td-right1" colspan="6">
				<textarea readonly="readonly" name="cddSpyj" id="cddSpyj" rows="7" cols="25" class="l-textarea"  validate="{maxlength:2000}"></textarea>
			</td>
		</tr>
		<tr>
            <td class="l-t-td-left" colspan="2">审批</td>
            <td class="l-t-td-right" colspan="2"><input readonly="readonly" id="cddSpyjMan" name="cddSpyjMan" type="text" ltype="text" /></td>
         	<td class="l-t-td-left">日期</td>
         	<td class="l-t-td-right" colspan="2"><input readonly="readonly" name="cddSpyjDate" type="text" ltype="date" ligerui="{disabled:true}"/></td>
        </tr>
		<tr stlye="width=20px">
			<td  rowspan="3" class="l-t-td-left" >接收意见</td>
			<td class="l-t-td-left">接收部门</td>
            <td class="l-t-td-right" colspan="6"><input readonly="readonly" id="cddJsbm" name="cddJsbm" type="text" ltype="text" /></td>
		</tr>
		<tr>
		    <td class="l-t-td-left">经办</td>
            <td class="l-t-td-right" colspan="6"><input readonly="readonly" id="cddJsjb" name="cddJsjb" type="text" ltype="text" /></td>
		</tr>
		<tr>
         	<td class="l-t-td-left">日期</td>
         	<td class="l-t-td-right" colspan="6"><input readonly="readonly" id="cddJsdate3" name="cddJsdate3" type="text" ltype="date" ligerui="{disabled:true}"/></td>
		</tr>
<!-- 		<tr stlye="width=20px"> -->
<!-- 			<td  rowspan="5" class="l-t-td-left" >审核记录</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td class="l-t-td-left" rowspan="2">意见</td> -->
<!-- 			<td class="l-t-td-right" colspan="6"><input readonly="readonly" id="cddYj" name="cddYj" type="text" ltype="text" /></td> -->
			
<!-- 		</tr> -->
<!-- 		<tr> -->
<!--             <td class="l-t-td-left">审核</td> -->
<!--             <td class="l-t-td-right" colspan="2"><input readonly="readonly" id="cddYjMan" name="cddYjMan" type="text" ltype="text" /></td> -->
<!--          	<td class="l-t-td-left">日期</td> -->
<!--          	<td class="l-t-td-right" colspan="2"><input readonly="readonly" name="cddYjDate" type="text" ltype="date" ligerui="{disabled:true}"/></td> -->
<!--         </tr> -->
<!-- 		<tr> -->
<!-- 			<td class="l-t-td-left" rowspan="2">复审意见</td> -->
<!-- 			<td class="l-t-td-right" colspan="6"><input readonly="readonly" id="cddYj2" name="cddYj2" type="text" ltype="text" /></td> -->
			
<!-- 		</tr> -->
<!-- 		<tr> -->
<!--             <td class="l-t-td-left">审核</td> -->
<!--             <td class="l-t-td-right" colspan="2"><input readonly="readonly" id="cddYjMan2" name="cddYjMan2" type="text" ltype="text" /></td> -->
<!--          	<td class="l-t-td-left">日期</td> -->
<!--          	<td class="l-t-td-right" colspan="2"><input readonly="readonly" name="cddYjDate2" type="text" ltype="date" ligerui="{disabled:true}" /></td> -->
<!--         </tr> -->
        
          
<!--         <tr stlye="width=20px"> -->
<!-- 			<td  rowspan="6" class="l-t-td-left" >审批记录</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!--             <td class="l-t-td-left" colspan="2"> 接受日期</td> -->
<!--             <td class="l-t-td-right" colspan="2"><input readonly="readonly" id="cddJsdate" name="cddJsdate" type="text" ltype="date" ligerui="{disabled:true}"/></td> -->
<!--          	<td class="l-t-td-left">返回日期</td> -->
<!--          	<td class="l-t-td-right" colspan="2"><input readonly="readonly" name="cddFhdate" type="text" ltype="date" ligerui="{disabled:true}"/></td> -->
<!--         </tr> -->
<!-- 		<tr> -->
<!-- 			<td class="l-t-td-left" rowspan="2">意见</td> -->
<!-- 			<td class="l-t-td-right" colspan="6"><input readonly="readonly" id="cddSpyj" name="cddSpyj" type="text" ltype="text" /></td> -->
			
<!-- 		</tr> -->
<!-- 		<tr> -->
<!--             <td class="l-t-td-left">审批</td> -->
<!--             <td class="l-t-td-right" colspan="2"><input readonly="readonly" id="cddSpyjMan" name="cddSpyjMan" type="text" ltype="text" /></td> -->
<!--          	<td class="l-t-td-left">日期</td> -->
<!--          	<td class="l-t-td-right" colspan="2"><input readonly="readonly" name="cddSpyjDate" type="text" ltype="date" ligerui="{disabled:true}"/></td> -->
<!--         </tr> -->
<!-- 		<tr> -->
<!-- 			<td class="l-t-td-left" rowspan="2">复审意见</td> -->
<!-- 			<td class="l-t-td-right" colspan="6"><input readonly="readonly" id="cddSpyj2" name="cddSpyj2" type="text" ltype="text" /></td> -->
			
<!-- 		</tr> -->
<!-- 		<tr> -->
<!--             <td class="l-t-td-left">审批</td> -->
<!--             <td class="l-t-td-right" colspan="2"><input readonly="readonly" id="cddSpyjMan2" name="cddSpyjMan2" type="text" ltype="text" /></td> -->
<!--          	<td class="l-t-td-left">日期</td> -->
<!--          	<td class="l-t-td-right"  colspan="2"><input readonly="readonly" name="cddSpyjDate2" type="text" ltype="date" ligerui="{disabled:true}"/></td> -->
<!--         </tr> -->
        
        
<!--          <tr stlye="width=20px"> -->
<!-- 			<td  rowspan="4" class="l-t-td-left" >接收意见</td> -->
<!-- 		</tr> -->
<!-- 		<tr> -->
<!--             <td class="l-t-td-left" colspan="2">接受日期</td> -->
<!--             <td class="l-t-td-right" colspan="2"><input readonly="readonly" id="cddJsdate2" name="cddJsdate2" type="text" ltype="date" ligerui="{disabled:true}"/></td> -->
<!--          	<td class="l-t-td-left">存挡日期</td> -->
<!--          	<td class="l-t-td-right" colspan="2"><input readonly="readonly" name="cddCddate" type="text" ltype="date" ligerui="{disabled:true}"/></td> -->
<!--         </tr> -->
<!-- 		<tr> -->
<!-- 			<td class="l-t-td-right" colspan="7"><input readonly="readonly" id="cddJsyj" name="cddJsyj" type="text" ltype="text" /></td> -->
			
<!-- 		</tr> -->
<!-- 		<tr> -->
<!-- 			<td class="l-t-td-left">接收部门</td> -->
<!--             <td class="l-t-td-right"><input readonly="readonly" id="cddJsbm" name="cddJsbm" type="text" ltype="text" /></td> -->
<!--             <td class="l-t-td-left">经办</td> -->
<!--             <td class="l-t-td-right"><input readonly="readonly" id="cddJsjb" name="cddJsjb" type="text" ltype="text" /></td> -->
<!--          	<td class="l-t-td-left">日期</td> -->
<!--          	<td class="l-t-td-right" colspan="2"><input readonly="readonly" id="cddJsdate3" name="cddJsdate3" type="text" ltype="date" ligerui="{disabled:true}"/></td> -->
<!--         </tr> -->
       
	</table>
</form>
</body>
</html>
