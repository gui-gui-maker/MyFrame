<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">

    </script>
</head>
<body>
    <form id="form1" action="list/fcp/save.do" getAction="list/fcp/detail.do?id=${param.id}">
       <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
		  		          <tr > 
        <td class="l-t-td-left">姓名</td>
        <td class="l-t-td-right"> 
        <input name="sysName" type="text" ltype='text' validate="{required:false,maxlength:40}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 费用报销费用:</td>
        <td class="l-t-td-right"> 
        <input name="averageF" type="text" ltype='text' validate="{required:true,maxlength:22}"/>
        </td>
        <td class="l-t-td-left"> 差旅费人均费:</td>
        <td class="l-t-td-right"> 
        <input name="averageC" type="text" ltype='text' validate="{required:true,maxlength:22}"/>
        </td>
       </tr>
       <tr> 
        <td class="l-t-td-left"> 培训费人均费:</td>
        <td class="l-t-td-right"> 
        <input name="averageP" type="text" ltype='text' validate="{required:true,maxlength:22}"/>
        </td>
        <td class="l-t-td-left"> 总金额:</td>
        <td class="l-t-td-right"> 
        <input name="total" type="text" ltype='text' validate="{required:true,maxlength:22}"/>
        </td>
       </tr>
      </table>
    </form> 




</div>

</body>
</html>
