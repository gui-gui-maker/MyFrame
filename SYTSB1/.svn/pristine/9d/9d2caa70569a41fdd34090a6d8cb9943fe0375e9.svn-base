<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%@ include file="/k/kui-base-list.jsp"%>
<title>印章展示</title>
<script type="text/javascript">
    var qmUserConfig = {
    		sp_defaults:{columnWidth:0.33,labelAlign:'right',labelSeparator:'',labelWidth:120},//可以自己定义 layout:column,float,
    		sp_fields : [
						{name : "seal_serial_number", compare : "like"},
						{name:"title", compare:"like"},
						{name:"status", compare:"like"},
    			{group: [
    							{name: "start_usingtime", compare: ">="},
    							{label: "到", name: "start_usingtime", compare: "<=", labelAlign: "center", labelWidth: 20}
    							]}, 					 
				{group: [
	  							{name: "stop_usingtime", compare: ">="},
	  							{label: "到", name: "stop_usingtime", compare: "<=", labelAlign: "center", labelWidth: 20}
	  							]},  
    	        ],}
    </script>	    
</head>
<!-- <div id="content" class="scroll-tm">
</div> -->
<body>	
	<qm:qm  pageid="TJY2_YZ_YZZS" singleSelect="true"></qm:qm>
</body>
</html>