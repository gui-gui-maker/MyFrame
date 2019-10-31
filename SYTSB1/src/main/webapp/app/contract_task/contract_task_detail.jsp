﻿<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>添加合同检验任务单</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
   <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
	var grid;
	$(function() {
		init();
	    $("#formObj").initForm({
			success: function (response) {//处理成功
				$("body").unmask();
	    		if (response.success) {
	            	top.$.dialog.notice({
	             		content: "保存成功！"
	            	});
	         		api.data.window.refreshGrid();
	            	api.close();	            	      	
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess:function(resp){
	        	if(resp.success){
					$("#formObj").setValues(resp.data);
					$.ajax({
		                url: "contractTaskInfo/getCjsb.do?ids="+resp.data.cjsb_id,
		                type: "POST",
		                success : function(data, stats) {
		                	var gridDataArr=new Array();
		                	$.each(data.data,function(i,v){
		                		var rowData=new Object();
		                		rowData.daflh=v.EQ_ARCHIVE_CLASS_ID;//
		                		rowData.sbmc=v.EQ_NAME;
		                		rowData.sbbh=v.EQ_NO;
		                		rowData.ggxh=v.EQ_MODEL;
		                		rowData.ccbh=v.EQ_SN;
		                		rowData.zzcj=v.EQ_MANUFACTURER;
		                		rowData.glsyry=v.EQ_USER;
		                		gridDataArr.push(rowData);
		                	});
		                	if(gridDataArr!=null){
		         			   grid.loadData({Rows:gridDataArr});
		         			   }
		                	if(${param.status=='detail'}){
		                		$("#cjsb_name").html("您已选择"+data.data.length+"条设备");
		                	}else{
		                		$("#cjsb_name").val("您已选择"+data.data.length+"条设备");
		                	}
				            
		                	}
		                });

						if (resp.attachs != null && resp.attachs != undefined){
							showAttachFile(resp.attachs);
						}
				}
	        }, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		var contract_no=$("#contract_no").val();
				    		var procufiles=$("#procufiles li").length;
				    		if(procufiles==0 && contract_no==""){
				    			confirm("合同协议不存，请上传佐证附件！");
				    		}else{
	 				    		if(confirm("确定保存？")){
				    			$("body").mask("正在保存数据，请稍后！");
				    			//表单提交
				    			$("#formObj").submit();
					    		}
				    		}

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
				$.each(files,function(i){
					var file=files[i];
				/* 	$("#procufiles").append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/downloadByFilePath.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\""+file.path+"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>"); */
							
					$("#procufiles").append("<li id='"+file.id+"'>"+
							"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
							"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile)'>&nbsp;</div>"+
							"</li>");
				});
				getUploadFile();
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

			// 显示附件
		  function showAttachFile(files){
		  	if("${param.status}"=="detail"){
					$("#procufilesBtn").hide();
					$("#procufilesDIV").hide();
				}
				if(files){
					//详情
					if("${param.status}"=="detail"){
						$.each(files,function(i){
							var file=files[i];
							 //显示附件
						/* 	$("#procufiles").append("<li id='"+file.id+"'>"+
									"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
									"</li>"); */
									
							$("#procufiles").append("<li id='"+file.id+"'>"+
									"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
									"</li>");
						});
					}
					//修改
					else if("${param.status}"=="modify"){
						$.each(files,function(i){
							var file=files[i];
							$("#procufiles").append("<li id='"+file.id+"'>"+
									"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
									"<div class='l-icon-close progress' onclick='deleteUploadFile(\""+file.id+"\",\"\",this,getUploadFile)'>&nbsp;</div>"+
									"</li>");
						});
						getUploadFile();
					}
				}
		  }
	function selectCom(){
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择报检单位",
// 			content: 'url:app/enter/enter_open_list.jsp',
			content:'url:app/fwxm/contract/custom_open_list.jsp',
			data : {"parentWindow" : window}
		});
	}

	function callBack(id, name){
		$('#com_id').val(id);
		$('#com_name').val(name);			
	}
	
	function selectContract(){	
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择合同",
			content: 'url:app/contract_task/contract_list.jsp',
			data : {"parentWindow" : window}
		});
	}

	function callBackContract(id, contract_no,xmmc,dwmc,dw_id){	
		$('#xmmc').val(xmmc);
		$('#com_name').val(dwmc);
		$('#com_id').val(dw_id);
		$('#contract_id').val(id);
		$('#contract_no').val(contract_no);			
	}
	function selectSb(){
	    top.$.dialog({
	        lock: true,
	        parent: api,
			width : 800, 
			height : 550,
	        title: "选择参检设备",
	        content: 'url:app/contract_task/contract_task_cjsb_list.jsp',
	        cancel: true,
	        ok: function(){
	            var p = this.iframe.contentWindow.getSelectedPerson();
	            if(p.count==0){
	                top.$.notice("请选参检设备！", 3, "k/kui/images/icons/dialog/32X32/hits.png");
	                return false;
	            }
	            $("#cjsb_id").val(p.id);
	            $("#cjsb_name").val("您已选择"+p.count+"条设备");
// 	            $("#cjsb_list")
				$.ajax({
                url: "contractTaskInfo/getCjsb.do?ids="+p.id,
                type: "POST",
                data:{"ids":p.id},
                success : function(data, stats) {
                	var gridDataArr=new Array();
                	$.each(data.data,function(i,v){
                		var rowData=new Object();
                		rowData.daflh=v.EQ_ARCHIVE_CLASS_ID;//
                		rowData.sbmc=v.EQ_NAME;
                		rowData.sbbh=v.EQ_NO;
                		rowData.ggxh=v.EQ_MODEL;
                		rowData.ccbh=v.EQ_SN;
                		rowData.zzcj=v.EQ_MANUFACTURER;
                		rowData.glsyry=v.EQ_USER;
                		gridDataArr.push(rowData);
                	});
                	if(gridDataArr!=null){
         			   grid.loadData({Rows:gridDataArr});
         			   }
                	}
                })
				
	        }
	    }).max();
		
	}
	
	 function init(){
	     	   column =[
						{ display: '档案分类号', name: 'daflh',type: 'text'},
						{ display: '设备名称', name: 'sbmc',type: 'text'},
						{ display: '设备编号', name: 'sbbh',type: 'text'},
						{ display: '规格型号', name: 'ggxh',type: 'text'},
						{ display: '出厂编号', name: 'ccbh',type: 'text'},
						{ display: '制造厂家', name: 'zzcj',type: 'text'},
						{ display: '管理（使用）人员', name: 'glsyry',type: 'text'}
	 				]
	    		 grid = $("#cjsb_list").ligerGrid({
	             columns:column, 
	             data:{Rows:[]},
	             rownumbers:true,
	             frozenRownumbers: false,
	             usePager: false,
	             enabledEdit: '${param.pageStatus}'=='detail'?false:true,
			     height:'90%'
	 	  	});
	     	  
	    }
	 
	 function emptyCjsb(){
		 grid.loadData({Rows:null});
		 $("#cjsb_id").val("");
		 $("#cjsb_name").val("");
		 }
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="contractTaskInfo/saveBasic.do?status=${param.status}"
		getAction="contractTaskInfo/getDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="id" value="${param.id}"/>
		<c:if test="${param.status eq 'modify'}">
		<input type="hidden" name="sn" id="sn"/>
		<input type="hidden" name="create_user_id" id="create_user_id"/>
		<input type="hidden" name="create_user_name" id="create_user_name"/>
		<input type="hidden" name="create_date" id="create_date"/>
		<input type="hidden" name="data_status" id="data_status"/>
		</c:if>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">	
			<tr>
				<td class="l-t-td-left"></td>
				<td class="l-t-td-right"></td>
				<td class="l-t-td-left"></td>
				<td class="l-t-td-right"></td>
				<td class="l-t-td-left"></td>
				<td class="l-t-td-right"></td>
				<td class="l-t-td-left"></td>
				<td class="l-t-td-right"></td>
			</tr>
				<c:if test="${param.status eq 'detail'}">
					<tr>
						<td class="l-t-td-left">任务单编号：</td>
						<td class="l-t-td-right"  colspan="3"><input type="text" name="sn" id="sn" ltype="text"/></td>
						<td class="l-t-td-left">&nbsp;&nbsp;</td>
						<td class="l-t-td-right">&nbsp;&nbsp;</td>
						<td class="l-t-td-left">&nbsp;&nbsp;</td>
						<td class="l-t-td-right">&nbsp;&nbsp;</td>
					</tr>
				</c:if>
				<tr>
					<td class="l-t-td-left">报检单位名称：</td>
					<td class="l-t-td-right"  colspan="3">
						<input type="hidden" name="com_id" id="com_id" />
						<input type="text" id="com_name" name="com_name"  readonly="readonly"  ltype="text"  validate="{required:true}" onclick="selectCom()"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectCom()}}]}"/>
					</td>
					<td class="l-t-td-left">报检时间：</td>
					<td class="l-t-td-right" colspan="3"><input name="inspection_date" type="text" ltype="date" validate="{required:true}"
								ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="inspection_date" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">检验性质：</td>
					<td class="l-t-td-right"  colspan="3">
						<u:combo name="check_type" code="BASE_CHECK_TYPE" validate="required:true"  attribute="disabled:false" />
					</td>
					<td class="l-t-td-left">是否含税：</td>
					<td class="l-t-td-right" >
						<input name="task_money_s" id="task_money_s" type="text" ltype="select" validate="{required:false,maxlength:500}" ligerui="{initValue:'',data:[{id:'1',text:'是'},{id:'2',text:'否'}]}"/>
					</td>
					<td class="l-t-td-left">金额（万元）：</td>
					<td class="l-t-td-right" >
					<input  name="task_money"  id="task_money" type="text" ltype='float' ligerui="{maxlength:18}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">合同协议：</td>
					<td class="l-t-td-right"  colspan="3">
						<input type="hidden" name="contract_id" id="contract_id" />
						<input type="text" id="contract_no" name="contract_no"  readonly="readonly"  ltype="text"  onclick="selectContract()"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectContract()}}]}"/>
					</td>
					<td class="l-t-td-left">拟承办部门：</td>
					<td class="l-t-td-right" colspan="3">
						<input name="org_name" id="org_name" type="text" readonly="readonly" title="点击此处选择业务部门" ltype='text' validate="{required:true}" onclick="selectUnitOrUser(0,0,'org_id','org_name')"value="" />
						<input type="hidden" name="org_id" id="org_id" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">项目名称：</td>
					<td class="l-t-td-right"  colspan="7"><input name="xmmc" id="xmmc" type="text" ltype="text" /></td>
				</tr>
				<tr>
					<td class="l-t-td-left">超期时间：</td>
					<td class="l-t-td-right"  colspan="3"><input name="cqsj" id="cqsj" type="text" ltype="date" readonly="readonly"/></td>
					<td class="l-t-td-left">结算金额（万元）:</td>
					<td class="l-t-td-right"  colspan="3"><input name="js_money" id="js_money" type="text" ltype="text"/></td>
				</tr>
				<tr>
					<td class="l-t-td-left">佐证附件：</td>
					<td class="l-t-td-right" colspan="3">
					<input name="uploadFiles" type="hidden" id="uploadFiles" validate="{maxlength:1000}" />
						<p id="procufilesDIV">
						 <c:if test='${param.status!="detail" }'>
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
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="7"><input name="remarks" id="remarks" type="text" ltype="text"/></td>
				</tr>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>设备资料</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">			
				<tr>					
					<td class="l-t-td-left">设备类型：</td>
					<td class="l-t-td-right">
						<input name="sblx" id="sblx" validate="{required:true}" ltype='select' 
       		ligerui="{onSelected:emptyCjsb,isMultiSelect:true,
       		data:<u:dict code='SBLX_JYRWD'/>}"/>
					</td>
					<td class="l-t-td-left">参检工具：</td>
					<td class="l-t-td-right" >
					<input type="text" id="cjsb_name" name="cjsb_name"  readonly="readonly"  ltype="text"   onclick="selectSb()"
							ligerui="{readonly:true,iconItems:[{icon:'add',click:selectSb}]}"/>
					<input type="hidden" ltype="hidden"  name="cjsb_id" id="cjsb_id" />
					</td>
					
				</tr>
				<tr>	
					<td class="l-t-td-left">设备数量：</td>
					<td class="l-t-td-right">
						<input name="sbsl" id="sbsl" type="text" ltype='text'  />
					</td>
					<td class="l-t-td-left">参检部门： </td>
					<td class="l-t-td-right"> 
					 <input type="hidden" name="cjry_id" id="cjry_id" />
					<input type="text" name="cjry_name" id="cjry_name" ltype="text"   validate="{required:false}" onclick="selectUnitOrUser(0,1,'cjry_id','cjry_name')" />
					</td>
				</tr>
			</table>
		</fieldset>	
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>选中的参检设备</div>
			</legend>
			<div id="cjsb_list"></div>
		</fieldset>	
		<c:if test="${param.status eq 'detail'}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>操作日志</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>					
					<td class="l-t-td-left">创建人：</td>
					<td class="l-t-td-right">
						<input type="text" id="create_user_name" name="create_user_name" ltype="text" />
					</td>
					<td class="l-t-td-left">创建日期：</td>
					<td class="l-t-td-right">
						<input name="create_date" id="create_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>
				<tr>					
					<td class="l-t-td-left">修改人：</td>
					<td class="l-t-td-right">
						<input type="text" id="mdy_user_name" name="mdy_user_name" ltype="text" />
					</td>
					<td class="l-t-td-left">修改日期：</td>
					<td class="l-t-td-right">
						<input name="mdy_date" id="mdy_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>
			</table>
		</fieldset>
		</c:if>
	</form>
</body>
</html>