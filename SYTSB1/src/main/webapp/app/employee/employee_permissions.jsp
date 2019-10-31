<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.status}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">
   /*  $(function() {
    	  $("#form1").initForm({
    		  getSuccess: function (response){
    			  $("#fkEmployeeId").val();
    		  }
    		  
    	  });
    }); */
   
    </script>
</head>
<body>
    <form id="form1" action="employeePermissionsAction/save.do" getAction="employeePermissionsAction/detailPermissions.do?id=${param.id}">
      <input type="hidden" name="id"/>
      <input type="hidden" name="fkEmployeeId" id="fkEmployeeId" value="${param.id}"/>
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr> 
        <td class="l-t-td-left"> 是否审核人:</td>
        <td class="l-t-td-right"><input type="radio" ltype="radioGroup" name="isAuditMan" 
        ligerui="{value:'1',data: [ { text:'是', id:'0' }, { text:'否', id:'1' }] }" /> 
        </td>
        <td class="l-t-td-left"> 分管设备类别:</td>
        <td class="l-t-td-right"><input type="text" name="auditDevice" id="auditDevice" ltype="select" 
        ligerui="{
				readonly:true,
				tree:{checkbox:true,data: <u:dict code="audit_device_permissions"/>}
				}"/>
        </td>
       </tr>
       <tr>
        <td class="l-t-td-left"> 设备类别审核范围:</td>
        <td class="l-t-td-right"><input type="text" name="auditDeviceScope" id="auditDeviceScope" ltype="select" 
        ligerui="{
				readonly:true,
				tree:{checkbox:true,data: <u:dict code="audit_device_permissions"/>}
				}"/>
        </td>
       
       </tr>
       <tr> 
        <td class="l-t-td-left"> 部门审核范围:</td>
        <td class="l-t-td-right" colspan="3"><input type="text" name="auditDepartmentScope" id="auditDepartmentScope" ltype="select" 
        ligerui="{
				readonly:true,
				tree:{checkbox:true,data: <u:dict code="audit_dep_permissions"/>}
				}"/>
        </td>
       
       </tr>
       <tr> 
        <td class="l-t-td-left"> 是否签发人:</td>
        <td class="l-t-td-right"> <input type="radio" ltype="radioGroup" name="isSign" 
        ligerui="{value:'1',data: [ { text:'是', id:'0' }, { text:'否', id:'1' }] }" /> 
        </td>
        <td class="l-t-td-left"> 签发范围:</td>
        <td class="l-t-td-right"> <input type="text" name="signScope" id="signScope" ltype="select" 
        ligerui="{
				readonly:true,
				tree:{checkbox:true,data: <u:dict code="sign_permissions"/>}
				}"/>
        </td>
        </tr>
         <tr>
        <td class="l-t-td-left"> 设备类别审核范围:</td>
        <td class="l-t-td-right"><input type="text" name="signScopeType" id="signScopeType" ltype="select" 
        ligerui="{
				readonly:true,
				tree:{checkbox:true,data: <u:dict code="audit_device_permissions"/>}
				}"/>
        </td>
       
       </tr>
      </table>
    </form> 




</div>

</body>
</html>
