<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head pageStatus="detail">
<title>查看任务</title>
<%@ include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="app/task/css/bootstrap.progress.min.css" />
<link type="text/css" rel="stylesheet" href="app/task/css/tl_component.css" />
<script type="text/javascript" src="pub/fileupload/js/fileupload.js"></script>
<script type="text/javascript">
$(function(){
	var ctbar = [{text:"关闭",icon:"close",id:"close",click:function(){api.close();}}];
	$("#formObj").initForm({
		getSuccess: function(resp){
			initToolbar(resp.data);
			if(resp.data.parent) $("#parent_task").show();
			if(resp.data.attachments && resp.data.attachments.length>0){
				$("#uploaded_list").replaceWith("<p class='l-file-preview'></p>");
				$.each(resp.data.validAttachments,function(i){
					$(".l-file-preview").append("<a href='javascript:viewAttachment(\"" + this.attId + "\",\"" + this.attName + "\")' class='preview-item' title='" + this.attName + 
							"' onclick='viewAttachment(\"" + this.attId + "\",\"" + this.attName + "\")'><img width='48' height='48' src='" + getPreviewImageAddr(this.attId,this.attName,48) + 
							"' /></a>");
				});
			}else{
				$("#att_tr").hide();
			}
			setAssigner(resp.data.assigner);
			showProgress(resp.data.progress);
			setExecutor(resp.data.executor);
			setChecker(resp.data.checker);
			addReader(resp.data.readers);
			showRemindText(resp.data.remind);
			showLogs(resp.data.logs);
		},
		showToolbar: true,
		toolbar: ctbar
	});
});

function initToolbar(fdata){
	var c_user_id = '<sec:authentication property="principal.id"/>';
	//执行者
	var tbar = [];
	if((fdata.assignerId==c_user_id || c_user_id == fdata.createrId) && fdata.status==1){
		tbar = tbar.concat({
			text:'修改', id:'modify',icon:'modify', click: function(){
				location = $("base").attr("href") + "app/task/edit_task.jsp?status=modify&id=" + fdata.id;
			}
		});
	}
	if(fdata.executor.stakeholderId==c_user_id || c_user_id == fdata.createrId){
		if(fdata.status==1){
			tbar = tbar.concat({
				text:'终止', id:'terminal',icon:'forbid', click: function(){
					top.winOpen({
						id: "tmnDlg",
						title: "终止任务",
						width: 500,
						height: 300,
						parent: api,
						data: {callback:function(){
							if(api.data.Qm)api.data.Qm.refreshGrid();
							api.close();
						}},
						content: "url:app/task/terminal_task.jsp?id=" + fdata.id
					});
				}
			});
		}
		if(fdata.status==1){
			tbar = tbar.concat([{
				text:'转派', id:'transfer',icon:'userMove',
				click: function() {
					top.winOpen({
						title: "任务转派",
						width: 450,
						height: 250,
						lock: true,
						data: {callback:function(){
							if(api.data.Qm)api.data.Qm.refreshGrid();
							api.close();
						}},
						parent: api,
						content:"url:app/task/transfer_task.jsp?id=" + fdata.id
					});
				}
			},{
				text:'分解', id:'resolve',icon:'same',
				click: function() {
					top.$.dialog({
						width: 700,
						height: 480,
						lock: true,
						parent: api,
						data: {
							Qm: api.data.Qm
						},
						title: "分解任务",
						content: 'url:app/task/edit_task.jsp?status=add&pid=' + fdata.id,
						data: {ptitle:fdata.title}
					});
				}
			},{
				text: "反馈", icon: "feedback", id: "feedback", 
				click: function() {
					top.winOpen({
						title: "任务反馈",
						width: 500,
						height: 300,
						lock: true,
						data: {callback:function(){
							if(api.data.Qm)api.data.Qm.refreshGrid();
							api.close();
						}},
						parent: api,
						content:"url:app/task/feedback_task.jsp?id=" + fdata.id
					});
				}
			},{
				text: "完成", icon: "right", id: "finish", 
				click: function() {
					top.winOpen({
						title: "任务完成",
						width: 500,
						height: 300,
						lock: true,
						data: {callback:function(){
							if(api.data.Qm)api.data.Qm.refreshGrid();
							api.close();
						}},
						parent: api,
						content: 'url:app/task/finish_task.jsp?id=' + fdata.id
					});
				}
			}]);
		}
	}
	else if(fdata.checker.stakeholderId==c_user_id && fdata.status=="2"){
		tbar.push({
			text: "考核", icon: "accept", id: "check", click: function() {
				top.winOpen({
					title: "任务考核",
					width: 500,
					height: 300,
					id: "chkDlg",
					data: {callback:function(){
						api.close();
						if(api.data.Qm)api.data.Qm.refreshGrid();
					}},
					content:"url:app/task/check_task.jsp?id=" + fdata.id
				});
			}
		});
	}
	tbar.push({
		text: "评论", icon: "discuss", id: "discuss", click: function() {
			top.winOpen({
				title: "任务考核",
				width: 500,
				height: 300,
				id: "chkDlg",
				data: {callback:function(){
					location.reload();
				}},
				content:"url:app/task/discuss_task.jsp?id=" + fdata.id
			});
		}
	},{
		text: "关闭", icon: "close", id: "close2", click: function() {api.close();}
	});
	$(".toolbar-tm-bottom").ligerButton({
		items : tbar
	});
	
}

function showProgress(p){
	//$("#progress_td > div").html('<div class="progress" title="' + p + '%"><div class="progress-bar progress-bar-success" style="width:'+ p + 
	//		'%">' + p + '%</div><span class="sr-only">' + p + '%</span></div>');
	$("#progress_td > div").html('<div class="progress" title="' + p + '%"><div class="progress-bar progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="width:' + p + '%"><span class="sr-only">' + p + '%</span></div></div>');
}

function showLogs(logsData){
	$.getJSON("oa/task/log/holdersIcon.do?taskId=${param.id}",function(resp){
		var icons = {};
		if(!resp.success)
			$.ligerDialog.error("获取头像失败！");
		else
			icons = resp.data;
		showLogsWithIcon(logsData,icons);
	});	
}

function showLogsWithIcon(logsData,icons){
	if(logsData.length>0){
		var date = "";
		for(var i = logsData.length-1;i>=0;i--){
			var uid = logsData[i].operatorId;
			var icon = "k/kui/images/head/default.gif";
			if(icons[uid]) icon = icons[uid];
			addLogItem(logsData[i],date,icon);
			date = logsData[i].operatorTime.substring(0,10);
		}
		filterLogs("all");
	}
	$(".cbp_tmtimeline_menu input").show();
	
	$(".cbp_tmtimeline_menu li").click(function(){
		$(this).find(":radio").attr("checked","checked");
		filterLogs($(this).find(":radio").val());
	});
}

function filterLogs(t){
	if(t=="all")$("#tasklogs > li").show(500);
	else{
		$("#tasklogs > li").not($(".li_" + t)).hide(500);
		$(".li_" + t).show(500);
	}
}

function setExecutor(executor){
	$("#executorText").parent().parent().html('<div class="input-warp"><div class="input" xtype="radioGroup" name="level"><span class="label label-detail label-exe">' + executor.stakeholder + '</span></div></div>');
}

function addReader(newReaders){
	if(!newReaders || newReaders.length==0){
		$("#reader_td").parent().hide();
		return;
	}
	var readerHtml = '<div class="input-warp"><div class="input" xtype="radioGroup" name="level">';
	$.each(newReaders,function(i){
		readerHtml += '<span class="label label-detail label-read">' +this.stakeholder +"</span>";
	});
	$("#reader_td").html(readerHtml + '</div></div>');
}
function setChecker(checker){
	$("#checkerText").parent().parent().html('<div class="input-warp"><div class="input" xtype="radioGroup" name="level"><span class="label label-detail label-chk">' + checker.stakeholder + '</span></div></div>');
}
function setAssigner(assigner){
	$("#assignerText").parent().parent().html('<div class="input-warp"><div class="input" xtype="radioGroup" name="level"><span class="label label-detail label-exe">' + assigner + '</span></div></div>');
}
</script>
<style type="text/css">
.l-detail-table td{font-size:14px;}
.l-detail-table tr{
	height: 2.5em;
}
.label{padding:5px}
form {height:100%;}
</style>
<body>
	<jsp:include page="task_form.jsp" />
</body>