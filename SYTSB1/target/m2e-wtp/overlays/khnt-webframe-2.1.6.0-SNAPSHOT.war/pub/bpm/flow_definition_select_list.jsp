<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>工作流程查询</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	function getSelect() {
		return {
			code : Qm.getValueByName("id"),
			name : Qm.getValueByName("flowname")
		};
	}

	//指定条件加载数据
	function loadGridData(flowtype) {
		Qm.setCondition({name:"flowtype",compare:"=",value:flowtype});
		Qm.searchData();
	}
</script>
</head>
<body style="padding: 0; margin: 0;">
	<qm:qm pageid="bpm_3" script="false" singleSelect="false" />
</body>
</html>
