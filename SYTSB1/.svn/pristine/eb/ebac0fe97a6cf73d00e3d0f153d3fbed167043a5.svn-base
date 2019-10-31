<%@ page contentType="text/html;charset=UTF-8"%>
<%@page import="com.khnt.security.CurrentSessionUser"%>
<%@page import="com.khnt.security.util.SecurityUtil"%>
<%@page import="com.lsts.employee.service.EmployeesService"%>
<%@page import="com.khnt.rbac.impl.bean.Employee"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
 <%
    CurrentSessionUser user = SecurityUtil.getSecurityUser();
    String org_id = user.getDepartment().getId();
%>
    <head>
        <title>选择部门</title>
        <%@include file="/k/kui-base-list.jsp"%>
        <script type="text/javascript" src="pub/bpm/js/util.js"></script>
        <script type="text/javascript">
        var orgid=<sec:authentication property="principal.unit.id" />
        var qmUserConfig = {
        		sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
        sp_fields:[
            {name:"org_name",compare:"like"}
        ]
        };
            function getSelectedPerson(){
	             return {
	                 id: Qm.getValuesByName("id"),
	                 name: Qm.getValuesByName("org_name"),
	             };
            }
        </script>
    </head>
    <body>
        <qm:qm pageid="TJY2_CW_ORG_LIST" singleSelect="false" pagesize="50">
         <qm:param name="parent_id" value="${param.par_id}" compare="=" logic="and"/> 
        </qm:qm>
    </body>
</html>