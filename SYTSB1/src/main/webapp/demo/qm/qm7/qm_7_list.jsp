<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>通用查询示例</title>
    <%@include file="/k/kui-base-form.jsp" %>
    <script type="text/javascript" src="demo/qm/qm7/kkpager.min.js"></script>
    <link rel="stylesheet" type="text/css" href="demo/qm/qm7/kkpager.css"/>
    <script type="text/javascript">
        $(getData);
        function getData() {
            var data = {page: 1, pagesize: pageSize, start: 0,_method:"q"};
            $.post(url, data, function(res){
                var totalPage = parseInt((res.total  +  pageSize  - 1) / pageSize);
                var totalRecords=res.total;
                first=false;
                initPager(totalPage,totalRecords);
                parseData(res);
            },"json");
        }
        var url="qm?pageid=qm_01";
        var pageSize=10;
        var first=true;
        function initPager(totalPage,totalRecords){
//            var totalPage = 20;
//            var totalRecords = 390;
            var pageNo = 1;
            //生成分页
            //有些参数是可选的，比如lang，若不传有默认值
            kkpager.generPageHtml({
                pno : pageNo,
                //总页码
                total : totalPage,
                //总数据条数
                totalRecords : totalRecords,
                mode : 'click',//默认值是link，可选link或者click
                click : function(n){
                    var start=(n-1)*pageSize;
                    var data = {page: 1, pagesize: pageSize, start: start,_method:"q"};
                    $.post(url, data, parseData,"json");
                    if(!first){
                        this.selectPage(n);
                    }
                    return false;
                }
            });
        }
        function parseData(data) {
            var tbody=$("#dataTable tbody").empty();
            if (data.rows.length > 0) {
                for (var i in data.rows) {
                    var row=data.rows[i];
                    tbody.append("<tr><td>"+row["id"]+"</td><td>"+row["str1"]+"</td><td>"+row["num1"]+"</td><td>"+row["date1"]+"</td></tr>")
                }
            }
        }
    </script>
    <style type="text/css">
        .airlist_second {
            border-bottom: 1px solid #A9D5E2;
            border-right: 1px solid #A9D5E2;
            margin-left: 10px;
            width: 740px;
        }
        .airlist_second .headtd {
            background: none repeat scroll 0 0 #CCE9F1;
            text-align: center;
        }
        .airlist_second td {
            border-left: 1px solid #A9D5E2;
            border-top: 1px solid #A9D5E2;
        }
    </style>
</head>
<body>
<div class="item-tm" isTitle="true">
        <div class="l-page-note-div">
    <div class="l-page-note">
            该页面演示自己解析数据
        </div>
    </div>
</div>

<div id="content" class="scroll-tm">
<table id="dataTable" class="airlist_second" cellSpacing="0" cellPadding="0" width="80%">
<thead>
<tr>
    <td class="headtd">
        id
    </td>
    <td class="headtd">
        str1
    </td>
    <td class="headtd">
        num1
    </td>
    <td class="headtd">
        date1
    </td>
</tr>
</thead>

<tbody>
</tbody>
</table>
<div id="kkpager"></div>
</div>
</body>
</html>