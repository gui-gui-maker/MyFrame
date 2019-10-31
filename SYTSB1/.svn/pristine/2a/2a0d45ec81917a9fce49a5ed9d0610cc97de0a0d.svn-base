<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}" id="ak">
    <title></title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript">
        $(function () {	
			$("#form1").initForm({    
				success: function (res) {
					if(res.success){
						if(res.data==true){
							top.$.notice("保存成功！",3);
							//alert(JSON.stringify(res));
							api.data.window.refreshTree(res);
							api.data.window.changeToolbarStatus();
							api.close();
						} else{
							$.ligerDialog.warn('类别名不能一样');
						} 	
					}
				}
			});
		});
    </script>
</head>
<body>
 <form id="form1" action="rbac/pubAddressType/mySavePublic.do"  getAction="rbac/pubAddressType/detail.do?id=${param.id}"       > 
 <input type="hidden" id="id" name="id" >
    <table cellpadding="3" class="l-detail-table">
           <tr>
            <td class="l-t-td-left">类别：</td>
            <td class="l-t-td-right"><input name="name" type="text" ltype="text" validate="{required:true,maxlength:20}"  /></td>
           </tr>
<%--    <c:if test="${param.pageStatus=='detail'}">
            <tr>
            <td class="l-t-td-left">创建人：</td>
            <td class="l-t-td-right" >
               <input type="text" id="createby" name="createby" ltype="select" validate="{required:true}" ligerui="{
				 //value:'1',
				 readonly:true,
				//disabled:true,
			   data:<u:dict  sql='select id ,name from sys_user' > </u:dict>
			  }"/>
            </td> 
           </tr>
           <tr>
            <td class="l-t-td-left">类型：</td>
            <td class="l-t-td-right">
              <input type="text" id="type" name="type" ltype="select" validate="{required:true}" ligerui="{
				 value:'1',
				 readonly:true,
				//disabled:true,
			   data:[{text:'公共',id:'1'}]
			   }"/>
            </td>
           </tr>
         </c:if> --%>
         <c:if test="${param.pageStatus=='add'}">
              <input type="hidden"  name="createby" value="1">
              <input type="hidden"  name="type"  value="1">
         </c:if>
            <tr>
            <td class="l-t-td-left">排序：</td>
            <td class="l-t-td-right"><input name="sort" type="text" ltype="text" validate="{required:true,maxlength:6,digits:true}"    />  </td>
           </tr>
    </table>    
</form>
</body>
</html>
