<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 12-8-10
  Time: 上午11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://khnt.com/tags/qm" prefix="qm" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>通用查询</title>
    <%@include file="/k/kui-base-list.jsp"%>
    <script test="text/javascript">
        var qmUserConfig = {
//            //自定义查询面板样式参数
            title:"不通过原因"
//            sp_defaults:{columnWidth:0.2,labelAlign:'top',labelSeparator:'',labelWidth:100},// 可以自己定义
//            自定义查询面板查询字段

//

////
        };


    </script>
</head>
<body>
<qm:qm pageid="hr_zp_003" singleSelect="true" >
    <qm:param name="fk_id" compare="=" value="${param.id}" logic="and"/>
    <qm:param name="yw_id" compare="=" value="${param.ywId}"/>
</qm:qm>
</body>
</html>