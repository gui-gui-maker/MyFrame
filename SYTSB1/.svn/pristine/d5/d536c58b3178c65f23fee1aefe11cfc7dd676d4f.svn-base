<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.alibaba.fastjson.JSONArray"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="util.TS_Util"%>
<%@page import="util.ReportUtil"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>院空间</title>
<%@include file="/k/kui-base-list.jsp"%>
<link rel="stylesheet" type="text/css" href="css/stylegx1.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/animate.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/responsive-tabs.css " />
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/easyResponsiveTabs.js"></script>
<script type="text/javascript" src="/app/cloud_platform/owner/js/jquery.contextmenu.r2.js"></script>
<script type="text/javascript" src="/app/cloud_platform/upload/easyui/jquery.easyui.min.js"></script>
<%
JSONArray auditData=TS_Util.getOaAuditNum();
%>

<!--[if lt IE 9]> 

<script type="text/javascript" src="PIE/PIE.js"></script> 

<![endif]-->

<script>

$(function() { 


//获取待处理数据
var auditData=<%=auditData%>;

//alert(noticeData);
// var jsonNoticeData = $.parseJSON(noticeData);
if(auditData!=null){
   
   
    var auditUl=$('#tab');
    var l = auditUl.length;
    for (var i = 0; i < 100; i++) {
    /* var remark=auditData[i].remark?auditData[i].remark:"";
    alert(remark);
    return; */
    //auditUl.append($("<li><p><span><a href='javascript:void(0);' onclick=\"openDialog('待处理','"+auditData[i].url+"')\">"+auditData[i].lable+"（共"+auditData[i].count+"份）</a></span></p></li>"));	
    auditUl.append($("<tr><td style='background:url(k/kui/images/icon_vip.gif) no-repeat left center; padding-left:25px;'><a href='javascript:void(0);' onclick=\"openDialog('待处理','"+auditData[i].url+"')\"><font size='3' >"+auditData[i].lable+"(共:"+auditData[i].count+"份)----"+auditData[i].remark+"</font></a></td></tr>"));	
   
    }
}
});


function openDialog(title,url){
	top.$.dialog({
		width : $(top).width() * 0.8,
		height : $(top).height() * 0.8,
		lock : true,
		title : title,
		data : {
			"window" : window
		},
		content : 'url:/' + url
	});
}

</script>


</head>
<body >
<fieldset class="l-fieldset" style="height:90%; overflow:auto;">
					<legend class="l-legend" style="font-size:16px; padding:6px 20px;">
						<div>
							待处理列表
						</div>
					</legend>
		<table border="0" cellpadding="3" id="tab"  cellspacing="0" width="" style="font-size:18px; line-height:36px; margin:5px 20px;">
				
				
				
						<%-- 	<tr>
							<td style="background:url(k/kui/images/icon_vip.gif) no-repeat left center; padding-left:25px;"><a href="app/flow/report_enter_list.jsp?flow_num=${item[3]}&function=${item[4]}&flowId=${item[5]}"><font size="3" >${item[0]}(共:${item[2]}份)&nbsp;&nbsp;&nbsp;&nbsp;---${item[1]}</font></a></td>
						
							</tr> --%>
						
					
				</table>
	</fieldset>
		<!-- 	<div >
						
							
							
							<div class="list_box">

									<ul class="event_list">
									
											<li><p><span><a href="#">报告录入（共2份）</a></span></p></li>
											<li><p><span><a href="#">报告审核（共1份）</a></span></p></li>
											<li><p><span><a href="#">报告签发（共110份）</a></span></p></li>
											<li><p><span><a href="#">报告归档（共8份）</a></span></p></li>
										
									</ul>
					
									<div class="clearfix"></div>
							

						   </div>
						
						</div>		 -->
</body>
</html>
