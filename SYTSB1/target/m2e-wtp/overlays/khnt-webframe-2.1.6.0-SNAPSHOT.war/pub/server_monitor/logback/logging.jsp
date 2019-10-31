<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统日志</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#toolbar").ligerToolBar({
			items : [ "-", {
				id : "save",
				text : "刷新",
				icon : "refresh",
				click : function(){pollingLogs("index");}
			}, "-", {
				id : "down",
				text : "下载",
				icon : "export",
				click : function() {
					$("#logdown").attr("src",$("base").attr("href") + "pub/logback/downloadLogs.do");
				}
			} ]
		});
		$("#layout").ligerLayout({
			topHeight : 30,
			space : 0,
			allowTopResize : false
		});
		
		// 刷新时间选择下拉框
		$("#time_list").ligerComboBox({
			onSelected : function(v, t) {
				if (v == "")
					return;
				if (v == "manual")
					window.clearInterval(global_interval_id);
				else
					createTimer(v);
			}
		});
		// 日志大小选择下拉框
		$("#size_list").ligerComboBox({
			onSelected : function(v, t) {
				if (v) {
					global_last_log_size = v;
					pollingLogs("size");
				}
			}
		});
		
		// 第一次加载默认大小的日志
		//$("body").mask("日志加载中");
		getLogfiles();
	});
	
	// 获取日志文件列表
	function getLogfiles(){
		$.getJSON("pub/logback/appenders.do",function(resp){
			if(resp.success){
				var appenders = [];
				for(var i in resp.data){
					appenders.push({
						id: resp.data[i],
						text: resp.data[i]
					});
				}
				$("#logger_list").ligerComboBox({
					data: appenders,
					emptyOption: false,
					width: 120,
					onSelected : function(v, t) {
						if (v) {
							$("#logging").empty();
							global_log_name = v;
							pollingLogs("size");
						}
					}
				});
			}else{
				$.lgerDialog.error("加载日志文件列表失败");
			}
		});
	}
	
	// 全局的几个变量
	// ----
	var global_log_name = null;
	// 定时器ID
	var global_interval_id = null;
	// 每次获取日志的大小,改变大小时会修改此变量，获取日志时使用此参数
	var global_last_log_size = 200; 
	// 获取日志的起始位置,日志文件可能会很大，这里会记录下每一次获取的日志最后末尾位置，作为自动获取日志时的起始位置，以便获取自上一次之后新增的日志内容
	var global_last_log_index = 0;
	// 不同模式下获取日志的两个URL，包括按大小获取和指定起始位置获取
	var global_polling_url = {
		"size" : "pub/logback/lastSizeLogs.do",
		"index" : "pub/logback/lastIndexLogs.do"
	};
	// 不同模式获取日志的参数，按大小获取和按指定起始位置获取
	var global_polling_param = {
		"size" : {
			logName: function(){return global_log_name},
			size : function(){return global_last_log_size}
		},
		"index" : {
			logName: function(){return global_log_name},
			idx : function(){return global_last_log_index}
		}
	};
	// ---- 全局变量声明结束

	// 定时刷新
	function createTimer(sec) {
		if (global_interval_id)
			window.clearInterval(global_interval_id);
		global_interval_id = window.setInterval(function(){pollingLogs("index");}, sec * 1000);
	}

	// 拉取日志
	function pollingLogs(type) {
		$(".refresh_msg").show();
		// 显示数据加载提示信息
		$.ajax({
			url : global_polling_url[type],
			data : global_polling_param[type],
			dataType : "text",
			success : function(resp) {
				appendLogs(type,resp);
				$(".refresh_msg").hide();
			},
			error : function(xhr, st, et) {
				$(".refresh_msg").hide();
				$("#logging").html("刷新日志失败!"+ st + " " + et);
			}
		});
	}
	
	function html_encode(str){
		if (str.length == 0) return str;
		return str.replace(/</g,"&lt;").replace(/>/g, "&gt;");
	}
	
	function appendLogs(type,resp){
		resp = resp.replace(/\n/gi,'\r');
		var lineStrArr = resp.split("\r");
		if(lineStrArr[0]=="error"){
			$("#logging").html("<div class='error-logs'>"+html_encode(lineStrArr[1])+"</div>");
	        $("body").unmask();
			return;
		}
		global_last_log_index = lineStrArr[0].replace("lastIndex:","");
		for(var i = 1; i < lineStrArr.length; i++){
			var lineStr = html_encode(lineStrArr[i]);
			if(lineStr == "")
				continue;
			var color = "#000000";
			if(lineStr.indexOf("ERROR")==0)
				color = "#FF0000";
			else if(lineStr.indexOf("INFO")==0)
				color = "#0000FF";
			else if(lineStr.indexOf("WRAN")==0)
				color = "#FFA500";
			else if(lineStr.indexOf("TRACE")==0)
				color = "#808080";
			else if(lineStr.indexOf("DEBUG")==0)
				color = "#000000";
			else 
				color = "#FF0000";
			
			lineStr = "<div style='color:" + color + "'>" + lineStr + "</div>";
			$("#logging").append(lineStr);
		}
		// 数据加载完成，隐藏数据加载提示信息
		$("body").unmask();
		// 滚到到最末尾
		$("#logging").scrollTop($("#logging").get(0).scrollHeight);
	}
</script>
<style type="text/css">
.logging p{padding: 3px 0 0 5px;}
.error-logs{
    padding:1em;color:red;
}
</style>
</head>
<body>
	<div id="layout">
		<div position="top">
			<div id="toolbar" style="width: 50%; float: left;"></div>
			<div id="configs" class="l-toolbar" style="width: 50%; float: left; text-align: right;">
				<table style="float: right">
					<tr>
						<td style="width:20px;display:none;" class="refresh_msg"><img src='k/kui/images/indicator.gif' /></td>
						<td style="width:80px;display:none;text-align:left;padding: 2px" class="refresh_msg">正在刷新！</td>
						<td style="width:125px;"><input id='logger_list' type='input' ltype='select' /></td>
						<td style="width:125px;"><input id='time_list' type='input' ltype='select'
							ligerui='{initValue:"manual",emptyOption:false,width:120,data:[{id:"manual",text:"手动刷新"},{id:5,text:"5秒自动刷新"},{id:20,text:"20秒自动刷新"},{id:60,text:"1分钟自动刷新"},{id:120,text:"2分钟自动刷新"}]}' />
						</td>
						<td style="width: 105px;"><input id='size_list' type='input' ltype='select'
							ligerui='{initValue:200,emptyOption:false,width:100,data:[{id:200,text:"200KB"},{id:500,text:"500KB"},{id:1024,text:"1MB"}]}' />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div position="center" class="logging" id="logging" 
			style="word-wrap:normal;white-space:nowrap;overflow:auto;overflow-y:auto;overflow-x:auto;"></div>
	</div>
    <iframe id="logdown" src="" style="display:none;"></iframe>
</body>
</html>