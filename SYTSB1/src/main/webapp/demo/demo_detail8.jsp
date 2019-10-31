<%@ page contentType="text/html;charset=UTF-8" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}" pageKeys="detail2">
    <title></title>
    <!-- 每个页面引入，页面编码、引入js，页面标题 -->
    <%@include file="/k/kui-base-form.jsp" %>

    <script type="text/javascript">
    $(function () {

	});
	function init_detail2() {
		//top.$.dialog.alert("自定义初始化页面init函数",null,api);
		//top.$.dialog.confirm("自定义初始化页面init函数2222",function(){alert("yes")},function(){alert("no")},api);
		//top.$.dialog.prompt("自定义初始化页面init函数3333",function(){alert("yes")},"xxxxxxxxxxxxxxxxx",api);
		//top.$.dialog.tips("系统开发完成系统开发完成系统开发完成系统开发完成系统开发完成",311,"k/kui/images/icons/dialog/32X32/hits.png",function(){alert("是不");},0);
	}
	;

	function closeWin() {
		top.$.dialog.list["detail2"].close();
		top.$.dialog.list["detail1"].close();
	}
	;

	function getWin() {
		var x = top.$.dialog.list["detail1"].content;
		alert(x.ABC);
	}
	;

	function getWin2(arg) {
		var wObj = top.$.dialog({
			id: "detail3",
			width: 300,
			height: 150,
			lock: true, parent: api,
			title: "详细页面2",
			//cancel:function(){},
			cancel: true,
			ok: function () {
				var datas = this.iframe.contentWindow.getSelectResult();
				if (datas) {
					alert(datas)
				}
				return true;
			},
			content: 'url:demo/detail3.jsp'
		});
	}
	;

	function getSelectResult() {
		return "sdfasdfasdfasdf";
	}

    </script>
</head>
<body>





<div class="item-tm">
	<div class="l-page-note"><div class="l-page-note-div">操作说明：近年来，研究所一直致力于RFID和智能卡读写设备的研发和生产，目前已有多款定型产品和解决方案。研究所紧紧抓住RFID技术的发展趋势和潮流，不断加大在RFID产业方面的研发和生产投入，目前研究所在RFID和智能卡读写设备方面的研发技术处于国内领先水平。</div></div>
</div>

<div class="item-tm">
	<div class="div1" id="zdyToolbar" style=""></div>
</div>

<div class="item-tm">
	<p>自定义区3</p>
</div>

<div class="scroll-tm">


    <button onclick="getWin();">取窗口的对象。</button>
    <button onclick="closeWin();">关闭窗口。</button>
    <button onclick="getWin2();">再次打开一个新窗口</button>

</div>

<div class="toolbar-tm">
	<div class="toolbar-tm-bottom">
		<div id="toolbar1">什么</div>
	</div>
</div>



</body>
</html>
