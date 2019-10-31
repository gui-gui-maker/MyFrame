<%@ page contentType="text/html;charset=UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<title>工作组</title>
<script type="" src="app/gis/scjy/v5/js/jquery.min.js"></script>
<script type="text/javascript">
var summaryDetails=[];
var rows = []; 
$(function(){
	getData();
	/* setInterval(function(){
		getData();
	}, 20000); */
});

function getData(){
	$.post("cactiUtil/getData.do",
			{
				local_graph_id:168,
				graph_start:1531362600,
				graph_end:1531364400
			},
			function(data){
				//百度那边的 数据已经回来，我现在要解析这个数据.
				alert(222);
				var htm = data.data;
				htm.indexOf("<script");
				htm.indexOf()
				htm = htm.replace("/<script/")
				$("#data").html(data.data);
				alert(111);
				alert($("#data table").get()[0]);
				/* $("table .cactiTable").eq(0).find("tr").each(function(){
					var summaryRow = $(this).finf("td").html();
					console.log(summaryRow);
				}); */
			});
}
</script>
</head>
<body>
	<div id="data"></div>
</body>
</html>
