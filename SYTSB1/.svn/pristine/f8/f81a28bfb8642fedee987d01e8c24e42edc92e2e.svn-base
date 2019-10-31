<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/k/kui-base-form.jsp" %>

	<script type="text/javascript">
		var aaa = 444;
		function opchild() {
			top.$.dialog({
				id: 'sdfgergerg',
				width: 200, height: 100,
				content: 'url:demo/dialog5.jsp',
				lock: true, parent: api,
				cancel: function () {
				},
				ok: function () {
				}
			});
		}
		$(window).focus(function () {
			//处于激活状态
			//alert(aaa)
		});
	</script>
</head>

<body>
<p style="color:#F00;text-align:center;margin-top:40px;">4
	<button id="child" onclick="opchild();">还想打开？？？？？？？</button>
</p>

<p><button id="close11" onclick="api.close();">关闭</button></p>
</body>
</html>