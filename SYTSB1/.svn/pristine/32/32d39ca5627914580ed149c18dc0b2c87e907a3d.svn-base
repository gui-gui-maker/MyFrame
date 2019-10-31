<%@ page contentType="text/html;charset=UTF-8"%>
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
	var gridBg;
	$(function() {
		init();
	    $("#formObj").initForm({
			success: function (response) {
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
						

					//查询报告信息
					$.ajax({
		                url: "contractTaskInfo/getbgxx.do?id="+resp.data.id,
		                type: "POST",
		                success : function(data, stats) {
		                	console.log(data);
		                	var gridDataArr=new Array();
		                	$.each(data.data,function(i,v){
		                		var rowData=new Object();
		                		rowData.bgsbh=v.REPORRT_SN;//
		                		rowData.sbdm=v.DEVICE_REGISTRATION_CODE;
		                		rowData.sbmc=v.DEVICE_NAME;
		                		rowData.sbxh=v.DEVICE_MODEL;
		                		rowData.ssje=v.RECEIVABLE;
		                		rowData.zzcj=v.FEE_STATUS;
		                		rowData.cjry=v.CHECK_OP_NAME;
		                		rowData.xcjyrq=v.LAST_CHECK_TIME;
		                		gridDataArr.push(rowData);
		                	});
		                	if(gridDataArr!=null){
		                		gridBg.loadData({Rows:gridDataArr});
		         			   }
		                	}
		                });
					
						
						if (resp.attachs != null && resp.attachs != undefined){
							showAttachFile(resp.attachs);
						}
				}
	        }, toolbar: null, toolbarPosition: "bottom"
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
							/* $("#procufiles").append("<li id='"+file.id+"'>"+
									"<div><a href='fileupload/downloadByFilePath.do?path="+file.filePath+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
									"</li>"); */
							$("#procufiles").append("<li id='"+file.id+"'>"+
									"<div><a href='fileupload/download.do?id="+file.id+"&fileName="+file.fileName+"'>"+file.fileName+"</a></div>"+
									"</li>");
						});
					}
				}
		  }


	var columnbg;
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
	             height:"200",
	             frozenRownumbers: false,
	             usePager: false,
	             enabledEdit: '${param.pageStatus}'=='detail'?false:true
	 	  	});


		     	  columnbg =[
							{ display: '报告书编号', name: 'bgsbh',type: 'text'},
							{ display: '设备代码', name: 'sbdm',type: 'text'},
							{ display: '设备名称', name: 'sbmc',type: 'text'},
							{ display: '设备型号', name: 'sbxh',type: 'text'},
							{ display: '实收金额', name: 'ssje',type: 'text'},
							{ display: '收费状态', name: 'zzcj',type: 'text'},
							{ display: '参检人员', name: 'cjry',type: 'text'},
							{ display: '下次检验日期', name: 'xcjyrq',type: 'text'}
		 				]
		     	 gridBg = $("#bg_list").ligerGrid({
		             columns:columnbg, 
		             data:{Rows:[]},
		             rownumbers:true,
		             height:"200",
		             frozenRownumbers: false,
		             usePager: false,
		             enabledEdit: false
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
						<input type="text" id="com_name" name="com_name"  readonly="readonly"  ltype="text"  validate="{required:true}" />
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
						<input type="text" id="contract_no" name="contract_no"  readonly="readonly"  ltype="text" />
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
					<td class="l-t-td-right"  colspan="3"><input name="js_money" id="js_money" type="text"  ltype="text"/></td>
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
					<td class="l-t-td-left">参检工具</td>
					<td class="l-t-td-right" >
					<input type="text" id="cjsb_name" name="cjsb_name"  readonly="readonly"  ltype="text"   />
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
		<fieldset class="l-fieldset" >
			<legend class="l-legend">
				<div>报告信息</div>
			</legend>
			<div id="bg_list" ></div>
		</fieldset>
		
	</form>
</body>
</html>
