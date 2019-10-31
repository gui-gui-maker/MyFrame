<%@ page contentType="text/html;charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<head pageStatus="${param.status}">
<%@ include file="/k/kui-base-form.jsp"%>
<title></title>
<script type="text/javascript">
var basepath = "${pageContext.request.contextPath}/";
$(function() {
	$("#form1").initForm({ //参数设置示例
		toolbar : [ {
			text : '打印',
			icon : 'print',
			click : printThis
		},  {
			text : '导出',
			//id : 'save',
			icon : 'excel-import',
			click : openDialog
		},{
			text : '关闭',
			//id : 'close',
			icon : 'cancel',
			click : function (){
				api.close();
			}
		} ],
		  toolbarPosition :"top"
	})
})


function openDialog(type){
	   /*  var fd = new ActiveXObject("MSComDlg.CommonDialog");
	    fd.Filter = type;
	    fd.FilterIndex = 2; 
	     fd.MaxFileSize = 128;
	     fd.Filter = "Excel Files(*.xls)|*.xls";
	     fd.filename="锅炉排放测试统计表（按燃料类型分）";
	    fd.ShowSave();
	    if(fd.filename=="" || fd.filename.length<1)return false;
	   var filepath = fd.filename+"";
	  // alert(filepath) */
	  $("#downa").attr("href","contractInfoAction/sametimeStatistic.do?type=contrast&"+
				"view=app/fwxm/contract/statistic/sametime_statistics_exp");
	  $("#downexcel").click();
	  /* top.$.dialog({
				width :695 , 
				height : 942, 
				lock : true, 
				title:"按区域统计",
				content: "url:contractInfoAction/sametimeStatistic.do?type=contrast&"+
						"view=app/fwxm/contract/statistic/sametime_statistics_exp",
				data : {"window" : window}
			}).max();
	   */
	    //return fd.filename;
	}

function printThis(){
	var win = $("body").html();
	$("body").html($("#f").html());
	window.print();
	/* alert(1);
		pagesetup_default(); */
		$("body").html(win);
}

</script>
<style type="text/css">
.text_double{
font-weight: bold;
}

</style>
</head>
<body>
	<form id="form1">
	<div id="f">
	<h1 align="center">合同额（月份）同期对比（万元）</h1>
	<br />
	<a style="display: none;" id="downa"  target="_blank"><span id="downexcel">下载</span></a>
	<table width="90%" border="1" align="center" cellpadding="3" cellspacing="3"
	 style="text-align: center; font-size: small;">
		<tr class="text_double">
		<td>年份</td><td>总金额</td><td>1月</td><td>2月</td><td>3月</td><td>4月</td>
		<td>5月</td><td>6月</td><td>7月</td><td>8月</td><td>9月</td><td>10月</td><td>11月</td><td>12月</td>
		</tr>
		<c:forEach items="${list}" var="data">
		
			<tr>
				<td>${data.year }</td><td>${data.all }</td><td>${data.m_01 }</td><td>${data.m_02 }</td><td>${data.m_03 }</td><td>${data.m_04 }</td>
				<td>${data.m_05 }</td><td>${data.m_06 }</td><td>${data.m_07 }</td><td>${data.m_08 }</td><td>${data.m_09 }</td><td>${data.m_10 }</td>
				<td>${data.m_11 }</td><td>${data.m_12 }</td>
			</tr>
		</c:forEach>
		
	</table>
	</div>
	
	</form>
</body>

</html>
