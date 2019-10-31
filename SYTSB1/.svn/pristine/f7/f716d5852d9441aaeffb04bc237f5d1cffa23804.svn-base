<%@page import="com.khnt.utils.StringUtil"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<%@ page import="java.util.*" %>
<%@include file="/k/kui-base-list.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title></title>
<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
<style type="text/css">
.op1 {}
#workaround {width:100%;overflow:;text-align:center;margin:0px 40px 0px 0px;}
#table_detail_big {width:100%;border:0px solid #FF0000;margin-bottom:20px;}
.ipt_01 {border:0px;border-bottom:1px solid #8CBBDB;}
.d_tr {height:25px;position: absolute;left: 15%;}
.d_title {text-align:right;padding:0px 10px 0px 0px;width:15%;font-size:14px;}
.d_input {width:15%;}
</style>
<script language="javascript">
function init()
{	
	$("#barcode").bind("input propertychange", function () {
    	var bar_code = $("#barcode").val();
    	if(bar_code.length == 13){
    		check(bar_code);
    	}
    });
}
/* 页面加载完，条形码文本输入框框自动获取焦点 */
setTimeout(function(){
		try{
			document.getElementById('barcode').focus();
		} catch(e){}
	}, 500);
/* 校核二维码是否存在 */
function check(barcode){
	$.ajax({
    	url: "equipment2Action/queryByBarcode.do?barcode="+barcode,
        type: "POST",
        success: function (resp) {
        	if(resp.success){
        		var equipment = resp.equipment;//接收设备对象
        		var id = equipment.id;
        		$("#rightIframe",parent.document.body).attr("src","app/equipment/equipment_look.jsp?status=detail&id="+id)
        	}else{
        		$("#rightIframe",parent.document.body).attr("src","app/equipment/equipment_look_notice.jsp")
        	}
        },
        error: function (data0,stats) {
        	$("#rightIframe",parent.document.body).attr("src","app/equipment/equipment_look_notice.jsp")
        }
    });
	$("#barcode").val("").focus();
}			
</script>
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="init();">
<div id="workaround">
	<h1 style="padding:5mm 0 2mm 0;font-family:微软雅黑;font-size:5mm;text-align:center;">设&nbsp;备&nbsp;扫&nbsp;码&nbsp;查&nbsp;询</h1></br>
	<table>
		<tr class="d_tr">
			<td class="d_title">扫码框</td>
			<td class="d_input" >
				<input name="barcode" id="barcode" type="text" ltype='text' validate="{maxlength:13}"/>
			</td>
		</tr>
	</table>
</div>
</body>
</html>
