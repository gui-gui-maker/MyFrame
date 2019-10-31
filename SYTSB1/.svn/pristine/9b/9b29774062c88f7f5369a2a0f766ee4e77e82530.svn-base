<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="/k/kui-base-form.jsp"%>
<script src="${pageContext.request.contextPath}/pub/chart/js/color_exchange.js"></script>
<style type="text/css">
body {
	text-align: center;
}
</style>
</head>
<body>
	<p>
		<script type="text/javascript" language="javascript">
		<!--
			var ColorHex = new Array('00', '33', '66', '99', 'CC', 'FF')
			var SpColorHex = new Array('FF0000', '00FF00', '0000FF', 'FFFF00',
					'00FFFF', 'FF00FF')
			var current = null
			function initcolor(evt) {
				coloropen()
				var colorTable = ''
				for (i = 0; i < 2; i++) {
					for (j = 0; j < 6; j++) {
						colorTable = colorTable + '<tr height=15>'
						colorTable = colorTable
								+ '<td width=15 style="background-color:#000000">'
						if (i == 0) {
							colorTable = colorTable
									+ '<td width=15 style="cursor:pointer;background-color:#'
									+ ColorHex[j]
									+ ColorHex[j]
									+ ColorHex[j]
									+ '" onclick="doclick(this.style.backgroundColor)">'
						} else {
							colorTable = colorTable
									+ '<td width=15 style="cursor:pointer;background-color:#'
									+ SpColorHex[j]
									+ '" onclick="doclick(this.style.backgroundColor)">'
						}
						colorTable = colorTable
								+ '<td width=15 style="background-color:#000000">'
						for (k = 0; k < 3; k++) {
							for (l = 0; l < 6; l++) {
								colorTable = colorTable
										+ '<td width=15 style="cursor:pointer;background-color:#'
										+ ColorHex[k + i * 3]
										+ ColorHex[l]
										+ ColorHex[j]
										+ '" onclick="doclick(this.style.backgroundColor)">'
							}
						}
					}
				}
				colorTable = '<table border="1" cellspacing="0" cellpadding="0" style="border-collapse: collapse" bordercolor="000000" style="cursor:pointer;">'
						+ colorTable + '</table>';
				document.getElementById("colorpane").innerHTML = colorTable;
			}
			function doclick(obj) {
				//alert(obj);
				//var sRgb = "RGB(23, 245, 56)" , sHex = "#34538b";
				//var sRgbColor = sHex.colorRgb();
				var sHexColor = obj.colorHex();
				api.data.window.setColorValue(sHexColor.substr(1,sHexColor.length))
				api.close();
			}
			function colorclose() {
				document.getElementById("colorpane").style.display = "none";
			}
			function coloropen() {
				document.getElementById("colorpane").style.display = "";
			}
			window.onload = initcolor;
		</script>
	</p>
	<div id="colorpane" style="position:absolute;z-index:999;display:none;"></div>
</body>
</html>
