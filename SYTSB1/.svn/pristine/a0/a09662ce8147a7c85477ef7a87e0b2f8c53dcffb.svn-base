<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-list.jsp" %>
    <link rel="stylesheet" type="text/css" href="k/kui/skins/Aqua/css/ligerui-desktop.css"/>
    <script type="text/javascript">
        $(function () {
            $.getJSON("/infomanage/article/getsite.do", function (data) {
                var data = data.data;
                //alert(data.length)
                var inHtml = "";
                for (var i in data) {
                    //alert(i)
                    inHtml += '<fieldset class="fieldSet">';
                    inHtml += '<legend class="fieldLeg">' + data[i].name + '【文章统计】</legend>';
                    inHtml += '<table border=0 width="100%" cellpadding="0" cellspacing="0">';
                    inHtml += '<tr class="padding">';
                    inHtml += '<td align="center" style="font-weight:bold">机构</td>';
                    inHtml += '<td align="center" style="font-weight:bold">文章总数</td>';
                    inHtml += '<td align="center" style="font-weight:bold">已发布文章数</td>';
                    inHtml += '<td align="center" style="font-weight:bold">未发布文章数</td>';
                    inHtml += '</tr>';
                    $.ajax({
                        url:"/infomanage/article/Satisics.do", data:{"siteId":data[i].id},
                        async:false,
                        dataType:"json",
                        success:function (Iteimdata) {
                            //$.getJSON("/infomanage/article/Satisics.do", {"siteId":data[i].id}, function (Iteimdata) {
                            var Iteimdatadata = Iteimdata.data;
                            for (var k in Iteimdatadata) {
                                inHtml += '<tr class="padding">';
                                inHtml += '<td align="center">'+Iteimdatadata[k][0]+'</td>';
                                inHtml += '<td align="center">'+Iteimdatadata[k][1]+'</td>';
                                inHtml += '<td align="center">'+Iteimdatadata[k][2]+'</td>';
                                inHtml += '<td align="center">'+Iteimdatadata[k][3]+'</td>';
                                inHtml += '</tr>';
                            }
                        }})
                    inHtml += '</table>';
                    inHtml += '</fieldset>';
                }

                //alert(inHtml);
                $("#Satisics_result").append(inHtml);
            })
        })
    </script>
</head>
<body style="overflow:auto;">
<form name="form1" method="post">
    <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0" class="layww_tbl">
        <tr>
            <td align="left" id="Satisics_result"></td>
        </tr>
    </table>
</form>
</body>
</html>