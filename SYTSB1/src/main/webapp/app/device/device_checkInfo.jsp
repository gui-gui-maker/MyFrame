<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.utils.StringUtil"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
	<script type="text/javascript">
	var qmUserConfig = {
		
		tbar : [
		        { text:'流转过程', id:'flow_note',icon:'follow', click: getFlow}
		]
	
	
	};
	
	
	//流转过程
	function  getFlow(){
		
		 top.$.dialog({
	   			width : 400, 
	   			height : 700, 
	   			lock : true, 
	   			title: "流程卡",
	   			content: 'url:department/basic/getFlowStep.do?ins_info_id='+Qm.getValuesByName("id"),
	   			data : {"window" : window}
	   		});
	
		
	}
</script>
	</head>
	<body>
		<qm:qm pageid="checkInfo" script="false" singleSelect="true">
		
			<qm:param name="fk_tsjc_device_document_id" value="${param.id}" compare="=" />
						
				
		</qm:qm>
	</body>
</html>