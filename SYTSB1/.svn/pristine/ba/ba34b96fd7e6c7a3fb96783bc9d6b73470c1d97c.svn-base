<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title></title>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
	<style type="text/css">
		#ow_big_table {width:100%;height:100%;}
		#ow_big_table .obt3 {text-align:right;height:48px;}
		#ow_big_table .obt3 input {margin-right:30px;margin-top:10px;}
		#tree_big_tb {width:100%;height:100%;/*border-top:1px solid #0066FF;border-left:1px solid #0066FF;*/}
		#tree_big_tb_td_left {vertical-align:top;width:278px;height:100%;}
		#tree_big_tb_td_left iframe {width:99%;height:100%;}
		#tree_big_tb_td_center {width:4px;border:1px solid #276BA2;cursor:col-resize;}
		#tree_big_tb_td_right {vertical-align:top;height:100%;}
		#tree_big_tb_td_right iframe {width:100%;height:100%;}
		.ipt_02 {background:url("k/kui/images/button_1.gif");width:71px;height:23px;border:0px;padding:3px 0px 0px 5px;}
	</style>
	
	<%@ include file="/k/kui-base-form.jsp"%>
	<script type="text/javascript">
			$(function() {
				$("#form_index").initForm({ //参数设置示例
					toolbar : [{
						text : '关闭',
						id : 'close',
						icon : 'cancel',
						click : close
					} ],
					getSuccess : function(res) {

					}
				});
				/* var height = $(window).height();
				$('#tree_big_tb_td_left').css({height:height});
				$('#tree_big_tb_td_right').css({height:height}); */
			})
		
		function close(){	
			api.close();
		}
		
		function initLeft(){
			document.getElementById("leftIframe").src='app/equipment/equipment_look_left.jsp'; 
		}
	</script>	
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="javascript:initLeft();">
<div style="overflow:hidden">
<form id="form_index" style="height:99%;">
	<table border="0" cellpadding="0" cellspacing="0" id="tree_big_tb" height="100%">
		<tr>
			<td id="tree_big_tb_td_left">
				<iframe name="left" id="leftIframe"src="" width="100%" height="100%" scrolling="no" frameborder="0" style="border-right:1px solid #a2c8fb"></iframe>
			</td>
			<td id="tree_big_tb_td_right">
				<iframe name="right" id="rightIframe" src="" width="100%" height="100%" scrolling="no" frameborder="0" border="0"></iframe>				
			</td>
		</tr>
	</table>
</form>
</div>
</body>
</html>


