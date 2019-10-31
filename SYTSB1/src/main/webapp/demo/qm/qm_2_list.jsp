<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-list.jsp" %>
    <title>通用查询示例</title>
    <script type="text/javascript">
        var qmUserConfig = {
            tbar: [
                {text: "点击才查询", id: "search", icon: "search-list", click: function () {
                    Qm.searchData();
                }
                }
            ]
        };
    </script>
</head>
<body>
<qm:qm pageid="qm_01" sql="select * from qm_testtable where id<100" pagesize="30" seachOnload="false"
       singleSelect="true" showTitle="true">
    <qm:param name="str3" compare="=" value="1"/>
</qm:qm>
</body>
</html>