<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="/k/kui-base-form.jsp"%>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8"/>
	<title></title>
	<base href="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/" />
	<style type="text/css">
		#ow_big_table {width:100%;height:100%;}
		#ow_big_table .obt3 {text-align:right;height:48px;}
		#ow_big_table .obt3 input {margin-right:30px;margin-top:10px;}
		#tree_big_tb {width:100%;height:100%;/*border-top:1px solid #0066FF;border-left:1px solid #0066FF;*/}
		#tree_big_tb td {/*border-bottom:1px solid #0066FF;border-right:1px solid #0066FF;*/}
		#tree_big_tb_td_left {vertical-align:top;width:278px;height:100%;}
		#tree_big_tb_td_left iframe {width:100%;height:100%;}
		#tree_big_tb_td_center {width:4px;border:1px solid #276BA2;cursor:col-resize;}
		#tree_big_tb_td_right {vertical-align:top;height:100%;}
		#tree_big_tb_td_right iframe {width:100%;height:100%;}
		.ipt_02 {background:url("k/kui/images/button_1.gif");width:71px;height:23px;border:0px;padding:3px 0px 0px 5px;}
	</style>
	<script>
	//导入JS文件
	var ImportFormJS=true;
	var PageTitle = "打印合格标签";
	</script>
	<script type="text/javascript">
			$(function() {



				$("#form1").initForm({ //参数设置示例
					toolbar : [ {
						text : '打印',
						//id : 'save',
						icon : 'save',
						click : save
					}
				
					, 
					{
						text : '关闭',
						//id : 'close',
						icon : 'cancel',
						click : close
					} ],
					getSuccess : function(res) {
						
					
					}
				});
			
				var height = $(window).height();
				$('#tree_big_tb_td_left').css({height:height});
				$('#tree_big_tb_td_right').css({height:height});
			})
		
		function close(url)
			{	
				var type = '${param.type}';
				if(type=="2"){					
					api.close();
				}else{
					api.data.window.Qm.refreshGrid();
					api.close();
				}
			}
			function save(url)
			{
				left.printAll();
	
	
			}
	function initLeft(){
		document.getElementById("leftIframe").src='report/query/showPrintTags.do?flag=${param.flag}&id='+api.data.id; 
	}
	</script>		
</head>
<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0" onload="javascript:initLeft();">
<div style="overflow:hidden">
<form id="form1" style="height:99%;">

			<table border="0" cellpadding="0" cellspacing="0" id="tree_big_tb" height="100%">
				<tr>
					<td id="tree_big_tb_td_left">
						<iframe name="left" id="leftIframe" src="" width="100%" height="100%" scrolling="atuo" frameborder="0" border="0"></iframe>
					</td>
					<td id="tree_big_tb_td_right">
						<iframe name="right" src="" width="100%" height="100%" scrolling="no" frameborder="0" border="0"></iframe>				
					</td>
				</tr>
			</table>
</form>
</div>
</body>
</html>


