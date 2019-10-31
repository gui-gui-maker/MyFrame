<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>添加系统台账</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
	$(function() {
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
					if (resp.attachs != null && resp.attachs != undefined){
						showAttachFile(resp.attachs);
					}
				}
	        }, toolbar: [
	      		{
	      			text: "保存", icon: "save", click: function(){
	      				//表单验证
				    	if ($("#formObj").validate().form()) {
				    		if(confirm("确定保存？")){
				    			$("body").mask("正在保存数据，请稍后！");
				    			//表单提交
				    			$("#formObj").submit();
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
    			fileSize : "10mb",	//文件大小限制
    			businessId : "",	//业务ID
    			buttonId : "procufilesBtn",		//上传按钮ID
    			container : "procufilesDIV",	//上传控件容器ID
    			title : "图片、Word、Excel、压缩文件",	//文件选择框提示
    			extName : "*",	//文件扩展名限制
    			workItem : "",	//页面多点上传文件标识符号
    			fileNum : 1,	//限制上传文件数目
    			callback : function(file){	//上传成功后回调函数,实现页面文件显示，交与业务自行后续处理
    				addAttachFile(file);
    			}
    	};
		var receiptUploader= new KHFileUploader(receiptUploadConfig);
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
						"<div><a href='fileupload/downloadByFilePath2.do?path="+file.path+"&fileName="+file.name+"'>"+file.name+"</a></div>"+
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
  	
	function selectUser(){
		var org_id = $("#advance_org_id").val();
		var org_name = $("#advance_org_name").val();
		if(org_id == "" || org_name == "" ){
			$.ligerDialog.alert("请先选择报告部门！");
			return;
		}
		top.$.dialog({
			width : 200,
			height : 420,
			lock : true,
			title : "选择报告人",
			content : 'url:app/maintenance/choose_user_list.jsp?org_id='+org_id,
			data : {
				"window" : window
			}
		});
	}
	
	function callUser(id, name){ 
		$('#advance_user_id').val(id);
		$('#advance_user_name').val(name);
	}		
</script>
</head>
<body>
	<form name="formObj" id="formObj" method="post" action="functionTaskInfo/saveBasic.do?status=${param.status}"
		getAction="functionTaskInfo/getDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="basic_id" value="${param.id}"/>
		<input type="hidden" name="sn" id="sn" />
		<input type="hidden" name="data_status" id="data_status" />
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">	
				<c:if test="${param.status eq 'detail'}">
					<tr>
						<td class="l-t-td-left">任务编号：</td>
						<td class="l-t-td-right"><input type="text" name="sn" id="sn" ltype="text"/></td>
						<td class="l-t-td-left">&nbsp;&nbsp;</td>
						<td class="l-t-td-right">&nbsp;&nbsp;</td>
					</tr>
				</c:if>
				<tr>
					<td class="l-t-td-left">任务名称：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="task_name" id="task_name" rows="3" cols="25" class="l-textarea" validate="{required:true,maxlength:200}"></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">任务要求：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="task_requirement" id="task_requirement" rows="3" cols="25" class="l-textarea" validate="{required:false,maxlength:200}"></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">任务类型：</td>
					<td class="l-t-td-right"><input type="text" id="task_type"
						name="task_type" ltype="select" validate="{required:true}"
						ligerui="{
								value:'1',
								readonly:true,
								data: [ { text:'新功能', id:'1' }, { text:'升级功能', id:'2' }, { text:'系统维护', id:'3' }, { text:'数据统计', id:'4' }, { text:'优化功能', id:'5' }],
								suffixWidth:'140'
							}" />
					</td>
					<td class="l-t-td-left">任务分类：</td>
					<td class="l-t-td-right">
						<u:combo name="task_category" code="MAINTENANCE_FUNCTION" modify="false" validate="required:true"/>
					</td>				
				</tr>
				<tr>					
					<td class="l-t-td-left">优先级：</td>
					<td class="l-t-td-right">
						<input type="text" id="task_priority" name="task_priority" ltype="select" validate="{required:true}" ligerui="{
								value:'0',
								readonly:true,
								data: [ { text:'一般', id:'0' }, { text:'重要', id:'1' }, { text:'紧急', id:'2' }],
								suffixWidth:'140'
							}" />
					</td>
					<td class="l-t-td-left">期望完成日期：</td>
					<td class="l-t-td-right">
						<input name="expect_finish_date" id="expect_finish_date" type="text" ltype="date" validate="{required:true}" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">报告部门：</td>
					<td class="l-t-td-right">
						<input name="advance_org_name" id="advance_org_name" type="text" readonly="readonly" title="点击此处选择业务部门" ltype='text' validate="{required:true}" onclick="selectUnitOrUser(0,0,'advance_org_id','advance_org_name')"value="" />
						<input type="hidden" name="advance_org_id" id="advance_org_id" />
					</td>
					<td class="l-t-td-left">报告人：</td>
					<td class="l-t-td-right">
						<input type="hidden" name="advance_user_id" id="advance_user_id" />
						<input type="text"
							name="advance_user_name" id="advance_user_name" ltype="text"
								validate="{required:false}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectUser()}}]}" onclick="selectUser()"/><!-- onclick="selectUser()" -->
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">报告日期：</td>
					<td class="l-t-td-right"><input name="advance_date"
						type="text" ltype="date" validate="{required:false}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="advance_date" />
					</td>
				</tr>
				<c:if test="${param.status eq 'detail'}">
				<tr>					
					<td class="l-t-td-left">任务下达：</td>
					<td class="l-t-td-right">
						<input type="text" id="create_user_name" name="create_user_name" ltype="text" />
					</td>
					<td class="l-t-td-left">下达日期：</td>
					<td class="l-t-td-right">
						<input name="create_date" id="create_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>
				<tr>					
					<td class="l-t-td-left">任务签发：</td>
					<td class="l-t-td-right">
						<input type="text" id="issue_user_name" name="issue_user_name" ltype="text" />
					</td>
					<td class="l-t-td-left">签发日期：</td>
					<td class="l-t-td-right">
						<input name="issue_date" id="issue_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>
				</c:if>
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>附件信息</div>
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
		<c:if test="${param.status eq 'detail'}">
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>信息中心接收信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">接收人：</td>
					<td class="l-t-td-right">
						<input type="text" id="receive_user_name" name="receive_user_name" ltype="text" />
					</td>
					<td class="l-t-td-left">接收日期：</td>
					<td class="l-t-td-right">
						<input name="receive_date" id="receive_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>			
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>一测情况</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">测试结果：</td>
					<td class="l-t-td-right"><input type="radio"
						name="test_result1" id="test_result1" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'满意', id:'1' }, { text:'存在问题或建议', id:'2' } ] }" />
					</td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right">
						<input type="text" id="test_remark1" name="test_remark1" ltype="text" />
					</td>
				</tr>	
				<tr>
					<td class="l-t-td-left">测试人：</td>
					<td class="l-t-td-right">
						<input type="text" id="test_user_name1" name="test_user_name1" ltype="text" />
					</td>
					<td class="l-t-td-left">测试日期：</td>
					<td class="l-t-td-right">
						<input name="test_date1" id="test_date1" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>		
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>二测情况</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">测试结果：</td>
					<td class="l-t-td-right"><input type="radio"
						name="test_result2" id="test_result2" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'满意', id:'1' }, { text:'存在问题或建议', id:'2' } ] }" />
					</td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right">
						<input type="text" id="test_remark2" name="test_remark2" ltype="text" />
					</td>
				</tr>	
				<tr>
					<td class="l-t-td-left">测试人：</td>
					<td class="l-t-td-right">
						<input type="text" id="test_user_name2" name="test_user_name2" ltype="text" />
					</td>
					<td class="l-t-td-left">测试日期：</td>
					<td class="l-t-td-right">
						<input name="test_date2" id="test_date2" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>		
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>三测情况</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">测试结果：</td>
					<td class="l-t-td-right"><input type="radio"
						name="test_result3" id="test_result3" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'满意', id:'1' }, { text:'存在问题或建议', id:'2' } ] }" />
					</td>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right">
						<input type="text" id="test_remark3" name="test_remark3" ltype="text" />
					</td>
				</tr>	
				<tr>
					<td class="l-t-td-left">测试人：</td>
					<td class="l-t-td-right">
						<input type="text" id="test_user_name3" name="test_user_name1" ltype="text" />
					</td>
					<td class="l-t-td-left">测试日期：</td>
					<td class="l-t-td-right">
						<input name="test_date1" id="test_date3" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>		
			</table>
		</fieldset>
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>质管部确认情况</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">确认完成情况：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="text" id="zlb_desc" name="zlb_desc" ltype="text" />
					</td>
				</tr>	
				<tr>
					<td class="l-t-td-left">备注：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="text" id="zlb_remark" name="zlb_remark" ltype="text" />
					</td>
				</tr>	
				<tr>
					<td class="l-t-td-left">质管部经办人：</td>
					<td class="l-t-td-right">
						<input type="text" id="zlb_user_name" name="zlb_user_name" ltype="text" />
					</td>
					<td class="l-t-td-left">日期：</td>
					<td class="l-t-td-right">
						<input name="develop_end_date" id="develop_end_date" type="text" ltype="date" ligerui="{initValue:'',format:'yyyy-MM-dd'}" />
					</td>
				</tr>		
			</table>
		</fieldset>
		</c:if>
	</form>
</body>
</html>
