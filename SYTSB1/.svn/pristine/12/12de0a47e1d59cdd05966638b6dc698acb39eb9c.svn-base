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
        <title>选择人员</title>
        <%@include file="/k/kui-base-list.jsp"%>
        <script type="text/javascript" src="pub/bpm/js/util.js"></script>
        <script type="text/javascript">
        function getSelectedPerson(){
            if(Qm.getSelectedCount()!=1){
                return null;
            }else{
                return {
                    id: Qm.getValueByName("id"),
                    name: Qm.getValueByName("name")
                };
            }
        }
        </script>
    </head>
    <body>
        <qm:qm pageid="user_sel" singleSelect="true">
         <qm:param name="org_id" value="${param.org_id}" compare="="/> 
        </qm:qm>
    </body>
</html>