<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告纠正</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<%
			CurrentSessionUser user = SecurityUtil.getSecurityUser();
			String apply_user_name = user.getName();
			String dep_id = user.getDepartment().getId();
			//String dep_name = user.getDepartment().getOrgName();
			String data_status = request.getParameter("data_status");
		%>
		<script type="text/javascript">
        var qmUserConfig = {
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	// 默认值，可自定义
            sp_fields:[
            	{name:"sn", compare:"like"},
            	<%
            		if(!"0".equals(data_status)){
            			if("03".equals(data_status) || "05".equals(data_status) || "07".equals(data_status)){
            				%>
            				{name:"apply_dep_id", compare:"="},
            				<%
            			}
            			%>
            			{name:"apply_user_name", compare:"like"},
            			<%
            		}else{
            			// 100082：信息中心 100026：质量部 100027：业务服务部
            			if("100026".equals(dep_id) || "100027".equals(dep_id) || "100082".equals(dep_id)){
            				%>
            				{name:"apply_dep_id", compare:"="},
            				{name:"apply_user_name", compare:"like"},
            				<%
            			}
            		}
            	%>
            	{name:"report_sn", compare:"like"},
            	{name:"new_report_sn", compare:"like"},
            	{name:"report_status", compare:"like"}
            ],
            tbar:[
            	{ text:'详情', id:'detail',icon:'detail', click: detail}
				
            	<%
            		if("0".equals(data_status)){
            			if("100026".equals(dep_id) || "100027".equals(dep_id) || "100082".equals(dep_id)){	// 100082：信息中心 100026：质量部 100027：业务服务部
            			}else{
            				%>
            				//,"-",
    		                //{ text:'增加', id:'add',icon:'add', click: add},
    		                ,"-",
    		                { text:'修改', id:'modify',icon:'modify', click:modify},
	            			"-",
	            			{ text:'纠正', id:'app_deal',icon:'modify', click: app_deal}
                			<%
            			}
            		}else if("01".equals(data_status)){
            			%>
            			,"-",
            			{ text:'审核', id:'dep_head_check',icon:'modify', click: dep_head_check},
	            		"-",
	            		{ text:'纠正完成情况', id:'app_dep_finish',icon:'modify', click: app_dep_finish}
            			<%
            		}else if("03".equals(data_status)){
            			%>
            			,"-",
            			{ text:'审核', id:'qua_dep_check',icon:'modify', click: qua_dep_check}
            			<%
            		}else if("06".equals(data_status)){
            			%>
            			,"-",
            			{ text:'确认', id:'confirm',icon:'modify', click: confirm}
            			<%
            		}else if("09".equals(data_status)){
            			%>
            			,"-",
            			{ text:'纠正结果', id:'qua_finish',icon:'modify', click: qua_finish}
            			<%
            		}
            	%>
            ],
            listeners: {
                selectionChange : function(rowData,rowIndex){
                	selectionChange();
                },
        		rowDblClick :function(rowData,rowIndex){
        			detail(rowData.id);
        		}
            }
        };
        
        // 行选择改变事件
		function selectionChange() {
	   		var count = Qm.getSelectedCount(); // 行选择个数
	   		<%
            	if("0".equals(data_status)){
            		%>
            		if(count == 1){
			            Qm.setTbar({add: true, modify: true, del: true, detail: true, app_deal: true});            	
			 		}else if(count > 1){
			       		Qm.setTbar({add: true, modify: false, del: true, detail: false, app_deal: false});
			    	}else{
			    		Qm.setTbar({add: true, modify: false, del: false, detail: false, app_deal: false});
			    	}
            		<%
            	}else if("01".equals(data_status)){
        			%>
        			if(count == 1){
			            Qm.setTbar({dep_head_check: true, app_dep_finish: true, detail: true});            	
			 		}else if(count > 1){
			       		Qm.setTbar({dep_head_check: false, app_dep_finish: false, detail: false});
			    	}else{
			    		Qm.setTbar({dep_head_check: false, app_dep_finish: false, detail: false});
			    	}
        			<%
        		}else if("03".equals(data_status)){
        			%>
        			if(count == 1){
			            Qm.setTbar({qua_dep_check: true, detail: true});            	
			 		}else if(count > 1){
			       		Qm.setTbar({qua_dep_check: false, detail: false});
			    	}else{
			    		Qm.setTbar({qua_dep_check: false, detail: false});
			    	}
        			<%
        		}else if("06".equals(data_status)){
        			%>
        			if(count == 1){
			            Qm.setTbar({confirm: true, detail: true});            	
			 		}else if(count > 1){
			       		Qm.setTbar({confirm: false, detail: false});
			    	}else{
			    		Qm.setTbar({confirm: false, detail: false});
			    	}
        			<%
        		}else if("07".equals(data_status)){
        			%>
        			if(count == 1){
			            Qm.setTbar({qua_finish: true, detail: true});            	
			 		}else if(count > 1){
			       		Qm.setTbar({qua_finish: false, detail: false});
			    	}else{
			    		Qm.setTbar({qua_finish: false, detail: false});
			    	}
        			<%
        		}
        	%>
		}
	
        
        function detail(id){
			if($.type(id)!="string"){
				id = Qm.getValueByName("id").toString();
			}
			top.$.dialog({
				width:800,
				height:500,
				lock:true,
				title:"查看详情",
				content: 'url:app/report/report_cancel_detail.jsp?status=detail&id=' + id,
				data:{window:window},
				cancel:true
			});
		}
        
        //新增
        function add(){
			top.$.dialog({
				width: 800,
				height: 500,
				lock:true,
				title:"新增",
				data: {window:window},
				content: 'url:app/report/report_cancel_detail.jsp?status=add'
			});
        }
        
        //修改
        function modify(){
        	var data_status = Qm.getValueByName("data_status").toString();
        	if(data_status.indexOf("已提交纠正申请")==-1 && data_status.indexOf("质保工程师审核未通过")==-1){
        		$.ligerDialog.alert("该纠正申请正在流转中或已纠正完成，不能修改，请核查！");
        		return;
        	}
			top.$.dialog({
				width: 800,
				height: 500,
				lock:true,
				title:"修改",
				data: {window:window},
				content: 'url:app/report/report_cancel_detail.jsp?status=modify&id='+Qm.getValueByName("id")
			});
        }    
        
        // 责任部门负责人审核纠正申请
        function dep_head_check(){
        	var data_status = Qm.getValueByName("data_status").toString();
        	if(data_status.indexOf("已提交纠正申请")==-1){
        		$.ligerDialog.alert("该纠正申请已审核过，正在流转中，请核查！");
        		return;
        	}
			top.$.dialog({
				width: 800,
				height: 500,
				lock:true,
				title:"审核纠正申请",
				data: {window:window},
				content: 'url:app/report/report_cancel_detail.jsp?status=modify&type=1&id='+Qm.getValueByName("id")
			});
        }
        
        // 质保工程师审核纠正申请
        function qua_dep_check(){
			top.$.dialog({
				width: 800,
				height: 500,
				lock:true,
				title:"审核纠正申请",
				data: {window:window},
				content: 'url:app/report/report_cancel_detail.jsp?status=modify&type=3&id='+Qm.getValueByName("id")
			});
        }
        
        // 责任人纠正
        function app_deal(){
        	var data_status = Qm.getValueByName("data_status").toString();
        	if(data_status.indexOf("已提交纠正申请")!=-1){
        		$.ligerDialog.alert("请先等部门负责人审核，待质保工程师审核通过后再操作！");
        		return;
        	}
        	if(data_status.indexOf("质保工程师已审核通过")!=-1){
				top.$.dialog({
					width: 800,
					height: 500,
					lock:true,
					title:"纠正完成情况",
					data: {window:window},
					content: 'url:app/report/report_cancel_detail.jsp?status=modify&type=4&id='+Qm.getValueByName("id")
				});
			}else{
				alert(data_status);
			}
        }
        
        // 业务服务部确认纠正措施完成情况
        function confirm(){
        	top.$.dialog({
				width : 500, 
				height : 200, 
				lock : true, 
				title:"确认纠正措施完成情况",
				parent:api,
				content: 'url:app/report/report_cancel_confirm.jsp?id='+Qm.getValueByName("id"),
				data : {"window" : window}
			});
        }
        
        // 责任部门负责人纠正完成情况
        function app_dep_finish(){
        	var data_status = Qm.getValueByName("data_status").toString();
        	if(data_status.indexOf("业务服务部已确认")!=-1){
				top.$.dialog({
					width: 800,
					height: 500,
					lock:true,
					title:"纠正完成情况",
					data: {window:window},
					content: 'url:app/report/report_cancel_detail.jsp?status=modify&type=5&id='+Qm.getValueByName("id")
				});
			}else{
	        	$.ligerDialog.alert("请先等待业务服务部确认后，再提交纠正完成情况！");
	        	return;
			}
        }
        
        // 质量监督管理部纠正结果
        function qua_finish(){
        	top.$.dialog({
				width : 500, 
				height : 200, 
				lock : true, 
				title:"纠正结果",
				parent:api,
				content: 'url:app/report/report_cancel_finish.jsp?id='+Qm.getValueByName("id"),
				data : {"window" : window}
			});
        }
        
        
        // 刷新Grid
        function refreshGrid() {
            Qm.refreshGrid();
        }
        
        function parseErrorFrom(value){
        	if("1" == value){
        		return "质量抽查";
        	}else if("2" == value){
        		return "部门自查";
        	}else if("3" == value){
        		return "内部审核";
        	}else{
        		return "外部输入："+value;
        	}
        }
        
        function parseConfirmResult(value){
        	if(value.indexOf(",")==-1){
        		if("1" == value){
	        		return "原报告号作废";
	        	}else if("2" == value){
	        		return "原报告盖作废章";
	        	}else if("3" == value){
	        		return "追回报告销毁";
	        	}else{
	        		return "";
	        	}
        	}else{
        		var returnStr = "";
        		var arr = value.split(",");
        		for(var i = 0 ; i < arr.length ; i++){
        			if(returnStr!=""){
        				returnStr += "；";
        			}
        			if("1" == arr[i] || 1 == arr[i]){
		        		returnStr += "原报告号作废";
		        	}else if("2" == arr[i] || 2 == arr[i]){
		        		returnStr += "原报告盖作废章";
		        	}else if("3" == arr[i] || 3 == arr[i]){
		        		returnStr += "追回报告销毁";
		        	}
        		}
        		return returnStr;
        	}
        }
    </script>
	</head>
	<body>	
		<qm:qm  pageid="report_cancel_list" script="false" singleSelect="false">
			<c:choose>
				<c:when test="${'0' != param.data_status}">
					<c:choose>
						<c:when test="${'01' eq param.data_status}">
							<qm:param name="apply_dep_id" compare="=" value="<%=dep_id %>"/>		
							<qm:param name="data_status" compare="in" value="('04','07','${param.data_status}')" dataType="user"/>	
						</c:when>
						<c:otherwise>
							<qm:param name="data_status" compare="=" value="${param.data_status}"/>
						</c:otherwise>
					</c:choose>
				</c:when>	
				<c:otherwise>
					<%
						if(!"100027".equals(dep_id) && !"100026".equals(dep_id) && !"100082".equals(dep_id)){	// 100082：信息中心 100026：质量部，100027：业务服务部
							%>
							<qm:param name="apply_user_name" compare="=" value="<%=apply_user_name %>"/>
							<%
						}
					%>
				</c:otherwise>
			</c:choose>	
		</qm:qm>
		<script type="text/javascript">
//    根据 sql或码表名称获得Json格式数据
Qm.config.columnsInfo.apply_dep_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
</script>
	</body>
</html>
