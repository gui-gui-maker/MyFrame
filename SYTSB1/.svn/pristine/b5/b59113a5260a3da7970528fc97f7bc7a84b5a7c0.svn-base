<%@page import="java.text.DateFormat"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.*" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html;charset=UTF-8"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<%
DateFormat ds = new SimpleDateFormat("yyyyMMdd");
String date = ds.format(new Date());
%>
    <title></title>
<%@ include file="/k/kui-base-form.jsp"%>
	 <link type="text/css" rel="stylesheet" href="app/finance/css/form_detail.css" />
<script type="text/javascript" src="pub/fileupload1/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript" src="app/archives/js/doc_order.js"></script>
<script type="text/javascript">
var status="${param.pageStatus}";
var permission="${param.permission}";
var serviceId = "${requestScope.Id}";
var tbar="";	
	$(function () {
		var e=document.getElementById("j").getAttribute("title");
     tbar=[{ text: '保存', id: 'up', icon: 'save', click: directChange},
         { text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
     if ("${param.pageStatus}"=="detail")
	        tbar=[{ text: '关闭', id: 'close', icon:'cancel', click:function(){api.close();}}];
    
     $("#form1").initForm({
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
			$.ajax({
				url : 'uploadsAction/a/detail1.do?id='+"${param.id}",
				type : "POST",
				success : function(data, stats) {
					if (data.attachs != null && data.attachs != undefined)
					showAttachFile(data.attachs);
//					alert(data.attachs);
				},
				error : function(data) {
					$.ligerDialog.error('获取失败！');
				}
			});
			
		}, toolbar: tbar,
		 toolbarPosition: "bottom"
	});
     $("#form2").initForm({
    	 getSuccess:function (res){
    		 showAttachFile(res.list)
    	 }
     });
     $('#checker').autocomplete("employee/basic/searchEmployee.do", {
         max: 12,    //列表里的条目数
         minChars: 1,    //自动完成激活之前填入的最小字符
         width: 200,     //提示的宽度，溢出隐藏
         scrollHeight: 300,   //提示的高度，溢出显示滚动条
         scroll: false,   //当结果集大于默认高度时是否使用卷轴显示
         matchContains: true,    //包含匹配，就是data参数里的数据，是否只要包含文本框里的数据就显示
         autoFill: false,    //自动填充
         formatItem: function(row, i, max) {
             return row.name + '   ' + row.mobileTel;
         },
         formatMatch: function(row, i, max) {
             return row.name + row.mobileTel;
         },
         formatResult: function(row) {
             return row.name;
         }
     }).result(function(event, row, formatted) {
         //alert(row.id);
         $("#checkerId").val(row.id);
     
     }); 
     //上传文件
     var receiptUploadConfig = {
    			fileSize : "10mb",	//文件大小限制
    			businessId : "",	//业务ID
    			folder: <%=date%>,
    			buttonId : "procufilesBtn",		//上传按钮ID
    			container : "procufilesDIV",	//上传控件容器ID
    			attType : "0",//文件存储类型；1:数据库，0:磁盘，默认为磁盘
    			title : "图片",	//文件选择框提示
    			extName : "doc,pdf",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 1,	//限制上传文件数目
    			callback : function(file){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				  $.ajax({
   	                     url: "uploadsAction/a/saveUpload.do?uploadPath="+file[0].path+"&uploadId="+file[0].id,
   	                     type: "POST",
   	                     data:"&uploadName="+file[0].name,
   	                     dataType:'json',
   	                     async: false,
   	                     success:function (data) {
   	                        if(data.success){
   	                        	$("#uploadId").val(data.id)
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
	 function directChange(){ 
	     var obj=$("#form1").validate().form();
	 	 if(obj){
	 		if($("#checker").val() != "" && $("#checker").val() != undefined){
		           if($("#checkerId").val() == "" || $("#checkerId").val() == undefined){
		               $.ligerDialog.warn("人员id为空，请重新选择人员！");
		               return;
		           }
		         }
	 		 $("#form1").submit();
	 		var serviceId = "${requestScope.Id}";
			//alert(serviceId);
	 	 }else{
	 		 return;
	 	}}     
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
	                $("#checkerId").val(p.id);
	                $("#checker").val(p.name);
	            }
	        });
	    }
	    var status="";
	  //添加合同附件
    	function addAttachFile(files){
		  var id=$("#uploadId").val();
    	  status="add";
    		if("${param.pageStatus}"=="detail"){
    			$("#procufilesBtn").hide();
    		}
    		if(files){
    			var attContainerId="procufiles";
    			$.each(files,function(i){
    				var file=files[i];
    				
    				 $("#procufiles").append("<li id='"+file.id+"'>"+
    						"<div><a href='#' onclick='editor(\""+file.path+"\",\""+file.name+"\",\""+status+"\");return false'>"+file.name+"</a></div>"+
    						"<div class='l-icon-close progress'  onclick='deleteUpload1(\""+file.path+"\",\""+file.id+"\",\""+id+"\")'>&nbsp;</div>"+
    						"</li>"); 
    			});
    			getUploadFile();
    		}
    	}
    	// 将上传的合同附件id写入隐藏框中
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
    	//编辑word文档
    	function editor(docId,docName,status){
    		var type="";
    		var id=$("#uploadId").val()
    		var documentDoc=$("#uploadDoc").val()
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
    	// 显示合同
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
    							"<div class='l-icon-close progress' onclick='deleteUpload1(\""+file.uploadPath+"\",\""+file.uploadId+"\",\""+file.id+"\")'>&nbsp;</div>"+
    							"</li>");
    				getUploadFile();
    				});
    			}
    		}
        }
        function deleteUpload1(uploadPath,uploadId,id){
        	 $.ajax({
                    url: "uploadsAction/a/deleteUpload.do?uploadPath="+uploadPath+"&uploadId="+uploadId+"&id="+id,
                    type: "POST",
                    dataType:'json',
                    async: false,
                    success:function (data) {
                       if(data.success){
                    	   $("#procufiles").parent().remove();
                    	   $.ligerDialog.warn("删除成功！");
                   
                       }else{
                           $.ligerDialog.warn(data.msg);
                       }
                    },
                    error:function () {
                        $.ligerDialog.warn("提交失败！");
                    }
                }); 
        }
    </script>
</head>

<body>
<div class="navtab">
<div title="档案基本信息" id="j" >
	<form id="form1" action="archives/file/save.do" getAction="archives/file/detail.do?id=${param.id}">
		<input type="hidden" id="id" name="id"/>
		<input type="hidden" id="status" name="status"/>
		<input type="hidden" id="checkerId" name="checkerId"/>
<!-- 			<table id="xgsq1" class="check"> -->
<!-- 				 <tr> -->
<!-- 	                    <td width="660px">&nbsp;</td> -->
<!-- 	                    <td >报告编号：</td> -->
<!-- 	                    <td class="l-t-td-right"><input ltype='text' readOnly="true" name="reportNumber" type="text"/></td> -->
<!-- 	            </tr> -->
<!-- 			</table> -->
			<table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table" id="">
					
				<tr>
					<td class="l-t-td-left">证书编号</td>
					<td class="l-t-td-right"><input name="certificateNumber" type="text" ltype="text" /></td>
					<td class="l-t-td-left">产品名称</td>
					<td class="l-t-td-right"><input name="productName" type="text" ltype="text"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">出厂编号</td>
					<td class="l-t-td-right"><input name="serialNumber" type="text" ltype="text"/></td>
					<td class="l-t-td-left">产品规格</td>
					<td class="l-t-td-right"><input name="productSpecifications" type="text" ltype="text"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">产品编号</td>
					<td class="l-t-td-right"><input name="productNumber" type="text" ltype="text"/></td>
					<td class="l-t-td-left">使用单位</td>
					<td class="l-t-td-right"><input name="useUnit" type="text" ltype="text"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">制造单位</td>
					<td class="l-t-td-right"><input name="manufactureUnit" type="text" ltype="text"/></td>
					<td class="l-t-td-left">安装单位</td>
					<td class="l-t-td-right"><input name="installUnit" type="text" ltype="text"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">检验员</td>
					<td class="l-t-td-right"><input id="checker" name="checker" type="text" ltype="text" ligerui="{iconItems:[{icon:'user',click:choosePerson}]}"/></td>
					<td class="l-t-td-left">发证日期</td>
					 	<%SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
						String nowTime=""; 
						nowTime = dateFormat.format(new Date());%>
		       		<td class="l-t-td-right"><input name="issuingTime" type="text" ltype='date' ligerui="{format:'yyyy-MM-dd'}" value="<%=nowTime%>" /></td>
				</tr>
			</table>
	</form>
	</div>
	<div title="上传附件" id="f" >
		<form name="form2" id="form2" method="post" action="uploadsAction/a/save1.do" getAction="uploadsAction/a/detailUpload.do?empId=${param.id}">
				<input type="hidden" name="id" id="uploadId" />
				 <input type="hidden" name="fileId" id="fileId" value="${param.id}" />
				 <input type="hidden" name="uploadName" id="uploadName" />
				 <input type="hidden" name="uploadmanName" id="uploadmanName" />
				<table cellpadding="3" cellspacing="0" class="l-detail-table">
			     <tr>
			           <td class="l-t-td-left">上传的类型</td>
		               <td class="l-t-td-right"><u:combo  name="uploadType"  code="TJY2_DA_UPLOAD" validate="required:true"></u:combo></td>
					   <td class="l-t-td-right" >
						      <input name="uploadDoc" type="hidden"  id="uploadDoc" validate="{maxlength:1000}" />
						      <p id="procufilesDIV">
							<a class="l-button" id="procufilesBtn">
								<span class="l-button-main"><span class="l-button-text">选择文件</span></span>
							</a>
						     </p>
				    	
					   </td>
                  </tr>	
				</table>
				<table>
					   <tr>
                  		<div class="l-upload-ok-list">
							<ul id="procufiles"></ul>
						</div>
					   
					   </tr>
				
				</table>
			</form>
			<script type="text/javascript">
			
			
			</script>
	</div>
</div>
	
</body>
</html>