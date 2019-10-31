<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>安装OA系统文档控件</title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/"/>
<link rel="stylesheet" type="text/css" href="app/oa/css/newbase.css" />
<script type="text/javascript" src="k/kui/frame/jquery.min.js"></script>
<script type="text/javascript">
	function changeColor() {
		var color = "#f00|#0f0|#00f|#880|#808|#088|yellow|green|blue|gray";
		color = color.split("|");
		document.getElementById("tishi").style.color = color[parseInt(Math
				.random()
				* color.length)];
	}

	setInterval("changeColor()", 200);

	function blink(elId) {
		var html = '';
		if (document.all)
			html += 'var el = document.all.' + elId + ';';
		else if (document.getElementById)
			html += 'var el = document.getElementById("' + elId + '");';
		html += 'el.style.visibility = '
				+ 'el.style.visibility == "hidden" ? "visible" : "hidden"';
		if (document.all || document.getElementById)
			setInterval(html, 800)
	}
	function init() {
		blink('tishi');
	}
</script>

</head>

<body ONLOAD="init();">
	<div style="display: none" id="_activexContainer">
		<script type="text/javascript">
			document.write('<object id="TANGER_OCX_OBJ" classid="clsid:01DFB4B4-0E07-4e3f-8B7A-98FD6BFF153F" codebase="pub/plugins/office/OfficeControl.cab?r='+ Math.random() + '#version=5,0,1,0""></object>');
		</script>
	</div>
	<div class="container">
		<hr style="display:" color="red">
		<div style="width:100%">
			<img src="pub/plugins/office/img/word.png" style="height: 60px; padding-left: 60px;">
			<img src="pub/plugins/office/img/install_plugin_title.jpg">
			<div id="tishi" style="float: right; display: none">
				<img src="pub/plugins/office/img/up.png" style="height: 30px;" title="点击黄色提示进行安装......">点击黄色提示进行安装......
			</div>
		</div>
		<div class="content">
			<div class="install_process" style="display:;">
				<h2 id="installText">
					<img src="pub/plugins/office/img/loading.gif" style="float: left; margin-top: 5px;">&nbsp;在线安装已经开始，请稍候...
				</h2>

				<h2 id="installSubject" style="display: none; margin-bottom: 20px;">在线安装失败，您可以</h2>
				<ul id="installFAQ" style="display: none; margin-left: 16px;">
					<li style="line-height: 22px;">试试关闭并重新打开浏览器</li>
					<li style="line-height: 22px;" id="nosupportfirefox">或者<a
						nocheck="true"
						href="${pageContext.request.contextPath}/pub/tools/dowloadSoft.jsp?fpath=Office文档控件安装包.rar">下载安装文件</a>&nbsp;手动安装。
					</li>
				</ul>
			</div>

			<div id="axInstallSuccess" class="install_process"
				style="display: none;">
				<div id="axPanelInstallSuccess" style="margin-bottom: 40px;">
					<h2 id="axTitleInstallSuccess" style="margin-bottom: 5px;">安装完成</h2>
					<p style="margin-top: 0;">现在您可以登录OA系统进行文档操作。</p>
					<p>
						<a class="btn" onClick="frameElement.api.close();" href="#">关闭本页</a>
					</p>
				</div>
			</div>

		</div>
	</div>
	<div class="clr"></div>
	<div class="footer">
		<hr />
		<p class="gray">&copy; khnt. All Rights Reserved.</p>
		<p class="gray">成都川大科鸿新技术研究所 版权所有</p>
	</div>
	</div>
	<script type="text/javascript">
		var gTryTimes = 0;
		var OBJ = TANGER_OCX_OBJ.FileNew;
		var InstallActiveX = function() {
			if (gTryTimes < 5) {
				if (OBJ != undefined) {
					document.getElementById('installText').style.display = "none";
					document.getElementById('axInstallSuccess').style.display = "";
					document.getElementById('tishi').style.display = "none";

				}
			} else {
				if (OBJ == undefined) {
					document.getElementById('installText').style.display = "none";
					document.getElementById('installSubject').style.display = "";
					document.getElementById('installFAQ').style.display = "";
				}
			}
			gTryTimes = gTryTimes + 1;
			if (gTryTimes <= 20)
				window.setTimeout(InstallActiveX, 2000);
		};
		$().ready(function() {
			InstallActiveX();
		});

		window.setTimeout(tishi, 1000);
		function tishi() {
			//判断是否是IE
			if (document.all) {
				//IE代码
				document.getElementById('tishi').style.display = ""
			} else {
				//其他
			}
		}
	</script>
</body>
</html>