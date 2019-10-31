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
        sp_fields:[
				{name:"card_no", compare:"like"},
				{name:"eq_archive_class_id", compare:"like"}, 
				{name:"eq_no", compare:"like"},
            	{name:"eq_name", compare:"like"},
            	{name:"eq_model", compare:"like"},
            	{name:"eq_sn", compare:"like"},
            	{group:[
						{name:"eq_buy_date", compare:">=", value:""},
						{label:"到", name:"eq_buy_date", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
					]},
				{name:"eq_user", compare:"like"},
				{name:"eq_use_department", compare:"like"},
            	{name:"eq_status", compare:"="},
				{name:"eq_inout_status", compare:"="},
				{name:"eq_check_cycle", compare:"like"},
				{name:"inventory", compare:"like"},
				{name:"BOX_NUMBER", compare:"like"},
				{name:"owner", compare:"="},
				{name:"position", compare:"like"},
				{group:[
					{name:"yuanzhi", compare:">=", value:""},
					{label:"到", name:"yuanzhi", compare:"<=", value:"", labelAlign:"center", labelWidth:20}
				]},
				{name:"remark", compare:"like"}
        ]
        };
            function getSelectedPerson(){
	             return {
	                 id: Qm.getValuesByName("id"),
	                 count: Qm.getSelectedCount()
	             };
            }
            
        </script>
    </head>
    <body>
		<qm:qm  pageid="TJY2_EQUIPMENT_LIST" singleSelect="false">
		<qm:param name="eq_category" value="01" compare="="/>
		</qm:qm>
    </body>
</html>