<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>业务规则公式设计器</title>
<%@include file="/k/kui-base-form.jsp"%>
<link type="text/css" rel="stylesheet" href="pub/formula/css/formula.css" />
<script type="text/javascript">
    $(function () {
		$("#layout1").ligerLayout({
			height : "100%",
			topHeight : 200,
			bottomHeight : 200,
			allowTopCollapse : false,
			space : 0
		});
		$.getJSON("pub/formula/detail.do?id=${param.id}",function(response){
			if(response.success){
				$("#desc").html(response.data.desc);
				$("#code").val(response.data.code);
			}
		});
    });
</script>
<style type="text/css">
	textarea{background-color: lemonchiffon;}
</style>
</head>
<body>
	<div id="layout1">
		<div position="top" id="desc" style="height:100%;padding:5px;font-size:4mm;background-color:lemonchiffon;border: 1px solid #BED5F3;"></div>
		<div position="bottom" style="height:100%">
			<textarea style="height:100%;padding:5px;font-size: 4mm;background-color:lemonchiffon;border:0;border-top: 1px solid #BED5F3;" id="code" readonly="readonly"></textarea>
		</div>
	</div>
</body>
</html>