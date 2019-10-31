<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="q"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>通用查询</title>
<%@include file="/k/kui-base-list.jsp"%>
<script test="text/javascript">
	var qmUserConfig = {
		sp_fields : [ {
			name : "name",
			compare : "like"
		}, {
			name : "target_object",
			compare : "like"
		} ],
		tbar : [ {
			text : '查看',
			id : 'import1',
			icon : 'detail',
			click : detail
		}, {
			text : '添加',
			id : 'import2',
			icon : 'add',
			click : add
		} ]
	};
	function add() {
		top.$.dialog({
			width : 450,
			height : 250,
			lock : true,
			title : "添加任务",
			content : "url:demo/job/job_detail.jsp?status=add"
		});
	}
	function detail() {
		top.$.dialog({
			width : 450,
			height : 250,
			lock : true,
			title : "查看任务",
			content : "url:demo/job/job_detail.jsp?status=detail&id=" + Qm.getValueByName("id")
		});
	}
</script>
</head>
<body>
	<q:qm pageid="pub_jobs" />
</body>
</html>