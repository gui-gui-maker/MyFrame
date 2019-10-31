<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.status}">
<title>添加&修改&分解任务</title>
<%@ include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript" src="pub/selectUser/selectUnitOrUser.js"></script>
<script type="text/javascript">
$(function(){
	$("#formObj").initForm({
		success: function(resp) {
			if (resp.success) {
				top.$.dialog.notice('保存成功!',3);
				if(api.data.Qm)
					api.data.Qm.refreshGrid();
				api.close();
			} else {
				$.ligerDialog.error('保存失败!<br/>' + resp.msg);
			}
		},
		getSuccess: function(resp){
			if(resp.data.attachments){
				$.each(resp.data.validAttachments,function(i){
					addAttachment(this,false);
				});
			}
			setExecutor(resp.data.executor);
			setChecker(resp.data.checker);
			showRemindText(resp.data.remind);
			setAssigner(resp.data.assignerId,resp.data.assigner);
			if(resp.data.readers){
				addReader(resp.data.readers,false);
			}
		}
	});
	<c:if test="${param.status=='add'}">
	setChecker({
		stakeholderId:'<sec:authentication property="principal.id"/>',stakeholder:'<sec:authentication property="principal.name" htmlEscape="false" />',types:'checker'
	});
	if(api.data.ptitle){
		$("#parent_task").show();
		$("#ptitle").val(api.data.ptitle);
	}
	</c:if>
	var manyuloadConfig = {
		fileSize : "10mb",
		container : "uploader_container",
		buttonId : "upload_btn",
		businessId : "${param.id}",
		title : "rar,doc,docx,xls,xlsx,ppt,pptx,pdf,zip,7z,jpg,png,gif,txt,epub",
		extName : "rar,doc,docx,xls,xlsx,ppt,pptx,pdf,zip,7z,jpg,png,gif,txt,epub",
		fileNum : -1,
		callback : function(files){
			$.each(files,function(i){
				addAttachment({
					attId:this.id,
					attName:this.name
				},true);
			});
		}
	};
	new KHFileUploader(manyuloadConfig);
	
	$("#formObj").validate({
		submitHandler : saveForm
	});
});

// --------------------- 附件管理 ---------------------------
function addAttachment(att,isNew){
	var attachments = $("#formObj").data("attachments");
	if(!attachments) attachments = [];
	attachments.push(att);
	$("#formObj").data("attachments",attachments);
	if(!att.id || att.valid){
		$("#uploaded_list").append("<li id='" + (isNew?att.attId:att.id) + "' class='preview'>"+
			"<div><a href='javascript:viewAttachment(\"" + att.attId + "\",\"" + att.attName + "\")'><img width='48' height='48' src='" + 
			getPreviewImageAddr(att.attId,att.attName,48) + "' /></a></div><div class='l-icon-close progress' onclick='deleteAttachment(" + 
			isNew + ",\"" + (isNew?att.attId:att.id) + "\")' title='删除'>&nbsp;</div></li>");
	}
}
function deleteAttachment(isnew, attid){
	var attachments = $("#formObj").data("attachments");
	var newAttachments = [];
	$.each(attachments,function(){
		if(isnew && this.attId == attid){
			deleteAttFromServer(attid);
		}
		else{
			if(this.id == attid){
				this.valid = false;
				$("#" + attid).remove();
			}
			newAttachments.push(this);
		}
	});
	$("#formObj").data("attachments",newAttachments);
}

//将新上传的文件从服务器删除
function deleteAttFromServer(fid){
	var url = 'fileupload/deleteAtt.do?id=' + fid;
	$.getJSON(url,function(resp){
		if(resp.success){
			$("#" + fid).remove();
		}else{
			$.ligerDialog.error("删除失败！<br/>" + resp.msg);
		}
	});
}
//--------------------- 分配人管理 ---------------------------
function enterAssigner(){
	var assigner = $("#formObj").data("assigner");
	$.ligerDialog.prompt("请输入分配人姓名",assigner==null||assigner.stakeholderId=="not_in_system"?"":assigner.stakeholder,function(yes,value){
		if(yes) setAssigner("not_in_system",value);
	});
}

//选择分配者
function selectAssigner(){
	selectOrgUser({callback:function(datas){
		if(datas.code) setAssigner(datas.code,datas.name);
	}});
}
function setAssigner(aid,aname){
	//如果ID不为空，表示修改
	$("#assigner").val(aname);
	$("#assignerId").val(aid);
	$("#assignerText").text(aname);
}
//--------------------- 责任人管理 ---------------------------
function setExecutor(executor){
	var preExecutor = $("#formObj").data("executor");
	if(preExecutor && preExecutor.id){
		preExecutor.valid = false;
		var stakeholders = $("#formObj").data("stakeholders");
		if(stakeholders)
			stakeholders.push(preExecutor);
		else
			stakeholders = [preExecutor];
		$("#formObj").data("stakeholders",stakeholders);
	}
	
	$("#executorText").text(executor.stakeholder);
	$("#formObj").data("executor",executor);
}

function selectExecutor(){
	selectOrgUser({callback:function(datas){
		if(!datas.code)return;
		setExecutor({
			types : "executor",
			stakeholder: datas.name,
			stakeholderId: datas.code
		});
	}});
}
//---------------------参阅者管理 ---------------------------
function selectReaders(){
	selectOrgUser({checkbox:1,callback:function(datas){
		if(!datas.code)return;
		var codeArr = datas.code.split(",");
		var nameArr = datas.name.split(",");
		var readers = [];
		var existReaders = $("#formObj").data("readers")||[];
		for(var i in codeArr){
			var exist = false;
			for(var j in existReaders){
				if(existReaders[j].stakeholderId == codeArr[i] && existReaders[j].valid == true)
					exist=true;
			}
			if(exist) continue;
			readers.push({
				types : "reader",
				stakeholder: nameArr[i],
				stakeholderId: codeArr[i]
			});
		}
		addReader(readers,true);
	}});
}

function addReader(newReaders,isNew){
	var readers = $("#formObj").data("readers");
	$.each(newReaders,function(){
		$("#reader_td").prepend('<span class="label label-read" id="' + (isNew?this.stakeholderId:this.id) + '"><span class="text">' + this.stakeholder + 
			'</span><span class="del btn btn-lm" onclick="deleteReader(\'' + (isNew?this.stakeholderId:this.id) + '\',' + isNew + ')">&nbsp;</span></span>');
	});
	if(readers)
		readers = readers.concat(newReaders);
	else
		readers = newReaders;
	$("#formObj").data("readers",readers);
}

function deleteReader(id,isNew){
	var readers = $("#formObj").data("readers");
	var newReaders = [];
	$.each(readers,function(){
		if(!isNew){
			if(this.id == id)
				this.valid = false;
			newReaders.push(this);
		}
	});
	$("#formObj").data("readers",newReaders);
	$("#"+id).remove();
}

//--------------------- 考核者管理 ---------------------------
function setChecker(checker){
	var preChecker = $("#formObj").data("checker");
	if(preChecker && preChecker.id){
		preChecker.valid = false;
		var stakeholders = $("#formObj").data("stakeholders");
		if(stakeholders)
			stakeholders.push(preChecker);
		else
			stakeholders = [preChecker];
		$("#formObj").data("stakeholders",stakeholders);
	}
	$("#checkerText").text(checker.stakeholder);
	$("#formObj").data("checker",checker);
}

function selectChecker(){
	selectOrgUser({callback:function(datas){
		if(!datas.code)return;
		setChecker({
			stakeholderId:datas.code,stakeholder:datas.name,types:'checker'
		});
	}});
}

//--------------------- 选择父任务--------------------------
function selectParentTask(){
	top.winOpen({
		content: "url:app/task/select_parent.jsp?cid=${param.id}",
		id: "selParentTask",
		title: '选择上级任务',
		width: 1000,
		height: 450,
		parent: api,
		lock: true,
		cancel: true,
		ok: function(){
			var rst = this.iwin.getSelectResult();
			if(rst==null)return false;
			else {
				$("#pid").val(rst.id);
				$("#ptitle").val(rst.title);
			}
		}
	});
}

//--------------------- 提醒规则 ---------------------------
function configRemind(){
	top.winOpen({
		content: "url:app/task/remind_config.jsp",
		title: '任务提醒设置',
		width: 450,
		height: 240,
		parent: api,
		lock: true,
		data: {remind:$("#remindDay").val(),callback:showRemindText}
	});
}

//--------------------- 数据提交 ---------------------------
function saveForm(form){
	var stakeholders = $("#formObj").data("stakeholders");
	if(!stakeholders) stakeholders = [];
	var readers = $("#formObj").data("readers");
	var executor = $("#formObj").data("executor");
	var checker = $("#formObj").data("checker");
	if(!executor){
		$.ligerDialog.warn("请指定任务责任人！");
		return false;
	}
	stakeholders = stakeholders.concat(readers || [],executor,checker);
	var formdata = transFormDataToJSON($("#formObj").getValues());
	formdata["stakeholders"] = stakeholders;
	var attachments = $("#formObj").data("attachments");
	if(attachments) formdata["attachments"] = attachments;
	$("body").mask("正在保存数据！");
	$.ajax({
        url: "oa/task/save.do",
        type: "POST",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        data: $.ligerui.toJSON(formdata),
        success: function (resp, stats) {
        	if(resp.success){
    			top.$.notice("操作成功！",3);
    			if(api.data.Qm) 
    				api.data.Qm.refreshGrid();
    			api.close();
    		}else{
    			$.ligerDialog.error("操作失败！<br/>" + resp.msg);
    		}
    		$("body").unmask();
        },
        error: function (data) {
            $.ligerDialog.error('操作失败！<br/>' + data.msg);
    		$("body").unmask();
        }
    });
}
</script>
<body>
	<jsp:include page="task_form.jsp" />
</body>