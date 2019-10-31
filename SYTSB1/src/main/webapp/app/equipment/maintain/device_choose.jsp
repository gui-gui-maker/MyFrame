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
        var orgid=<sec:authentication property="principal.unit.id" />
        var qmUserConfig = {
        sp_defaults : {
            columnWidth : 3.3
        }, 
        sp_fields:[
            {name:"eq_name",compare:"like",width:"150"}
        ]
        };
            function getSelectedPerson(){
                if(Qm.getSelectedCount()!=1){
                    return null;
                }else{
                    return {
                        id: Qm.getValueByName("id"),
                        name: Qm.getValueByName("eq_name"),
                        eq_no: Qm.getValueByName("eq_no"),
                        eq_model: Qm.getValueByName("eq_model"),
                        eq_accurate: Qm.getValueByName("eq_accurate"),
                        eq_value: Qm.getValueByName("eq_value"),
                        eq_buy_date: Qm.getValueByName("eq_buy_date"),
                    };
                }
            }
        </script>
    </head>
    <body>
        <qm:qm pageid="TJY2_EQUIPMENT_LIST" singleSelect="true">
         <%-- <qm:param name="eq_status" value="01" compare="in" />
         <qm:param name="eq_use_status" value="(01,03)" compare="in" />
         <qm:param name="box_status" value="02" compare="in" /> --%>
     <%--     <qm:param name="eq_inout_status" value="01" compare="in" /> --%>
        </qm:qm>
    </body>
</html>