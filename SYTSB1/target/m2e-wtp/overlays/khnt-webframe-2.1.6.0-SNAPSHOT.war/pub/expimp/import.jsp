<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript" src="k/kui/frame/jquery.form.js"></script>
<script type="text/javascript">
var data_file_types,templete_file_id,cfg_data;
$(function() {
	$("#impform").initForm({
		toolbar : [{
			id : "tpldownload",
			text : "<span style='color:red'>下载模板文件</span>",
			icon : "table-save",
			click : function(){
				window.open($("base").attr("href") + "pub/expimp/cfg/download_tpl.do?cfg=${param.cfg}");
			}
		},{
			id : "userconfig",
			text : "<span style='color:blue'>设置</span>",
			icon : "settings",
			click : function(){
				doUserConfig(cfg_data);
			}
		},{
			id: "submitbtn",
			text : "导入",
			icon : "save",
			disabled: true,
			click : submitForm
		},{
			text : "关闭",
			icon : "close",
			click : function() {
				api.close();
			}
		}]
		,afterParse:function(){
			$("body").mask("正在加载，请稍候...");
			$.getJSON(
				"pub/expimp/cfg/getBusConfig.do?code=${param.cfg}",
				function(resp){
					$("body").unmask();
					if(resp.success){
						data_file_types = resp.data.sourceType;
						// 模板文件
						if(resp.data.tplId || resp.data.tplPath){
							$("#tpldownload").show();
						}
						else{
							$("#tpldownload").remove();
						}
						
						// 用户定义设置
						if(resp.data.ucg=="1"){
							$("#userconfig").show();
							cfg_data = resp.data;
						}
						else{
							$("#userconfig").remove();
						}
						setTimeout(function(){
							$("#submitbtn").ligerGetButtonManager().setEnabled();
						},100)
						
					}else{
						$.ligerDialog.error("获取配置信息失败，请稍后再试！");
					}
				}
			);
		}
	});
	
	if(window.api){
		api.title("数据导入");
	}
	
});

function doUserConfig(data){
	top.winOpen({
		title:"数据导入自定义配置",
		parent:api,
		lock:true,
		width:350,
		height: 250,
		content:"url:pub/expimp/imp_user_config.jsp",
		data:{callback:userConfigCallback,settings:data}
	});
}

function userConfigCallback(cfgInfo){
	if(cfgInfo){
		$("#ucfg").val($.ligerui.toJSON(cfgInfo));
	}
}

function submitForm() {
	if ($("#f_excel").val() == "") {
		top.$.dialog.alert('请选择要导入的文件',null,api);
		return;
	}
	
	var suffix = $("#f_excel").val().match(/^(.*)(\.)(.{1,8})$/)[3];
	suffix = suffix.toUpperCase();
	if ((data_file_types=="excel" && (suffix != "XLS" && suffix != "XLSX"))||(data_file_types=="csv" &&(suffix != "CSV" && suffix != "TXT"))) {
		var msg = data_file_types=="excel"?"EXCEL文件（后缀为xls或者xlsx）":"格式化文本文件（后缀为csv或者txt）";
		top.$.dialog.alert('文件格式错误！<br/>只能选择' + msg,null,api);
		return false;
	}
	$(".fup_info").show(200);
	$(".result_infosc").empty();
	$(".result_infofd").empty();
	$(".process-deta").text("0");
	$(".process-rate").width(0);
	$(".processbar").hide();
	$(".result_sc").hide();
	$(".result_fd").hide();
	$(".toolbar-tm-bottom").hide(200);
	$("#impform").ajaxSubmit({
		dataType: "json",
		success: function(response) {
			$(".fup_info").hide(200);
			if(response.success){
				$(".processbar").show();
				window.setTimeout("rolingProcess('" + response.data.task + "')",1000);
			}
			else{
				$(".toolbar-tm-bottom").show(200);
				$(".result_fd").show(200);
				$(".result_infofd").append("<p>" + response.data.msg+"</p>");
			}
			$("#impform").get(0).reset();
		}
	});
}

function rolingProcess(taskKey) {
	$.getJSON("pub/expimp/rollingAsk.do?taskKey=" + taskKey, function(response) {
		if (response.success) {
			if(response.data.status==200){
				$(".process-deta").text("100%");
				$(".process-rate").css("width","100%");
				$(".result_infosc").append("<p>数据导入完成。</p>");
				if(response.data.error){
					$(".result_infosc").append("<p>成功导入【" + (response.data.process) + "】行数据。</p><p>有【" 
							+ response.data.errorNum + "】行错误数据。</p>"
							//+ "<p>错误的数据在原文件中的行号为：【" + response.data.errorRows + "】。</p>"
							+ "<p class='download'><a href='pub/expimp/downloadErrorReport.do?taskKey="
							+ taskKey + "'>点击这里下载错误数据汇总。</a></p>");
				}else
					$(".result_infosc").append("<p>成功导入<span class='red'>【" + response.data.process + "】</span>行数据。</p>");
				if(response.data.emptyRows>0)
					$(".result_infosc").append("<br/>有<span class='red'>【" + response.data.emptyRows + "】</span>行空数据被忽略。");
				window.setTimeout('$(".processbar").hide(200);$(".result_sc").show(200);$(".toolbar-tm-bottom").show(200);',500);
				
				// 页面回调，一般用于刷新数据
				if(api.data.callback){
					api.data.callback();
				}
			}
			else if(response.data.status==400){
				updateProcess(response.data.process,response.data.total);
				$("#parse-info").hide(200);
				$("#imp-info").show(200);
				//定时0.5秒刷新状态
				window.setTimeout("rolingProcess('" + taskKey + "')",500);
			}
			else if(response.data.status==300){
				updateProcess(response.data.process,response.data.total);
				//定时0.5秒刷新状态
				window.setTimeout("rolingProcess('" + taskKey + "')",500);
			}else if(response.data.status==500){
				$(".processbar").hide(200);
				$(".result_fd").show(200);
				$(".toolbar-tm-bottom").show(200);
				$(".result_infofd").append("数据导入失败！<br />" + response.data.msg);
			}
		}
	});
}

function downloadError(k){
	window.location.href = "pub/expimp/downloadErrorReport.do?taskKey=" + k;
}

function updateProcess(prc,total,arg){
	if(total<=0){
		$(".process-deta").text(prc);
	}else{
		var rate = 0;
		if(total==prc) rate = 100;
		else rate = new Number(prc/total*100).toFixed(1);
		$(".process-deta").text(rate + "%");
		$(".process-rate").css("width",rate + "%");
	}
}
</script>
<style type="text/css">
body {margin: 0;padding: 0;}
form {height: 100%;font: 14px "微软雅黑", "宋体";overflow: hidden;}
.import-caption {padding: 10px 0;height: 30px;background: #ebebeb;overflow: hidden;line-height: 28px;position: relative;}
.import-caption .icon {	background: url(pub/expimp/image/table_import.png) no-repeat;position: absolute;left: 10px;top: 13px;width: 32px;height: 32px;}
.import-caption strong {font: 14px "微软雅黑", "宋体";font-weight: bold;line-height: 28px;margin-left: 38px;}
/* 1 */
.fup_info {text-align: center;line-height: 130px;}
.red {	color: #d80101;}
/* 2 */
.processbar {margin: 35px auto 0px;	width: 420px;overflow: hidden;}
.prsbar-txt {text-align: center;height: 30px;}
.imp_process_bg {position: relative;text-align: center;height: 22px;background-color: gray;	overflow: hidden;}
.imp_process {width: 416px;height: 18px;border: 1px #FFFFFF solid;z-index: 9;margin: 1px;}
.process-rate {	height: 18px;width: 0;background: url(pub/expimp/image/loading-ss.png) repeat-x left top;}
.process-deta {position: absolute;top: 2px;left: 45%;z-index: 12;font-size: 12px;text-align: center;color: #fff;}
/* 3 */
.result_sc,.result_fd {	margin: 5px 0 0 0;width: 440px;	overflow: hidden;}
.download {	line-height: 30px;	padding-left: 20px;	background: url('pub/expimp/image/download.png') no-repeat left center;}
.result_infosc {clear: both;background: url(pub/expimp/image/ok.png) no-repeat left center;padding-left: 50px;margin: 10px auto 0px;width: 320px;}
.result_infofd {clear: both;background: url(pub/expimp/image/error.png) no-repeat left center;padding-left: 50px;margin: 10px auto 0px;width: 320px;}
#tpldownload,#userconfig{float:left;display:none;}
</style>
</head>
<body>
	<form class="wrap" id="impform" action="pub/expimp/import.do" method="post"
		enctype="multipart/form-data">
		<input type="hidden" name="config" value="${param.cfg}" />
		<input type="hidden" name="ucfg" id="ucfg" value="{}" />
		<input type="hidden" name="param" id="param" value="${param.param}" />
		<div class="import-caption">
			<span class="icon"></span><strong>请选择数据文件：</strong> <input type="file" name="upload_excel" id="f_excel" style="width: 60%;" />
		</div>
		<!--  1  -->
		<div class="fup_info" style="display: none;">
			正在上传文件<span class='red' style="display: none;">0</span>
		</div>
		<!--  2 -->
		<div class="processbar" style="display: none">
			<div id="parse-info" class="prsbar-txt">文件上传完成，正在解析数据&sdot;&sdot;&sdot;</div>
			<div id="imp-info" class="prsbar-txt" style="display: none;">数据解析完成，正在导入数据&sdot;&sdot;&sdot;</div>
			<div class="imp_process_bg">
				<div class="imp_process">
					<div class="process-rate"></div>
				</div>
				<div class="process-deta">0</div>
			</div>
		</div>
		<!--  3  -->
		<div class="result_sc" style="display: none;">
			<div class="result_infosc"></div>
		</div>
		<div class="result_fd" style="display: none;">
			<div class="result_infofd"></div>
		</div>
	</form>
</body>
</html>