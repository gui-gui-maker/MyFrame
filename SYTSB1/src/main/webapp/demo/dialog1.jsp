<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
	<%@include file="/k/kui-base-form.jsp" %>


	<script type="text/javascript">
		var aaa = 111;
		function opchild(arg) {
			top.$.dialog({
				id: 'LHG1976D',
				width: 500, height: 300,
				content: 'url:demo/dialog2.jsp',
				lock: true, parent: api,
				cancel: function () {
				},
				ok: function () {
				}
			});
		}
		;
		function opchild2(arg) {
			api.data.window.openwin1is2();
		}
		$(window).focus(function () {
			//处于激活状态
			//alert(aaa)
		});
		$(function () {//jQuery页面载入事件
			//api.buttonDisplay(false);//关闭弹窗按钮层。
			api.button({
				name: '登录11',
				focus: true, icon: "search",
				callback: function () {
				}
			}, {
				name: '取消22'
			});
			$("#close11").toggle(function () {
				api.buttonDisplay(true);
				$(this).text($(this).text().replace(/显示/g, "关闭"));
			}, function () {
				api.buttonDisplay(false);
				$(this).text($(this).text().replace(/关闭/g, "显示"));
			})
		});
	</script>

</head>
<body>

<p style="color:#F00;text-align:center;margin-top:40px;">1
	<button id="child" onclick="opchild();">打开一个锁屏窗口</button>
</p>

<p style="color:#F00;text-align:center;margin-top:40px;">1
	<button id="child2" onclick="opchild2();">关闭本窗口，并打开一个新锁屏窗口</button>
</p>

<p><button id="close11" onclick="">显示本窗口的按钮</button></p>

<p><button id="close12" onclick="api.close();">关闭</button></p>

<table border="1" cellpadding="3" cellspacing="0" width="100%" height="" align="center" class="" style="border-collapse:collapse;" bgcolor="#eff7ff" bordercolor="#96c2f1">
	<tr>
		<td><object id="TANGER_OCX_OBJ" classid="clsid:01DFB4B4-0E07-4e3f-8B7A-98FD6BFF153F"
					codebase="oa/plugin/OfficeControl.cab#version=5,0,1,0" width="100%" height="95%">
		<param name="BorderStyle" value="1">
		<param name="Titlebar" value="0">
		<param name="StatusBar" value="-1">
		<param name="BorderStyle" value="0">
		<param name="TitlebarColor" value="42768">
		<param name="TitlebarTextColor" value="0">
		<param name="MenubarColor" value="14402205">
		<param name="BorderColor" value="14402205">
		<PARAM NAME="MenuButtonColor" VALUE="16180947">
		<param name="MenuBarStyle" value="3">
		<param name="MenuButtonStyle" value="7">
		<param name="FilePrint" value="-1">
		<param name="FileSave" value="-1">
		<param name="FileSaveAs" value="-1">
		<param name="MakerCaption" value="成都川大科鸿新技术研究所">
		<param name="MakerKey" value="5CF5FD7E2FCEE83E94BC681C4162AD366621F605">
		<param name="ProductCaption" value="川大科鸿OA">
		<param name="ProductKey" value="01DB7CC2E552800DCDD79527D8FD0BD3D07F3028">
		<span style="color:red">不能装载文档控件。请在检查浏览器的选项中检查浏览器的安全设置。</span>
    </object></td>
	</tr>
</table>

</body>
</html>