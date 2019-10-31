<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="/k/kui-base-form.jsp" %>

	<script type="text/javascript">
		var aaa = 333;
		function opchild() {
			top.$.dialog({
				id: 'dzfgdsfgsdfg',
				width: 300, height: 200,
				content: 'url:demo/dialog4.jsp',
				lock: true, parent: api,
				cancel: function () {
				},
				ok: function () {
				}
			});
		}
		function opchild2() {
			top.$.dialog({
				id: 'dzfgdsfgsdfg',
				width: 300, height: 200,
				content: 'url:demo/dialog4.jsp',
				lock: false, parent: api,
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
<p style="color:#F00;text-align:center;margin-top:40px;">3
	<button id="child" onclick="opchild();"><span style="color:#0000FF;">再打开一个锁屏窗口</span></button>
</p>

<p style="color:#F00;text-align:center;margin-top:40px;">3
	<button id="child2" onclick="opchild2();"><span style="color:#ff0000;">再打开一个不锁屏窗口</span></button>
</p>

<p><button id="close11" onclick="api.close();">关闭</button></p>

</body>
</html>