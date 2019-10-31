<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%
	CurrentSessionUser user = SecurityUtil.getSecurityUser();
	String userId = user.getId();
	String userName = user.getName();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<title>选择审核意见</title>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
 <script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
$(function() {
	//alert(11);
	 $("#formObj").initForm({
			toolbar: [{ text: '保存', id: 'submit', icon: 'submit', click:save},
			          {text: '关闭', id: 'close', icon: 'cancel', click: function () {
						api.close();
						}}],
		
	}); 
});
function save(){
	 var opinion=$("#opinion").ligerGetRadioGroupManager().getValue();
		if(opinion=="1"){
		if($("#remark").val()==""||$("#remark").val()=="请在此处填写不通过原因！"){
			$.ligerDialog.warn("请填写不通过原因！！！");
			return;
		}
	}
		if($("#remark").val().length>200){
			$.ligerDialog.warn("审核意见超过200长度！！！");
			return;
		}
	$.ligerDialog.confirm("确认提交？", function(yes){
		if(yes){
			 $("body").mask("提交中...");
			var opinion=$("#opinion").ligerGetRadioGroupManager().getValue();
			var remark=$("#remark").val();
			var back=$("#back").val();
			var uploadFiles=$("#uploadFiles").val();
			var ids="${param.id}";
			if(opinion==""){
				$.ligerDialog.warn("请选择结论！！！");
				return;
			}
			$.ajax({
	        	url: "com/tjy2/instructionRw/subAudit1.do?",
	            type: "POST",
	            data:{id:ids,opinion:opinion,remark:remark},
	            success: function (resp) {
	                if (resp.success) {
	                	$("body").unmask();
	               	 	top.$.notice('提交成功！！！');
	               	    api.data.window.Qm.refreshGrid();
						api.close();
	                }else{
	                	$.ligerDialog.error('提示：' + data.msg);
	                }
	            },
	            error: function (data0,stats) {
	                $.ligerDialog.error('提示：' + data.msg);
	            }
	        });
		}
	});
}
function selectUser() {
    selectUnitOrUser(1, 1, "auditId", "auditName");
}
function changeFlag(flag) {
	$("#back").hide();
	if (flag == "0") {
		$("#sub_op").show();
	} else {
		$("#back").show();
		$("#sub_op").hide();
	}
	<%-- if(main_audit_id!=null&&main_audit_id!=""&&main_audit_id!="<%=user.getId()%>"){
	$("#sub_op").hide();
	} --%>
}
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
	<form name="formObj" id="formObj">
	<table  border="1" cellpadding="3" cellspacing="0"
			class="l-detail-table">
		<tr>
			<td class="l-t-td-left">结论：</td>
			<td class="l-t-td-right">
			<input id="opinion" name="opinion" type="radio" ltype='radioGroup'
			 ligerui="{initValue:'0',onChange:changeFlag,data:[{id:'0',text:'通过'},{id:'1',text:'不通过'}]}"/>
			</td>
		</tr>
				<tr>
					<td class="l-t-td-left">审核意见：</td>
					<td class="l-t-td-right" colspan="3">
						<textarea name="remark" id="remark" rows="3" cols="" placeholder="请在此处填写不通过原因！" ext_type="string" ext_maxLength="200" ext_name="备注" isNull="Y" class="area_text" ></textarea></textarea>
					</td>	
				</tr>
				
	</table>
	</form>
</body>
</html>
