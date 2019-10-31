<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>业务流程配置选择</title>
<%@include file="/k/kui-base-list.jsp"%>
<script type="text/javascript">
	$(function(){
		var dataRows = api.data.data;
		/* if(dataRows){
			for(var i in dataRows){
				dataRows[i].orgName = dataRows[i].org.orgName;
			}
		} */
		$("#dataGrid").ligerGrid({
			columns: [ 
				{ display: '流程ID', name: 'flowId',hide:1},
				{ display: '业务编码', name: 'serviceCode', align: 'left', width: 120 }, 
				{ display: '流程名称', name: 'flowName', minWidth: 180 }, 
				{ display: '单位', name: 'orgName', minWidth: 190},
				{ display: '描述', name: 'remark', minWidth: 200}
			],
			width: '100%',
			height:'100%',
			data:{Rows:dataRows}
		});
	});

	//确定选择
	function getSelect() {
		var grid = $('#dataGrid').ligerGetGridManager ();
		return grid.getSelected();
	}
</script>
</head>
<body>
	<div id="dataGrid"></div>
</body>
</html>
