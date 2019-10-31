<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-list.jsp" %>
    <title>通用查询示例</title>
</head>
<body>
<div class="item-tm" isTitle="true">
    <div class="l-page-note">
        <div class="l-page-note-div">
            模板用于sql性能要求较高等特殊场合(由于通过查询是基于SQL分页的原理，原始SQL会经过再次包装，查询条件默认在最外层，效率较低，通过模板方式可以把条件直接加到原始SQL上)
        </div>
    </div>
</div>
<%
session.setAttribute("abc","alibaba");
%>
<qm:qm pageid="qm_03">
    <qm:attr name="id" value="100"/>
</qm:qm>
</body>
</html>