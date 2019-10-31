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
        	sp_defaults:{columnWidth:0.3,labelAlign:'right',labelSeparator:'',labelWidth:120},	
	        sp_fields:[
	            {name:"name",compare:"like"},
	            {name:"org_name",compare:"like"},
	        ]
        };
            function getSelectedPerson(){
                if(Qm.getSelectedCount()!=1){
                    return null;
                }else{
                    return {
                        id: Qm.getValueByName("id"),
                        userid: Qm.getValueByName("userid"),
                        name: Qm.getValueByName("name"),
                        mobile_tel: Qm.getValueByName("mobile_tel"),
                        org_name: Qm.getValueByName("org_name"),
                        org_id: Qm.getValueByName("org_id"),
                        initial_degree: Qm.getValueByName("initial_degree"),
                        initial_education: Qm.getValueByName("initial_education"),
                        postSalaryZc: Qm.getValueByName("postsalaryzc"),
                        total: Qm.getValueByName("total"),
                        salactName: Qm.getValueByName("salactname"),
                        educationMoney: Qm.getValueByName("educationmoney"),
                        emp_title: Qm.getValueByName("emp_title"),
                        POSTSALARYZW: Qm.getValueByName("postsalaryzw"),
                        POSITION: Qm.getValueByName("position"),
                        jySj:Qm.getValueByName("hired_date")
                    };
                }
            }
        </script>
    </head>
    <body>
        <qm:qm pageid="sys_employee_list" singleSelect="true">
        </qm:qm>
    </body>
</html>