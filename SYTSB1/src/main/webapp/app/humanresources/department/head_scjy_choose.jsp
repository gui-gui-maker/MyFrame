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
	String sql="select e.*,eb.initial_education,eb.emp_title,eb.initial_degree, "+
    		   "(floor(months_between(SYSDATE,e.hired_date)/12)) gl, "+
		       "(floor(months_between(SYSDATE,e.birth_date)/12)) ygnl,o.org_name "+  
		       "from employee e,sys_org o,tjy2_rl_employee_base eb "+
		       "where e.org_id = o.id(+)  and e.id = eb.id and e.org_id = '100029' and eb.emp_status='4'"; 
	%>
    <head>
        <title>选择院领导</title>
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
                        name: Qm.getValueByName("name"),
                        mobile_tel: Qm.getValueByName("mobile_tel"),
                        org_name: Qm.getValueByName("org_name"),
                        org_id: Qm.getValueByName("org_id")
                        
                    };
                }
            }
        </script>
    </head>
    <body>
        <qm:qm pageid="sys_employee_list" singleSelect="true" sql="<%=sql %>">
        </qm:qm>
    </body>
</html>