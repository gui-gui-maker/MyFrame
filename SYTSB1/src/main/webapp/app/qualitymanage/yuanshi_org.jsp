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
            {name:"org_name",compare:"like",width:"150"}
        ]
        };
            function getSelectedPerson(){
                if(Qm.getSelectedCount()!=1){
                    return null;
                }else{
                    return {
                        id: Qm.getValueByName("id"),
                        name: Qm.getValueByName("org_name"),
                    };
                }
            }
        </script>
    </head>
    <body>
 <!--    机电1-6部 -->
        <qm:qm pageid="TJY2_CW_ORG_LIST" singleSelect="true">
         <qm:param name="id" value="100020" compare="=" logic="or"/> 
          <qm:param name="id" value="100021" compare="=" logic="or"/> 
           <qm:param name="id" value="100022" compare="=" logic="or"/> 
            <qm:param name="id" value="100023" compare="=" logic="or"/> 
             <qm:param name="id" value="100024" compare="=" logic="or"/> 
             <qm:param name="id" value="100063" compare="=" logic="or"/>
             
          <!--    承压1-5部 -->
              <qm:param name="id" value="100033" compare="=" logic="or"/> 
               <qm:param name="id" value="100034" compare="=" logic="or"/> 
                <qm:param name="id" value="100035" compare="=" logic="or"/> 
                 <qm:param name="id" value="100036" compare="=" logic="or"/> 
                  <qm:param name="id" value="100037" compare="=" logic="or"/> 
        </qm:qm>
    </body>
</html>