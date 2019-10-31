<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="kui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
<!--
.l-detail-table td,.l-detail-table input {font-size:14px;}
.l-detail-table input {height:1.5em;}
.l-detail-table tr {height: 2.5em;}
.label {
  display: inline-block;
  padding: .2em 5px .3em;
  margin: 2px;
  font-weight: bold;
  color: #EEF;
  text-align: center;
  white-space: nowrap;
  vertical-align: middle;
  border-radius: .25em;
  height: 18px;
}
.label-detail {
  padding: 2px 5px;
  margin: 1px 3px;
}
.label-read{
  background-color: #777;
}
.label-remind{
  background-color: #f2dede;
  color: #000000;
}
.label-exe{
  background-color: #428bca;
}
.label-chk{
  background-color: #5bc0de;
}
.label .text,.label .btn{
	display: inline-block;
	height: 16px;
}
.label .btn{
	height: 16px;
	width: 16px;
	cursor: pointer;
}
.label .btn-lm{
	margin-left: .5em;
}
.label .add{
	background:url('k/kui/images/icons/16/add.png') no-repeat center 2px;
}
.label .del{
	background:url('k/kui/images/icons/16/cancel.png') no-repeat center 2px;
}
.label .search{
	background:url('k/kui/images/icons/16/search.png') no-repeat center 2px;
}
.label .edit{
	background:url('k/kui/images/icons/16/edit.png') no-repeat center 2px;
}
.l-text-explain-div{
	padding: 2px 5px;
	position: absolute;
	left: 80px;
	top: 0px;
	height: 20px;
}
-->
</style>
<script type="text/javascript">
<!--
function showRemindText(remind){
	if($.kh.isNull(remind)){
		$("#remindcfg").val("");
		$("#remindText").text("无提醒");
		return;
	}
	var jsonData = remind;
	if(typeof remind == "string"){
		jsonData = $.parseJSON(remind);
		$("#remindcfg").val(remind);
	}else{
		$("#remindcfg").val($.ligerui.toJSON(jsonData));
	}
	
	var text = "";
	if(jsonData.ahead){
		text = "提前" + jsonData.aheadDay+"天"+jsonData.aheadHour+"小时提醒";
	}
	if(jsonData.timeout){
		text += (text==""?"":"；") + "到期提醒";
		if(jsonData.rolling)
			text += "，并每隔" + jsonData.rollingHour + "小时循环提醒";
	}
	if("detail"=="${param.status}")
		$("#remindText").parent().parent().html('<div class="input-warp"><div class="input" xtype="radioGroup" name="level"><span class="label label-detail label-exe">' + text + '</span></div></div>');
	else
		$("#remindText").text(text);
}
//-->
</script>
<form id="formObj" action="" getAction="oa/task/view.do?id=${param.id}">
	<input type="hidden" name="id" value="${param.id}" />
	<input type="hidden" name="parent.id" value="${param.pid}" id="pid" />
	<input type="hidden" name="remind" id="remindcfg" value="" />
	<input type="hidden" name="assigner" id="assigner" value="<sec:authentication property="principal.name" htmlEscape="false" />" />
	<input type="hidden" name="assignerId" id="assignerId" value="<sec:authentication property="principal.id" />" />
	
	<c:if test="${param.status=='detail'}">
	<div class="navtab">
	<div title='基本信息'>
	</c:if>
	<table class="l-detail-table" style="margin-top: 10px;">
		<tr>
			<td class="l-t-td-left">任务名称：</td>
			<td class="l-t-td-right"><input type="text" name="title" ltype="text"
				validate="{required:true,maxLenth:300}" /></td>
			<td class="l-t-td-left">级别：</td>
			<td class="l-t-td-right" style="width:350px;"><input type="radio" name="level" ltype="radioGroup"
				ligerui="{initValue:'0',data:[{id:'0',text:'普通'},{id:'1',text:'紧急'}]}" /></td>
		</tr>
		<tr>
			<td class="l-t-td-left">上级任务：</td>
			<td class="l-t-td-right"><input type="text" name="parent.title" ltype="text" id="ptitle"
				<c:if test="${param.status!='detail'}">ligerui="{iconItems:[{icon:'search',click:selectParentTask}]}"</c:if> readonly="readonly" validate="{maxLenth:200}" /></td>
			<td class="l-t-td-left">任务类别：</td>
			<td class="l-t-td-right">
				<kui:combo name="types" code="oa_task_types" ltype="select" validate="required:true" />
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">计划开始时间：</td>
			<td class="l-t-td-right"><input type="text" name="begin" ltype="date" validate="{required:true}" ligerui="{format:'yyyy-MM-dd hh:00',showTime:true}" /></td>
			<td class="l-t-td-left">计划完成时间：</td>
			<td class="l-t-td-right"><input type="text" name="end" ltype="date" ligerui="{format:'yyyy-MM-dd hh:00',showTime:true}" /></td>
		</tr>
		<tr>
			<td class="l-t-td-left">分配人：</td>
			<td class="l-t-td-right">
				<span class="label label-exe">
					<span class="text" id="assignerText"><sec:authentication property="principal.name" htmlEscape="false" /></span>
					<c:if test="${param.status=='modify' or param.status=='add'}"><span class="search btn btn-lm" title="从系统内选择分配人"
						onclick="selectAssigner();">&nbsp;</span>&nbsp;<span class="edit btn btn-lm" title="直接输入分配人姓名"
						onclick="enterAssigner();">&nbsp;</span></c:if>
				</span>
			</td>
			<td class="l-t-td-left">责任人：</td>
			<td class="l-t-td-right">
				<span class="label label-exe">
					<span class="text" id="executorText">请选择</span>
					<c:if test="${param.status=='modify' or param.status=='add'}"><span class="search btn btn-lm" title="从系统内选择责任人"
						onclick="selectExecutor();">&nbsp;</span></c:if>
				</span>
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">参阅人：</td>
			<td class="l-t-td-right" id="reader_td" colspan="3">
				<c:if test="${param.status=='modify' or param.status=='add'}"><span class="l-button label" title="添加参阅人">
					<span class="add btn" onclick="selectReaders();">&nbsp;</span>
				</span></c:if>
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">考核人：</td>
			<td class="l-t-td-right" colspan="3">
				<span class="label label-chk">
					<span class="text" id="checkerText"><sec:authentication property="principal.name" htmlEscape="false" /></span>
					<c:if test="${param.status=='modify' or param.status=='add'}"><span class="search btn btn-lm" title="从系统内选择考核人"
						onclick="selectChecker();">&nbsp;</span></c:if>
				</span>
			</td>
		</tr>
		<tr>
			<td class="l-t-td-left">提醒：</td>
			<td class="l-t-td-right" colspan="3" <c:if test="${param.status=='modify' or param.status=='add'}">colspan="3"</c:if>>
				<span class="label label-remind">
					<span class="text" id="remindText">无提醒</span>
					<c:if test="${param.status=='modify' or param.status=='add'}"><span class="edit btn btn-lm" title="设置提醒规则"
						onclick="configRemind();">&nbsp;</span></c:if>
				</span>
			</td>
		</tr>
		<c:if test="${param.status=='detail'}"><tr>
			<td class="l-t-td-left">进度：</td>
			<td class="l-t-td-right" id="progress_td" colspan="3"><input type="text" name="progress" ltype="text" ligerui="{suffix:'%'}" /></td>
		</tr></c:if>
		<tr>
			<td class="l-t-td-left" valign="top">任务说明：</td>
			<td class="l-t-td-right" colspan="3"><textarea name="desc" rows="8" ltype="textarea"
					validate="{required:true,maxLenth:2000}"></textarea></td>
		</tr>
		<tr id="att_tr">
			<td class="l-t-td-left" valign="top">附件：</td>
			<td class="l-t-td-right" colspan="3">
				<c:if test="${param.status=='modify' or param.status=='add'}">
				<div id="uploader_container">
					<a class="l-button-warp l-button" id="upload_btn" >
						<span class="l-button-main l-button-hasicon">
							<span class="l-button-text">上传</span>
							<span class="l-button-icon l-icon-word"></span>
						</span>
					</a>
					<span class="l-text-explain-div">
					请选择以下格式文件: rar,doc,docx,xls,xlsx,ppt,pptx,pdf,zip,7z,jpg,png,gif,txt,epub
					</span>
				</div>
				</c:if>
				<ul id="uploaded_list" class="l-upload-ok-list"></ul>
			</td>
		</tr>
	</table>
	<c:if test="${param.status=='detail'}">
	</div>
	<div title="任务日志" id="task_logs" style="height:100%;overflow:auto;">
		<jsp:include page="task_logs.jsp" />
	</div>
	</div>
	</c:if>
</form>