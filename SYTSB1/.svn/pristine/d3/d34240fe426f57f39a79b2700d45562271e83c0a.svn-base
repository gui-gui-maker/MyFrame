<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head pageStatus="${param.pageStatus}">
<title>公式管理</title>
<%@include file="/k/kui-base-form.jsp"%>
<script type="text/javascript">
	var treeManager;
	$(function() {
		$(".layout").ligerLayout({
			leftWidth : 180
		});
		var formulaType = '';
		$.getJSON("pub/codetablevalue/getTextValue.do?code=pub_formula_type",function(response){
			
			for (var i=0;i<response.data.length;i++) {
				formulaType += "'" + response.data[i].id + "',";
			}
			
			treeManager = $("#typeTree").ligerTree({
				checkbox : false,
				selectCancel:false,
				data : response.data,
				onSelect : function(node) {
                    var rWindow = $("#formula_frame")[0].contentWindow;
                    rWindow.submitAction([ {
						name : "type_code",
						compare : "=",
						value : node.data.id
					}]);
				}
			});
			
			if (formulaType.length > 0) {
				formulaType = "( " + formulaType.substring(0, formulaType.length-1) + " )" ;
			}
			
			$("#formula_frame").attr("src","pub/formula/formula_list.jsp?formulaType=" + formulaType);
		});
		
		
		
	});
</script>
</head>
<body class="p5">
	<div class="layout">
		<div position="left" id="typeTree" title="公式类别"></div>
		<div position="center">
			<iframe id="formula_frame" name="item_frame" src=""
				width="100%" height="100%" frameborder="0"></iframe>
		</div>
	</div>
</body>
</html>
