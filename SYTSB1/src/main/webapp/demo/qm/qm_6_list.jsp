<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@include file="/k/kui-base-form.jsp" %>
    <title>通用查询示例</title>
    <script type="text/javascript">
        $(getData);
        function getData() {
            var data = {page: $("#page").val(), pagesize: $("#pagesize").val(), start: $("#start").val()};
            $.getJSON("qm?_method=q&pageid=qm_01", data, parseData);
        }
        var index = 0;
        function parseData(data) {
            $("#content div").animate({opacity: 'hide'}, "slow");
            $("#content").empty();
            if (data.rows.length > 0) {
                index = data.rows.length;
                for (var j in data.rows) {
                    $("#content").append("<div class='tt' id='div" + j + "' style='display:none'>" +
                            "<table>" +
                            "<tr><td>ID:</td><td>" + data.rows[j]["id"] + "</td></tr>" +
                            "<tr><td>Str1:</td><td>" + data.rows[j]["str1"] + "</td></tr>" +
                            "<tr><td>Num1:</td><td>" + data.rows[j]["num1"] + "</td></tr>" +
                            "<tr><td>Date1:</td><td>" + data.rows[j]["date1"] + "</td></tr>" +
                            "</table></div>");
                }
                animate(0);
            }
        }
        function animate(i) {
            if (i < index) {
                $("#div" + i).animate({opacity: 'show'}, "normal", function () {
                    animate(++i)
                });
            }
        }
        function page(i) {
            var page = parseInt($("#page").val());
            $("#page").val(page + i);
            $("#start").val(page * $("#pagesize").val());
            getData();
        }
    </script>
    <style type="text/css">
        .tt {
            float: left;
            background-color: #e4fcff;
            margin: 5px;
            padding: 5px;
            width: 200px;
            height: 80px;
        }
    </style>
</head>
<body>
<div class="item-tm" isTitle="true">
    <div class="l-page-note">
        <div class="l-page-note-div">
            该页面演示自己解析数据
            page:<input id="page" value="1"/>
            pagesize:<input id="pagesize" value="20"/>
            start:<input id="start" value="0"/>
            <input type="button" value="pre" onclick="page(-1)"/>
            <input type="button" value="next" onclick="page(1)"/>
            <input type="button" value="search" onclick="getData()"/>
        </div>
    </div>
</div>

<div id="content" class="scroll-tm">

</div>
</body>
</html>