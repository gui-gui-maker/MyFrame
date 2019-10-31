<%@ page contentType="text/html;charset=UTF-8" %>
<%@page import="com.khnt.utils.DateUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.scts.payment.bean.InspectionPayInfo"%>
<%@page import="java.util.List"%>
<%@page import="com.scts.payment.bean.InspectionInfoDTO"%>
<%@page import="com.lsts.finance.bean.CwBankDTO"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ include file="/k/kui-base-form.jsp"%>
<style type="text/css">
html,body,form{
	margin:0;
	padding:0;
	height:100%;
	width:100%;
}
form h2{
	text-align: center;
}
form div{
	height:60%;
	margin-top:100px;
	padding-left:40%;
}
form div img{
}
</style>
<script type="text/javascript">
	$(function() {
		$("#form1").initForm({ //参数设置示例
			toolbar : [{
				text : '关闭',
				id : 'close',
				icon : 'cancel',
				click : close 
			} ],
			getSuccess : function(res) {
			
			}
		});
	})
	function close(){	
		if("1" == "${param.type}"){
			api.data.pwindow.api.close();
			api.data.pwindow.api.data.window.refreshGrid();
		}else{
			api.data.window.refreshGrid();
		}
		api.close();
	}
</script>
</head>
<body >
<form id="form1">
<h2>用app扫描下方二维码，跳转签字确认页面</h2>
<div><img src='payment/payInfo/showTwoDim.do?id=paymentSheet-${param.id }'></div>

</form>
</body>
</html>