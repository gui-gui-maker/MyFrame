<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="com.khnt.security.CurrentSessionUser"%>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
    CurrentSessionUser user=(CurrentSessionUser)request.getSession().getAttribute("currentSessionUser");

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
   
        $(function () {
//          如果不设置额外参数，不用调用此方法，初始化时会自动调用
         $("#form1").initForm({    //参数设置示例
				success: function (response) {
					if(response.success){
						top.$.notice("保存成功！",3);
						api.data.window.Qm.refreshGrid();
						api.close();
					}
					else{
						$.ligerDialog.error("操作失败！<br/>" + response.msg);
					}
				}
			});
      
        	
        });

    </script>
</head>
<body>


     <form id="form" action="finance/importSalaryAction/saveim.do" getAction="finance/importSalaryAction/detail.do">
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
       <tr> 
        <td class="l-t-td-left"> 合计</td>
        <td class="l-t-td-right"> 
        <input name="totalWages" type="text" ltype='text' validate="{required:true,maxlength:22}"/>
        </td>
        <td class="l-t-td-left"> 操作人</td>
        <td class="l-t-td-right"> 
        <input name="importCerate" type="text" ltype='text' validate="{required:true,maxlength:20}"/>
        </td>
       </tr>
       <tr>
       <td class="l-t-td-left">月份/工资</td>
       <td class="l-t-td-right"> <input  validate="{required:true,maxlength:32}" name="salartTime" type="text" ltype="date" validate="{required:false}"ligerui="{initValue:'',format:'yyyy-MM'}"  onchange="setValues(this.value,'advance_time')"/></td>
       </tr>
      </table>
    </form> 
</body>
</html>
