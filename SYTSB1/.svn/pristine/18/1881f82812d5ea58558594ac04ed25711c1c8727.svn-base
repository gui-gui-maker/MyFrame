<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工作任务管理</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	$(function() {
		var url = "pub/worktask/worktask_list.jsp?status=";
		var status = {
			"nodeal" : "0",
			"overdue" : "2",
			"finish" : "3"
		};
		var tabManager = $("#navtab").ligerTab({
			height : "100%",
			onBeforeSelectTabItem : function(tabId) {
				if ($("#" + tabId).attr("src") == "") {
					$("#" + tabId).attr("src", url + status[tabId]);
				}
			}
		});
		$("#nodeal").attr("src","pub/worktask/worktask_list.jsp?status=0");
	});
</script>
</head>
<body>
	<div id="navtab">
		<div title="未处理" tabId="nodeal">
			<iframe src="" id="nodeal"
				style="width: 100%; height: 100%; border: none;" scrolling="no"></iframe>
		</div>
		<div title="逾期" tabId="overdue">
			<iframe src="" id="overdue"
				style="width: 100%; height: 100%; border: none;" scrolling="no"></iframe>
		</div>
		<div title="已处理" tabId="finish">
			<iframe src="" id="finish"
				style="width: 100%; height: 100%; border: none;" scrolling="no"></iframe>
		</div>
	</div>
</body>
</html>
