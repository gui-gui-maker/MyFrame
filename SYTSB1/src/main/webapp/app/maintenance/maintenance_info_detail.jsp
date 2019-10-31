<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="java.util.Date"%>
<%@page import="com.khnt.utils.DateUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>编辑系统台账</title>
<%@ include file="/k/kui-base-form.jsp"%>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<%
		String curDate = DateUtil.getCurrentDateTime();
		String cur_date = DateUtil.format(new Date(), "yyyy-MM-dd");
	%>
<script type="text/javascript">
	$(function() {
	    $("#formObj1").initForm({
			success: function (response) {//处理成功
				$("body").unmask();
	    		if (response.success) {
 	            	top.$.dialog.notice({
 	             		content: "保存成功！"
 	            	});
	            	if("${param.status}"=="modify"){
	            		api.data.window.refreshGrid();
		            	api.close();
	            	}else{
	            		clearForm($("#formObj1"));
		            	$("#procufiles").html("");
	            	}
	    		} else {
	           		$.ligerDialog.error('保存失败！<br/>' + response.msg);
	      		}
			}, getSuccess: function (response){
				if (response.task_attachs != null && response.task_attachs != undefined){
					showAttachFile(response.task_attachs);
				}
				//论证人员添加默认值
				var advance_user_name=$("#advance_user_name").val();
				var advance_user_id=$("#advance_user_id").val();
				if(response.data.prove_user_name==null || response.data.prove_user_name==""){
					if("<sec:authentication property='principal.name' htmlEscape='false' />"==advance_user_name){
						$("#prove_user_name").val("<sec:authentication property='principal.name' htmlEscape='false' />");
						$("#prove_user_id").val("<sec:authentication property='principal.id' htmlEscape='false' />");
					}else{
						$("#prove_user_name").val("<sec:authentication property='principal.name' htmlEscape='false' />,"+advance_user_name);
						$("#prove_user_id").val("<sec:authentication property='principal.id' htmlEscape='false' />,"+advance_user_id);
					}
				}
				if(response.data.expect_finish_date==null || response.data.expect_finish_date==""){
					$("#expect_finish_date").val('<%=cur_date%>');
				}
				//完成情况添加默认值
				if(response.data.develop_user_name==null || response.data.develop_user_name==""){
					$("#develop_user_name").val("科鸿-高雅");
				}
				if(response.data.develop_start_date==null || response.data.develop_start_date==""){
					$("#develop_start_date").val('<%=cur_date%>');
				}
				if(response.data.develop_end_date==null || response.data.develop_end_date==""){
					$("#develop_end_date").val('<%=cur_date%>');
				}
				
				if(response.data.test_date==null || response.data.test_date==""){$("#test_date").val('<%=cur_date%>');}
				if(response.data.test_user_name==null || response.data.test_user_name==""){$("#test_user_name").val('科鸿-高雅');}
				if(response.data.develop_desc==null || response.data.develop_desc==""){$("#develop_desc").val('已按要求处理完成');}
				if(response.data.write_user_name==null || response.data.write_user_name==""){$("#write_user_name").val('科鸿-高雅');}
				if(response.data.write_date==null || response.data.write_date==""){$("#write_date").val('<%=cur_date%>');}
				if(response.data.prove_type==null || response.data.prove_type==""){	$('#prove_type').ligerGetRadioGroupManager().setValue("0");}
				
			}, toolbar: [
	      		{text: "修改", icon: "modify", click: modify},
				{text: "论证", icon: "modify", click:demonstration},
				{text: "完成", icon: "modify", click: complete}
			], toolbarPosition: "bottom"
		});		
	    //修改
	    function modify(){
	    	//if(confirm("确定保存？")){
	    		$("#type").val(1);
    			$("#formObj1").submit();	
	    	//}
				
	    }
	    
	    //论证
	    function demonstration() {

			var prove_type =$('#prove_type').ligerGetRadioGroupManager().getValue();
	    	if(prove_type==null|| prove_type==""){
	    		confirm("请填写论证结论！");
	    		return false;
	    	}
	    	
	    	if("1" == prove_type){
				if($('#prove_remark').val()==null||$('#prove_remark').val()==undefined||$('#prove_remark').val()==""){
					$.ligerDialog.alert('论证结论为延期时，请填写论证备注！');
					return;
				}
			}else{
				var expect_finish_date = $('#expect_finish_date').val();
				if(expect_finish_date==null || expect_finish_date==undefined || expect_finish_date==""){
					$.ligerDialog.alert('论证结论为处理时，请选择预计完成日期！');
					return;
				}
			}
	    	
			//表单提交
	    	//if(confirm("确定保存？")){
	    		$("#type").val(2);
    			$("#formObj1").submit();
	    	//}
	        
	    }
	  //完成
		function complete(){
			var status="${param.statusData}";

			var prove_type =$('#prove_type').ligerGetRadioGroupManager().getValue();
	    	if(prove_type==null|| prove_type==""){
	    		confirm("请填写论证结论！");
	    		return false;
	    	}
	    	
	    	if("1" == prove_type){
				if($('#prove_remark').val()==null||$('#prove_remark').val()==undefined||$('#prove_remark').val()==""){
					$.ligerDialog.alert('论证结论为延期时，请填写论证备注！');
					return;
				}
			}else{
				var expect_finish_date = $('#expect_finish_date').val();
				if(expect_finish_date==null || expect_finish_date==undefined || expect_finish_date==""){
					$.ligerDialog.alert('论证结论为处理时，请选择预计完成日期！');
					return;
				}
			}
	    	
			if(status != "2"){
				$.ligerDialog.error("亲，该功能未论证暂不能标记完成！");
				return;
			}else{
				//标记完成
		    	//if(confirm("确定保存？")){
		    		$("#type").val(3);
	    			$("#formObj1").submit();
		    	//}
		        
			}
		}
		// 附件上传
		var receiptUploadConfig = {
    			fileSize : "100mb",	//文件大小限制
    			businessId : "",	//业务ID
    			buttonId : "procufilesBtn",		//上传按钮ID
    			container : "procufilesDIV",	//上传控件容器ID
    			title : "图片、Word、Excel",	//文件选择框提示
    			extName : "jpg,gif,png,bmp,doc,docx,xls,xlsx,rar,zip,pptx,pps,pdf,txt",	//文件扩展名限制
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
  	
	function selectUser(){
		var org_id = $("#org_id").val();
		var org_name = $("#org_name").val();
		if(org_id == "" || org_name == "" ){
			$.ligerDialog.alert("请先选择业务部门！");
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
	
	function selectTask(){
		var url = 'url:app/func_task/func_task_list4.jsp';
		top.$.dialog({
			parent: api,
			width : 800, 
			height : 550, 
			lock : true, 
			title:"选择任务书",
			content: url,
			data : {"parentWindow" : window}
		});
	}
	
	function callBackTask(id,name,sn){
		$('#fk_func_task_id').val(id);
		$('#func_task_name').val(name);
		$('#func_task_sn').val(sn);
	}

	function selUser12() {
		selectUnitOrUser(1, 1,"" , "", function(callbackData) {
			var userId = callbackData["code"];
			var nameArr = callbackData["name"];
			$("#prove_user_id").val(userId);
			$("#prove_user_name").val(nameArr);
		});
	}
</script>
</head>
<body>
	<form name="formObj1" id="formObj1" action="maintenance/info/maintenanceEdit.do" getAction="maintenance/info/getDetail.do?id=${param.id}">
		<input type="hidden" name="id" id="basic_id" value="${param.id}"/>
		<input type="hidden" name="type" id="type" value=""/>
		
		<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>基本信息</div>
			</legend>
			<table cellpadding="3" cellspacing="0" class="l-detail-table">
				<tr>
					<td class="l-t-td-left">类型：</td>
					<td class="l-t-td-right"><input type="text" id="info_type"
						name="info_type" ltype="select" validate="{required:true}"
						ligerui="{
								value:'',
								readonly:true,
								data: [ { text:'新增功能', id:'1' }, { text:'运行维护', id:'2' }, { text:'数据处理', id:'4' }, { text:'其他', id:'6' }],
								suffixWidth:'140'
							}" />
					</td>
					<td class="l-t-td-left">功能模块：</td>
					<td class="l-t-td-right"><!--<input type="text" name="func_name"
						id="func_name" ltype="text" validate="{required:true}" />-->
						<u:combo name="func_name" code="MAINTENANCE_FUNCTION" modify="false" validate="required:true"/>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">功能说明：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="pro_desc" id="pro_desc" rows="3" cols="25" class="l-textarea" validate="{required:true,maxlength:2000}"></textarea>
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">来源部门：</td>
					<td class="l-t-td-right">
						<input name="org_name" type="text" readonly="readonly" id="org_name" title="点击此处选择业务部门"
					ltype='text' validate="{required:true}" onclick="selectUnitOrUser(0,0,'org_id','org_name')"
					value="" />
						<input type="hidden" name="org_id" id="org_id" />
					</td>
					<td class="l-t-td-left">报告人：</td>
					<td class="l-t-td-right">
						<input type="hidden" name="advance_user_id" id="advance_user_id" />
						<input type="text"
							name="advance_user_name" id="advance_user_name" ltype="text"
								validate="{required:true}" ligerui="{value:'',iconItems:[{icon:'add',click:function(){selectUser()}}]}" onclick="selectUser()"/><!-- onclick="selectUser()" -->
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">报告日期：</td>
					<td class="l-t-td-right"><input name="advance_date" 
						type="text" ltype="date" validate="{required:false}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="advance_date" />
					</td>
					<td class="l-t-td-left">记录人：</td>
					<td class="l-t-td-right"><input type="text"
						name="create_user_name" id="create_user_name" ltype="text"
						validate="{required:false}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">记录日期：</td>
					<td class="l-t-td-right"><input name="create_date"
						type="text" ltype="date" validate="{required:false}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="create_date" />
					</td>
					<td class="l-t-td-left">受理人：</td>
					<td class="l-t-td-right"><input type="text"
						name="receive_user_name" id="receive_user_name" ltype="text"
						validate="{required:false}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">受理日期：</td>
					<td class="l-t-td-right"><input name="receive_date"
						type="text" ltype="date" validate="{required:false}"
						ligerui="{initValue:'',format:'yyyy-MM-dd'}" id="receive_date" />
					</td>
					<td class="l-t-td-left">任务书：</td>
					<td class="l-t-td-right">
						<input name="fk_func_task_id" id="fk_func_task_id" type="hidden" />
						<input name="func_task_name" id="func_task_name" type="hidden" />
						<input type="text" name="func_task_sn" id="func_task_sn" ltype="text"
						validate="{required:false}" onclick="selectTask()"
										ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selectTask()}}]}"/>
					</td>
				</tr>
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
				<c:if test="${param.status!='detail' }">
				<tr>
					<td class="l-t-td-left">反馈方式：</td>
					<td class="l-t-td-right" colspan="3"><input type="radio"
						name="send_msg_type" id="send_msg_type" ltype="radioGroup"
						validate="{required:false}"
						ligerui="{value:'1',data: [ { text:'微信', id:'1' }, { text:'短信', id:'2' }, { text:'微信和短信', id:'3' }, { text:'不反馈', id:'0' } ] }" />
					</td>
				</tr>
				</c:if>
			</table>
		</fieldset>
		
		<div id="bean">
			<fieldset class="l-fieldset">
			<legend class="l-legend">
				<div>论证情况</div>
			</legend>
			<table width="100%">
				<tr>
					<td class="l-t-td-left">论证人员：</td>
					<td class="l-t-td-right" colspan="3">
						<input type="hidden" id="prove_user_id" name="prove_user_id"  />  
							<input type="text" id="prove_user_name"  name="prove_user_name" validate="{required:true}" ltype="text"
							onclick="selUser12()" ligerui="{readonly:true,value:'',iconItems:[{icon:'add',click:function(){selUser12()}}]}" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">论证结论：</td>
					<td class="l-t-td-right">
						<input type="radio" name="prove_type" id="prove_type" ltype="radioGroup"
						 ligerui="{value:'0',data: [ { text:'处理', id:'0' }, { text:'延期', id:'1' }] }" />
					</td>	
					<td class="l-t-td-left">预计完成日期：</td>
					<td class="l-t-td-right">
						<input name="expect_finish_date" type="text" ltype="date" validate="{required:false}"
						ligerui="{initValue:'<%=curDate %>',format:'yyyy-MM-dd'}" id="expect_finish_date" />
					</td>
				</tr>
				<tr>
					<td class="l-t-td-left">论证备注：</td>
					<td class="l-t-td-right" colspan="3"><textarea
							name="prove_remark" id="prove_remark" rows="3" cols="25"
							class="l-textarea" validate="{required:false,maxlength:1000}"></textarea>
					</td>
				</tr>
			</table>
		</fieldset>
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
				
			</table>
		</fieldset>
		</div>
	</form>
	
</body>
</html>
