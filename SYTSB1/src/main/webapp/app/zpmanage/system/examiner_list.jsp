<%@ page contentType="text/html;charset=UTF-8"%>
<%
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>考官列表页面</title>
    <%@ include file="/k/kui-base-list.jsp"%>
    <script type="text/javascript">
        function getSelected()
        {
            return Qm.getValuesByName("id");
        }
    </script>
</head>
<body>
<qm:qm pageid="hr_zp_009" script="false" singleSelect="false">
</qm:qm>

</body>
</html>