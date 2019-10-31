<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.rbac.impl.bean.User"%>
<%
CurrentSessionUser user = SecurityUtil.getSecurityUser();
User uu = (User)user.getSysUser();
com.khnt.rbac.impl.bean.Employee e = (com.khnt.rbac.impl.bean.Employee)uu.getEmployee();
%>
<%@page import="com.khnt.utils.StringUtil"%>
<%@ taglib uri="http://khnt.com/tags/ui" prefix="u" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>报告信息查询列表</title>
		<%@include file="/k/kui-base-list.jsp"%>
		<%
			String user_id = e.getId();
			String org_id = user.getDepartment().getId();
			String org_code = user.getDepartment().getOrgCode();
			String enter_op_id = request.getParameter("enter_op_id");
		%>
		<script type="text/javascript">
			var qmUserConfig = {
				sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
	         	sp_fields:[
	         		{name:"device_registration_code", id:"device_registration_code", compare:"like"},
	         		{name:"report_sn", id:"report_sn", compare:"like"},
	         		{name:"report_com_name",id:"report_com_name",compare:"like"}
	         		<%
	         			if(org_code.startsWith("ziliang")){
	         				%>
	         				,{name:"check_unit_id", id:"check_unit_id", compare:"="}
	         				,{name:"enter_op_name", id:"enter_op_name", compare:"like"}
	         				<%
	         			}
	         		%>
	            ],
	            tbar:[
			        { text:'复制', id:'select',icon:'check',click:selectValue},
			        "-",
			        { text:'关闭', id:'close',icon:'modify',click:close}
	            ],
	            listeners: {
	                selectionChange : function(rowData,rowIndex){
	                	var count=Qm.getSelectedCount();//行选择个数
	                    Qm.setTbar({select:count>0});
	                }
	            }
	        };
			
			function selectValue(){	
				api.data.window.callBack(Qm.getValuesByName("id").toString());
				api.close();
			}
			
			function close(){
				api.close();
			}
        </script>
	</head>
	<body>
		<qm:qm pageid="report_query_list42" singleSelect="false">
		<%
			if(org_code.startsWith("cy")){
				if(StringUtil.isNotEmpty(org_id)){
					%>
					<qm:param name="check_unit_id" value="<%=org_id %>" compare="=" />
					<%
				}
				if(StringUtil.isNotEmpty(user_id)){
					%>
					<qm:param name="enter_op_id" value="<%=user_id %>" compare="=" />
					<%
				}
			}else if(org_code.startsWith("jd")){
				if(StringUtil.isNotEmpty(org_id)){
					%>
					<qm:param name="check_unit_id" value="<%=org_id %>" compare="=" />
					<%
				}
				if(StringUtil.isNotEmpty(user_id)){
					%>
					<qm:param name="check_op_id" value="<%=user_id %>" compare="like" />
					<%
				}
			}
			if(StringUtil.isNotEmpty(enter_op_id)){
				if(org_code.startsWith("jd")){
					%>
					<qm:param name="check_op_id" value="<%=enter_op_id %>" compare="like" />
					<%
				}else{
					%>
					<qm:param name="enter_op_id" value="<%=enter_op_id %>" compare="=" />
					<%
				}
				%>
				<%
			}
		%>
		</qm:qm>
		<script type="text/javascript">
		// 根据 sql或码表名称获得Json格式数据
		// Qm.config.columnsInfo.report_id.binddata=<u:dict sql="select id,report_name from base_reports "></u:dict>;
		<%
	   		if(org_code.startsWith("ziliang")){
	        	%>
	        	Qm.config.columnsInfo.check_unit_id.binddata=<u:dict sql="select id code, ORG_NAME text from SYS_ORG where ORG_CODE like 'jd%' or ORG_CODE like 'cy%' order by orders "></u:dict>;
	    		<%
	    	}
		%>
		</script>
	</body>
</html>