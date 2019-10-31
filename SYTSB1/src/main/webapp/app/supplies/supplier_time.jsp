<%@ page import="java.util.Calendar" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@include file="/k/kui-base-form.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM");
    String endTime = sf.format(calendar.getTime());
    calendar.add(Calendar.MONTH,-2);
    String startTime = sf.format(calendar.getTime());
%>
<html>
<head>
    <title>导出</title>
    <script>
        $(function(){
        });

        function getSelectResult() {
        	var startTime = $("#startTime").val();
         	var endTime = $("#endTime").val();
         	var result = {'startTime':startTime,'endTime':endTime};
         	return result;
        }
    </script>
</head>
<body>
<div class="title-tm">
    <div class="l-page-title has-icon no-note">
        <div class="l-page-title-div"></div>
        <div class="l-page-title-text"><h1>请选择导出范围</h1></div>
        <div class="l-page-title-icon"><img src="k/kui/images/icons/32/places.png" border="0"></div>
    </div>
</div>
<div class="scroll-tm">
    <form id="expForm" action="">
        <table border="1" cellpadding="3" cellspacing="0" width="" class="l-detail-table">
            <input id="id" type="hidden" name="id" value="${param.id}">
            <tr style="height: 20px;">
                <td class="l-t-td-right">&nbsp;</td>
            </tr>
            <tr>
                <td class="l-t-td-left">开始时间</td>
                <td class="l-t-td-right">
                    <input id="startTime" type="text" ltype="date" ligerui="{format:'yyyy-MM'}" value="<%=startTime%>"/>
                </td>
                <td class="l-t-td-left">截止时间</td>
                <td class="l-t-td-right">
                    <input id="endTime" type="text" ltype="date" ligerui="{format:'yyyy-MM'}" value="<%=endTime%>"/>
                </td>
            </tr>
        </table>
    </form>
</div>
<!-- <div class="toolbar-tm"> -->
<!--     <div class="toolbar-tm-bottom"></div> -->
<!-- </div> -->
</body>
</html>