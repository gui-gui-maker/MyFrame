<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head pageStatus="${param.pageStatus}">
<title></title>
<!-- 每个页面引入，页面编码、引入js，页面标题 -->
<%@include file="/k/kui-base-list.jsp" %>
<script type="text/javascript">

var bdDate = [];
var bdDateKey = 0;
$(function () {
	pageTitle({to: "#title", text: "数据比对", note: '一共需要比对<span id="bdsp1">0</span>组数据，<span id="bdsp2"></span>', icon: ""});
	$("#toolbar1").ligerButton({
		items: [
			{text: "关闭", id: "close", icon: "cancel", click: function () {
				api.close();
			} }
		]
	});
	//2013-5-20 下午10:18 lybide
	//var bdDate=[];
	bdDate.push({id: "gsj", text: "工商局", url: "sssss", end: function () {
	}});
	bdDate.push({id: "yh", text: "银行", url: "sssss", end: function () {
	}});
	bdDate.push({id: "swj", text: "税务局", url: "sssss", end: function () {
	}});
	bdDate.push({id: "gaj", text: "公安局", url: "sssss", end: function () {
	}});
	bdDate.push({id: "rsb", text: "人社部", url: "sssss", end: function () {
	}});
	bdDate.push({id: "cz", text: "财政", url: "sssss", end: function () {
	}});
	bdDate.push({id: "ggj", text: "公积金中心", url: "sssss", end: function () {
	}});
	bdDate.push({id: "dsr", text: "低收入库", url: "sssss", end: function () {
	}});
	var alStr1 = [];
	for (var i = 0; i < bdDate.length; i++) {
		var item = bdDate[i];
		alStr1.push(item["text"]);
		var tr;
		if (false) {
			//tr=$('<tr id="bdtr'+i+'"><td class="l-bd-td1 c-1">正在对比 ( '+item["text"]+' ) 数据</td><td class="l-bd-td2"><div id="bdtd2_'+item["id"]+'"></div></td><td class="l-bd-td3"><img src="k/kui/images/loading-s7.gif" border="0" alt=""/></td></tr>');
		} else {
			tr = $('<tr id="bdtr' + i + '"><td class="l-bd-td1"><span>等待比对</span> ( ' + item["text"] + ' ) 数据</td><td class="l-bd-td2"><div id="bdtd2_' + item["id"] + '"></div></td><td class="l-bd-td3"></td></tr>');
		}
		$("#lbd1").append(tr);
	}
	$("#bdsp1").html(alStr1.length);
	$("#bdsp2").html(alStr1.join("、"));
	bdLoadData(bdDateKey);
});
function bdLoadData(srci) {//console.log(srci,bdDate.length);
	var items = bdDate;
	var item;
	for (var i = 0; i < items.length; i++) {
		if (i.toString() == srci.toString()) {
			item = items[i];
		}
	}
	;//alert(liger.toJSON(item));
	if (srci == bdDate.length) {//2013-5-21 上午9:27 lybide
		$("#bdtr" + (bdDate.length - 1)).find("td:eq(0)").addClass("l-db-color2").find("span").html('完成比对');
		$("#bdtr" + (bdDate.length - 1)).find("td:eq(2)").html('<table border="1" cellpadding="0" cellspacing="0" width="100%" height="" align="center"> <tr> <td>成都小型公司</td> <td>地址：不晓得</td> <td>电话：028-88888888</td> </tr> </table>');
	}
	if (!item) {
		return;
	}
	//alert($("#bdtr"+srci).find("td:eq(0)").html())
	$("#bdtr" + srci).find("td:eq(0)").addClass("l-db-color1").find("span").html("正在对比");
	$("#bdtr" + srci).find("td:eq(2)").html('<img src="k/kui/images/loading-s7.gif" border="0" alt=""/>');
	if (srci > 0) {
		$("#bdtr" + (srci - 1)).find("td:eq(0)").addClass("l-db-color2").find("span").html('完成比对');
		$("#bdtr" + (srci - 1)).find("td:eq(2)").html('<table border="1" cellpadding="0" cellspacing="0" width="100%" height="" align="center"> <tr> <td>成都小型公司</td> <td>地址：不晓得</td> <td>电话：028-88888888</td> </tr> </table>');
	}
	;

}
;
var sI1 = setInterval(function () {
	bdDateKey++;
	bdLoadData(bdDateKey);//console.log([bdDateKey,bdDate.length]);
	if (bdDateKey == bdDate.length) {
		clearInterval(sI1);
	}
}, 1000);
</script>

<style type="text/css">
.l-bd {
	padding:5px;
}

.l-bd-talbe {
}

.l-bd-talbe td {
	padding:2px;
}

.l-bd-title {
	padding:2px;
	text-align:center;
}

.l-bd-title div {
	border-bottom:1px solid #cccccc;
	padding:0px 0px 5px 0px;
}

.l-bd-td1 {
	width:160px;
}

.l-bd-td2 {
	width:32px;
	height:32px;
}

.l-bd-td2 div {
	background:url("demo/img/bd-icon1.png") no-repeat -32px -32px;
	width:32px;
	height:32px;
	border:0px solid #FF0000;
}

.l-bd-td3 {
	text-align:center;
}

.l-db-color1 {
	color:#ff0000;
}

.l-db-color2 {
	color:#0d6811;
}

	/* 2013-5-20 下午10:33 lybide */
#bdtd2_swj {
	background-position:0px 0px;
}

	/*税务局*/
#bdtd2_yh {
	background-position:-32px 0px;
}

	/*银行*/
#bdtd2_cz {
	background-position:-64px 0px;
}

	/*财政*/
#bdtd2_zjb {
	background-position:-96px 0px;
}

	/*住建部*/
#bdtd2_gaj {
	background-position:-128px 0px;
}

	/*公安局*/
#bdtd2_nj {
	background-position:-160px 0px;
}

	/*农机*/
#bdtd2_nw {
	background-position:-192px 0px;
}

	/*农委*/
#bdtd2_ggj {
	background-position:-224px 0px;
}

	/*公积金中心*/
#bdtd2_cgs {
	background-position:-256px 0px;
}

	/*车管所*/
#bdtd2_rsb {
	background-position:-288px 0px;
}

	/*人社部*/
#bdtd2_gsj {
	background-position:-320px 0px;
}

	/*工商局*/
#bdtd2_jtj {
	background-position:-352px 0px;
}

	/*交通局*/
#bdtd2_dsr {
	background-position:-384px 0px;
}

	/*低收入*/
</style>

</head>
<body>

<div class="item-tm">
	<div id="title"></div>
</div>

<div class="scroll-tm">
	<div class="l-bd">
		<%--<div class="l-bd-title"><div></div></div>--%>
		<table id="lbd1" border="0" cellpadding="0" cellspacing="0" width="100%" height="" align="center" class="l-bd-talbe">
			<%--<tr>--%>
				<%--<td class="l-db-td1">正在对比 ( 工商局 ) 数据</td>--%>
				<%--<td class="l-db-td1"><div></div></td>--%>
				<%--<td class="l-db-td1"><img src="k/kui/images/loading-s7.gif" border="0" alt=""/></td>--%>
			<%--</tr>--%>
			<%--<tr>--%>
				<%--<td class="l-db-td1">正在对比 ( 银行 ) 数据</td>--%>
				<%--<td><img src="k/kui/images/loading-s7.gif" border="0" alt=""/></td>--%>
			<%--</tr>--%>
			<%--<tr>--%>
				<%--<td class="l-db-td1">正在对比 ( 税务局 ) 数据</td>--%>
				<%--<td><img src="k/kui/images/loading-s7.gif" border="0" alt=""/></td>--%>
			<%--</tr>--%>
			<%--<tr>--%>
				<%--<td class="l-db-td1">正在对比 ( 公安局 ) 数据</td>--%>
				<%--<td><img src="k/kui/images/loading-s7.gif" border="0" alt=""/></td>--%>
			<%--</tr>--%>
			<%--<tr>--%>
				<%--<td class="l-db-td1">正在对比 ( 人社部 ) 数据</td>--%>
				<%--<td><img src="k/kui/images/loading-s7.gif" border="0" alt=""/></td>--%>
			<%--</tr>--%>
		</table>
	</div>
</div>

<div class="toolbar-tm">
	<div class="toolbar-tm-bottom">
		<div id="toolbar1"></div>
	</div>
</div>

</body>
</html>
