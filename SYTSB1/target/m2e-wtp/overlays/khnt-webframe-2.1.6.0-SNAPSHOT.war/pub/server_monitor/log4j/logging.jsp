<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>系统日志</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	$(function() {
		$("#layout").ligerLayout({
			topHeight : 30,
			space : 0,
			allowTopResize : false
		});
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
					$("#logdown").attr("src",$("base").attr("href") + "pub/log4j/downloadLogs.do");
				}
			} ]
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
		$("body").mask("日志加载中");
		pollingLogs("size");
	});
	
	// 全局的几个变量
	// ----
	// 定时器ID
	var global_interval_id = null;
	// 每次获取日志的大小,改变大小时会修改此变量，获取日志时使用此参数
	var global_last_log_size = 200; 
	// 获取日志的起始位置,日志文件可能会很大，这里会记录下每一次获取的日志最后末尾位置，作为自动获取日志时的起始位置，以便获取自上一次之后新增的日志内容
	var global_last_log_index = 0;
	// 不同模式下获取日志的两个URL，包括按大小获取和指定起始位置获取
	var global_polling_url = {
		"size" : "pub/log4j/lastSizeLogs.do",
		"index" : "pub/log4j/lastIndexLogs.do"
	};
	// 不同模式获取日志的参数，按大小获取和按指定起始位置获取
	var global_polling_param = {
		"size" : {
			size : function(){return global_last_log_size}
		},
		"index" : {
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
		// 显示数据加载提示信息
		$.ajax({
			url : global_polling_url[type],
			data : global_polling_param[type],
			dataType : "text",
			success : function(resp) {appendLogs(type,resp);},
			error : function(xhr, st, et) {
				$("#logging").html("刷新日志失败!"+ st + " " + et);
			}
		});
	}
	function html_encode(str){
		if (str.length == 0) return str;
		return str.replace(/</g,"&lt;").replace(/>/g, "&gt;");
	}
	function appendLogs(type,resp){
		var lineStrArr = resp.split("\r");
		if(lineStrArr[0]=="error"){
			$("#logging").html("<div class='error-logs'>"+html_encode(lineStrArr[1])+"</div>");
	        $("body").unmask();
	        $(".refresh_msg").hide();
			return;
		}
		global_last_log_index = lineStrArr[0].replace("lastIndex:","");
		for(var i = 1; i < lineStrArr.length; i++){
			var lineStr = html_encode(lineStrArr[i]);
			console.log(lineStr);
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
		$(".refresh_msg").hide();
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
						<td style="width: 20px;" class="refresh_msg"><img src='k/kui/images/indicator.gif' /></td>
						<td style="width: 80px; text-align: left; padding: 2px" class="refresh_msg">正在刷新！</td>
						<td style="width: 125px;"><input id='time_list' type='input' ltype='select'
							ligerui='{initValue:"manual",emptyOption:false,width:120,data:[{id:"manual",text:"手动刷新"},{id:5,text:"5秒自动刷新"},{id:20,text:"20秒自动刷新"},{id:60,text:"1分钟自动刷新"},{id:120,text:"2分钟自动刷新"}]}' />
						</td>
						<td style="width: 105px;"><input id='size_list' type='input' ltype='select'
							ligerui='{initValue:200,emptyOption:false,width:100,data:[{id:200,text:"200KB"},{id:500,text:"500KB"},{id:1024,text:"1MB"}]}' />
						</td>
					</tr>
				</table>
			</div>
		</div>
		<div position="center" class="logging" id="logging" style="word-wrap:normal;white-space:nowrap;overflow:auto;overflow-y:auto;overflow-x:auto;">
			<!-- <textarea class="l-textarea" wrap="off" style="height: 100%; border: none;" id="content"></textarea> -->
		</div>
	</div>
    <iframe id="logdown" src="" style="display:none;"></iframe>
</body>
</html>